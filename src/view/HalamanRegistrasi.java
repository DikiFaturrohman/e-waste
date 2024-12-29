package view;

import controller.Registrasi;
import model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalamanRegistrasi extends JFrame {
    // Komponen GUI
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton backButton;

    public HalamanRegistrasi() {
        // Pengaturan JFrame
        setTitle("Halaman Registrasi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Panel utama dengan GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label dan TextField untuk Username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(usernameField, gbc);

        // Label dan PasswordField untuk Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);

        // Label dan PasswordField untuk Konfirmasi Password
        JLabel confirmPasswordLabel = new JLabel("Konfirmasi Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        mainPanel.add(confirmPasswordField, gbc);

        // Label dan TextField untuk Email
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(emailField, gbc);

        // Tombol Register
        registerButton = new JButton("Daftar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(registerButton, gbc);

        // Tombol Kembali ke Halaman Login
        backButton = new JButton("Kembali ke Login");
        gbc.gridy = 5;
        mainPanel.add(backButton, gbc);

        // Tambahkan panel ke JFrame
        add(mainPanel);

        // Event Listener untuk Registrasi
        registerButton.addActionListener(new RegisterActionListener());
        backButton.addActionListener(new BackActionListener());
    }

    // Inner class untuk menangani registrasi pengguna
    private class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText();

            // Validasi input
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Semua kolom harus diisi!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Password dan konfirmasi password tidak cocok!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Proses registrasi menggunakan controller
            Registrasi registrasi = new Registrasi();
            UserInfo user = new UserInfo(username, password, email);

            boolean isRegistered = registrasi.registerUser(user); // Pastikan metode registerUser tersedia di controller

            if (isRegistered) {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Registrasi berhasil! Silakan login.");
                HalamanLogin loginForm = new HalamanLogin();
                loginForm.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Terjadi kesalahan saat registrasi. Silakan coba lagi.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Inner class untuk kembali ke halaman login
    private class BackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HalamanLogin loginForm = new HalamanLogin();
            loginForm.setVisible(true);
            dispose();
        }
    }
}
