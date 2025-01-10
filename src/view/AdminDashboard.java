package view;

import controller.Kategori;
import model.Waste;
import model.WasteMapper;
import org.apache.ibatis.session.SqlSession;
import util.MyBatisUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.UserInfo;
import model.UserInfoMapper;

public class AdminDashboard extends JFrame {
    private JTable wasteTable, userTable;
    private DefaultTableModel wasteTableModel, userTableModel;
    private Kategori kategoriController;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        kategoriController = new Kategori();
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        // Create Tables
        wasteTableModel = new DefaultTableModel(new String[]{"ID", "Category", "Type"}, 0);
        userTableModel = new DefaultTableModel(new String[]{"ID", "Username", "Email", "Role", "Address"}, 0);

        wasteTable = new JTable(wasteTableModel);
        userTable = new JTable(userTableModel);

        // Create Panels for CRUD Buttons and Tables
        JPanel wastePanel = createCrudPanel("Waste Data", wasteTable, wasteTableModel);
        JPanel userPanel = createCrudPanel("User Data", userTable, userTableModel);

        // Add Panels to Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Waste Data", wastePanel);
        tabbedPane.addTab("User Data", userPanel);
        
        // Create Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> handleLogout());
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);

        // Add Tabbed Pane to Frame
        add(tabbedPane, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);

        // Load Initial Data
        loadDataToWasteTable();
        loadDataToUserTable();
    }
    
     private void handleLogout() {
        int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            this.dispose(); // Close current dashboard
            // Uncomment this if you have a Login screen class:
            // new LoginScreen().setVisible(true); 
        }
    }

    private JPanel createCrudPanel(String title, JTable table, DefaultTableModel tableModel) {
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel buttonPanel = new JPanel();

        // Create Buttons
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        // Add Buttons to Panel
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Add Action Listeners for Buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (title.equals("Waste Data")) {
                    handleAddWaste();
                } else {
                    handleAddUser();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    if (title.equals("Waste Data")) {
                        handleUpdateWaste(selectedRow);
                    } else {
                        handleUpdateUser(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    if (title.equals("Waste Data")) {
                        handleDeleteWaste(selectedRow);
                    } else {
                        handleDeleteUser(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                }
            }
        });

        // Add Components to Panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void handleAddWaste() {
        String category = JOptionPane.showInputDialog("Enter Category:");
        String type = JOptionPane.showInputDialog("Enter Type:");
        if (category != null && type != null) {
            Waste waste = new Waste();
            waste.setCategory(category);
            waste.setType(type);

            try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                WasteMapper wasteMapper = session.getMapper(WasteMapper.class);
                wasteMapper.insertWaste(waste);
                session.commit();
            }
            loadDataToWasteTable(); // Refresh table
        }
    }

    private void handleUpdateWaste(int selectedRow) {
        int id = (int) wasteTable.getValueAt(selectedRow, 0);
        String category = JOptionPane.showInputDialog("Enter new Category:");
        String type = JOptionPane.showInputDialog("Enter new Type:");

        if (category != null && type != null) {
            Waste waste = new Waste();
            waste.setId(id);
            waste.setCategory(category);
            waste.setType(type);

            try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
                WasteMapper wasteMapper = session.getMapper(WasteMapper.class);
                wasteMapper.updateWaste(waste);
                session.commit();
            }
            loadDataToWasteTable(); // Refresh table
        }
    }

    private void handleDeleteWaste(int selectedRow) {
        int id = (int) wasteTable.getValueAt(selectedRow, 0);

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            WasteMapper wasteMapper = session.getMapper(WasteMapper.class);
            wasteMapper.deleteWaste(id);
            session.commit();
        }
        loadDataToWasteTable(); // Refresh table
    }

private void handleAddUser() {
    String username = JOptionPane.showInputDialog("Enter Username:");
    String email = JOptionPane.showInputDialog("Enter Email:");
    String role = JOptionPane.showInputDialog("Enter Role:");
    String address = JOptionPane.showInputDialog("Enter Address:");

    if (username != null && email != null && role != null && address != null) {
        UserInfo newUser = new UserInfo();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setRole(role);
        newUser.setAddress(address);

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
            userMapper.insertUser(newUser);
            session.commit();
            loadDataToUserTable(); // Refresh tabel
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menambahkan pengguna ke database.");
        }
    }
}


private void handleUpdateUser(int selectedRow) {
    int id = (int) userTable.getValueAt(selectedRow, 0);
    String username = JOptionPane.showInputDialog("Enter new Username:");
    String email = JOptionPane.showInputDialog("Enter new Email:");
    String role = JOptionPane.showInputDialog("Enter new Role:");
    String address = JOptionPane.showInputDialog("Enter new Address:");

    if (username != null && email != null && role != null && address != null) {
        UserInfo updatedUser = new UserInfo();
        updatedUser.setId(id);
        updatedUser.setUsername(username);
        updatedUser.setEmail(email);
        updatedUser.setRole(role);
        updatedUser.setAddress(address);

        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
            userMapper.updateUserProfile(updatedUser);
            session.commit();
            loadDataToUserTable(); // Refresh tabel
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data pengguna.");
        }
    }
}


private void handleDeleteUser(int selectedRow) {
    int id = (int) userTable.getValueAt(selectedRow, 0);

    try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
        UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
        userMapper.deleteUserById(id);
        session.commit();
        loadDataToUserTable(); // Refresh tabel
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal menghapus pengguna.");
    }
}




    private void loadDataToWasteTable() {
        List<Waste> wasteList = kategoriController.getAllWaste();
        wasteTableModel.setRowCount(0);
        for (Waste waste : wasteList) {
            wasteTableModel.addRow(new Object[]{waste.getId(), waste.getCategory(), waste.getType()});
        }
    }

private void loadDataToUserTable() {
    try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
        UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
        List<UserInfo> userList = userMapper.selectAllUsers(); // Mengambil semua data pengguna
        userTableModel.setRowCount(0); // Membersihkan tabel sebelum memuat data baru
        for (UserInfo user : userList) {
            userTableModel.addRow(new Object[]{
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getAddress()
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Gagal memuat data pengguna dari database.");
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
    }
}
