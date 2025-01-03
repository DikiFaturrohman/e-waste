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
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Grid layout dengan 5 baris, 2 kolom

        // Label dan field untuk kata sandi lama
        JLabel labelOldPassword = new JLabel("Kata Sandi Lama:");
        JPasswordField fieldOldPassword = new JPasswordField();
        panel.add(labelOldPassword);
        panel.add(fieldOldPassword);

        // Label dan field untuk kata sandi baru
        JLabel labelNewPassword = new JLabel("Kata Sandi Baru:");
        JPasswordField fieldNewPassword = new JPasswordField();
        panel.add(labelNewPassword);
        panel.add(fieldNewPassword);

        // Label dan field untuk konfirmasi kata sandi baru
        JLabel labelConfirmPassword = new JLabel("Konfirmasi Kata Sandi Baru:");
        JPasswordField fieldConfirmPassword = new JPasswordField();
        panel.add(labelConfirmPassword);
        panel.add(fieldConfirmPassword);

        // Tombol ubah sandi
        JButton btnChangePassword = new JButton("Ubah Sandi");
        panel.add(btnChangePassword);

        // Tombol kembali ke halaman profil
        JButton btnBackToProfile = new JButton("Kembali ke Halaman Profil");
        panel.add(btnBackToProfile);

        // Event listener untuk tombol ubah sandi
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = new String(fieldOldPassword.getPassword());
                String newPassword = new String(fieldNewPassword.getPassword());
                String confirmPassword = new String(fieldConfirmPassword.getPassword());

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
