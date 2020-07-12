package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumTipeWarehouse;

import java.util.Date;


@Entity(tableName="fWarehouse")
public class FWarehouse {

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;
	
	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;

	private String kode1 ="";
	private String kode2 ="";

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private boolean productHppSaved=false;
	
	private Integer numberPriority;
	
	private String description ="";
	private boolean gudangUtama =false;
	
	private String address1 ="";
	private String city1;
	private String state1 ="";
	private String phone ="";
	private boolean statusActive =false;

	private boolean gudangPo =true;
	private boolean gudangSo =true;
	private boolean gudangTransfer =true;

	private boolean gudangRetail =true;
	
	private boolean gudangPusatCompany =true;
	
	private boolean gudangTransitDiv =true;
	
	private EnumTipeWarehouse tipeWarehouse;

	//PORT WS:: UNTUK TRANSAKSI PEMBALIAN DAN PENJUALAN
	private String wsport ="";

	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID



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

	public String getKode1() {
		return kode1;
	}

	public void setKode1(String kode1) {
		this.kode1 = kode1;
	}

	public String getKode2() {
		return kode2;
	}

	public void setKode2(String kode2) {
		this.kode2 = kode2;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public boolean isProductHppSaved() {
		return productHppSaved;
	}

	public void setProductHppSaved(boolean productHppSaved) {
		this.productHppSaved = productHppSaved;
	}

	public Integer getNumberPriority() {
		return numberPriority;
	}

	public void setNumberPriority(Integer numberPriority) {
		this.numberPriority = numberPriority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isGudangUtama() {
		return gudangUtama;
	}

	public void setGudangUtama(boolean gudangUtama) {
		this.gudangUtama = gudangUtama;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
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

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public boolean isGudangPo() {
		return gudangPo;
	}

	public void setGudangPo(boolean gudangPo) {
		this.gudangPo = gudangPo;
	}

	public boolean isGudangSo() {
		return gudangSo;
	}

	public void setGudangSo(boolean gudangSo) {
		this.gudangSo = gudangSo;
	}

	public boolean isGudangTransfer() {
		return gudangTransfer;
	}

	public void setGudangTransfer(boolean gudangTransfer) {
		this.gudangTransfer = gudangTransfer;
	}

	public boolean isGudangRetail() {
		return gudangRetail;
	}

	public void setGudangRetail(boolean gudangRetail) {
		this.gudangRetail = gudangRetail;
	}

	public boolean isGudangPusatCompany() {
		return gudangPusatCompany;
	}

	public void setGudangPusatCompany(boolean gudangPusatCompany) {
		this.gudangPusatCompany = gudangPusatCompany;
	}

	public boolean isGudangTransitDiv() {
		return gudangTransitDiv;
	}

	public void setGudangTransitDiv(boolean gudangTransitDiv) {
		this.gudangTransitDiv = gudangTransitDiv;
	}

	public EnumTipeWarehouse getTipeWarehouse() {
		return tipeWarehouse;
	}

	public void setTipeWarehouse(EnumTipeWarehouse tipeWarehouse) {
		this.tipeWarehouse = tipeWarehouse;
	}

	public String getWsport() {
		return wsport;
	}

	public void setWsport(String wsport) {
		this.wsport = wsport;
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
}