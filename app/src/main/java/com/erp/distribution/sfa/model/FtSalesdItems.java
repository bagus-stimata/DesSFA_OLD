package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumUom;

import java.io.Serializable;


 
@Entity(tableName="ftSalesdItems")
public class FtSalesdItems implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long id=0;

	private boolean freeGood=false;
	
	private Integer noUrut=0;
	
	private String notes = "";
	
	private Double sprice=0.0;
	@Ignore
	private Double spricePpnRp=0.0;
	
	
	/*
	 * Dasar harga total
	 */
	private boolean tax=true;
//	@ManyToOne
//	@JoinColumn(name="ftaxBean", referencedColumnName="ID")
//	private FTax ftaxBean;
	private Integer ftaxBean = 0;

	private Double taxPercent=0.0;
	/*
	 * End: Dasar harga total
	 */

	
	@Ignore
	private Double spriceUom2=0.0;
	@Ignore
	private Double spriceUom3=0.0;
	@Ignore
	private Double spriceUom4=0.0;
	
	@Ignore
	private Double spriceAfterPpn=0.0;
	@Ignore
	private Double spriceUom2AfterPpn=0.0;
	@Ignore
	private Double spriceUom3AfterPpn=0.0;
	@Ignore
	private Double spriceUom4AfterPpn=0.0;

	//HARGA NET SETELAH Diskon Barang Semua: Ditaruh dibelakang diskon Harusnya
	@Ignore
	private Double spriceNET_Uom1AfterDiscAfterPpn=0.0;
	@Ignore
	private Double spriceNET_Uom2AfterDiscAfterPpn=0.0;
	@Ignore
	private Double spriceNET_Uom3AfterDiscAfterPpn=0.0;
	@Ignore
	private Double spriceNET_Uom4AfterDiscAfterPpn=0.0;
	
//	@Ignore
//	private Integer qty1=0.0;
//	@Ignore
//	private Integer qty2=0.0;
//	@Ignore
//	private Integer qty3=0.0;
//	@Ignore
//	private Integer qty4=0.0;
	@Ignore
	private Double qty1=0.0;
	@Ignore
	private Double qty2=0.0;
	@Ignore
	private Double qty3=0.0;
	@Ignore
	private Double qty4=0.0;

//	@Ignore
//	private Integer qty1Kembali=0.0;
//	@Ignore
//	private Integer qty2Kembali=0.0;
//	@Ignore
//	private Integer qty3Kembali=0.0;
//	@Ignore
//	private Integer qty4Kembali=0.0;
	@Ignore
	private Double qty1Kembali=0.0;
	@Ignore
	private Double qty2Kembali=0.0;
	@Ignore
	private Double qty3Kembali=0.0;
	@Ignore
	private Double qty4Kembali=0.0;
	
	/*Setiap pengiriman ada 2 kemungkinan: (1)Coretan Faktur/Tolakan dan (2)Faktur Batal
	qty = qtyTerkirim
	Total Jumlah yang diOrder = qty + qtyKembali
	*/
//	@Column(name="QTY", length=9)
//	private Integer qty=0.0;
//	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
//	private Integer qtyKembali = 0;	
//	@Ignore
//	private Integer qtyNET=0.0;
	private Double qty=0.0;
	private Double qtyKembali =0.0;
	@Ignore
	private Double qtyNET=0.0;
	/*
	 * Untuk Keperluan Retur
	 */
