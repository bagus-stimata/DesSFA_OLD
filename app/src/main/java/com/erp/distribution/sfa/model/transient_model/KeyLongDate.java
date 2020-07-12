package com.erp.distribution.sfa.model.transient_model;

import java.util.Date;

public class KeyLongDate {
	long ID;
	Date date;
	public KeyLongDate() {}
	public KeyLongDate(long iD, Date date) {
		super();
		ID = iD;
		this.date = date;
	}
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		KeyLongDate other = (KeyLongDate) obj;
		if (ID != other.ID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	

}
