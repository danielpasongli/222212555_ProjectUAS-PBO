/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import java.awt.event.*;
import javax.swing.*;
import uas.database.*;


/**
 *
 * @author DANIEL
 */
public class RegisterController implements ActionListener {

    private final JTextField usernameTextField;
    private final JPasswordField passwordField;
    private final JTextField nipTextField;
    private final JTextField fullnameTextField;

    public RegisterController(JTextField usernameTextField, JPasswordField passwordField, JTextField nipTextField, JTextField fullnameTextField) {
        this.usernameTextField = usernameTextField;
        this.passwordField = passwordField;
        this.nipTextField = nipTextField;
        this.fullnameTextField = fullnameTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Ambil data dari form melalui controller
        String username = getUsername();
        String password = new String(getPassword());
        String nip = getNip();
        String fullName = getFullName();

        // Validasi data
        if (username.isEmpty() || password.isEmpty() || nip.isEmpty() || fullName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Masukkan data ke database menggunakan metode registerUser
        Database dbInstance = Database.getInstance();
        boolean registered = dbInstance.registerUser(username, password, nip, fullName);
        if (registered) {
            JOptionPane.showMessageDialog(null, "Registrasi berhasil", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Gagal melakukan registrasi: Username atau NIP sudah ada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metode untuk mendapatkan data dari form
    private String getUsername() {
        return usernameTextField.getText();
    }

    private char[] getPassword() {
        return passwordField.getPassword();
    }

    private String getNip() {
        return nipTextField.getText();
    }

    private String getFullName() {
        return fullnameTextField.getText();
    }
}