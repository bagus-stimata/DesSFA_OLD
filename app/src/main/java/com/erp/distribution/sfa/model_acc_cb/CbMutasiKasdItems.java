package com.erp.distribution.sfa.model_acc_cb;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

 
@Entity(tableName="cb_mutasikasd_items")
public class CbMutasiKasdItems implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
	private long id=0;

	private Integer noUrut=0;
	
	private String description = "";

	
//	@ManyToOne
//	@JoinColumn(name="cbMutasiKashBean", referencedColumnName="refno", nullable=false)
//    private CbMutasiKash cbMutasiKashBean;
	private Integer cbMutasiKashBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
//    private AccAccount accAccountBean;
	private Integer accAccountBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID")
//    private AccCostCenter accCostCenterBean;
	private Integer accCostCenterBean = 0;

	private Double amount =0.0;

	
	

	
}
