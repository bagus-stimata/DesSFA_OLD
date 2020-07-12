package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumCurrency;
import com.erp.distribution.sfa.model.modelenum.EnumTipePajakCustomer;
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName="fCustomer")
public class FCustomer  implements Serializable {


	@PrimaryKey(autoGenerate = true)
	private Integer id=0;
	
	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	 
	private Integer sourceID =0;
	
	 
	private String custno="";
	
	 
	private boolean outletActive=false;

	 
	private String oldKode1="";
	
	 
	private boolean flagNewItem=false;
	
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	/*
	 * Fungsinya: Jika menarik data dari sistem lain dimana mempunyai kode customer yang berbeda
	 * Maka akan di konversikan menjadi kode pada sistem
	 * Pada import data: ada opsi pilih mapping customer yang mana
	 */
	private String mappingInCode1="";
	private String mappingInCode2="";
	private String mappingInCode3="";

	/*
	 * Digunanakan untuk menghasilkan kode yang berbeda jika di extract
	 * Fungsinya: Untuk Integrasi dengan Sistem Lain jika ternyata kode customer berbeda
	 */
	private String mappingOutCode1 ="";
	private String mappingOutCode2 ="";
	
	private Integer custGroupPromo1 =0; //GROUP PROMO
	private Integer custGroupPromo2 =0; //GROUP PROMO

	private String custname ="";
	
	 
	private EnumCurrency currency = EnumCurrency.IDR;
	
	/*
	 * PERPAJAKAN / TAX
	 */	
	 
	private boolean pkp = true;
	 
	private String namaPrshFakturPajak ="";
	 
	private String alamatPrshFakturPajak ="";
	
	private String namaPengusahaKenaPajak ="";
	
	private String nikPajak ="";
	 
	private String npwp ="";
	 
	private Date tanggalPengukuhanPkp=new Date();
	
	 
	private EnumTipePajakCustomer tipePajakCustomer = EnumTipePajakCustomer.REG_01;
	
	 
	private EnumTunaiKredit tunaikredit = EnumTunaiKredit.T;
	
	 
	private Integer lamaCredit =0;
	 
	private Double creditlimit =0.0;
	 
	private Integer maxInvoice =0;
	
	private String namaPemilik ="";
	 
	private String address1 ="";
	 
	private String address2 ="";
	 
	private String address3 ="";
	 
	private String city1 ="";
	 
	private String city2 ="";
	 
	private String state1 ="";
	 
	private String phone1 ="";
	 
	private String phone2 ="";
	 
	private String postcode ="";

	private String email ="";
	 
	private String whatsApp ="";
	 
	private boolean statusActive =false;

	//Tidak akan dipkai: 
	 
	private Integer harikunjungan =0;
	 
	private Integer pekankunjungan =0;
	
	 
	private boolean noeffcall =false;

	 
	private Double latitude =0.0;
	 
	private Double longitude =0.0;
	
	/*
	 * sementara belum dipakai sampai tahu principal atau SAP
	 */
	//SHIPTO BILLTO	
//	@Column(name="SHIPTOBILLTO", length=20)
//	private String shipToBillTo ="";
//	@Column(name="BILLTO", length=20)
//	private String billTo ="";
	private Double basicDisc1Barang = 0.0;
	private Double basicDisc1PlusBarang = 0.0;
	
	 
	private boolean disc1RegManual=false;
	 
	private boolean discPlusRegManual=false;

	/*
	 * 0 = Menggunakan Harga Reguler Distributor (tidak mengenal harga bertingkat)
	 * 1 = Menggunakan harga Retail
	 * 2 = Menggunakan harga Grosir A
	 * 3 = Menggunakan harga Grosir B
	 */
	 
	private Integer priceAltSwalayan =0;
	
//	@ManyToOne
//	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
//	private FCustomerGroup fcustomerGroupBean;
	private Integer fcustomerGroupBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fsubAreaBean", referencedColumnName="ID")
//	private FSubArea fsubAreaBean;
	private Integer fsubAreaBean = 0;

