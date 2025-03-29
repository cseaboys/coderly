import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.util.Base64;

public class P7_blowfishencryption {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            
            String text = "Hello world";
            System.out.println("Original Text: " + text);
            
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String encryptedText = Base64.getEncoder().encodeToString(
                cipher.doFinal(text.getBytes()));
            System.out.println("Encrypted Text: " + encryptedText);
            
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            String decryptedText = new String(
                cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}