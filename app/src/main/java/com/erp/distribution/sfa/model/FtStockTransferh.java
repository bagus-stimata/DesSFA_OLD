package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumTipeStockTransfer;

import java.util.*;


 
@Entity(tableName="ftStockTransferh")
public class FtStockTransferh {

	@PrimaryKey(autoGenerate = true)
	private long refno =0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;

	private String noRek = "";

	private Date trDate = new Date();

	/*
	 * PENGGUNAANYA MENGGUNAKAN PARAMETER SISTEM
	 * JIKA TIPE 0 = MAKA DEFAULT DAN TIDAK MENGGUNAKAN JURNAL DAN TIDAK MODE DITERIMA ATAU TIDAK
	 * JIKA TIPE 1= Maka terdapat jurnal
	 * 		a. Saat Kirim (yaitu saat nomor gudang diterbitkan)
	 * 		b. Saat Diterima 
	 * 		User penerima hanya bisa memberi tanda diterima dan memasukkan Qty Kembali (Kekurangan jika barang tidak ada atau rusak)
	 */
//	@Column(name="RECEIVED")
//	private boolean received =true; //Default adalah tidak memakai jurnal sehingga true
	private boolean receiptByDest = false;
	
	private Date goodReceiptDate=new Date();
	
	private EnumTipeStockTransfer tipeStockTransfer;
	
	private String notes="";

	/*
	 * Jumlah keseluruhan
	 */
	private Double amountRp=0.0;
	
	private boolean endOfDay = false;
	private Integer printCounter = 0;
	
	@Ignore
	private Integer tempInt=0;


//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fwarehouseBeanFrom", referencedColumnName="ID", nullable=false)
//	private FWarehouse fwarehouseBeanFrom;
	private Integer fwarehouseBeanFrom = 0;

//	@ManyToOne
//	@JoinColumn(name="fwarehouseBeanTo", referencedColumnName="ID", nullable=false)
//	private FWarehouse fwarehouseBeanTo;
	private Integer fwarehouseBeanTo = 0;


//	@ManyToOne
//	@JoinColumn(name="ftPurchaseh_ReqBean", referencedColumnName="refno", nullable=true)
//	private FtPurchaseh ftPurchaseh_ReqBean;
	private Integer ftPurchaseh_ReqBean = 0;

	
	 
	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID




	public long getRefno() {
		return refno;
	}

	public void setRefno(long refno) {
		this.refno = refno;
	}

	public long getSourceID() {
		return sourceID;
	}

	public void setSourceID(long sourceID) {
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

	public boolean isReceiptByDest() {
		return receiptByDest;
	}

	public void setReceiptByDest(boolean receiptByDest) {
		this.receiptByDest = receiptByDest;
	}

	public Date getGoodReceiptDate() {
		return goodReceiptDate;
	}

	public void setGoodReceiptDate(Date goodReceiptDate) {
		this.goodReceiptDate = goodReceiptDate;
	}

	public EnumTipeStockTransfer getTipeStockTransfer() {
		return tipeStockTransfer;
	}

	public void setTipeStockTransfer(EnumTipeStockTransfer tipeStockTransfer) {
		this.tipeStockTransfer = tipeStockTransfer;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getAmountRp() {
		return amountRp;
	}

	public void setAmountRp(Double amountRp) {
		this.amountRp = amountRp;
	}

	public boolean isEndOfDay() {
		return endOfDay;
	}

	public void setEndOfDay(boolean endOfDay) {
		this.endOfDay = endOfDay;
	}

	public Integer getPrintCounter() {
		return printCounter;
	}

	public void setPrintCounter(Integer printCounter) {
		this.printCounter = printCounter;
	}

	public Integer getTempInt() {
		return tempInt;
	}

	public void setTempInt(Integer tempInt) {
		this.tempInt = tempInt;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getFwarehouseBeanFrom() {
		return fwarehouseBeanFrom;
	}

	public void setFwarehouseBeanFrom(Integer fwarehouseBeanFrom) {
		this.fwarehouseBeanFrom = fwarehouseBeanFrom;
	}

	public Integer getFwarehouseBeanTo() {
		return fwarehouseBeanTo;
	}

	public void setFwarehouseBeanTo(Integer fwarehouseBeanTo) {
		this.fwarehouseBeanTo = fwarehouseBeanTo;
	}

	public Integer getFtPurchaseh_ReqBean() {
		return ftPurchaseh_ReqBean;
	}

	public void setFtPurchaseh_ReqBean(Integer ftPurchaseh_ReqBean) {
		this.ftPurchaseh_ReqBean = ftPurchaseh_ReqBean;
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