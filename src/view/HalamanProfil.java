package view;

import controller.Profil;
import model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HalamanProfil extends JFrame {
    private JTextField usernameField, phoneField, addressField;
    private JLabel profilePicLabel, usernameLabel, phoneLabel, addressLabel;
    private JButton saveButton, changePasswordButton, changeProfilePicButton, backButton;
    private Profil profil;
    private UserInfo currentUser;

    public HalamanProfil(UserInfo user) {
        this.currentUser = user; // User yang sedang login
        profil = new Profil();

        setTitle("Halaman Profil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600); // Tambah tinggi agar cukup untuk tombol kembali
        setLocationRelativeTo(null);

        // Panel utama dengan GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margin untuk setiap elemen

        // Foto Profil
        profilePicLabel = new JLabel();
        profilePicLabel.setPreferredSize(new Dimension(150, 150));
        profilePicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilePicLabel.setIcon(loadImage("path_to_default_image.jpg")); // Path default foto profil

        // Tambahkan label foto ke panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(profilePicLabel, gbc);

        // Tombol Ubah Foto Profil
        changeProfilePicButton = new JButton("Ubah Foto Profil");
        gbc.gridy = 1;
        panel.add(changeProfilePicButton, gbc);
        changeProfilePicButton.addActionListener(new ChangeProfilePicButtonActionListener());

        // Username
        usernameLabel = new JLabel("Username:");
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(currentUser.getUsername());
        usernameField.setPreferredSize(new Dimension(200, 30)); // Ukuran khusus
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Nomor Telepon
        phoneLabel = new JLabel("Nomor Telepon:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(phoneLabel, gbc);

        phoneField = new JTextField(currentUser.getPhone());
        phoneField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        // Alamat
        addressLabel = new JLabel("Alamat:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(addressLabel, gbc);

        addressField = new JTextField(currentUser.getAddress());
        addressField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(addressField, gbc);

        // Tombol Simpan Perubahan
        saveButton = new JButton("Simpan Perubahan");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);
        saveButton.addActionListener(new SaveButtonActionListener());

        // Tombol Ubah Sandi
        changePasswordButton = new JButton("Ubah Sandi");
        gbc.gridy = 6;
        panel.add(changePasswordButton, gbc);
        changePasswordButton.addActionListener(new ChangePasswordButtonActionListener());

        // Tombol Kembali ke Halaman Beranda
        backButton = new JButton("Kembali ke Halaman Beranda");
        gbc.gridy = 7;
        panel.add(backButton, gbc);
        backButton.addActionListener(new BackButtonActionListener());

        // Menambahkan panel ke frame
        add(panel);
    }

    private ImageIcon loadImage(String path) {
        File imageFile = new File(path);
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null; // Jika gambar tidak ditemukan
    }

    private class SaveButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Ambil nilai dari field dan update UserInfo
            currentUser.setUsername(usernameField.getText());
            currentUser.setPhone(phoneField.getText());
            currentUser.setAddress(addressField.getText());

            // Simpan perubahan ke database melalui controller
            boolean isUpdated = profil.updateUserProfile(currentUser);
            if (isUpdated) {
                JOptionPane.showMessageDialog(HalamanProfil.this, "Perubahan berhasil disimpan!");
            } else {
                JOptionPane.showMessageDialog(HalamanProfil.this, "Gagal menyimpan perubahan!");
            }
        }
    }

    private class ChangePasswordButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Pindah ke halaman ubah sandi
            new HalamanUbahSandi(currentUser).setVisible(true);
            HalamanProfil.this.dispose(); // Menutup halaman profil
        }
    }

    private class ChangeProfilePicButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Dialog untuk memilih gambar
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(HalamanProfil.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                profilePicLabel.setIcon(loadImage(imagePath));

                // Simpan path gambar ke database (opsional)
                currentUser.setProfilePicturePath(imagePath);
                profil.updateUserProfile(currentUser); // Simpan ke database
            }
        }
    }

    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Pindah ke halaman beranda
            new HalamanBeranda(currentUser).setVisible(true);
            HalamanProfil.this.dispose(); // Menutup halaman profil
        }
    }
}