//	@Column(name="QTYRETURN", length=9)
//	private Integer qtyReturn =0.0;
	private Double qtyReturn =0.0;
	
	/*
	 * PRICE yang muncul pada faktur dengan menggunakan UOM
	 * 0. dan 1 adalah default
	 * 2. uom 2
	 * 3. uom 3
	 * 4.uom 4
	 * 
	 */
	private EnumUom priceUom = EnumUom.UOM1;
		
	//Sub total sebelum diskon
	@Ignore
	private Double subtotalRp=0.0;
	@Ignore
	private Double subtotalPpnRp=0.0;
	@Ignore
	private Double subtotalRpAfterPpn=0.0;
	
	private Double disc1=0.0;
	@Ignore
	private Double disc1Rp=0.0;
	@Ignore
	private Double disc1PpnRp=0.0;
	@Ignore
	private Double disc1RpAfterPpn=0.0;
	@Ignore
	private Double disc1RpAfterPpnUom1=0.0;
	@Ignore
	private Double disc1RpAfterPpnUom2=0.0;
	@Ignore
	private Double disc1RpAfterPpnUom3=0.0;
	@Ignore
	private Double disc1RpAfterPpnUom4=0.0;
	
	private Double disc2;
	@Ignore
	private Double disc2Rp=0.0;
	@Ignore
	private Double disc2PpnRp=0.0;
	@Ignore
	private Double disc2RpAfterPpn=0.0;
	@Ignore
	private Double disc2RpAfterPpnUom1=0.0;
	@Ignore
	private Double disc2RpAfterPpnUom2=0.0;
	@Ignore
	private Double disc2RpAfterPpnUom3=0.0;
	@Ignore
	private Double disc2RpAfterPpnUom4=0.0;

	private Double disc3=0.0;
	@Ignore
	private Double disc3Rp=0.0;
	@Ignore
	private Double disc3PpnRp=0.0;	
	@Ignore
	private Double disc3RpAfterPpn=0.0;	
	@Ignore
	private Double disc3RpAfterPpnUom1=0.0;	
	@Ignore
	private Double disc3RpAfterPpnUom2=0.0;	
	@Ignore
	private Double disc3RpAfterPpnUom3=0.0;	
	@Ignore
	private Double disc3RpAfterPpnUom4=0.0;		
	
	@Ignore
	private Double subtotalAfterDisc123Rp=0.0;
	@Ignore
	private Double subtotalAfterDisc123PpnRp=0.0;
	@Ignore
	private Double subtotalAfterDisc123RpAfterPpn=0.0;

	private Double disc1Plus=0.0;
	@Ignore
	private Double disc1PlusRp=0.0;
	@Ignore
	private Double disc1PlusPpnRp=0.0;
	@Ignore
	private Double disc1PlusRpAfterPpn=0.0;
	@Ignore
	private Double disc1PlusRpAfterPpnUom1=0.0;
	@Ignore
	private Double disc1PlusRpAfterPpnUom2=0.0;
	@Ignore
	private Double disc1PlusRpAfterPpnUom3=0.0;
	@Ignore
	private Double disc1PlusRpAfterPpnUom4=0.0;
	
	@Ignore
	private Double subtotalAfterDisc1PlusRp=0.0;
	@Ignore
	private Double subtotalAfterDisc1PlusPpnRp=0.0;
	@Ignore
	private Double subtotalAfterDisc1PlusRpAfterPpn=0.0;
	
	private Double disc2Plus=0.0;
	@Ignore
	private Double disc2PlusRp=0.0;
	@Ignore
	private Double disc2PlusPpnRp=0.0;	//ppn
	@Ignore
	private Double disc2PlusRpAfterPpn=0.0;	
	@Ignore
	private Double disc2PlusRpAfterPpnUom1=0.0;	
	@Ignore
	private Double disc2PlusRpAfterPpnUom2=0.0;	
	@Ignore
	private Double disc2PlusRpAfterPpnUom3=0.0;	
	@Ignore
	private Double disc2PlusRpAfterPpnUom4=0.0;	
	
	@Ignore
	private Double subtotalAfterDisc2PlusRp=0.0;
	@Ignore
	private Double subtotalAfterDisc2PlusPpnRp=0.0; //ppn
	@Ignore
	private Double subtotalAfterDisc2PlusRpAfterPpn=0.0;
	
	
	
	/*
	 * AFTER DISKON NOTA :: TAMBAHAN UNTUK MEMUDAHKAN PERHITUNGAN
	 */
	@Ignore
	private Double discNota1=0.0;
	@Ignore
	private Double discNota1Rp=0.0;
	@Ignore
	private Double discNota1PpnRp=0.0; //ppn
	@Ignore
	private Double discNota1RpAfterPpn=0.0;
	
	/*
	 * TAMBAHAN
	 */
	@Ignore
	private Double subtotalAfterDiscNota1Rp=0.0;
	@Ignore
	private Double subtotalAfterDiscNota1PpnRp=0.0; //ppn
	@Ignore
	private Double subtotalAfterDiscNota1RpAfterPpn=0.0;
	
	@Ignore
	private Double discNota2=0.0;
	@Ignore
	private Double discNota2Rp=0.0;
	@Ignore
	private Double discNota2PpnRp=0.0; //ppn
	@Ignore
	private Double discNota2RpAfterPpn=0.0;
	
	@Ignore
	private Double subtotalAfterDiscNota2Rp=0.0;
	@Ignore
	private Double subtotalAfterDiscNota2PpnRp=0.0; //ppn
	@Ignore
	private Double subtotalAfterDiscNota2RpAfterPpn=0.0;
	
	@Ignore
	private Double discNotaPlus_FG=0.0;
	@Ignore
	private Double discNotaPlusRp_FG=0.0;
	@Ignore
	private Double discNotaPlusPpnRp_FG=0.0; //ppn
	@Ignore
	private Double discNotaPlusRpAfterPpn_FG=0.0;
	
	@Ignore
	private Double subtotalAfterDiscNotaPlusRp_FG=0.0;
	@Ignore
	private Double subtotalAfterDiscNotaPlusPpnRp_FG=0.0; //ppn
	@Ignore
	private Double subtotalAfterDiscNotaPlusRpAfterPpn_FG=0.0;

	@Ignore
	private String tempString="";
	@Ignore
	private Integer tempInt=0;
	@Ignore
	private Double tempDouble1=0.0;
	@Ignore
	private Double tempDouble2=0.0;
	@Ignore
	private Integer tempDouble31=0;
	
	
	//TPR BARANG DAN UANG
