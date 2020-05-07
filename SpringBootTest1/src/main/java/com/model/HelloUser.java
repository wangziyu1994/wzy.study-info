package com.model;

import java.io.Serializable;

public class HelloUser implements Serializable{
private Integer sId;
private String userName;
public Integer getsId() {
	return sId;
}
public void setsId(Integer sId) {
	this.sId = sId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
@Override
public String toString() {
	return "HelloUser [sId=" + sId + ", userName=" + userName + "]";
}
}
