package com.jsp.Model;

import java.sql.Date;
import java.sql.Time;

public class BankStatement {
 private String	username;
 private Date dateoftransaction;
 private Time timeoftransaction;
 private int useraccountnum;
 private int transactionid;
 private double transactionamount;
 private double balanceamount;
public BankStatement(String username, Date dateoftransaction, Time timeoftransaction, int useraccountnum,
		int transactionid, double transactionamount, double balanceamount) {
	super();
	this.username = username;
	this.dateoftransaction = dateoftransaction;
	this.timeoftransaction = timeoftransaction;
	this.useraccountnum = useraccountnum;
	this.transactionid = transactionid;
	this.transactionamount = transactionamount;
	this.balanceamount = balanceamount;
}
public BankStatement() {
	super();
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public Date getDateoftransaction() {
	return dateoftransaction;
}
public void setDateoftransaction(Date dateoftransaction) {
	this.dateoftransaction = dateoftransaction;
}
public Time getTimeoftransaction() {
	return timeoftransaction;
}
public void setTimeoftransaction(Time timeoftransaction) {
	this.timeoftransaction = timeoftransaction;
}
public int getUseraccountnum() {
	return useraccountnum;
}
public void setUseraccountnum(int useraccountnum) {
	this.useraccountnum = useraccountnum;
}
public int getTransactionid() {
	return transactionid;
}
public void setTransactionid(int transactionid) {
	this.transactionid = transactionid;
}
public double getTransactionamount() {
	return transactionamount;
}
public void setTransactionamount(double transactionamount) {
	this.transactionamount = transactionamount;
}
public double getBalanceamount() {
	return balanceamount;
}
public void setBalanceamount(double balanceamount) {
	this.balanceamount = balanceamount;
}
@Override
public String toString() {
	return "BankStatement [username=" + username + ", dateoftransaction=" + dateoftransaction + ", timeoftransaction="
			+ timeoftransaction + ", useraccountnum=" + useraccountnum + ", transactionid=" + transactionid
			+ ", transactionamount=" + transactionamount + ", balanceamount=" + balanceamount + "]";
}
 
}
