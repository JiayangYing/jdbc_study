package com.jiayang.api.transactionNew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.jdbcUtilsV2;
public class BankDao {

    public void add(String account, int money) throws SQLException {

        String sql = "update t_bank set money = money  + ? where account = ? ;";
        Connection connection = jdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);

        preparedStatement.executeUpdate();
        System.out.println("added money successfully");

    }

    public void sub(String account, int money) throws SQLException {
        String sql = "update t_bank set money = money - ? where account = ? ;";
        Connection connection = jdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);

        preparedStatement.executeUpdate();

        System.out.println("subtract money successfully");

    }
}
