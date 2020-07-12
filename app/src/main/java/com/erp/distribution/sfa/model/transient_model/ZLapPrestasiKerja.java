package com.erp.distribution.sfa.model.transient_model;

import java.util.Date;


public class ZLapPrestasiKerja {
	private Long id;
	
	String grup1;
	String grup2;
	String grup3;
	
	String hari;
	Date tanggal;
	
	//OUTLET ACTIVE
	Integer jumlahToko;
	
	Integer jumlahNota;
	
	Integer efectiveCall;
	Integer skuSold;
	Integer lines;

	// SKU / EC
	Double rataRata;
	Double rataRata2;

	Double totalBeforediscBeforeppn;
	Double discPerbarang;
	Double discNota;
	Double returdpp;
	Double returppn;
	Double dpp;

	Double ppn;

	Double totalAfterdiscAfterppn;


	String string1;

	String string2;

	Integer integer1;

	Integer integer2;

	Double double1;

	Double double2;

	Date date1;

	Date date2;
	
	
	public Long getId() {
		return id;
	}
	public Date getTanggal() {
		return tanggal;
	}
	public Integer getJumlahToko() {
		return jumlahToko;
	}
	public Integer getSkuSold() {
		return skuSold;
	}
	public Double getDiscPerbarang() {
		return discPerbarang;
	}
	public Double getDiscNota() {
		return discNota;
	}
	public Double getDpp() {
		return dpp;
	}
	public Double getPpn() {
		return ppn;
	}
	public Double getTotalAfterdiscAfterppn() {
		return totalAfterdiscAfterppn;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	public void setJumlahToko(Integer jumlahToko) {
		this.jumlahToko = jumlahToko;
	}
	public void setSkuSold(Integer skuSold) {
		this.skuSold = skuSold;
	}
	public void setDiscPerbarang(Double discPerbarang) {
		this.discPerbarang = discPerbarang;
	}
	public void setDiscNota(Double discNota) {
		this.discNota = discNota;
	}
	
	public Double getRataRata() {
		return rataRata;
	}
	public void setRataRata(Double rataRata) {
		this.rataRata = rataRata;
	}
	public void setDpp(Double dpp) {
		this.dpp = dpp;
	}
	public void setPpn(Double ppn) {
		this.ppn = ppn;
	}
	public void setTotalAfterdiscAfterppn(Double totalAfterdiscAfterppn) {
		this.totalAfterdiscAfterppn = totalAfterdiscAfterppn;
	}
	public String getHari() {
		return hari;
	}
	public String getString1() {
		return string1;
	}
	public String getString2() {
		return string2;
	}
	public Integer getInteger1() {
		return integer1;
	}
	public Integer getInteger2() {
		return integer2;
	}
	public Double getDouble1() {
		return double1;
	}
	public Double getDouble2() {
		return double2;
	}
	public Date getDate1() {
		return date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setHari(String hari) {
		this.hari = hari;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public void setInteger1(Integer integer1) {
		this.integer1 = integer1;
	}
	public void setInteger2(Integer integer2) {
		this.integer2 = integer2;
	}
	public void setDouble1(Double double1) {
		this.double1 = double1;
	}
	public void setDouble2(Double double2) {
		this.double2 = double2;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public Integer getEfectiveCall() {
		return efectiveCall;
	}
	public void setEfectiveCall(Integer efectiveCall) {
		this.efectiveCall = efectiveCall;
	}
	
	public Double getTotalBeforediscBeforeppn() {
		return totalBeforediscBeforeppn;
	}
	public void setTotalBeforediscBeforeppn(Double totalBeforediscBeforeppn) {
		this.totalBeforediscBeforeppn = totalBeforediscBeforeppn;
	}
	
	
	public String getGrup1() {
		return grup1;
	}
	public String getGrup2() {
		return grup2;
	}
	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}
	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}
	
	public Integer getLines() {
		return lines;
	}
	public Double getRataRata2() {
		return rataRata2;
	}
	public void setLines(Integer lines) {
		this.lines = lines;
	}
	public void setRataRata2(Double rataRata2) {
		this.rataRata2 = rataRata2;
	}
	
	
	public Double getReturdpp() {
		return returdpp;
	}
	public Double getReturppn() {
		return returppn;
	}
	public void setReturdpp(Double returdpp) {
		this.returdpp = returdpp;
	}
	public void setReturppn(Double returppn) {
		this.returppn = returppn;
	}
	public String getGrup3() {
		return grup3;
	}
	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}
	
	
	public Integer getJumlahNota() {
		return jumlahNota;
	}
	public void setJumlahNota(Integer jumlahNota) {
		this.jumlahNota = jumlahNota;
	}

	@Override
	public String toString() {
		return "" + id + "";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof ZLapPrestasiKerja)) {
			return false;
		}
		ZLapPrestasiKerja other = (ZLapPrestasiKerja) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	

	
	
	
	
}
