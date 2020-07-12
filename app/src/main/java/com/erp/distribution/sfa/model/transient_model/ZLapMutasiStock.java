package com.erp.distribution.sfa.model.transient_model;

import java.util.Date;



public class ZLapMutasiStock {

	private long ID=0;
	
	private double noUrut = 0;
	private double idProduct = 0;
	
	double hargaBeli_Pcs = 0;
	double hargaJual_Pcs = 0;
	
	String string1="";
	String string2="";
	String string3="";
	String string4="";
	String string5="";

	String string6="";
	String string7="";
	

	String grup1="";

	String grup2="";

	String grup3="";

	String wareHouse ="";
	String vendor ="";
	

	String pcode="";

	String pname="";

	String convfactString = "";
	double convfact1=0;
	double convfact2=0;
	double convfact3=0;
	

	Date stockdate;
	
	//**SALDO AWAL

	double saldoAwalPcs=0;
	double saldoAwal_uom1=0;
	double saldoAwal_uom2=0;
	double saldoAwal_uom3=0;
	double saldoAwal_uom4=0;
	String saldoAwalString_uom1 ="";
	String saldoAwalString_uom2 ="";
	String saldoAwalString_uom3 ="";
	String saldoAwalString_uom4 ="";
	String saldoAwalString="";
	
	double saldoAwalNilaiBeli=0.00001;
	double saldoAwalNilaiJual=0.00001;

	//**PENJUALAN(-)
	double penjualanPcs=0;
	double penjualan_uom1 =0;
	double penjualan_uom2 =0;
	double penjualan_uom3 =0;
	double penjualan_uom4 =0;
	double penjualanNilaiBeli=0.00001;
	double penjualanNilaiJual=0.00001;
	String penjualanString ="";
	
	//**RETUR PENJUALAN(+)
	double returPenjualanPcs=0;
	double returPenjualan_uom1=0;
	double returPenjualan_uom2=0;
	double returPenjualan_uom3=0;
	double returPenjualan_uom4=0;
	double returPenjualanNilaiBeli=0.00001;
	double returPenjualanNilaiJual=0.00001;
	String returPenjualanString="";
	

	//**PEMBELIAN (+)
	double pembelianPcs=0;
	double pembelian_uom1=0;
	double pembelian_uom2=0;
	double pembelian_uom3=0;
	double pembelian_uom4=0;
	double pembelianNilaiBeli=0.00001;
	double pembelianNilaiJual=0.00001;
	String pembelianString="";


	//**RETUR PEMBELIAN (-)
	double returPembelianPcs=0;
	double returPembelian_uom1=0;
	double returPembelian_uom2=0;
	double returPembelian_uom3=0;
	double returPembelian_uom4=0;
	double returPembelianNilaiBeli=0.00001;
	double returPembelianNilaiJual=0.00001;
	String returPembelianString="";
	
	//**TRANSFER IN (+)
	double transferInPcs=0;
	double transferIn_uom1=0;
	double transferIn_uom2=0;
	double transferIn_uom3=0;
	double transferIn_uom4=0;
	double transferInNilaiBeli=0.00001;
	double transferInNilaiJual=0.00001;
	String transferInString="";

	//**TRANSFER OUT (+)
	double transferOutPcs=0;
	double transferOut_uom1=0;
	double transferOut_uom2=0;
	double transferOut_uom3=0;
	double transferOut_uom4=0;
	double transferOutNilaiBeli=0.00001;
	double transferOutNilaiJual=0.00001;
	String transferOutString="";
	
	//**PENYESUAIAN/STOCK OPNAME (+/-)
	double penyesuaianPcs=0;
	double penyesuaian_uom1=0;
	double penyesuaian_uom2=0;
	double penyesuaian_uom3=0;
	double penyesuaian_uom4=0;
	double penyesuaianNilaiBeli=0.00001;
	double penyesuaianNilaiJual=0.00001;
	String penyesuaianString="";
	
	
	//**SALDO AKHIR
	double saldoAkhirPcs=0;
	double saldoAkhir_uom1=0;
	double saldoAkhir_uom2=0;
	double saldoAkhir_uom3=0;
	double saldoAkhir_uom4=0;
	String saldoAkhirString_uom1 ="";
	String saldoAkhirString_uom2 ="";
	String saldoAkhirString_uom3 ="";
	String saldoAkhirString_uom4 ="";
	
