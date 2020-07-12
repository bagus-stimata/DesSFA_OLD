package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeStockTransfer {
    MUTASI_STD_TO_STD(1, "GOOD TO GOOD", "Good Stock To Good Stock"),
    MUTASI_STD_TO_BS(2, "GOOD TO BS",  "Good Stock To BS"),
    MUTASI_BS_TO_STD(3, "BS TO GOOD",  "BS to Good Stock"),
    MUTASI_STD_TO_ALOKASI(4, "GOOD TO ALOK",  "Good Stock To Alokasi"),
    MUTASI_ALOKASI_TO_STD(5, "ALOK TO GOOD",  "Alokasi to Good Stock"),
    MUTASI_STD_TO_CVS(6, "GOOD TO CVS", "Good Stock to Canvas"),
    MUTASI_CVS_TO_STD(7,  "CVS TO GOOD", "Canvas to Good Stock"),
    OTH1(10, "OTH",  "Other");
    
    private int intId;
    private String strId;
    private String description;
    
    private EnumTipeStockTransfer(int intId, String strId, String description){
        this.intId = intId;
        this.strId = strId;
        this.description = description;    
    }

	
	public int getIntId() {
		return intId;
	}



	public void setIntId(int intId) {
		this.intId = intId;
	}



	public String getStrId() {
		return strId;
	}


	public void setStrId(String strId) {
		this.strId = strId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    

}