package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


 
 
@Entity(tableName="fStock")
public class FStock {

	@PrimaryKey(autoGenerate = true)
	private long refno=0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;
	
	/*
	 * HARI YANG DIPAKAI JUGA PADA HPP
	 */
	private Date stockDate;
		
//	@Column(name="SALDO_AWAL")
//	private Integer saldoAwal =0.0;
	private Double saldoAwal =0.0;
	/*
	 * NEXT AKAN DIBIKIN TRANSIEN
	 */
//	@Column(name="QTY_IN")
//	private Integer qtyIn =0.0;
//	@Column(name="QTY_OUT")
//	private Integer qtyOut =0.0;
	private Double qtyIn =0.0;
	private Double qtyOut =0.0;

	
//	@Column(name="QTY_ADJUST", length=10)	
//	private Integer qtyAdjust =0.0;
//	@Column(name="SALDO_AKHIR")
//	private Integer saldoAkhir =0.0;
	private Double qtyAdjust =0.0;
	private Double saldoAkhir =0.0;

	
	/*
	 * ORDERED STOCK
	 */
//	@Column(name="QTY_HOLD", length=9)	
//	private Integer qtyHold =0.0;
	private Double qtyHold =0.0;
	
	/*
	 * HARGA BELI NET TERAKHIR
	 * TAPI HARGA BELI TERAKHIR INI MENGGUNAKAN PPN (after PPN)
	 */
	private Double closingPprice2_AfterPpn =0.0;
		
	
//	@ManyToOne
//	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID")
//	private FWarehouse fwarehouseBean;
	private Integer fwarehouseBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
//	private FMaterial fmaterialBean;
	private Integer fmaterialBean = 0;




	public long getRefno() {
		return refno;
	}

	public void setRefno(long refno) {
		this.refno = refno;
	}

	public long getSourceID() {
		return sourceID;
	}

	public void setSourceID(long sourceID) {
		this.sourceID = sourceID;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public Double getSaldoAwal() {
		return saldoAwal;
	}

	public void setSaldoAwal(Double saldoAwal) {
		this.saldoAwal = saldoAwal;
	}

	public Double getQtyIn() {
		return qtyIn;
	}

	public void setQtyIn(Double qtyIn) {
		this.qtyIn = qtyIn;
	}

	public Double getQtyOut() {
		return qtyOut;
	}

	public void setQtyOut(Double qtyOut) {
		this.qtyOut = qtyOut;
	}

	public Double getQtyAdjust() {
		return qtyAdjust;
	}

	public void setQtyAdjust(Double qtyAdjust) {
		this.qtyAdjust = qtyAdjust;
	}

	public Double getSaldoAkhir() {
		return saldoAkhir;
	}

	public void setSaldoAkhir(Double saldoAkhir) {
		this.saldoAkhir = saldoAkhir;
	}

	public Double getQtyHold() {
		return qtyHold;
	}

	public void setQtyHold(Double qtyHold) {
		this.qtyHold = qtyHold;
	}

	public Double getClosingPprice2_AfterPpn() {
		return closingPprice2_AfterPpn;
	}

	public void setClosingPprice2_AfterPpn(Double closingPprice2_AfterPpn) {
		this.closingPprice2_AfterPpn = closingPprice2_AfterPpn;
	}

	public Integer getFwarehouseBean() {
		return fwarehouseBean;
	}

	public void setFwarehouseBean(Integer fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public Integer getFmaterialBean() {
		return fmaterialBean;
	}

	public void setFmaterialBean(Integer fmaterialBean) {
		this.fmaterialBean = fmaterialBean;
	}
}