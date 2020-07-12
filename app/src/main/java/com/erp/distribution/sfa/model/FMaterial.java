package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumMaterialType;
import com.erp.distribution.sfa.model.modelenum.EnumUom;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName="fMaterial")
public class FMaterial implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;
	
	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	@Ignore
	@JsonIgnore
	private Integer sourceID =0;

	@Ignore
	@JsonIgnore
	private Integer noUrut=0;
	
	private String pcode="";
	private String barcode="";
	private String pname="";

	@Ignore
	@JsonIgnore
	private String oldKode1="";


	@Ignore
	@JsonIgnore
	private String varianName="";

	@Ignore
	@JsonIgnore
	private boolean freeGood=false;

	@Ignore
	@JsonIgnore
	private String shortname="";
	
	private boolean statusActive=true;

	/*
	 * KLASIFIKASI: BASIC
	 */
	/* 
	 * exclusiveDivisionTransaction: Input Penjualan dan Pembelian akan menolak jika item product berlainan Divisi
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 * jika Division = All Division maka exclusiveDivisionView tidak berlaku
	 * 
	 * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
	 * 
	 * 	 Dalam satu Divisi biasanya terdapat beberapa Vendor
	 */
	@Ignore
	@JsonIgnore
	private boolean exclusiveDivisionTransaction=false;
	@Ignore
	@JsonIgnore
	private boolean exclusiveDivisionView=false;
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	/*
	 * TAX
	 */
//	@ManyToOne
//	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
//	private FTax ftaxBean;
	private Integer ftaxBean = 0;

	@Ignore
	@JsonIgnore
	private boolean taxable=true;
	
	/*
	 * Adalah Vendor Utama Produk Tersebut
	 * Kenyataanya Material/Product didapatkan dari banyak Vendor/Suplier
	 * 
	 * exclusiveVendorTransaction: Input Penjualan, Pembelian akan menolak jika berlaikan Vendor
	 * Transaksi Mutasi & Stock opname tidak termasuk(sementara)
	 */
	@Ignore
	@JsonIgnore
	private boolean exclusiveVendorTransaction=false;
//	@ManyToOne
//	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
//	private FVendor fvendorBean;
	private Integer fvendorBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fwarehouseBean_Utm", referencedColumnName="ID")
//	private FWarehouse fwarehouseBean_Utm;
	@Ignore
	@JsonIgnore
	private Integer fwarehouseBean_Utm = 0;


	@Ignore
	@JsonIgnore
	private EnumMaterialType materialType;


//	@ManyToOne
//	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
//	private FDistributionChannel fdistributionChannelBean;
	@Ignore
	@JsonIgnore
	private Integer fdistributionChannelBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
//	private FMaterialGroup3 fmaterialGroup3Bean;
	private Integer fmaterialGroup3Bean = 0;

	/*
	 * KLASIFIKASI: SALES
	 */
//	@ManyToOne
//	@JoinColumn(name="fmaterialSalesBrandBean", referencedColumnName="ID")
//	private FMaterialSalesBrand fmaterialSalesBrandBean;
	private Integer fmaterialSalesBrandBean = 0;

	
	//BATCH CODE --> Berhubungan dengan Stockist atau Gudang
	//PRODUCTION CODE --> Berhubungan dengan TANGGAL DIPRODUKSI DAN EXP.DATE
	@Ignore
	@JsonIgnore
	private String principalCode="";
	@Ignore
	@JsonIgnore
	private String batchCode="";
	@Ignore
	@JsonIgnore
	private String productionCode="";
	
	private Date productionDate=new Date();
	private Date expiredDate=new Date();

	@Ignore
	@JsonIgnore
	private Integer prodclass=0;
	
	private String uom1="";
	private String uom2="";
	private String uom3="";
	private String uom4="";
	
	private Integer convfact1=1; //uom1 to uom4
	private Integer convfact2=1; //uom2 to uom4
	private Integer convfact3=1; //uom3 to uom4
	
	/*
	 * PRICE yang muncul pada faktur dengan menggunakan UOM
	 * 0. dan 1 adalah default
	 * 2. uom 2
	 * 3. uom 3
	 * 4.uom 4
	 * 
	 */
	private EnumUom priceUom = EnumUom.UOM1;

	
