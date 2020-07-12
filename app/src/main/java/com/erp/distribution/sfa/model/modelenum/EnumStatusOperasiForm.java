package com.erp.distribution.sfa.model.modelenum;

public enum EnumStatusOperasiForm {
    OPEN("OPEN", "Status Open"),
    ADD_NEW("ADD_NEW", "Status Add New"),
    EDIT_FORM("EDIT_FORM", "Status Edit Form"),
    ADD_ITEMS("ADD_ITEMS", "Status Add Items"),
    EDIT_ITEMS("EDIT_ITEMS", "Status Edit Items"),
    EDIT_PRICE("EDIT_PRICE", "Status Edit Item Prices"),
    
    OTHER("OTHER", "Other Reserved");
    
    private String stringCode;
    private String description;
    
    private EnumStatusOperasiForm(String strCode, String description){
        this.stringCode = strCode;
        this.description = description;    
    }
    public String getStrCode(){
        return stringCode;
    }
    public String getDescription(){
        return description;
    }

}
