package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName="fDivision")
public class FDivision {

	@PrimaryKey(autoGenerate = true)
	private Integer id =0;
	
	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;
	
	private String kode1 ="";
	private String kode2 ="";
	
	private String description ="";
	
	private boolean diffCompanyAccount = false;
	
//	@ManyToOne
//	@JoinColumn(name="accCostCenterBean", referencedColumnName="ID", nullable=true)
//    private AccCostCenter accCostCenterBean;
	private Integer accCostCenterBean = 0;

	/*
	 * Common Division Parameter
	 */
//	@Column(name="USE_OWN_NOMOR_URUT_MASTER")
//	private boolean useOwnNomorUrutMaster=false;
//	@Column(name="USE_OWN_NOMOR_URUT_TRANSAKSI")
//	private boolean useOwnNomorUrutTransaksi=false;
	
	private boolean useNomorUrutMaterialToCompany=false;
	private boolean useNomorUrutCustomerToCompany=false;
	private boolean useNomorUrutVendorToCompany=false;
	
	private boolean useNomorUrutTransaksiToCompany=true;
	private boolean useNomorUrutJurnalToCompany=true;
	
	/*
	 * Untuk Warehouse, Salesman Wajib Jadi satu dalam company
	 */
	private boolean shareMaterialToCompany=false;
	private boolean shareMaterialOrgToCompany=false;
	private boolean shareCustomerToCompany=false;
	private boolean shareCustomerOrgToCompany=false;

	private boolean shareSalesmanToCompany=false;
	private boolean shareWarehouseToCompany=false;
	private boolean shareVendorToCompany=false;
	
	

	/*
	 * General Ledger Accounting: Default True
	 */
	private boolean shareCoaToCompany=true;
	private boolean shareCoaOrgToCompany=true;
	
	/*
	 * TRANSAKSI: Default false
	 */
	private boolean shareTransaksiToCompany=false;
	
	
	/*
	 * DISKON PROMO DAN DISKON NOTA: Menggunakan Aturan Divisi
	 * 
	 */
	private boolean sharePromotionRulesToCompany=false;
	private boolean shareDiskonNotaToCompany=false;
	
	/*
	 * ACCOUNTING
	 */
	
	private boolean userOnlyRead_HisDivision_Coa_WhenInput=false;

	private boolean noTax_Trans =false;
	
//	@ManyToOne
//	@JoinColumn(name="fcompanyBean", referencedColumnName="ID")
//	private FCompany fcompanyBean;
	private Integer fcompanyBean = 0;


	/*
	 * ****************************
	 */
	
	private boolean statusActive=true;
	
	private boolean webServiceActive=false;
	
	/*
	 * SETTING YANG BERLAKU UNTUK SEMUA DIVISI
	 * JIKA KOSONG MAKA MENGGUNAKAN PRIORITAS ATASNYA
	 * 1. Parameter System
	 * 2. Corporation
	 * 3. Division 
	 */

	/*
	 * Urutan nomor dalam satu divisi biasanya mengikuti perusahaan, karena mempunyai nomor urut faktur pajak yang sama
	 * Tidak digunakan. Karena urutan Dokumen Transaksi  mengikuti Company
	 */
//	@Column(name="NOMOR_URUT_DOC_FOLLOW_CORP") //Tidak boleh diubah-ubah
//	private boolean nomorUrutDocTransFollowCorp = true;
	/*
	 * Urutan master product, material, salesman, mengikuti parameter level 2. Level Perusahaan
	 */
	private boolean sysvarNomorUrutMasterFollowCorp = false;
	
	private boolean sysvarFormatFakturFollowCorp = false;
	
//	/*
//	 * FORMAT FAKTUR DAN ALAMAT
//	 */
//	@Column(name="INVOICE_COMP_NAME_1", length=75)
//	private String invoiceCompanyName1 = "";
//	@Column(name="INVOICE_COMP_ADDRESS_1", length=120)
//	private String invoiceCompanyAddress1 = "";
//	@Column(name="INVOICE_COMP_CITY_1", length=30)
//	private String invoiceCompanyCity1 = "";
//	@Column(name="INVOICE_COMP_PHONE_1", length=25)
//	private String invoiceCompanyPhone1 = "";
	
	
/*
 * Pajak, Nomor Urut Transaksi, Nomor Urut Mengikuti Mengikuti Corporation	
 */
//	@Column(name="INVOICE_COMPANY_NPWP_1", length=45)
//	private String invoiceCompanyNpwpPhone1 = "";
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDiffCompanyAccount() {
		return diffCompanyAccount;
	}

	public void setDiffCompanyAccount(boolean diffCompanyAccount) {
		this.diffCompanyAccount = diffCompanyAccount;
	}

	public Integer getAccCostCenterBean() {
		return accCostCenterBean;
	}

	public void setAccCostCenterBean(Integer accCostCenterBean) {
		this.accCostCenterBean = accCostCenterBean;
	}

	public boolean isUseNomorUrutMaterialToCompany() {
		return useNomorUrutMaterialToCompany;
	}

	public void setUseNomorUrutMaterialToCompany(boolean useNomorUrutMaterialToCompany) {
		this.useNomorUrutMaterialToCompany = useNomorUrutMaterialToCompany;
	}

	public boolean isUseNomorUrutCustomerToCompany() {
		return useNomorUrutCustomerToCompany;
	}

	public void setUseNomorUrutCustomerToCompany(boolean useNomorUrutCustomerToCompany) {
		this.useNomorUrutCustomerToCompany = useNomorUrutCustomerToCompany;
	}

