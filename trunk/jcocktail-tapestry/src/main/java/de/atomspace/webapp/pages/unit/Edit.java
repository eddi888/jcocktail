package de.atomspace.webapp.pages.unit;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;

import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitService;

public class Edit {
		
	@Autowired
	@Inject
	private UnitService unitService;
	
	@InjectComponent
    private Form form;

	@Property
	@Persist
	private Unit unit;
	
	void onActivate(String unitName){
		unit = unitService.findOneByName(unitName);
		if(unit==null){
			unit = new Unit();
			unit.setName(unitName);
			unit.setDescription("");
		}
	}
	
	void onActivate(){
		if(unit==null){
			unit = new Unit();
			unit.setName("");
			unit.setDescription("");
		}
	}
	
	public void onVadidateForm(){
		form.recordError("BLABLABLA");
    }
	
	public String onSuccessForm(){
		unitService.save(unit);
		return "unit/list";
    }
}
