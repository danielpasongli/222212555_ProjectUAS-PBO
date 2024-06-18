/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;
import java.awt.event.ActionListener;
import uas.database.Database;
import uas.model.Pengaduan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import uas.view.Dashboard;
import uas.view.DetailPengaduan;

/**
 *
 * @author DANIEL
 */
public class DataPengaduanController {
    private JTable belumTable;
    private JTable sedangTable;
    private JTable telahTable;
    private JTextField searchBelumTextField;
    private JTextField searchSedangTextField;
    private JTextField searchTelahTextField;
    private JButton detailButton;
    private JButton deleteButton;
    private JButton exportButton;
    private SimpleDateFormat dateFormat;
    private Dashboard dashboard;

    public DataPengaduanController(JTable belumTable, JTable sedangTable, JTable telahTable, JTextField searchBelumTextField, JTextField searchSedangTextField, JTextField searchTelahTextField, JButton detailButton, JButton deleteButton, JButton exportButton, Dashboard dashboard) {
        this.belumTable = belumTable;
        this.sedangTable = sedangTable;
        this.telahTable = telahTable;
        this.searchBelumTextField = searchBelumTextField;
        this.searchSedangTextField = searchSedangTextField;
        this.searchTelahTextField = searchTelahTextField;
        this.detailButton = detailButton;
        this.deleteButton = deleteButton;
        this.exportButton = exportButton;
        this.dashboard = dashboard;
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        loadData();
        addListeners();
    }

    private void loadData() {
        Database db = Database.getInstance();
        loadTableData(db.getPengaduanByStatus("Sedang diajukan"), belumTable);
        loadTableData(db.getPengaduanByStatus("Sedang diproses"), sedangTable);
        loadTableData(db.getPengaduanByStatus("Selesai diproses"), telahTable);
    }

    private void loadTableData(List<Pengaduan> pengaduanList, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Pengaduan pengaduan : pengaduanList) {
            Object[] rowData = {
                pengaduan.getID(),
                pengaduan.getNamaPelapor(),
                pengaduan.getLokasiKerusakan(),
                pengaduan.getDeskripsi(),
                dateFormat.format(pengaduan.getTglLapor())
            };
            model.addRow(rowData);
        }
    }

    private void addListeners() {
        searchBelumTextField.addKeyListener(getSearchKeyListener(belumTable, "Sedang diajukan"));
        searchSedangTextField.addKeyListener(getSearchKeyListener(sedangTable, "Sedang diproses"));
        searchTelahTextField.addKeyListener(getSearchKeyListener(telahTable, "Selesai diproses"));

        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetail();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportTablesToCSV();
            }
        });

        belumTable.getSelectionModel().addListSelectionListener(getTableSelectionListener(belumTable, sedangTable, telahTable));
        sedangTable.getSelectionModel().addListSelectionListener(getTableSelectionListener(sedangTable, belumTable, telahTable));
        telahTable.getSelectionModel().addListSelectionListener(getTableSelectionListener(telahTable, belumTable, sedangTable));
    }

    private KeyAdapter getSearchKeyListener(JTable table, String status) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                JTextField searchField = (JTextField) e.getSource();
                String searchText = searchField.getText();
                searchTableData(searchText, table, status);
            }
        };
    }

    private void searchTableData(String searchText, JTable table, String status) {
        Database db = Database.getInstance();
        List<Pengaduan> pengaduanList = db.searchPengaduanByStatusAndText(status, searchText);
        loadTableData(pengaduanList, table);
    }

    private void showDetail() {
        int selectedRow = getSelectedRow();

        if (selectedRow >= 0) {
            JTable selectedTable = getSelectedTable();
            String id = selectedTable.getValueAt(selectedRow, 0).toString();

            Database db = Database.getInstance();
            Pengaduan pengaduan = db.getPengaduanByNomor(id);

            DetailPengaduan detailPanel = new DetailPengaduan();
            new DetailPengaduanController(detailPanel, dashboard, pengaduan);
            dashboard.setPanel(detailPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Silakan pilih baris untuk melihat detail.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = getSelectedRow();

        if (selectedRow >= 0) {
            JTable selectedTable = getSelectedTable();
            String id = selectedTable.getValueAt(selectedRow, 0).toString();

            Database db = Database.getInstance();
            int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus pengaduan ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = db.deletePengaduanById(id);
                if (deleted) {
                    JOptionPane.showMessageDialog(null, "Pengaduan berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus pengaduan.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silakan pilih baris untuk dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getSelectedRow() {
        int selectedRow = belumTable.getSelectedRow();
        if (selectedRow == -1) selectedRow = sedangTable.getSelectedRow();
        if (selectedRow == -1) selectedRow = telahTable.getSelectedRow();
        return selectedRow;
    }

    private JTable getSelectedTable() {
        if (belumTable.getSelectedRow() != -1) {
            return belumTable;
        } else if (sedangTable.getSelectedRow() != -1) {
            return sedangTable;
        } else {
            return telahTable;
        }
    }

    private ListSelectionListener getTableSelectionListener(JTable mainTable, JTable... otherTables) {
        return new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (mainTable.getSelectedRow() != -1) {
                        for (JTable table : otherTables) {
                            table.clearSelection();
                        }
                    }
                }
            }
        };
    }

    private void exportTablesToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(dashboard);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getAbsolutePath().endsWith(".csv")) {
                fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".csv");
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
                writer.println("Belum Diproses");
                writeTableToCSV(writer, belumTable);

                writer.println("\nSedang Diproses");
                writeTableToCSV(writer, sedangTable);

                writer.println("\nTelah Diproses");
                writeTableToCSV(writer, telahTable);

                JOptionPane.showMessageDialog(dashboard, "Data berhasil diekspor ke CSV!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(dashboard, "Terjadi kesalahan saat menulis ke file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void writeTableToCSV(PrintWriter writer, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getColumnCount(); i++) {
            writer.print(model.getColumnName(i));
            if (i < model.getColumnCount() - 1) {
                writer.print(",");
            }
        }
        writer.println();

        for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                writer.print(model.getValueAt(i, j));
                if (j < model.getColumnCount() - 1) {
                    writer.print(",");
                }
            }
            writer.println();
        }
    }
}