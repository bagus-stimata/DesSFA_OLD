package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumAccTransactionType;

import java.io.Serializable;
import java.util.Date;


 
@Entity(tableName="accjournalh")
public class AccJournalh implements Serializable{

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

	private String noRek= "";
	
	private Date trDate = new Date();

	private String notes= "";

	@Ignore
	private String tempNotes= "";
	
	private Double amountDebit =0.0;
	private Double amountCredit =0.0;
	
	private boolean posting=false;

	
	private boolean postingTemp=false;

	private boolean endOfDay = false;
	
	//SUMBER J=JOURNAL, BP=Sales Order and Retur, AR=Account Receivable, PO=Purchase, AP=Account Payable
//	@Column(name="SOURCE", length=5)
//	private String source;
	private EnumAccTransactionType accTransactionType;
	/*
	 * Nomor refenrensi dari sumber. pasti berbeda kalau menggunakan refno ini
	 */
	private long sourceRefno = 0;
	

	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID
	

}
