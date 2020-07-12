package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumTipeStockOpname;

import java.util.*;


 
@Entity(tableName="ftOpnameh")
public class FtOpnameh {

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
	
	private Date trDate= new Date();

	private EnumTipeStockOpname tipeStockOpname;
	
	/*
	 * Account 
	 */
//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
//    private AccAccount accAccountBean;
	private Integer accAccountBean = 0;

	private String notes="";


	private boolean posting = false;
	
	
	private boolean endOfDay= false;
	private Integer printCounter=0;
	
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
//	private FWarehouse fwarehouseBean;
	private Integer fwarehouseBean = 0;

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

	public EnumTipeStockOpname getTipeStockOpname() {
		return tipeStockOpname;
	}

	public void setTipeStockOpname(EnumTipeStockOpname tipeStockOpname) {
		this.tipeStockOpname = tipeStockOpname;
	}

	public Integer getAccAccountBean() {
		return accAccountBean;
	}

	public void setAccAccountBean(Integer accAccountBean) {
		this.accAccountBean = accAccountBean;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isPosting() {
		return posting;
	}

	public void setPosting(boolean posting) {
		this.posting = posting;
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

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getFwarehouseBean() {
		return fwarehouseBean;
	}

	public void setFwarehouseBean(Integer fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
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