//	@Transient
//	private Integer temp_QtySaldo = 0; //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur
	@Ignore
	private Double temp_QtySaldo = 0.0; //Penolong untuk keperluan lain: seperti untuk saldo retur atas faktur
	
	/*
	 * Harga Beli berbeda dengan HPP
	 */
	@Ignore
	@JsonIgnore
	private Double hargaBeliUOM4NetAfterPpn=0.0;
	
	/*
	 * HPP disimpan Perdivisi pada Tabel AccBalanceHpp
	 * HPP adalah Harga Net Per Barang SEBELUM PPN
	 */
	@Ignore
	@JsonIgnore
	private Double hppAwalPprice2=0.0; //Jika tidak ada HPP Awal maka menggunakan Harga Barang Net Sebelum PPN

	@Ignore
	@JsonIgnore
	private Double hppLifo=0.0;
	@Ignore
	@JsonIgnore
	private Double hppLifoTotalAmount=0.0;
	@Ignore
	@JsonIgnore
	private Double hppAverage=0.0;
	@Ignore
	@JsonIgnore
	private Double hppAverageTotalAmount=0.0;
	@Ignore
	@JsonIgnore
	private Double hppFifo=0.0;
	@Ignore
	@JsonIgnore
	private Double hppFifoTotalAmount=0.0;
	
	//PPRICE:: Disimpan dalam satuan Terbesar dan Terkecil. Setelah dan sebelum PPN
	private Double pprice=0.0;
	private Double ppriceAfterPpn=0.0;
	private Double pprice2=0.0;
	private Double pprice2AfterPpn=0.0;
	
	private Double sprice=0.0;
	private Double spriceAfterPpn;
	private Double sprice2=0.0;
	private Double sprice2AfterPpn=0.0;
	
	/*
	 * Min Stok: sama dengan Buffer Stock
	 * Max Stock: Stok dikatakan Over, sebetulnya tidak terlalu kepake, karena
	 * cara yang paling realistis untuk mengukur stok over adalah
	 * Melihat History Penjualan Vs Jumalh Stok hasilnya adalah = Jumlah hari stok akan habis
	 */
	private Integer minQtyStok=0;

	
	//TIDAK BOLEH DIGANTI-GANTI
	@Ignore
	@JsonIgnore
	//dalam Mili Liter
	private Integer volumeSmalest=0;
	//Dalam Grams
	private Integer weightSmalest=0;
	//Dalam Grams
	private Integer caseWeight=0;

	//IN Cm.. Cm3
	@Ignore
	@JsonIgnore
	private Integer caseWidth=0; //Panjang
	@Ignore
	@JsonIgnore
	private Integer caseHeight=0; //Tinggi
	@Ignore
	@JsonIgnore
	private Integer caseDepth=0; //Lebar (dibalik kan kalau english.. hehehe)



	@Ignore
	@JsonIgnore
	private boolean flagNewItem=false;
	@Ignore
	@JsonIgnore
	private boolean flagNewPrice=false;

	@Ignore
	@JsonIgnore
	private boolean useSpriceAlt=false;

	//#PRICEALT1 -- Retail -->ALL AFTER PPN
	//Retail-Besar
	@Ignore
	@JsonIgnore
	private Double spriceAltRetailBes=0.0;
	//Retail-Sedang
	@Ignore
	@JsonIgnore
	private Double spriceAltRetailSed=0.0;
	//Retail-Kecil
	@Ignore
	@JsonIgnore
	private Double spriceAltRetailKec=0.0;

	//#PRICEALT2 --> Grosir 1
	//Grosir-Besar
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosir1Bes=0.0;
	//Grosir-Sed
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosir1Sed=0.0;
	//Grosir-Kec
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosir1Kec=0.0;

	//#PRICEALT3 --> Grosir 2
	//Grosir2-Bes
	//Grosir-Besar
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosir2Bes=0.0;
	//Grosir-Sed
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosir2Sed=0.0;
	//Grosir-Kec
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosir2Kec=0.0;
	
	//Grosir Quantity
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosirQtyMoreEqual1=0.0;
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosirQtyMoreEqual2=0.0;
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosirQtyMoreEqual3=0.0;
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosirQtyMoreEqual4=0.0;

	@Ignore
	@JsonIgnore
	private Double spriceAltGrosirQtyValue1=0.0;
	@JsonIgnore
	private Double spriceAltGrosirQtyValue2=0.0;
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosirQtyValue3=0.0;
	@Ignore
	@JsonIgnore
	private Double spriceAltGrosirQtyValue4=0.0;

	@JsonIgnore
	private Date created = new Date();
	@JsonIgnore
	private Date modified = new Date();
	@JsonIgnore
	private String modifiedBy =""; //User ID

	@Ignore
	@JsonIgnore
	private boolean stared;
	@Ignore
	@JsonIgnore
	private boolean unread;
	@Ignore
	@JsonIgnore
	private boolean selected;


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

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getOldKode1() {
		return oldKode1;
	}

	public void setOldKode1(String oldKode1) {
		this.oldKode1 = oldKode1;
	}

	public String getVarianName() {
		return varianName;
	}

	public void setVarianName(String varianName) {
		this.varianName = varianName;
	}

	public boolean isFreeGood() {
		return freeGood;
	}

	public void setFreeGood(boolean freeGood) {
		this.freeGood = freeGood;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public boolean isExclusiveDivisionTransaction() {
		return exclusiveDivisionTransaction;
	}

	public void setExclusiveDivisionTransaction(boolean exclusiveDivisionTransaction) {
		this.exclusiveDivisionTransaction = exclusiveDivisionTransaction;
	}

	public boolean isExclusiveDivisionView() {
		return exclusiveDivisionView;
	}

	public void setExclusiveDivisionView(boolean exclusiveDivisionView) {
		this.exclusiveDivisionView = exclusiveDivisionView;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getFtaxBean() {
		return ftaxBean;
	}

	public void setFtaxBean(Integer ftaxBean) {
		this.ftaxBean = ftaxBean;
	}

	public boolean isTaxable() {
		return taxable;
	}

	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}

	public boolean isExclusiveVendorTransaction() {
		return exclusiveVendorTransaction;
	}

	public void setExclusiveVendorTransaction(boolean exclusiveVendorTransaction) {
		this.exclusiveVendorTransaction = exclusiveVendorTransaction;
	}

	public Integer getFvendorBean() {
		return fvendorBean;
	}

	public void setFvendorBean(Integer fvendorBean) {
		this.fvendorBean = fvendorBean;
	}

	public Integer getFwarehouseBean_Utm() {
		return fwarehouseBean_Utm;
	}

	public void setFwarehouseBean_Utm(Integer fwarehouseBean_Utm) {
		this.fwarehouseBean_Utm = fwarehouseBean_Utm;
	}

	public EnumMaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(EnumMaterialType materialType) {
		this.materialType = materialType;
	}

	public Integer getFdistributionChannelBean() {
		return fdistributionChannelBean;
	}

	public void setFdistributionChannelBean(Integer fdistributionChannelBean) {
		this.fdistributionChannelBean = fdistributionChannelBean;
	}

	public Integer getFmaterialGroup3Bean() {
		return fmaterialGroup3Bean;
	}

	public void setFmaterialGroup3Bean(Integer fmaterialGroup3Bean) {
		this.fmaterialGroup3Bean = fmaterialGroup3Bean;
	}

	public Integer getFmaterialSalesBrandBean() {
		return fmaterialSalesBrandBean;
	}

	public void setFmaterialSalesBrandBean(Integer fmaterialSalesBrandBean) {
		this.fmaterialSalesBrandBean = fmaterialSalesBrandBean;
	}

	public String getPrincipalCode() {
		return principalCode;
	}

	public void setPrincipalCode(String principalCode) {
		this.principalCode = principalCode;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getProductionCode() {
		return productionCode;
	}

	public void setProductionCode(String productionCode) {
		this.productionCode = productionCode;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Integer getProdclass() {
		return prodclass;
	}

	public void setProdclass(Integer prodclass) {
		this.prodclass = prodclass;
	}

	public String getUom1() {
		return uom1;
	}

	public void setUom1(String uom1) {
		this.uom1 = uom1;
	}

	public String getUom2() {
		return uom2;
	}

	public void setUom2(String uom2) {
		this.uom2 = uom2;
	}

	public String getUom3() {
		return uom3;
	}

	public void setUom3(String uom3) {
		this.uom3 = uom3;
	}

	public String getUom4() {
		return uom4;
	}

	public void setUom4(String uom4) {
		this.uom4 = uom4;
	}

	public Integer getConvfact1() {
		return convfact1;
	}

	public void setConvfact1(Integer convfact1) {
		this.convfact1 = convfact1;
	}

	public Integer getConvfact2() {
		return convfact2;
	}

	public void setConvfact2(Integer convfact2) {
		this.convfact2 = convfact2;
	}

	public Integer getConvfact3() {
		return convfact3;
	}

	public void setConvfact3(Integer convfact3) {
		this.convfact3 = convfact3;
	}

	public EnumUom getPriceUom() {
		return priceUom;
	}

	public void setPriceUom(EnumUom priceUom) {
		this.priceUom = priceUom;
	}

	public Double getTemp_QtySaldo() {
		return temp_QtySaldo;
	}

	public void setTemp_QtySaldo(Double temp_QtySaldo) {
		this.temp_QtySaldo = temp_QtySaldo;
	}

	public Double getHargaBeliUOM4NetAfterPpn() {
		return hargaBeliUOM4NetAfterPpn;
	}

	public void setHargaBeliUOM4NetAfterPpn(Double hargaBeliUOM4NetAfterPpn) {
		this.hargaBeliUOM4NetAfterPpn = hargaBeliUOM4NetAfterPpn;
	}

	public Double getHppAwalPprice2() {
		return hppAwalPprice2;
	}

	public void setHppAwalPprice2(Double hppAwalPprice2) {
		this.hppAwalPprice2 = hppAwalPprice2;
	}

	public Double getHppLifo() {
		return hppLifo;
	}

	public void setHppLifo(Double hppLifo) {
		this.hppLifo = hppLifo;
	}

	public Double getHppLifoTotalAmount() {
		return hppLifoTotalAmount;
	}

	public void setHppLifoTotalAmount(Double hppLifoTotalAmount) {
		this.hppLifoTotalAmount = hppLifoTotalAmount;
	}

	public Double getHppAverage() {
		return hppAverage;
	}

	public void setHppAverage(Double hppAverage) {
		this.hppAverage = hppAverage;
	}

	public Double getHppAverageTotalAmount() {
		return hppAverageTotalAmount;
	}

	public void setHppAverageTotalAmount(Double hppAverageTotalAmount) {
		this.hppAverageTotalAmount = hppAverageTotalAmount;
	}

	public Double getHppFifo() {
		return hppFifo;
	}

	public void setHppFifo(Double hppFifo) {
		this.hppFifo = hppFifo;
	}

	public Double getHppFifoTotalAmount() {
		return hppFifoTotalAmount;
	}

	public void setHppFifoTotalAmount(Double hppFifoTotalAmount) {
		this.hppFifoTotalAmount = hppFifoTotalAmount;
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

	public Integer getMinQtyStok() {
		return minQtyStok;
	}

	public void setMinQtyStok(Integer minQtyStok) {
		this.minQtyStok = minQtyStok;
	}

	public Integer getVolumeSmalest() {
		return volumeSmalest;
	}

	public void setVolumeSmalest(Integer volumeSmalest) {
		this.volumeSmalest = volumeSmalest;
	}

	public Integer getWeightSmalest() {
		return weightSmalest;
	}

	public void setWeightSmalest(Integer weightSmalest) {
		this.weightSmalest = weightSmalest;
	}

	public Integer getCaseWidth() {
		return caseWidth;
	}

	public void setCaseWidth(Integer caseWidth) {
		this.caseWidth = caseWidth;
	}

	public Integer getCaseHeight() {
		return caseHeight;
	}

	public void setCaseHeight(Integer caseHeight) {
		this.caseHeight = caseHeight;
	}

	public Integer getCaseDepth() {
		return caseDepth;
	}

	public void setCaseDepth(Integer caseDepth) {
		this.caseDepth = caseDepth;
	}

	public Integer getCaseWeight() {
		return caseWeight;
	}

	public void setCaseWeight(Integer caseWeight) {
		this.caseWeight = caseWeight;
	}

	public boolean isFlagNewItem() {
		return flagNewItem;
	}

	public void setFlagNewItem(boolean flagNewItem) {
		this.flagNewItem = flagNewItem;
	}

	public boolean isFlagNewPrice() {
		return flagNewPrice;
	}

	public void setFlagNewPrice(boolean flagNewPrice) {
		this.flagNewPrice = flagNewPrice;
	}

	public boolean isUseSpriceAlt() {
		return useSpriceAlt;
	}

	public void setUseSpriceAlt(boolean useSpriceAlt) {
		this.useSpriceAlt = useSpriceAlt;
	}

	public Double getSpriceAltRetailBes() {
		return spriceAltRetailBes;
	}

	public void setSpriceAltRetailBes(Double spriceAltRetailBes) {
		this.spriceAltRetailBes = spriceAltRetailBes;
	}

	public Double getSpriceAltRetailSed() {
		return spriceAltRetailSed;
	}

	public void setSpriceAltRetailSed(Double spriceAltRetailSed) {
		this.spriceAltRetailSed = spriceAltRetailSed;
	}

	public Double getSpriceAltRetailKec() {
		return spriceAltRetailKec;
	}

	public void setSpriceAltRetailKec(Double spriceAltRetailKec) {
		this.spriceAltRetailKec = spriceAltRetailKec;
	}

	public Double getSpriceAltGrosir1Bes() {
		return spriceAltGrosir1Bes;
	}

	public void setSpriceAltGrosir1Bes(Double spriceAltGrosir1Bes) {
		this.spriceAltGrosir1Bes = spriceAltGrosir1Bes;
	}

	public Double getSpriceAltGrosir1Sed() {
		return spriceAltGrosir1Sed;
	}

	public void setSpriceAltGrosir1Sed(Double spriceAltGrosir1Sed) {
		this.spriceAltGrosir1Sed = spriceAltGrosir1Sed;
	}

	public Double getSpriceAltGrosir1Kec() {
		return spriceAltGrosir1Kec;
	}

	public void setSpriceAltGrosir1Kec(Double spriceAltGrosir1Kec) {
		this.spriceAltGrosir1Kec = spriceAltGrosir1Kec;
	}

	public Double getSpriceAltGrosir2Bes() {
		return spriceAltGrosir2Bes;
	}

	public void setSpriceAltGrosir2Bes(Double spriceAltGrosir2Bes) {
		this.spriceAltGrosir2Bes = spriceAltGrosir2Bes;
	}

	public Double getSpriceAltGrosir2Sed() {
		return spriceAltGrosir2Sed;
	}

	public void setSpriceAltGrosir2Sed(Double spriceAltGrosir2Sed) {
		this.spriceAltGrosir2Sed = spriceAltGrosir2Sed;
	}

	public Double getSpriceAltGrosir2Kec() {
		return spriceAltGrosir2Kec;
	}

	public void setSpriceAltGrosir2Kec(Double spriceAltGrosir2Kec) {
		this.spriceAltGrosir2Kec = spriceAltGrosir2Kec;
	}

	public Double getSpriceAltGrosirQtyMoreEqual1() {
		return spriceAltGrosirQtyMoreEqual1;
	}

	public void setSpriceAltGrosirQtyMoreEqual1(Double spriceAltGrosirQtyMoreEqual1) {
		this.spriceAltGrosirQtyMoreEqual1 = spriceAltGrosirQtyMoreEqual1;
	}

	public Double getSpriceAltGrosirQtyMoreEqual2() {
		return spriceAltGrosirQtyMoreEqual2;
	}

	public void setSpriceAltGrosirQtyMoreEqual2(Double spriceAltGrosirQtyMoreEqual2) {
		this.spriceAltGrosirQtyMoreEqual2 = spriceAltGrosirQtyMoreEqual2;
	}

	public Double getSpriceAltGrosirQtyMoreEqual3() {
		return spriceAltGrosirQtyMoreEqual3;
	}

	public void setSpriceAltGrosirQtyMoreEqual3(Double spriceAltGrosirQtyMoreEqual3) {
		this.spriceAltGrosirQtyMoreEqual3 = spriceAltGrosirQtyMoreEqual3;
	}

	public Double getSpriceAltGrosirQtyMoreEqual4() {
		return spriceAltGrosirQtyMoreEqual4;
	}

	public void setSpriceAltGrosirQtyMoreEqual4(Double spriceAltGrosirQtyMoreEqual4) {
		this.spriceAltGrosirQtyMoreEqual4 = spriceAltGrosirQtyMoreEqual4;
	}

	public Double getSpriceAltGrosirQtyValue1() {
		return spriceAltGrosirQtyValue1;
	}

	public void setSpriceAltGrosirQtyValue1(Double spriceAltGrosirQtyValue1) {
		this.spriceAltGrosirQtyValue1 = spriceAltGrosirQtyValue1;
	}

	public Double getSpriceAltGrosirQtyValue2() {
		return spriceAltGrosirQtyValue2;
	}

	public void setSpriceAltGrosirQtyValue2(Double spriceAltGrosirQtyValue2) {
		this.spriceAltGrosirQtyValue2 = spriceAltGrosirQtyValue2;
	}

	public Double getSpriceAltGrosirQtyValue3() {
		return spriceAltGrosirQtyValue3;
	}

	public void setSpriceAltGrosirQtyValue3(Double spriceAltGrosirQtyValue3) {
		this.spriceAltGrosirQtyValue3 = spriceAltGrosirQtyValue3;
	}

	public Double getSpriceAltGrosirQtyValue4() {
		return spriceAltGrosirQtyValue4;
	}

	public void setSpriceAltGrosirQtyValue4(Double spriceAltGrosirQtyValue4) {
		this.spriceAltGrosirQtyValue4 = spriceAltGrosirQtyValue4;
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

	public boolean isStared() {
		return stared;
	}

	public void setStared(boolean stared) {
		this.stared = stared;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FMaterial fMaterial = (FMaterial) o;

		return id.equals(fMaterial.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "FMaterial{" +
				"id=" + id +
				'}';
	}
}