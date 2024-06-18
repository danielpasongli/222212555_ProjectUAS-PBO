/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uas.database.Database;
import uas.model.User;
import uas.view.ProfilAkun;

/**
 *
 * @author DANIEL
 */
public class ProfilAkunController implements ActionListener {
    private ProfilAkun profilAkun;
    private User currentUser;

    public ProfilAkunController(ProfilAkun profilAkun, User user) {
        this.profilAkun = profilAkun;
        this.currentUser = user;
        this.profilAkun.getSubmitButton().addActionListener(this);
        this.profilAkun.getResetButton().addActionListener(this);

        // Isi data profil
        populateProfileData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == profilAkun.getSubmitButton()) {
            updatePassword();
        } else if (e.getSource() == profilAkun.getResetButton()) {
            resetPasswordField();
        }
    }

    private void populateProfileData() {
        Database db = Database.getInstance();
        // Reload the user data from the database
        currentUser = db.getUserByUsername(currentUser.getUsername());
        profilAkun.getUsernameLabel().setText(currentUser.getUsername());
        profilAkun.getNipLabel().setText(currentUser.getUserID());
        profilAkun.getFullnameLabel().setText(currentUser.getName());
    }

    private void updatePassword() {
        int confirm = JOptionPane.showConfirmDialog(profilAkun, "Apakah Anda yakin ingin memperbarui password?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String newPassword = new String(profilAkun.getPasswordPasswordField().getPassword());
            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(profilAkun, "Password tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Database db = Database.getInstance();
            boolean updated = db.updatePassword(currentUser.getUsername(), newPassword);
            if (updated) {
                JOptionPane.showMessageDialog(profilAkun, "Password berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                resetPasswordField();
                // Reload the user data from the database after password update
                populateProfileData();
            } else {
                JOptionPane.showMessageDialog(profilAkun, "Gagal memperbarui password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetPasswordField() {
        profilAkun.getPasswordPasswordField().setText("");
    }
}