	public boolean isUseNomorUrutVendorToCompany() {
		return useNomorUrutVendorToCompany;
	}

	public void setUseNomorUrutVendorToCompany(boolean useNomorUrutVendorToCompany) {
		this.useNomorUrutVendorToCompany = useNomorUrutVendorToCompany;
	}

	public boolean isUseNomorUrutTransaksiToCompany() {
		return useNomorUrutTransaksiToCompany;
	}

	public void setUseNomorUrutTransaksiToCompany(boolean useNomorUrutTransaksiToCompany) {
		this.useNomorUrutTransaksiToCompany = useNomorUrutTransaksiToCompany;
	}

	public boolean isUseNomorUrutJurnalToCompany() {
		return useNomorUrutJurnalToCompany;
	}

	public void setUseNomorUrutJurnalToCompany(boolean useNomorUrutJurnalToCompany) {
		this.useNomorUrutJurnalToCompany = useNomorUrutJurnalToCompany;
	}

	public boolean isShareMaterialToCompany() {
		return shareMaterialToCompany;
	}

	public void setShareMaterialToCompany(boolean shareMaterialToCompany) {
		this.shareMaterialToCompany = shareMaterialToCompany;
	}

	public boolean isShareMaterialOrgToCompany() {
		return shareMaterialOrgToCompany;
	}

	public void setShareMaterialOrgToCompany(boolean shareMaterialOrgToCompany) {
		this.shareMaterialOrgToCompany = shareMaterialOrgToCompany;
	}

	public boolean isShareCustomerToCompany() {
		return shareCustomerToCompany;
	}

	public void setShareCustomerToCompany(boolean shareCustomerToCompany) {
		this.shareCustomerToCompany = shareCustomerToCompany;
	}

	public boolean isShareCustomerOrgToCompany() {
		return shareCustomerOrgToCompany;
	}

	public void setShareCustomerOrgToCompany(boolean shareCustomerOrgToCompany) {
		this.shareCustomerOrgToCompany = shareCustomerOrgToCompany;
	}

	public boolean isShareSalesmanToCompany() {
		return shareSalesmanToCompany;
	}

	public void setShareSalesmanToCompany(boolean shareSalesmanToCompany) {
		this.shareSalesmanToCompany = shareSalesmanToCompany;
	}

	public boolean isShareWarehouseToCompany() {
		return shareWarehouseToCompany;
	}

	public void setShareWarehouseToCompany(boolean shareWarehouseToCompany) {
		this.shareWarehouseToCompany = shareWarehouseToCompany;
	}

	public boolean isShareVendorToCompany() {
		return shareVendorToCompany;
	}

	public void setShareVendorToCompany(boolean shareVendorToCompany) {
		this.shareVendorToCompany = shareVendorToCompany;
	}

	public boolean isShareCoaToCompany() {
		return shareCoaToCompany;
	}

	public void setShareCoaToCompany(boolean shareCoaToCompany) {
		this.shareCoaToCompany = shareCoaToCompany;
	}

	public boolean isShareCoaOrgToCompany() {
		return shareCoaOrgToCompany;
	}

	public void setShareCoaOrgToCompany(boolean shareCoaOrgToCompany) {
		this.shareCoaOrgToCompany = shareCoaOrgToCompany;
	}

	public boolean isShareTransaksiToCompany() {
		return shareTransaksiToCompany;
	}

	public void setShareTransaksiToCompany(boolean shareTransaksiToCompany) {
		this.shareTransaksiToCompany = shareTransaksiToCompany;
	}

	public boolean isSharePromotionRulesToCompany() {
		return sharePromotionRulesToCompany;
	}

	public void setSharePromotionRulesToCompany(boolean sharePromotionRulesToCompany) {
		this.sharePromotionRulesToCompany = sharePromotionRulesToCompany;
	}

	public boolean isShareDiskonNotaToCompany() {
		return shareDiskonNotaToCompany;
	}

	public void setShareDiskonNotaToCompany(boolean shareDiskonNotaToCompany) {
		this.shareDiskonNotaToCompany = shareDiskonNotaToCompany;
	}

	public boolean isUserOnlyRead_HisDivision_Coa_WhenInput() {
		return userOnlyRead_HisDivision_Coa_WhenInput;
	}

	public void setUserOnlyRead_HisDivision_Coa_WhenInput(boolean userOnlyRead_HisDivision_Coa_WhenInput) {
		this.userOnlyRead_HisDivision_Coa_WhenInput = userOnlyRead_HisDivision_Coa_WhenInput;
	}

	public boolean isNoTax_Trans() {
		return noTax_Trans;
	}

	public void setNoTax_Trans(boolean noTax_Trans) {
		this.noTax_Trans = noTax_Trans;
	}

	public Integer getFcompanyBean() {
		return fcompanyBean;
	}

	public void setFcompanyBean(Integer fcompanyBean) {
		this.fcompanyBean = fcompanyBean;
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

	public boolean isSysvarNomorUrutMasterFollowCorp() {
		return sysvarNomorUrutMasterFollowCorp;
	}

	public void setSysvarNomorUrutMasterFollowCorp(boolean sysvarNomorUrutMasterFollowCorp) {
		this.sysvarNomorUrutMasterFollowCorp = sysvarNomorUrutMasterFollowCorp;
	}

	public boolean isSysvarFormatFakturFollowCorp() {
		return sysvarFormatFakturFollowCorp;
	}

	public void setSysvarFormatFakturFollowCorp(boolean sysvarFormatFakturFollowCorp) {
		this.sysvarFormatFakturFollowCorp = sysvarFormatFakturFollowCorp;
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