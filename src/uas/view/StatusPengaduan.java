/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package uas.view;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import uas.controller.SwitchToHomeController;
import uas.model.Pengaduan;

/**
 *
 * @author DANIEL
 */
public class StatusPengaduan extends javax.swing.JPanel {
    
    private final MainFrame mainFrame;
    private Pengaduan pengaduan;

    /**
     * Creates new form FormPengaduan
     */
    public StatusPengaduan(MainFrame mainFrame, Pengaduan pengaduan) {
        this.mainFrame = mainFrame;
        this.pengaduan = pengaduan;
        initComponents();
        addListeners();
        displayPengaduanDetails();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jawabanNoLabel = new javax.swing.JLabel();
        jawabanTglLabel = new javax.swing.JLabel();
        jawabanNamaLabel = new javax.swing.JLabel();
        jawabanJbtnLabel = new javax.swing.JLabel();
        jawabanLokasiLabel = new javax.swing.JLabel();
        jawabanDescLabel = new javax.swing.JLabel();
        jawabanStatusLabel = new javax.swing.JLabel();
        jawabanPetugasLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(799, 532));
        setMinimumSize(new java.awt.Dimension(799, 532));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/homepage.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Status Pengaduan Anda");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Nomor Pengaduan    :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tanggal Pengaduan   :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Nama Pelapor            :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Jabatan                      :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Lokasi Kerusakan       :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Deskpripsi                 :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Status                        :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Catatan dari petugas :");

        jawabanNoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanNoLabel.setText("Jawaban");

        jawabanTglLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanTglLabel.setText("Jawaban");

        jawabanNamaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanNamaLabel.setText("Jawaban");

        jawabanJbtnLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanJbtnLabel.setText("Jawaban");

        jawabanLokasiLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanLokasiLabel.setText("Jawaban");

        jawabanDescLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanDescLabel.setText("Jawaban");

        jawabanStatusLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanStatusLabel.setText("Jawaban");

        jawabanPetugasLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jawabanPetugasLabel.setText("Jawaban");

        backButton.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jawabanNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jawabanTglLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jawabanNamaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jawabanJbtnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jawabanLokasiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jawabanDescLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jawabanStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jawabanPetugasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel1))
                    .addComponent(backButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jawabanNoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jawabanTglLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jawabanNamaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jawabanJbtnLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jawabanLokasiLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jawabanDescLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jawabanStatusLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jawabanPetugasLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addListeners() {
        backButton.addActionListener(new SwitchToHomeController(mainFrame));
    }
    
    private void displayPengaduanDetails() {
        if (pengaduan != null) {
            // Menampilkan data teks pengaduan
            jawabanNoLabel.setText(pengaduan.getID());
            jawabanNamaLabel.setText(pengaduan.getNamaPelapor());
            jawabanJbtnLabel.setText(pengaduan.getJabatan());
            jawabanLokasiLabel.setText(pengaduan.getLokasiKerusakan());
            jawabanDescLabel.setText(pengaduan.getDeskripsi());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            jawabanTglLabel.setText(dateFormat.format(pengaduan.getTglLapor()));
            jawabanStatusLabel.setText(pengaduan.getStatus());
            // Jika ada catatan dari petugas, tampilkan, jika tidak, tampilkan "Tidak ada catatan"
            jawabanPetugasLabel.setText(pengaduan.getCatatanPetugas() != null ? pengaduan.getCatatanPetugas() : "Tidak ada catatan");
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Pengaduan tidak ditemukan atau tidak dapat diakses.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jawabanDescLabel;
    private javax.swing.JLabel jawabanJbtnLabel;
    private javax.swing.JLabel jawabanLokasiLabel;
    private javax.swing.JLabel jawabanNamaLabel;
    private javax.swing.JLabel jawabanNoLabel;
    private javax.swing.JLabel jawabanPetugasLabel;
    private javax.swing.JLabel jawabanStatusLabel;
    private javax.swing.JLabel jawabanTglLabel;
    // End of variables declaration//GEN-END:variables
}