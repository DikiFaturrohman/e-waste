package view;

import controller.UbahSandi;
import model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalamanUbahSandi extends JFrame {
    private int id; // ID pengguna untuk konteks user saat ini

    public HalamanUbahSandi(UserInfo user) {
        this.id = user.getId();
        setTitle("Halaman Ubah Sandi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Gunakan GridBagLayout untuk fleksibilitas
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 248, 255)); // Warna latar belakang

        // Konfigurasi font umum
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);

        // GridBagConstraints untuk tata letak
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding antar komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 0.3;

        // Label dan field untuk kata sandi lama
        JLabel labelOldPassword = new JLabel("Kata Sandi Lama:");
        labelOldPassword.setFont(labelFont);
        gbc.gridy = 0;
        panel.add(labelOldPassword, gbc);

        JPasswordField fieldOldPassword = new JPasswordField();
        fieldOldPassword.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.ipadx = 200; // Lebar text field
        panel.add(fieldOldPassword, gbc);

        // Label dan field untuk kata sandi baru
        JLabel labelNewPassword = new JLabel("Kata Sandi Baru:");
        labelNewPassword.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.ipadx = 0;
        panel.add(labelNewPassword, gbc);

        JPasswordField fieldNewPassword = new JPasswordField();
        fieldNewPassword.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.ipadx = 200; // Lebar text field
        panel.add(fieldNewPassword, gbc);

        // Label dan field untuk konfirmasi kata sandi baru
        JLabel labelConfirmPassword = new JLabel("Konfirmasi Kata Sandi Baru:");
        labelConfirmPassword.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        gbc.ipadx = 0;
        panel.add(labelConfirmPassword, gbc);

        JPasswordField fieldConfirmPassword = new JPasswordField();
        fieldConfirmPassword.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.ipadx = 200; // Lebar text field
        panel.add(fieldConfirmPassword, gbc);

        // Tombol ubah sandi
        JButton btnChangePassword = new JButton("Ubah Sandi");
        btnChangePassword.setFont(labelFont);
        btnChangePassword.setBackground(new Color(60, 179, 113));
        btnChangePassword.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.gridwidth = 2;
        gbc.ipadx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnChangePassword, gbc);

        // Tombol kembali ke halaman profil
        JButton btnBackToProfile = new JButton("Kembali ke Halaman Profil");
        btnBackToProfile.setFont(labelFont);
        btnBackToProfile.setBackground(new Color(59, 89, 152));
        btnBackToProfile.setForeground(Color.WHITE);
        gbc.gridy = 4;
        panel.add(btnBackToProfile, gbc);

        // Event listener untuk tombol ubah sandi
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = fieldOldPassword.getText();
                String newPassword = fieldNewPassword.getText();
                String confirmPassword = fieldConfirmPassword.getText();

                if (newPassword.equals(confirmPassword)) {
                    UbahSandi ubahSandiController = new UbahSandi();
                    boolean isSuccess = ubahSandiController.ubahPassword(id, oldPassword, newPassword);
                    if (isSuccess) {
                        JOptionPane.showMessageDialog(HalamanUbahSandi.this,
                                "Kata sandi berhasil diubah!",
                                "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Tutup halaman ini
                        new HalamanProfil(user).setVisible(true); // Kembali ke halaman profil
                    } else {
                        JOptionPane.showMessageDialog(HalamanUbahSandi.this,
                                "Kata sandi lama salah!",
                                "Gagal", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(HalamanUbahSandi.this,
                            "Kata sandi baru dan konfirmasi tidak cocok!",
                            "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event listener untuk tombol kembali
        btnBackToProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tutup halaman ini
                new HalamanProfil(user).setVisible(true); // Buka halaman profil
            }
        });

        add(panel);
    }
}
