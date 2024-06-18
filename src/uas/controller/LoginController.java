/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import uas.database.*;
import uas.view.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import uas.model.User;

/**
 *
 * @author DANIEL
 */
public class LoginController implements ActionListener, MouseListener {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private MainFrame mainFrame;
    private JLabel showPasswordLabel;
    private boolean isPasswordVisible = false;

    public LoginController(MainFrame mainFrame, JTextField usernameTextField, JPasswordField passwordField, JLabel showPasswordLabel) {
        this.mainFrame = mainFrame;
        this.usernameTextField = usernameTextField;
        this.passwordField = passwordField;
        this.showPasswordLabel = showPasswordLabel;
        Database.getInstance(); // Mengambil instance dari singleton database
        this.showPasswordLabel.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = getUsername();
        String password = new String(getPassword());

        // Validasi input username dan password
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username dan password harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Database dbInstance = Database.getInstance();
        User user = dbInstance.authenticate(username, password); // Otentikasi pengguna

        if (user != null) {
            JOptionPane.showMessageDialog(null, "Login berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);

            // Simpan pengguna yang sedang login ke mainFrame
            mainFrame.setCurrentUser(user);

            // Pindah ke Dashboard
            mainFrame.switchToDashboard();
        } else {
            JOptionPane.showMessageDialog(null, "Username atau password salah.", "Login Gagal", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mendapatkan username dari JTextField
    private String getUsername() {
        return usernameTextField.getText();
    }

    // Mendapatkan password dari JPasswordField
    private char[] getPassword() {
        return passwordField.getPassword();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isPasswordVisible) {
            passwordField.setEchoChar('•');
            showPasswordLabel.setIcon(new ImageIcon(getClass().getResource("/img/eye-fill.png")));
        } else {
            passwordField.setEchoChar((char) 0);
            showPasswordLabel.setIcon(new ImageIcon(getClass().getResource("/img/eye-off-fill.png")));
        }
        isPasswordVisible = !isPasswordVisible;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Tidak digunakan
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Tidak digunakan
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Tidak digunakan
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Tidak digunakan
    }
}