import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.util.Base64;

public class P7_blowfishencryption {
    public static void main(String[] args) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
            kg.init(128);
            SecretKey sck = kg.generateKey();
            
            String text = "Hello world";
            System.out.println("Original Text: " + text);
            
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, sck);
            String encText = Base64.getEncoder().encodeToString(
                cipher.doFinal(text.getBytes()));
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