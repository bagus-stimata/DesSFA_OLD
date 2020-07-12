package com.erp.distribution.sfa.model.modelenum;

public enum EnumKeyGoogleAPI {
    KEY1(1, "AIzaSyBKZ_U8aTm4IiTFOBZflSDJSIqllFlsk-M", "Key Google API pertama"),
    KEY2(2, "AIzaSyBKZ_U8aTm4IiTFOBZflSDJSIqllFlsk-M", "Key Google API pertama");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumKeyGoogleAPI(int intCode, String strCode, String description){
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

}
