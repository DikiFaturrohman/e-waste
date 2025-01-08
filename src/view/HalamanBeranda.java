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
        
        setTitle("Halaman Beranda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Label selamat datang
        JLabel welcomeLabel = new JLabel("Selamat datang di Halaman Beranda!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panel.add(welcomeLabel, BorderLayout.NORTH);
        
        // Panel tombol navigasi
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        
        // Tombol menuju Halaman Profil
        JButton profilButton = new JButton("Profil");
        profilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Misalnya user adalah objek UserInfo yang diambil saat login
           new HalamanProfil(currentUser).setVisible(true); // Pastikan currentUser adalah objek UserInfo yang valid

 // Pastikan `currentUser` adalah objek `UserInfo` yang valid 

                dispose(); // Menutup halaman beranda
            }
        });
        buttonPanel.add(profilButton);
        
        // Tombol menuju Halaman Kategori
        JButton kategoriButton = new JButton("Daftar Sampah Elektronik");
        kategoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HalamanKategori(currentUser).setVisible(true);
                dispose(); // Menutup halaman beranda
            }
        });
        buttonPanel.add(kategoriButton);
        
        // Tombol Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HalamanLogin().setVisible(true);
                dispose(); // Menutup halaman beranda
            }
        });
        buttonPanel.add(logoutButton);
        
        // Tambahkan panel tombol ke panel utama
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        // Tambahkan panel utama ke frame
        add(panel);
    }
}
