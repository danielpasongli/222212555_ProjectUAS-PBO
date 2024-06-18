/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.*;
import uas.view.FormPengaduan;
import uas.database.Database;
import uas.model.Pengaduan;
import java.util.Date;

/**
 *
 * @author DANIEL
 */
public class SubmitPengaduanController implements ActionListener {
    private FormPengaduan formPengaduan;
    private byte[] fotoPengaduan;

    public SubmitPengaduanController(FormPengaduan formPengaduan) {
        this.formPengaduan = formPengaduan;
        this.fotoPengaduan = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source == formPengaduan.getKirimButton()) {
            submitPengaduan();
        } else if (source == formPengaduan.getUploadButton()) {
            uploadFoto();
        } else if (source == formPengaduan.getResetFormButton()) {
            formPengaduan.resetForm();
        }
    }

    private void submitPengaduan() {
        // Validasi form sebelum menyimpan
        if (!isValidForm()) {
            return;
        }

        String id = formPengaduan.getNomorPengaduanTextField().getText();
        String namaPelapor = formPengaduan.getNamaPelaporTextField().getText();
        String jabatan = formPengaduan.getPosisiTextField().getText();
        String lokasiKerusakan = (String) formPengaduan.getLocationComboBox().getSelectedItem();
        String deskripsi = formPengaduan.getDeskripsiTextArea().getText();
        String status = "Sedang diajukan";
        String ketPetugas = "-";
        Date tglLapor = new Date(Calendar.getInstance().getTimeInMillis());

        Pengaduan pengaduan = new Pengaduan(id, namaPelapor, jabatan, lokasiKerusakan, fotoPengaduan, deskripsi, tglLapor, status, ketPetugas);

        Database db = Database.getInstance();
        db.savePengaduan(pengaduan);

        JOptionPane.showMessageDialog(formPengaduan, "Pengaduan berhasil disubmit.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        formPengaduan.resetForm(); // Panggil resetForm setelah berhasil disubmit
    }

    private void uploadFoto() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(formPengaduan);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                fotoPengaduan = new byte[(int) selectedFile.length()];
                fis.read(fotoPengaduan);
                JOptionPane.showMessageDialog(formPengaduan, "Foto berhasil diupload.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(formPengaduan, "Gagal mengupload foto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Metode untuk validasi form
    private boolean isValidForm() {
        if (formPengaduan.getNamaPelaporTextField().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(formPengaduan, "Nama Pelapor harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (formPengaduan.getPosisiTextField().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(formPengaduan, "Jabatan harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (formPengaduan.getLocationComboBox().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(formPengaduan, "Lokasi Kerusakan harus dipilih.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (formPengaduan.getDeskripsiTextArea().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(formPengaduan, "Deskripsi harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}