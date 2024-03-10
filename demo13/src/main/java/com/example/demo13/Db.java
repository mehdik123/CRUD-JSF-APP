package com.example.demo13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

public class Db {

    private  Connection con;

    public Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            String host = "mysql-23d00d43-mehdikamal535-6090.a.aivencloud.com";
            String port = "23469";
            String databaseName = "defaultdb";
            String userName = "avnadmin";
            String password = "AVNS_VcdHEH4QXmrq-__uvtD";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver not found", e);
            }
        }
        return con;
    }


}