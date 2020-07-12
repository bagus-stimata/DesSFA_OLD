package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

 
@Entity(tableName="accjournald_items")
public class AccJournaldItems implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
	private long id=0;

	private Integer noUrut=0;
	
	private String description = "";

	
	private Double amountDebit =0.0;
	private Double amountCredit =0.0;
	
//	@ManyToOne
//	@JoinColumn(name="accJournalhBean", referencedColumnName="refno")
//    private AccJournalh accJournalhBean;
	private Integer accJournalhBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
//    private AccAccount accAccountBean;
	private Integer accAccountBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID")
//    private AccCostCenter accCostCenterBean;
	private Integer accCostCenterBean = 0;



	
	
}
