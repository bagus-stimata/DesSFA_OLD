package com.erp.distribution.sfa.model.modelenum;

public enum Enum3Level {
    LOW(0, "LOW", "Low"),
    MEDIUM(1, "MED", "Medium"),
    HIGH(2, "HIGH", "High")
    ;
    
    private int intCode;
    private String stringCode;
    private String stringCodeLong;
    private String description;
    
    private Enum3Level(int intCode, String strCode, String description){
        this.intCode = intCode;
        this.stringCode = strCode;
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
