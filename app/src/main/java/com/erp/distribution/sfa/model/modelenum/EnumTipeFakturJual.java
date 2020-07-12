package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeFakturJual {
	F("F", "Faktur"),
	R("R", "Retur"),
	FI("FI", "Nota Intern/Sampling"),
	FDN("FDN", "Nota Piutang Debit"),
	RCN("RCN", "Nota Piutang Credit"),
	SRV("SRV", "Service or Non Stockable Transaction"),
	SO("SO", "Sales Order (Non Stockable)")
	; 
    
    private String stringId;
    private String description;
    
    private EnumTipeFakturJual(String stringId, String description){
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
