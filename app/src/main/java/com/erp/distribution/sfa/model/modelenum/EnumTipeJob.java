package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipeJob {
    JOB_1(1, "JOB_1", "JOB_1"),
    JOB_2(2, "JOB_2", "JOB_2"),
    JOB_3(3, "JOB_3", "JOB_3");
    
    private int intCode;
    private String stringCode;
    private String description;
    
    private EnumTipeJob(int intCode, String strCode, String description){
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
