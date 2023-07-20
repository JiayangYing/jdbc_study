package com.jiayang.api.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidUserPart {
    public static void main(String[] args) throws Exception {
        //1. read outer properties file
        Properties properties = new Properties();

        // get the input stream of the file
        InputStream ips = DruidUserPart.class.getClassLoader().getResourceAsStream("druid.properties");

        // read the config file into the properties object
        properties.load(ips);

        //2. use connection pool tool to create connection pool

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //3. get connection
        Connection connection = dataSource.getConnection();

        // 4. sql crud

        // 5. close
        connection.close();
    }
}
