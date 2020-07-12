package com.erp.distribution.sfa.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="ftStocktransferdItems")
public class FtStockTransferdItems {

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut =0;

	/*
	 * Dasar harga total
	 */
	private Double pprice=0.0; //Harga disimpan dalam Satuan Besar Sebelum Ppn
	
	@Ignore
	private Double ppriceUom2=0.0;
	@Ignore
	private Double ppriceUom3=0.0;
	@Ignore
	private Double ppriceUom4=0.0;
	
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
	
//	@Column(name="QTY", length=9)
//	private Integer qty=0.0;
	private Double qty=0.0;
	
	/*
	 * Qty Kembali digunakan jika barang kurang atau Rusak
	 */
//	@Column(name="QTY_KEMBALI", length=9) //Kembali dari pengiriman
//	private Integer qtyKembali = 0;	
//	@Ignore
//	private Integer qtyNET=0.0;
	private Double qtyKembali =0.0;
	@Ignore
	private Double qtyNET=0.0;
	
	//Subtotal sebelum disc
	@Ignore
	private Double subtotalRp=0.0;
	
	@Ignore
	private String tempString="";
	@Ignore
	private Integer tempInt=0;
	@Ignore
	private Double tempFloat=0.0;
	@Ignore
	private Double tempDouble31=0.0;
	
	
//	@ManyToOne
//	@JoinColumn(name="ftStockTransferhBean", referencedColumnName="refno")
//	private FtStockTransferh ftStockTransferhBean;
	private Integer ftStockTransferhBean = 0;

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

	public Double getPpriceUom2() {
		return ppriceUom2;
	}

	public void setPpriceUom2(Double ppriceUom2) {
		this.ppriceUom2 = ppriceUom2;
	}

	public Double getPpriceUom3() {
		return ppriceUom3;
	}

	public void setPpriceUom3(Double ppriceUom3) {
		this.ppriceUom3 = ppriceUom3;
	}

	public Double getPpriceUom4() {
		return ppriceUom4;
	}

	public void setPpriceUom4(Double ppriceUom4) {
		this.ppriceUom4 = ppriceUom4;
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

	public Double getSubtotalRp() {
		return subtotalRp;
	}

	public void setSubtotalRp(Double subtotalRp) {
		this.subtotalRp = subtotalRp;
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

	public Double getTempFloat() {
		return tempFloat;
	}

	public void setTempFloat(Double tempFloat) {
		this.tempFloat = tempFloat;
	}

	public Double getTempDouble31() {
		return tempDouble31;
	}

	public void setTempDouble31(Double tempDouble31) {
		this.tempDouble31 = tempDouble31;
	}

	public Integer getFtStockTransferhBean() {
		return ftStockTransferhBean;
	}

	public void setFtStockTransferhBean(Integer ftStockTransferhBean) {
		this.ftStockTransferhBean = ftStockTransferhBean;
	}

	public Integer getFmaterialBean() {
		return fmaterialBean;
	}

	public void setFmaterialBean(Integer fmaterialBean) {
		this.fmaterialBean = fmaterialBean;
	}
}