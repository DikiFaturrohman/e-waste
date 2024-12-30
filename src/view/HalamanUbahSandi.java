package view;

import javax.swing.*;

public class HalamanUbahSandi extends JFrame {
    public HalamanUbahSandi() {
        setTitle("Halaman Ubah Sandi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        JLabel welcomeLabel = new JLabel("Selamat datang di Halaman Ubah Sandi!");
        panel.add(welcomeLabel);

        add(panel);
    }
}