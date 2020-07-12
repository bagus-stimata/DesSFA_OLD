package com.erp.distribution.sfa.model.modelenum;

public enum EnumMaxRowsShow {
	MAX_1("MAX_1", 1,"max 1 Bulan"),
	MAX_3("MAX_3", 2,"max 3 Bulan"),
	MAX_6("MAX_6", 3,"max 6 Bulan"),
	MAX_12("MAX_12", 4,"max 12 Bulan"),
	ALL("ALL", 100,"Semua Data"); //Principal
    
    private String stringId;
    private int intId;
    private String description;
    
    private EnumMaxRowsShow(String stringId, int intId, String description){
        this.stringId = stringId;
        this.description = description;   
        this.intId = intId;
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

	public int getIntId() {
		return intId;
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}
    

}
