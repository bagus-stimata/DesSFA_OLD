package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


 
@Entity(tableName="ftPurchasedExpenses")
public class FtPurchasedExpenses implements Serializable{


	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut=0;
	
	private Double amount=0.0;
	
	private String notes="";
	/*
	 * HPP Pasti Apply To Item untuk DES
	 * Jika ditaruh sebagai biaya maka pasti akan dihitungkan sebagai HPP
	 *  
	 */
//	@Column(name="APPLY_TO_ITEM")
//	private boolean applyToItem=false;
	
	/*
	 * Jika pay to other vendor=true, maka:
	 * - Akan menjadi Journal tersendiri:
	 * - Hutang vendor lain -> pada Biaya
	 * 
	 * Jika false
	 *  - akan ikut total pada nota tersebut
	 * 
	 */
	private boolean payToOtherVendor=false;

	
//	@ManyToOne
//	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
//	private FtPurchaseh ftPurchasehBean;
	private Integer ftPurchasehBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
//	private AccAccount accAccountBean;
	private Integer accAccountBean = 0;

	

	
}