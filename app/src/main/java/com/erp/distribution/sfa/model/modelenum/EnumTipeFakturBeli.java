package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeFakturBeli {
	F("F", "Faktur"),
	FF("FF", "Faktur from GR"),
	FI("FI", "Pembelian Intern"),
	INFG("INFG", "Free Good"),
	R("R", "Retur"),
	DTR("DTR", "Destroy or Burning"),
	PO("PO", "Purchase Order(Non Stockable)"),
	GR("GR", "Good Receipt"),
	FDN("FDN", "Nota Hutang Debit(Non Stockable)"),
	RCN("RCN", "Nota Hutang Credit(Non Stockable)"),
	SR("SR", "Permintaan Barang Gudang Pusat(Non Stockable)"),
	PR("PR", "Purchase Requisition(Non Stockable)"),
	; //Principal
    
    private String stringId;
    private String description;
    
    private EnumTipeFakturBeli(String stringId, String description){
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
