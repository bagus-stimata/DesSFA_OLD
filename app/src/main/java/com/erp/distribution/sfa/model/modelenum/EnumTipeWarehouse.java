package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeWarehouse {
	GS("GS", "Good Stock"),
    BS("BS", "Bad Stock"),
    CVS("CVS", "Canvas"),
    ALOC("ALOC", "Alokasi")
    ;
    
    private String stringId;
    private String description;
    
    private EnumTipeWarehouse(String stringId, String description){
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
