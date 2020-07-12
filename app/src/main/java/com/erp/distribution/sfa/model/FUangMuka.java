package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType;

import java.io.Serializable;
import java.util.Date;

 
@Entity(tableName="fUangMuka")
public class FUangMuka implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. xxxx
	 */
	private Integer sourceID =0;
	
	private String noRek ="";

	private Date trDate =new Date();
	
	/*
	 * Ikut Cash Bank: Deposit or Payment
	 */
	private EnumAccTransactionType accTransactionType;
	
	private Double amountRp =0.0;
	private Double amountUsed =0.0;

	/*
	 * Pencairan Giro
	 * Tanggal Cair or Tolak Adalah Tanggal Transaksi Jurnal Akuntansi
	 */
//	@Enumerated(EnumType.ORDINAL)
//	@Column(name="STATUS_GIRO")
//	private EnumStatusGiro statusGiro;
	
//	@Column(name="SHARED_TO_COMPANY")
//	private boolean sharedToCompany=false; //setting Shared to company khusus ditaruh disini, bukan di divisi

	private Double cashAmountPay=0.0;

	private Double giroAmountPay=0.0;
	private Double transferAmountPay=0.0;
	
//	@ManyToOne
//	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
//	private FGiro ftransferBean;
	private Integer ftransferBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
//	private FGiro fgiroBean;
	private Integer fgiroBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private boolean sharedToCompany=false; //setting Shared to company khusus ditaruh disini, bukan di divisi

	//Jika uang muka penjualan
//	@ManyToOne
//	@JoinColumn(name="fcustomerBean", referencedColumnName="ID")
//	private FCustomer fcustomerBean;
	private Integer fcustomerBean = 0;

	//Jika uang muka pembelian
//	@ManyToOne
//	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
//	private FVendor fvendorBean;
	private Integer fvendorBean = 0;

	/*
	 * Account Mapping: Bank Account
	 * Accaount untuk Pencairan Pada Piutang Giro Misalnya
	 */
//	@ManyToOne
//	@JoinColumn(name="accAccountDebitBean", referencedColumnName="ID", nullable=true)
//	private AccAccount accAccountDebitBean;
	private Integer accAccountDebitBean = 0;
//	@ManyToOne
//	@JoinColumn(name="accAccountCreditBean", referencedColumnName="ID", nullable=true)
//	AccAccount accAccountCreditBean;
	private Integer accAccountCreditBean = 0;

	private String notes="";
	
	private boolean statusActive=true;

	@Ignore
	private String tempString="";

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

	public String getNoRek() {
		return noRek;
	}

	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}

	public Date getTrDate() {
		return trDate;
	}

	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}

	public EnumAccTransactionType getAccTransactionType() {
		return accTransactionType;
	}

	public void setAccTransactionType(EnumAccTransactionType accTransactionType) {
		this.accTransactionType = accTransactionType;
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

	public Double getCashAmountPay() {
		return cashAmountPay;
	}

	public void setCashAmountPay(Double cashAmountPay) {
		this.cashAmountPay = cashAmountPay;
	}

	public Double getGiroAmountPay() {
		return giroAmountPay;
	}

	public void setGiroAmountPay(Double giroAmountPay) {
		this.giroAmountPay = giroAmountPay;
	}

	public Double getTransferAmountPay() {
		return transferAmountPay;
	}

	public void setTransferAmountPay(Double transferAmountPay) {
		this.transferAmountPay = transferAmountPay;
	}

	public Integer getFtransferBean() {
		return ftransferBean;
	}

	public void setFtransferBean(Integer ftransferBean) {
		this.ftransferBean = ftransferBean;
	}

	public Integer getFgiroBean() {
		return fgiroBean;
	}

	public void setFgiroBean(Integer fgiroBean) {
		this.fgiroBean = fgiroBean;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public boolean isSharedToCompany() {
		return sharedToCompany;
	}

	public void setSharedToCompany(boolean sharedToCompany) {
		this.sharedToCompany = sharedToCompany;
	}

	public Integer getFcustomerBean() {
		return fcustomerBean;
	}

	public void setFcustomerBean(Integer fcustomerBean) {
		this.fcustomerBean = fcustomerBean;
	}

	public Integer getFvendorBean() {
		return fvendorBean;
	}

	public void setFvendorBean(Integer fvendorBean) {
		this.fvendorBean = fvendorBean;
	}

	public Integer getAccAccountDebitBean() {
		return accAccountDebitBean;
	}

	public void setAccAccountDebitBean(Integer accAccountDebitBean) {
		this.accAccountDebitBean = accAccountDebitBean;
	}

	public Integer getAccAccountCreditBean() {
		return accAccountCreditBean;
	}

	public void setAccAccountCreditBean(Integer accAccountCreditBean) {
		this.accAccountCreditBean = accAccountCreditBean;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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