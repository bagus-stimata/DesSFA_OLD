package com.erp.distribution.sfa.model.modelenum;

public enum EnumStatusGiro {
    GIRO_OPEN(0, "O", "OPEN", "Open atau blum ada status"),
    GIRO_CAIR(1, "C", "CAIR",  "Giro Cair"),
    GIRO_TOLAK(2, "T", "REJECT", "Giro Tolak/Blong"),
    ;
    
    private int intCode;
    private String stringCode;
    private String stringCodeLong;
    private String description;
    
    private EnumStatusGiro(int intCode, String strCode, String stringCodeLong, String description){
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
