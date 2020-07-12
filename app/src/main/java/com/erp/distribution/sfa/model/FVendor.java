package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumCurrency;

import java.util.Date;


@Entity(tableName="fVendor")
public class FVendor {

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

    
	private String vcode ="";
	private String vname ="";
	private String address1 ="";
	private String address2 ="";
	private String city1 ="";
	private String state1 ="";
	private String phone ="";
	private String email ="";
	private Date joinDate=new Date();
	private Date lastTrans=new Date();
	
	private String noRekening ="";

	private EnumCurrency currency;
	
	/*
	 * Diskon Margin Barang: disc2 & Disc2Plus
	 */	
	private Double disc2Margin=0.0;
	private Double disc1PlusMargin =0.0;
	
	/*
	 * PERPAJAKAN
	 */
	private boolean pkp = true;
	private String namaPrshFakturPajak ="";
	private String namaPengusahaKenaPajak ="";
	private String npwp ="";
	private Date tanggalPengukuhanPkp=new Date();
	
	private boolean statusActive = true;

	private Integer top=0;
	
	//PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
	private String wsport;
	
	private boolean disc1RegManual=false;
	private boolean discPlusRegManual=false;
	

	/*
	 * Tidak Wajib Disi: karena untuk aturan yang bersifat opsional
	 * - Jika Salesman Exclusive maka hanya boleh menjual barang dari Satu vendor tertentu atau Beberapa Vendor
	 */
//    @ManyToOne
//	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable= true)
//	private FSalesman fsalesmanBean;
	private Integer fsalesmanBean = 0;


	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID
	
	//TAMBAHAN
	@Ignore
	private String tempString1 ="";
	@Ignore
	private String tempString2 ="";
	@Ignore
	private Integer tempInt1 =0;
	@Ignore
	private Integer tempInt2 =0;
	@Ignore
	private Double tempDouble1 =0.0;
	@Ignore
	private Double tempDouble2 =0.0;
	@Ignore
	private Double tempDouble31 =0.0;
	@Ignore
	private Double tempDouble32 =0.0;



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

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
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

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastTrans() {
		return lastTrans;
	}

	public void setLastTrans(Date lastTrans) {
		this.lastTrans = lastTrans;
	}

	public String getNoRekening() {
		return noRekening;
	}

	public void setNoRekening(String noRekening) {
		this.noRekening = noRekening;
	}

	public EnumCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(EnumCurrency currency) {
		this.currency = currency;
	}

	public Double getDisc2Margin() {
		return disc2Margin;
	}

	public void setDisc2Margin(Double disc2Margin) {
		this.disc2Margin = disc2Margin;
	}

	public Double getDisc1PlusMargin() {
		return disc1PlusMargin;
	}

	public void setDisc1PlusMargin(Double disc1PlusMargin) {
		this.disc1PlusMargin = disc1PlusMargin;
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

	public String getNamaPengusahaKenaPajak() {
		return namaPengusahaKenaPajak;
	}

	public void setNamaPengusahaKenaPajak(String namaPengusahaKenaPajak) {
		this.namaPengusahaKenaPajak = namaPengusahaKenaPajak;
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

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getWsport() {
		return wsport;
	}

	public void setWsport(String wsport) {
		this.wsport = wsport;
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

	public Integer getFsalesmanBean() {
		return fsalesmanBean;
	}

	public void setFsalesmanBean(Integer fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
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

	public String getTempString1() {
		return tempString1;
	}

	public void setTempString1(String tempString1) {
		this.tempString1 = tempString1;
	}

	public String getTempString2() {
		return tempString2;
	}

	public void setTempString2(String tempString2) {
		this.tempString2 = tempString2;
	}

	public Integer getTempInt1() {
		return tempInt1;
	}

	public void setTempInt1(Integer tempInt1) {
		this.tempInt1 = tempInt1;
	}

	public Integer getTempInt2() {
		return tempInt2;
	}

	public void setTempInt2(Integer tempInt2) {
		this.tempInt2 = tempInt2;
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

	public Double getTempDouble31() {
		return tempDouble31;
	}

	public void setTempDouble31(Double tempDouble31) {
		this.tempDouble31 = tempDouble31;
	}

	public Double getTempDouble32() {
		return tempDouble32;
	}

	public void setTempDouble32(Double tempDouble32) {
		this.tempDouble32 = tempDouble32;
	}
}