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

/*
 * Created on Dec 27, 2003
 *
 */
package impl.owls.process;

import org.mindswap.owl.OWLIndividual;
import org.mindswap.owls.grounding.AtomicGrounding;
import org.mindswap.owls.process.AtomicProcess;

/**
 * @author Evren Sirin
 *
 */
public class AtomicProcessImpl extends ProcessImpl implements AtomicProcess {
	AtomicGrounding grounding = null;
	
	/**
	 * @param resource
	 */
	public AtomicProcessImpl(OWLIndividual ind) {
		super(ind);
	}

	/* (non-Javadoc)
	 * @see org.mindswap.owls.process.AtomicProcess#getGrounding()
	 */
	public AtomicGrounding getGrounding() {	
		return getService().getGrounding().getAtomicGrounding(this);
	}


    /* (non-Javadoc)
     * @see org.mindswap.owls.process.AtomicProcess#setGrounding(org.mindswap.owls.grounding.AtomicGrounding)
     */
    public void setGrounding(AtomicGrounding grounding) {
        grounding.setProcess(this);
    }


}
