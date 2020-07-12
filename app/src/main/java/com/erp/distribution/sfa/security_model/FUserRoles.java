package com.erp.distribution.sfa.security_model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity(tableName="fUserRoles")
public class FUserRoles {

    @PrimaryKey(autoGenerate = true)
    private int id=0;

    
    private String roleID = Role.GUEST; //as default

    @Ignore
    private FUser fuserBean;

    @JsonIgnore
    private Integer fuserBeanInt;


    @Override
    public String toString() {
        return "Todo [description=" + roleID + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((fuserBean == null) ? 0 : fuserBean.hashCode());
        result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        FUserRoles other = (FUserRoles) obj;
        if (fuserBean == null) {
            if (other.fuserBean != null)
                return false;
        } else if (!fuserBean.equals(other.fuserBean))
            return false;
        if (roleID == null) {
            if (other.roleID != null)
                return false;
        } else if (!roleID.equals(other.roleID))
            return false;
        return true;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public FUser getFuserBean() {
        return fuserBean;
    }

    public void setFuserBean(FUser fuserBean) {
        this.fuserBean = fuserBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFuserBeanInt() {
        return fuserBeanInt;
    }

    public void setFuserBeanInt(Integer fuserBeanInt) {
        this.fuserBeanInt = fuserBeanInt;
    }
}