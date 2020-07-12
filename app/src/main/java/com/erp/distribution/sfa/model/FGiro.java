package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType;
import com.erp.distribution.sfa.model.modelenum.EnumStatusGiro;

import java.io.Serializable;


import java.util.Date;


/**
 * The persistent class for the BUKUGIRO database table.
 * 
 */
 
 
@Entity(tableName="fGiro")
public class FGiro implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private long id=0;


	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;
	
	private String giroNumber ="";
	
	private String bankName = "";
	
	private String pemilikRek ="";
	
	/*
	 * Ikut Cash Bank: Deposit or Payment
	 */
	private EnumAccTransactionType accTransactionType;
	
	private Date giroDate =new Date();

	private Date giroDueDate = new Date();

	private Double amountRp =0.0;
	private Double amountUsed =0.0;

	/*
	 * Pencairan Giro
	 * Tanggal Cair or Tolak Adalah Tanggal Transaksi Jurnal Akuntansi
	 */
	private EnumStatusGiro statusGiro;
	
	private Date cairOrTolakDate = new Date();
	
	private boolean sharedToCompany=false; //setting Shared to company khusus ditaruh disini, bukan di divisi
	
	/*
	 * Account Mapping: Bank Account
	 * Accaount untuk Pencairan Pada Piutang Giro Misalnya
	 */
//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID", nullable=true)
//	private AccAccount accAccountBean;
	private Integer accAccountBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accAccountCairOrTolak", referencedColumnName="ID", nullable=true)
//	AccAccount accAccountCairOrTolak;
	private Integer accAccountCairOrTolak = 0;

	
	/*
	 * true	= Giro
	 * false =  Transfer
	 */
	private boolean giroOrTransfer = true;
	

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;


	private boolean statusActive=true;

    @Ignore
	private String tempString="";

	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSourceID() {
		return sourceID;
	}

	public void setSourceID(long sourceID) {
		this.sourceID = sourceID;
	}

	public String getGiroNumber() {
		return giroNumber;
	}

	public void setGiroNumber(String giroNumber) {
		this.giroNumber = giroNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPemilikRek() {
		return pemilikRek;
	}

	public void setPemilikRek(String pemilikRek) {
		this.pemilikRek = pemilikRek;
	}

	public EnumAccTransactionType getAccTransactionType() {
		return accTransactionType;
	}

	public void setAccTransactionType(EnumAccTransactionType accTransactionType) {
		this.accTransactionType = accTransactionType;
	}

	public Date getGiroDate() {
		return giroDate;
	}

	public void setGiroDate(Date giroDate) {
		this.giroDate = giroDate;
	}

	public Date getGiroDueDate() {
		return giroDueDate;
	}

	public void setGiroDueDate(Date giroDueDate) {
		this.giroDueDate = giroDueDate;
	}

	public Double getAmountRp() {
		return amountRp;
	}

	public void setAmountRp(Double amountRp) {
		this.amountRp = amountRp;
	}

	public Double getAmountUsed() {
		return amountUsed;
	}

	public void setAmountUsed(Double amountUsed) {
		this.amountUsed = amountUsed;
	}

	public EnumStatusGiro getStatusGiro() {
		return statusGiro;
	}

	public void setStatusGiro(EnumStatusGiro statusGiro) {
		this.statusGiro = statusGiro;
	}

	public Date getCairOrTolakDate() {
		return cairOrTolakDate;
	}

	public void setCairOrTolakDate(Date cairOrTolakDate) {
		this.cairOrTolakDate = cairOrTolakDate;
	}

	public boolean isSharedToCompany() {
		return sharedToCompany;
	}

	public void setSharedToCompany(boolean sharedToCompany) {
		this.sharedToCompany = sharedToCompany;
	}

	public Integer getAccAccountBean() {
		return accAccountBean;
	}

	public void setAccAccountBean(Integer accAccountBean) {
		this.accAccountBean = accAccountBean;
	}

	public Integer getAccAccountCairOrTolak() {
		return accAccountCairOrTolak;
	}

	public void setAccAccountCairOrTolak(Integer accAccountCairOrTolak) {
		this.accAccountCairOrTolak = accAccountCairOrTolak;
	}

	public boolean isGiroOrTransfer() {
		return giroOrTransfer;
	}

	public void setGiroOrTransfer(boolean giroOrTransfer) {
		this.giroOrTransfer = giroOrTransfer;
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

	public String getTempString() {
		return tempString;
	}

	public void setTempString(String tempString) {
		this.tempString = tempString;
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