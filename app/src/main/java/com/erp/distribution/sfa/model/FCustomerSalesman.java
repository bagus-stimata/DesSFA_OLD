package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName="fCustomerSalesman")
public class FCustomerSalesman implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@ManyToOne
//	@JoinColumn(name="fcustomerBean")
//	private FCustomer fcustomerBean;
	private Integer fcustomerBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fsalesmanBean")
//	private FSalesman fsalesmanBean;
	private Integer fsalesmanBean = 0;


	@PrimaryKey(autoGenerate = true)
	private Integer id = 0;
	

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;
	
	
	private Integer noUrut = 0;
    
	private Integer harikunjungan =0;
	private Integer pekankunjungan =0;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getFcustomerBean() {
		return fcustomerBean;
	}

	public void setFcustomerBean(Integer fcustomerBean) {
		this.fcustomerBean = fcustomerBean;
	}

	public Integer getFsalesmanBean() {
		return fsalesmanBean;
	}

	public void setFsalesmanBean(Integer fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
	}

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

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public Integer getHarikunjungan() {
		return harikunjungan;
	}

	public void setHarikunjungan(Integer harikunjungan) {
		this.harikunjungan = harikunjungan;
	}

	public Integer getPekankunjungan() {
		return pekankunjungan;
	}

	public void setPekankunjungan(Integer pekankunjungan) {
		this.pekankunjungan = pekankunjungan;
	}
}