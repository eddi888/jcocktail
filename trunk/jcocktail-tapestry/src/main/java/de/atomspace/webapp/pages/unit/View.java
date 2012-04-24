package de.atomspace.webapp.pages.unit;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;

import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitService;

public class View {
	
	@Property
	@SessionAttribute
	private Integer unitListNumber;
	
	
	@Autowired
	@Inject
	private UnitService unitService;
	
	@Property
	private Unit unit;
	
	void onActivate(String unitName){
		unit = unitService.findOneByName(unitName);
		if(unit==null){
			//TODO FORWARD
		}
	}
	
	public NumberFormat getDecimalFormat() {
		return new DecimalFormat(",##0.00" );
	}
	
}
