package cms.utils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {

    public int executeUpdate(String sql, Object... params) throws SQLException{
        //get connection
        Connection connection = jdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int length = params.length;

        for(int i = 1; i <= length; i++){
            preparedStatement.setObject(i,params[i-1]);
        }

        int i = preparedStatement.executeUpdate();

        preparedStatement.close();

        if(connection.getAutoCommit()){
            jdbcUtilsV2.freeConnection();
        }

        return i;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Connection connection = jdbcUtilsV2.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        // 4.
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // encapsulate the resultSet
        ArrayList<T>  list = new ArrayList<>();

        while (resultSet.next()){
            T t = clazz.newInstance();
            for(int i = 1; i <= columnCount; i++){
                Object object = resultSet.getObject(i);
                String columnLabel = metaData.getColumnLabel(i);
                Field declaredField = clazz.getDeclaredField(columnLabel);
                declaredField.setAccessible(true);
                declaredField.set(t,object);
            }

            list.add(t);
        }

        resultSet.close();
        preparedStatement.close();
        if (connection.getAutoCommit()){
            jdbcUtilsV2.freeConnection();
        }
        return list;
    }



}
