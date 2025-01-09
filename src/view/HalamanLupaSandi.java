/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.LupaSandiController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalamanLupaSandi extends JFrame {
    private JTextField emailField;
    private JButton sendOtpButton, verifyOtpButton, resetPasswordButton;
    private JTextField otpField;
    private JPasswordField newPasswordField;

    private LupaSandiController controller;

    public HalamanLupaSandi() {
        controller = new LupaSandiController();

        setTitle("Lupa Kata Sandi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 30, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 30, 200, 25);
        add(emailField);

        sendOtpButton = new JButton("Kirim OTP");
        sendOtpButton.setBounds(50, 70, 300, 25);
        sendOtpButton.addActionListener(new SendOtpListener());
        add(sendOtpButton);

        JLabel otpLabel = new JLabel("Kode OTP:");
        otpLabel.setBounds(50, 110, 100, 25);
        add(otpLabel);

        otpField = new JTextField();
        otpField.setBounds(150, 110, 200, 25);
        add(otpField);

        verifyOtpButton = new JButton("Verifikasi OTP");
        verifyOtpButton.setBounds(50, 150, 300, 25);
        verifyOtpButton.addActionListener(new VerifyOtpListener());
        add(verifyOtpButton);

        JLabel newPasswordLabel = new JLabel("Kata Sandi Baru:");
        newPasswordLabel.setBounds(50, 190, 150, 25);
        add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(150, 190, 200, 25);
        add(newPasswordField);

        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setBounds(50, 230, 300, 25);
        resetPasswordButton.addActionListener(new ResetPasswordListener());
        add(resetPasswordButton);

        setLocationRelativeTo(null);
    }

private class SendOtpListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(HalamanLupaSandi.this, "Email tidak boleh kosong.");
            return;
        }

        String otpCode = controller.generateOtp(email);
        if (otpCode != null) {
            JOptionPane.showMessageDialog(HalamanLupaSandi.this, "OTP telah dikirim ke email Anda.");
        } else {
            JOptionPane.showMessageDialog(HalamanLupaSandi.this, "Gagal mengirim OTP. Coba lagi nanti.");
        }
    }
}


    private class VerifyOtpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String otp = otpField.getText();
            if (controller.validateOtp(email, otp)) {
                JOptionPane.showMessageDialog(HalamanLupaSandi.this, "OTP berhasil diverifikasi!");
            } else {
                JOptionPane.showMessageDialog(HalamanLupaSandi.this, "OTP salah atau sudah kadaluarsa.");
            }
        }
    }

    private class ResetPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String newPassword = new String(newPasswordField.getPassword());
            if (controller.resetPassword(email, newPassword)) {
                JOptionPane.showMessageDialog(HalamanLupaSandi.this, "Kata sandi berhasil diubah!");
                dispose();
                new HalamanLogin().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(HalamanLupaSandi.this, "Gagal mengubah kata sandi.");
            }
        }
    }
}
