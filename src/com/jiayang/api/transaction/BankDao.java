package com.jiayang.api.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankDao {

    public void add(String account, int money, Connection connection) throws SQLException {
        String sql = "update t_bank set money = money  + ? where account = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);

        preparedStatement.executeUpdate();
        System.out.println("added money successfully");

    }

    public void sub(String account, int money, Connection connection) throws SQLException {
        String sql = "update t_bank set money = money - ? where account = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1,money);
        preparedStatement.setObject(2,account);

        preparedStatement.executeUpdate();

        System.out.println("subtract money successfully");

    }
}
