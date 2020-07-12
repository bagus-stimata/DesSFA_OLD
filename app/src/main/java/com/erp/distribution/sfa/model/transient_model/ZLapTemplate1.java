package com.erp.distribution.sfa.model.transient_model;


import java.util.Date;

public class ZLapTemplate1 {

	private long ID;
	
	private int noUrut =0;
	
	private	String grup1 ="";
	private	String grup2 ="";
	private	String grup3 ="";

	private	String string1 ="";
	private	String string2 ="";
	private	String string3 ="";
	private	String string4 ="";
	private	String string5 ="";
	private	String string6 ="";
	private	String string7 ="";
	private	String string8 ="";
	private	String string9 ="";
	private	String string10 ="";

	private int int1 =0;
	private int int2 =0;
	private int int3 =0;
	private int int4 =0;
	private int int5 =0;

	
	private double double1 = 0;
	private double double2 = 0;
	private double double3 = 0;
	private double double4 = 0;
	private double double5 = 0;
	

	Date date1;

	Date date2;

	Date date3;
	
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getGrup1() {
		return grup1;
	}
	public void setGrup1(String grup1) {
		this.grup1 = grup1;
	}
	public String getGrup2() {
		return grup2;
	}
	public void setGrup2(String grup2) {
		this.grup2 = grup2;
	}
	public String getGrup3() {
		return grup3;
	}
	public void setGrup3(String grup3) {
		this.grup3 = grup3;
	}
	public String getString1() {
		return string1;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	public String getString2() {
		return string2;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public String getString3() {
		return string3;
	}
	public void setString3(String string3) {
		this.string3 = string3;
	}
	public String getString4() {
		return string4;
	}
	public void setString4(String string4) {
		this.string4 = string4;
	}
	public String getString5() {
		return string5;
	}
	public void setString5(String string5) {
		this.string5 = string5;
	}
	public String getString6() {
		return string6;
	}
	public void setString6(String string6) {
		this.string6 = string6;
	}
	public String getString7() {
		return string7;
	}
	public void setString7(String string7) {
		this.string7 = string7;
	}
	public String getString8() {
		return string8;
	}
	public void setString8(String string8) {
		this.string8 = string8;
	}
	public int getInt1() {
		return int1;
	}
	public void setInt1(int int1) {
		this.int1 = int1;
	}
	public int getInt2() {
		return int2;
	}
	public void setInt2(int int2) {
		this.int2 = int2;
	}
	public int getInt3() {
		return int3;
	}
	public void setInt3(int int3) {
		this.int3 = int3;
	}
	public int getInt4() {
		return int4;
	}
	public void setInt4(int int4) {
		this.int4 = int4;
	}
	public int getInt5() {
		return int5;
	}
	public void setInt5(int int5) {
		this.int5 = int5;
	}
	public double getDouble1() {
		return double1;
	}
	public void setDouble1(double double1) {
		this.double1 = double1;
	}
	public double getDouble2() {
		return double2;
	}
	public void setDouble2(double double2) {
		this.double2 = double2;
	}
	public double getDouble3() {
		return double3;
	}
	public void setDouble3(double double3) {
		this.double3 = double3;
	}
	public double getDouble4() {
		return double4;
	}
	public void setDouble4(double double4) {
		this.double4 = double4;
	}
	public double getDouble5() {
		return double5;
	}
	public void setDouble5(double double5) {
		this.double5 = double5;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public Date getDate3() {
		return date3;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	
	public String getString9() {
		return string9;
	}
	public void setString9(String string9) {
		this.string9 = string9;
	}
	public String getString10() {
		return string10;
	}
	public void setString10(String string10) {
		this.string10 = string10;
	}
	public int getNoUrut() {
		return noUrut;
	}
	public void setNoUrut(int noUrut) {
		this.noUrut = noUrut;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
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
		if (!(obj instanceof ZLapTemplate1)) {
			return false;
		}
		ZLapTemplate1 other = (ZLapTemplate1) obj;
		if (ID != other.ID) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "" + ID + "";
	}
	

	
}
