package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName="fParamDiskonItemVendor")
public class FParamDiskonItemVendor implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
	private Integer id=0;

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private long sourceID =0;

	//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	private String description="";
	
	//QTY DIMANFAATKAN UNTUK RUPIAH
	private boolean qtyOrRupiah=false; //true = Qty
	
	private Double nominal1=0.0; //Nominal or Qty
	private Double diskon1=0.0;
	private Double diskon1plus=0.0;

	private Double nominal2=0.0;
	private Double diskon2=0.0;
	private Double diskon2plus=0.0;

	private Double nominal3=0.0;
	private Double diskon3=0.0;
	private Double diskon3plus=0.0;

	private Double nominal4=0.0;
	private Double diskon4=0.0;
	private Double diskon4plus=0.0;

	private Double nominal5=0.0;
	private Double diskon5=0.0;
	private Double diskon5plus=0.0;
	

	private boolean allvendor=true;
//	@ManyToOne
//	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
//	private FVendor fvendorBean;
	private Integer fvendorBean = 0;

	private boolean allProductGroup=true;
//	@ManyToOne
//	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
//	private FMaterialGroup3 fmaterialGroup3Bean;
	private Integer fmaterialGroup3Bean = 0;

	private boolean statusActive=true;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getSourceID() {
		return sourceID;
	}

	public void setSourceID(long sourceID) {
		this.sourceID = sourceID;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isQtyOrRupiah() {
		return qtyOrRupiah;
	}

	public void setQtyOrRupiah(boolean qtyOrRupiah) {
		this.qtyOrRupiah = qtyOrRupiah;
	}

	public Double getNominal1() {
		return nominal1;
	}

	public void setNominal1(Double nominal1) {
		this.nominal1 = nominal1;
	}

	public Double getDiskon1() {
		return diskon1;
	}

	public void setDiskon1(Double diskon1) {
		this.diskon1 = diskon1;
	}

	public Double getDiskon1plus() {
		return diskon1plus;
	}

	public void setDiskon1plus(Double diskon1plus) {
		this.diskon1plus = diskon1plus;
	}

	public Double getNominal2() {
		return nominal2;
	}

	public void setNominal2(Double nominal2) {
		this.nominal2 = nominal2;
	}

	public Double getDiskon2() {
		return diskon2;
	}

	public void setDiskon2(Double diskon2) {
		this.diskon2 = diskon2;
	}

	public Double getDiskon2plus() {
		return diskon2plus;
	}

	public void setDiskon2plus(Double diskon2plus) {
		this.diskon2plus = diskon2plus;
	}

	public Double getNominal3() {
		return nominal3;
	}

	public void setNominal3(Double nominal3) {
		this.nominal3 = nominal3;
	}

	public Double getDiskon3() {
		return diskon3;
	}

	public void setDiskon3(Double diskon3) {
		this.diskon3 = diskon3;
	}

	public Double getDiskon3plus() {
		return diskon3plus;
	}

	public void setDiskon3plus(Double diskon3plus) {
		this.diskon3plus = diskon3plus;
	}

	public Double getNominal4() {
		return nominal4;
	}

	public void setNominal4(Double nominal4) {
		this.nominal4 = nominal4;
	}

	public Double getDiskon4() {
		return diskon4;
	}

	public void setDiskon4(Double diskon4) {
		this.diskon4 = diskon4;
	}

	public Double getDiskon4plus() {
		return diskon4plus;
	}

	public void setDiskon4plus(Double diskon4plus) {
		this.diskon4plus = diskon4plus;
	}

	public Double getNominal5() {
		return nominal5;
	}

	public void setNominal5(Double nominal5) {
		this.nominal5 = nominal5;
	}

	public Double getDiskon5() {
		return diskon5;
	}

	public void setDiskon5(Double diskon5) {
		this.diskon5 = diskon5;
	}

	public Double getDiskon5plus() {
		return diskon5plus;
	}

	public void setDiskon5plus(Double diskon5plus) {
		this.diskon5plus = diskon5plus;
	}

	public boolean isAllvendor() {
		return allvendor;
	}

	public void setAllvendor(boolean allvendor) {
		this.allvendor = allvendor;
	}

	public Integer getFvendorBean() {
		return fvendorBean;
	}

	public void setFvendorBean(Integer fvendorBean) {
		this.fvendorBean = fvendorBean;
	}

	public boolean isAllProductGroup() {
		return allProductGroup;
	}

	public void setAllProductGroup(boolean allProductGroup) {
		this.allProductGroup = allProductGroup;
	}

	public Integer getFmaterialGroup3Bean() {
		return fmaterialGroup3Bean;
	}

	public void setFmaterialGroup3Bean(Integer fmaterialGroup3Bean) {
		this.fmaterialGroup3Bean = fmaterialGroup3Bean;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}
}