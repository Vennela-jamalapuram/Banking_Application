package com.jsp.Model;

import java.sql.Date;

public class BankUserDetails {
  private int userid;
  private String username;
  private String usermobilenumber;
  private String useremailid;
  private String useraddress;
  private String usergender;
  private Date userdateofbirth;
  private String typeofaccount;
  private int useraccountnum;
  private String bankifsc_code;
  private int userpassword;
  private double useraccountbalance;
public BankUserDetails(int userid, String username, String usermobilenumber, String useremailid, String useraddress,
		String usergender, Date userdateofbirth, String typeofaccount, int useraccountnum, String bankifsc_code,
		int userpassword, double useraccountbalance) {
	super();
	this.userid = userid;
	this.username = username;
	this.usermobilenumber = usermobilenumber;
	this.useremailid = useremailid;
	this.useraddress = useraddress;
	this.usergender = usergender;
	this.userdateofbirth = userdateofbirth;
	this.typeofaccount = typeofaccount;
	this.useraccountnum = useraccountnum;
	this.bankifsc_code = bankifsc_code;
	this.userpassword = userpassword;
	this.useraccountbalance = useraccountbalance;
}
public BankUserDetails() {
	super();
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUsermobilenumber() {
	return usermobilenumber;
}
public void setUsermobilenumber(String usermobilenumber) {
	this.usermobilenumber = usermobilenumber;
}
public String getUseremailid() {
	return useremailid;
}
public void setUseremailid(String useremailid) {
	this.useremailid = useremailid;
}
public String getUseraddress() {
	return useraddress;
}
public void setUseraddress(String useraddress) {
	this.useraddress = useraddress;
}
public String getUsergender() {
	return usergender;
}
public void setUsergender(String usergender) {
	this.usergender = usergender;
}
public Date getUserdateofbirth() {
	return userdateofbirth;
}
public void setUserdateofbirth(Date userdateofbirth) {
	this.userdateofbirth = userdateofbirth;
}
public String getTypeofaccount() {
	return typeofaccount;
}
public void setTypeofaccount(String typeofaccount) {
	this.typeofaccount = typeofaccount;
}
public int getUseraccountnum() {
	return useraccountnum;
}
public void setUseraccountnum(int useraccountnum) {
	this.useraccountnum = useraccountnum;
}
public String getBankifsc_code() {
	return bankifsc_code;
}
public void setBankifsc_code(String bankifsc_code) {
	this.bankifsc_code = bankifsc_code;
}
public int getUserpassword() {
	return userpassword;
}
public void setUserpassword(int userpassword) {
	this.userpassword = userpassword;
}
public double getUseraccountbalance() {
	return useraccountbalance;
}
public void setUseraccountbalance(double useraccountbalance) {
	this.useraccountbalance = useraccountbalance;
}
@Override
public String toString() {
	return "BankUserDetails [userid=" + userid + ", username=" + username + ", usermobilenumber=" + usermobilenumber
			+ ", useremailid=" + useremailid + ", useraddress=" + useraddress + ", usergender=" + usergender
			+ ", userdateofbirth=" + userdateofbirth + ", typeofaccount=" + typeofaccount + ", useraccountnum="
			+ useraccountnum + ", bankifsc_code=" + bankifsc_code + ", userpassword=" + userpassword
			+ ", useraccountbalance=" + useraccountbalance + "]";
}
  
}
