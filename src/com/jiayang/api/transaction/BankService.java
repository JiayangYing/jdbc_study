package com.jiayang.api.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankService {

    public static void  transfer(String addAccount, String subAccount, int money) throws SQLException, ClassNotFoundException {
        // register driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // connect to DB
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?user=root&password=123abc");
        BankDao bankDao = new BankDao();
        try {

            // open transaction
            connection.setAutoCommit(false);// close transaction commit


            bankDao.add(addAccount, money, connection);
            System.out.println("------------");
            bankDao.sub(subAccount,money,connection);

            connection.commit();
        }catch (Exception e){
            connection.rollback();
            connection.close();
            throw e;
        }

    }

    public static void main(String[] args) throws Exception {
        transfer("root", "admin", 100);
    }
}
