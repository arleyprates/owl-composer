package impl.owls.sh.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pf.main.MainFunctionalMatcher;
import pf.matcher.implementations.functional.SimilarityDegree;

import org.mindswap.exceptions.ExecutionException;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owls.process.AtomicProcess;
import org.mindswap.owls.process.Input;
import org.mindswap.owls.process.Output;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;
import impl.owls.sh.Degree;
import impl.owls.sh.ObjectRequest;
import impl.owls.sh.SHHandler;
import impl.owls.sh.SimilarService;

public class ReplaceByEquivalent extends SHDiagnostic {
	
	public ReplaceByEquivalent(SHHandler successHandler) {
		super(successHandler);
	}
	
	public ValueMap execute(ObjectRequest objectRequest) {
		System.out.println("ReplaceByEquivalent");
		ValueMap result = null;
		OWLKnowledgeBase kb = objectRequest.getKb();
		System.out.println("uri: " + objectRequest.getGrounding().getProcess().getURI());
		String newURI = getEquivalentURI(objectRequest.getGrounding().getProcess().getURI().toString());
		
		try {
			Service aService = kb.readService(newURI);
			System.out.println("New service: " + aService.getLabel());
			AtomicProcess atomicProcess =(AtomicProcess)aService.getProcess();
			
			ValueMap values = objectRequest.getValues();
			ValueMap newValues = new ValueMap();
			
			for (Iterator iterator = atomicProcess.getInputs().iterator(); iterator.hasNext();) {
				Input input = (Input) iterator.next();
				Input lastInput = objectRequest.getGrounding().getProcess().getInput(input.getLocalName());
				newValues.setDataValue(input, values.getStringValue(lastInput));
			}
			
			try {
				ValueMap auxResult = atomicProcess.getGrounding().invoke(newValues, kb);
				
				result = new ValueMap();
				
				for (Iterator iterator = atomicProcess.getOutputs().iterator(); iterator.hasNext();) {
					System.out.println("Setou output");
					Output output = (Output) iterator.next();
					Output lastOuInput = objectRequest.getGrounding().getProcess().getOutput(output.getLocalName());
					result.setDataValue(lastOuInput, auxResult.getStringValue(output));
				}
		    } catch(ExecutionException ee) {
		    	ee.printStackTrace();
		    	System.out.println("ReplaceByEquivalent failed 2!");
//		    	return null;
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ReplaceByEquivalent failed 3!");
//			return null;
		}
		
		System.out.println("Successful ReplaceByEquivalent!");
		return result;
	}
	
	private String getEquivalentURI(String processURI) {
		MainFunctionalMatcher matcher = new MainFunctionalMatcher();
		List<pf.vo.Service> servicesDiscovered = null;
		Map<String, ArrayList<SimilarityDegree>> resultInput = new HashMap<String, ArrayList<SimilarityDegree>>();
		Map<String, ArrayList<SimilarityDegree>> resultOutput = new HashMap<String, ArrayList<SimilarityDegree>>();
		
		try {
			//TODO Colocar aqui o path correto
			processURI.split("/");
			String directoryPath = "http://localhost:8080/axis/wsFinal/";
			servicesDiscovered = matcher.discoverServices(processURI, directoryPath);
			resultInput = matcher.getResultInputs();
			resultOutput = matcher.getResultOutputs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<SimilarService> serviceList = new ArrayList<SimilarService>();
		for (pf.vo.Service service : servicesDiscovered) {
			if (service.getDegreeMatch().equals("EXACT")) {
				SimilarService newService = new SimilarService(service.getUri().getRawPath(),
						Degree.EXACT);
				newService.inputList = resultInput.get(service.getUri().toString());
				newService.outputList = resultOutput.get(service.getUri().toString());
				serviceList.add(newService);
			} else if (service.getDegreeMatch().equals("PLUGIN")) {
				SimilarService newService = new SimilarService(service.getUri().getRawPath(),
						Degree.PLUGIN);
				newService.inputList = resultInput.get(service.getUri().toString());
				newService.outputList = resultOutput.get(service.getUri().toString());
				serviceList.add(newService);
			} else if (service.getDegreeMatch().equals("SUBSUMES")) {
				SimilarService newService = new SimilarService(service.getUri().getRawPath(),
						Degree.SUBSUMES);
				newService.inputList = resultInput.get(service.getUri().toString());
				newService.outputList = resultOutput.get(service.getUri().toString());
				serviceList.add(newService);
			} else if (service.getDegreeMatch().equals("SIBLING")) {
				SimilarService newService = new SimilarService(service.getUri().getRawPath(),
						Degree.SIBLING);
				newService.inputList = resultInput.get(service.getUri().toString());
				newService.outputList = resultOutput.get(service.getUri().toString());
				serviceList.add(newService);
			}
		}
		
		// Sort serviceList by degree
		Collections.sort(serviceList);
		System.out.println("SERVICES DISCOVERED FOR " + processURI + ":");
		
		for (SimilarService similarService : serviceList) {
			System.out.println(similarService.path + " - "								
					+ similarService.degree);
		}
		
		if(serviceList.size() > 0)
			return serviceList.get(0).path;
		
		return "";
	}
	
	public boolean canExecute(ObjectRequest objectRequest) {
		return true;
	}
}
