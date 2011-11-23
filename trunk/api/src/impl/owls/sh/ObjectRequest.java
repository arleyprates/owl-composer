package impl.owls.sh;

import impl.owls.sh.handler.KindFault;

import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owls.grounding.AtomicGrounding;
import org.mindswap.query.ValueMap;

public class ObjectRequest {
	
	private AtomicGrounding grounding;
	private ValueMap values;
	private OWLKnowledgeBase kb;	
	private KindFault kind;
	
	public AtomicGrounding getGrounding() {
		return grounding;
	}
	public void setGrounding(AtomicGrounding grounding) {
		this.grounding = grounding;
	}
	public ValueMap getValues() {
		return values;
	}
	public void setValues(ValueMap values) {
		this.values = values;
	}
	public OWLKnowledgeBase getKb() {
		return kb;
	}
	public void setKb(OWLKnowledgeBase kb) {
		this.kb = kb;
	}
	public KindFault getKind() {
		return kind;
	}
	public void setKind(KindFault kind) {
		this.kind = kind;
	}
}
