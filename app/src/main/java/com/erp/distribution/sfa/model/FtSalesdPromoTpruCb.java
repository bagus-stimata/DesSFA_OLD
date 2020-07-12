package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName="ftSalesdPromoTpruCb")
public class FtSalesdPromoTpruCb implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long refnoPromo =0;
	
	
	private Integer noUrut=0;
	
	
//	@ManyToOne
//	@JoinColumns({@JoinColumn(name="ftSalesdRefno", referencedColumnName="refno"),
//		@JoinColumn(name="ftSalesdId", referencedColumnName="ID"),
//		@JoinColumn(name="ftSalesdFreegood", referencedColumnName="freeGood")})
//	@ManyToOne
//	@JoinColumn(name="ftSalesdFreegood", referencedColumnName="ID")
//	private FtSalesdItems ftSalesdBean;
	private long ftSalesdFreegood = 0;


//	@ManyToOne
//	@JoinColumn(name="fPromoBean", referencedColumnName="ID")
//	private FPromotionRulesh fPromoBean;
	private Integer fPromoBean = 0;

	private Double tpruCashback=0.0;



}