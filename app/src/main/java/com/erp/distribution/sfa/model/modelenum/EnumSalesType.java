package com.erp.distribution.sfa.model.modelenum;

public enum EnumSalesType {
    TO("TO", "Taking Order"),
    C("C", "Canvas"),
    TF("TF", "Task Force"),
    SHOP("SHOP", "Shop Sales"),
    R("R", "RETAIL"),
    KS("KS", "Kassa"),
    COL("COL", "Collector"),
    DRV("DRV", "Driver"),
    HLP("HLP", "Helper"),
    OTH("OTH", "Others");
    
    private String stringId;
    private String description;
    
    private EnumSalesType(String stringId, String description){
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
