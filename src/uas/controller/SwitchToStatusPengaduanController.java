/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import java.awt.event.*;
import javax.swing.*;
import uas.database.Database;
import uas.model.Pengaduan;
import uas.view.MainFrame;
import uas.view.StatusPengaduan;

/**
 *
 * @author DANIEL
 */
public class SwitchToStatusPengaduanController implements ActionListener {
    private MainFrame mainFrame;
    private JTextField cekTextField;
    
    public SwitchToStatusPengaduanController(MainFrame mainFrame, JTextField cekTextField) {
        this.mainFrame = mainFrame;
        this.cekTextField = cekTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Mengambil teks dari cekTextField
        String nomorPengaduan = cekTextField.getText().trim();

        // Validasi input untuk memastikan bahwa nomor pengaduan tidak kosong
        if (nomorPengaduan.isEmpty() || nomorPengaduan.equals("Masukkan nomor pengaduan anda disini")) {
            JOptionPane.showMessageDialog(mainFrame, "Harap masukkan nomor pengaduan yang valid.", "Input Invalid", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mengambil data pengaduan dari database
        Database db = Database.getInstance();
        Pengaduan pengaduan = db.getPengaduanByNomor(nomorPengaduan);

        // Cek jika pengaduan ditemukan
        if (pengaduan != null) {
            // Membuka panel StatusPengaduan dengan data yang diperoleh
            StatusPengaduan statusPanel = new StatusPengaduan(mainFrame, pengaduan);
            mainFrame.setContentPane(statusPanel);
            mainFrame.validate();
        } else {
            // Menampilkan pesan error jika pengaduan tidak ditemukan
            JOptionPane.showMessageDialog(mainFrame, "Pengaduan dengan nomor tersebut tidak ditemukan.", "Pengaduan Tidak Ditemukan", JOptionPane.ERROR_MESSAGE);
        }
    }
}
