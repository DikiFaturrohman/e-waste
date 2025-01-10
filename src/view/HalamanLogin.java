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
    private JButton forgotPasswordButton;

    public HalamanLogin() {
        // Pengaturan JFrame
        setTitle("Halaman Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500); // Perbesar ukuran jendela
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Label Header
        JLabel headerLabel = new JLabel("Login ke Akun Anda");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Panel Form Login
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label dan TextField untuk Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Label dan PasswordField untuk Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Tombol Login
        loginButton = new JButton("Login");
        styleButton(loginButton, new Color(59, 89, 152));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        // Tombol Registrasi
        registerButton = new JButton("Registrasi");
        styleButton(registerButton, new Color(60, 179, 113));
        gbc.gridy = 3;
        formPanel.add(registerButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        forgotPasswordButton = new JButton("Lupa Sandi");
        styleButton(forgotPasswordButton, new Color(255, 140, 0));
        gbc.gridy = 4; // Posisi di bawah tombol register
        formPanel.add(forgotPasswordButton, gbc);

        // Footer
        JLabel footerLabel = new JLabel("Belum punya akun? Daftar sekarang!");
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        // Tambahkan panel ke JFrame
        add(mainPanel);

        // Event Listener
        loginButton.addActionListener(new LoginActionListener());
        registerButton.addActionListener(new RegisterActionListener());
        forgotPasswordButton.addActionListener(new ForgotPasswordActionListener());
    }

    // Metode untuk memberikan gaya pada tombol
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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
            UserInfo user = registrasiController.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(HalamanLogin.this,
                        "Login berhasil! Selamat datang, " + user.getUsername());

                // Cek role user dan arahkan ke halaman yang sesuai
                if ("admin".equals(user.getRole())) {
                    System.out.println("Admin login berhasil, membuka AdminDashboard.");
                    AdminDashboard adminDashboard = new AdminDashboard();
                    adminDashboard.setVisible(true);
                } else if ("user".equals(user.getRole())) {
                    System.out.println("User login berhasil, membuka HalamanBeranda.");
                    HalamanBeranda beranda = new HalamanBeranda(user);
                    beranda.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(HalamanLogin.this,
                            "Role tidak dikenal.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                }
                
                dispose();  // Tutup halaman login setelah pengalihan
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

    // Inner class untuk menangani lupa password
    private class ForgotPasswordActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HalamanLupaSandi lupaSandi = new HalamanLupaSandi();
            lupaSandi.setVisible(true);
            dispose(); // Tutup halaman login jika diinginkan
        }
    }
}
