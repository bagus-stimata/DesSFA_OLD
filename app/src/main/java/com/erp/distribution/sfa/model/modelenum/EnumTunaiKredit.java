package com.erp.distribution.sfa.model.modelenum;

public enum EnumTunaiKredit {
	T("T", "Tunai"),
    K("K", "Kredit")
    ;
    
    private String stringId;
    private String description;
    
    private EnumTunaiKredit(String stringId, String description){
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