	double saldoAkhirNilaiBeli=0.00001;
	double saldoAkhirNilaiJual=0.00001;
	String saldoAkhirString="";
	String saldoAkhirAvailableString="";
	
	/*
	 * Untuk laporan Posisi Stok pada Gudang
	 */
	String saldoAkhirGudang1Desc="";
	String saldoAkhirGudang2Desc="";
	String saldoAkhirGudang3Desc="";
	String saldoAkhirGudang4Desc="";
	String saldoAkhirGudang5Desc="";
	String saldoAkhirGudang1String="";
	String saldoAkhirGudang2String="";
	String saldoAkhirGudang3String="";
	String saldoAkhirGudang4String="";
	String saldoAkhirGudang5String="";

	double saldoAkhirGudang1_Double1 = 0;
	double saldoAkhirGudang1_Double2 = 0;
	double saldoAkhirGudang2_Double1 = 0;
	double saldoAkhirGudang2_Double2 = 0;
	double saldoAkhirGudang3_Double1 = 0;
	double saldoAkhirGudang3_Double2 = 0;
	double saldoAkhirGudang4_Double1 = 0;
	double saldoAkhirGudang4_Double2 = 0;
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getGrup1() {
		return grup1;
	}
	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}
	public String getGrup2() {
		return grup2;
	}
	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}
	public String getGrup3() {
		return grup3;
	}
	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getConvfact1() {
		return convfact1;
	}
	public void setConvfact1(double convfact1) {
		this.convfact1 = convfact1;
	}
	public double getConvfact2() {
		return convfact2;
	}
	public void setConvfact2(double convfact2) {
		this.convfact2 = convfact2;
	}
	public double getConvfact3() {
		return convfact3;
	}
	public void setConvfact3(double convfact3) {
		this.convfact3 = convfact3;
	}
	public Date getStockdate() {
		return stockdate;
	}
	public void setStockdate(Date stockdate) {
		this.stockdate = stockdate;
	}
	public double getSaldoAwalPcs() {
		return saldoAwalPcs;
	}
	public void setSaldoAwalPcs(double saldoAwalPcs) {
		this.saldoAwalPcs = saldoAwalPcs;
	}
	public double getSaldoAwal_uom1() {
		return saldoAwal_uom1;
	}
	public void setSaldoAwal_uom1(double saldoAwal_uom1) {
		this.saldoAwal_uom1 = saldoAwal_uom1;
	}
	public double getSaldoAwal_uom2() {
		return saldoAwal_uom2;
	}
	public void setSaldoAwal_uom2(double saldoAwal_uom2) {
		this.saldoAwal_uom2 = saldoAwal_uom2;
	}
	public double getSaldoAwal_uom3() {
		return saldoAwal_uom3;
	}
	public void setSaldoAwal_uom3(double saldoAwal_uom3) {
		this.saldoAwal_uom3 = saldoAwal_uom3;
	}
	public double getSaldoAwal_uom4() {
		return saldoAwal_uom4;
	}
	public void setSaldoAwal_uom4(double saldoAwal_uom4) {
		this.saldoAwal_uom4 = saldoAwal_uom4;
	}
	public String getSaldoAwalString() {
		return saldoAwalString;
	}
	public void setSaldoAwalString(String saldoAwalString) {
		this.saldoAwalString = saldoAwalString;
	}
	public double getSaldoAwalNilaiBeli() {
		return saldoAwalNilaiBeli;
	}
	public void setSaldoAwalNilaiBeli(double saldoAwalNilaiBeli) {
		this.saldoAwalNilaiBeli = saldoAwalNilaiBeli;
	}
	public double getSaldoAwalNilaiJual() {
		return saldoAwalNilaiJual;
	}
	public void setSaldoAwalNilaiJual(double saldoAwalNilaiJual) {
		this.saldoAwalNilaiJual = saldoAwalNilaiJual;
	}
	public double getPenjualanPcs() {
		return penjualanPcs;
	}
	public void setPenjualanPcs(double penjualanPcs) {
		this.penjualanPcs = penjualanPcs;
	}
	public double getPenjualan_uom1() {
		return penjualan_uom1;
	}
	public void setPenjualan_uom1(double penjualan_uom1) {
		this.penjualan_uom1 = penjualan_uom1;
	}
	public double getPenjualan_uom2() {
		return penjualan_uom2;
	}
	public void setPenjualan_uom2(double penjualan_uom2) {
		this.penjualan_uom2 = penjualan_uom2;
	}
	public double getPenjualan_uom3() {
		return penjualan_uom3;
	}
	public void setPenjualan_uom3(double penjualan_uom3) {
		this.penjualan_uom3 = penjualan_uom3;
	}
	public double getPenjualan_uom4() {
		return penjualan_uom4;
	}
	public void setPenjualan_uom4(double penjualan_uom4) {
		this.penjualan_uom4 = penjualan_uom4;
	}
	public double getPenjualanNilaiBeli() {
		return penjualanNilaiBeli;
	}
	public void setPenjualanNilaiBeli(double penjualanNilaiBeli) {
		this.penjualanNilaiBeli = penjualanNilaiBeli;
	}
	public double getPenjualanNilaiJual() {
		return penjualanNilaiJual;
	}
	public void setPenjualanNilaiJual(double penjualanNilaiJual) {
		this.penjualanNilaiJual = penjualanNilaiJual;
	}
	public String getPenjualanString() {
		return penjualanString;
	}
	public void setPenjualanString(String penjualanString) {
		this.penjualanString = penjualanString;
	}
	public double getReturPenjualanPcs() {
		return returPenjualanPcs;
	}
	public void setReturPenjualanPcs(double returPenjualanPcs) {
		this.returPenjualanPcs = returPenjualanPcs;
	}
	public double getReturPenjualan_uom1() {
		return returPenjualan_uom1;
	}
	public void setReturPenjualan_uom1(double returPenjualan_uom1) {
		this.returPenjualan_uom1 = returPenjualan_uom1;
	}
	public double getReturPenjualan_uom2() {
		return returPenjualan_uom2;
	}
	public void setReturPenjualan_uom2(double returPenjualan_uom2) {
		this.returPenjualan_uom2 = returPenjualan_uom2;
	}
	public double getReturPenjualan_uom3() {
		return returPenjualan_uom3;
	}
	public void setReturPenjualan_uom3(double returPenjualan_uom3) {
		this.returPenjualan_uom3 = returPenjualan_uom3;
	}
	public double getReturPenjualan_uom4() {
		return returPenjualan_uom4;
	}
	public void setReturPenjualan_uom4(double returPenjualan_uom4) {
		this.returPenjualan_uom4 = returPenjualan_uom4;
	}
	public double getReturPenjualanNilaiBeli() {
		return returPenjualanNilaiBeli;
	}
	public void setReturPenjualanNilaiBeli(double returPenjualanNilaiBeli) {
		this.returPenjualanNilaiBeli = returPenjualanNilaiBeli;
	}
	public double getReturPenjualanNilaiJual() {
		return returPenjualanNilaiJual;
	}
	public void setReturPenjualanNilaiJual(double returPenjualanNilaiJual) {
		this.returPenjualanNilaiJual = returPenjualanNilaiJual;
	}
	public String getReturPenjualanString() {
		return returPenjualanString;
	}
	public void setReturPenjualanString(String returPenjualanString) {
		this.returPenjualanString = returPenjualanString;
	}
	public double getPembelianPcs() {
		return pembelianPcs;
	}
	public void setPembelianPcs(double pembelianPcs) {
		this.pembelianPcs = pembelianPcs;
	}
	public double getPembelian_uom1() {
		return pembelian_uom1;
	}
	public void setPembelian_uom1(double pembelian_uom1) {
		this.pembelian_uom1 = pembelian_uom1;
	}
	public double getPembelian_uom2() {
		return pembelian_uom2;
	}
	public void setPembelian_uom2(double pembelian_uom2) {
		this.pembelian_uom2 = pembelian_uom2;
	}
	public double getPembelian_uom3() {
		return pembelian_uom3;
	}
	public void setPembelian_uom3(double pembelian_uom3) {
		this.pembelian_uom3 = pembelian_uom3;
	}
	public double getPembelian_uom4() {
		return pembelian_uom4;
	}
	public void setPembelian_uom4(double pembelian_uom4) {
		this.pembelian_uom4 = pembelian_uom4;
	}
	public double getPembelianNilaiBeli() {
		return pembelianNilaiBeli;
	}
	public void setPembelianNilaiBeli(double pembelianNilaiBeli) {
		this.pembelianNilaiBeli = pembelianNilaiBeli;
	}
	public double getPembelianNilaiJual() {
		return pembelianNilaiJual;
	}
	public void setPembelianNilaiJual(double pembelianNilaiJual) {
		this.pembelianNilaiJual = pembelianNilaiJual;
	}
	public String getPembelianString() {
		return pembelianString;
	}
	public void setPembelianString(String pembelianString) {
		this.pembelianString = pembelianString;
	}
	public double getReturPembelianPcs() {
		return returPembelianPcs;
	}
	public void setReturPembelianPcs(double returPembelianPcs) {
		this.returPembelianPcs = returPembelianPcs;
	}
	public double getReturPembelian_uom1() {
		return returPembelian_uom1;
	}
	public void setReturPembelian_uom1(double returPembelian_uom1) {
		this.returPembelian_uom1 = returPembelian_uom1;
	}
	public double getReturPembelian_uom2() {
		return returPembelian_uom2;
	}
	public void setReturPembelian_uom2(double returPembelian_uom2) {
		this.returPembelian_uom2 = returPembelian_uom2;
	}
	public double getReturPembelian_uom3() {
		return returPembelian_uom3;
	}
	public void setReturPembelian_uom3(double returPembelian_uom3) {
		this.returPembelian_uom3 = returPembelian_uom3;
	}
	public double getReturPembelian_uom4() {
		return returPembelian_uom4;
	}
	public void setReturPembelian_uom4(double returPembelian_uom4) {
		this.returPembelian_uom4 = returPembelian_uom4;
	}
	public double getReturPembelianNilaiBeli() {
		return returPembelianNilaiBeli;
	}
	public void setReturPembelianNilaiBeli(double returPembelianNilaiBeli) {
		this.returPembelianNilaiBeli = returPembelianNilaiBeli;
	}
	public double getReturPembelianNilaiJual() {
		return returPembelianNilaiJual;
	}
	public void setReturPembelianNilaiJual(double returPembelianNilaiJual) {
		this.returPembelianNilaiJual = returPembelianNilaiJual;
	}
	public String getReturPembelianString() {
		return returPembelianString;
	}
	public void setReturPembelianString(String returPembelianString) {
		this.returPembelianString = returPembelianString;
	}
	public double getTransferInPcs() {
		return transferInPcs;
	}
	public void setTransferInPcs(double transferInPcs) {
		this.transferInPcs = transferInPcs;
	}
	public double getTransferIn_uom1() {
		return transferIn_uom1;
	}
	public void setTransferIn_uom1(double transferIn_uom1) {
		this.transferIn_uom1 = transferIn_uom1;
	}
	public double getTransferIn_uom2() {
		return transferIn_uom2;
	}
	public void setTransferIn_uom2(double transferIn_uom2) {
		this.transferIn_uom2 = transferIn_uom2;
	}
	public double getTransferIn_uom3() {
		return transferIn_uom3;
	}
	public void setTransferIn_uom3(double transferIn_uom3) {
		this.transferIn_uom3 = transferIn_uom3;
	}
	public double getTransferIn_uom4() {
		return transferIn_uom4;
	}
	public void setTransferIn_uom4(double transferIn_uom4) {
		this.transferIn_uom4 = transferIn_uom4;
	}
	public double getTransferInNilaiBeli() {
		return transferInNilaiBeli;
	}
	public void setTransferInNilaiBeli(double transferInNilaiBeli) {
		this.transferInNilaiBeli = transferInNilaiBeli;
	}
	public double getTransferInNilaiJual() {
		return transferInNilaiJual;
	}
	public void setTransferInNilaiJual(double transferInNilaiJual) {
		this.transferInNilaiJual = transferInNilaiJual;
	}
	public String getTransferInString() {
		return transferInString;
	}
	public void setTransferInString(String transferInString) {
		this.transferInString = transferInString;
	}
	public double getTransferOutPcs() {
		return transferOutPcs;
	}
	public void setTransferOutPcs(double transferOutPcs) {
		this.transferOutPcs = transferOutPcs;
	}
	public double getTransferOut_uom1() {
		return transferOut_uom1;
	}
	public void setTransferOut_uom1(double transferOut_uom1) {
		this.transferOut_uom1 = transferOut_uom1;
	}
	public double getTransferOut_uom2() {
		return transferOut_uom2;
	}
	public void setTransferOut_uom2(double transferOut_uom2) {
		this.transferOut_uom2 = transferOut_uom2;
	}
	public double getTransferOut_uom3() {
		return transferOut_uom3;
	}
	public void setTransferOut_uom3(double transferOut_uom3) {
		this.transferOut_uom3 = transferOut_uom3;
	}
	public double getTransferOut_uom4() {
		return transferOut_uom4;
	}
	public void setTransferOut_uom4(double transferOut_uom4) {
		this.transferOut_uom4 = transferOut_uom4;
	}
	public double getTransferOutNilaiBeli() {
		return transferOutNilaiBeli;
	}
	public void setTransferOutNilaiBeli(double transferOutNilaiBeli) {
		this.transferOutNilaiBeli = transferOutNilaiBeli;
	}
	public double getTransferOutNilaiJual() {
		return transferOutNilaiJual;
	}
	public void setTransferOutNilaiJual(double transferOutNilaiJual) {
		this.transferOutNilaiJual = transferOutNilaiJual;
	}
	public String getTransferOutString() {
		return transferOutString;
	}
	public void setTransferOutString(String transferOutString) {
		this.transferOutString = transferOutString;
	}
	public double getPenyesuaianPcs() {
		return penyesuaianPcs;
	}
	public void setPenyesuaianPcs(double penyesuaianPcs) {
		this.penyesuaianPcs = penyesuaianPcs;
	}
	public double getPenyesuaian_uom1() {
		return penyesuaian_uom1;
	}
	public void setPenyesuaian_uom1(double penyesuaian_uom1) {
		this.penyesuaian_uom1 = penyesuaian_uom1;
	}
	public double getPenyesuaian_uom2() {
		return penyesuaian_uom2;
	}
	public void setPenyesuaian_uom2(double penyesuaian_uom2) {
		this.penyesuaian_uom2 = penyesuaian_uom2;
	}
	public double getPenyesuaian_uom3() {
		return penyesuaian_uom3;
	}
	public void setPenyesuaian_uom3(double penyesuaian_uom3) {
		this.penyesuaian_uom3 = penyesuaian_uom3;
	}
	public double getPenyesuaian_uom4() {
		return penyesuaian_uom4;
	}
	public void setPenyesuaian_uom4(double penyesuaian_uom4) {
		this.penyesuaian_uom4 = penyesuaian_uom4;
	}
	public double getPenyesuaianNilaiBeli() {
		return penyesuaianNilaiBeli;
	}
	public void setPenyesuaianNilaiBeli(double penyesuaianNilaiBeli) {
		this.penyesuaianNilaiBeli = penyesuaianNilaiBeli;
	}
	public double getPenyesuaianNilaiJual() {
		return penyesuaianNilaiJual;
	}
	public void setPenyesuaianNilaiJual(double penyesuaianNilaiJual) {
		this.penyesuaianNilaiJual = penyesuaianNilaiJual;
	}
	public String getPenyesuaianString() {
		return penyesuaianString;
	}
	public void setPenyesuaianString(String penyesuaianString) {
		this.penyesuaianString = penyesuaianString;
	}
	public double getSaldoAkhirPcs() {
		return saldoAkhirPcs;
	}
	public void setSaldoAkhirPcs(double saldoAkhirPcs) {
		this.saldoAkhirPcs = saldoAkhirPcs;
	}
	public double getSaldoAkhir_uom1() {
		return saldoAkhir_uom1;
	}
	public void setSaldoAkhir_uom1(double saldoAkhir_uom1) {
		this.saldoAkhir_uom1 = saldoAkhir_uom1;
	}
	public double getSaldoAkhir_uom2() {
		return saldoAkhir_uom2;
	}
	public void setSaldoAkhir_uom2(double saldoAkhir_uom2) {
		this.saldoAkhir_uom2 = saldoAkhir_uom2;
	}
	public double getSaldoAkhir_uom3() {
		return saldoAkhir_uom3;
	}
	public void setSaldoAkhir_uom3(double saldoAkhir_uom3) {
		this.saldoAkhir_uom3 = saldoAkhir_uom3;
	}
	public double getSaldoAkhir_uom4() {
		return saldoAkhir_uom4;
	}
	public void setSaldoAkhir_uom4(double saldoAkhir_uom4) {
		this.saldoAkhir_uom4 = saldoAkhir_uom4;
	}
	public double getSaldoAkhirNilaiBeli() {
		return saldoAkhirNilaiBeli;
	}
	public void setSaldoAkhirNilaiBeli(double saldoAkhirNilaiBeli) {
		this.saldoAkhirNilaiBeli = saldoAkhirNilaiBeli;
	}
	public double getSaldoAkhirNilaiJual() {
		return saldoAkhirNilaiJual;
	}
	public void setSaldoAkhirNilaiJual(double saldoAkhirNilaiJual) {
		this.saldoAkhirNilaiJual = saldoAkhirNilaiJual;
	}
	public String getSaldoAkhirString() {
		return saldoAkhirString;
	}
	public void setSaldoAkhirString(String saldoAkhirString) {
		this.saldoAkhirString = saldoAkhirString;
	}
	
	public String getSaldoAkhirGudang1String() {
		return saldoAkhirGudang1String;
	}
	public void setSaldoAkhirGudang1String(String saldoAkhirGudang1String) {
		this.saldoAkhirGudang1String = saldoAkhirGudang1String;
	}
	public String getSaldoAkhirGudang2String() {
		return saldoAkhirGudang2String;
	}
	public void setSaldoAkhirGudang2String(String saldoAkhirGudang2String) {
		this.saldoAkhirGudang2String = saldoAkhirGudang2String;
	}
	public String getSaldoAkhirGudang3String() {
		return saldoAkhirGudang3String;
	}
	public void setSaldoAkhirGudang3String(String saldoAkhirGudang3String) {
		this.saldoAkhirGudang3String = saldoAkhirGudang3String;
	}
	public String getSaldoAkhirGudang4String() {
		return saldoAkhirGudang4String;
	}
	public void setSaldoAkhirGudang4String(String saldoAkhirGudang4String) {
		this.saldoAkhirGudang4String = saldoAkhirGudang4String;
	}
	public String getSaldoAkhirGudang5String() {
		return saldoAkhirGudang5String;
	}
	public void setSaldoAkhirGudang5String(String saldoAkhirGudang5String) {
		this.saldoAkhirGudang5String = saldoAkhirGudang5String;
	}
	
	
	public String getSaldoAkhirGudang1Desc() {
		return saldoAkhirGudang1Desc;
	}
	public void setSaldoAkhirGudang1Desc(String saldoAkhirGudang1Desc) {
		this.saldoAkhirGudang1Desc = saldoAkhirGudang1Desc;
	}
	public String getSaldoAkhirGudang2Desc() {
		return saldoAkhirGudang2Desc;
	}
	public void setSaldoAkhirGudang2Desc(String saldoAkhirGudang2Desc) {
		this.saldoAkhirGudang2Desc = saldoAkhirGudang2Desc;
	}
	public String getSaldoAkhirGudang3Desc() {
		return saldoAkhirGudang3Desc;
	}
	public void setSaldoAkhirGudang3Desc(String saldoAkhirGudang3Desc) {
		this.saldoAkhirGudang3Desc = saldoAkhirGudang3Desc;
	}
	public String getSaldoAkhirGudang4Desc() {
		return saldoAkhirGudang4Desc;
	}
	public void setSaldoAkhirGudang4Desc(String saldoAkhirGudang4Desc) {
		this.saldoAkhirGudang4Desc = saldoAkhirGudang4Desc;
	}
	public String getSaldoAkhirGudang5Desc() {
		return saldoAkhirGudang5Desc;
	}
	public void setSaldoAkhirGudang5Desc(String saldoAkhirGudang5Desc) {
		this.saldoAkhirGudang5Desc = saldoAkhirGudang5Desc;
	}
	public double getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(double idProduct) {
		this.idProduct = idProduct;
	}
	
	public double getNoUrut() {
		return noUrut;
	}
	public void setNoUrut(double noUrut) {
		this.noUrut = noUrut;
	}
	
	public String getString1() {
		return string1;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public String getString2() {
		return string2;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public String getString3() {
		return string3;
	}
	public void setString3(String string3) {
		this.string3 = string3;
	}
	public String getString4() {
		return string4;
	}
	public void setString4(String string4) {
		this.string4 = string4;
	}
	public String getString5() {
		return string5;
	}
	public void setString5(String string5) {
		this.string5 = string5;
	}
	
	public String getSaldoAwalString_uom1() {
		return saldoAwalString_uom1;
	}
	public void setSaldoAwalString_uom1(String saldoAwalString_uom1) {
		this.saldoAwalString_uom1 = saldoAwalString_uom1;
	}
	public String getSaldoAwalString_uom2() {
		return saldoAwalString_uom2;
	}
	public void setSaldoAwalString_uom2(String saldoAwalString_uom2) {
		this.saldoAwalString_uom2 = saldoAwalString_uom2;
	}
	public String getSaldoAwalString_uom3() {
		return saldoAwalString_uom3;
	}
	public void setSaldoAwalString_uom3(String saldoAwalString_uom3) {
		this.saldoAwalString_uom3 = saldoAwalString_uom3;
	}
	public String getSaldoAwalString_uom4() {
		return saldoAwalString_uom4;
	}
	public void setSaldoAwalString_uom4(String saldoAwalString_uom4) {
		this.saldoAwalString_uom4 = saldoAwalString_uom4;
	}
	public String getSaldoAkhirString_uom1() {
		return saldoAkhirString_uom1;
	}
	public void setSaldoAkhirString_uom1(String saldoAkhirString_uom1) {
		this.saldoAkhirString_uom1 = saldoAkhirString_uom1;
	}
	public String getSaldoAkhirString_uom2() {
		return saldoAkhirString_uom2;
	}
	public void setSaldoAkhirString_uom2(String saldoAkhirString_uom2) {
		this.saldoAkhirString_uom2 = saldoAkhirString_uom2;
	}
	public String getSaldoAkhirString_uom3() {
		return saldoAkhirString_uom3;
	}
	public void setSaldoAkhirString_uom3(String saldoAkhirString_uom3) {
		this.saldoAkhirString_uom3 = saldoAkhirString_uom3;
	}
	public String getSaldoAkhirString_uom4() {
		return saldoAkhirString_uom4;
	}
	public void setSaldoAkhirString_uom4(String saldoAkhirString_uom4) {
		this.saldoAkhirString_uom4 = saldoAkhirString_uom4;
	}
	
	public String getConvfactString() {
		return convfactString;
	}
	public void setConvfactString(String convfactString) {
		this.convfactString = convfactString;
	}
	
	public String getSaldoAkhirAvailableString() {
		return saldoAkhirAvailableString;
	}
	public void setSaldoAkhirAvailableString(String saldoAkhirAvailableString) {
		this.saldoAkhirAvailableString = saldoAkhirAvailableString;
	}
	
	
	public double getSaldoAkhirGudang1_Double1() {
		return saldoAkhirGudang1_Double1;
	}
	public void setSaldoAkhirGudang1_Double1(double saldoAkhirGudang1_Double1) {
		this.saldoAkhirGudang1_Double1 = saldoAkhirGudang1_Double1;
	}
	public double getSaldoAkhirGudang1_Double2() {
		return saldoAkhirGudang1_Double2;
	}
	public void setSaldoAkhirGudang1_Double2(double saldoAkhirGudang1_Double2) {
		this.saldoAkhirGudang1_Double2 = saldoAkhirGudang1_Double2;
	}
	public double getSaldoAkhirGudang2_Double1() {
		return saldoAkhirGudang2_Double1;
	}
	public void setSaldoAkhirGudang2_Double1(double saldoAkhirGudang2_Double1) {
		this.saldoAkhirGudang2_Double1 = saldoAkhirGudang2_Double1;
	}
	public double getSaldoAkhirGudang2_Double2() {
		return saldoAkhirGudang2_Double2;
	}
	public void setSaldoAkhirGudang2_Double2(double saldoAkhirGudang2_Double2) {
		this.saldoAkhirGudang2_Double2 = saldoAkhirGudang2_Double2;
	}
	public double getSaldoAkhirGudang3_Double1() {
		return saldoAkhirGudang3_Double1;
	}
	public void setSaldoAkhirGudang3_Double1(double saldoAkhirGudang3_Double1) {
		this.saldoAkhirGudang3_Double1 = saldoAkhirGudang3_Double1;
	}
	public double getSaldoAkhirGudang3_Double2() {
		return saldoAkhirGudang3_Double2;
	}
	public void setSaldoAkhirGudang3_Double2(double saldoAkhirGudang3_Double2) {
		this.saldoAkhirGudang3_Double2 = saldoAkhirGudang3_Double2;
	}
	public double getSaldoAkhirGudang4_Double1() {
		return saldoAkhirGudang4_Double1;
	}
	public void setSaldoAkhirGudang4_Double1(double saldoAkhirGudang4_Double1) {
		this.saldoAkhirGudang4_Double1 = saldoAkhirGudang4_Double1;
	}
	public double getSaldoAkhirGudang4_Double2() {
		return saldoAkhirGudang4_Double2;
	}
	public void setSaldoAkhirGudang4_Double2(double saldoAkhirGudang4_Double2) {
		this.saldoAkhirGudang4_Double2 = saldoAkhirGudang4_Double2;
	}
	
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public String getString6() {
		return string6;
	}
	public void setString6(String string6) {
		this.string6 = string6;
	}
	public String getString7() {
		return string7;
	}
	public void setString7(String string7) {
		this.string7 = string7;
	}
	
	public double getHargaBeli_Pcs() {
		return hargaBeli_Pcs;
	}
	public void setHargaBeli_Pcs(double hargaBeli_Pcs) {
		this.hargaBeli_Pcs = hargaBeli_Pcs;
	}
	public double getHargaJual_Pcs() {
		return hargaJual_Pcs;
	}
	public void setHargaJual_Pcs(double hargaJual_Pcs) {
		this.hargaJual_Pcs = hargaJual_Pcs;
	}

	@Override
	public String toString() {
		return ID + "";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ZLapMutasiStock)) {
			return false;
		}
		ZLapMutasiStock other = (ZLapMutasiStock) obj;
		if (ID != other.ID) {
			return false;
		}
		return true;
	}
	

	
	
}
