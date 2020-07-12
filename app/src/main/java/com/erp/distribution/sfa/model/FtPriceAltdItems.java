package com.erp.distribution.sfa.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="ftPricealtdItems")
public class FtPriceAltdItems {

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;
	
	private Integer noUrut=0;
	
	private Double pprice=0.0;
	private Double ppriceAfterPpn;
	private Double pprice2=0.0;
	private Double pprice2AfterPpn=0.0;
	
	private Double sprice=0.0;
	private Double spriceAfterPpn;
	private Double sprice2=0.0;
	private Double sprice2AfterPpn=0.0;
	
//	@ManyToOne
//	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID")
//	private FtPriceAlth  ftPriceAlthBean;
	private Integer ftPriceAlthBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
//	private FMaterial fmaterialBean;
	private Integer fmaterialBean = 0;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public Double getPprice() {
		return pprice;
	}

	public void setPprice(Double pprice) {
		this.pprice = pprice;
	}

	public Double getPpriceAfterPpn() {
		return ppriceAfterPpn;
	}

	public void setPpriceAfterPpn(Double ppriceAfterPpn) {
		this.ppriceAfterPpn = ppriceAfterPpn;
	}

	public Double getPprice2() {
		return pprice2;
	}

	public void setPprice2(Double pprice2) {
		this.pprice2 = pprice2;
	}

	public Double getPprice2AfterPpn() {
		return pprice2AfterPpn;
	}

	public void setPprice2AfterPpn(Double pprice2AfterPpn) {
		this.pprice2AfterPpn = pprice2AfterPpn;
	}

	public Double getSprice() {
		return sprice;
	}

	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}

	public Double getSpriceAfterPpn() {
		return spriceAfterPpn;
	}

	public void setSpriceAfterPpn(Double spriceAfterPpn) {
		this.spriceAfterPpn = spriceAfterPpn;
	}

	public Double getSprice2() {
		return sprice2;
	}

	public void setSprice2(Double sprice2) {
		this.sprice2 = sprice2;
	}

	public Double getSprice2AfterPpn() {
		return sprice2AfterPpn;
	}

	public void setSprice2AfterPpn(Double sprice2AfterPpn) {
		this.sprice2AfterPpn = sprice2AfterPpn;
	}

	public Integer getFtPriceAlthBean() {
		return ftPriceAlthBean;
	}

	public void setFtPriceAlthBean(Integer ftPriceAlthBean) {
		this.ftPriceAlthBean = ftPriceAlthBean;
	}

	public Integer getFmaterialBean() {
		return fmaterialBean;
	}

	public void setFmaterialBean(Integer fmaterialBean) {
		this.fmaterialBean = fmaterialBean;
	}
}
