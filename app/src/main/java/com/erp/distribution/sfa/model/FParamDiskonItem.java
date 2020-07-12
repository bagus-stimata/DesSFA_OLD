package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


 
 
@Entity(tableName="fParamDiskonItem")
public class FParamDiskonItem implements Serializable {
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

	private String noRek="";
	
	private String description="";

	//	@ManyToOne
//	@JoinColumn(name="fdivisionBean", referencedColumnName="ID")
//	private FDivision fdivisionBean;
	private Integer fdivisionBean = 0;

	//Value
	private Double nominal1 =0.0;
	private Double diskon1 =0.0;
	private Double diskon1plus =0.0;

	private Double nominal2 =0.0;
	private Double diskon2 =0.0;
	private Double diskon2plus =0.0;

	private Double nominal3 =0.0;
	private Double diskon3 =0.0;
	private Double diskon3plus =0.0;

	private Double nominal4 =0.0;
	private Double diskon4 =0.0;
	private Double diskon4plus =0.0;

	private Double nominal5 =0.0;
	private Double diskon5 =0.0;
	private Double diskon5plus =0.0;

	//Qty In PCS
	private Integer qtyLebihDari1 =0;
	private Double diskonFromQty1 =0.0;
	private Double diskonFromQty1plus =0.0;
	
	private Integer qtyLebihDari2 =0;
	private Double diskonFromQty2 =0.0;
	private Double diskonFromQty2plus =0.0;
	
	private Integer qtyLebihDari3 =0;
	private Double diskonFromQty3 =0.0;
	private Double diskonFromQty3plus =0.0;

	private Integer qtyLebihDari4 =0;
	private Double diskonFromQty4 =0.0;
	private Double diskonFromQty4plus =0.0;

	private Integer qtyLebihDari5 =0;
	private Double diskonFromQty5 =0.0;
	private Double diskonFromQty5plus =0.0;
	
	private boolean allvendor=false;
//	@ManyToOne
//	@JoinColumn(name="fvendorBean", referencedColumnName="ID")
//	private FVendor fvendorBean;
	private Integer fvendorBean = 0;

	private boolean allsubgrup=false;
//	@ManyToOne
//	@JoinColumn(name="fcustomerGroupBean", referencedColumnName="ID")
//	private FCustomerGroup fcustomerGroupBean;
	private Integer fcustomerGroupBean = 0;

	private boolean allproductgrup=false;
//	@ManyToOne
//	@JoinColumn(name="fmaterialGroup3Bean", referencedColumnName="ID")
//	private FMaterialGroup3	fmaterialGroup3Bean;
	private Integer fmaterialGroup3Bean = 0;

	private boolean alltunaikredit=false;
	private String tunaikredit="";

	private boolean statusActive=false;

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

	public long getSourceID() {
		return sourceID;
	}

	public void setSourceID(long sourceID) {
		this.sourceID = sourceID;
	}

	public String getNoRek() {
		return noRek;
	}

	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
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

	public Integer getQtyLebihDari1() {
		return qtyLebihDari1;
	}

	public void setQtyLebihDari1(Integer qtyLebihDari1) {
		this.qtyLebihDari1 = qtyLebihDari1;
	}

	public Double getDiskonFromQty1() {
		return diskonFromQty1;
	}

	public void setDiskonFromQty1(Double diskonFromQty1) {
		this.diskonFromQty1 = diskonFromQty1;
	}

	public Double getDiskonFromQty1plus() {
		return diskonFromQty1plus;
	}

	public void setDiskonFromQty1plus(Double diskonFromQty1plus) {
		this.diskonFromQty1plus = diskonFromQty1plus;
	}

	public Integer getQtyLebihDari2() {
		return qtyLebihDari2;
	}

	public void setQtyLebihDari2(Integer qtyLebihDari2) {
		this.qtyLebihDari2 = qtyLebihDari2;
	}

	public Double getDiskonFromQty2() {
		return diskonFromQty2;
	}

	public void setDiskonFromQty2(Double diskonFromQty2) {
		this.diskonFromQty2 = diskonFromQty2;
	}

	public Double getDiskonFromQty2plus() {
		return diskonFromQty2plus;
	}

	public void setDiskonFromQty2plus(Double diskonFromQty2plus) {
		this.diskonFromQty2plus = diskonFromQty2plus;
	}

	public Integer getQtyLebihDari3() {
		return qtyLebihDari3;
	}

	public void setQtyLebihDari3(Integer qtyLebihDari3) {
		this.qtyLebihDari3 = qtyLebihDari3;
	}

	public Double getDiskonFromQty3() {
		return diskonFromQty3;
	}

	public void setDiskonFromQty3(Double diskonFromQty3) {
		this.diskonFromQty3 = diskonFromQty3;
	}

	public Double getDiskonFromQty3plus() {
		return diskonFromQty3plus;
	}

	public void setDiskonFromQty3plus(Double diskonFromQty3plus) {
		this.diskonFromQty3plus = diskonFromQty3plus;
	}

	public Integer getQtyLebihDari4() {
		return qtyLebihDari4;
	}

	public void setQtyLebihDari4(Integer qtyLebihDari4) {
		this.qtyLebihDari4 = qtyLebihDari4;
	}

	public Double getDiskonFromQty4() {
		return diskonFromQty4;
	}

	public void setDiskonFromQty4(Double diskonFromQty4) {
		this.diskonFromQty4 = diskonFromQty4;
	}

	public Double getDiskonFromQty4plus() {
		return diskonFromQty4plus;
	}

	public void setDiskonFromQty4plus(Double diskonFromQty4plus) {
		this.diskonFromQty4plus = diskonFromQty4plus;
	}

	public Integer getQtyLebihDari5() {
		return qtyLebihDari5;
	}

	public void setQtyLebihDari5(Integer qtyLebihDari5) {
		this.qtyLebihDari5 = qtyLebihDari5;
	}

	public Double getDiskonFromQty5() {
		return diskonFromQty5;
	}

	public void setDiskonFromQty5(Double diskonFromQty5) {
		this.diskonFromQty5 = diskonFromQty5;
	}

	public Double getDiskonFromQty5plus() {
		return diskonFromQty5plus;
	}

	public void setDiskonFromQty5plus(Double diskonFromQty5plus) {
		this.diskonFromQty5plus = diskonFromQty5plus;
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

	public boolean isAllsubgrup() {
		return allsubgrup;
	}

	public void setAllsubgrup(boolean allsubgrup) {
		this.allsubgrup = allsubgrup;
	}

	public Integer getFcustomerGroupBean() {
		return fcustomerGroupBean;
	}

	public void setFcustomerGroupBean(Integer fcustomerGroupBean) {
		this.fcustomerGroupBean = fcustomerGroupBean;
	}

	public boolean isAllproductgrup() {
		return allproductgrup;
	}

	public void setAllproductgrup(boolean allproductgrup) {
		this.allproductgrup = allproductgrup;
	}

	public Integer getFmaterialGroup3Bean() {
		return fmaterialGroup3Bean;
	}

	public void setFmaterialGroup3Bean(Integer fmaterialGroup3Bean) {
		this.fmaterialGroup3Bean = fmaterialGroup3Bean;
	}

	public boolean isAlltunaikredit() {
		return alltunaikredit;
	}

	public void setAlltunaikredit(boolean alltunaikredit) {
		this.alltunaikredit = alltunaikredit;
	}

	public String getTunaikredit() {
		return tunaikredit;
	}

	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
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