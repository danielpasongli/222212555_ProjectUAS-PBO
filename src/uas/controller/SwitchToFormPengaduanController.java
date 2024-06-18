/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import java.awt.event.*;
import javax.swing.*;
import uas.view.MainFrame;
import uas.view.FormPengaduan;

/**
 *
 * @author DANIEL
 */
public class SwitchToFormPengaduanController extends MouseAdapter implements ActionListener {
    private MainFrame mainFrame;
    
    public SwitchToFormPengaduanController(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switchPanel();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switchPanel();
    }
    
    private void switchPanel(){
        JPanel Panel = new FormPengaduan(mainFrame);
        mainFrame.setContentPane(Panel);
        mainFrame.invalidate();
        mainFrame.validate();
    }
}
