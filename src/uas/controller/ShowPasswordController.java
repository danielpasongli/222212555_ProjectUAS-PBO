/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.controller;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author DANIEL
 */
public class ShowPasswordController extends MouseAdapter {
    private JPasswordField passwordField;
    private JLabel toggleLabel;
    private boolean isPasswordVisible = false;
    private ImageIcon eyeIcon;
    private ImageIcon eyeOffIcon;

    public ShowPasswordController(JPasswordField passwordField, JLabel toggleLabel) {
        this.passwordField = passwordField;
        this.toggleLabel = toggleLabel;
        this.eyeIcon = new ImageIcon(getClass().getResource("/img/eye-fill.png"));
        this.eyeOffIcon = new ImageIcon(getClass().getResource("/img/eye-off-fill.png"));
        toggleLabel.setIcon(eyeIcon);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        togglePasswordVisibility();
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordField.setEchoChar('•');
            toggleLabel.setIcon(eyeIcon);
        } else {
            passwordField.setEchoChar((char) 0);
            toggleLabel.setIcon(eyeOffIcon);
        }
        isPasswordVisible = !isPasswordVisible;
    }
}