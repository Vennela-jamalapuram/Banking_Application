package com.jsp.Dao;

public class UserDaoHelp {
  public static UserDao userDao() {
	UserDao user = new  UserDaoImpl();
	return user;
  }
}
