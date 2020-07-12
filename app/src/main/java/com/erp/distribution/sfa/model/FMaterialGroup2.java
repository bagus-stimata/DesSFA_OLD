package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;



@Entity(tableName="fMaterialGroup2")
public class FMaterialGroup2 {

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
	
//	@ManyToOne
//	@JoinColumn(name="fmaterialGroup1Bean", referencedColumnName="ID")
//	private FMaterialGroup1 fmaterialGroup1Bean;
	private Integer fmaterialGroup1Bean = 0;

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

	public Integer getFmaterialGroup1Bean() {
		return fmaterialGroup1Bean;
	}

	public void setFmaterialGroup1Bean(Integer fmaterialGroup1Bean) {
		this.fmaterialGroup1Bean = fmaterialGroup1Bean;
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