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
    private JTextField phoneField;
    private JTextField addressField;

    public HalamanRegistrasi() {
        // Pengaturan JFrame
        setTitle("Halaman Registrasi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header
        JLabel headerLabel = new JLabel("Formulir Registrasi");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tambahkan komponen form
        addFormField(formPanel, gbc, "Username:", usernameField = new JTextField(20), 0);
        addFormField(formPanel, gbc, "Password:", passwordField = new JPasswordField(20), 1);
        addFormField(formPanel, gbc, "Konfirmasi Password:", confirmPasswordField = new JPasswordField(20), 2);
        addFormField(formPanel, gbc, "Email:", emailField = new JTextField(20), 3);
        addFormField(formPanel, gbc, "Nomor Telepon:", phoneField = new JTextField(20), 4);
        addFormField(formPanel, gbc, "Alamat:", addressField = new JTextField(20), 5);

        // Tombol Registrasi
        registerButton = new JButton("Daftar");
        styleButton(registerButton, new Color(60, 179, 113));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        // Tombol Kembali ke Login
        backButton = new JButton("Kembali");
        styleButton(backButton, new Color(59, 89, 152));
        gbc.gridy = 7;
        formPanel.add(backButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Footer
        JLabel footerLabel = new JLabel("");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        // Tambahkan panel ke JFrame
        add(mainPanel);

        // Event Listener
        registerButton.addActionListener(new RegisterActionListener());
        backButton.addActionListener(new BackActionListener());
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int row) {
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(jLabel, gbc);

        field.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()
                    || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Semua kolom harus diisi!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Password dan konfirmasi password tidak cocok!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("\\d{10,15}")) {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Nomor telepon tidak valid!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Registrasi registrasi = new Registrasi();
            UserInfo user = new UserInfo(username, password, email, phone, address);

            user.setPhone(phone);
            user.setAddress(address);

            boolean isRegistered = registrasi.registerUser(user);

            if (isRegistered) {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Registrasi berhasil! Silakan login.");
                HalamanLogin loginForm = new HalamanLogin(); // Pastikan HalamanLogin tersedia
                loginForm.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(HalamanRegistrasi.this,
                        "Terjadi kesalahan saat registrasi. Silakan coba lagi.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class BackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HalamanLogin loginForm = new HalamanLogin(); // Pastikan HalamanLogin tersedia
            loginForm.setVisible(true);
            dispose();
        }
    }
}
