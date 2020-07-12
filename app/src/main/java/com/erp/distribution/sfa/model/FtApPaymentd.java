package com.erp.distribution.sfa.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="ftApPaymentd")
public class FtApPaymentd {

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut=0;
	
	private Double cashAmountPay=0.0;

	private Double uangMukaAmountPay=0.0;

	private Double returAmountPay=0.0;
	
	private Double promotionAmountPay=0.0; //Jika ada potongan lain semacam DCV maka langsung saja dimasukkan Potongan

	private Double giroAmountPay=0.0;
	private Double transferAmountPay=0.0;
	
	private Double kelebihanBayarAmount=0.0;
	
//	@Column(name="SUBTOTAL_PAY")
//	private Double subtotalPay=0.0;
	
	private Double potonganAmount=0.0;
	
	private String potonganNotes = "";
	
//	@ManyToOne
//	@JoinColumn(name="ftApPaymenthBean", referencedColumnName="refno")
//	private FtApPaymenth ftApPaymenthBean;
	private Integer ftApPaymenthBean = 0;
//	@ManyToOne
//	@JoinColumn(name="ftPurchasehBean", referencedColumnName="refno")
//	private FtPurchaseh ftPurchasehBean;
	private Integer ftPurchasehBean = 0;

	/*
	 * Dibayarkan untuk Faktur atau Potong retur
	 */
//	@ManyToOne
//	@JoinColumn(name="returBean", referencedColumnName="refno")
//	private FtPurchaseh returBean;
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
//	private AccAccount accAccountPotonganBean; // Hutang(D) pada Biaya Potongan -> Hutang berkurang di Debet dan Biaya Potongan bertambah di Kredit
	private Integer accAccountPotonganBean = 0;


//	@ManyToOne
//	@JoinColumn(name="fpromotionRuleshBean", referencedColumnName="ID")
//	private FPromotionRulesh fpromotionRuleshBean; // Hutang(D) pada pada Piutang(K) -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
	private Integer fpromotionRuleshBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fuangMukaBean", referencedColumnName="ID")
//	private FUangMuka fuangMukaBean; // Hutang(D) pada pada Uang Muka -> Hutang Berkurang di DEBET dan Piutang Berkurang di Kredit
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

	public Double getPromotionAmountPay() {
		return promotionAmountPay;
	}

	public void setPromotionAmountPay(Double promotionAmountPay) {
		this.promotionAmountPay = promotionAmountPay;
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

	public Integer getFtApPaymenthBean() {
		return ftApPaymenthBean;
	}

	public void setFtApPaymenthBean(Integer ftApPaymenthBean) {
		this.ftApPaymenthBean = ftApPaymenthBean;
	}

	public Integer getFtPurchasehBean() {
		return ftPurchasehBean;
	}

	public void setFtPurchasehBean(Integer ftPurchasehBean) {
		this.ftPurchasehBean = ftPurchasehBean;
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

	public Integer getFpromotionRuleshBean() {
		return fpromotionRuleshBean;
	}

	public void setFpromotionRuleshBean(Integer fpromotionRuleshBean) {
		this.fpromotionRuleshBean = fpromotionRuleshBean;
	}

	public Integer getFuangMukaBean() {
		return fuangMukaBean;
	}

	public void setFuangMukaBean(Integer fuangMukaBean) {
		this.fuangMukaBean = fuangMukaBean;
	}
}