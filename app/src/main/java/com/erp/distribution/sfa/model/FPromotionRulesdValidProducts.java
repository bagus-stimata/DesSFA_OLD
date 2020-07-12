package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName="fPromotionRulesdValidProducts")
public class FPromotionRulesdValidProducts implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;

	private Integer noUrut=0;
	
//	@ManyToOne
//	@JoinColumn(name="fpromotionRuleshBean", referencedColumnName="ID")
//    private FPromotionRulesh fpromotionRuleshBean;
	private Integer fpromotionRuleshBean = 0;

	/*
	 * Customer Classfication = null berarti semua/All
	 */
//	@ManyToOne
//	@JoinColumn(name="validVendorBean", referencedColumnName="ID")
//    private FVendor validVendorBean;
	private Integer validVendorBean = 0;
	private boolean validVendorAccumulation=false;
	
//	@ManyToOne
//	@JoinColumn(name="validMaterialSalesBrandBean", referencedColumnName="ID")
//    private FMaterialSalesBrand validMaterialSalesBrandBean;
	private Integer validMaterialSalesBrandBean = 0;
	private boolean validSalesBrandAccumulation=false;

//	@ManyToOne
//	@JoinColumn(name="validMaterialGroup2Bean", referencedColumnName="ID")
//    private FMaterialGroup2 validMaterialGroup2Bean;
	private Integer validMaterialGroup2Bean = 0;
	private boolean validMaterialGroup2Accumulation=false;
	
//	@ManyToOne
//	@JoinColumn(name="validMaterialGroup3Bean", referencedColumnName="ID")
//    private FMaterialGroup3 validMaterialGroup3Bean;
	private Integer validMaterialGroup3Bean = 0;
	private boolean validMaterialGroup3Accumulation=false;
	
//	@ManyToOne
//	@JoinColumn(name="validMaterialBean", referencedColumnName="ID")
//    private FMaterial validMaterialBean;
	private Integer validMaterialBean = 0;

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

	public Integer getFpromotionRuleshBean() {
		return fpromotionRuleshBean;
	}

	public void setFpromotionRuleshBean(Integer fpromotionRuleshBean) {
		this.fpromotionRuleshBean = fpromotionRuleshBean;
	}

	public Integer getValidVendorBean() {
		return validVendorBean;
	}

	public void setValidVendorBean(Integer validVendorBean) {
		this.validVendorBean = validVendorBean;
	}

	public boolean isValidVendorAccumulation() {
		return validVendorAccumulation;
	}

	public void setValidVendorAccumulation(boolean validVendorAccumulation) {
		this.validVendorAccumulation = validVendorAccumulation;
	}

	public Integer getValidMaterialSalesBrandBean() {
		return validMaterialSalesBrandBean;
	}

	public void setValidMaterialSalesBrandBean(Integer validMaterialSalesBrandBean) {
		this.validMaterialSalesBrandBean = validMaterialSalesBrandBean;
	}

	public boolean isValidSalesBrandAccumulation() {
		return validSalesBrandAccumulation;
	}

	public void setValidSalesBrandAccumulation(boolean validSalesBrandAccumulation) {
		this.validSalesBrandAccumulation = validSalesBrandAccumulation;
	}

	public Integer getValidMaterialGroup2Bean() {
		return validMaterialGroup2Bean;
	}

	public void setValidMaterialGroup2Bean(Integer validMaterialGroup2Bean) {
		this.validMaterialGroup2Bean = validMaterialGroup2Bean;
	}

	public boolean isValidMaterialGroup2Accumulation() {
		return validMaterialGroup2Accumulation;
	}

	public void setValidMaterialGroup2Accumulation(boolean validMaterialGroup2Accumulation) {
		this.validMaterialGroup2Accumulation = validMaterialGroup2Accumulation;
	}

	public Integer getValidMaterialGroup3Bean() {
		return validMaterialGroup3Bean;
	}

	public void setValidMaterialGroup3Bean(Integer validMaterialGroup3Bean) {
		this.validMaterialGroup3Bean = validMaterialGroup3Bean;
	}

	public boolean isValidMaterialGroup3Accumulation() {
		return validMaterialGroup3Accumulation;
	}

	public void setValidMaterialGroup3Accumulation(boolean validMaterialGroup3Accumulation) {
		this.validMaterialGroup3Accumulation = validMaterialGroup3Accumulation;
	}

	public Integer getValidMaterialBean() {
		return validMaterialBean;
	}

	public void setValidMaterialBean(Integer validMaterialBean) {
		this.validMaterialBean = validMaterialBean;
	}
}
