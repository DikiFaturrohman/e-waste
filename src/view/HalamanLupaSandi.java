package view;

import controller.LupaSandiController;

import javax.swing.*;
import java.awt.*;
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
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout and background color
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255));
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Lupa Kata Sandi", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(30, 144, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Email label and field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(emailLabel, gbc);

emailField = new JTextField();
emailField.setFont(new Font("Arial", Font.PLAIN, 14));
emailField.setPreferredSize(new Dimension(250, 25)); // Menentukan lebar text field
gbc.gridx = 1;
gbc.gridy = 1;
panel.add(emailField, gbc);

        // Send OTP button
        sendOtpButton = new JButton("Kirim OTP");
        sendOtpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        sendOtpButton.setBackground(new Color(30, 144, 255));
        sendOtpButton.setForeground(Color.WHITE);
        sendOtpButton.addActionListener(new SendOtpListener());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(sendOtpButton, gbc);

        // OTP label and field
        JLabel otpLabel = new JLabel("Kode OTP:");
        otpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(otpLabel, gbc);

        otpField = new JTextField();
        otpField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(otpField, gbc);

        // Verify OTP button
        verifyOtpButton = new JButton("Verifikasi OTP");
        verifyOtpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        verifyOtpButton.setBackground(new Color(34, 139, 34));
        verifyOtpButton.setForeground(Color.WHITE);
        verifyOtpButton.addActionListener(new VerifyOtpListener());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(verifyOtpButton, gbc);

        // New password label and field
        JLabel newPasswordLabel = new JLabel("Kata Sandi Baru:");
        newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(newPasswordLabel, gbc);

        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(newPasswordField, gbc);

        // Reset password button
        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setFont(new Font("Arial", Font.PLAIN, 14));
        resetPasswordButton.setBackground(new Color(220, 20, 60));
        resetPasswordButton.setForeground(Color.WHITE);
        resetPasswordButton.addActionListener(new ResetPasswordListener());
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(resetPasswordButton, gbc);

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
