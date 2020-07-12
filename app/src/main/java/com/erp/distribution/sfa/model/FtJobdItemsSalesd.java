package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName="ftjobd_items_salesd")
public class FtJobdItemsSalesd implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut=0;
	
	private Double qty=0.0;

	private Integer priority=0;

	
	//Untuk Job Schedule
//	@JoinColumn(name="ftJobItemsBean", referencedColumnName="ID", nullable = false)
	private FtJobdItems ftJobItemsBean;

	
//	@ManyToOne
//	@JoinColumn(name="ftSalesdBean", referencedColumnName="ID", nullable = false)
	private FtSalesdItems ftSalesdBean;
	
//	@ManyToOne
//	@JoinColumn(name="fmaterialBean", referencedColumnName="ID", nullable = false)
	private FMaterial fmaterialBean;
	




}