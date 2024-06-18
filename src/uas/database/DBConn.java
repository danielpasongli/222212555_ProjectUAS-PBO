/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.database;


import java.sql.*;

/**
 *
 * @author DANIEL
 */
public final class DBConn {
    private static final String SQLITE_DB_PATH = "db/uas.db";

    static {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            System.out.println("Driver JDBC untuk SQLite berhasil dimuat.");
        } catch (ClassNotFoundException e) {
            System.err.println("Gagal memuat driver JDBC untuk SQLite: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + SQLITE_DB_PATH);
            System.out.println("Koneksi ke database berhasil!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Gagal menyambung ke database: " + e.getMessage());
            throw e; // Lempar pengecualian agar kelas yang memanggil dapat menangani kesalahan
        }
    }
}