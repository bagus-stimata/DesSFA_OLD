package com.erp.distribution.sfa.model.modelenum;

public enum EnumStatusPengiriman {
    NOTA_OPEN(0, "O", "OPEN", "Open atau blum ada status"),
    NOTA_TERKIRIM(1, "T", "KIRIM",  "Terkirim"),
    NOTA_PENDING(2, "P", "PENDING", "Pending Pengiriman/Belum Dikirim"),
    NOTA_BATAL(3, "B", "BATAL", "Nota Batal Seluruhnya "),
    ;
    
    private int intCode;
    private String stringCode;
    private String stringCodeLong;
    private String description;
    
    private EnumStatusPengiriman(int intCode, String strCode, String stringCodeLong, String description){
        this.intCode = intCode;
        this.stringCode = strCode;
        this.stringCodeLong = stringCodeLong;
        this.description = description;    
    }
    public String getStrCode(){
        return stringCode;
    }
    public int getIntCode(){
        return intCode;
    }
    public String getDescription(){
        return description;
    }
	public String getStringCode() {
		return stringCode;
	}
	public void setStringCode(String stringCode) {
		this.stringCode = stringCode;
	}
	public String getStringCodeLong() {
		return stringCodeLong;
	}
	public void setStringCodeLong(String stringCodeLong) {
		this.stringCodeLong = stringCodeLong;
	}
	public void setIntCode(int intCode) {
		this.intCode = intCode;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
