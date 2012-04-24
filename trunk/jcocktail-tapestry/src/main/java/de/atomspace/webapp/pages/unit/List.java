package de.atomspace.webapp.pages.unit;

import org.apache.tapestry5.annotations.SessionAttribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitService;

public class List {

	@SessionAttribute
	private Integer unitListNumber;
	
	@Autowired
	@Inject
	private UnitService unitService;
	
	private PageRequest pageRequest;
	
	private Page<Unit> page;
	
	private int pageNumber;
	
	private Unit unit;
	
	void onActivate(){
		setUnitListNumber(pageNumber);
		pageRequest = new PageRequest(pageNumber,10);
		setPage(unitService.findAll(pageRequest));
	}
	
	void onActivate(int id){
		pageNumber = id;
	}
	
	public Page<Unit> getPage() {
		return page;
	}
	
	public void setPage(Page<Unit> page) {
		this.page = page;
	}
	
	public int getPageNr(){
		return page.getNumber();
	}
	
	public int getPreviousPageNr(){
		return page.getNumber()-1;
	}
	
	public int getNextPageNr(){
		return page.getNumber()+1;
	}
	
	public int getLastPageNr(){
		return page.getTotalPages()-1;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Integer getUnitListNumber() {
		return unitListNumber;
	}

	public void setUnitListNumber(Integer unitListNumber) {
		this.unitListNumber = unitListNumber;
	}
	
	
}
