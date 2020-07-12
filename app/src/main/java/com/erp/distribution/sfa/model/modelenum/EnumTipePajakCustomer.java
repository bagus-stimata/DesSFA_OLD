package com.erp.distribution.sfa.model.modelenum;

public enum EnumTipePajakCustomer {
//    REG_01(0, "01", "Pihak yang bukan pemungut pajak"),
//    PB_02(1, "02", "Pemungut Bendaharawan"),
//    PSB_03(2, "03", "Pemungut Selain Bendaharawan"),
//    DPL_04(3, "04", "DPP Nilai Lain-lain"),
//    PL_05(4, "05", "Penyerahan Lainnya"),
//    PPTD_06(5, "06", "Peyerahan yang PPN-nya tidak Dipungut"),
//    PPBS_07(6, "07", "Penyerahan yang PPN-nya Dibebaskan"),
//    PA_08(7, "08", "Penyerahan Aktiva (Pasal 16D UU PPN)")
	REG_01(0, "REG_01", "Pihak yang bukan pemungut pajak"),
	PB_02(1, "PB_02", "Pemungut Bendaharawan"),
	PSB_03(2, "PSB_03", "Pemungut Selain Bendaharawan"),
	DPL_04(3, "DPL_04", "DPP Nilai Lain-lain"),
	PL_05(4, "PL_05", "Penyerahan Lainnya"),
	PPTD_06(5, "PPTD_06", "Peyerahan yang PPN-nya tidak Dipungut"),
	PPBS_07(6, "PPBS_07", "Penyerahan yang PPN-nya Dibebaskan"),
	PA_08(7, "PA_08", "Penyerahan Aktiva (Pasal 16D UU PPN)")
    ;
    
	/*
	 * Ditambah denga 1
	 */
    private int intId;
    private String stringId;
    private String description;
    
    private EnumTipePajakCustomer(int intId, String stringId, String description){
        this.intId = intId;
        this.stringId = stringId;    
        this.description = description;    
    }

	
	public String getStringId() {
		return stringId;
	}


	public void setStringId(String stringId) {
		this.stringId = stringId;
	}


	public int getIntId() {
		return intId;
	}



	public void setIntId(int intId) {
		this.intId = intId;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    

}
