package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumTipeSuratJalan;

import java.io.Serializable;
import java.util.Date;


 
@Entity(tableName="ftSaleshHistorySj")
public class FtSaleshHistorySJ implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long id=0;

	private EnumTipeSuratJalan tipeSuratJalan;
	
	private String sjNumber="";
	private Date sjDate = new Date();

	String driverName = "";
	/*
	 * Di pakai untuk klarifikasi di kemudian hari jika ada yang berusahan untuk menghapus atau melakukan upaya-upaya kecurangan
	 * pada Surat Jalan Pengiriman
	 */
	
	private Integer totalPcsKirimTagih=0;
	//Total saat kirim
	private Double amountAfterDiscPlusRpAfterPpn =0.0;

//	@ManyToOne
//	@JoinColumn(name="ftSaleshBean", referencedColumnName="refno")
//	private FtSalesh ftSaleshBean;
	private Integer ftSaleshBean = 0;




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EnumTipeSuratJalan getTipeSuratJalan() {
		return tipeSuratJalan;
	}

	public void setTipeSuratJalan(EnumTipeSuratJalan tipeSuratJalan) {
		this.tipeSuratJalan = tipeSuratJalan;
	}

	public String getSjNumber() {
		return sjNumber;
	}

	public void setSjNumber(String sjNumber) {
		this.sjNumber = sjNumber;
	}

	public Date getSjDate() {
		return sjDate;
	}

	public void setSjDate(Date sjDate) {
		this.sjDate = sjDate;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Integer getTotalPcsKirimTagih() {
		return totalPcsKirimTagih;
	}

	public void setTotalPcsKirimTagih(Integer totalPcsKirimTagih) {
		this.totalPcsKirimTagih = totalPcsKirimTagih;
	}

	public Double getAmountAfterDiscPlusRpAfterPpn() {
		return amountAfterDiscPlusRpAfterPpn;
	}

	public void setAmountAfterDiscPlusRpAfterPpn(Double amountAfterDiscPlusRpAfterPpn) {
		this.amountAfterDiscPlusRpAfterPpn = amountAfterDiscPlusRpAfterPpn;
	}

	public Integer getFtSaleshBean() {
		return ftSaleshBean;
	}

	public void setFtSaleshBean(Integer ftSaleshBean) {
		this.ftSaleshBean = ftSaleshBean;
	}
}