package impl.owls.sh.handler;

import org.mindswap.query.ValueMap;

import impl.owls.sh.ObjectRequest;
import impl.owls.sh.SHHandler;

public abstract class SHDiagnostic extends SHHandler {
	
	public SHDiagnostic(SHHandler successHandler) {
		super(successHandler);
	}
	
	public ValueMap handleHealing(ObjectRequest objectRequest) {
		ValueMap result = null;
		if (canExecute(objectRequest))
			 result = execute(objectRequest);
		System.out.println("next: "+result);
		if (result == null)
			result = super.handleHealing(objectRequest);
			
		return result;
	}
	
	public abstract ValueMap execute(ObjectRequest objectRequest);
	public abstract boolean canExecute(ObjectRequest objectRequest);
}
