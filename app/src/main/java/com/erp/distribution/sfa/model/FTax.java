package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.*;


 
@Entity(tableName="fTax")
public class FTax  implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private Integer id =0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;
	
	
	private String kode1 ="";
	private String kode2 ="";
	
	private String description ="";

	private Double taxPercent = 0.0;
	
//	@ManyToOne
//	@JoinColumn(name="accAccountTaxPurchaseBean", referencedColumnName="ID")
//	private AccAccount accAccountTaxPurchaseBean;
	private Integer accAccountTaxPurchaseBean = 0;
//	@ManyToOne
//	@JoinColumn(name="accAccountTaxSalesBean", referencedColumnName="ID")
//	private AccAccount accAccountTaxSalesBean;
	private Integer accAccountTaxSalesBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private boolean statusActive=true;
	
	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSourceID() {
		return sourceID;
	}

	public void setSourceID(Integer sourceID) {
		this.sourceID = sourceID;
	}

	public String getKode1() {
		return kode1;
	}

	public void setKode1(String kode1) {
		this.kode1 = kode1;
	}

	public String getKode2() {
		return kode2;
	}

	public void setKode2(String kode2) {
		this.kode2 = kode2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public Integer getAccAccountTaxPurchaseBean() {
		return accAccountTaxPurchaseBean;
	}

	public void setAccAccountTaxPurchaseBean(Integer accAccountTaxPurchaseBean) {
		this.accAccountTaxPurchaseBean = accAccountTaxPurchaseBean;
	}

	public Integer getAccAccountTaxSalesBean() {
		return accAccountTaxSalesBean;
	}

	public void setAccAccountTaxSalesBean(Integer accAccountTaxSalesBean) {
		this.accAccountTaxSalesBean = accAccountTaxSalesBean;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}