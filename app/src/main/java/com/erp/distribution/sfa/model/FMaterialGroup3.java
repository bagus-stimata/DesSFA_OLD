package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


 
@Entity(tableName="fMaterialGroup3")
public class FMaterialGroup3 {

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
	
	private Integer tempInt1 = 0;
	private Integer tempInt2 = 0;
	private Integer tempInt3 = 0;
	
//	@ManyToOne
//	@JoinColumn(name="fmaterialGroup2Bean", referencedColumnName="ID")
//	private FMaterialGroup2 fmaterialGroup2Bean;
	private Integer fmaterialGroup2Bean = 0;

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

	public Integer getTempInt1() {
		return tempInt1;
	}

	public void setTempInt1(Integer tempInt1) {
		this.tempInt1 = tempInt1;
	}

	public Integer getTempInt2() {
		return tempInt2;
	}

	public void setTempInt2(Integer tempInt2) {
		this.tempInt2 = tempInt2;
	}

	public Integer getTempInt3() {
		return tempInt3;
	}

	public void setTempInt3(Integer tempInt3) {
		this.tempInt3 = tempInt3;
	}

	public Integer getFmaterialGroup2Bean() {
		return fmaterialGroup2Bean;
	}

	public void setFmaterialGroup2Bean(Integer fmaterialGroup2Bean) {
		this.fmaterialGroup2Bean = fmaterialGroup2Bean;
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