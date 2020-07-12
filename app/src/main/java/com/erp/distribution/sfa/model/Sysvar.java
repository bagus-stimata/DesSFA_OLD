/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

 
 
@Entity(tableName="sysvar")
public class Sysvar implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey(autoGenerate = true)
    private Integer id	=0;

	private String sysvarId = "";

	/*
	 * JIKA COPY DARI TEMPAT LAIN: MAKA SEBAGAI LOG TRACK MENINGGALKAN SOURCE_ID = ID sumber asal dia dicopy
	 * keperluan diantaranya:
	 * 1. Clone Database. karena tidak mungkin menggunakan Kode External yang bisa jadi kemungkinan kembar, tapi harus pakai kode internal
	 * 2. 
	 */
	private String sourceID = "";

	private int nomorUrut=0;
	
	private boolean visible = true;
	
    private String groupSysvar ="";
    
    private String deskripsi ="";
    private String notes ="";
    private String tipeData ="";
    private int lenghtData =0;

    private String prefix = "";
    private String suffix = "";

    
    private String nilaiString1 ="";
    private String nilaiString2 ="";
    private Boolean nilaiBol1 =false;
    private Boolean nilaiBol2 =false;

    private int nilaiInt1 = 0;
    private int nilaiInt2=0;
    private double nilaiDouble1 = 0;
    private double nilaiDouble2 = 0;
    
    private Date nilaiDate1;
    private Date nilaiDate2;
    
    private Date nilaiTime1;
    private Date nilaiTime2;

    /*
     * DIPAKAI UNTUK 
     * Level 1= Level Aplikasi
     * Level 2= Level Company
     * Level 3= Level Division
     */
	 
//	private FCompany fcompanyBean;
	private Integer fcompanyBean;

	 
//	private FDivision fdivisionBean;
	private Integer fdivisionBean;

	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID

	//** End Tools
   

	@Override
	public String toString() {
		return id + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fcompanyBean == null) ? 0 : fcompanyBean.hashCode());
		result = prime * result + ((fdivisionBean == null) ? 0 : fdivisionBean.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Sysvar)) {
			return false;
		}
		Sysvar other = (Sysvar) obj;
		if (fcompanyBean == null) {
			if (other.fcompanyBean != null) {
				return false;
			}
		} else if (!fcompanyBean.equals(other.fcompanyBean)) {
			return false;
		}
		if (fdivisionBean == null) {
			if (other.fdivisionBean != null) {
				return false;
			}
		} else if (!fdivisionBean.equals(other.fdivisionBean)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSysvarId() {
		return sysvarId;
	}

	public void setSysvarId(String sysvarId) {
		this.sysvarId = sysvarId;
	}

	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}

	public int getNomorUrut() {
		return nomorUrut;
	}

	public void setNomorUrut(int nomorUrut) {
		this.nomorUrut = nomorUrut;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getGroupSysvar() {
		return groupSysvar;
	}

	public void setGroupSysvar(String groupSysvar) {
		this.groupSysvar = groupSysvar;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTipeData() {
		return tipeData;
	}

	public void setTipeData(String tipeData) {
		this.tipeData = tipeData;
	}

	public int getLenghtData() {
		return lenghtData;
	}

	public void setLenghtData(int lenghtData) {
		this.lenghtData = lenghtData;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getNilaiString1() {
		return nilaiString1;
	}

	public void setNilaiString1(String nilaiString1) {
		this.nilaiString1 = nilaiString1;
	}

	public String getNilaiString2() {
		return nilaiString2;
	}

	public void setNilaiString2(String nilaiString2) {
		this.nilaiString2 = nilaiString2;
	}

	public Boolean getNilaiBol1() {
		return nilaiBol1;
	}

	public void setNilaiBol1(Boolean nilaiBol1) {
		this.nilaiBol1 = nilaiBol1;
	}

	public Boolean getNilaiBol2() {
		return nilaiBol2;
	}

	public void setNilaiBol2(Boolean nilaiBol2) {
		this.nilaiBol2 = nilaiBol2;
	}

	public int getNilaiInt1() {
		return nilaiInt1;
	}

	public void setNilaiInt1(int nilaiInt1) {
		this.nilaiInt1 = nilaiInt1;
	}

	public int getNilaiInt2() {
		return nilaiInt2;
	}

	public void setNilaiInt2(int nilaiInt2) {
		this.nilaiInt2 = nilaiInt2;
	}

	public double getNilaiDouble1() {
		return nilaiDouble1;
	}

	public void setNilaiDouble1(double nilaiDouble1) {
		this.nilaiDouble1 = nilaiDouble1;
	}

	public double getNilaiDouble2() {
		return nilaiDouble2;
	}

	public void setNilaiDouble2(double nilaiDouble2) {
		this.nilaiDouble2 = nilaiDouble2;
	}

	public Date getNilaiDate1() {
		return nilaiDate1;
	}

	public void setNilaiDate1(Date nilaiDate1) {
		this.nilaiDate1 = nilaiDate1;
	}

	public Date getNilaiDate2() {
		return nilaiDate2;
	}

	public void setNilaiDate2(Date nilaiDate2) {
		this.nilaiDate2 = nilaiDate2;
	}

	public Date getNilaiTime1() {
		return nilaiTime1;
	}

	public void setNilaiTime1(Date nilaiTime1) {
		this.nilaiTime1 = nilaiTime1;
	}

	public Date getNilaiTime2() {
		return nilaiTime2;
	}

	public void setNilaiTime2(Date nilaiTime2) {
		this.nilaiTime2 = nilaiTime2;
	}

	public Integer getFcompanyBean() {
		return fcompanyBean;
	}

	public void setFcompanyBean(Integer fcompanyBean) {
		this.fcompanyBean = fcompanyBean;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
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
