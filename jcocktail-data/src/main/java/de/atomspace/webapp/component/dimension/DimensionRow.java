package de.atomspace.webapp.component.dimension;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;

import de.atomspace.webapp.component.unit.Unit;

public class DimensionRow {
	
	
	@DBRef private Unit unit;
	
	private BigDecimal factor;
	
	
	public DimensionRow() {
		
	}
	
	public DimensionRow(Unit unit, BigDecimal factor){
		this.unit = unit;
		this.factor = factor;
	}
	
	public Unit getUnit() {
		return unit;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getFactor() {
		return factor;
	}

	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}
	
	

	
	
}
