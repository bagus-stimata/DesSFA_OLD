package com.erp.distribution.sfa.model.modelenum;

public enum EnumMonth {
    JAN(0, "JAN", "Januari", "January"),
    FEB(1,"FEB",  "Februari", "February"),
    MAR(2, "MAR", "Maret", "March"),
    APRL(3,"APRL",  "April", "April"),
    MEI(4, "MEI", "Mei", "May"),
    JUN(5,"JUN",  "Juni", "June"),
    JUL(6, "JUL", "Juli", "July"),
    AGT(7,"AGT",  "Agustus", "August"),
    SEP(8, "SEP", "September", "September"),
    OKT(9, "OKT", "Oktober", "October"),
    NOP(10,"NOP",  "Nopember", "November"),
    DES(11, "DES", "Desember", "December")
    ;
    
    private int intId;
	private String strCode;
    private String descIndonesia;
    private String descEnglish;
    
    private EnumMonth(int intId, String strCode, String descIndonesia, String descEnglish){
        this.intId = intId;
        this.descIndonesia = descIndonesia;    
        this.descEnglish = descEnglish;    
    }

    public static EnumMonth getMonth(int month_Int) {
    		EnumMonth enumMonthSelected = null;
    		switch (month_Int) {
			case 0:
				enumMonthSelected = EnumMonth.JAN;
				break;
			case 1:
				enumMonthSelected = EnumMonth.FEB;
				break;
			case 2:
				enumMonthSelected = EnumMonth.MAR;
				break;
			case 3:
				enumMonthSelected = EnumMonth.APRL;
				break;
			case 4:
				enumMonthSelected = EnumMonth.MEI;
				break;
			case 5:
				enumMonthSelected = EnumMonth.JUN;
				break;
			case 6:
				enumMonthSelected = EnumMonth.JUL;
				break;
			case 7:
				enumMonthSelected = EnumMonth.AGT;
				break;
			case 8:
				enumMonthSelected = EnumMonth.SEP;
				break;
			case 9:
				enumMonthSelected = EnumMonth.OKT;
				break;
			case 10:
				enumMonthSelected = EnumMonth.NOP;
				break;
			case 11:
				enumMonthSelected = EnumMonth.DES;
				break;

			default:
				break;
			}
    	
    		return enumMonthSelected;
    }
	
	public int getIntId() {
		return intId;
	}

	public String getStrCode() {
		return strCode;
	}

	public void setStrCode(String strCode) {
		this.strCode = strCode;
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}


	public String getDescIndonesia() {
		return descIndonesia;
	}


	public void setDescIndonesia(String descIndonesia) {
		this.descIndonesia = descIndonesia;
	}


	public String getDescEnglish() {
		return descEnglish;
	}


	public void setDescEnglish(String descEnglish) {
		this.descEnglish = descEnglish;
	}



    

}
