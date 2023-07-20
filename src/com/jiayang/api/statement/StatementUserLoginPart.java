package com.jiayang.api.statement;

import java.sql.*;
import java.util.Scanner;

/**
 * TODO:
 *  1. understand the process of how jdbc works and the way to design the inner api
 *  2. find problem, introduce preparedStatement
 */

public class StatementUserLoginPart {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input account: ");
        String  account = scanner.next();
        System.out.println("please input password: ");
        String password = scanner.next();

        // register driver

        Class.forName("com.mysql.cj.jdbc.Driver");

        // connect to DB

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?user=root&password=123abc");

        // create statement

        Statement statement = connection.createStatement();

        String sql = "select * from t_user where account = '" + account + "'and password = '" + password + "';";

        // get result
        ResultSet resultSet = statement.executeQuery(sql);

        // analyse result

        if(resultSet.next()){
            System.out.println("log in successfully ");
            int id = resultSet.getInt("id");
            account = resultSet.getString("account");
            password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + "--" + account + "--" + password +"--" + nickname);
        }else{
            System.out.println("incorrect password or account");
        }

        // close
        resultSet.close();
        statement.close();
        connection.close();



    }
}
