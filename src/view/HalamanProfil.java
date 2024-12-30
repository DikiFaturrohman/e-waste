package view;

import controller.Profil;
import model.UserInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HalamanProfil extends JFrame {
    private JTextField usernameField, phoneField, addressField;
    private JLabel profilePicLabel, usernameLabel, phoneLabel, addressLabel;
    private JButton saveButton, changePasswordButton;
    private Profil profil;
    private UserInfo currentUser;

    public HalamanProfil(UserInfo user) {
        this.currentUser = user; // User yang sedang login
        profil = new Profil();
        
        setTitle("Halaman Profil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Foto Profil
        profilePicLabel = new JLabel("Foto Profil");
        profilePicLabel.setPreferredSize(new Dimension(100, 100));
        profilePicLabel.setIcon(new ImageIcon("path_to_default_image.jpg"));  // Ganti dengan path default foto profil

        // Username
        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(currentUser.getUsername(), 20);

        // Phone number
        phoneLabel = new JLabel("Nomor Telepon:");
        phoneField = new JTextField(currentUser.getPhone(), 20);

        // Address
        addressLabel = new JLabel("Alamat:");
        addressField = new JTextField(currentUser.getAddress(), 20);

        // Tombol Save (Simpan perubahan)
        saveButton = new JButton("Simpan Perubahan");
        saveButton.addActionListener(new SaveButtonActionListener());

        // Tombol Ubah Sandi
        changePasswordButton = new JButton("Ubah Sandi");
        changePasswordButton.addActionListener(new ChangePasswordButtonActionListener());

        // Menambahkan elemen ke panel
        panel.add(profilePicLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(saveButton);
        panel.add(changePasswordButton);

        // Menambahkan panel ke frame
        add(panel);
    }

private class SaveButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Ambil nilai dari field dan update UserInfo
        currentUser.setUsername(usernameField.getText());
        currentUser.setPhone(phoneField.getText());
        currentUser.setAddress(addressField.getText());

        // Simpan perubahan ke database melalui controller
        boolean isUpdated = profil.updateUserProfile(currentUser); // Memastikan updateUserProfile mengembalikan boolean
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
            new HalamanUbahSandi().setVisible(true);
            HalamanProfil.this.dispose();  // Menutup halaman profil
        }
    }
}
