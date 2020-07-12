package com.erp.distribution.sfa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName="fCustomerPic")
public class FCustomerPic implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@ManyToOne
//	@JoinColumn(name="fcustomerBean")
//	private FCustomer fcustomerBean;
	private Integer fcustomerBean = 0;

	@PrimaryKey(autoGenerate = true)
	private long id = 0;
	/*
	 * Nomor Urut:
	 * 1 = adalah gambar produk depan (Utama)
	 * 2 = adalah gambar produk sisi lain
	 * 3 = adalah gambar produk sisi lain
	 * 4 = aalah gambar produk sisi lain
	 */
	private Integer nomorUrut = 0;

	//DOC, PIC, PDF
	private String tipeFile = "";

	private String imageName = "";

	private String title = "";

	private String description = "";
	
    /*
     * MOBILE = diupload dari mobile
     * APP_WEB = dari aplikasi Web
     */
	private String uploadFrom = "";
	private String imageCapturedBy = "";

	private Date created = new Date();
	private Date modified = new Date();
	private String modifiedBy =""; //User ID




	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getFcustomerBean() {
		return fcustomerBean;
	}

	public void setFcustomerBean(Integer fcustomerBean) {
		this.fcustomerBean = fcustomerBean;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNomorUrut() {
		return nomorUrut;
	}

	public void setNomorUrut(Integer nomorUrut) {
		this.nomorUrut = nomorUrut;
	}

	public String getTipeFile() {
		return tipeFile;
	}

	public void setTipeFile(String tipeFile) {
		this.tipeFile = tipeFile;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUploadFrom() {
		return uploadFrom;
	}

	public void setUploadFrom(String uploadFrom) {
		this.uploadFrom = uploadFrom;
	}

	public String getImageCapturedBy() {
		return imageCapturedBy;
	}

	public void setImageCapturedBy(String imageCapturedBy) {
		this.imageCapturedBy = imageCapturedBy;
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