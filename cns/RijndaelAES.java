import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class RijndaelAES {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();

            String plaintext = "This is a Rijndael AES logic!";
            System.out.println("Original Text: " + plaintext);

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encBytes = cipher.doFinal(plaintext.getBytes());
            String encString = Base64.getEncoder().encodeToString(encBytes);

            System.out.println("Encrypted text: " + encString);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decBytes = cipher.doFinal(Base64.getDecoder().decode(encString));
            String decString = new String(decBytes);
            System.out.println("Decrypted text: " + decString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}