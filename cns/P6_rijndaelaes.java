import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.util.Base64;

public class P6_rijndaelaes {
    public static void main(String[] args) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey sck = kg.generateKey();
            
            String plainTxt = "Hello world";
            System.out.println("Original Text: " + plainTxt);
            
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sck);
            String encText = Base64.getEncoder().encodeToString(
                cipher.doFinal(plainTxt.getBytes()));
            System.out.println("Encrypted Text: " + encText);
            
            cipher.init(Cipher.DECRYPT_MODE, sck);
            String decText = new String(
                cipher.doFinal(Base64.getDecoder().decode(encText)));
            System.out.println("Decrypted Text: " + decText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}