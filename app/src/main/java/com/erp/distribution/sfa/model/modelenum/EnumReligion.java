package com.erp.distribution.sfa.model.modelenum;

public enum EnumReligion {
    ISL(1, "ISLAM"),
    KRS(2, "KRISTEN"),
    KTK(3, "KATOLIK"),
    HND(4, "HINDU"),
    BDH(5, "BUDHA"),
    OTH1(6, "Aliran Kepercayaan");
    
    private int intId;
    private String description;
    
    private EnumReligion(int intId, String description){
        this.intId = intId;
        this.description = description;    
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
    

}
