package view;

import controller.Registrasi;
import model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalamanLogin extends JFrame {
    // Komponen GUI
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public HalamanLogin() {
        // Pengaturan JFrame
        setTitle("Halaman Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
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

        // Tombol Login
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(loginButton, gbc);

        // Tombol Registrasi
        registerButton = new JButton("Registrasi");
        gbc.gridy = 3;
        mainPanel.add(registerButton, gbc);

        // Tambahkan panel ke JFrame
        add(mainPanel);

        // Event Listener
        loginButton.addActionListener(new LoginActionListener());
        registerButton.addActionListener(new RegisterActionListener());
    }

    // Inner class untuk menangani login
    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(HalamanLogin.this,
                        "Username dan password harus diisi!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Proses login menggunakan controller
            Registrasi registrasiController = new Registrasi();
            UserInfo user = registrasiController.login(username, password); // Pastikan metode login tersedia di controller

            if (user != null) {
                JOptionPane.showMessageDialog(HalamanLogin.this,
                        "Login berhasil! Selamat datang, " + user.getUsername());
                // Navigasi ke Halaman Beranda
                HalamanBeranda beranda = new HalamanBeranda();
                beranda.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(HalamanLogin.this,
                        "Username atau password salah!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Inner class untuk menangani navigasi ke halaman registrasi
    private class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HalamanRegistrasi registrasi = new HalamanRegistrasi();
            registrasi.setVisible(true);
            dispose();
        }
    }
}
