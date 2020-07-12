package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName="fCompany")
public class FCompany {

	@PrimaryKey(autoGenerate = true)
	private Integer id =0;
	
	private String kode1 ="";
	private String kode2 ="";
	
	private String description ="";

	private boolean shareDataToBeClone=false;
	
	private String shareDataToBeCloneSecurityCode ="";
	
	private boolean statusActive=true;

	private boolean webServiceActive=false;
	
	
	/*
	 * SETTING YANG BERLAKU UNTUK SEMUA DIVISI
	 * JIKA KOSONG MAKA MENGGUNAKAN PRIORITAS ATASNYA
	 * 1. Parameter System
	 * 2. Corporation
	 * 3. Division 
	 */

	/*
	 * 
	 */
//	@Column(name="NOMOR_URUT_DOC_FOLLOW_APP") //SELALU MENGIKUTI NOMOR URUT COMPANY
//	private boolean nomorUrutDocTransFollowApp = true;
	
	/*
	 * FORMAT FAKTUR DAN ALAMAT
	 */
//	@Column(name="INVOICE_COMP_NAME_1", length=75)
//	private String invoiceCompanyName1 = "";
//	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
//	private String invoiceCompanyAddress1 = "";
//	@Column(name="INVOICE_COMP_CITY_1", length=30)
//	private String invoiceCompanyCity1 = "";
//	@Column(name="INVOICE_COMP_PHONE_1", length=25)
//	private String invoiceCompanyPhone1 = "";
	
/*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Customer, Mengikuti Corporation	
 */
//	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
//	private String invoiceCompanyNpwpPhone1 = "";
	
	
	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isShareDataToBeClone() {
		return shareDataToBeClone;
	}

	public void setShareDataToBeClone(boolean shareDataToBeClone) {
		this.shareDataToBeClone = shareDataToBeClone;
	}

	public String getShareDataToBeCloneSecurityCode() {
		return shareDataToBeCloneSecurityCode;
	}

	public void setShareDataToBeCloneSecurityCode(String shareDataToBeCloneSecurityCode) {
		this.shareDataToBeCloneSecurityCode = shareDataToBeCloneSecurityCode;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public boolean isWebServiceActive() {
		return webServiceActive;
	}

	public void setWebServiceActive(boolean webServiceActive) {
		this.webServiceActive = webServiceActive;
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