//	@Column(name="TPRB")
//	private Double tprb=0.0;
//	@Column(name="TPRU_DISC")
//	private Double tpruDisc=0.0;
//	@Column(name="TPRU_CASHBACK")
//	private Double tpruCashback=0.0;
	
	
//	@ManyToOne
//	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
//	private FtSalesh ftSaleshBean;
	private Integer ftSaleshBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
//	private FMaterial fmaterialBean;
	private Integer fmaterialBean = 0;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isFreeGood() {
		return freeGood;
	}

	public void setFreeGood(boolean freeGood) {
		this.freeGood = freeGood;
	}

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getSprice() {
		return sprice;
	}

	public void setSprice(Double sprice) {
		this.sprice = sprice;
	}

	public Double getSpricePpnRp() {
		return spricePpnRp;
	}

	public void setSpricePpnRp(Double spricePpnRp) {
		this.spricePpnRp = spricePpnRp;
	}

	public boolean isTax() {
		return tax;
	}

	public void setTax(boolean tax) {
		this.tax = tax;
	}

	public Integer getFtaxBean() {
		return ftaxBean;
	}

	public void setFtaxBean(Integer ftaxBean) {
		this.ftaxBean = ftaxBean;
	}

	public Double getTaxPercent() {
		return taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public Double getSpriceUom2() {
		return spriceUom2;
	}

	public void setSpriceUom2(Double spriceUom2) {
		this.spriceUom2 = spriceUom2;
	}

	public Double getSpriceUom3() {
		return spriceUom3;
	}

	public void setSpriceUom3(Double spriceUom3) {
		this.spriceUom3 = spriceUom3;
	}

	public Double getSpriceUom4() {
		return spriceUom4;
	}

	public void setSpriceUom4(Double spriceUom4) {
		this.spriceUom4 = spriceUom4;
	}

	public Double getSpriceAfterPpn() {
		return spriceAfterPpn;
	}

	public void setSpriceAfterPpn(Double spriceAfterPpn) {
		this.spriceAfterPpn = spriceAfterPpn;
	}

	public Double getSpriceUom2AfterPpn() {
		return spriceUom2AfterPpn;
	}

	public void setSpriceUom2AfterPpn(Double spriceUom2AfterPpn) {
		this.spriceUom2AfterPpn = spriceUom2AfterPpn;
	}

	public Double getSpriceUom3AfterPpn() {
		return spriceUom3AfterPpn;
	}

	public void setSpriceUom3AfterPpn(Double spriceUom3AfterPpn) {
		this.spriceUom3AfterPpn = spriceUom3AfterPpn;
	}

	public Double getSpriceUom4AfterPpn() {
		return spriceUom4AfterPpn;
	}

	public void setSpriceUom4AfterPpn(Double spriceUom4AfterPpn) {
		this.spriceUom4AfterPpn = spriceUom4AfterPpn;
	}

	public Double getSpriceNET_Uom1AfterDiscAfterPpn() {
		return spriceNET_Uom1AfterDiscAfterPpn;
	}

	public void setSpriceNET_Uom1AfterDiscAfterPpn(Double spriceNET_Uom1AfterDiscAfterPpn) {
		this.spriceNET_Uom1AfterDiscAfterPpn = spriceNET_Uom1AfterDiscAfterPpn;
	}

	public Double getSpriceNET_Uom2AfterDiscAfterPpn() {
		return spriceNET_Uom2AfterDiscAfterPpn;
	}

	public void setSpriceNET_Uom2AfterDiscAfterPpn(Double spriceNET_Uom2AfterDiscAfterPpn) {
		this.spriceNET_Uom2AfterDiscAfterPpn = spriceNET_Uom2AfterDiscAfterPpn;
	}

	public Double getSpriceNET_Uom3AfterDiscAfterPpn() {
		return spriceNET_Uom3AfterDiscAfterPpn;
	}

	public void setSpriceNET_Uom3AfterDiscAfterPpn(Double spriceNET_Uom3AfterDiscAfterPpn) {
		this.spriceNET_Uom3AfterDiscAfterPpn = spriceNET_Uom3AfterDiscAfterPpn;
	}

	public Double getSpriceNET_Uom4AfterDiscAfterPpn() {
		return spriceNET_Uom4AfterDiscAfterPpn;
	}

	public void setSpriceNET_Uom4AfterDiscAfterPpn(Double spriceNET_Uom4AfterDiscAfterPpn) {
		this.spriceNET_Uom4AfterDiscAfterPpn = spriceNET_Uom4AfterDiscAfterPpn;
	}

	public Double getQty1() {
		return qty1;
	}

	public void setQty1(Double qty1) {
		this.qty1 = qty1;
	}

	public Double getQty2() {
		return qty2;
	}

	public void setQty2(Double qty2) {
		this.qty2 = qty2;
	}

	public Double getQty3() {
		return qty3;
	}

	public void setQty3(Double qty3) {
		this.qty3 = qty3;
	}

	public Double getQty4() {
		return qty4;
	}

	public void setQty4(Double qty4) {
		this.qty4 = qty4;
	}

	public Double getQty1Kembali() {
		return qty1Kembali;
	}

	public void setQty1Kembali(Double qty1Kembali) {
		this.qty1Kembali = qty1Kembali;
	}

	public Double getQty2Kembali() {
		return qty2Kembali;
	}

	public void setQty2Kembali(Double qty2Kembali) {
		this.qty2Kembali = qty2Kembali;
	}

	public Double getQty3Kembali() {
		return qty3Kembali;
	}

	public void setQty3Kembali(Double qty3Kembali) {
		this.qty3Kembali = qty3Kembali;
	}

	public Double getQty4Kembali() {
		return qty4Kembali;
	}

	public void setQty4Kembali(Double qty4Kembali) {
		this.qty4Kembali = qty4Kembali;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getQtyKembali() {
		return qtyKembali;
	}

	public void setQtyKembali(Double qtyKembali) {
		this.qtyKembali = qtyKembali;
	}

	public Double getQtyNET() {
		return qtyNET;
	}

	public void setQtyNET(Double qtyNET) {
		this.qtyNET = qtyNET;
	}

	public Double getQtyReturn() {
		return qtyReturn;
	}

	public void setQtyReturn(Double qtyReturn) {
		this.qtyReturn = qtyReturn;
	}

	public EnumUom getPriceUom() {
		return priceUom;
	}

	public void setPriceUom(EnumUom priceUom) {
		this.priceUom = priceUom;
	}

	public Double getSubtotalRp() {
		return subtotalRp;
	}

	public void setSubtotalRp(Double subtotalRp) {
		this.subtotalRp = subtotalRp;
	}

	public Double getSubtotalPpnRp() {
		return subtotalPpnRp;
	}

	public void setSubtotalPpnRp(Double subtotalPpnRp) {
		this.subtotalPpnRp = subtotalPpnRp;
	}

	public Double getSubtotalRpAfterPpn() {
		return subtotalRpAfterPpn;
	}

	public void setSubtotalRpAfterPpn(Double subtotalRpAfterPpn) {
		this.subtotalRpAfterPpn = subtotalRpAfterPpn;
	}

	public Double getDisc1() {
		return disc1;
	}

	public void setDisc1(Double disc1) {
		this.disc1 = disc1;
	}

	public Double getDisc1Rp() {
		return disc1Rp;
	}

	public void setDisc1Rp(Double disc1Rp) {
		this.disc1Rp = disc1Rp;
	}

	public Double getDisc1PpnRp() {
		return disc1PpnRp;
	}

	public void setDisc1PpnRp(Double disc1PpnRp) {
		this.disc1PpnRp = disc1PpnRp;
	}

	public Double getDisc1RpAfterPpn() {
		return disc1RpAfterPpn;
	}

	public void setDisc1RpAfterPpn(Double disc1RpAfterPpn) {
		this.disc1RpAfterPpn = disc1RpAfterPpn;
	}

	public Double getDisc1RpAfterPpnUom1() {
		return disc1RpAfterPpnUom1;
	}

	public void setDisc1RpAfterPpnUom1(Double disc1RpAfterPpnUom1) {
		this.disc1RpAfterPpnUom1 = disc1RpAfterPpnUom1;
	}

	public Double getDisc1RpAfterPpnUom2() {
		return disc1RpAfterPpnUom2;
	}

	public void setDisc1RpAfterPpnUom2(Double disc1RpAfterPpnUom2) {
		this.disc1RpAfterPpnUom2 = disc1RpAfterPpnUom2;
	}

	public Double getDisc1RpAfterPpnUom3() {
		return disc1RpAfterPpnUom3;
	}

	public void setDisc1RpAfterPpnUom3(Double disc1RpAfterPpnUom3) {
		this.disc1RpAfterPpnUom3 = disc1RpAfterPpnUom3;
	}

	public Double getDisc1RpAfterPpnUom4() {
		return disc1RpAfterPpnUom4;
	}

	public void setDisc1RpAfterPpnUom4(Double disc1RpAfterPpnUom4) {
		this.disc1RpAfterPpnUom4 = disc1RpAfterPpnUom4;
	}

	public Double getDisc2() {
		return disc2;
	}

	public void setDisc2(Double disc2) {
		this.disc2 = disc2;
	}

	public Double getDisc2Rp() {
		return disc2Rp;
	}

	public void setDisc2Rp(Double disc2Rp) {
		this.disc2Rp = disc2Rp;
	}

	public Double getDisc2PpnRp() {
		return disc2PpnRp;
	}

	public void setDisc2PpnRp(Double disc2PpnRp) {
		this.disc2PpnRp = disc2PpnRp;
	}

	public Double getDisc2RpAfterPpn() {
		return disc2RpAfterPpn;
	}

	public void setDisc2RpAfterPpn(Double disc2RpAfterPpn) {
		this.disc2RpAfterPpn = disc2RpAfterPpn;
	}

	public Double getDisc2RpAfterPpnUom1() {
		return disc2RpAfterPpnUom1;
	}

	public void setDisc2RpAfterPpnUom1(Double disc2RpAfterPpnUom1) {
		this.disc2RpAfterPpnUom1 = disc2RpAfterPpnUom1;
	}

	public Double getDisc2RpAfterPpnUom2() {
		return disc2RpAfterPpnUom2;
	}

	public void setDisc2RpAfterPpnUom2(Double disc2RpAfterPpnUom2) {
		this.disc2RpAfterPpnUom2 = disc2RpAfterPpnUom2;
	}

	public Double getDisc2RpAfterPpnUom3() {
		return disc2RpAfterPpnUom3;
	}

	public void setDisc2RpAfterPpnUom3(Double disc2RpAfterPpnUom3) {
		this.disc2RpAfterPpnUom3 = disc2RpAfterPpnUom3;
	}

	public Double getDisc2RpAfterPpnUom4() {
		return disc2RpAfterPpnUom4;
	}

	public void setDisc2RpAfterPpnUom4(Double disc2RpAfterPpnUom4) {
		this.disc2RpAfterPpnUom4 = disc2RpAfterPpnUom4;
	}

	public Double getDisc3() {
		return disc3;
	}

	public void setDisc3(Double disc3) {
		this.disc3 = disc3;
	}

	public Double getDisc3Rp() {
		return disc3Rp;
	}

	public void setDisc3Rp(Double disc3Rp) {
		this.disc3Rp = disc3Rp;
	}

	public Double getDisc3PpnRp() {
		return disc3PpnRp;
	}

	public void setDisc3PpnRp(Double disc3PpnRp) {
		this.disc3PpnRp = disc3PpnRp;
	}

	public Double getDisc3RpAfterPpn() {
		return disc3RpAfterPpn;
	}

	public void setDisc3RpAfterPpn(Double disc3RpAfterPpn) {
		this.disc3RpAfterPpn = disc3RpAfterPpn;
	}

	public Double getDisc3RpAfterPpnUom1() {
		return disc3RpAfterPpnUom1;
	}

	public void setDisc3RpAfterPpnUom1(Double disc3RpAfterPpnUom1) {
		this.disc3RpAfterPpnUom1 = disc3RpAfterPpnUom1;
	}

	public Double getDisc3RpAfterPpnUom2() {
		return disc3RpAfterPpnUom2;
	}

	public void setDisc3RpAfterPpnUom2(Double disc3RpAfterPpnUom2) {
		this.disc3RpAfterPpnUom2 = disc3RpAfterPpnUom2;
	}

	public Double getDisc3RpAfterPpnUom3() {
		return disc3RpAfterPpnUom3;
	}

	public void setDisc3RpAfterPpnUom3(Double disc3RpAfterPpnUom3) {
		this.disc3RpAfterPpnUom3 = disc3RpAfterPpnUom3;
	}

	public Double getDisc3RpAfterPpnUom4() {
		return disc3RpAfterPpnUom4;
	}

	public void setDisc3RpAfterPpnUom4(Double disc3RpAfterPpnUom4) {
		this.disc3RpAfterPpnUom4 = disc3RpAfterPpnUom4;
	}

	public Double getSubtotalAfterDisc123Rp() {
		return subtotalAfterDisc123Rp;
	}

	public void setSubtotalAfterDisc123Rp(Double subtotalAfterDisc123Rp) {
		this.subtotalAfterDisc123Rp = subtotalAfterDisc123Rp;
	}

	public Double getSubtotalAfterDisc123PpnRp() {
		return subtotalAfterDisc123PpnRp;
	}

	public void setSubtotalAfterDisc123PpnRp(Double subtotalAfterDisc123PpnRp) {
		this.subtotalAfterDisc123PpnRp = subtotalAfterDisc123PpnRp;
	}

	public Double getSubtotalAfterDisc123RpAfterPpn() {
		return subtotalAfterDisc123RpAfterPpn;
	}

	public void setSubtotalAfterDisc123RpAfterPpn(Double subtotalAfterDisc123RpAfterPpn) {
		this.subtotalAfterDisc123RpAfterPpn = subtotalAfterDisc123RpAfterPpn;
	}

	public Double getDisc1Plus() {
		return disc1Plus;
	}

	public void setDisc1Plus(Double disc1Plus) {
		this.disc1Plus = disc1Plus;
	}

	public Double getDisc1PlusRp() {
		return disc1PlusRp;
	}

	public void setDisc1PlusRp(Double disc1PlusRp) {
		this.disc1PlusRp = disc1PlusRp;
	}

	public Double getDisc1PlusPpnRp() {
		return disc1PlusPpnRp;
	}

	public void setDisc1PlusPpnRp(Double disc1PlusPpnRp) {
		this.disc1PlusPpnRp = disc1PlusPpnRp;
	}

	public Double getDisc1PlusRpAfterPpn() {
		return disc1PlusRpAfterPpn;
	}

	public void setDisc1PlusRpAfterPpn(Double disc1PlusRpAfterPpn) {
		this.disc1PlusRpAfterPpn = disc1PlusRpAfterPpn;
	}

	public Double getDisc1PlusRpAfterPpnUom1() {
		return disc1PlusRpAfterPpnUom1;
	}

	public void setDisc1PlusRpAfterPpnUom1(Double disc1PlusRpAfterPpnUom1) {
		this.disc1PlusRpAfterPpnUom1 = disc1PlusRpAfterPpnUom1;
	}

	public Double getDisc1PlusRpAfterPpnUom2() {
		return disc1PlusRpAfterPpnUom2;
	}

	public void setDisc1PlusRpAfterPpnUom2(Double disc1PlusRpAfterPpnUom2) {
		this.disc1PlusRpAfterPpnUom2 = disc1PlusRpAfterPpnUom2;
	}

	public Double getDisc1PlusRpAfterPpnUom3() {
		return disc1PlusRpAfterPpnUom3;
	}

	public void setDisc1PlusRpAfterPpnUom3(Double disc1PlusRpAfterPpnUom3) {
		this.disc1PlusRpAfterPpnUom3 = disc1PlusRpAfterPpnUom3;
	}

	public Double getDisc1PlusRpAfterPpnUom4() {
		return disc1PlusRpAfterPpnUom4;
	}

	public void setDisc1PlusRpAfterPpnUom4(Double disc1PlusRpAfterPpnUom4) {
		this.disc1PlusRpAfterPpnUom4 = disc1PlusRpAfterPpnUom4;
	}

	public Double getSubtotalAfterDisc1PlusRp() {
		return subtotalAfterDisc1PlusRp;
	}

	public void setSubtotalAfterDisc1PlusRp(Double subtotalAfterDisc1PlusRp) {
		this.subtotalAfterDisc1PlusRp = subtotalAfterDisc1PlusRp;
	}

	public Double getSubtotalAfterDisc1PlusPpnRp() {
		return subtotalAfterDisc1PlusPpnRp;
	}

	public void setSubtotalAfterDisc1PlusPpnRp(Double subtotalAfterDisc1PlusPpnRp) {
		this.subtotalAfterDisc1PlusPpnRp = subtotalAfterDisc1PlusPpnRp;
	}

	public Double getSubtotalAfterDisc1PlusRpAfterPpn() {
		return subtotalAfterDisc1PlusRpAfterPpn;
	}

	public void setSubtotalAfterDisc1PlusRpAfterPpn(Double subtotalAfterDisc1PlusRpAfterPpn) {
		this.subtotalAfterDisc1PlusRpAfterPpn = subtotalAfterDisc1PlusRpAfterPpn;
	}

	public Double getDisc2Plus() {
		return disc2Plus;
	}

	public void setDisc2Plus(Double disc2Plus) {
		this.disc2Plus = disc2Plus;
	}

	public Double getDisc2PlusRp() {
		return disc2PlusRp;
	}

	public void setDisc2PlusRp(Double disc2PlusRp) {
		this.disc2PlusRp = disc2PlusRp;
	}

	public Double getDisc2PlusPpnRp() {
		return disc2PlusPpnRp;
	}

	public void setDisc2PlusPpnRp(Double disc2PlusPpnRp) {
		this.disc2PlusPpnRp = disc2PlusPpnRp;
	}

	public Double getDisc2PlusRpAfterPpn() {
		return disc2PlusRpAfterPpn;
	}

	public void setDisc2PlusRpAfterPpn(Double disc2PlusRpAfterPpn) {
		this.disc2PlusRpAfterPpn = disc2PlusRpAfterPpn;
	}

	public Double getDisc2PlusRpAfterPpnUom1() {
		return disc2PlusRpAfterPpnUom1;
	}

	public void setDisc2PlusRpAfterPpnUom1(Double disc2PlusRpAfterPpnUom1) {
		this.disc2PlusRpAfterPpnUom1 = disc2PlusRpAfterPpnUom1;
	}

	public Double getDisc2PlusRpAfterPpnUom2() {
		return disc2PlusRpAfterPpnUom2;
	}

	public void setDisc2PlusRpAfterPpnUom2(Double disc2PlusRpAfterPpnUom2) {
		this.disc2PlusRpAfterPpnUom2 = disc2PlusRpAfterPpnUom2;
	}

	public Double getDisc2PlusRpAfterPpnUom3() {
		return disc2PlusRpAfterPpnUom3;
	}

	public void setDisc2PlusRpAfterPpnUom3(Double disc2PlusRpAfterPpnUom3) {
		this.disc2PlusRpAfterPpnUom3 = disc2PlusRpAfterPpnUom3;
	}

	public Double getDisc2PlusRpAfterPpnUom4() {
		return disc2PlusRpAfterPpnUom4;
	}

	public void setDisc2PlusRpAfterPpnUom4(Double disc2PlusRpAfterPpnUom4) {
		this.disc2PlusRpAfterPpnUom4 = disc2PlusRpAfterPpnUom4;
	}

	public Double getSubtotalAfterDisc2PlusRp() {
		return subtotalAfterDisc2PlusRp;
	}

	public void setSubtotalAfterDisc2PlusRp(Double subtotalAfterDisc2PlusRp) {
		this.subtotalAfterDisc2PlusRp = subtotalAfterDisc2PlusRp;
	}

	public Double getSubtotalAfterDisc2PlusPpnRp() {
		return subtotalAfterDisc2PlusPpnRp;
	}

	public void setSubtotalAfterDisc2PlusPpnRp(Double subtotalAfterDisc2PlusPpnRp) {
		this.subtotalAfterDisc2PlusPpnRp = subtotalAfterDisc2PlusPpnRp;
	}

	public Double getSubtotalAfterDisc2PlusRpAfterPpn() {
		return subtotalAfterDisc2PlusRpAfterPpn;
	}

	public void setSubtotalAfterDisc2PlusRpAfterPpn(Double subtotalAfterDisc2PlusRpAfterPpn) {
		this.subtotalAfterDisc2PlusRpAfterPpn = subtotalAfterDisc2PlusRpAfterPpn;
	}

	public Double getDiscNota1() {
		return discNota1;
	}

	public void setDiscNota1(Double discNota1) {
		this.discNota1 = discNota1;
	}

	public Double getDiscNota1Rp() {
		return discNota1Rp;
	}

	public void setDiscNota1Rp(Double discNota1Rp) {
		this.discNota1Rp = discNota1Rp;
	}

	public Double getDiscNota1PpnRp() {
		return discNota1PpnRp;
	}

	public void setDiscNota1PpnRp(Double discNota1PpnRp) {
		this.discNota1PpnRp = discNota1PpnRp;
	}

	public Double getDiscNota1RpAfterPpn() {
		return discNota1RpAfterPpn;
	}

	public void setDiscNota1RpAfterPpn(Double discNota1RpAfterPpn) {
		this.discNota1RpAfterPpn = discNota1RpAfterPpn;
	}

	public Double getSubtotalAfterDiscNota1Rp() {
		return subtotalAfterDiscNota1Rp;
	}

	public void setSubtotalAfterDiscNota1Rp(Double subtotalAfterDiscNota1Rp) {
		this.subtotalAfterDiscNota1Rp = subtotalAfterDiscNota1Rp;
	}

	public Double getSubtotalAfterDiscNota1PpnRp() {
		return subtotalAfterDiscNota1PpnRp;
	}

	public void setSubtotalAfterDiscNota1PpnRp(Double subtotalAfterDiscNota1PpnRp) {
		this.subtotalAfterDiscNota1PpnRp = subtotalAfterDiscNota1PpnRp;
	}

	public Double getSubtotalAfterDiscNota1RpAfterPpn() {
		return subtotalAfterDiscNota1RpAfterPpn;
	}

	public void setSubtotalAfterDiscNota1RpAfterPpn(Double subtotalAfterDiscNota1RpAfterPpn) {
		this.subtotalAfterDiscNota1RpAfterPpn = subtotalAfterDiscNota1RpAfterPpn;
	}

	public Double getDiscNota2() {
		return discNota2;
	}

	public void setDiscNota2(Double discNota2) {
		this.discNota2 = discNota2;
	}

	public Double getDiscNota2Rp() {
		return discNota2Rp;
	}

	public void setDiscNota2Rp(Double discNota2Rp) {
		this.discNota2Rp = discNota2Rp;
	}

	public Double getDiscNota2PpnRp() {
		return discNota2PpnRp;
	}

	public void setDiscNota2PpnRp(Double discNota2PpnRp) {
		this.discNota2PpnRp = discNota2PpnRp;
	}

	public Double getDiscNota2RpAfterPpn() {
		return discNota2RpAfterPpn;
	}

	public void setDiscNota2RpAfterPpn(Double discNota2RpAfterPpn) {
		this.discNota2RpAfterPpn = discNota2RpAfterPpn;
	}

	public Double getSubtotalAfterDiscNota2Rp() {
		return subtotalAfterDiscNota2Rp;
	}

	public void setSubtotalAfterDiscNota2Rp(Double subtotalAfterDiscNota2Rp) {
		this.subtotalAfterDiscNota2Rp = subtotalAfterDiscNota2Rp;
	}

	public Double getSubtotalAfterDiscNota2PpnRp() {
		return subtotalAfterDiscNota2PpnRp;
	}

	public void setSubtotalAfterDiscNota2PpnRp(Double subtotalAfterDiscNota2PpnRp) {
		this.subtotalAfterDiscNota2PpnRp = subtotalAfterDiscNota2PpnRp;
	}

	public Double getSubtotalAfterDiscNota2RpAfterPpn() {
		return subtotalAfterDiscNota2RpAfterPpn;
	}

	public void setSubtotalAfterDiscNota2RpAfterPpn(Double subtotalAfterDiscNota2RpAfterPpn) {
		this.subtotalAfterDiscNota2RpAfterPpn = subtotalAfterDiscNota2RpAfterPpn;
	}

	public Double getDiscNotaPlus_FG() {
		return discNotaPlus_FG;
	}

	public void setDiscNotaPlus_FG(Double discNotaPlus_FG) {
		this.discNotaPlus_FG = discNotaPlus_FG;
	}

	public Double getDiscNotaPlusRp_FG() {
		return discNotaPlusRp_FG;
	}

	public void setDiscNotaPlusRp_FG(Double discNotaPlusRp_FG) {
		this.discNotaPlusRp_FG = discNotaPlusRp_FG;
	}

	public Double getDiscNotaPlusPpnRp_FG() {
		return discNotaPlusPpnRp_FG;
	}

	public void setDiscNotaPlusPpnRp_FG(Double discNotaPlusPpnRp_FG) {
		this.discNotaPlusPpnRp_FG = discNotaPlusPpnRp_FG;
	}

	public Double getDiscNotaPlusRpAfterPpn_FG() {
		return discNotaPlusRpAfterPpn_FG;
	}

	public void setDiscNotaPlusRpAfterPpn_FG(Double discNotaPlusRpAfterPpn_FG) {
		this.discNotaPlusRpAfterPpn_FG = discNotaPlusRpAfterPpn_FG;
	}

	public Double getSubtotalAfterDiscNotaPlusRp_FG() {
		return subtotalAfterDiscNotaPlusRp_FG;
	}

	public void setSubtotalAfterDiscNotaPlusRp_FG(Double subtotalAfterDiscNotaPlusRp_FG) {
		this.subtotalAfterDiscNotaPlusRp_FG = subtotalAfterDiscNotaPlusRp_FG;
	}

	public Double getSubtotalAfterDiscNotaPlusPpnRp_FG() {
		return subtotalAfterDiscNotaPlusPpnRp_FG;
	}

	public void setSubtotalAfterDiscNotaPlusPpnRp_FG(Double subtotalAfterDiscNotaPlusPpnRp_FG) {
		this.subtotalAfterDiscNotaPlusPpnRp_FG = subtotalAfterDiscNotaPlusPpnRp_FG;
	}

	public Double getSubtotalAfterDiscNotaPlusRpAfterPpn_FG() {
		return subtotalAfterDiscNotaPlusRpAfterPpn_FG;
	}

	public void setSubtotalAfterDiscNotaPlusRpAfterPpn_FG(Double subtotalAfterDiscNotaPlusRpAfterPpn_FG) {
		this.subtotalAfterDiscNotaPlusRpAfterPpn_FG = subtotalAfterDiscNotaPlusRpAfterPpn_FG;
	}

	public String getTempString() {
		return tempString;
	}

	public void setTempString(String tempString) {
		this.tempString = tempString;
	}

	public Integer getTempInt() {
		return tempInt;
	}

	public void setTempInt(Integer tempInt) {
		this.tempInt = tempInt;
	}

	public Double getTempDouble1() {
		return tempDouble1;
	}

	public void setTempDouble1(Double tempDouble1) {
		this.tempDouble1 = tempDouble1;
	}

	public Double getTempDouble2() {
		return tempDouble2;
	}

	public void setTempDouble2(Double tempDouble2) {
		this.tempDouble2 = tempDouble2;
	}

	public Integer getTempDouble31() {
		return tempDouble31;
	}

	public void setTempDouble31(Integer tempDouble31) {
		this.tempDouble31 = tempDouble31;
	}

	public Integer getFtSaleshBean() {
		return ftSaleshBean;
	}

	public void setFtSaleshBean(Integer ftSaleshBean) {
		this.ftSaleshBean = ftSaleshBean;
	}

	public Integer getFmaterialBean() {
		return fmaterialBean;
	}

	public void setFmaterialBean(Integer fmaterialBean) {
		this.fmaterialBean = fmaterialBean;
	}
}