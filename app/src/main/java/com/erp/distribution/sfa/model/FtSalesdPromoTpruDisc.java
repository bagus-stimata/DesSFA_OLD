package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="ftSalesdPromoTpruDisc")
public class FtSalesdPromoTpruDisc implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut=0;
	
//	@ManyToOne
//	@JoinColumn(name="ftSalesdBean", referencedColumnName="ID")
//	private FtSalesdItems ftSalesdBean;
	private long ftSalesdBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fPromoBean", referencedColumnName="ID")
//	private FPromotionRulesh fPromoBean;
	private Integer fPromoBean = 0;

	/*
	 * PRICE in PCS: akan dipakai sebagai dasar perhitungan pada laporan aktifitas promosi
	 * dan Dipakai untuk Perhitungan Piutang pada Principle pada Journal:
	 */
	private Double pricePcsRpt =0.0;
	
	private Double disc1;

	/*
	 * KHUSUS PEMBERIAN BERUPA VALUE: 
	 */
	private Double disc1Value_Uom1=0.0;
	//==============
	
	private Double disc2=0.0;
	
	private Double disc3=0.0;

	private Double disc1Plus=0.0;

	private Double disc2Plus=0.0;


	public Long getFtSalesdBean() {
		return ftSalesdBean;
	}

	public void setFtSalesdBean(Long ftSalesdBean) {
		this.ftSalesdBean = ftSalesdBean;
	}

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

	public Integer getfPromoBean() {
		return fPromoBean;
	}

	public void setfPromoBean(Integer fPromoBean) {
		this.fPromoBean = fPromoBean;
	}

	public Double getPricePcsRpt() {
		return pricePcsRpt;
	}

	public void setPricePcsRpt(Double pricePcsRpt) {
		this.pricePcsRpt = pricePcsRpt;
	}

	public Double getDisc1() {
		return disc1;
	}

	public void setDisc1(Double disc1) {
		this.disc1 = disc1;
	}

	public Double getDisc1Value_Uom1() {
		return disc1Value_Uom1;
	}

	public void setDisc1Value_Uom1(Double disc1Value_Uom1) {
		this.disc1Value_Uom1 = disc1Value_Uom1;
	}

	public Double getDisc2() {
		return disc2;
	}

	public void setDisc2(Double disc2) {
		this.disc2 = disc2;
	}

	public Double getDisc3() {
		return disc3;
	}

	public void setDisc3(Double disc3) {
		this.disc3 = disc3;
	}

	public Double getDisc1Plus() {
		return disc1Plus;
	}

	public void setDisc1Plus(Double disc1Plus) {
		this.disc1Plus = disc1Plus;
	}

	public Double getDisc2Plus() {
		return disc2Plus;
	}

	public void setDisc2Plus(Double disc2Plus) {
		this.disc2Plus = disc2Plus;
	}
}