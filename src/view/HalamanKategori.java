package view;

import controller.Kategori;
import model.Waste;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.JTableHeader;
import model.UserInfo;

public class HalamanKategori extends JFrame {
    private JTable categoryTable;
    private JTable typeTable;
    private JTable combinedTable;
    private JButton backButton;
    private UserInfo currentUser;

    public HalamanKategori(UserInfo user) {
        this.currentUser = user; // Menyimpan user yang sedang login

        setTitle("Halaman Kategori");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600); // Perbesar ukuran jendela untuk memuat tiga panel tabel
        setLocationRelativeTo(null);

        // Panel utama dengan layout BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Label Selamat Datang
        JLabel welcomeLabel = new JLabel("Daftar Kategori dan Jenis Sampah Elektronik", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Panel untuk tabel kategori, jenis, dan gabungan
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(3, 1, 10, 10)); // Tiga baris untuk tiga tabel

        // Tabel Kategori
        categoryTable = new JTable();
        JScrollPane categoryScrollPane = new JScrollPane(categoryTable);
        tablePanel.add(createLabeledPanel("", categoryScrollPane));

        // Tabel Jenis
        typeTable = new JTable();
        JScrollPane typeScrollPane = new JScrollPane(typeTable);
        tablePanel.add(createLabeledPanel("", typeScrollPane));

        // Tabel Gabungan
        combinedTable = new JTable();
        JScrollPane combinedScrollPane = new JScrollPane(combinedTable);
        tablePanel.add(createLabeledPanel("", combinedScrollPane));

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Tombol Kembali
        backButton = new JButton("Kembali ke Beranda");
        backButton.addActionListener(new BackButtonActionListener());
        backButton.setBackground(new Color(59, 89, 152)); // Warna merah
        backButton.setForeground(Color.WHITE); // Teks tombol warna putih
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Memuat data ke tabel
        loadCategoryData();
        loadTypeData();
        loadCombinedData();

        // Menambahkan panel utama ke frame
        add(mainPanel);
    }

    // Metode untuk membuat panel berlabel
    private JPanel createLabeledPanel(String title, JScrollPane scrollPane) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    
    private void styleTableHeader(JTable table) {
    JTableHeader header = table.getTableHeader();
    header.setBackground(new Color(0, 128, 0)); // Warna hijau
    header.setForeground(Color.WHITE); // Teks header warna putih
    header.setFont(new Font("Arial", Font.BOLD, 14));
    header.setReorderingAllowed(false); // Mencegah reordering kolom
    header.setResizingAllowed(false);  // Mencegah resizing kolom
}
    
    


    // Memuat data kategori ke tabel kategori
    private void loadCategoryData() {
    Kategori kategoriController = new Kategori();
    List<Waste> wasteList = kategoriController.getAllWaste();

    // Menggunakan Set untuk menghilangkan duplikasi
    Set<String> uniqueCategories = new HashSet<>();
    for (Waste waste : wasteList) {
        uniqueCategories.add(waste.getCategory());
    }

    // Model untuk tabel kategori
    String[] columnNames = {"Kategori"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    // Menambahkan kategori unik ke model tabel
    for (String category : uniqueCategories) {
        Object[] rowData = {category};
        tableModel.addRow(rowData);
    }

    categoryTable.setModel(tableModel);

    styleTableHeader(categoryTable);
}


    // Memuat data jenis ke tabel jenis
    private void loadTypeData() {
        Kategori kategoriController = new Kategori();
        List<Waste> wasteList = kategoriController.getAllWaste();

        // Model untuk tabel jenis
        String[] columnNames = {"Jenis"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Waste waste : wasteList) {
            Object[] rowData = {waste.getType()};
            tableModel.addRow(rowData);
        }

        typeTable.setModel(tableModel);
            styleTableHeader(typeTable);
    }

    // Memuat data gabungan ke tabel gabungan
    private void loadCombinedData() {
        Kategori kategoriController = new Kategori();
        List<Waste> wasteList = kategoriController.getAllWaste();

        // Model untuk tabel gabungan
        String[] columnNames = {"Kategori", "Jenis"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Waste waste : wasteList) {
            Object[] rowData = {waste.getCategory(), waste.getType()};
            tableModel.addRow(rowData);
        }

        combinedTable.setModel(tableModel);
            styleTableHeader(combinedTable);
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
