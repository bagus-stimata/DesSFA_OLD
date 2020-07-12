package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeSettlement {
    AR_CB_SETORAN(1, "AR_CB_SETORAN", "Payment: AR Cash vs Cash Bank Deposit"),
    WHSTK_REALSTK(2, "WHSTK_REALSTK", "Warehouse Stock Card vs Real Stock Sales Admin"),
    OTH1(10, "OTH1", "Other");
    
    private int intId;
    private String stringId;
    private String description;
    
    private EnumTipeSettlement(int intId, String stringId, String description){
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
