package com.erp.distribution.sfa.model.modelenum;

public enum EnumMaterialType {
	DIEN("DIEN", "*Services"),
	ERSA("ERSA", "Spare Parts"),
	FERT("FERT", "Finished Products"),
	FGTR("FGTR", "Beverages"),
	FHMI("FHMI", "Production Resources/Tools"),
	FOOD("FOOD", "Foods Excluding Perishables"),
	FRIP("FRIP", "Perishables"),
	HALB("HALB", "Semi Finished Goods"),
	HAWA("HAWA", "Trading Goods"),
	HERS("HERS", "Manufacturer Parts"),
	HIBE("HIBE", "Operationg Suppliers"),
	IBAU("IBAU", "Maintenance Assemblies"),
	INTR("INTR", "Intra Materials"),
	KMAT("KMAT", "Configurable Materials"),
	LEER("LEER", "Industry Empties"),
	LGUT("LGUT", "Retial Empties"),
	MODE("MODE", "Apparel Seasonal"),
	NLAG("NLAG", "*Non-Stock Material"),
	NOF1("NOF1", "Non Foods"),
	PIPE("PIPE", "Pipeline Materials"),
	ROH("ROH", "Raw Materials"),
	UNBW("UNBW", "Non-Valuated Materials"),
	VERP("VERP", "Packaging Materials"),
	WETT("WETT", "Competitive Producs"),
    OTH1("OTH1", "Others1");
    
    private String stringId;
    private String description;
    
    private EnumMaterialType(String stringId, String description){
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
