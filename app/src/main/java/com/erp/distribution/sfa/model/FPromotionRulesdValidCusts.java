package com.erp.distribution.sfa.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="fPromotionRulesdValidCusts")
public class FPromotionRulesdValidCusts implements Serializable{

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
//	@JoinColumn(name="validCustomerGroupBean", referencedColumnName="ID")
//    private FCustomerGroup validCustomerGroupBean;
	private Integer validCustomerGroupBean = 0;

//	@ManyToOne
//	@JoinColumn(name="validDistributionChannelBean", referencedColumnName="ID")
//    private FDistributionChannel validDistributionChannelBean;
	private Integer validDistributionChannelBean = 0;

//	@ManyToOne
//	@JoinColumn(name="validAreaBean", referencedColumnName="ID")
//    private FArea validAreaBean;
	private Integer validAreaBean = 0;

//	@ManyToOne
//	@JoinColumn(name="validCustomerBean", referencedColumnName="ID")
//    private FCustomer validCustomerBean;
	private Integer validCustomerBean = 0;



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

	public Integer getValidCustomerGroupBean() {
		return validCustomerGroupBean;
	}

	public void setValidCustomerGroupBean(Integer validCustomerGroupBean) {
		this.validCustomerGroupBean = validCustomerGroupBean;
	}

	public Integer getValidDistributionChannelBean() {
		return validDistributionChannelBean;
	}

	public void setValidDistributionChannelBean(Integer validDistributionChannelBean) {
		this.validDistributionChannelBean = validDistributionChannelBean;
	}

	public Integer getValidAreaBean() {
		return validAreaBean;
	}

	public void setValidAreaBean(Integer validAreaBean) {
		this.validAreaBean = validAreaBean;
	}

	public Integer getValidCustomerBean() {
		return validCustomerBean;
	}

	public void setValidCustomerBean(Integer validCustomerBean) {
		this.validCustomerBean = validCustomerBean;
	}
}
