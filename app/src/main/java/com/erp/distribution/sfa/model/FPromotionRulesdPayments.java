package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName="fPromotionRulesdPayments")
public class FPromotionRulesdPayments {

	/*
	 * HARUS DIRUBAH JADI LONG
	 */
	@PrimaryKey(autoGenerate = true)
	private Integer id=0;
	
//	@ManyToOne
//	@JoinColumn(name="fpromotionRuleshBean", referencedColumnName="ID")
//	private FPromotionRulesh fpromotionRuleshBean;
	private Integer fpromotionRuleshBean = 0;

	private Integer noUrut=0;

    private Date trDate = new Date();
	
	//Total Pembayaran:
	private Double amountPayRp=0.0;

	
	private Double potonganAmount=0.0;
	
	private Double transferAmountPay=0.0;
//	@ManyToOne
//	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
//	private FGiro ftransferBean;
	private Integer ftransferBean = 0;

	private Double giroAmountPay=0.0;
//	@ManyToOne
//	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
//	private FGiro fgiroBean;
	private Integer fgiroBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accAccountPotonganBean", referencedColumnName="ID", nullable=true)
//	private AccAccount accAccountPotonganBean;
	private Integer accAccountPotonganBean = 0;

	//Semua Giro dan Transfer masuk ke Giro
//	@ManyToOne
//	@JoinColumn(name="accAccountBankBean", referencedColumnName="ID")
//	private AccAccount accAccountBankBean; //Account ke Bank Mana Bayarnya


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFpromotionRuleshBean() {
		return fpromotionRuleshBean;
	}

	public void setFpromotionRuleshBean(Integer fpromotionRuleshBean) {
		this.fpromotionRuleshBean = fpromotionRuleshBean;
	}

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public Date getTrDate() {
		return trDate;
	}

	public void setTrDate(Date trDate) {
		this.trDate = trDate;
	}

	public Double getAmountPayRp() {
		return amountPayRp;
	}

	public void setAmountPayRp(Double amountPayRp) {
		this.amountPayRp = amountPayRp;
	}

	public Double getPotonganAmount() {
		return potonganAmount;
	}

	public void setPotonganAmount(Double potonganAmount) {
		this.potonganAmount = potonganAmount;
	}

	public Double getTransferAmountPay() {
		return transferAmountPay;
	}

	public void setTransferAmountPay(Double transferAmountPay) {
		this.transferAmountPay = transferAmountPay;
	}

	public Integer getFtransferBean() {
		return ftransferBean;
	}

	public void setFtransferBean(Integer ftransferBean) {
		this.ftransferBean = ftransferBean;
	}

	public Double getGiroAmountPay() {
		return giroAmountPay;
	}

	public void setGiroAmountPay(Double giroAmountPay) {
		this.giroAmountPay = giroAmountPay;
	}

	public Integer getFgiroBean() {
		return fgiroBean;
	}

	public void setFgiroBean(Integer fgiroBean) {
		this.fgiroBean = fgiroBean;
	}

	public Integer getAccAccountPotonganBean() {
		return accAccountPotonganBean;
	}

	public void setAccAccountPotonganBean(Integer accAccountPotonganBean) {
		this.accAccountPotonganBean = accAccountPotonganBean;
	}
}