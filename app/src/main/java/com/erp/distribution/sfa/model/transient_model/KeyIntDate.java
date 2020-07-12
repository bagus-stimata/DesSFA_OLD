package com.erp.distribution.sfa.model.transient_model;

import java.util.Date;

public class KeyIntDate {
	int ID;
	Date date;
	public KeyIntDate() {}
	public KeyIntDate(int iD, Date date) {
		super();
		ID = iD;
		this.date = date;
	}
	
	
	public long getID() {
		return ID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setID(int iD) {
		ID = iD;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (!(obj instanceof KeyIntDate)) {
			return false;
		}
		KeyIntDate other = (KeyIntDate) obj;
		if (ID != other.ID) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		return true;
	}
	
	

}
