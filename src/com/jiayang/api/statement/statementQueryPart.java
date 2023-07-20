package com.jiayang.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class statementQueryPart {

    /**
     * TODO:
     *  DriverManager
     *  Connection
     *  Statement
     *  ResultSet
     * @param args
     */

    public static void main(String[] args) throws SQLException {

        /**
         * TODO:
         *  register driver
         *  dependency: driver version 8+ com.mysql.cj.jdbc.Driver
         *              driver version 5+ com.mysql.jdbc.Driver
         */
        //1.register
        DriverManager.registerDriver(new Driver());

        //2. get connected
        /**
         * TODO
         *  java program connects to database
         *  java program, connect to DB, call method, the method needs information about the DB
         *      DB IP address
         *      port number
         *      user name
         *      password
         *      the name of the DB connected
         */
//        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "abc123");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?user=root&password=123abc");
        //3. create statement
        Statement statement = connection.createStatement();

        //4. send sql query and get response
        String sql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(sql);

        //5. analyse the result
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + "--" + account + "--" + password +"--" + nickname);
        }

        //6. close
        resultSet.close();
        statement.close();
        connection.close();
    }
}
