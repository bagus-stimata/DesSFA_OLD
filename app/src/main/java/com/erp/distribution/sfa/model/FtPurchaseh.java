package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumRequestStatus;
import com.erp.distribution.sfa.model.modelenum.EnumStatusUpload;
import com.erp.distribution.sfa.model.modelenum.EnumTipeFakturBeli;
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName="ftPurchaseh")
public class FtPurchaseh implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long refno= 0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;
	/*
	 * TRANSAKSI BISA DIMULAI DARI PURCHASE INVOICE ATAU PO
	 */
	private String nopo="";
	private String invoiceno="";

	private Date poDate=new Date();

	private Integer lamaCredit =0;

	private EnumRequestStatus requestStatus = EnumRequestStatus.OPEN;

	/*
	 * TANGGAL BARANG DITERIMA: yang akan menjadi STOK dan HPP(Accounting)
	 */
	private String goodReceiptNo="";
	
	private Date goodReceiptDate=new Date();
	
	/*
	 * Tanggal Invoice yang akan menjadi 
	 * 1. Account Payable
	 * 2. dan Menjadi Tanggal pada Faktur Pajak
	 */
	private Date invoiceDate =new Date();
	private Date dueDate=new Date();
	
	/*
	 * Posting Date atau Journal Date dipakai untuk alternatif tanggal posting
	 * Sesuai dengan referensi SAP
	 * 
	 */
	private Date postingDate = new Date();
	
	private String taxNumber="";
	/*
	 * Tax Date adalah tanggal Faktur Pajak Masuk Jurnal
	 */
	private Date taxDate = new Date();
	/*
	 * Defaultnya adalah mengikuti nomor invoice
	 * Tapi bisa diubah-ubah sesuai bulan upload faktur pajak
	 */
	private Integer masaPajak = 0;
	
	private EnumStatusUpload statusUpload = EnumStatusUpload.OPEN;
	
//	@Column(name = "RETUR_ATAS_FAKTUR", length = 30)
//	private String returAtasFaktur ="";
	
	/*
	 * DIBAYAR TUNAI ATAU SESUAI DENGAN AKUNNYA
	 * PADA LAPORAN HUTANG: Akan dianggal Sudah Lunas dan dilewati
	 */
	private boolean RtrDibayarTunai =false;

	/*
	 * FAKTUR FROM PO
	 */

//	@ManyToOne
//	@JoinColumn(name="fakturPOBean", referencedColumnName="refno", nullable = true)
//	private  FtPurchaseh fakturPOBean; //me as good receipt
	private Integer fakturPOBean = 0;

	
	/*
	 * FAKTUR FROM GR
	 */
//	@ManyToOne
//	@JoinColumn(name="fakturGRBean", referencedColumnName="refno", nullable = true)
//	private  FtPurchaseh fakturGRBean; //me as good receipt
	private Integer fakturGRBean = 0;


//	@ManyToOne
//	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable = true)
//	private  FtPurchaseh returAtasFakturBean; //me as Retur
	private Integer returAtasFakturBean = 0;

	//RETUR ATAS FAKTUR	
	private EnumTunaiKredit tunaiKredit = EnumTunaiKredit.T;
	/* TIPE FAKTUR
	 * F=FAKTUR PEMBELIAN STANDART, R= RETURN PEMBELIAN DARI PABRIK, 
	 * FI=PEMBELIAN INTERN, INFG=BARANG DATANG FREE GOOD
	 * PO=PEMBELIAN to Factory
	 * */
	private EnumTipeFakturBeli tipeFaktur;
		
	/*
	 * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon) 
	 */
	private Double amountRp=0.0;
	private Double amountPpnRp=0.0;
	@Ignore
	private Double amountRpAfterPpn=0.0;
	
	/*
	 * berhubungan dengan Account -> menjadi apa
	 * 	Uang Muka Pembelian pada
	 * 		Kas (kas keluar)
	 * Dengan Giro/Transfer, dan jika kosong maka Artinya Cash
	 * Asumsi: Uang muka adalah sekali
	 */
	private Double uangMukaRp=0.0;
