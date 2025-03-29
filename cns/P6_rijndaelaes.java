import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.util.Base64;

public class P6_rijndaelaes {
    public static void main(String[] args) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            
            String plaintext = "Hello world";
            System.out.println("Original Text: " + plaintext);
            
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String encryptedText = Base64.getEncoder().encodeToString(
                cipher.doFinal(plaintext.getBytes()));
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