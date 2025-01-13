package view;

import controller.Profil;
import model.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HalamanProfil extends JFrame {
    private JTextField usernameField, phoneField, addressField;
    private JLabel profilePicLabel;
    private JButton saveButton, changePasswordButton, changeProfilePicButton, backButton;
    private Profil profil;
    private UserInfo currentUser;

    public HalamanProfil(UserInfo user) {
        this.currentUser = user;
        profil = new Profil();

        setTitle("Halaman Profil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245)); // Warna latar abu terang
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header
        JLabel headerLabel = new JLabel("Halaman Profil", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        headerLabel.setForeground(new Color(34, 45, 65)); // Warna teks gelap
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Panel formulir
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Foto Profil


        // Tombol Ubah Foto Profil


        // Username
        formPanel.add(createFormLabel("Username"), updateConstraints(gbc, 0, 2));
        usernameField = createRoundedTextField(currentUser.getUsername(), "Masukkan username");
        formPanel.add(usernameField, updateConstraints(gbc, 1, 2));

        // Nomor Telepon
        formPanel.add(createFormLabel("Nomor Telepon"), updateConstraints(gbc, 0, 3));
        phoneField = createRoundedTextField(currentUser.getPhone(), "Masukkan nomor telepon");
        formPanel.add(phoneField, updateConstraints(gbc, 1, 3));

        // Alamat
        formPanel.add(createFormLabel("Alamat"), updateConstraints(gbc, 0, 4));
        addressField = createRoundedTextField(currentUser.getAddress(), "Masukkan alamat");
        formPanel.add(addressField, updateConstraints(gbc, 1, 4));

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
        
        JButton deleteAccountButton = createDeleteButton("Hapus Akun");
        gbc.gridy = 8; // Menempatkan tombol di bawah tombol kembali
        formPanel.add(deleteAccountButton, gbc);
        deleteAccountButton.addActionListener(new DeleteAccountButtonActionListener());

        // Tombol Kembali
        backButton = createStyledButton("Kembali ke Halaman Beranda");
        gbc.gridy = 7;
        formPanel.add(backButton, gbc);
        backButton.addActionListener(new BackButtonActionListener());

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Tambahkan panel utama ke frame
        add(mainPanel);
    }
    
    

    private JLabel createFormLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(50, 50, 50));
        return label;
    }
    
    private JButton createDeleteButton(String text) {
    JButton button = new JButton(text);
    button.setBackground(new Color(220, 20, 60)); // Warna merah
    button.setForeground(Color.WHITE); // Warna teks putih
    button.setFont(new Font("SansSerif", Font.BOLD, 14));
    button.setFocusPainted(false);
    button.setPreferredSize(new Dimension(200, 40));
    return button;
}


    private GridBagConstraints updateConstraints(GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        return gbc;
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
    
    

    private JTextField createRoundedTextField(String text, String placeholder) {
        JTextField textField = new JTextField(text);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setToolTipText(placeholder);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(59, 89, 152)); // Warna dasar biru gelap
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
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
    
    private class DeleteAccountButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmation = JOptionPane.showConfirmDialog(
            HalamanProfil.this,
            "Apakah Anda yakin ingin menghapus akun ini? Tindakan ini tidak dapat dibatalkan.",
            "Konfirmasi Hapus Akun",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            Profil profilController = new Profil();
            try {
                profilController.deleteUserAccount(currentUser.getId());
                JOptionPane.showMessageDialog(
                    HalamanProfil.this,
                    "Akun Anda telah berhasil dihapus.",
                    "Hapus Akun Berhasil",
                    JOptionPane.INFORMATION_MESSAGE
                );
                new HalamanLogin().setVisible(true); // Mengarahkan ke halaman login
                HalamanProfil.this.dispose(); // Menutup halaman profil

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    HalamanProfil.this,
                    "Gagal menghapus akun. Silakan coba lagi.",
                    "Kesalahan",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
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
