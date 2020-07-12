package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


 
@Entity(tableName="acc_balance")
public class AccBalance implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	//ACCOUNT: Dibuat syncron dengan peiode buku
//	@ManyToOne
//	@JoinColumn(name="accPeriodeBukuBean", referencedColumnName="ID")
//	private AccPeriodeBuku accPeriodeBukuBean;
	private Integer accPeriodeBukuBean = 0;

	/*
	 * END: ID of Balance
	 */
	
	//PERIODE
//	@ManyToOne
//	@JoinColumn(name="accAccountBean", referencedColumnName="ID")
//	private AccAccount accAccountBean;
	private Integer accAccountBean = 0;

	
	/*
	 * Cuma dipakai oleh 1 Account yaitu COGS
	 * dan ada Setiap Hari pada satu periode akuntansi
	 * Tidak visible: sulit untuk mengimplementasikan
	 */
	

	@Ignore
	private Double amountMutasiDebit=0.0;
	@Ignore
	private Double amountMutasiKredit=0.0;
	

	//SALDO AWAL: Dibuat Syncrond 
	private Double amountBalanceAwal=0.0;
	//SALDO AKHIR
	private Double amountBalance=0.0;
	
	


	
	
}