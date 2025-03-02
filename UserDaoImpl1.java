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

public class UserDaoImpl1{
  String url="jdbc:mysql://localhost:3306/teca57?user=root&password=12345";
	public BankUserDetails login(String emailid, int pasword)
	{
		String select="select * from bankuserinformation where useremailid=? and userpassword=?";
	    try {
//	    	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, emailid);
			ps.setInt(2, pasword);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst())
			{
				BankUserDetails user=new BankUserDetails();
				if(rs.next())
				{
					user.setBankifsc_code(rs.getString("bankifsc_code"));
					user.setTypeofaccount(rs.getString("typeofaccount"));
					user.setUseraccountbalance(rs.getDouble("useraccountbalance"));
					user.setUseraccountnum(rs.getInt("useraccountnum"));
					user.setUseraddress(rs.getString("useraddress"));
				    user.setUserdateofbirth(rs.getDate("userdateofbirth"));
				    user.setUseremailid(rs.getString("useremailid"));
				    user.setUsergender(rs.getString("usergender"));
				    user.setUserid(rs.getInt("userid"));
				    user.setUsermobilenumber(rs.getString("mobileNo"));
				    user.setUsername(rs.getString("username"));
				    user.setUserpassword(rs.getInt("userpassword"));
				    return user;
				}
			}
			else
			{
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean userRegistration(BankUserDetails userinfo) {
		Random random=new Random();
		String insert="insert into bankuserinformation(username, mobileNo, useremailid,"
				+ " useraddress, usergender, userdateofbirth, typeofaccount, useraccountnum, "
				+ "bankifsc_code, userpassword, useraccountbalance) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(insert);
			ps.setString(1, userinfo.getUsername());
			ps.setString(2, userinfo.getUsermobilenumber());
			ps.setString(3, userinfo.getUseremailid());
			ps.setString(4,userinfo.getUseraddress());
			ps.setString(5, userinfo.getUsergender());
			ps.setDate(6, userinfo.getUserdateofbirth());
			ps.setString(7, userinfo.getTypeofaccount());
			int acc_num=random.nextInt(10000000);
			if(acc_num<1000000)
			{
				acc_num+=1000000;
			}
			ps.setInt(8, acc_num);
			ps.setString(9, "JSP2765Bank");
			int password=random.nextInt(10000);
			if(password<1000)
			{
				password+=1000;
			}
			ps.setInt(10, password);
			ps.setDouble(11, userinfo.getUseraccountbalance());
			int updatedRows=ps.executeUpdate();
			if(updatedRows!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean credit(double amount, BankUserDetails userinfo) {
		String update="update bankuserinformation set useraccountbalance=? where useraccountnum=? and userpassword=?";
		String insert="insert into statement(username, dateoftransaction, timeoftransaction, useraccountnum,transactionamount, balanceamount) values (?,?,?,?,?,?)";
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps1=connection.prepareStatement(insert);
		    ps1.setDouble(1,userinfo.getUseraccountbalance());
		    ps1.setInt(2, userinfo.getUseraccountnum());
		    ps1.setInt(3, userinfo.getUserpassword());
		    int updateRow1=ps1.executeUpdate();
		    if(updateRow1!=0)
		    {
		    	PreparedStatement ps2=connection.prepareStatement(insert);
		    	ps2.setString(1, userinfo.getUsername());
		    	ps2.setDate(2, Date.valueOf(LocalDate.now()));
		    	ps2.setTime(3, Time.valueOf(LocalTime.now()));
		    	ps2.setInt(4, userinfo.getUseraccountnum());
		    	ps2.setDouble(5, amount);
		    	ps2.setDouble(6, userinfo.getUseraccountbalance());
		    	int updatedRows2=ps2.executeUpdate();
		    	if(updatedRows2!=0)
		    	{
		    		return true;
		    	}
		    	else
		    	{
		    		return false;
		    	}
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean mobileToMobileTransaction(BankUserDetails userinfo, String receiversphno, String senderNo,
			double amount) {
		
		String select="select * from bankuserinformation where usermobilenum=?";
		String update="update bankuserinformation set useraccountbalance=? where useraccountnum=?";
		String insert="insert into statement(username, dateoftransaction, timeoftransaction, useraccountnum, transactionamount, balanceamount) values(?,?,?,?,?,?)";
		try {
			Connection connectcion=DriverManager.getConnection(url);
			PreparedStatement ps=connectcion.prepareStatement(select);
			ps.setString(1, senderNo);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst())
			{
				if(rs.next())
				{
					PreparedStatement ps2=connectcion.prepareStatement(update);
					double balanceAmount=userinfo.getUseraccountbalance()-amount;
					ps2.setDouble(1, balanceAmount);
					ps2.setInt(2, userinfo.getUseraccountnum());
					int updatedRows=ps2.executeUpdate();
					if(updatedRows!=0)
					{
						PreparedStatement ps3=connectcion.prepareStatement(insert);
						ps3.setString(1,userinfo.getUsername());
						ps3.setDate(2, Date.valueOf(LocalDate.now()));
						ps3.setTime(3, Time.valueOf(LocalTime.now()));
						ps3.setInt(4, userinfo.getUseraccountnum());
						ps3.setDouble(5, amount);
						ps3.setDouble(6, balanceAmount);
						int insertedrows=ps3.executeUpdate();
						if(insertedrows>0)
						{
							PreparedStatement psn=connectcion.prepareStatement(select);
							ps.setString(1, receiversphno);
							ResultSet rs2=ps.executeQuery();
							if(rs2.isBeforeFirst())
							{
								if(rs2.next())
								{
									PreparedStatement ps4=connectcion.prepareStatement(update);
									balanceAmount=rs2.getDouble("useraccountbalance")+amount;
									ps4.setDouble(1, balanceAmount);
									ps4.setInt(2, rs2.getInt("useraccountnum"));
									int updatedRow1=ps4.executeUpdate();
									if(updatedRow1>0)
									{
										PreparedStatement ps5=connectcion.prepareStatement(insert);
										ps5.setString(1, rs2.getString("username"));
										ps5.setDate(2, Date.valueOf(LocalDate.now()));
										ps5.setTime(3, Time.valueOf(LocalTime.now()));
										ps5.setInt(4,rs2.getInt("useraccountnum"));
										ps5.setDouble(5, amount);
										ps5.setDouble(6, balanceAmount);
									     int insertedRow1=ps5.executeUpdate();
									     if(insertedRow1>0)
									     {
									    	 return true;
									     }
									     else
									     {
									    	 return false;
									     }
									}
								}
							}
							else
							{
								return false;
							}
						}
					}
				}
				else
				{
					System.out.println("Receive amount not credited");
				}
			}
			else
			{
				System.out.println("Statement table not updated");
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
				
	}
	public boolean debit(double amount, BankUserDetails userinfo) {
		// TODO Auto-generated method stub
		return false;
	}
	public void checkStatement(int acc_num, int password) {
		// TODO Auto-generated method stub
		
	}
	public boolean checkBalance(int acc_num, int password) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean deleteAcc(int acc_num, int password) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
