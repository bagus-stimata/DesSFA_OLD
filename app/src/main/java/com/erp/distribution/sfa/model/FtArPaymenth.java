package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.*;


@Entity(tableName="ftArPaymenth")
public class FtArPaymenth implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long refno=0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;

	private String noRek="";
	
	private Date trDate = new Date();
	private Date entryDate = new Date();
	
	private String notes="";
	
	private Integer printCounter=0;
	
	//PENGGUNAAN UTAMA PADA END_OF_DAY
	private boolean endOfDay=false;
	
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;


	/*
	 * Dipakai untuk Settlemen dengan AR vs Kasir
	 */
//	@ManyToOne
//	@JoinColumn(name="payeeBean", referencedColumnName="ID", nullable=true)
//	private FSalesman payeeBean;
	private Integer payeeBean = 0;


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

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getPrintCounter() {
		return printCounter;
	}

	public void setPrintCounter(Integer printCounter) {
		this.printCounter = printCounter;
	}

	public boolean isEndOfDay() {
		return endOfDay;
	}

	public void setEndOfDay(boolean endOfDay) {
		this.endOfDay = endOfDay;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getPayeeBean() {
		return payeeBean;
	}

	public void setPayeeBean(Integer payeeBean) {
		this.payeeBean = payeeBean;
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