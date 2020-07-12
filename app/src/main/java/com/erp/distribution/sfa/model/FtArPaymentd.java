package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


 
@Entity(tableName="ftArPaymentd")
public class FtArPaymentd implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut=0;
	
	private Double cashAmountPay=0.0;

	private Double uangMukaAmountPay=0.0;
	
	private Double returAmountPay=0.0;
	
	private Double giroAmountPay=0.0;
	private Double transferAmountPay=0.0;
	
	private Double kelebihanBayarAmount=0.0;
	
//	@Column(name="SUBTOTAL_PAY")
//	private Double subtotalPay=0.0;
	
	private Double potonganAmount=0.0;
	
	/*
	 * DIPAKAI NOTE SECARA UMUM DALAM SATU TRANSAKSI
	 */
	private String potonganNotes = "";
	
//	@ManyToOne
//	@JoinColumn(name="ftArPaymenthBean", referencedColumnName="refno")
//	private FtArPaymenth ftArPaymenthBean;
	private Integer ftArPaymenthBean = 0;

	/*
	 * Dibayarkan untuk Faktur atau Potong retur
	 */
//	@ManyToOne
//	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
//	private FtSalesh ftSaleshBean;
	private Integer ftSaleshBean = 0;

	
//	@ManyToOne
//	@JoinColumn(name="returBean", referencedColumnName="refno")
//	private FtSalesh returBean;
	private Integer returBean = 0;
//	@ManyToOne
//	@JoinColumn(name="ftransferBean", referencedColumnName="ID", nullable=true)
//	private FGiro ftransferBean;
	private Integer ftransferBean = 0;
//	@ManyToOne
//	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
//	private FGiro fgiroBean;
	private Integer fgiroBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accAccountPotonganBean", referencedColumnName="ID", nullable=true)
//	private AccAccount accAccountPotonganBean;
	private Integer accAccountPotonganBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fuangMukaBean", referencedColumnName="ID")
//	private FUangMuka fuangMukaBean; // Hutang(D) pada pada Piutang(K) -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
	private Integer fuangMukaBean = 0;


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

	public Double getCashAmountPay() {
		return cashAmountPay;
	}

	public void setCashAmountPay(Double cashAmountPay) {
		this.cashAmountPay = cashAmountPay;
	}

	public Double getUangMukaAmountPay() {
		return uangMukaAmountPay;
	}

	public void setUangMukaAmountPay(Double uangMukaAmountPay) {
		this.uangMukaAmountPay = uangMukaAmountPay;
	}

	public Double getReturAmountPay() {
		return returAmountPay;
	}

	public void setReturAmountPay(Double returAmountPay) {
		this.returAmountPay = returAmountPay;
	}

	public Double getGiroAmountPay() {
		return giroAmountPay;
	}

	public void setGiroAmountPay(Double giroAmountPay) {
		this.giroAmountPay = giroAmountPay;
	}

	public Double getTransferAmountPay() {
		return transferAmountPay;
	}

	public void setTransferAmountPay(Double transferAmountPay) {
		this.transferAmountPay = transferAmountPay;
	}

	public Double getKelebihanBayarAmount() {
		return kelebihanBayarAmount;
	}

	public void setKelebihanBayarAmount(Double kelebihanBayarAmount) {
		this.kelebihanBayarAmount = kelebihanBayarAmount;
	}

	public Double getPotonganAmount() {
		return potonganAmount;
	}

	public void setPotonganAmount(Double potonganAmount) {
		this.potonganAmount = potonganAmount;
	}

	public String getPotonganNotes() {
		return potonganNotes;
	}

	public void setPotonganNotes(String potonganNotes) {
		this.potonganNotes = potonganNotes;
	}

	public Integer getFtArPaymenthBean() {
		return ftArPaymenthBean;
	}

	public void setFtArPaymenthBean(Integer ftArPaymenthBean) {
		this.ftArPaymenthBean = ftArPaymenthBean;
	}

	public Integer getFtSaleshBean() {
		return ftSaleshBean;
	}

	public void setFtSaleshBean(Integer ftSaleshBean) {
		this.ftSaleshBean = ftSaleshBean;
	}

	public Integer getReturBean() {
		return returBean;
	}

	public void setReturBean(Integer returBean) {
		this.returBean = returBean;
	}

	public Integer getFtransferBean() {
		return ftransferBean;
	}

	public void setFtransferBean(Integer ftransferBean) {
		this.ftransferBean = ftransferBean;
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

	public Integer getFuangMukaBean() {
		return fuangMukaBean;
	}

	public void setFuangMukaBean(Integer fuangMukaBean) {
		this.fuangMukaBean = fuangMukaBean;
	}
}