

import view.HalamanLogin;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Menjalankan aplikasi pada Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Membuka Halaman Login sebagai halaman pertama
            HalamanLogin loginForm = new HalamanLogin();
            loginForm.setVisible(true);
        });
    }
}
