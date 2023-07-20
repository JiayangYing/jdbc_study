package com.jiayang.api.preparedstatement;


import org.junit.Test;
import utils.BaseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PSCURDPart {

    @Test
    public void testInsert () throws ClassNotFoundException, SQLException {
        // register driver
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");

        //get connection
        // 2. connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?user=root&password=123abc");

        // 3. create prepared statement
        String sql = "insert into t_user(account, password,nickname) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,"test");
        preparedStatement.setObject(2,"test");
        preparedStatement.setObject(3,"test");

        // send statement
        int i = preparedStatement.executeUpdate();

        if(i > 0){
            System.out.println("upgraded successfully");
        }else System.out.println("failed to upgrade");

        // close
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testInsert02 () throws ClassNotFoundException, SQLException {


        BaseDao baseDao = new BaseDao();
        int i = baseDao.executeUpdate("insert into t_user(account, password,nickname) values(?,?,?)", "test02","test02","test02");
        System.out.println(i);
    }


    @Test
    public void testSelect() throws ClassNotFoundException, SQLException {
        // register driver
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");

        //get connection
        // 2. connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?user=root&password=123abc");

        // 3. create prepared statement
        String sql = "select  * from t_user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        // 4.
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // encapsulate the resultSet
        ArrayList<Map> maps = new ArrayList<>();

        while (resultSet.next()){
            Map<Object, Object> map = new HashMap<>();

            for(int i = 1; i < columnCount; i++){
                Object value = resultSet.getObject(i);

                map.put(metaData.getColumnLabel(i), value);
            }

            System.out.println(map);
            maps.add(map);
        }

    }






}
