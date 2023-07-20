package com.jiayang.api.transactionNew;

import java.sql.Connection;
import java.sql.SQLException;
import utils.jdbcUtilsV2;

public class BankService {

    public static void  transfer(String addAccount, String subAccount, int money) throws SQLException, ClassNotFoundException {

        BankDao bankDao = new BankDao();
        Connection connection = jdbcUtilsV2.getConnection();
        try {

            // open transaction
            connection.setAutoCommit(false);// close transaction commit

            bankDao.add(addAccount, money);
            System.out.println("------------");
            bankDao.sub(subAccount,money);

            connection.commit();
        }catch (Exception e){
            connection.rollback();
            throw e;
        }finally {
            jdbcUtilsV2.freeConnection();
        }

    }

    public static void main(String[] args) throws Exception {
        transfer("root", "admin", 100);
    }
}
