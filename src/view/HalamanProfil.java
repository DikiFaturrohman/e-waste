package view;

import javax.swing.*;

public class HalamanProfil extends JFrame {
    public HalamanProfil() {
        setTitle("Halaman Profil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        JLabel welcomeLabel = new JLabel("Selamat datang di Halaman Profil!");
        panel.add(welcomeLabel);

        add(panel);
    }
}
