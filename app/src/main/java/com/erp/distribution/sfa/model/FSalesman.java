package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumReligion;
import com.erp.distribution.sfa.model.modelenum.EnumSalesType;

import java.util.Date;


@Entity(tableName="fSalesman")
public class FSalesman {

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;
	
	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;

	private String spcode="";
	
	private String spname="";
	
	/*
	 * TIPE JUAL SALESMAN
	 * TO = Taking Order
	 * C = Canvas
	 * TF = Task Force
	 * SHOP = Shop Sales
	 */
//	@Column(name="SALES_TYPE", length=5)
//	private String salesType="";
	private EnumSalesType salesType;
	
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private String address1="";
	private String city1="";
	private String state1="";
	private String phone="";
	private String mobile="";
	private String whatsApp ="";
	
	private String email="";
	private Date joinDate=new Date();
	private Date lastTrans=new Date();
	private String bornPlace = "";
	private Date bornDate = new Date();
	
	private EnumReligion religion;
	
	private boolean statusActive=false;

	private boolean webServiceActive=false;

	//FOR KASSA
	private boolean kassaStatusOpen=false;
	private String kassaIp="";
	
//	@OneToMany(mappedBy="fsalesmanBean", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	 

//	@ManyToOne
//	@JoinColumn(name="ftPriceAlthBean", referencedColumnName="ID", nullable=true)
//	private FtPriceAlth ftPriceAlthBean;
	private Integer ftPriceAlthBean = 0;

	/*
	 * ignore/reject promotion rules setting
	 */
	private boolean noPromotionRules = false;
	
	private boolean vendorcovered;
	 
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

	public String getSpcode() {
		return spcode;
	}

	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}

	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public EnumSalesType getSalesType() {
		return salesType;
	}

	public void setSalesType(EnumSalesType salesType) {
		this.salesType = salesType;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWhatsApp() {
		return whatsApp;
	}

	public void setWhatsApp(String whatsApp) {
		this.whatsApp = whatsApp;
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

	public String getBornPlace() {
		return bornPlace;
	}

	public void setBornPlace(String bornPlace) {
		this.bornPlace = bornPlace;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public EnumReligion getReligion() {
		return religion;
	}

	public void setReligion(EnumReligion religion) {
		this.religion = religion;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public boolean isWebServiceActive() {
		return webServiceActive;
	}

	public void setWebServiceActive(boolean webServiceActive) {
		this.webServiceActive = webServiceActive;
	}

	public boolean isKassaStatusOpen() {
		return kassaStatusOpen;
	}

	public void setKassaStatusOpen(boolean kassaStatusOpen) {
		this.kassaStatusOpen = kassaStatusOpen;
	}

	public String getKassaIp() {
		return kassaIp;
	}

	public void setKassaIp(String kassaIp) {
		this.kassaIp = kassaIp;
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

	public boolean isVendorcovered() {
		return vendorcovered;
	}

	public void setVendorcovered(boolean vendorcovered) {
		this.vendorcovered = vendorcovered;
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