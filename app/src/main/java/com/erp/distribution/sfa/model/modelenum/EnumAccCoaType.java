package com.erp.distribution.sfa.model.modelenum;

public enum EnumAccCoaType {
    CASH_BA("CASH_BA", "Cash And Bank"),
    ACC_REC("ACC_REC", "Account Receivable"),
    FIX_ASS("FIX_ASS", "Fix Assets"),
    ACCU_DEP("ACCU_DEP", "Accumulated Depreciation"),
    ACC_PAY("ACC_PAY", "Account Payable"),
    REVENUE("REVENUE", "Revenue"), 			//LR
    COGS("COGS", "Cost of Goods Sold"),	 	//LR
    INVENTO("INVENTO", "Inventory"),
    EXPENSE("EXPENSE", "Expense"), 			//LR
    	EQUITY("EQUITY", "Equity/Modal"),
    OTH_INCO("OTH_INCO", "Other Income"), 			//LR
    OTH_CU_L("OTH_CU_L", "Other Current Liability"),
    OTH_CU_A("OTH_CU_S", "Other Current Asset"),
    OTH_EXPE("OTH_EXPE", "Other Expense"), 			//LR
    OTH_REVE("OTH_REVE", "Other Revenue"),
    DUMMY("DUMMY", "Dummy") //Dummy adalah akun yang tidak akan dibaca: Coa type harus dikeluarkan
    ; 			//LR
    
    private String stringId;
    private String description;
    
    private EnumAccCoaType(String stringId, String description){
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
