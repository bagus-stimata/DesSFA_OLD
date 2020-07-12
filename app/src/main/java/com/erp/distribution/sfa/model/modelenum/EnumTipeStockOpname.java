package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeStockOpname {
	ALL("ALL", "Semua Barang"),
	PAR("PAR", "Parsial"),
	F_ADJ("F_ADJ", "Form Adjust"),
	; //Principal
    
    private String stringId;
    private String description;
    
    private EnumTipeStockOpname(String stringId, String description){
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
