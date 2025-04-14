import javax.swing.*;
import java.awt.*;

public class GiaoDienMaHoa extends JFrame {
    private JTextField txtInput;
    private JTextArea txtKetQua;
    private JButton btnAES, btnRSA;

    public GiaoDienMaHoa() {
        setTitle("Mã hóa AES & RSA");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        txtInput = new JTextField(30);
        txtKetQua = new JTextArea(10, 40);
        txtKetQua.setEditable(false);

        btnAES = new JButton("Mã hóa bằng AES");
        btnRSA = new JButton("Mã hóa bằng RSA");

        btnAES.addActionListener(e -> {
            Encryptable aes = new MaHoaAES();
            String input = txtInput.getText();
            String maHoa = aes.maHoa(input);
            String giaiMa = aes.giaiMa(maHoa);

            txtKetQua.setText("Mã hóa AES: " + maHoa + "\nGiải mã AES: " + giaiMa);
        });

        btnRSA.addActionListener(e -> {
            Encryptable rsa = new MaHoaRSA();
            String input = txtInput.getText();
            String maHoa = rsa.maHoa(input);
            String giaiMa = rsa.giaiMa(maHoa);

            txtKetQua.setText("Mã hóa RSA: " + maHoa + "\nGiải mã RSA: " + giaiMa);
        });

        add(new JLabel("Nhập văn bản cần mã hóa:"));
        add(txtInput);
        add(btnAES);
        add(btnRSA);
        add(new JScrollPane(txtKetQua));
    }

    public static void main(String[] args) {
        new GiaoDienMaHoa().setVisible(true);
    }
}
