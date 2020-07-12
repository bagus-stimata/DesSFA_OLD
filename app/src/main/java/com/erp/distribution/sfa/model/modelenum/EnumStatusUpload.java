package com.erp.distribution.sfa.model.modelenum;

public enum EnumStatusUpload {
    OPEN(0, "OPEN", "Belum dilakukan apapun"),
    UPLOADED(1, "UPLOADED", "Selesai di upload"),
    PENDING(2, "PENDING", "Pending atau penundaan")
    ;
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumStatusUpload(int intCode, String strCode, String description){
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
