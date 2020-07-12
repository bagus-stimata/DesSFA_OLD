package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumRequestStatus;
import com.erp.distribution.sfa.model.modelenum.EnumSalesType;
import com.erp.distribution.sfa.model.modelenum.EnumStatusPengiriman;
import com.erp.distribution.sfa.model.modelenum.EnumTipeFakturJual;
import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Entity(tableName="ftSalesh")
public class FtSalesh implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
	private long refno=0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;
	
	//ORDERNO=SO
	private String orderno="";
	private boolean validOrder = false;
	
	//INVOICENO
	private String invoiceno="";
	
	private Integer priority =0;
		
	/*
	 * ignore/reject promotion rules setting
	 */
	private boolean noPromotionRules = false;
	
	private String taxNumber="";
	private Date taxDate = new Date();

	/*
	 * SO: FROM GOOD RECEIVE
	 */
//	@ManyToOne
//	@JoinColumn(name="fromGoodReceiptBean", referencedColumnName="refno", nullable=true)
//	private  FtPurchaseh fromGoodReceiptBean;
	private Integer fromGoodReceiptBean = 0;

	/*
	 * FAKTUR FROM SO
	 */

//	@ManyToOne
//	@JoinColumn(name="fakturSOBean", referencedColumnName="refno")
//	private  FtSalesh fakturSOBean; //me as Sales Invoice
	private Integer fakturSOBean = 0;

//	@ManyToOne
//	@JoinColumn(name="returAtasFakturBean", referencedColumnName="refno", nullable=true)
//	private  FtSalesh returAtasFakturBean;
	private Integer returAtasFakturBean = 0;


	/*
	 * Status Nota: (1)O-Open Sedang dikirim, (2)T-Terkirim, 
	 * 		(3)P-Pending Pengiriman, (4)B-Batal Nota Batal Seluruhnya
	 */
	private EnumStatusPengiriman statusPengiriman = EnumStatusPengiriman.NOTA_OPEN;

	//SURAT JALAN PENGIRIMAIN = DO
	private String sjPengirimanNo ="";
	private Date sjPengirimanDate =new Date();//Jika tidak ada nomor SJ maka tidak berlaku
		
	//Driver
//	@Column(name="DRIVER_NAME", length=40)
//	private String driverName="";
//	@ManyToOne
//	@JoinColumn(name="driverBean", referencedColumnName="ID", nullable=true)
//	private FSalesman driverBean;
	private Integer driverBean = 0;

	private String nopol ="";
	
	
	/*
	 * HARUS DIGANTI MENGGUNAKAN LIST
	 */
	//SJ Pengiriman, Delivery, SJ Penagihan
//	@Column(name="SJ_AND_DELV_HISTORY", length=250)
//	private String sjAndDelvHistory="";		

	private String sjPenagihanNo ="";
	private Date sjPenagihanDate =new Date();
	
//	@ManyToOne
//	@JoinColumn(name="collectorBean", referencedColumnName="ID", nullable=true)
//	private FSalesman collectorBean;
	private Integer collectorBean = 0;

	private Date invoiceDate=new Date();
	private Date orderDate=new Date();
	
	private Integer top=0;
	private Date dueDate=new Date();
	
	/*
	 * SEPERTINYA KITA TIDAK PAKAI INI
	 * SALDO AWAL ITU ada pada tipe transaksi
	 */
//	@Column(name="SALDO")
//	private boolean saldo=false;
	
	/*
	 * AMOUNT: Amount Setelah Disc1, Disc2, Disc3, +Disc1, +Disc2 pada DETIL (Amount Detil Setelah dipotong Diskon) 
	 */
	private Double amountRp=0.0;
	private Double amountPpnRp=0.0;
	@Ignore
	private Double amountRpAfterPpn=0.0;
	
	/*
	 * berhubungan dengan Account -> menjadi apa
	 * 	Kas (kas Masuk) pada
	 * 		Uang Muka Penjulaan
	 * Dengan Giro/Transfer, dan jika kosong maka Artinya Cash
	 * Asumsi: Uang muka adalah sekali
	 */
//	@Column(name="UANG_MUKA_RP")
//	private Double uangMukaRp=0.0;
//	@ManyToOne
//	@JoinColumn(name="fgiroBean", referencedColumnName="ID", nullable=true)
//	private FGiro fgiroBean;

	/*
	 * ATAS TIDAK DIPAKAI LAGI
	 * Untuk SO saja
	 */
