package com.jiayang.api.preparedstatement;

import java.sql.*;
import java.util.Scanner;

public class PSUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input account: ");
        String  account = scanner.next();
        System.out.println("please input password: ");
        String password = scanner.next();

        // 1. register

        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?user=root&password=123abc");

        // 3. create prepared statement
        String sql = "select * from t_user where account = ? and password = ? ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,account);
        preparedStatement.setObject(2,password);

        // 4. get result set
        ResultSet resultSet = preparedStatement.executeQuery();
        // 5. analyse the result set

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
        preparedStatement.close();
        connection.close();
    }
}
