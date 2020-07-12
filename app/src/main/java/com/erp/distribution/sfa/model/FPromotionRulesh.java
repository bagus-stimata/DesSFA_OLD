package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumPromoDiscFgMethod;

import java.util.Date;


@Entity(tableName="fPromotionRulesh")
public class FPromotionRulesh {

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;

	private String kode1 ="";
	private String kode2 ="";
	
    private String description="";
	
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

    private Date validPeriodDateFrom =new Date();
    private Date validPeriodDateTo = new Date();
		
	private boolean sharedToCompany=false; //setting Shared to company khusus ditaruh disini, bukan di divisi

	private boolean statusActive=true;
	
	/*
	 * AKAN DITAMBAHKAN
	 * di freegood ditambahkan jumlah case maximal
	 */
	
//	FPromotionBudget fpromotionBudget;
//	AccAccount accAccountBean;
	/*
	 * Account Mapping
	 * Account Biaya Diskon jika: Yang akan terbit saat akan membuat jurnal saat transaksi penjualan
	 */
//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
//	private AccAccount accAccountBean; //DEBIT
	private Integer accAccountBean = 0;

//	@ManyToOne
//	@JoinColumn(name="accAccount_CreditBean", referencedColumnName="ID")
//	private AccAccount accAccount_CreditBean; //CREDIT
	private Integer accAccount_CreditBean = 0;

	private boolean claimPabrik=false;
//	@Column(name="GANTI_BARANG")
//	private boolean gantiBarang=false;
//	@ManyToOne
//	@JoinColumn(name="accAccountDebitBean", referencedColumnName="ID")
//	private AccAccount accAccountDebitBean; //Acount Debit jika Claimable ke Pabrik
	
	
    private Double maxTargetValue=0.0; // 0 (nol) berarti tidak ada target sampai behenti
    private Integer maxTargetQty=0; // 0 (nol) berarti tidak ada target sampai berhenti
	
    private Double totalValueApplied=0.0;
    private Integer totalQtyApplied=0;
    @Ignore
    private String totalQtyAppliedUom1234="";
	
	private Double amountPayRp=0.0; //Menghilangkan DCV

	/*
	 * DISCOUNT METHOD: What Customers Gets
	 */
	private EnumPromoDiscFgMethod promoDiscMethod;
	
	/*
	 * Minimum Pengambilan
	 */
    private Integer discMinQtyOrValue=0;
    private Integer discMaxQtyOrValue=0;
	
	/*
	 * KALAU DISC KAN PASTI KELIPATAN YA?
	 * INI BERARTI TIDAK PERLU: 
	 * PERLU DIEVALUASI LAGI
	 */
	private boolean discKelipatan=true;
	
	private boolean discCashOnly = false;
	
    private Double forDiscQtyOrValueLev1 =0.0;
		private Double disc1GetLev1 =0.0;
		private Double disc1GetLev1_Value =0.0;
		private Double disc2GetLev1 =0.0;
		private Double disc3GetLev1 =0.0;
		private Double disc1PlusGetLev1 =0.0;
		private Double disc2PlusGetLev1 =0.0;
		
    private Double forDiscQtyOrValueLev2 =0.0;
	    private Double disc1GetLev2 =0.0;
	    private Double disc1GetLev2_Value =0.0;
	    private Double disc2GetLev2 =0.0;
	    private Double disc3GetLev2 =0.0;
	    private Double disc1PlusGetLev2 =0.0;
	    private Double disc2PlusGetLev2 =0.0;
		
    private Double forDiscQtyOrValueLev3 =0.0;
	    private Double disc1GetLev3 =0.0;
	    private Double disc1GetLev3_Value =0.0;
	    private Double disc2GetLev3 =0.0;
	    private Double disc3GetLev3 =0.0;
	    private Double disc1PlusGetLev3 =0.0;
	    private Double disc2PlusGetLev3 =0.0;
		
    private Double forDiscQtyOrValueLev4 =0.0;
	    private Double disc1GetLev4 =0.0;
		
	    private Double disc1GetLev4_Value =0.0;
		
	    private Double disc2GetLev4 =0.0;
	    private Double disc3GetLev4 =0.0;
	    private Double disc1PlusGetLev4 =0.0;
	    private Double disc2PlusGetLev4 =0.0;
		
