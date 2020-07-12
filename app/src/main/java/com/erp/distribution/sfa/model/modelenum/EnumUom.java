package com.erp.distribution.sfa.model.modelenum;

public enum EnumUom {
	UOM1("UOM1", "Uom1"),
	UOM2("UOM2", "Uom2"),
    UOM3("UOM3", "Uom3"),
	UOM4("UOM4", "Uom4")
    ;
    
    private String stringId;
    private String description;
    
    private EnumUom(String stringId, String description){
        this.stringId = stringId;
        this.description = description;    
    }

	public String getStringId() {
		return stringId;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    

}
