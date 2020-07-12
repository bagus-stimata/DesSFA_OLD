package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.erp.distribution.sfa.model.modelenum.EnumTunaiKredit;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName="fParamDiskonNota")
public class FParamDiskonNota implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
	private Integer id =0;

	//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private Integer sourceID =0;

	private String kode1 ="";
	private String kode2 ="";
	
	private String description="";
	
//	@ManyToOne
//	@JoinColumn(name="forFcustomerGroupBean", referencedColumnName="ID")
//	private FCustomerGroup forFcustomerGroupBean;
	private Integer forFcustomerGroupBean = 0;

	private EnumTunaiKredit forTunaiKredit;
			
	private boolean statusActive=true;
	
	/*
	 * dimulai daribyNominal TERKECIL sampai terbesar: Programnya menaik donk
	 */
	private Double buyNominal_1 =0.0;
	private Double disc1Get_1 =0.0;
	private Double disc2Get_1 =0.0;
	private Double discPlusGet_1 =0.0;

	private Double buyNominal_2 =0.0;
	private Double disc1Get_2 =0.0;
	private Double disc2Get_2 =0.0;
	private Double discPlusGet_2 =0.0;

	private Double buyNominal_3 =0.0;
	private Double disc1Get_3 =0.0;
	private Double disc2Get_3 =0.0;
	private Double discPlusGet_3 =0.0;

	private Double buyNominal_4 =0.0;
	private Double disc1Get_4 =0.0;
	private Double disc2Get_4 =0.0;
	private Double discPlusGet_4 =0.0;

	private Double buyNominal_5 =0.0;
	private Double disc1Get_5 =0.0;
	private Double disc2Get_5 =0.0;
	private Double discPlusGet_5 =0.0;

	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
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

	public Integer getForFcustomerGroupBean() {
		return forFcustomerGroupBean;
	}

	public void setForFcustomerGroupBean(Integer forFcustomerGroupBean) {
		this.forFcustomerGroupBean = forFcustomerGroupBean;
	}

	public EnumTunaiKredit getForTunaiKredit() {
		return forTunaiKredit;
	}

	public void setForTunaiKredit(EnumTunaiKredit forTunaiKredit) {
		this.forTunaiKredit = forTunaiKredit;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public Double getBuyNominal_1() {
		return buyNominal_1;
	}

	public void setBuyNominal_1(Double buyNominal_1) {
		this.buyNominal_1 = buyNominal_1;
	}

	public Double getDisc1Get_1() {
		return disc1Get_1;
	}

	public void setDisc1Get_1(Double disc1Get_1) {
		this.disc1Get_1 = disc1Get_1;
	}

	public Double getDisc2Get_1() {
		return disc2Get_1;
	}

	public void setDisc2Get_1(Double disc2Get_1) {
		this.disc2Get_1 = disc2Get_1;
	}

	public Double getDiscPlusGet_1() {
		return discPlusGet_1;
	}

	public void setDiscPlusGet_1(Double discPlusGet_1) {
		this.discPlusGet_1 = discPlusGet_1;
	}

	public Double getBuyNominal_2() {
		return buyNominal_2;
	}

	public void setBuyNominal_2(Double buyNominal_2) {
		this.buyNominal_2 = buyNominal_2;
	}

	public Double getDisc1Get_2() {
		return disc1Get_2;
	}

	public void setDisc1Get_2(Double disc1Get_2) {
		this.disc1Get_2 = disc1Get_2;
	}

	public Double getDisc2Get_2() {
		return disc2Get_2;
	}

	public void setDisc2Get_2(Double disc2Get_2) {
		this.disc2Get_2 = disc2Get_2;
	}

	public Double getDiscPlusGet_2() {
		return discPlusGet_2;
	}

	public void setDiscPlusGet_2(Double discPlusGet_2) {
		this.discPlusGet_2 = discPlusGet_2;
	}

	public Double getBuyNominal_3() {
		return buyNominal_3;
	}

	public void setBuyNominal_3(Double buyNominal_3) {
		this.buyNominal_3 = buyNominal_3;
	}

	public Double getDisc1Get_3() {
		return disc1Get_3;
	}

	public void setDisc1Get_3(Double disc1Get_3) {
		this.disc1Get_3 = disc1Get_3;
	}

	public Double getDisc2Get_3() {
		return disc2Get_3;
	}

	public void setDisc2Get_3(Double disc2Get_3) {
		this.disc2Get_3 = disc2Get_3;
	}

	public Double getDiscPlusGet_3() {
		return discPlusGet_3;
	}

	public void setDiscPlusGet_3(Double discPlusGet_3) {
		this.discPlusGet_3 = discPlusGet_3;
	}

	public Double getBuyNominal_4() {
		return buyNominal_4;
	}

	public void setBuyNominal_4(Double buyNominal_4) {
		this.buyNominal_4 = buyNominal_4;
	}

	public Double getDisc1Get_4() {
		return disc1Get_4;
	}

	public void setDisc1Get_4(Double disc1Get_4) {
		this.disc1Get_4 = disc1Get_4;
	}

	public Double getDisc2Get_4() {
		return disc2Get_4;
	}

	public void setDisc2Get_4(Double disc2Get_4) {
		this.disc2Get_4 = disc2Get_4;
	}

	public Double getDiscPlusGet_4() {
		return discPlusGet_4;
	}

	public void setDiscPlusGet_4(Double discPlusGet_4) {
		this.discPlusGet_4 = discPlusGet_4;
	}

	public Double getBuyNominal_5() {
		return buyNominal_5;
	}

	public void setBuyNominal_5(Double buyNominal_5) {
		this.buyNominal_5 = buyNominal_5;
	}

	public Double getDisc1Get_5() {
		return disc1Get_5;
	}

	public void setDisc1Get_5(Double disc1Get_5) {
		this.disc1Get_5 = disc1Get_5;
	}

	public Double getDisc2Get_5() {
		return disc2Get_5;
	}

	public void setDisc2Get_5(Double disc2Get_5) {
		this.disc2Get_5 = disc2Get_5;
	}

	public Double getDiscPlusGet_5() {
		return discPlusGet_5;
	}

	public void setDiscPlusGet_5(Double discPlusGet_5) {
		this.discPlusGet_5 = discPlusGet_5;
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