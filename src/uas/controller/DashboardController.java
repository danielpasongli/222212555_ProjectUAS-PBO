/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import uas.model.User;
import uas.view.Dashboard;
import uas.view.DataPengaduan;
import uas.view.MainFrame;
import uas.view.ProfilAkun;

/**
 *
 * @author DANIEL
 */
public class DashboardController {

    private Dashboard dashboard;
    private MainFrame mainFrame;

    public DashboardController(Dashboard dashboard, MainFrame mainFrame) {
        this.dashboard = dashboard;
        this.mainFrame = mainFrame;
    }

    public MouseAdapter getLabelClickListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel source = (JLabel) e.getSource();
                resetPanelColors();
                if (source == dashboard.getDataLabel()) {
                    dashboard.getDataPanel().setBackground(Color.BLUE);
                    showPanel(new DataPengaduan(dashboard));
                } else if (source == dashboard.getProfilLabel()) {
                    dashboard.getProfilPanel().setBackground(Color.BLUE);
                    // Dapatkan pengguna yang sedang login dari mainFrame
                    User currentUser = mainFrame.getCurrentUser();
                    ProfilAkun profilAkun = new ProfilAkun();
                    showPanel(profilAkun);
                    ProfilAkunController profilAkunController = new ProfilAkunController(profilAkun, currentUser);
                    addShowPasswordListener(profilAkun);
                } else if (source == dashboard.getKeluarLabel()) {
                    dashboard.getKeluarPanel().setBackground(Color.BLUE);
                    int confirm = JOptionPane.showConfirmDialog(dashboard, "Apakah Anda yakin ingin keluar?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        dashboard.dispose();
                        mainFrame.setVisible(true);
                    }
                }
            }
        };
    }
    
    private void addShowPasswordListener(ProfilAkun profilAkun) {
        ShowPasswordController showPasswordController = new ShowPasswordController(profilAkun.getPasswordPasswordField(), profilAkun.getShowPasswordLabel());
        profilAkun.getShowPasswordLabel().addMouseListener(showPasswordController);
    }

    private void resetPanelColors() {
        dashboard.getDataPanel().setBackground(new Color(51, 51, 51));
        dashboard.getProfilPanel().setBackground(new Color(51, 51, 51));
        dashboard.getKeluarPanel().setBackground(new Color(51, 51, 51));
    }

    private void showPanel(JPanel panel) {
        dashboard.getMainPanel().removeAll();
        dashboard.getMainPanel().setLayout(new BorderLayout());
        dashboard.getMainPanel().add(panel, BorderLayout.CENTER);
        dashboard.getMainPanel().revalidate();
        dashboard.getMainPanel().repaint();
    }
}