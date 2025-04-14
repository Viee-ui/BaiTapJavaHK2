import javax.swing.*;
import java.awt.*;

public class GiaoDienDaLuong extends JFrame {
    private JTextField txtDuLieu;
    private JTextArea txtKetQua;
    private JButton btnThucHien;

    public GiaoDienDaLuong() {
        setTitle("Mã hóa/Giải mã đồng thời bằng đa luồng");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        txtDuLieu = new JTextField(30);
        txtKetQua = new JTextArea();
        txtKetQua.setEditable(false);
        btnThucHien = new JButton("Bắt đầu mã hóa và giải mã");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Nhập dữ liệu:"));
        topPanel.add(txtDuLieu);
        topPanel.add(btnThucHien);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(txtKetQua), BorderLayout.CENTER);

        btnThucHien.addActionListener(e -> {
            txtKetQua.setText(""); // Xóa cũ
            String duLieu = txtDuLieu.getText();

            // Luồng mã hóa
            Thread maHoaThread = new Thread(() -> {
                String maHoa = MaHoaAES.maHoa(duLieu);
                SwingUtilities.invokeLater(() -> txtKetQua.append("🔐 Đã mã hóa: " + maHoa + "\n"));
            });

            // Luồng giải mã
            Thread giaiMaThread = new Thread(() -> {
                try {
                    Thread.sleep(500); // Chờ luồng mã hóa một chút
                    String maHoa = MaHoaAES.maHoa(duLieu); // Mã hóa lại để giải mã
                    String giaiMa = MaHoaAES.giaiMa(maHoa);
                    SwingUtilities.invokeLater(() -> txtKetQua.append("🔓 Đã giải mã: " + giaiMa + "\n"));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });

            // Bắt đầu cả hai luồng
            maHoaThread.start();
            giaiMaThread.start();
        });
    }

    public static void main(String[] args) {
        new GiaoDienDaLuong().setVisible(true);
    }
}
