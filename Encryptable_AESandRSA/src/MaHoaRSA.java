import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class MaHoaRSA implements Encryptable {
    private static KeyPair capKhoa;

    static {
        try {
            KeyPairGenerator taoKhoa = KeyPairGenerator.getInstance("RSA");
            taoKhoa.initialize(2048);
            capKhoa = taoKhoa.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String maHoa(String duLieuGoc) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, capKhoa.getPublic());
            byte[] ketQua = cipher.doFinal(duLieuGoc.getBytes());
            return Base64.getEncoder().encodeToString(ketQua);
        } catch (Exception e) {
            return "Lỗi mã hóa RSA";
        }
    }

    @Override
    public String giaiMa(String duLieuMaHoa) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, capKhoa.getPrivate());
            byte[] giaiMa = cipher.doFinal(Base64.getDecoder().decode(duLieuMaHoa));
            return new String(giaiMa);
        } catch (Exception e) {
            return "Lỗi giải mã RSA";
        }
    }
}
