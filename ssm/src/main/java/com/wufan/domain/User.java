package com.wufan.domain;

import java.io.Serializable;

/**
 * 用户信息
 */
public class User implements Serializable {
	private Long id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String idcard;
	private String address;
	private String sex;
	private String department;
	private String education;
	private String fork;
	private boolean isRemember;

	// 构造函数
	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getFork() {
		return fork;
	}

	public void setFork(String fork) {
		this.fork = fork;
	}

	public boolean isRemember() {
		return isRemember;
	}

	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone + ", email="
				+ email + ", idcard=" + idcard + ", address=" + address + ", sex=" + sex + ", department=" + department
				+ ", education=" + education + ", fork=" + fork + "]";
	}

}