import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;

public class DangNhapUI extends JFrame {
    private JTextField txtTenDangNhap;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap;

    // Mật khẩu hash mẫu trong hệ thống (vd: mật khẩu là "admin123")
    private final String matKhauDaHash = hashMatKhau("admin123");

    public DangNhapUI() {
        setTitle("Đăng nhập bảo mật");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        txtTenDangNhap = new JTextField();
        txtMatKhau = new JPasswordField();
        btnDangNhap = new JButton("Đăng nhập");

        add(new JLabel("Tên đăng nhập:"));
        add(txtTenDangNhap);
        add(new JLabel("Mật khẩu:"));
        add(txtMatKhau);
        add(new JLabel());
        add(btnDangNhap);

        btnDangNhap.addActionListener(this::xuLyDangNhap);
    }

    private void xuLyDangNhap(ActionEvent e) {
        String tenDN = txtTenDangNhap.getText();
        String matKhauNhap = new String(txtMatKhau.getPassword());
        String matKhauHash = hashMatKhau(matKhauNhap);

        if (tenDN.equals("admin") && matKhauHash.equals(matKhauDaHash)) {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu.");
        }
    }

    // Hàm hash bằng SHA-256
    private String hashMatKhau(String matKhau) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // hoặc "MD5"
            byte[] bytes = md.digest(matKhau.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        new DangNhapUI().setVisible(true);
    }
}
