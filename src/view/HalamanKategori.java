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
import model.WasteMapper;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

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
        
        
        JPanel actionAndBackPanel = new JPanel(new BorderLayout());
        // Tambahkan tombol aksi di bawah tabel gabungan
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton addButton = new JButton("Tambah");
        JButton editButton = new JButton("Ubah");
        JButton deleteButton = new JButton("Hapus");

        actionPanel.add(addButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);

        // Tambahkan panel tombol aksi ke mainPanel

        // Tambahkan action listener ke tombol
        addButton.addActionListener(new AddActionListener());
        editButton.addActionListener(new EditActionListener());
        deleteButton.addActionListener(new DeleteActionListener());
        
        

        // Tombol Kembali
        backButton = new JButton("Kembali ke Beranda");
        backButton.addActionListener(new BackButtonActionListener());
        backButton.setBackground(new Color(59, 89, 152)); // Warna merah
        backButton.setForeground(Color.WHITE); // Teks tombol warna putih
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        
        // Tambahkan kedua panel ke panel vertikal
actionAndBackPanel.add(actionPanel, BorderLayout.CENTER); // Tombol aksi di tengah
actionAndBackPanel.add(buttonPanel, BorderLayout.SOUTH);  // Tombol kembali di bawah

// Tambahkan panel vertikal ke mainPanel
mainPanel.add(actionAndBackPanel, BorderLayout.SOUTH);

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
    String[] columnNames = {"ID", "Kategori", "Jenis"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    for (Waste waste : wasteList) {
        Object[] rowData = {waste.getId(), waste.getCategory(), waste.getType()};
        tableModel.addRow(rowData);
    }

    combinedTable.setModel(tableModel);
    styleTableHeader(combinedTable);

    // Sembunyikan kolom ID
    combinedTable.getColumnModel().getColumn(0).setMinWidth(0);
    combinedTable.getColumnModel().getColumn(0).setMaxWidth(0);
    combinedTable.getColumnModel().getColumn(0).setWidth(0);
}


    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Pindah ke halaman beranda
            new HalamanBeranda(currentUser).setVisible(true); // Memastikan currentUser diteruskan
            HalamanKategori.this.dispose(); // Menutup halaman kategori
        }
    }
    
    private class AddActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField categoryField = new JTextField();
        JTextField typeField = new JTextField();
        Object[] message = {
            "Kategori:", categoryField,
            "Jenis:", typeField
        };

        int option = JOptionPane.showConfirmDialog(
            HalamanKategori.this,
            message,
            "Tambah Data",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            String category = categoryField.getText();
            String type = typeField.getText();

            if (!category.isEmpty() && !type.isEmpty()) {
                Kategori kategoriController = new Kategori();
                Waste newWaste = new Waste();
                newWaste.setCategory(category);
                newWaste.setType(type);

                try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                    WasteMapper wasteMapper = session.getMapper(WasteMapper.class);
                    wasteMapper.insertWaste(newWaste);
                    session.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                loadCategoryData();
                loadTypeData();
                loadCombinedData();
                JOptionPane.showMessageDialog(HalamanKategori.this, "Data berhasil ditambahkan.");
            } else {
                JOptionPane.showMessageDialog(HalamanKategori.this, "Data tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
    
    private class EditActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = combinedTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(HalamanKategori.this, "Pilih baris yang ingin diubah.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ambil ID dari kolom tersembunyi
        int id = (int) combinedTable.getValueAt(selectedRow, 0); // Kolom 0 adalah ID
        String currentCategory = combinedTable.getValueAt(selectedRow, 1).toString();
        String currentType = combinedTable.getValueAt(selectedRow, 2).toString();

        JTextField categoryField = new JTextField(currentCategory);
        JTextField typeField = new JTextField(currentType);
        Object[] message = {
            "Kategori Baru:", categoryField,
            "Jenis Baru:", typeField
        };

        int option = JOptionPane.showConfirmDialog(
            HalamanKategori.this,
            message,
            "Ubah Data",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            String newCategory = categoryField.getText();
            String newType = typeField.getText();

            if (!newCategory.isEmpty() && !newType.isEmpty()) {
                try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                    WasteMapper wasteMapper = session.getMapper(WasteMapper.class);
                    Waste wasteToUpdate = wasteMapper.selectWasteById(id); // Ambil data berdasarkan ID
                    wasteToUpdate.setCategory(newCategory);
                    wasteToUpdate.setType(newType);

                    wasteMapper.updateWaste(wasteToUpdate);
                    session.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                loadCategoryData();
                loadTypeData();
                loadCombinedData();
                JOptionPane.showMessageDialog(HalamanKategori.this, "Data berhasil diubah.");
            } else {
                JOptionPane.showMessageDialog(HalamanKategori.this, "Data tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}


    private class DeleteActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = combinedTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(HalamanKategori.this, "Pilih baris yang ingin dihapus.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ambil ID dari kolom tersembunyi
        int id = (int) combinedTable.getValueAt(selectedRow, 0); // Kolom 0 adalah ID

        int option = JOptionPane.showConfirmDialog(
            HalamanKategori.this,
            "Apakah Anda yakin ingin menghapus data ini?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION
        );

        if (option == JOptionPane.YES_OPTION) {
            try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                WasteMapper wasteMapper = session.getMapper(WasteMapper.class);
                wasteMapper.deleteWaste(id); // Hapus berdasarkan ID
                session.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            loadCategoryData();
            loadTypeData();
            loadCombinedData();
            JOptionPane.showMessageDialog(HalamanKategori.this, "Data berhasil dihapus.");
        }
    }
}


}