    private Double forDiscQtyOrValueLev5 =0.0;
	    private Double disc1GetLev5 =0.0;
	    private Double disc1GetLev5_Value =0.0;
	    private Double disc2GetLev5 =0.0;
	    private Double disc3GetLev5 =0.0;
	    private Double disc1PlusGetLev5 =0.0;
	    private Double disc2PlusGetLev5 =0.0;
	
	
	
	/*
	 * Kadang dalam suatu promo mendapat 2 barang
	 * FREE GOOD 1 METHOD: What Customers Gets
	 */
		private EnumPromoDiscFgMethod promoFg1Method;

		/*
		 * Minimum Pengambilan
		 */
	    private Integer fg1MinQtyOrValue=0;
		
		private boolean fg1Kelipatan=true;
		
//		@ManyToOne
//		@JoinColumn(name="freeGood1MaterialBean", referencedColumnName="ID")
//	    private FMaterial freeGood1MaterialBean;
		private Integer freeGood1MaterialBean = 0;

		private boolean fg1HargaJualNol =true;
		/*
		 * Dipakai untuk perhitungan berapa nominal rupiah
		 * 
		 */
		private Double fg1PricePcs =0.0;
		
		
	    private Integer forFg1QtyOrValueLev1=0;
		    private Integer fg1QtyGetLev1=0;

	    private Integer forFg1QtyOrValueLev2=0;
			private Integer fg1QtyGetLev2=0;

	    private Integer forFg1QtyOrValueLev3=0;
		    private Integer fg1QtyGetLev3=0;

	    private Integer forFg1QtyOrValueLev4=0;
		    private Integer fg1QtyGetLev4=0;
			
	    private Integer forFg1QtyOrValueLev5=0;
		    private Integer fg1QtyGetLev5=0;
	
	/*
	 * Kadang dalam suatu promo mendapat 2 barang
	 * FREE GOOD 2 METHOD: What Customers Gets
	 */
		private EnumPromoDiscFgMethod promoFg2Method;
	
		/*
		 * Minimum Pengambilan
		 */
	    private Integer fg2MinQtyOrValue=0;
		
		private boolean fg2Kelipatan=true;
		
//		@ManyToOne
//		@JoinColumn(name="freeGood2MaterialBean", referencedColumnName="ID")
//	    private FMaterial freeGood2MaterialBean;
		private Integer freeGood2MaterialBean = 0;

		private boolean fg2HargaJualNol =true;
		/*
		 * Dipakai untuk perhitungan berapa nominal rupiah
		 * 
		 */
		private Double fg2PricePcs =0.0;

	    private Integer forFg2QtyOrValueLev1=0;
		    private Integer fg2QtyGetLev1=0;
	
	    private Integer forFg2QtyOrValueLev2=0;
			private Integer fg2QtyGetLev2=0;
	
	    private Integer forFg2QtyOrValueLev3=0;
		    private Integer fg2QtyGetLev3=0;
	
	    private Integer forFg2QtyOrValueLev4=0;
		    private Integer fg2QtyGetLev4=0;
			
	    private Integer forFg2QtyOrValueLev5=0;
		    private Integer fg2QtyGetLev5=0;

		
	
	//CASHBACK	
    private Double cashBackValue1=0.0;
    private Double cashBackGet1=0.0;
    private Double cashBackValue2;
    private Double cashBackGet2=0.0;
    private Double cashBackValue3=0.0;
    private Double cashBackGet3=0.0;
    private Double cashBackValue4=0.0;
    private Double cashBackGet4=0.0;

	//LOG
	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSourceID() {
		return sourceID;
	}

	public void setSourceID(Integer sourceID) {
		this.sourceID = sourceID;
	}

	public String getKode1() {
		return kode1;
	}

	public void setKode1(String kode1) {
		this.kode1 = kode1;
	}

	public String getKode2() {
		return kode2;
	}

