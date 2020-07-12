package com.erp.distribution.sfa.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="ftOpnamedItems")
public class FtOpnamedItems {


	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	private Integer noUrut=0;
	
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
	
    @Ignore
	private Double qty1=0.0;
    @Ignore
	private Double qty2=0.0;
    @Ignore
	private Double qty3=0.0;
    @Ignore
	private Double qty4=0.0;

//	@Column(name="QTY", length=9)
//	private Integer qty=0.0; //Qty Fisik
	private Double qty=0.0; //Qty Fisik

//	@Column(name="QTY_TEORI", length=9)
//	private Integer qtyTeori=0.0;	//Qty pada Komputer
	private Double qtyTeori=0.0;	//Qty pada Komputer
	

	
	
    @Ignore
	private Double qty1_Adjust=0.0;
    @Ignore
	private Double qty2_Adjust=0.0;
    @Ignore
	private Double qty3_Adjust=0.0;
    @Ignore
	private Double qty4_Adjust=0.0;
	
//	private Integer qtyAdjust=0.0; //Qty setelah dihitung dan dilakukan penyesuaian
	private Double qtyAdjust=0.0; //Qty setelah dihitung dan dilakukan penyesuaian

	private boolean visible=true;
	
	//Subtotal sebelum disc
    @Ignore
	private Double subtotalRp=0.0;
    @Ignore
	private Double subtotalTeoriRp=0.0;
    @Ignore
	private Double subtotalAdjustRp=0.0;
	
	
    @Ignore
	private String tempString="";
    @Ignore
	private Integer tempInt=0;
    @Ignore
	private Double tempFloat=0.0;
    @Ignore
	private Double tempDouble31=0.0;
	
//	@ManyToOne
//	@JoinColumn(name="ftOpnamehBean", referencedColumnName="refno")
//	private FtOpnameh ftOpnamehBean;
	private Integer ftOpnamehBean = 0;

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

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getQtyTeori() {
		return qtyTeori;
	}

	public void setQtyTeori(Double qtyTeori) {
		this.qtyTeori = qtyTeori;
	}

	public Double getQty1_Adjust() {
		return qty1_Adjust;
	}

	public void setQty1_Adjust(Double qty1_Adjust) {
		this.qty1_Adjust = qty1_Adjust;
	}

	public Double getQty2_Adjust() {
		return qty2_Adjust;
	}

	public void setQty2_Adjust(Double qty2_Adjust) {
		this.qty2_Adjust = qty2_Adjust;
	}

	public Double getQty3_Adjust() {
		return qty3_Adjust;
	}

	public void setQty3_Adjust(Double qty3_Adjust) {
		this.qty3_Adjust = qty3_Adjust;
	}

	public Double getQty4_Adjust() {
		return qty4_Adjust;
	}

	public void setQty4_Adjust(Double qty4_Adjust) {
		this.qty4_Adjust = qty4_Adjust;
	}

	public Double getQtyAdjust() {
		return qtyAdjust;
	}

	public void setQtyAdjust(Double qtyAdjust) {
		this.qtyAdjust = qtyAdjust;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Double getSubtotalRp() {
		return subtotalRp;
	}

	public void setSubtotalRp(Double subtotalRp) {
		this.subtotalRp = subtotalRp;
	}

	public Double getSubtotalTeoriRp() {
		return subtotalTeoriRp;
	}

	public void setSubtotalTeoriRp(Double subtotalTeoriRp) {
		this.subtotalTeoriRp = subtotalTeoriRp;
	}

	public Double getSubtotalAdjustRp() {
		return subtotalAdjustRp;
	}

	public void setSubtotalAdjustRp(Double subtotalAdjustRp) {
		this.subtotalAdjustRp = subtotalAdjustRp;
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

	public Integer getFtOpnamehBean() {
		return ftOpnamehBean;
	}

	public void setFtOpnamehBean(Integer ftOpnamehBean) {
		this.ftOpnamehBean = ftOpnamehBean;
	}

	public Integer getFmaterialBean() {
		return fmaterialBean;
	}

	public void setFmaterialBean(Integer fmaterialBean) {
		this.fmaterialBean = fmaterialBean;
	}
}