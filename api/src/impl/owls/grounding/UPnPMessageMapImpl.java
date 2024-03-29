// The MIT License
//
// Copyright (c) 2004 Evren Sirin
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to
// deal in the Software without restriction, including without limitation the
// rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
// sell copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
// IN THE SOFTWARE.

package impl.owls.grounding;

import impl.owl.WrappedIndividual;

import org.mindswap.owl.OWLIndividual;
import org.mindswap.owls.grounding.MessageMap;
import org.mindswap.owls.process.Parameter;
import org.mindswap.owls.vocabulary.FLAServiceOnt;
import org.mindswap.owls.vocabulary.OWLS;

public class UPnPMessageMapImpl extends WrappedIndividual implements MessageMap {
    public UPnPMessageMapImpl(OWLIndividual ind) { 
        super(ind); 
    }

	public String getGroundingParameter() { 
	    return getPropertyAsString(FLAServiceOnt.upnpParameter); 
	}
	
	public void setGroundingParameter(String groundingParam) { 
	    setProperty(FLAServiceOnt.upnpParameter, groundingParam); 
	}
	
	public String getTransformation() { 
	    return getPropertyAsString(OWLS.Grounding.xsltTransformationString); 
	}
	
	public void setTransformation(String xsltTransformation) { 
	    setProperty(OWLS.Grounding.xsltTransformationString, xsltTransformation); 
	}

	public Parameter getOWLSParameter() { 
	    return (Parameter) getPropertyAs(OWLS.Grounding.owlsParameter, Parameter.class); 
	}
	
	public void setOWLSParameter(Parameter param) {
	    setProperty(OWLS.Grounding.owlsParameter, param);
	}
	
	public String toString() {
		return "[" + getOWLSParameter() + " -> " + getGroundingParameter() + "]";
	}
}
