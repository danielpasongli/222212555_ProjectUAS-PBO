/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import uas.database.Database;
import uas.model.Pengaduan;
import uas.view.DetailPengaduan;
import uas.view.Dashboard;
import uas.view.DataPengaduan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 *
 * @author DANIEL
 */
public class DetailPengaduanController implements ActionListener {
    private DetailPengaduan detailPanel;
    private Dashboard dashboard;
    private Pengaduan currentPengaduan;

    public DetailPengaduanController(DetailPengaduan detailPanel, Dashboard dashboard, Pengaduan pengaduan) {
        this.detailPanel = detailPanel;
        this.dashboard = dashboard;
        this.currentPengaduan = pengaduan;
        
        // Add action listeners
        detailPanel.getSaveButton().addActionListener(this);
        detailPanel.getResetButton().addActionListener(this);
        detailPanel.getBackButton().addActionListener(this);
        
        // Populate the detail panel with data
        populateDetails(pengaduan);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == detailPanel.getSaveButton()) {
            saveDetails();
        } else if (e.getSource() == detailPanel.getResetButton()) {
            resetDetails();
        } else if (e.getSource() == detailPanel.getBackButton()) {
            backToDataPengaduan();
        }
    }

    private void populateDetails(Pengaduan pengaduan) {
        detailPanel.getNomorPengaduanTextField().setText(pengaduan.getID());
        detailPanel.getTanggalTextField().setText(new SimpleDateFormat("dd-MM-yyyy").format(pengaduan.getTglLapor()));
        detailPanel.getNamaPelaporTextField().setText(pengaduan.getNamaPelapor());
        detailPanel.getJabatanTextField().setText(pengaduan.getJabatan());
        detailPanel.getLokasiComboBox().setSelectedItem(pengaduan.getLokasiKerusakan());
        detailPanel.getDescTextArea().setText(pengaduan.getDeskripsi());
        detailPanel.getCatatanPetugasTextArea().setText(pengaduan.getCatatanPetugas());

        if (pengaduan.getStatus().equals("Sedang diajukan")) {
            detailPanel.getDiajukanRadioButton().setSelected(true);
        } else if (pengaduan.getStatus().equals("Sedang diproses")) {
            detailPanel.getDiprosesRadioButton().setSelected(true);
        } else if (pengaduan.getStatus().equals("Selesai diproses")) {
            detailPanel.getSelesaiRadioButton().setSelected(true);
        }

        // Set the photo
        if (pengaduan.getFoto() != null) {
            ImageIcon imageIcon = new ImageIcon(pengaduan.getFoto());
            Image scaledImage = imageIcon.getImage().getScaledInstance(680, 512, Image.SCALE_SMOOTH);
            detailPanel.getFotoLabel().setIcon(new ImageIcon(scaledImage));
        } else {
            detailPanel.getFotoLabel().setText("No Photo Available");
        }
    }

    private void saveDetails() {
        String status = "";
        if (detailPanel.getDiajukanRadioButton().isSelected()) {
            status = "Sedang diajukan";
        } else if (detailPanel.getDiprosesRadioButton().isSelected()) {
            status = "Sedang diproses";
        } else if (detailPanel.getSelesaiRadioButton().isSelected()) {
            status = "Selesai diproses";
        }

        currentPengaduan.setStatus(status);
        currentPengaduan.setCatatanPetugas(detailPanel.getCatatanPetugasTextArea().getText());

        Database db = Database.getInstance();
        boolean updated = db.updatePengaduan(currentPengaduan);

        if (updated) {
            JOptionPane.showMessageDialog(detailPanel, "Pengaduan berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            backToDataPengaduan(); // Return to DataPengaduan after successful update
        } else {
            JOptionPane.showMessageDialog(detailPanel, "Gagal memperbarui pengaduan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetDetails() {
        populateDetails(currentPengaduan);
    }

    private void backToDataPengaduan() {
        DataPengaduan dataPengaduanPanel = new DataPengaduan(dashboard);
        dashboard.setPanel(dataPengaduanPanel);
    }
}