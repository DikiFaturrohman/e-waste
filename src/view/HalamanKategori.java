package view;

import javax.swing.*;

public class HalamanKategori extends JFrame {
    public HalamanKategori() {
        setTitle("Halaman Kategori");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        JLabel welcomeLabel = new JLabel("Selamat datang di Halaman Kategori!");
        panel.add(welcomeLabel);

        add(panel);
    }
}
