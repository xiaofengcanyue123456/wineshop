package com.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class C3P0Utils {

    private static ComboPooledDataSource ds=null;
    static{
        ds=new ComboPooledDataSource();
    }



    //获取数据源
//    public static DataSource getDataSource(){
//        try {
//            ds.setDriverClass("com.mysql.cj.jdbc.Driver");
//            ds.setJdbcUrl("jdbc:mysql://localhost:3306/wineshop?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
//            ds.setUser("root");
//            ds.setPassword("root");
//            ds.setAcquireIncrement(5);
//            ds.setInitialPoolSize(10);
//            ds.setMinPoolSize(5);
//            ds.setMaxPoolSize(20);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        return ds;
//    }
    //获取一个连接
    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = ds.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 关闭数据库连接
     */
    public static void closeConn(Connection conn) {
        try {
            if (conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



//    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

//    public static Connection getConnection() {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try {
//            return dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new ExceptionInInitializerError("初始化失败，请检查配置文件");
//        }
//    }
//
//
//    /**
//     * 关闭连接对象
//     * @param conn
//     * @param stmt
//     * @param rs
//     */
//    public static void release(Connection conn, Statement stmt, ResultSet rs) {
//        if(rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            rs = null; //赶紧垃圾回收
//        }
//        if(stmt != null) {
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            stmt = null;
//        }
//        if(conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            conn = null;
//        }
//    }
}
