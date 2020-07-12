package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

 
@Entity(tableName="ftJobdItems")
public class FtJobdItems implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private long id=0;
	
	//Digunakan untuk membentuk Array
	//BARIS
	private Integer noUrut=0;
	//KOLOM
	private Integer kolom=0;


	//CONTENT
	private String contentString1 = "";
	private String contentString2	= "";
	
	private Integer contentInt1=0;
	private Integer contentInt2=0;

	private Double contentDouble1=0.0;
	private Double contentDouble2=0.0;
	
	private boolean contentBol1=false;
	private boolean contentBol2=false;
	
	
	private Date contentTime1 =new Date();
	private Date contentTime2  =new Date();
	
	private String notes = "";
	
    @Ignore
	private Double tempDouble1=0.0;
	
//	@ManyToOne
//	@JoinColumn(name="ftJobhBean", referencedColumnName="refno")
//	private FtJobh ftJobhBean;
	private Integer ftJobhBean = 0;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNoUrut() {
		return noUrut;
	}

	public void setNoUrut(Integer noUrut) {
		this.noUrut = noUrut;
	}

	public Integer getKolom() {
		return kolom;
	}

	public void setKolom(Integer kolom) {
		this.kolom = kolom;
	}

	public String getContentString1() {
		return contentString1;
	}

	public void setContentString1(String contentString1) {
		this.contentString1 = contentString1;
	}

	public String getContentString2() {
		return contentString2;
	}

	public void setContentString2(String contentString2) {
		this.contentString2 = contentString2;
	}

	public Integer getContentInt1() {
		return contentInt1;
	}

	public void setContentInt1(Integer contentInt1) {
		this.contentInt1 = contentInt1;
	}

	public Integer getContentInt2() {
		return contentInt2;
	}

	public void setContentInt2(Integer contentInt2) {
		this.contentInt2 = contentInt2;
	}

	public Double getContentDouble1() {
		return contentDouble1;
	}

	public void setContentDouble1(Double contentDouble1) {
		this.contentDouble1 = contentDouble1;
	}

	public Double getContentDouble2() {
		return contentDouble2;
	}

	public void setContentDouble2(Double contentDouble2) {
		this.contentDouble2 = contentDouble2;
	}

	public boolean isContentBol1() {
		return contentBol1;
	}

	public void setContentBol1(boolean contentBol1) {
		this.contentBol1 = contentBol1;
	}

	public boolean isContentBol2() {
		return contentBol2;
	}

	public void setContentBol2(boolean contentBol2) {
		this.contentBol2 = contentBol2;
	}

	public Date getContentTime1() {
		return contentTime1;
	}

	public void setContentTime1(Date contentTime1) {
		this.contentTime1 = contentTime1;
	}

	public Date getContentTime2() {
		return contentTime2;
	}

	public void setContentTime2(Date contentTime2) {
		this.contentTime2 = contentTime2;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Double getTempDouble1() {
		return tempDouble1;
	}

	public void setTempDouble1(Double tempDouble1) {
		this.tempDouble1 = tempDouble1;
	}

	public Integer getFtJobhBean() {
		return ftJobhBean;
	}

	public void setFtJobhBean(Integer ftJobhBean) {
		this.ftJobhBean = ftJobhBean;
	}
}