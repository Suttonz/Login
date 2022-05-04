package com.sutton.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource dataSource;

    static {

        try {
            //load Druid database connection pool settings with classloader
            Properties pros = new Properties();
            InputStream settings = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pros.load(settings);

            // create datasource with DruidDataSourceFactory
            dataSource = DruidDataSourceFactory.createDataSource(pros);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return  dataSource.getConnection();
    }

    public static DataSource getDataSource(){
        return  dataSource;
    }
}