//	@ManyToOne
//	@JoinColumn(name="fuangMuka_SOBean", referencedColumnName="ID", nullable=true)
//	private FUangMuka fuangMuka_SOBean;
	private Integer fuangMuka_SOBean = 0;

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
	
	///Jika yes maka setiap FG yang harganya nol maka akan di hitung akumulasinya, lalu nilainya ditaruh di CashBack
	private boolean calcCashBackFg = false;
	
	/*
	 * Sama dengan bawah: Jangan Lupa
	 */
	//DPP
	private Double amountAfterDiscPlusRp_FG=0.0;

	private Double ppnRp=0.0;
	
	//DPP+PPN
	private Double amountAfterDiscPlusRpAfterPpn_FG =0.0;

	
	//AMOUNT PAY
	private Double amountPayRp=0.0;
	
	private boolean endOfDay=false;

	private boolean openLockInputPriceAndDiscount = false;
	/*
	 * REQUEST PLAFON
	 */
	private EnumRequestStatus statusRequestDiscount = EnumRequestStatus.OPEN;
	
	private EnumRequestStatus statusRequestPlafon = EnumRequestStatus.OPEN;

	private String notes="";
	

	private EnumTunaiKredit tunaiKredit = EnumTunaiKredit.T;
	/* TIPE FAKTUR
	 * F=FAKTUR PENJUALAN STANDART, R= RETURN PENJULAN, FI=PENJUALAN INTERN, 
	 * FDN= DEBIT NOTE MANUAL, RCN=RETUR CREDIT NOTE MANUAL
	 * */	
	private EnumTipeFakturJual tipeFaktur;
	
	/* TIPE JUAL
	 * SHOP=SHOPSALE, TO=TAKING ORDER, C=CANVAS, TF=TASK FORCE, D=DENTED, BS=BAD STOCK
	 * */
	private EnumSalesType salesType;
	
	private Integer printCounter =0;
	
	//ATURAN: update stok dan sumber apakah manual atau tidak
//	@Column(name="SOURCE", length=3)
//	private String source ="";	

	private boolean proses =false;

	private boolean usedSO=false;
	
	//1.Cash 2.Debit 3.Kartu Kredit 4.Cek 5.E-Wallet 6.Lain-lain
	private Integer tipeBayarPos=0;
	private Double amountKasirBayar =0.0;

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

//	@ManyToOne
//	@JoinColumn(name="fsalesmanBean", referencedColumnName="ID", nullable=false)
//	private FSalesman fsalesmanBean;
	private Integer fsalesmanBean = 0;

	/*
	 *	fcustomerBean = Bill To adalah yang melakuan Order Pertama kali
	 *	fcustomerShipToBean = adalah tempat pengiriman barang. jika null maka Shipto adalah BillTo 
	 *	fcustomerPromoToBean = adalah melakukan Switch Pengalihan Promo
	 */
//	@ManyToOne
//	@JoinColumn(name="fcustomerBean", referencedColumnName="ID", nullable=false)
	@Ignore
	@JsonIgnore
	private FCustomer fcustomerBeanTemp;
	private Integer fcustomerBean = 0;

	//Allow Null
//	@ManyToOne
//	@JoinColumn(name="fcustomerShipToBean", referencedColumnName="ID", nullable=true)
//	private FCustomer fcustomerShipToBean;
	private Integer fcustomerShipToBean = 0;

	//Allow Null
//	@ManyToOne
//	@JoinColumn(name="fcustomerPromoToBean", referencedColumnName="ID", nullable=true)
//	private FCustomer fcustomerPromoToBean;
	private Integer fcustomerPromoToBean = 0;

	
	
//	@ManyToOne
//	@JoinColumn(name="fwarehouseBean", referencedColumnName="ID", nullable=false)
//	private FWarehouse fwarehouseBean;
	private Integer fwarehouseBean = 0;

	/*
	 * Account Mapping
	 */
//	@ManyToOne
//	@JoinColumn(name="accAccountArKbBean", referencedColumnName="ID", nullable =true)
//	private AccAccount accAccountArKbBean;
	private Integer accAccountArKbBean = 0;
