package view;

import controller.Profil;
import model.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HalamanProfil extends JFrame {
    private JTextField usernameField, phoneField, addressField;
    private JLabel profilePicLabel, usernameLabel, phoneLabel, addressLabel, headerLabel;
    private JButton saveButton, changePasswordButton, changeProfilePicButton, backButton;
    private Profil profil;
    private UserInfo currentUser;

    public HalamanProfil(UserInfo user) {
        this.currentUser = user; // User yang sedang login
        profil = new Profil();

        setTitle("Halaman Profil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);

        // Panel utama dengan warna latar belakang
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255)); // Warna latar biru muda
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margin panel utama

        // Header dengan teks besar
        headerLabel = new JLabel("Halaman Profil", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(60, 90, 150)); // Warna teks biru gelap
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Panel formulir di tengah
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE); // Warna latar putih
        formPanel.setBorder(new LineBorder(new Color(200, 200, 200), 2, true)); // Border melengkung
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Foto Profil
        profilePicLabel = new JLabel();
        profilePicLabel.setPreferredSize(new Dimension(150, 150));
        profilePicLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profilePicLabel.setIcon(loadImage("path_to_default_image.jpg")); // Path default foto profil
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(profilePicLabel, gbc);

        // Tombol Ubah Foto Profil
        changeProfilePicButton = createStyledButton("Ubah Foto Profil");
        gbc.gridy = 1;
        formPanel.add(changeProfilePicButton, gbc);
        changeProfilePicButton.addActionListener(new ChangeProfilePicButtonActionListener());

        // Username
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField = createRoundedTextField(currentUser.getUsername());
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Nomor Telepon
        phoneLabel = new JLabel("Nomor Telepon:");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(phoneLabel, gbc);

        phoneField = createRoundedTextField(currentUser.getPhone());
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        // Alamat
        addressLabel = new JLabel("Alamat:");
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(addressLabel, gbc);

        addressField = createRoundedTextField(currentUser.getAddress());
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        // Tombol Simpan Perubahan
        saveButton = createStyledButton("Simpan Perubahan");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(saveButton, gbc);
        saveButton.addActionListener(new SaveButtonActionListener());

        // Tombol Ubah Sandi
        changePasswordButton = createStyledButton("Ubah Sandi");
        gbc.gridy = 6;
        formPanel.add(changePasswordButton, gbc);
        changePasswordButton.addActionListener(new ChangePasswordButtonActionListener());

        // Tombol Kembali
        backButton = createStyledButton("Kembali ke Halaman Beranda");
        gbc.gridy = 7;
        formPanel.add(backButton, gbc);
        backButton.addActionListener(new BackButtonActionListener());

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Menambahkan panel utama ke frame
        add(mainPanel);
    }

    private ImageIcon loadImage(String path) {
        File imageFile = new File(path);
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }

    private JTextField createRoundedTextField(String text) {
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true), // Border melengkung
            BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding di dalam teks
        ));
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(60, 90, 150)); // Warna dasar biru
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(60, 90, 150), 10, true)); // Border melengkung
        button.setPreferredSize(new Dimension(200, 40));

        // Tambahkan efek hover dan klik menggunakan MouseListener
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 110, 180)); // Warna saat hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(60, 90, 150)); // Kembali ke warna awal
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(40, 70, 130)); // Warna saat ditekan
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(80, 110, 180)); // Kembali ke warna hover
            }
        });

        return button;
    }

    private class SaveButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentUser.setUsername(usernameField.getText());
            currentUser.setPhone(phoneField.getText());
            currentUser.setAddress(addressField.getText());

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
            new HalamanUbahSandi(currentUser).setVisible(true);
            HalamanProfil.this.dispose();
        }
    }

    private class ChangeProfilePicButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(HalamanProfil.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                profilePicLabel.setIcon(loadImage(imagePath));
                currentUser.setProfilePicturePath(imagePath);
                profil.updateUserProfile(currentUser);
            }
        }
    }

    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new HalamanBeranda(currentUser).setVisible(true);
            HalamanProfil.this.dispose();
        }
    }
}
