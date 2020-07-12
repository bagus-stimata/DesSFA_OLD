package com.erp.distribution.sfa.model_acc_cb;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import java.util.Date;



@Entity(tableName="acc_balancehpp")
public class AccBalanceHpp implements Serializable {

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	/*
	 * KESALAHAN DESIGN AWAL, KARENA PENGGUNAAN DIVISI
	 * DIVISI ADALAH DUMMY, JADI ABAIKAN
	 * DI ISI DUMMY
	 * 
	 * KEPUTUSAN UNTUK MEMAKAI DIVISI
	 * dengan Syarat
	 * Jika Divisi Adalah
	 * 
	 * diffCompanyAccount = true;
	 * 
	 */
//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	
	/*
	 * KALAU INI HITUGANNYA PER Akun(cuma Cogs) per Periode, setiap hari: ???
	 * Hitung balance dari COGS pakai jurnal, jadi tidak bisa pakai ini
	 */
//	@ManyToOne
//	@JoinColumn(name="accBalanceBean", referencedColumnName="ID")
//	private AccBalance accBalanceBean;
	
	//##karena HPP maka mengambil dari PRODUCT
//	@ManyToOne
//	@JoinColumn(name="fmaterialBean", referencedColumnName="ID")
//	private FMaterial fmaterialBean;
	private Integer fmaterialBean = 0;

	
	/*
	 * HARI YANG DIPAKAI JUGA PADA HPP
	 * HPP/COGS adalah harga barang Net SEBELUM PPN
	 */
	private Date hppDate;
	
	
	
	/*
	 * Qty Cuma Pembantu:
	 * Qty Diambil dari Qty pada Saldo Stok Real pada Perushaan Tersebut
	 */
	private Integer openingBalanceQty=0;
	private Double openingBalanceTotAmount=0.0;
	
//	@Column(name="OPENINGBALANCE_HPP")
//	private Double openingBalanceHPP=0.0;
	
	
	
	@Ignore
	private Integer purchaseQty=0;
	@Ignore
	private Double purchasePrice=0.0;
	@Ignore
	private Double purchaseTotAmount=0.0;

	
	@Ignore
	private Integer soldQty=0;
	@Ignore
	private Double soldPrice=0.0;
	@Ignore
	private Double soldTotAmount=0.0;
	
	
	/*
	 * Qty Cuma Pembantu:
	 * Qty Diambil dari Qty pada Saldo Stok Real pada Perushaan Tersebut
	 */
	private Integer closingBalanceQty=0;
	private Double closingBalanceTotAmount=0.0;
	
//	@Column(name="CLOSINGBALANCE_HPP")
//	private Double closingBalanceHPP=0.0;
	
	
	
	/*
	 * SALDO AKAN MENGGUNAKAN INI
	 */
	private Double openingBalanceHppAverage =0.0;
	private Double openingBalanceHppFifo =0.0;
	private Double openingBalanceHppLifo =0.0;
	
	private Double closingBalanceHppAverage =0.0;
	private Double closingBalanceHppFifo =0.0;
	private Double closingBalanceHppLifo =0.0;
	
	

	
}