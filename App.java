package com.jsp.Driver;

import java.sql.Date;
import java.util.Scanner;

import com.jsp.Dao.UserDao;
import com.jsp.Dao.UserDaoHelp;
import com.jsp.Model.BankUserDetails;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to the bank Application..!");
        UserDao userDao = UserDaoHelp.userDao();
        Scanner sc = new Scanner(System.in);
        String response="";
        do {
        System.out.println("Enter \n 1.for Login \n 2.for Registration");
        int choice=sc.nextInt();
        switch(choice) {
        case 1:{
        	System.out.println("Enter tha email id");
        	String email = sc.next();
        	System.out.println("Enter the password");
        	int password =sc.nextInt();
        	BankUserDetails userinfo = userDao.login(email, password);
        	if(userinfo!=null) {
        		System.out.print("Loading"+" ");
        		try {
        			int num=1;
        			while(num<=5) {
        				Thread.sleep(300);
        				System.out.print("."+" ");
        				num++;
        			}
        		}catch(InterruptedException e) {
        			e.printStackTrace();
        		}
        		System.out.println("Enter \n 1. for Credit \n 2. for Debit \n 3.for check statement \n 4. for Check Balance \n 5. for Mobile to Mobile \n 6. for Delete Account ");
        	    int choice1=sc.nextInt();
        	    switch(choice1) {
        	    case 1:{
        	    	//System.out.println("credit opeartion");
        	    	System.out.println("Enter the Account Number :");
        	    	int acc_num =sc.nextInt();
        	    	System.out.println("Enter the password :");
        	    	int password1 =sc.nextInt();
        	    	System.out.println("Enter the amount ypu wat to credit");
        	    	double amount= sc.nextDouble();
        	    	if(acc_num==userinfo.getUseraccountnum()) {
        	    		if(password==password1) {
        	    			if(amount>0) {
        	    				double databaseAmount= userinfo.getUseraccountbalance();
        	    				databaseAmount+=amount;
        	    				userinfo.setUseraccountbalance(databaseAmount);
        	    				if(userDao.credit(amount, userinfo)) {
        	    					try {
        	    						Thread.sleep(1000);
        	    						System.out.print("Loading ");
        	    						for(int i=0;i<=5;i++) {
        	    							Thread.sleep(1000);
        	    							System.out.print(". ");
        	    						}
        	    					}catch (Exception e) {
										// TODO: handle exception
        	    						e.printStackTrace();
									}
        	    					System.out.println();
        	    					System.out.println("Amount Credited Successfully...");
        	    				}
        	    				else {
        	    					System.out.println("Transaction failed....");
        	    				}
        	    			}
        	    		}
        	    	}
        	    	
        	    }
        	    break;
        	    case 2:{
        	    	//System.out.println("debit operation");
        	    	System.out.println("Enter the Account Number :");
        	    	int acc_num =sc.nextInt();
        	    	System.out.println("Enter the password :");
        	    	int password1 =sc.nextInt();
        	    	System.out.println("Enter the amount you want to debit");
        	    	double amount= sc.nextDouble();
        	    	if(acc_num==userinfo.getUseraccountnum()) {
        	    		if(password==password1) {
        	    			if(amount>0) {
        	    				double databaseAmount= userinfo.getUseraccountbalance();
        	    				databaseAmount-=amount;
        	    				userinfo.setUseraccountbalance(databaseAmount);
        	    				if(userDao.debit(amount, userinfo)) {
        	    					try {
        	    						Thread.sleep(1000);
        	    						System.out.print("Loading ");
        	    						for(int i=0;i<=5;i++) {
        	    							Thread.sleep(1000);
        	    							System.out.print(". ");
        	    						}
        	    					}catch (Exception e) {
										// TODO: handle exception
        	    						e.printStackTrace();
									}
        	    					System.out.println();
        	    					System.out.println("Amount debited Successfully...");
        	    				}
        	    				else {
        	    					System.out.println("Transaction failed....");
        	    				}
        	    			}
        	    		}
        	    	}
        	    }
        	    break;
        	    case 3:{
        	    	//System.out.println("check statement");
        	    	System.out.println("Enter the Account Number :");
        	    	int acc_num =sc.nextInt();
        	    	System.out.println("Enter the password :");
        	    	int password1 =sc.nextInt();
        	    	if(acc_num==userinfo.getUseraccountnum()) {
        	    		if(password==password1) {
        	    			userDao.checkStatement(acc_num, password);
        	    		}
        	    		else {
        	    			System.out.println("Invalid password");
        	    		}
        	    	}
        	    	else {
        	    		System.out.println("Invalid account number");
        	    	}
        	    }
        	    break;
        	    case 4:{
        	    	//System.out.println("check balance");
        	    	System.out.println("Enter the Account Number :");
        	    	int acc_num =sc.nextInt();
        	    	System.out.println("Enter the password :");
        	    	int password1 =sc.nextInt();
        	    	if(acc_num==userinfo.getUseraccountnum()) {
        	    		if(password==password1) {
        	    			if(userDao.checkBalance(acc_num, password)) {
        	    			System.out.println("The total amount present is "+userinfo.getUseraccountbalance());
        	    			}
        	    			else {
                	    		System.out.println("Insufficient balane....!");
                	    	}
        	    		}
        	    	}
        	    	
        	    }
        	    break;
        	    case 5:{
        	    	//System.out.println("mobile to mobile");
        	    	System.out.println("Enter the sender's mobile number");
        	    	String senderNum = sc.next();
        	    	System.out.println("Enter the receiver's mobile number");
        	    	String recNum = sc.next();
        	    	System.out.println("Enter the amount you want to transfer");
        	    	double amount = sc.nextDouble();
        	    	if(senderNum.equalsIgnoreCase(userinfo.getUsermobilenumber())) {
        	    		if(amount>0 && userinfo.getUseraccountbalance()>=amount) {
        	    			if(userDao.mobileTomobile(userinfo, recNum,senderNum, amount)) {
        	    			System.out.println("Transaction successfull..");
        	    			}
        	    			else {
        	    				System.out.println("Transaction failed..");
        	    			}
        	    		}
        	    		else {
        	    		System.out.println("No suffiecient funds");
        	    		}
        	    	}
        	    	else {
        	    		System.out.println("Sender phnoe number incorrect");
        	    	}
        	    }
        	    break;
        	    case 6:{
        	    	//System.out.println("delete account");
        	    	System.out.println("Enter the Account Number :");
        	    	int acc_num =sc.nextInt();
        	    	System.out.println("Enter the password :");
        	    	int password1 =sc.nextInt();
        	    	if(acc_num==userinfo.getUseraccountnum()) {
        	    		if(password==password1) {
        	    			if(userDao.deleteAcc(acc_num, password)) {
        	    			System.out.println("Account deleted ");
        	    		}
        	    			else {
                	    		System.out.println("Account not deleted");
                	    	}
        	    	}
        	    		else {
            	    		System.out.println("Account not deleted");
            	    	}
        	    	}
        	    	else {
        	    		System.out.println("Account not deleted");
        	    	}
        	    	
        	    }
        	    break;
        	    default :{
        	    	System.out.println("Enter the valid input");
        	    }
        	    
        	    }
        	}
        	else {
        		System.out.println("Login falied..Enter valid credentials....😥");
        	}
        }
        break;
        case 2:{
        	System.out.println("Enter your name");
        	String name =sc.next();
        	System.out.println("Enter your mobile number");
        	String mobileNo =sc.next();
        	System.out.println("Enter your emailId");
        	String email =sc.next();
        	System.out.println("Enter your gender");
        	String gender= sc.next();
        	System.out.println("Enter your date of birth");
        	String dob =sc.next();
        	Date dateOfBirth =Date.valueOf(dob);
        	System.out.println("Enter your location");
        	String location =sc.next();
        	System.out.println("Enter type of account you want to open");
        	String toc= sc.next();
        	BankUserDetails user = new BankUserDetails();
        	user.setTypeofaccount(toc);
        	user.setUseraddress(location);
        	user.setUserdateofbirth(dateOfBirth);
        	user.setUseremailid(email);
        	user.setUsergender(gender);
        	user.setUsermobilenumber(mobileNo);
        	user.setUsername(name);
        	if(userDao.resitration(user)) {
        		System.out.println("Successfully registered..");
        	}
        	else {
        		System.out.println("Registration fail....");
        	}
        }
        break;
        default :{
        	System.out.println("Enter valid input");
        }
        break;
        }
        System.out.println("Do you want to continue operations press'yes' otherwise 'no'");
        response=sc.next();
        }while(response.equalsIgnoreCase("yes"));
        System.out.println("Thank you for choosing this bank");
        
    }
}