//	@ManyToOne
//	@JoinColumn(name="accAccountFtSaleshCredit", referencedColumnName="ID", nullable =true)
//	private AccAccount accAccountFtSaleshCredit;
	private Integer accAccountFtSaleshCredit = 0;

	@Ignore
	@JsonIgnore
	private Map<Long, FtSalesdItems> mapFtSalesdTemp = new HashMap<>();
	
	//PEGIRIMAN:
//	@ManyToOne
//	@JoinColumn(name="fexpedisiBean", referencedColumnName="ID", nullable=true)
//	private FExpedisi fexpedisiBean;
	private Integer fexpedisiBean = 0;


	@Ignore
	@JsonIgnore
	private boolean selected;

	/*
	 * MAPPING ACCOUNT
	 * Tidak Berubah Ubah
	 * 1. Tax Account: ikut Mappint Sistem Divisi
	 * 2. Persediaan: Tidak berubah ubah karena defaultnya per-divisi perbarang
	 * 3. COGS/HPP: tidak berubah-ubah karena defaultnya per-divisi perbarang
	 * Bisa di ubah-ubah
	 * 1. Kas Besar
	 * 2. Piutang 
	 */
	
	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID




	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public boolean isValidOrder() {
		return validOrder;
	}

	public void setValidOrder(boolean validOrder) {
		this.validOrder = validOrder;
	}

	public String getInvoiceno() {
		return invoiceno;
	}

	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public boolean isNoPromotionRules() {
		return noPromotionRules;
	}

	public void setNoPromotionRules(boolean noPromotionRules) {
		this.noPromotionRules = noPromotionRules;
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

	public Integer getFromGoodReceiptBean() {
		return fromGoodReceiptBean;
	}

	public void setFromGoodReceiptBean(Integer fromGoodReceiptBean) {
		this.fromGoodReceiptBean = fromGoodReceiptBean;
	}

	public Integer getFakturSOBean() {
		return fakturSOBean;
	}

	public void setFakturSOBean(Integer fakturSOBean) {
		this.fakturSOBean = fakturSOBean;
	}

	public Integer getReturAtasFakturBean() {
		return returAtasFakturBean;
	}

	public void setReturAtasFakturBean(Integer returAtasFakturBean) {
		this.returAtasFakturBean = returAtasFakturBean;
	}

	public EnumStatusPengiriman getStatusPengiriman() {
		return statusPengiriman;
	}

	public void setStatusPengiriman(EnumStatusPengiriman statusPengiriman) {
		this.statusPengiriman = statusPengiriman;
	}

	public String getSjPengirimanNo() {
		return sjPengirimanNo;
	}

	public void setSjPengirimanNo(String sjPengirimanNo) {
		this.sjPengirimanNo = sjPengirimanNo;
	}

	public Date getSjPengirimanDate() {
		return sjPengirimanDate;
	}

	public void setSjPengirimanDate(Date sjPengirimanDate) {
		this.sjPengirimanDate = sjPengirimanDate;
	}

	public Integer getDriverBean() {
		return driverBean;
	}

	public void setDriverBean(Integer driverBean) {
		this.driverBean = driverBean;
	}

	public String getNopol() {
		return nopol;
	}

	public void setNopol(String nopol) {
		this.nopol = nopol;
	}

	public String getSjPenagihanNo() {
		return sjPenagihanNo;
	}

	public void setSjPenagihanNo(String sjPenagihanNo) {
		this.sjPenagihanNo = sjPenagihanNo;
	}

	public Date getSjPenagihanDate() {
		return sjPenagihanDate;
	}

	public void setSjPenagihanDate(Date sjPenagihanDate) {
		this.sjPenagihanDate = sjPenagihanDate;
	}

	public Integer getCollectorBean() {
		return collectorBean;
	}

	public void setCollectorBean(Integer collectorBean) {
		this.collectorBean = collectorBean;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public Integer getFuangMuka_SOBean() {
		return fuangMuka_SOBean;
	}

	public void setFuangMuka_SOBean(Integer fuangMuka_SOBean) {
		this.fuangMuka_SOBean = fuangMuka_SOBean;
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

	public boolean isCalcCashBackFg() {
		return calcCashBackFg;
	}

	public void setCalcCashBackFg(boolean calcCashBackFg) {
		this.calcCashBackFg = calcCashBackFg;
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

	public boolean isOpenLockInputPriceAndDiscount() {
		return openLockInputPriceAndDiscount;
	}

	public void setOpenLockInputPriceAndDiscount(boolean openLockInputPriceAndDiscount) {
		this.openLockInputPriceAndDiscount = openLockInputPriceAndDiscount;
	}

	public EnumRequestStatus getStatusRequestDiscount() {
		return statusRequestDiscount;
	}

	public void setStatusRequestDiscount(EnumRequestStatus statusRequestDiscount) {
		this.statusRequestDiscount = statusRequestDiscount;
	}

	public EnumRequestStatus getStatusRequestPlafon() {
		return statusRequestPlafon;
	}

	public void setStatusRequestPlafon(EnumRequestStatus statusRequestPlafon) {
		this.statusRequestPlafon = statusRequestPlafon;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public EnumTunaiKredit getTunaiKredit() {
		return tunaiKredit;
	}

	public void setTunaiKredit(EnumTunaiKredit tunaiKredit) {
		this.tunaiKredit = tunaiKredit;
	}

	public EnumTipeFakturJual getTipeFaktur() {
		return tipeFaktur;
	}

	public void setTipeFaktur(EnumTipeFakturJual tipeFaktur) {
		this.tipeFaktur = tipeFaktur;
	}

	public EnumSalesType getSalesType() {
		return salesType;
	}

	public void setSalesType(EnumSalesType salesType) {
		this.salesType = salesType;
	}

	public Integer getPrintCounter() {
		return printCounter;
	}

	public void setPrintCounter(Integer printCounter) {
		this.printCounter = printCounter;
	}

	public boolean isProses() {
		return proses;
	}

	public void setProses(boolean proses) {
		this.proses = proses;
	}

	public boolean isUsedSO() {
		return usedSO;
	}

	public void setUsedSO(boolean usedSO) {
		this.usedSO = usedSO;
	}

	public Integer getTipeBayarPos() {
		return tipeBayarPos;
	}

	public void setTipeBayarPos(Integer tipeBayarPos) {
		this.tipeBayarPos = tipeBayarPos;
	}

	public Double getAmountKasirBayar() {
		return amountKasirBayar;
	}

	public void setAmountKasirBayar(Double amountKasirBayar) {
		this.amountKasirBayar = amountKasirBayar;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getFsalesmanBean() {
		return fsalesmanBean;
	}

	public void setFsalesmanBean(Integer fsalesmanBean) {
		this.fsalesmanBean = fsalesmanBean;
	}

	public Integer getFcustomerBean() {
		return fcustomerBean;
	}

	public void setFcustomerBean(Integer fcustomerBean) {
		this.fcustomerBean = fcustomerBean;
	}

	public Integer getFcustomerShipToBean() {
		return fcustomerShipToBean;
	}

	public void setFcustomerShipToBean(Integer fcustomerShipToBean) {
		this.fcustomerShipToBean = fcustomerShipToBean;
	}

	public Integer getFcustomerPromoToBean() {
		return fcustomerPromoToBean;
	}

	public void setFcustomerPromoToBean(Integer fcustomerPromoToBean) {
		this.fcustomerPromoToBean = fcustomerPromoToBean;
	}

	public Integer getFwarehouseBean() {
		return fwarehouseBean;
	}

	public void setFwarehouseBean(Integer fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public Integer getAccAccountArKbBean() {
		return accAccountArKbBean;
	}

	public void setAccAccountArKbBean(Integer accAccountArKbBean) {
		this.accAccountArKbBean = accAccountArKbBean;
	}

	public Integer getAccAccountFtSaleshCredit() {
		return accAccountFtSaleshCredit;
	}

	public void setAccAccountFtSaleshCredit(Integer accAccountFtSaleshCredit) {
		this.accAccountFtSaleshCredit = accAccountFtSaleshCredit;
	}

	public Integer getFexpedisiBean() {
		return fexpedisiBean;
	}

	public void setFexpedisiBean(Integer fexpedisiBean) {
		this.fexpedisiBean = fexpedisiBean;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public FCustomer getFcustomerBeanTemp() {
		return fcustomerBeanTemp;
	}

	public void setFcustomerBeanTemp(FCustomer fcustomerBeanTemp) {
		this.fcustomerBeanTemp = fcustomerBeanTemp;
	}

	public Map<Long, FtSalesdItems> getMapFtSalesdTemp() {
		return mapFtSalesdTemp;
	}

	public void setMapFtSalesdTemp(Map<Long, FtSalesdItems> mapFtSalesdTemp) {
		this.mapFtSalesdTemp = mapFtSalesdTemp;
	}
}