	public void setKode2(String kode2) {
		this.kode2 = kode2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Date getValidPeriodDateFrom() {
		return validPeriodDateFrom;
	}

	public void setValidPeriodDateFrom(Date validPeriodDateFrom) {
		this.validPeriodDateFrom = validPeriodDateFrom;
	}

	public Date getValidPeriodDateTo() {
		return validPeriodDateTo;
	}

	public void setValidPeriodDateTo(Date validPeriodDateTo) {
		this.validPeriodDateTo = validPeriodDateTo;
	}

	public boolean isSharedToCompany() {
		return sharedToCompany;
	}

	public void setSharedToCompany(boolean sharedToCompany) {
		this.sharedToCompany = sharedToCompany;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public Integer getAccAccountBean() {
		return accAccountBean;
	}

	public void setAccAccountBean(Integer accAccountBean) {
		this.accAccountBean = accAccountBean;
	}

	public Integer getAccAccount_CreditBean() {
		return accAccount_CreditBean;
	}

	public void setAccAccount_CreditBean(Integer accAccount_CreditBean) {
		this.accAccount_CreditBean = accAccount_CreditBean;
	}

	public boolean isClaimPabrik() {
		return claimPabrik;
	}

	public void setClaimPabrik(boolean claimPabrik) {
		this.claimPabrik = claimPabrik;
	}

	public Double getMaxTargetValue() {
		return maxTargetValue;
	}

	public void setMaxTargetValue(Double maxTargetValue) {
		this.maxTargetValue = maxTargetValue;
	}

	public Integer getMaxTargetQty() {
		return maxTargetQty;
	}

	public void setMaxTargetQty(Integer maxTargetQty) {
		this.maxTargetQty = maxTargetQty;
	}

	public Double getTotalValueApplied() {
		return totalValueApplied;
	}

	public void setTotalValueApplied(Double totalValueApplied) {
		this.totalValueApplied = totalValueApplied;
	}

	public Integer getTotalQtyApplied() {
		return totalQtyApplied;
	}

	public void setTotalQtyApplied(Integer totalQtyApplied) {
		this.totalQtyApplied = totalQtyApplied;
	}

	public String getTotalQtyAppliedUom1234() {
		return totalQtyAppliedUom1234;
	}

	public void setTotalQtyAppliedUom1234(String totalQtyAppliedUom1234) {
		this.totalQtyAppliedUom1234 = totalQtyAppliedUom1234;
	}

	public Double getAmountPayRp() {
		return amountPayRp;
	}

	public void setAmountPayRp(Double amountPayRp) {
		this.amountPayRp = amountPayRp;
	}

	public EnumPromoDiscFgMethod getPromoDiscMethod() {
		return promoDiscMethod;
	}

	public void setPromoDiscMethod(EnumPromoDiscFgMethod promoDiscMethod) {
		this.promoDiscMethod = promoDiscMethod;
	}

	public Integer getDiscMinQtyOrValue() {
		return discMinQtyOrValue;
	}

	public void setDiscMinQtyOrValue(Integer discMinQtyOrValue) {
		this.discMinQtyOrValue = discMinQtyOrValue;
	}

	public Integer getDiscMaxQtyOrValue() {
		return discMaxQtyOrValue;
	}

	public void setDiscMaxQtyOrValue(Integer discMaxQtyOrValue) {
		this.discMaxQtyOrValue = discMaxQtyOrValue;
	}

	public boolean isDiscKelipatan() {
		return discKelipatan;
	}

	public void setDiscKelipatan(boolean discKelipatan) {
		this.discKelipatan = discKelipatan;
	}

	public boolean isDiscCashOnly() {
		return discCashOnly;
	}

	public void setDiscCashOnly(boolean discCashOnly) {
		this.discCashOnly = discCashOnly;
	}

	public Double getForDiscQtyOrValueLev1() {
		return forDiscQtyOrValueLev1;
	}

	public void setForDiscQtyOrValueLev1(Double forDiscQtyOrValueLev1) {
		this.forDiscQtyOrValueLev1 = forDiscQtyOrValueLev1;
	}

	public Double getDisc1GetLev1() {
		return disc1GetLev1;
	}

	public void setDisc1GetLev1(Double disc1GetLev1) {
		this.disc1GetLev1 = disc1GetLev1;
	}

	public Double getDisc1GetLev1_Value() {
		return disc1GetLev1_Value;
	}

	public void setDisc1GetLev1_Value(Double disc1GetLev1_Value) {
		this.disc1GetLev1_Value = disc1GetLev1_Value;
	}

	public Double getDisc2GetLev1() {
		return disc2GetLev1;
	}

	public void setDisc2GetLev1(Double disc2GetLev1) {
		this.disc2GetLev1 = disc2GetLev1;
	}

	public Double getDisc3GetLev1() {
		return disc3GetLev1;
	}

	public void setDisc3GetLev1(Double disc3GetLev1) {
		this.disc3GetLev1 = disc3GetLev1;
	}

	public Double getDisc1PlusGetLev1() {
		return disc1PlusGetLev1;
	}

	public void setDisc1PlusGetLev1(Double disc1PlusGetLev1) {
		this.disc1PlusGetLev1 = disc1PlusGetLev1;
	}

	public Double getDisc2PlusGetLev1() {
		return disc2PlusGetLev1;
	}

	public void setDisc2PlusGetLev1(Double disc2PlusGetLev1) {
		this.disc2PlusGetLev1 = disc2PlusGetLev1;
	}

	public Double getForDiscQtyOrValueLev2() {
		return forDiscQtyOrValueLev2;
	}

	public void setForDiscQtyOrValueLev2(Double forDiscQtyOrValueLev2) {
		this.forDiscQtyOrValueLev2 = forDiscQtyOrValueLev2;
	}

	public Double getDisc1GetLev2() {
		return disc1GetLev2;
	}

	public void setDisc1GetLev2(Double disc1GetLev2) {
		this.disc1GetLev2 = disc1GetLev2;
	}

	public Double getDisc1GetLev2_Value() {
		return disc1GetLev2_Value;
	}

	public void setDisc1GetLev2_Value(Double disc1GetLev2_Value) {
		this.disc1GetLev2_Value = disc1GetLev2_Value;
	}

	public Double getDisc2GetLev2() {
		return disc2GetLev2;
	}

	public void setDisc2GetLev2(Double disc2GetLev2) {
		this.disc2GetLev2 = disc2GetLev2;
	}

	public Double getDisc3GetLev2() {
		return disc3GetLev2;
	}

	public void setDisc3GetLev2(Double disc3GetLev2) {
		this.disc3GetLev2 = disc3GetLev2;
	}

	public Double getDisc1PlusGetLev2() {
		return disc1PlusGetLev2;
	}

	public void setDisc1PlusGetLev2(Double disc1PlusGetLev2) {
		this.disc1PlusGetLev2 = disc1PlusGetLev2;
	}

	public Double getDisc2PlusGetLev2() {
		return disc2PlusGetLev2;
	}

	public void setDisc2PlusGetLev2(Double disc2PlusGetLev2) {
		this.disc2PlusGetLev2 = disc2PlusGetLev2;
	}

	public Double getForDiscQtyOrValueLev3() {
		return forDiscQtyOrValueLev3;
	}

	public void setForDiscQtyOrValueLev3(Double forDiscQtyOrValueLev3) {
		this.forDiscQtyOrValueLev3 = forDiscQtyOrValueLev3;
	}

	public Double getDisc1GetLev3() {
		return disc1GetLev3;
	}

	public void setDisc1GetLev3(Double disc1GetLev3) {
		this.disc1GetLev3 = disc1GetLev3;
	}

	public Double getDisc1GetLev3_Value() {
		return disc1GetLev3_Value;
	}

	public void setDisc1GetLev3_Value(Double disc1GetLev3_Value) {
		this.disc1GetLev3_Value = disc1GetLev3_Value;
	}

	public Double getDisc2GetLev3() {
		return disc2GetLev3;
	}

	public void setDisc2GetLev3(Double disc2GetLev3) {
		this.disc2GetLev3 = disc2GetLev3;
	}

	public Double getDisc3GetLev3() {
		return disc3GetLev3;
	}

	public void setDisc3GetLev3(Double disc3GetLev3) {
		this.disc3GetLev3 = disc3GetLev3;
	}

	public Double getDisc1PlusGetLev3() {
		return disc1PlusGetLev3;
	}

	public void setDisc1PlusGetLev3(Double disc1PlusGetLev3) {
		this.disc1PlusGetLev3 = disc1PlusGetLev3;
	}

	public Double getDisc2PlusGetLev3() {
		return disc2PlusGetLev3;
	}

	public void setDisc2PlusGetLev3(Double disc2PlusGetLev3) {
		this.disc2PlusGetLev3 = disc2PlusGetLev3;
	}

	public Double getForDiscQtyOrValueLev4() {
		return forDiscQtyOrValueLev4;
	}

	public void setForDiscQtyOrValueLev4(Double forDiscQtyOrValueLev4) {
		this.forDiscQtyOrValueLev4 = forDiscQtyOrValueLev4;
	}

	public Double getDisc1GetLev4() {
		return disc1GetLev4;
	}

	public void setDisc1GetLev4(Double disc1GetLev4) {
		this.disc1GetLev4 = disc1GetLev4;
	}

	public Double getDisc1GetLev4_Value() {
		return disc1GetLev4_Value;
	}

	public void setDisc1GetLev4_Value(Double disc1GetLev4_Value) {
		this.disc1GetLev4_Value = disc1GetLev4_Value;
	}

	public Double getDisc2GetLev4() {
		return disc2GetLev4;
	}

	public void setDisc2GetLev4(Double disc2GetLev4) {
		this.disc2GetLev4 = disc2GetLev4;
	}

	public Double getDisc3GetLev4() {
		return disc3GetLev4;
	}

	public void setDisc3GetLev4(Double disc3GetLev4) {
		this.disc3GetLev4 = disc3GetLev4;
	}

	public Double getDisc1PlusGetLev4() {
		return disc1PlusGetLev4;
	}

	public void setDisc1PlusGetLev4(Double disc1PlusGetLev4) {
		this.disc1PlusGetLev4 = disc1PlusGetLev4;
	}

	public Double getDisc2PlusGetLev4() {
		return disc2PlusGetLev4;
	}

	public void setDisc2PlusGetLev4(Double disc2PlusGetLev4) {
		this.disc2PlusGetLev4 = disc2PlusGetLev4;
	}

	public Double getForDiscQtyOrValueLev5() {
		return forDiscQtyOrValueLev5;
	}

	public void setForDiscQtyOrValueLev5(Double forDiscQtyOrValueLev5) {
		this.forDiscQtyOrValueLev5 = forDiscQtyOrValueLev5;
	}

	public Double getDisc1GetLev5() {
		return disc1GetLev5;
	}

	public void setDisc1GetLev5(Double disc1GetLev5) {
		this.disc1GetLev5 = disc1GetLev5;
	}

	public Double getDisc1GetLev5_Value() {
		return disc1GetLev5_Value;
	}

	public void setDisc1GetLev5_Value(Double disc1GetLev5_Value) {
		this.disc1GetLev5_Value = disc1GetLev5_Value;
	}

	public Double getDisc2GetLev5() {
		return disc2GetLev5;
	}

	public void setDisc2GetLev5(Double disc2GetLev5) {
		this.disc2GetLev5 = disc2GetLev5;
	}

	public Double getDisc3GetLev5() {
		return disc3GetLev5;
	}

	public void setDisc3GetLev5(Double disc3GetLev5) {
		this.disc3GetLev5 = disc3GetLev5;
	}

	public Double getDisc1PlusGetLev5() {
		return disc1PlusGetLev5;
	}

	public void setDisc1PlusGetLev5(Double disc1PlusGetLev5) {
		this.disc1PlusGetLev5 = disc1PlusGetLev5;
	}

	public Double getDisc2PlusGetLev5() {
		return disc2PlusGetLev5;
	}

	public void setDisc2PlusGetLev5(Double disc2PlusGetLev5) {
		this.disc2PlusGetLev5 = disc2PlusGetLev5;
	}

	public EnumPromoDiscFgMethod getPromoFg1Method() {
		return promoFg1Method;
	}

	public void setPromoFg1Method(EnumPromoDiscFgMethod promoFg1Method) {
		this.promoFg1Method = promoFg1Method;
	}

	public Integer getFg1MinQtyOrValue() {
		return fg1MinQtyOrValue;
	}

	public void setFg1MinQtyOrValue(Integer fg1MinQtyOrValue) {
		this.fg1MinQtyOrValue = fg1MinQtyOrValue;
	}

	public boolean isFg1Kelipatan() {
		return fg1Kelipatan;
	}

	public void setFg1Kelipatan(boolean fg1Kelipatan) {
		this.fg1Kelipatan = fg1Kelipatan;
	}

	public Integer getFreeGood1MaterialBean() {
		return freeGood1MaterialBean;
	}

	public void setFreeGood1MaterialBean(Integer freeGood1MaterialBean) {
		this.freeGood1MaterialBean = freeGood1MaterialBean;
	}

	public boolean isFg1HargaJualNol() {
		return fg1HargaJualNol;
	}

	public void setFg1HargaJualNol(boolean fg1HargaJualNol) {
		this.fg1HargaJualNol = fg1HargaJualNol;
	}

	public Double getFg1PricePcs() {
		return fg1PricePcs;
	}

	public void setFg1PricePcs(Double fg1PricePcs) {
		this.fg1PricePcs = fg1PricePcs;
	}

	public Integer getForFg1QtyOrValueLev1() {
		return forFg1QtyOrValueLev1;
	}

	public void setForFg1QtyOrValueLev1(Integer forFg1QtyOrValueLev1) {
		this.forFg1QtyOrValueLev1 = forFg1QtyOrValueLev1;
	}

	public Integer getFg1QtyGetLev1() {
		return fg1QtyGetLev1;
	}

	public void setFg1QtyGetLev1(Integer fg1QtyGetLev1) {
		this.fg1QtyGetLev1 = fg1QtyGetLev1;
	}

	public Integer getForFg1QtyOrValueLev2() {
		return forFg1QtyOrValueLev2;
	}

	public void setForFg1QtyOrValueLev2(Integer forFg1QtyOrValueLev2) {
		this.forFg1QtyOrValueLev2 = forFg1QtyOrValueLev2;
	}

	public Integer getFg1QtyGetLev2() {
		return fg1QtyGetLev2;
	}

	public void setFg1QtyGetLev2(Integer fg1QtyGetLev2) {
		this.fg1QtyGetLev2 = fg1QtyGetLev2;
	}

	public Integer getForFg1QtyOrValueLev3() {
		return forFg1QtyOrValueLev3;
	}

	public void setForFg1QtyOrValueLev3(Integer forFg1QtyOrValueLev3) {
		this.forFg1QtyOrValueLev3 = forFg1QtyOrValueLev3;
	}

	public Integer getFg1QtyGetLev3() {
		return fg1QtyGetLev3;
	}

	public void setFg1QtyGetLev3(Integer fg1QtyGetLev3) {
		this.fg1QtyGetLev3 = fg1QtyGetLev3;
	}

	public Integer getForFg1QtyOrValueLev4() {
		return forFg1QtyOrValueLev4;
	}

	public void setForFg1QtyOrValueLev4(Integer forFg1QtyOrValueLev4) {
		this.forFg1QtyOrValueLev4 = forFg1QtyOrValueLev4;
	}

	public Integer getFg1QtyGetLev4() {
		return fg1QtyGetLev4;
	}

	public void setFg1QtyGetLev4(Integer fg1QtyGetLev4) {
		this.fg1QtyGetLev4 = fg1QtyGetLev4;
	}

	public Integer getForFg1QtyOrValueLev5() {
		return forFg1QtyOrValueLev5;
	}

	public void setForFg1QtyOrValueLev5(Integer forFg1QtyOrValueLev5) {
		this.forFg1QtyOrValueLev5 = forFg1QtyOrValueLev5;
	}

	public Integer getFg1QtyGetLev5() {
		return fg1QtyGetLev5;
	}

	public void setFg1QtyGetLev5(Integer fg1QtyGetLev5) {
		this.fg1QtyGetLev5 = fg1QtyGetLev5;
	}

	public EnumPromoDiscFgMethod getPromoFg2Method() {
		return promoFg2Method;
	}

	public void setPromoFg2Method(EnumPromoDiscFgMethod promoFg2Method) {
		this.promoFg2Method = promoFg2Method;
	}

	public Integer getFg2MinQtyOrValue() {
		return fg2MinQtyOrValue;
	}

	public void setFg2MinQtyOrValue(Integer fg2MinQtyOrValue) {
		this.fg2MinQtyOrValue = fg2MinQtyOrValue;
	}

	public boolean isFg2Kelipatan() {
		return fg2Kelipatan;
	}

	public void setFg2Kelipatan(boolean fg2Kelipatan) {
		this.fg2Kelipatan = fg2Kelipatan;
	}

	public Integer getFreeGood2MaterialBean() {
		return freeGood2MaterialBean;
	}

	public void setFreeGood2MaterialBean(Integer freeGood2MaterialBean) {
		this.freeGood2MaterialBean = freeGood2MaterialBean;
	}

	public boolean isFg2HargaJualNol() {
		return fg2HargaJualNol;
	}

	public void setFg2HargaJualNol(boolean fg2HargaJualNol) {
		this.fg2HargaJualNol = fg2HargaJualNol;
	}

	public Double getFg2PricePcs() {
		return fg2PricePcs;
	}

	public void setFg2PricePcs(Double fg2PricePcs) {
		this.fg2PricePcs = fg2PricePcs;
	}

	public Integer getForFg2QtyOrValueLev1() {
		return forFg2QtyOrValueLev1;
	}

	public void setForFg2QtyOrValueLev1(Integer forFg2QtyOrValueLev1) {
		this.forFg2QtyOrValueLev1 = forFg2QtyOrValueLev1;
	}

	public Integer getFg2QtyGetLev1() {
		return fg2QtyGetLev1;
	}

	public void setFg2QtyGetLev1(Integer fg2QtyGetLev1) {
		this.fg2QtyGetLev1 = fg2QtyGetLev1;
	}

	public Integer getForFg2QtyOrValueLev2() {
		return forFg2QtyOrValueLev2;
	}

	public void setForFg2QtyOrValueLev2(Integer forFg2QtyOrValueLev2) {
		this.forFg2QtyOrValueLev2 = forFg2QtyOrValueLev2;
	}

	public Integer getFg2QtyGetLev2() {
		return fg2QtyGetLev2;
	}

	public void setFg2QtyGetLev2(Integer fg2QtyGetLev2) {
		this.fg2QtyGetLev2 = fg2QtyGetLev2;
	}

	public Integer getForFg2QtyOrValueLev3() {
		return forFg2QtyOrValueLev3;
	}

	public void setForFg2QtyOrValueLev3(Integer forFg2QtyOrValueLev3) {
		this.forFg2QtyOrValueLev3 = forFg2QtyOrValueLev3;
	}

	public Integer getFg2QtyGetLev3() {
		return fg2QtyGetLev3;
	}

	public void setFg2QtyGetLev3(Integer fg2QtyGetLev3) {
		this.fg2QtyGetLev3 = fg2QtyGetLev3;
	}

	public Integer getForFg2QtyOrValueLev4() {
		return forFg2QtyOrValueLev4;
	}

	public void setForFg2QtyOrValueLev4(Integer forFg2QtyOrValueLev4) {
		this.forFg2QtyOrValueLev4 = forFg2QtyOrValueLev4;
	}

	public Integer getFg2QtyGetLev4() {
		return fg2QtyGetLev4;
	}

	public void setFg2QtyGetLev4(Integer fg2QtyGetLev4) {
		this.fg2QtyGetLev4 = fg2QtyGetLev4;
	}

	public Integer getForFg2QtyOrValueLev5() {
		return forFg2QtyOrValueLev5;
	}

	public void setForFg2QtyOrValueLev5(Integer forFg2QtyOrValueLev5) {
		this.forFg2QtyOrValueLev5 = forFg2QtyOrValueLev5;
	}

	public Integer getFg2QtyGetLev5() {
		return fg2QtyGetLev5;
	}

	public void setFg2QtyGetLev5(Integer fg2QtyGetLev5) {
		this.fg2QtyGetLev5 = fg2QtyGetLev5;
	}

	public Double getCashBackValue1() {
		return cashBackValue1;
	}

	public void setCashBackValue1(Double cashBackValue1) {
		this.cashBackValue1 = cashBackValue1;
	}

	public Double getCashBackGet1() {
		return cashBackGet1;
	}

	public void setCashBackGet1(Double cashBackGet1) {
		this.cashBackGet1 = cashBackGet1;
	}

	public Double getCashBackValue2() {
		return cashBackValue2;
	}

	public void setCashBackValue2(Double cashBackValue2) {
		this.cashBackValue2 = cashBackValue2;
	}

	public Double getCashBackGet2() {
		return cashBackGet2;
	}

	public void setCashBackGet2(Double cashBackGet2) {
		this.cashBackGet2 = cashBackGet2;
	}

	public Double getCashBackValue3() {
		return cashBackValue3;
	}

	public void setCashBackValue3(Double cashBackValue3) {
		this.cashBackValue3 = cashBackValue3;
	}

	public Double getCashBackGet3() {
		return cashBackGet3;
	}

	public void setCashBackGet3(Double cashBackGet3) {
		this.cashBackGet3 = cashBackGet3;
	}

	public Double getCashBackValue4() {
		return cashBackValue4;
	}

	public void setCashBackValue4(Double cashBackValue4) {
		this.cashBackValue4 = cashBackValue4;
	}

	public Double getCashBackGet4() {
		return cashBackGet4;
	}

	public void setCashBackGet4(Double cashBackGet4) {
		this.cashBackGet4 = cashBackGet4;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
