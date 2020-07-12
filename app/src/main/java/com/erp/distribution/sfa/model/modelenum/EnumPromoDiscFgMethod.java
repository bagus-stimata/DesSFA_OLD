package com.erp.distribution.sfa.model.modelenum;

public enum EnumPromoDiscFgMethod {
	UOM1("UOM1", "Uom1/KRT"),
    UOM4("UOM4", "Uom4/PCS"),
    VAL("VAL", "Value/Rp"),
    ;
    
    private String stringId;
    private String description;
    
    private EnumPromoDiscFgMethod(String stringId, String description){
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
