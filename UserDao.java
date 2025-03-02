package com.jsp.Dao;

import com.jsp.Model.BankUserDetails;

public interface UserDao {
   BankUserDetails login(String emailid,int password);
   boolean resitration(BankUserDetails userinfo);
   boolean credit(double amount,BankUserDetails user);
   boolean debit(double amount,BankUserDetails user);
   boolean checkBalance(int acc_num,int password);
   boolean mobileTomobile(BankUserDetails user,String receiverNo,String senderNo, double transactionAmount);
   boolean deleteAcc(int acc_num,int password);
void checkStatement(int acc_num, int password);
}
