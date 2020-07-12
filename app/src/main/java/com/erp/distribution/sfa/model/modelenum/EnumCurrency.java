package com.erp.distribution.sfa.model.modelenum;

public enum EnumCurrency {
    IDR("IDR", 360, "Rupiah", "Indonesia"),
    USD("USD", 840, "US Dollar", "USA"),
    EUR("EUR", 978, "Euro", "Europian"),
    JPY("JPY", 392, "Yen Japan", "Japan"),
    SGD("SGD", 702, "Singapore Dollar", "Singapore"),
    MYR("MYR", 458, "Malaysian Ringgit", "Malaysia"),
    THB("THB", 764, "Bath", "Thailand"),
    CNH("CNH", 156, "Yuan Renminbi", "China Republic"),
    HKD("HKD", 344, "Hongkong Dollar", "Hongkong"),
    TWD("TWD", 901, "New Taiwan Dollar", "Taiwan"),
    OTH("OTH", 0, "Others", "");
    
    private String stringId;
    private int numericCode;
    private String description;
    private String country;


    private EnumCurrency(String stringId, int numericCode, String description, String country){
        this.stringId = stringId;
        this.numericCode = numericCode;
        this.description = description;    
        this.country = country;    
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

	public int getNumericCode() {
		return numericCode;
	}

	public void setNumericCode(int numericCode) {
		this.numericCode = numericCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
    

}
