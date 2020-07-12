package com.erp.distribution.sfa.model.transient_model;

import java.util.Date;



public class ZLapAktifitasPromoList {

	private Long id;
	

	String grup1;

	String grup2;

	String grup3;



	private String promoid;

	private String promodesc;
	

	private String invoiceno;

	private Date invoicedate;
	

	private String custarea;

	private String custsubarea;

	private String custgroup;

	private String custsubgroup;
	

	private String custno;

	private String custname;

	private String address;

	private String city;
	
	//NILAI NOTA

	private Double subtotalafterdiscafterppn;

	//PROMO BARANG

	private String freebonuspcode;

	private String freebonuspname;


	private Integer freebonusqtypcs;

	private Integer freebonusqtybes;

	private Integer freebonusqtysed;

	private Integer freebonusqtykec;
	

	private Double freebonusafterppn;
	
	//PROMO DISC
	private Double disc1;	

	private Double disc2;


	private Double disc1afterppn;

	private Double disc2afterppn;
	
	//PROMO CASHBACK
	

	private Double cashbackafterppn;

	
	
	
	public Integer getFreebonusqtypcs() {
		return freebonusqtypcs;
	}

	public Integer getFreebonusqtybes() {
		return freebonusqtybes;
	}

	public Integer getFreebonusqtysed() {
		return freebonusqtysed;
	}

	public Integer getFreebonusqtykec() {
		return freebonusqtykec;
	}

	public void setFreebonusqtypcs(Integer freebonusqtypcs) {
		this.freebonusqtypcs = freebonusqtypcs;
	}

	public void setFreebonusqtybes(Integer freebonusqtybes) {
		this.freebonusqtybes = freebonusqtybes;
	}

	public void setFreebonusqtysed(Integer freebonusqtysed) {
		this.freebonusqtysed = freebonusqtysed;
	}

	public void setFreebonusqtykec(Integer freebonusqtykec) {
		this.freebonusqtykec = freebonusqtykec;
	}

	public Long getId() {
		return id;
	}

	public String getGrup1() {
		return grup1;
	}

	public String getGrup2() {
		return grup2;
	}

	public String getGrup3() {
		return grup3;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public Date getInvoicedate() {
		return invoicedate;
	}

	public String getCustgroup() {
		return custgroup;
	}

	public String getCustsubarea() {
		return custsubarea;
	}

	public String getCustsubgroup() {
		return custsubgroup;
	}

	public String getCustno() {
		return custno;
	}

	public String getCustname() {
		return custname;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public Double getSubtotalafterdiscafterppn() {
		return subtotalafterdiscafterppn;
	}

	public Double getFreebonusafterppn() {
		return freebonusafterppn;
	}

	public Double getDisc1() {
		return disc1;
	}

	public Double getDisc2() {
		return disc2;
	}

	public Double getDisc1afterppn() {
		return disc1afterppn;
	}

	public Double getDisc2afterppn() {
		return disc2afterppn;
	}

	public Double getCashbackafterppn() {
		return cashbackafterppn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}

	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}

	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}

	public void setCustgroup(String custgroup) {
		this.custgroup = custgroup;
	}

	public void setCustsubarea(String custsubarea) {
		this.custsubarea = custsubarea;
	}

	public void setCustsubgroup(String custsubgroup) {
		this.custsubgroup = custsubgroup;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setSubtotalafterdiscafterppn(Double subtotalafterdiscafterppn) {
		this.subtotalafterdiscafterppn = subtotalafterdiscafterppn;
	}

	public void setFreebonusafterppn(Double freebonusafterppn) {
		this.freebonusafterppn = freebonusafterppn;
	}

	public void setDisc1(Double disc1) {
		this.disc1 = disc1;
	}

	public void setDisc2(Double disc2) {
		this.disc2 = disc2;
	}

	public void setDisc1afterppn(Double disc1afterppn) {
		this.disc1afterppn = disc1afterppn;
	}

	public void setDisc2afterppn(Double disc2afterppn) {
		this.disc2afterppn = disc2afterppn;
	}

	public void setCashbackafterppn(Double cashbackafterppn) {
		this.cashbackafterppn = cashbackafterppn;
	}

	public String getFreebonuspcode() {
		return freebonuspcode;
	}

	public String getFreebonuspname() {
		return freebonuspname;
	}

	public void setFreebonuspcode(String freebonuspcode) {
		this.freebonuspcode = freebonuspcode;
	}

	public void setFreebonuspname(String freebonuspname) {
		this.freebonuspname = freebonuspname;
	}

	public String getCustarea() {
		return custarea;
	}

	public void setCustarea(String custarea) {
		this.custarea = custarea;
	}

	public String getPromoid() {
		return promoid;
	}

	public String getPromodesc() {
		return promodesc;
	}

	public void setPromoid(String promoid) {
		this.promoid = promoid;
	}

	public void setPromodesc(String promodesc) {
		this.promodesc = promodesc;
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
		if (!(obj instanceof ZLapAktifitasPromoList)) {
			return false;
		}
		ZLapAktifitasPromoList other = (ZLapAktifitasPromoList) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "" + id + "";
	}


	
}
