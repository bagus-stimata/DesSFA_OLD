package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import java.util.Date;


/**
 * The persistent class for the ACCOUNTGROUP database table.
 * 
 */
 
 
@Entity(tableName="acc_costcenter")
public class AccCostCenter implements Serializable {
	private static final long serialVersionUID = 1L;

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
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private boolean statusActive=true;
	
	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID




}