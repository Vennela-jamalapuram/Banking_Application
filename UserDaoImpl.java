package com.jsp.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import com.jsp.Model.BankUserDetails;

public class UserDaoImpl implements UserDao{
	String url = "jdbc:mysql://localhost:3306/teca57?user=root&password=12345";
	Random random = new Random();
	@Override
	public BankUserDetails login(String emailid, int password) {
		String select = "select * from bankuserinformation where useremailid=? and userpassword=?";
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, emailid);
			ps.setInt(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				BankUserDetails userinfo = new BankUserDetails();
				if(rs.next()) {
					userinfo.setUserid(rs.getInt(1));
					userinfo.setUsername(rs.getString(2));
					userinfo.setUsermobilenumber(rs.getString(3));
					userinfo.setUseremailid(rs.getString(4));
					userinfo.setUseraddress(rs.getString(5));
					userinfo.setUsergender(rs.getString(6));
					userinfo.setUserdateofbirth(rs.getDate(7));
					userinfo.setTypeofaccount(rs.getString(8));
					userinfo.setUseraccountnum(rs.getInt(9));
					userinfo.setBankifsc_code(rs.getString(10));
					userinfo.setUserpassword(rs.getInt(11));
					userinfo.setUseraccountbalance(rs.getDouble(12));
					return userinfo;
					
				}
			}
			else {
				return null;
			//	System.out.println("Login falied..Enter valid credentials....😥");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean resitration(BankUserDetails userinfo) {
		String insert ="insert into bankuserinformation(username, usermobilenumber, useremailid, useraddress, usergender, userdateofbirth, typeofaccount, useraccountnum, bankifsc_code, userpassword, useraccountbalance) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(insert);
			ps.setString(1, userinfo.getUsername());
			ps.setString(2, userinfo.getUsermobilenumber());
			ps.setString(3, userinfo.getUseremailid());
			ps.setString(4, userinfo.getUseraddress());
			ps.setString(5, userinfo.getUsergender());
			ps.setDate(6, userinfo.getUserdateofbirth());
			ps.setString(7, userinfo.getTypeofaccount());
			int accnum = random.nextInt(10000000);
			if(accnum<1000000) {
				accnum+=1000000;
			}
			ps.setInt(8,accnum);
			ps.setString(9, "SBI546BOB");
			int password = random.nextInt(10000);
			if(password<1000) {
				password+=1000;
			}
			ps.setInt(10, password);
			ps.setDouble(11, userinfo.getUseraccountbalance());
			int result = ps.executeUpdate();
			if(result>0) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean credit(double amount, BankUserDetails user) {
		String update ="update bankuserinformation set useraccountbalance=? where useraccountnum=? and userpassword=?";
		String insert ="insert into statement(username, dateoftransaction, timeoftransaction, useraccountnum,  transactionamount, balanceamount) values(?,?,?,?,?,?) ";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(update);
			ps.setDouble(1, user.getUseraccountbalance());
			ps.setInt(2, user.getUseraccountnum());
			ps.setInt(3, user.getUserpassword());
			int updatedrows1 = ps.executeUpdate();
			if(updatedrows1>0) {
				PreparedStatement ps2 = connection.prepareStatement(insert);
				ps2.setString(1, user.getUsername());
				ps2.setDate(2, Date.valueOf(LocalDate.now()));
				ps2.setTime(3, Time.valueOf(LocalTime.now()));
				ps2.setInt(4, user.getUseraccountnum());
				//System.out.println(user.getUseraccountnum());
				ps2.setDouble(5, amount);
				ps2.setDouble(6, user.getUseraccountbalance());
				int update2Rows = ps2.executeUpdate();
				if(update2Rows>0) {
					return true;
				}
				else {
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean debit(double amount, BankUserDetails user) {
		String update ="update bankuserinformation set useraccountbalance=? where useraccountnum=? and userpassword=?";
		String insert ="insert into statement(username, dateoftransaction, timeoftransaction, useraccountnum,  transactionamount, balanceamount) values(?,?,?,?,?,?) ";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(update);
			ps.setDouble(1,user.getUseraccountbalance());
			ps.setInt(2, user.getUseraccountnum());
			ps.setInt(3, user.getUserpassword());
			int updatedRows = ps.executeUpdate();
			if(updatedRows>0) {
				PreparedStatement ps2 = connection.prepareStatement(insert);
				ps2.setString(1, user.getUsername());
				ps2.setDate(2, Date.valueOf(LocalDate.now()));
				ps2.setTime(3, Time.valueOf(LocalTime.now()));
				ps2.setInt(4, user.getUseraccountnum());
				ps2.setDouble(5, amount);
				ps2.setDouble(6, user.getUseraccountbalance());
				int insertedRows = ps2.executeUpdate();
				if(insertedRows>0) {
					return true;
				}
				else {
					return false;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean checkBalance(int acc_num, int password) {
		String select ="select * from bankuserinformation where useraccountnum=? and userpassword=?";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setInt(1, acc_num);
			ps.setInt(2, password);
			ResultSet rs =  ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
//			if(rows>0) {
//				return true;
//			}
//			else {
//				return false;
//			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteAcc(int acc_num, int password) {
		String delete = "delete from bankuserinformation where useraccountnum=? and userpassword=?";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(delete);
			ps.setInt(1, acc_num);
			ps.setInt(2, password);
			int rows = ps.executeUpdate();
			if(rows>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean mobileTomobile(BankUserDetails user, String receiverNo,String senderNo, double transactionAmount) {
		String select = "select * from bankuserinformation where usermobilenumber=?";
		String update= " update bankuserinformation set useraccountbalance=? where useraccountnum=?";
		String insert = "insert into statement(username, dateoftransaction, timeoftransaction, useraccountnum, transactionamount, balanceamount) values(?,?,?,?,?,?)";
		 try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setString(1, senderNo);
			ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				if(rs.next()) {
					PreparedStatement ps2 = connection.prepareStatement(update);
					double balanceAmount = user.getUseraccountbalance()-transactionAmount;
					ps2.setDouble(1, balanceAmount);
					ps2.setInt(2,user.getUseraccountnum());
					int updateRows = ps2.executeUpdate();
					if(updateRows>0) {
						PreparedStatement ps3 = connection.prepareStatement(insert);
						ps3.setString(1, user.getUsername());
						ps3.setDate(2, Date.valueOf(LocalDate.now()));
						ps3.setTime(3, Time.valueOf(LocalTime.now()));
						ps3.setInt(4, user.getUseraccountnum());
						ps3.setDouble(5,transactionAmount);
						ps3.setDouble(6, balanceAmount);
						int insertedrows = ps3.executeUpdate();
						if(insertedrows>0) {
							PreparedStatement psn = connection.prepareStatement(select);
							psn.setString(1, receiverNo);
							ResultSet rs2 = psn.executeQuery();
							if(rs2.isBeforeFirst()) {
								if(rs2.next()) {
							        
							         PreparedStatement ps4 = connection.prepareStatement(update);
							         balanceAmount = rs2.getDouble("useraccountbalance")+transactionAmount;
							ps4.setDouble(1, balanceAmount);
							ps4.setInt(2, rs2.getInt("useraccountnum"));
							int updatedRows1 = ps4.executeUpdate();
							if(updatedRows1>0) {
								PreparedStatement ps5 = connection.prepareStatement(insert);
								ps5.setString(1, rs2.getString("username"));
								ps5.setDate(2, Date.valueOf(LocalDate.now()));
								ps5.setTime(3, Time.valueOf(LocalTime.now()));
								ps5.setInt(4, rs2.getInt("useraccountnum"));
								ps5.setDouble(5, transactionAmount);
								ps5.setDouble(6, balanceAmount);
								int insertedRows1 = ps5.executeUpdate();
								if(insertedRows1>0) {
									return true;
								}
								else {
									return false;
								}
							}
								}
							}
							else {
								System.out.println("Receiver amount not credited");
							}
						}
						else {
							System.out.println("Statement table not updated");
						}
					}
					else {
						System.out.println("Sender amount not debited");
					}
				}
				else {
					System.out.println("select clause failed");
				}
			}
			else {
				System.out.println("Table empty");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void checkStatement(int acc_num, int password) {
		String select ="select * from bankuserinformation where useraccountnum=? and userpassword=?";
		String select2 ="select * from statement where useraccountnum=?";
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(select);
			ps.setInt(1, acc_num);
			ps.setInt(2, password);
			ResultSet rs =  ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps2 = connection.prepareStatement(select2);
				ps2.setInt(1, rs.getInt("useraccountnum"));
				ResultSet set = ps2.executeQuery();
				if(set.isBeforeFirst()) {
					while(set.next()) {
						System.out.println("user name "+set.getString("username"));
						System.out.println("user dateoftransaction "+set.getDate("dateoftransaction"));
						System.out.println("user timeoftransaction "+set.getTime("timeoftransaction"));
						System.out.println("user useraccountnum "+set.getInt("useraccountnum"));
						System.out.println("user transactionid "+set.getInt("transactionid"));
						System.out.println("user transactionamount "+set.getDouble("transactionamount"));
						System.out.println("user balanceamount"+set.getDouble("balanceamount"));
						System.out.println("--------------------------------------------------------------");
					}
				}
				
			}
			else {
				System.out.println("No user found");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
