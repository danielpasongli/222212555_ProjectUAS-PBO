/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.database;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import uas.model.Pengaduan;
import uas.model.User;

/**
 *
 * @author DANIEL
 */
public class Database implements Serializable {
    private static Database instance;
    
    // Konstruktor privat untuk mencegah pembuatan instance dari kelas ini oleh kelas lain
    private Database() {}

    // Metode yang disinkronisasi untuk mengendalikan akses simultan
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    // Mendaftarkan pengguna baru ke database
    public boolean registerUser(String username, String password, String nip, String fullName) {
        // Memeriksa apakah pengguna sudah ada
        if (userExists(username, nip)) {
            return false;
        }

        // Meng-hash password menggunakan BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String sql = "INSERT INTO users (nip, username, password, fullname, status) VALUES (?, ?, ?, ?, ?)";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nip);
            pstmt.setString(2, username);
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, fullName);
            pstmt.setInt(5, 0);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting new user: " + e.getMessage());
            return false;
        }
    }
    
    // Memeriksa apakah pengguna dengan username atau nip tertentu sudah ada
    private boolean userExists(String username, String nip) {
        String sql = "SELECT 1 FROM users WHERE username = ? OR nip = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, nip);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking if user exists: " + e.getMessage());
            return false;
        }
    }
    
    // Memeriksa kredensial pengguna
    public boolean checkCredentials(String username, String password) {
        String sql = "SELECT password FROM users WHERE username = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    return BCrypt.checkpw(password, hashedPassword);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking credentials: " + e.getMessage());
        }
        return false;
    }
    
    // Menghasilkan kode laporan berikutnya
    public String generateNextKodeLaporan() {
        String sql = "SELECT max(id) as kodeTerbesar FROM pengaduan";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String kodeTerbesar = rs.getString("kodeTerbesar");
                if (kodeTerbesar != null) {
                    int urutan = Integer.parseInt(kodeTerbesar.substring(2)) + 1;
                    return String.format("LP%04d", urutan);
                }
                return "LP0001";
            }
        } catch (SQLException e) {
            System.err.println("Error generating next kode laporan: " + e.getMessage());
        }
        return null;
    }
    
    // Mendapatkan pengaduan berdasarkan nomor pengaduan
    public Pengaduan getPengaduanByNomor(String nomorPengaduan) {
        String sql = "SELECT * FROM pengaduan WHERE id = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nomorPengaduan);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractPengaduanFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting pengaduan by nomor: " + e.getMessage());
        }
        return null;
    }

    
    // Menyimpan pengaduan baru ke database
    public boolean savePengaduan(Pengaduan pengaduan) {
        String sql = "INSERT INTO pengaduan (id, nama_pelapor, jabatan_pelapor, lokasi_kerusakan, deskripsi, tgl_lapor, status, catatan_petugas, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pengaduan.getID());
            pstmt.setString(2, pengaduan.getNamaPelapor());
            pstmt.setString(3, pengaduan.getJabatan());
            pstmt.setString(4, pengaduan.getLokasiKerusakan());
            pstmt.setString(5, pengaduan.getDeskripsi());
            pstmt.setString(6, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pengaduan.getTglLapor()));
            pstmt.setString(7, "Sedang diajukan");
            pstmt.setString(8, "-");
            pstmt.setBytes(9, pengaduan.getFoto());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saving pengaduan: " + e.getMessage());
            return false;
        }
    }
    
    // Mendapatkan daftar pengaduan berdasarkan status
    public List<Pengaduan> getPengaduanByStatus(String status) {
        String sql = "SELECT * FROM pengaduan WHERE status = ?";
        List<Pengaduan> pengaduanList = new ArrayList<>();

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pengaduanList.add(extractPengaduanFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting pengaduan by status: " + e.getMessage());
        }
        return pengaduanList;
    }

    // Mencari pengaduan berdasarkan status dan teks pencarian
    public List<Pengaduan> searchPengaduanByStatusAndText(String status, String searchText) {
        String sql = "SELECT * FROM pengaduan WHERE status = ? AND (id LIKE ? OR nama_pelapor LIKE ? OR lokasi_kerusakan LIKE ? OR deskripsi LIKE ? OR strftime('%Y-%m-%d', tgl_lapor) LIKE ?)";
        List<Pengaduan> pengaduanList = new ArrayList<>();

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            String likeSearchText = "%" + searchText + "%";
            pstmt.setString(2, likeSearchText);
            pstmt.setString(3, likeSearchText);
            pstmt.setString(4, likeSearchText);
            pstmt.setString(5, likeSearchText);
            pstmt.setString(6, likeSearchText);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pengaduanList.add(extractPengaduanFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching pengaduan by status and text: " + e.getMessage());
        }
        return pengaduanList;
    }

    // Ekstraksi data pengaduan dari ResultSet
    private Pengaduan extractPengaduanFromResultSet(ResultSet rs) throws SQLException {
        return new Pengaduan(
                rs.getString("id"),
                rs.getString("nama_pelapor"),
                rs.getString("jabatan_pelapor"),
                rs.getString("lokasi_kerusakan"),
                rs.getBytes("foto"),
                rs.getString("deskripsi"),
                rs.getTimestamp("tgl_lapor"),
                rs.getString("status"),
                rs.getString("catatan_petugas")
        );
    }
    
    // Memperbarui pengaduan di database
    public boolean updatePengaduan(Pengaduan pengaduan) {
        String sql = "UPDATE pengaduan SET status = ?, catatan_petugas = ? WHERE id = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pengaduan.getStatus());
            pstmt.setString(2, pengaduan.getCatatanPetugas());
            pstmt.setString(3, pengaduan.getID());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating pengaduan: " + e.getMessage());
            return false;
        }
    }
    
    // Menghapus pengaduan berdasarkan ID
    public boolean deletePengaduanById(String id) {
        String sql = "DELETE FROM pengaduan WHERE id = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting pengaduan: " + e.getMessage());
            return false;
        }
    }
    
    // Memperbarui password pengguna di database
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
            return false;
        }
    }
    
    // Mendapatkan pengguna berdasarkan username
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setUserID(rs.getString("nip"));
                    user.setName(rs.getString("fullname"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user by username: " + e.getMessage());
        }
        return null;
    }
    
    // Metode untuk otentikasi pengguna
    public User authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ?";

        // Menggunakan try-with-resources untuk menangani koneksi dan statement
        try (Connection conn = DBConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        return new User(
                            rs.getString("nip"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("fullname"),
                            rs.getInt("status")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user: " + e.getMessage());
        }
        return null;
    }
    
}
