package com.pgis.authorization.entity;

import com.pgis.authorization.common.BaseEntity;

public class UserRole extends BaseEntity{
	private Long userId;
	private Long roleId;
	
	public Long getUserId(){
    	return userId;
    }
    public void setUserId(Long userId){
    	this.userId = userId;
    }
	public Long getRoleId(){
    	return roleId;
    }
    public void setRoleId(Long roleId){
    	this.roleId = roleId;
    }
}
