package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumTipeJob;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName="ftJobh")
public class FtJobh implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long refno= 0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;
	/*
	 * TRANSAKSI BISA DIMULAI DARI PURCHASE INVOICE ATAU PO
	 */
	private String noDoc="";

	private Date docDate=new Date();

	private EnumTipeJob tipeJob = EnumTipeJob.JOB_1;

/*
 * MENGGUNAKAN ISTILAH PARAMETER AGAR LEBIH FLEXIBLE	
 */
	private Integer paramInt1=0;
	private Integer paramInt2=0;
	private Integer paramInt3=0;
	private Integer paramInt4=0;
	private Integer paramInt5=0;
	
	private Date paramDate1= new Date();

	private boolean endOfDay=false;
	
	private boolean proses=false;
	
	private String notes="";
	
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

	public String getNoDoc() {
		return noDoc;
	}

	public void setNoDoc(String noDoc) {
		this.noDoc = noDoc;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public EnumTipeJob getTipeJob() {
		return tipeJob;
	}

	public void setTipeJob(EnumTipeJob tipeJob) {
		this.tipeJob = tipeJob;
	}

	public Integer getParamInt1() {
		return paramInt1;
	}

	public void setParamInt1(Integer paramInt1) {
		this.paramInt1 = paramInt1;
	}

	public Integer getParamInt2() {
		return paramInt2;
	}

	public void setParamInt2(Integer paramInt2) {
		this.paramInt2 = paramInt2;
	}

	public Integer getParamInt3() {
		return paramInt3;
	}

	public void setParamInt3(Integer paramInt3) {
		this.paramInt3 = paramInt3;
	}

	public Integer getParamInt4() {
		return paramInt4;
	}

	public void setParamInt4(Integer paramInt4) {
		this.paramInt4 = paramInt4;
	}

	public Integer getParamInt5() {
		return paramInt5;
	}

	public void setParamInt5(Integer paramInt5) {
		this.paramInt5 = paramInt5;
	}

	public Date getParamDate1() {
		return paramDate1;
	}

	public void setParamDate1(Date paramDate1) {
		this.paramDate1 = paramDate1;
	}

	public boolean isEndOfDay() {
		return endOfDay;
	}

	public void setEndOfDay(boolean endOfDay) {
		this.endOfDay = endOfDay;
	}

	public boolean isProses() {
		return proses;
	}

	public void setProses(boolean proses) {
		this.proses = proses;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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