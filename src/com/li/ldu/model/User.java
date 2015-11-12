package com.li.ldu.model;

import cn.bmob.v3.BmobUser;

@SuppressWarnings("serial")
public class User extends BmobUser {

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
