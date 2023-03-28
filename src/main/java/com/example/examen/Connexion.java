package com.example.examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    static String user = "haroun";
    static String password = "passer";
    static String url = "jdbc:mysql://localhost/mabase";
    static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getCon(){
        Connection con = null;
        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con;
    }
}