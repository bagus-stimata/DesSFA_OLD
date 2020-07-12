package com.erp.distribution.sfa.model.modelenum;

public enum EnumRequestStatus {
    OPEN(0, "OPEN", ""),
    REQUEST(1, "REQUEST", "req"),
    APPROVE(2, "APPROVE", "Apv"),
    APV_HALF(3, "APPV_HALF", "Apv Half"),
    REJECTED(4, "REJECTED", "Rjt"),
    OTH1(10, "Lain-lain", "");
    
    private int intId;
    private String description;
    private String shortCode;
    
    private EnumRequestStatus(int intId, String description, String shortCode){
        this.intId = intId;
        this.description = description;    
        this.shortCode = shortCode;    
    }

	
	public int getIntId() {
		return intId;
	}



	public void setIntId(int intId) {
		this.intId = intId;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getShortCode() {
		return shortCode;
	}


	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
    

}