	/*
	 * CLASSIFIKASI MATERIAL & SALES
	 * exclusiveDivisionView: hanya akan dapat dilihat dan dipergunakan untuk transaksi untuk User dengan Divisi Sama
	 * jika Division = All Division maka exclusiveDivisionView tidak berlaku
	 */	
//	@ManyToOne
//	@JoinColumn(name="fdistributionChannelBean", referencedColumnName="ID")
//	private FDistributionChannel fdistributionChannelBean;
	private Integer fdistributionChannelBean = 0;


//	@ManyToOne
//	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
//	private FtPriceAlth ftPriceAlthBean;
	private Integer ftPriceAlthBean = 0;

	/*
	 * reject promotion rules setting
	 */
	private boolean noPromotionRules = false;
		

	/*
	 * Sales Covered
	 * dan Jadwal Kunjungan
	 */
	private boolean exclusiveSalesman=false;
	
	 

    @Ignore
	private String notes = "";

	private Date created = new Date();
	private Date modified = new Date();
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

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public boolean isOutletActive() {
		return outletActive;
	}

	public void setOutletActive(boolean outletActive) {
		this.outletActive = outletActive;
	}

	public String getOldKode1() {
		return oldKode1;
	}

	public void setOldKode1(String oldKode1) {
		this.oldKode1 = oldKode1;
	}

	public boolean isFlagNewItem() {
		return flagNewItem;
	}

