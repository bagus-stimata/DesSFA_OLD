package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType;

import java.io.Serializable;
import java.util.Date;


 
@Entity(tableName="cb_mutasikash")
public class CbMutasiKash implements Serializable{

	
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

//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID", nullable=false)
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	
	private String noRek = "";

	private String cekNumber = "";
	
	private Date trDate = new Date();

	private String payee = "";
	private String notes = "";
	
	
	/*
	 * JIKA: true maka jurnalnya berbeda
	 * 
	 */
	private boolean mutasiKas= false;
	private boolean mutasiAntarPT= false;

	private boolean mutasiKonfirm= false;
	
	/*
	 * Dipakai untuk Settlemen dengan AR vs Kasir
	 */
//	@ManyToOne
//	@JoinColumn(name="payeeBean", referencedColumnName="ID", nullable=true)
//	private FSalesman payeeBean;
	private Integer payeeBean = 0;

	
	private Double amount = 0.0;
	
	private boolean endOfDay = false;

	//SUMBER J=JOURNAL, BP=Sales Order and Retur, AR=Account Receivable, PO=Purchase, AP=Account Payable
//	@Column(name="SOURCE", length=5)
//	private String source= 0;
	private EnumAccTransactionType accTransactionType;
	
	/*
	 * Account Bank Only: Jadi harus difilter
	 */
//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID", nullable=false)
//    private AccAccount accAccountBean;
	private Integer accAccountBean = 0;


	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID

	

}
