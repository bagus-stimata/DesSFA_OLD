package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


 
@Entity(tableName="acc_periodebuku")
public class AccPeriodeBuku implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	/*
	 * Komposite Key: Tidak boleh ada yang sama
	 */
	private Integer periodeYear=0;
	private Integer periodeMonth=0;
	/*
	 * Komposite Key: End
	 */
	
	private Date periodeDateFrom;
	private Date periodeDateTo;

	private boolean posting=false;
	
	private boolean statusActive=true;
	
	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID
	


}