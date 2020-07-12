package com.erp.distribution.sfa.security_model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(tableName="fUser")
public class FUser implements Serializable {

	@PrimaryKey(autoGenerate = true)
    private int id=0;

	private String email="";

	private String username="";


	//Encripted Password
	private String password="";

	@JsonIgnore
	private String plainPassword = "";

	private String passwordConfirm="";

	private String fullName = "";

	private String phone = "";

	private String notes = "";


	@Ignore
	private List<FUserRoles> fuserRoles;
	@Ignore
    private List<String> tempRoles;

	// @NotBlank
	// @Size(max = 255)
	// private String role;

	private boolean locked = false;

	/**
	 * Tambahan for DES Setting
	 */
	private Integer fdivisionBean = 0;
	private Integer fwarehouseBean = 0;

	@Ignore
	private Integer tempInt1 = 0;
	@Ignore
	private boolean tempBol1 = false;
	@Ignore
	private boolean tempBol2 = false;

	private Date created = new Date();
	private Date lastModified = new Date();
	private String modifiedBy = "";

	
	private void prepareData(){
		this.email = email == null ? null : email.toLowerCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FUser other = (FUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FUser [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<FUserRoles> getFuserRoles() {
		return fuserRoles;
	}

	public void setFuserRoles(List<FUserRoles> fuserRoles) {
		this.fuserRoles = fuserRoles;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Integer getFdivisionBean() {
		return fdivisionBean;
	}

	public void setFdivisionBean(Integer fdivisionBean) {
		this.fdivisionBean = fdivisionBean;
	}

	public Integer getFwarehouseBean() {
		return fwarehouseBean;
	}

	public void setFwarehouseBean(Integer fwarehouseBean) {
		this.fwarehouseBean = fwarehouseBean;
	}

	public Integer getTempInt1() {
		return tempInt1;
	}

	public void setTempInt1(Integer tempInt1) {
		this.tempInt1 = tempInt1;
	}

	public boolean isTempBol1() {
		return tempBol1;
	}

	public void setTempBol1(boolean tempBol1) {
		this.tempBol1 = tempBol1;
	}

	public boolean isTempBol2() {
		return tempBol2;
	}

	public void setTempBol2(boolean tempBol2) {
		this.tempBol2 = tempBol2;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<String> getTempRoles() {
		return tempRoles;
	}

	public void setTempRoles(List<String> tempRoles) {
		this.tempRoles = tempRoles;
	}


	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
}
