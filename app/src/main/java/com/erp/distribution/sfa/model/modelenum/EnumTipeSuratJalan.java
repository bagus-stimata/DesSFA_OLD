package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeSuratJalan {
    SJ_PENGIRIMAN(1, "SJ_PENGIRIMAN", "Surat Jalan Pengiriman"),
    SJ_PENAGIHAN(2, "SJ_PENAGIHAN", "Surat Jalan Penagihan"),
    OTH1(10, "OTH1", "Other");
    
    private int intId;
    private String stringId;
    private String description;
    
    private EnumTipeSuratJalan(int intId, String stringId, String description){
        this.intId = intId;
        this.stringId = stringId;    
        this.description = description;    
    }

	
	public String getStringId() {
		return stringId;
	}


	public void setStringId(String stringId) {
		this.stringId = stringId;
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
