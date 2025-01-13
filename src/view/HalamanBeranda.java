package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.UserInfo;

public class HalamanBeranda extends JFrame {
    private UserInfo currentUser;

    public HalamanBeranda(UserInfo user) {
        this.currentUser = user;

        // Pengaturan JFrame
        setTitle("Halaman Beranda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400); // Perbesar ukuran jendela
        setLocationRelativeTo(null);

        // Panel Utama
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);

        // Label Selamat Datang
        JLabel welcomeLabel = new JLabel("Selamat datang di Halaman Beranda!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Tombol Profil
        JButton profilButton = new JButton("Profil");
        styleButton(profilButton, new Color(59, 89, 152));

        profilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HalamanProfil(currentUser).setVisible(true);
                dispose(); // Menutup halaman beranda
            }
        });

        // Tombol Daftar Sampah Elektronik
        JButton kategoriButton = new JButton("Daftar Sampah Elektronik");
        styleButton(kategoriButton, new Color(60, 179, 113));

        kategoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HalamanKategori(currentUser).setVisible(true);
                dispose(); // Menutup halaman beranda
            }
        });

        // Tombol Logout
        JButton logoutButton = new JButton("Keluar");
        styleButton(logoutButton, new Color(220, 20, 60));

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HalamanLogin().setVisible(true);
                dispose(); // Menutup halaman beranda
            }
        });

        // Layout menggunakan GroupLayout
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(profilButton, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addComponent(kategoriButton, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(welcomeLabel)
                .addGap(70)
                .addComponent(profilButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addComponent(kategoriButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        );

        // Tambahkan panel utama ke frame
        add(mainPanel);
    }

    // Metode untuk mengatur gaya tombol
    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(250, 50));
    }

    
}
