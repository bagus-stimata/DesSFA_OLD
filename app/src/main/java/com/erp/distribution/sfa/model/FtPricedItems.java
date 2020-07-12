package com.erp.distribution.sfa.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="ftPricedItems")
public class FtPricedItems {

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut=0;
	
	private Double pprice =0.0;
	private Double ppriceAfterPpn =0.0;
	
	private Double oldPprice =0.0;
	
	private Double sprice =0.0;
	private Double spriceAfterPpn =0.0;
	
	private Double oldSprice =0.0;
	private Double oldSpriceAfterPpn =0.0;

	private Double pprice2 =0.0;
	private Double pprice2AfterPpn =0.0;
	
	private Double oldPprice2 =0.0;
	
	private Double sprice2 =0.0;
	private Double sprice2AfterPpn;
	
	private Double oldSprice2 =0.0;
	private Double oldSprice2AfterPpn =0.0;
	
//	@ManyToOne
//	@JoinColumn(name="ftPricehBean", referencedColumnName="refno", insertable=true, updatable=true)
//	private FtPriceh  ftPricehBean;
	private Integer ftPricehBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fmaterialBean", referencedColumnName="ID", insertable=true, updatable=true)
//	private FMaterial fmaterialBean;
	private Integer fmaterialBean = 0;


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

	public Double getOldPprice() {
		return oldPprice;
	}

	public void setOldPprice(Double oldPprice) {
		this.oldPprice = oldPprice;
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

	public Double getOldSprice() {
		return oldSprice;
	}

	public void setOldSprice(Double oldSprice) {
		this.oldSprice = oldSprice;
	}

	public Double getOldSpriceAfterPpn() {
		return oldSpriceAfterPpn;
	}

	public void setOldSpriceAfterPpn(Double oldSpriceAfterPpn) {
		this.oldSpriceAfterPpn = oldSpriceAfterPpn;
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

	public Double getOldPprice2() {
		return oldPprice2;
	}

	public void setOldPprice2(Double oldPprice2) {
		this.oldPprice2 = oldPprice2;
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

	public Double getOldSprice2() {
		return oldSprice2;
	}

	public void setOldSprice2(Double oldSprice2) {
		this.oldSprice2 = oldSprice2;
	}

	public Double getOldSprice2AfterPpn() {
		return oldSprice2AfterPpn;
	}

	public void setOldSprice2AfterPpn(Double oldSprice2AfterPpn) {
		this.oldSprice2AfterPpn = oldSprice2AfterPpn;
	}

	public Integer getFtPricehBean() {
		return ftPricehBean;
	}

	public void setFtPricehBean(Integer ftPricehBean) {
		this.ftPricehBean = ftPricehBean;
	}

	public Integer getFmaterialBean() {
		return fmaterialBean;
	}

	public void setFmaterialBean(Integer fmaterialBean) {
		this.fmaterialBean = fmaterialBean;
	}
}
