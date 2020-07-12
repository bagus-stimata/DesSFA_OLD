package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName="ftSalesdPromoTprb")
public class FtSalesdPromoTprb implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	
	private Integer noUrut;

	
//	@ManyToOne
//	@JoinColumn(name="ftSalesdFreegood", referencedColumnName="ID")
//	private FtSalesdItems ftSalesdFreegood;	//Free Goodnya, bukan itemDetil yang mendapat
	private long ftSalesdFreegood = 0;

	
//	@ManyToOne
//	@JoinColumn(name="fpromoBean", referencedColumnName="ID")
//	private FPromotionRulesh fpromoBean;
	private Integer fpromoBean = 0;

	
//	@ManyToOne
//	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
//	private FMaterial fmaterialBean;
	private Integer fmaterialBean = 0;

//	@Column(name="PPRICE")
//	private Double pprice =0;
//	
//	@Column(name="SPRICE")
//	private Double sprice =0;
	
	private Double pricePcs_DetilItem =0.0; 	//Sebelum PPN
	/*
	 * PRICE in PCS: akan dipakai sebagai dasar perhitungan pada laporan aktifitas promosi
	 * dan Dipakai untuk Perhitungan Piutang pada Principle pada Journal:
	 */
	private Double pricePcsRpt =0.0; 			//Sebelum PPN
	
	
//	@Column(name="TPRB_QTY", length=8)
//	private Integer tprbQty;
	private Double tprbQty=0.0;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public long getFtSalesdFreegood() {
		return ftSalesdFreegood;
	}

	public void setFtSalesdFreegood(long ftSalesdFreegood) {
		this.ftSalesdFreegood = ftSalesdFreegood;
	}

	public Integer getFpromoBean() {
		return fpromoBean;
	}

	public void setFpromoBean(Integer fpromoBean) {
		this.fpromoBean = fpromoBean;
	}

	public Integer getFmaterialBean() {
		return fmaterialBean;
	}

	public void setFmaterialBean(Integer fmaterialBean) {
		this.fmaterialBean = fmaterialBean;
	}

	public Double getPricePcs_DetilItem() {
		return pricePcs_DetilItem;
	}

	public void setPricePcs_DetilItem(Double pricePcs_DetilItem) {
		this.pricePcs_DetilItem = pricePcs_DetilItem;
	}

	public Double getPricePcsRpt() {
		return pricePcsRpt;
	}

	public void setPricePcsRpt(Double pricePcsRpt) {
		this.pricePcsRpt = pricePcsRpt;
	}

	public Double getTprbQty() {
		return tprbQty;
	}

	public void setTprbQty(Double tprbQty) {
		this.tprbQty = tprbQty;
	}
}