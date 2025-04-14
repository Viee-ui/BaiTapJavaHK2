import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class MaHoaAES {
    private static final String KHOA = "1234567890123456"; // 16 ký tự = 128 bit

    public static String maHoa(String duLieuGoc) {
        try {
            SecretKeySpec key = new SecretKeySpec(KHOA.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] ketQua = cipher.doFinal(duLieuGoc.getBytes());
            return Base64.getEncoder().encodeToString(ketQua);
        } catch (Exception e) {
            return "Lỗi mã hóa AES";
        }
    }

    public static String giaiMa(String duLieuMaHoa) {
        try {
            SecretKeySpec key = new SecretKeySpec(KHOA.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] giaiMa = cipher.doFinal(Base64.getDecoder().decode(duLieuMaHoa));
            return new String(giaiMa);
        } catch (Exception e) {
            return "Lỗi giải mã AES";
        }
    }
}