//	@ManyToOne
//	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
//	private FGiro fgiroBean;
	private Integer fgiroBean = 0;

	
	/*
	 * ATAS TIDAK DIPAKAI LAGI
	 * Untuk PO saja
	 */
//	@ManyToOne
//	@JoinColumn(name="fuangMuka_POBean", referencedColumnName="ID", nullable=true)
//	private FUangMuka fuangMuka_POBean;
	private Integer fuangMuka_POBean = 0;

	
	private Double disc1=0.0;
	@Ignore
	private Double disc1Rp=0.0;
	@Ignore
	private Double disc1PpnRp=0.0;
	@Ignore
	private Double disc1RpAfterPpn=0.0;

	//###TAMBAHAN
	@Ignore
	private Double amountAfterDisc1Rp=0.0;
	@Ignore
	private Double amountAfterDisc1PpnRp=0.0;
	@Ignore
	private Double amountAfterDisc1RpAfterPpn=0.0;
	
	private Double disc2=0.0;
	@Ignore
	private Double disc2Rp=0.0;
	@Ignore
	private Double disc2PpnRp=0.0;
	@Ignore
	private Double disc2RpAfterPpn=0.0;
	
	//AMOUNT AFTER DISC1 dan DISC2 dan DiscPlus
	@Ignore
	private Double amountAfterDisc2Rp=0.0;
	@Ignore
	private Double amountAfterDisc2PpnRp=0.0;
	@Ignore
	private Double amountAfterDisc2RpAfterPpn=0.0;
		
	private Double discPlus_FG=0.0;
	@Ignore
	private Double discPlusRp_FG=0.0;
	@Ignore
	private Double discPlusPpnRp_FG=0.0;
	@Ignore
	private Double discPlusRpAfterPpn_FG=0.0;
	/*
	 * Sama dengan bawah: Jangan Lupa
	 */
	//DPP: Pindah Bawah
//	@Column(name="AMOUNT_AFTER_DISC_PLUS")
//	private Double amountAfterDiscPlusRp_FG=0.0;

	/*
	 * TIDAK ADA YANG PAKAI SEMENTARA KITA NON AKTIFKAN
	 */
	@Ignore
	private Double amountAfterDiscPlusFgRp=0.0;
	@Ignore
	private Double amountAfterDiscPlusFgPpnRp=0.0;
	@Ignore
	private Double amountAfterDiscPlusFgRpAfterPpn=0.0;

	
	//======== TAMBAHAN ===========
	private Double discExclCogs=0.0;
	@Ignore
	private Double discExclCogsRp=0.0;
	@Ignore
	private Double discExclCogsPpnRp=0.0;
	@Ignore
	private Double discExclCogsRpAfterPpn=0.0;
	
	
	
	/*
	 * PINDAHAN DARI ATAS: DAN TERPAKASA NAMANYA SEPERTI INI
	 * Sama dengan bawah: Jangan Lupa
	 */
	//DPP
	private Double amountAfterDiscPlusRp_FG=0.0;
	
//	@Column(name="PPN_PERCENT")
//	private Double ppnPercent=0.0;
//	@Ignore
	private Double ppnRp=0.0;
	
	//DPP+PPN
	private Double amountAfterDiscPlusRpAfterPpn_FG =0.0; //Sama dengan amountAfterDisc spt dibawah
	
