package br.ufes.inf.lprm.scene.model;

import br.ufes.inf.lprm.situation.model.Part;
import br.ufes.inf.lprm.situation.model.SituationType;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.drools.core.spi.Activation;

import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("serial")
public class SituationCast extends HashMap<String, Object> {
	
	private int hash = 0;

	public SituationCast(Activation activation, SituationType type) throws Exception {
		
		int 	counter;
		String 	roleLabel;
		Object 	obj;
		RuleImpl rule = activation.getRule();
		List<Part> parts = type.getParts();
		
		for(Part p: parts) {
			put(p.getLabel(), activation.getDeclarationValue(p.getLabel()));
		}
	}	
	
	/*public SituationCast(Situation sit) {
		List<Field> fields = SituationUtils.getSituationRoleFields(sit.getClass());
		for(Field field: fields) {
			try {
				this.put(field.getName(), field.get(sit));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}*/

	@Override
	public Object put(String key, Object value) {
		//this.hash = this.hash + (key.hashCode() + value.getClass().hashCode() + ((Entity) value).getEID());
		this.hash = this.hash + (key.hashCode() + value.getClass().hashCode() + value.hashCode());
		return super.put(key, value);
	}

	@Override
	public int hashCode() {
		return hash;
	}

}
