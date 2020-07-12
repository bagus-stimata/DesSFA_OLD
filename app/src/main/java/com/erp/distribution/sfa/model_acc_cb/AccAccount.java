package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumAccCoaType;

import java.io.Serializable;
import java.util.Date;


 
@Entity(tableName="acc_account")
public class AccAccount implements Serializable {
	private static final long serialVersionUID = 1L;


	@PrimaryKey(autoGenerate = true)
	private Integer id=0;
	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private String kode1 ="";
	
	private String kode2 ="";

	private EnumAccCoaType coaType;
	private String titleGroup1 ="";
	private String titleGroup2 ="";
	
	private String description ="";
	
	//Boleh Dimasukin Saldo
	private boolean postEdit =true;

	/*
	 * Biasanya dipakai untuk ayat silang yang digunakan untuk semua divisi dalam company
	 */
	private boolean usedForAllDiv =false;
	
	private boolean statusActive =true;

	
	/*
	 * Jika Level =1 
	 * maka adalah Top Level Account
	 */
	private Integer accLevel = 2;


	/*
	 * Cicular Relation Ship
	 */
//	@Column(name="parentAccount", length=30)
//	@ManyToOne
//	@JoinColumn(name = "parentAccount", nullable=true)
//	private AccAccount accParent;
	private Integer parentAccount = 0;


	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID
	

}