//	//AMOUNT AFTER DISC
//	@Column(name="AMOUNT_AFTERDISC_ALL")
//	private Double amountAfterDiscAll=0.0;
//	@Column(name="AMOUNT_AFTERDISC_ALL_AFTERPPN")
//	private Double amountAfterDiscAllAfterPpn=0.0;
	
	private Boolean saldo=false;
	
	//AMOUNT PAY
	private Double amountPayRp=0.0;
	
	private boolean endOfDay=false;

	private boolean usedGrInv=false;
	
	private boolean usedSO=false;
	
	private Integer printCounter=0;
	
	private boolean lunas=false;

	
	//ATURAN: update stok dan sumber apakah manual atau tidak
	private String source="";
	private boolean proses=false;
	
	private String notes="";

	private String shipTo="";
	
	private String billTo="";
	
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fvendorBean", referencedColumnName="ID", nullable=false)
//	private FVendor fvendorBean;
	private Integer fvendorBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
//	private FWarehouse fwarehouseBean;
	private Integer fwarehouseBean = 0;

	/*
	 * Account Mapping
	 */
//	@ManyToOne
//	@JoinColumn(name="accAccountApKbBean", referencedColumnName="ID", nullable = false)
//	private AccAccount accAccountApKbBean;
	private Integer accAccountApKbBean = 0;
//	@ManyToOne
//	@JoinColumn(name="accAccountApKbBean_SecondStep", referencedColumnName="ID", nullable = false)
//	private AccAccount accAccountApKbBean_SecondStep;
	private Integer accAccountApKbBean_SecondStep = 0;

	//Akun Potongan Khusus
//	@ManyToOne
//	@JoinColumn(name="accAccNonCogsFgBean", referencedColumnName="ID", nullable = true)
//	private AccAccount accAccNonCogsFgBean;
	private Integer accAccNonCogsFgBean = 0;
