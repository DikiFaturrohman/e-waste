package view;

import controller.Kategori;
import model.Waste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.UserInfo;


public class HalamanKategori extends JFrame {
    private JTable wasteTable;
    private JButton backButton;
    private UserInfo currentUser;

    public HalamanKategori(UserInfo user) {
        this.currentUser = user; // Menyimpan user yang sedang login

        setTitle("Halaman Kategori");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Label Selamat Datang
        JLabel welcomeLabel = new JLabel("Daftar Sampah Elektronik Yang Dapat Dijemput", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Tabel untuk menampilkan data
        wasteTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(wasteTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Kembali
        backButton = new JButton("Kembali ke Beranda");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Memuat data ke tabel
        loadWasteData();

        // Tambahkan listener untuk tombol kembali
        backButton.addActionListener(new BackButtonActionListener());

        // Menambahkan panel utama ke frame
        add(mainPanel);
    }

    private void loadWasteData() {
        // Mengambil data dari controller
        Kategori kategoriController = new Kategori();
        List<Waste> wasteList = kategoriController.getAllWaste();

        // Membuat model tabel
        String[] columnNames = {"ID", "Kategori", "Jenis"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Menambahkan data ke model tabel
        for (Waste waste : wasteList) {
            Object[] rowData = {waste.getId(), waste.getCategory(), waste.getType()};
            tableModel.addRow(rowData);
        }

        // Mengatur model ke JTable
        wasteTable.setModel(tableModel);
    }

    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Pindah ke halaman beranda
            new HalamanBeranda(currentUser).setVisible(true); // Memastikan currentUser diteruskan
            HalamanKategori.this.dispose(); // Menutup halaman kategori
        }
    }
}
