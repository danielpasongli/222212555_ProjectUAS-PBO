/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import java.awt.event.*;
import javax.swing.*;
import uas.view.MainFrame;
import uas.view.SignupPanel;

/**
 *
 * @author DANIEL
 */
public class SwitchToSignUpController extends MouseAdapter {
    private MainFrame mainFrame;
    
    public SwitchToSignUpController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel Panel = new SignupPanel(mainFrame);
        mainFrame.setContentPane(Panel);
        mainFrame.invalidate();
        mainFrame.validate();
    }
    
}