//	@ManyToOne
//	@JoinColumn(name="accAccNonCogsDiscBean", referencedColumnName="ID", nullable = true)
//	private AccAccount accAcNonCogsDiscBean;
	private Integer accAccNonCogsDiscBean = 0;


	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID

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

	public String getNopo() {
		return nopo;
	}

	public void setNopo(String nopo) {
		this.nopo = nopo;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public Date getPoDate() {
		return poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public Integer getLamaCredit() {
		return lamaCredit;
	}

	public void setLamaCredit(Integer lamaCredit) {
		this.lamaCredit = lamaCredit;
	}

	public EnumRequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(EnumRequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getGoodReceiptNo() {
		return goodReceiptNo;
	}

	public void setGoodReceiptNo(String goodReceiptNo) {
		this.goodReceiptNo = goodReceiptNo;
	}

	public Date getGoodReceiptDate() {
		return goodReceiptDate;
	}

	public void setGoodReceiptDate(Date goodReceiptDate) {
		this.goodReceiptDate = goodReceiptDate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public Date getTaxDate() {
		return taxDate;
	}

	public void setTaxDate(Date taxDate) {
		this.taxDate = taxDate;
	}

	public Integer getMasaPajak() {
		return masaPajak;
	}

	public void setMasaPajak(Integer masaPajak) {
		this.masaPajak = masaPajak;
	}

	public EnumStatusUpload getStatusUpload() {
		return statusUpload;
	}

	public void setStatusUpload(EnumStatusUpload statusUpload) {
		this.statusUpload = statusUpload;
	}

	public boolean isRtrDibayarTunai() {
		return RtrDibayarTunai;
	}

	public void setRtrDibayarTunai(boolean rtrDibayarTunai) {
		RtrDibayarTunai = rtrDibayarTunai;
	}

	public Integer getFakturPOBean() {
		return fakturPOBean;
	}

	public void setFakturPOBean(Integer fakturPOBean) {
		this.fakturPOBean = fakturPOBean;
	}

	public Integer getFakturGRBean() {
		return fakturGRBean;
	}

	public void setFakturGRBean(Integer fakturGRBean) {
		this.fakturGRBean = fakturGRBean;
	}

	public Integer getReturAtasFakturBean() {
		return returAtasFakturBean;
	}

	public void setReturAtasFakturBean(Integer returAtasFakturBean) {
		this.returAtasFakturBean = returAtasFakturBean;
	}

	public EnumTunaiKredit getTunaiKredit() {
		return tunaiKredit;
	}

	public void setTunaiKredit(EnumTunaiKredit tunaiKredit) {
		this.tunaiKredit = tunaiKredit;
	}

	public EnumTipeFakturBeli getTipeFaktur() {
		return tipeFaktur;
	}

	public void setTipeFaktur(EnumTipeFakturBeli tipeFaktur) {
		this.tipeFaktur = tipeFaktur;
	}

	public Double getAmountRp() {
		return amountRp;
	}

	public void setAmountRp(Double amountRp) {
		this.amountRp = amountRp;
	}

	public Double getAmountPpnRp() {
		return amountPpnRp;
	}

	public void setAmountPpnRp(Double amountPpnRp) {
		this.amountPpnRp = amountPpnRp;
	}

	public Double getAmountRpAfterPpn() {
		return amountRpAfterPpn;
	}

	public void setAmountRpAfterPpn(Double amountRpAfterPpn) {
		this.amountRpAfterPpn = amountRpAfterPpn;
	}

	public Double getUangMukaRp() {
		return uangMukaRp;
	}

	public void setUangMukaRp(Double uangMukaRp) {
		this.uangMukaRp = uangMukaRp;
	}

	public Integer getFgiroBean() {
		return fgiroBean;
	}

	public void setFgiroBean(Integer fgiroBean) {
		this.fgiroBean = fgiroBean;
	}

	public Integer getFuangMuka_POBean() {
		return fuangMuka_POBean;
	}

	public void setFuangMuka_POBean(Integer fuangMuka_POBean) {
		this.fuangMuka_POBean = fuangMuka_POBean;
	}

	public Double getDisc1() {
		return disc1;
	}

	public void setDisc1(Double disc1) {
		this.disc1 = disc1;
	}

	public Double getDisc1Rp() {
		return disc1Rp;
	}

	public void setDisc1Rp(Double disc1Rp) {
		this.disc1Rp = disc1Rp;
	}

	public Double getDisc1PpnRp() {
		return disc1PpnRp;
	}

	public void setDisc1PpnRp(Double disc1PpnRp) {
		this.disc1PpnRp = disc1PpnRp;
	}

	public Double getDisc1RpAfterPpn() {
		return disc1RpAfterPpn;
	}

	public void setDisc1RpAfterPpn(Double disc1RpAfterPpn) {
		this.disc1RpAfterPpn = disc1RpAfterPpn;
	}

	public Double getAmountAfterDisc1Rp() {
		return amountAfterDisc1Rp;
	}

	public void setAmountAfterDisc1Rp(Double amountAfterDisc1Rp) {
		this.amountAfterDisc1Rp = amountAfterDisc1Rp;
	}

	public Double getAmountAfterDisc1PpnRp() {
		return amountAfterDisc1PpnRp;
	}

	public void setAmountAfterDisc1PpnRp(Double amountAfterDisc1PpnRp) {
		this.amountAfterDisc1PpnRp = amountAfterDisc1PpnRp;
	}

	public Double getAmountAfterDisc1RpAfterPpn() {
		return amountAfterDisc1RpAfterPpn;
	}

	public void setAmountAfterDisc1RpAfterPpn(Double amountAfterDisc1RpAfterPpn) {
		this.amountAfterDisc1RpAfterPpn = amountAfterDisc1RpAfterPpn;
	}

	public Double getDisc2() {
		return disc2;
	}

	public void setDisc2(Double disc2) {
		this.disc2 = disc2;
	}

	public Double getDisc2Rp() {
		return disc2Rp;
	}

	public void setDisc2Rp(Double disc2Rp) {
		this.disc2Rp = disc2Rp;
	}

	public Double getDisc2PpnRp() {
		return disc2PpnRp;
	}

	public void setDisc2PpnRp(Double disc2PpnRp) {
		this.disc2PpnRp = disc2PpnRp;
	}

	public Double getDisc2RpAfterPpn() {
		return disc2RpAfterPpn;
	}

	public void setDisc2RpAfterPpn(Double disc2RpAfterPpn) {
		this.disc2RpAfterPpn = disc2RpAfterPpn;
	}

	public Double getAmountAfterDisc2Rp() {
		return amountAfterDisc2Rp;
	}

	public void setAmountAfterDisc2Rp(Double amountAfterDisc2Rp) {
		this.amountAfterDisc2Rp = amountAfterDisc2Rp;
	}

	public Double getAmountAfterDisc2PpnRp() {
		return amountAfterDisc2PpnRp;
	}

	public void setAmountAfterDisc2PpnRp(Double amountAfterDisc2PpnRp) {
		this.amountAfterDisc2PpnRp = amountAfterDisc2PpnRp;
	}

	public Double getAmountAfterDisc2RpAfterPpn() {
		return amountAfterDisc2RpAfterPpn;
	}

	public void setAmountAfterDisc2RpAfterPpn(Double amountAfterDisc2RpAfterPpn) {
		this.amountAfterDisc2RpAfterPpn = amountAfterDisc2RpAfterPpn;
	}

	public Double getDiscPlus_FG() {
		return discPlus_FG;
	}

	public void setDiscPlus_FG(Double discPlus_FG) {
		this.discPlus_FG = discPlus_FG;
	}

	public Double getDiscPlusRp_FG() {
		return discPlusRp_FG;
	}

	public void setDiscPlusRp_FG(Double discPlusRp_FG) {
		this.discPlusRp_FG = discPlusRp_FG;
	}

	public Double getDiscPlusPpnRp_FG() {
		return discPlusPpnRp_FG;
	}

	public void setDiscPlusPpnRp_FG(Double discPlusPpnRp_FG) {
		this.discPlusPpnRp_FG = discPlusPpnRp_FG;
	}

	public Double getDiscPlusRpAfterPpn_FG() {
		return discPlusRpAfterPpn_FG;
	}

	public void setDiscPlusRpAfterPpn_FG(Double discPlusRpAfterPpn_FG) {
		this.discPlusRpAfterPpn_FG = discPlusRpAfterPpn_FG;
	}

	public Double getAmountAfterDiscPlusFgRp() {
		return amountAfterDiscPlusFgRp;
	}

	public void setAmountAfterDiscPlusFgRp(Double amountAfterDiscPlusFgRp) {
		this.amountAfterDiscPlusFgRp = amountAfterDiscPlusFgRp;
	}

	public Double getAmountAfterDiscPlusFgPpnRp() {
		return amountAfterDiscPlusFgPpnRp;
	}

	public void setAmountAfterDiscPlusFgPpnRp(Double amountAfterDiscPlusFgPpnRp) {
		this.amountAfterDiscPlusFgPpnRp = amountAfterDiscPlusFgPpnRp;
	}

	public Double getAmountAfterDiscPlusFgRpAfterPpn() {
		return amountAfterDiscPlusFgRpAfterPpn;
	}

	public void setAmountAfterDiscPlusFgRpAfterPpn(Double amountAfterDiscPlusFgRpAfterPpn) {
		this.amountAfterDiscPlusFgRpAfterPpn = amountAfterDiscPlusFgRpAfterPpn;
	}

	public Double getDiscExclCogs() {
		return discExclCogs;
	}

	public void setDiscExclCogs(Double discExclCogs) {
		this.discExclCogs = discExclCogs;
	}

	public Double getDiscExclCogsRp() {
		return discExclCogsRp;
	}

	public void setDiscExclCogsRp(Double discExclCogsRp) {
		this.discExclCogsRp = discExclCogsRp;
	}

	public Double getDiscExclCogsPpnRp() {
		return discExclCogsPpnRp;
	}

	public void setDiscExclCogsPpnRp(Double discExclCogsPpnRp) {
		this.discExclCogsPpnRp = discExclCogsPpnRp;
	}

	public Double getDiscExclCogsRpAfterPpn() {
		return discExclCogsRpAfterPpn;
	}

	public void setDiscExclCogsRpAfterPpn(Double discExclCogsRpAfterPpn) {
		this.discExclCogsRpAfterPpn = discExclCogsRpAfterPpn;
	}

	public Double getAmountAfterDiscPlusRp_FG() {
		return amountAfterDiscPlusRp_FG;
	}

	public void setAmountAfterDiscPlusRp_FG(Double amountAfterDiscPlusRp_FG) {
		this.amountAfterDiscPlusRp_FG = amountAfterDiscPlusRp_FG;
	}

	public Double getPpnRp() {
		return ppnRp;
	}

	public void setPpnRp(Double ppnRp) {
		this.ppnRp = ppnRp;
	}

	public Double getAmountAfterDiscPlusRpAfterPpn_FG() {
		return amountAfterDiscPlusRpAfterPpn_FG;
	}

	public void setAmountAfterDiscPlusRpAfterPpn_FG(Double amountAfterDiscPlusRpAfterPpn_FG) {
		this.amountAfterDiscPlusRpAfterPpn_FG = amountAfterDiscPlusRpAfterPpn_FG;
	}

	public Boolean getSaldo() {
		return saldo;
	}

	public void setSaldo(Boolean saldo) {
		this.saldo = saldo;
	}

	public Double getAmountPayRp() {
		return amountPayRp;
	}

	public void setAmountPayRp(Double amountPayRp) {
		this.amountPayRp = amountPayRp;
	}

	public boolean isEndOfDay() {
		return endOfDay;
	}

	public void setEndOfDay(boolean endOfDay) {
		this.endOfDay = endOfDay;
	}

	public boolean isUsedGrInv() {
		return usedGrInv;
	}

	public void setUsedGrInv(boolean usedGrInv) {
		this.usedGrInv = usedGrInv;
	}

	public boolean isUsedSO() {
		return usedSO;
	}

	public void setUsedSO(boolean usedSO) {
		this.usedSO = usedSO;
	}

	public Integer getPrintCounter() {
		return printCounter;
	}

	public void setPrintCounter(Integer printCounter) {
		this.printCounter = printCounter;
	}

	public boolean isLunas() {
		return lunas;
	}

	public void setLunas(boolean lunas) {
		this.lunas = lunas;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isProses() {
		return proses;
	}

	public void setProses(boolean proses) {
		this.proses = proses;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getFvendorBean() {
		return fvendorBean;
	}

	public void setFvendorBean(Integer fvendorBean) {
		this.fvendorBean = fvendorBean;
	}

	public Integer getFwarehouseBean() {
		return fwarehouseBean;
	}

	public void setFwarehouseBean(Integer fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public Integer getAccAccountApKbBean() {
		return accAccountApKbBean;
	}

	public void setAccAccountApKbBean(Integer accAccountApKbBean) {
		this.accAccountApKbBean = accAccountApKbBean;
	}

	public Integer getAccAccountApKbBean_SecondStep() {
		return accAccountApKbBean_SecondStep;
	}

	public void setAccAccountApKbBean_SecondStep(Integer accAccountApKbBean_SecondStep) {
		this.accAccountApKbBean_SecondStep = accAccountApKbBean_SecondStep;
	}

	public Integer getAccAccNonCogsFgBean() {
		return accAccNonCogsFgBean;
	}

	public void setAccAccNonCogsFgBean(Integer accAccNonCogsFgBean) {
		this.accAccNonCogsFgBean = accAccNonCogsFgBean;
	}

	public Integer getAccAccNonCogsDiscBean() {
		return accAccNonCogsDiscBean;
	}

	public void setAccAccNonCogsDiscBean(Integer accAccNonCogsDiscBean) {
		this.accAccNonCogsDiscBean = accAccNonCogsDiscBean;
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