	public void setFlagNewItem(boolean flagNewItem) {
		this.flagNewItem = flagNewItem;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public String getMappingInCode1() {
		return mappingInCode1;
	}

	public void setMappingInCode1(String mappingInCode1) {
		this.mappingInCode1 = mappingInCode1;
	}

	public String getMappingInCode2() {
		return mappingInCode2;
	}

	public void setMappingInCode2(String mappingInCode2) {
		this.mappingInCode2 = mappingInCode2;
	}

	public String getMappingInCode3() {
		return mappingInCode3;
	}

	public void setMappingInCode3(String mappingInCode3) {
		this.mappingInCode3 = mappingInCode3;
	}

	public String getMappingOutCode1() {
		return mappingOutCode1;
	}

	public void setMappingOutCode1(String mappingOutCode1) {
		this.mappingOutCode1 = mappingOutCode1;
	}

	public String getMappingOutCode2() {
		return mappingOutCode2;
	}

	public void setMappingOutCode2(String mappingOutCode2) {
		this.mappingOutCode2 = mappingOutCode2;
	}

	public Integer getCustGroupPromo1() {
		return custGroupPromo1;
	}

	public void setCustGroupPromo1(Integer custGroupPromo1) {
		this.custGroupPromo1 = custGroupPromo1;
	}

	public Integer getCustGroupPromo2() {
		return custGroupPromo2;
	}

	public void setCustGroupPromo2(Integer custGroupPromo2) {
		this.custGroupPromo2 = custGroupPromo2;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public EnumCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(EnumCurrency currency) {
		this.currency = currency;
	}

	public boolean isPkp() {
		return pkp;
	}

	public void setPkp(boolean pkp) {
		this.pkp = pkp;
	}

	public String getNamaPrshFakturPajak() {
		return namaPrshFakturPajak;
	}

	public void setNamaPrshFakturPajak(String namaPrshFakturPajak) {
		this.namaPrshFakturPajak = namaPrshFakturPajak;
	}

	public String getAlamatPrshFakturPajak() {
		return alamatPrshFakturPajak;
	}

	public void setAlamatPrshFakturPajak(String alamatPrshFakturPajak) {
		this.alamatPrshFakturPajak = alamatPrshFakturPajak;
	}

	public String getNamaPengusahaKenaPajak() {
		return namaPengusahaKenaPajak;
	}

	public void setNamaPengusahaKenaPajak(String namaPengusahaKenaPajak) {
		this.namaPengusahaKenaPajak = namaPengusahaKenaPajak;
	}

	public String getNikPajak() {
		return nikPajak;
	}

	public void setNikPajak(String nikPajak) {
		this.nikPajak = nikPajak;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public Date getTanggalPengukuhanPkp() {
		return tanggalPengukuhanPkp;
	}

	public void setTanggalPengukuhanPkp(Date tanggalPengukuhanPkp) {
		this.tanggalPengukuhanPkp = tanggalPengukuhanPkp;
	}

	public EnumTipePajakCustomer getTipePajakCustomer() {
		return tipePajakCustomer;
	}

	public void setTipePajakCustomer(EnumTipePajakCustomer tipePajakCustomer) {
		this.tipePajakCustomer = tipePajakCustomer;
	}

	public EnumTunaiKredit getTunaikredit() {
		return tunaikredit;
	}

	public void setTunaikredit(EnumTunaiKredit tunaikredit) {
		this.tunaikredit = tunaikredit;
	}

	public Integer getLamaCredit() {
		return lamaCredit;
	}

	public void setLamaCredit(Integer lamaCredit) {
		this.lamaCredit = lamaCredit;
	}

	public Double getCreditlimit() {
		return creditlimit;
	}

	public void setCreditlimit(Double creditlimit) {
		this.creditlimit = creditlimit;
	}

	public Integer getMaxInvoice() {
		return maxInvoice;
	}

	public void setMaxInvoice(Integer maxInvoice) {
		this.maxInvoice = maxInvoice;
	}

	public String getNamaPemilik() {
		return namaPemilik;
	}

	public void setNamaPemilik(String namaPemilik) {
		this.namaPemilik = namaPemilik;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWhatsApp() {
		return whatsApp;
	}

	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public Integer getHarikunjungan() {
		return harikunjungan;
	}

	public void setHarikunjungan(Integer harikunjungan) {
		this.harikunjungan = harikunjungan;
	}

	public Integer getPekankunjungan() {
		return pekankunjungan;
	}

	public void setPekankunjungan(Integer pekankunjungan) {
		this.pekankunjungan = pekankunjungan;
	}

	public boolean isNoeffcall() {
		return noeffcall;
	}

	public void setNoeffcall(boolean noeffcall) {
		this.noeffcall = noeffcall;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getBasicDisc1Barang() {
		return basicDisc1Barang;
	}

	public void setBasicDisc1Barang(Double basicDisc1Barang) {
		this.basicDisc1Barang = basicDisc1Barang;
	}

	public Double getBasicDisc1PlusBarang() {
		return basicDisc1PlusBarang;
	}

	public void setBasicDisc1PlusBarang(Double basicDisc1PlusBarang) {
		this.basicDisc1PlusBarang = basicDisc1PlusBarang;
	}

	public boolean isDisc1RegManual() {
		return disc1RegManual;
	}

	public void setDisc1RegManual(boolean disc1RegManual) {
		this.disc1RegManual = disc1RegManual;
	}

	public boolean isDiscPlusRegManual() {
		return discPlusRegManual;
	}

	public void setDiscPlusRegManual(boolean discPlusRegManual) {
		this.discPlusRegManual = discPlusRegManual;
	}

	public Integer getPriceAltSwalayan() {
		return priceAltSwalayan;
	}

	public void setPriceAltSwalayan(Integer priceAltSwalayan) {
		this.priceAltSwalayan = priceAltSwalayan;
	}

	public Integer getFcustomerGroupBean() {
		return fcustomerGroupBean;
	}

	public void setFcustomerGroupBean(Integer fcustomerGroupBean) {
		this.fcustomerGroupBean = fcustomerGroupBean;
	}

	public Integer getFsubAreaBean() {
		return fsubAreaBean;
	}

	public void setFsubAreaBean(Integer fsubAreaBean) {
		this.fsubAreaBean = fsubAreaBean;
	}

	public Integer getFdistributionChannelBean() {
		return fdistributionChannelBean;
	}

	public void setFdistributionChannelBean(Integer fdistributionChannelBean) {
		this.fdistributionChannelBean = fdistributionChannelBean;
	}

	public Integer getFtPriceAlthBean() {
		return ftPriceAlthBean;
	}

	public void setFtPriceAlthBean(Integer ftPriceAlthBean) {
		this.ftPriceAlthBean = ftPriceAlthBean;
	}

	public boolean isNoPromotionRules() {
		return noPromotionRules;
	}

	public void setNoPromotionRules(boolean noPromotionRules) {
		this.noPromotionRules = noPromotionRules;
	}

	public boolean isExclusiveSalesman() {
		return exclusiveSalesman;
	}

	public void setExclusiveSalesman(boolean exclusiveSalesman) {
		this.exclusiveSalesman = exclusiveSalesman;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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




}

