import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class P4_des {
  public static void main(String[] args) {
    try {
      KeyGenerator kg = KeyGenerator.getInstance("DES");
      SecretKey key = kg.generateKey();
      String pText = "Hello world";
      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] encBytes = cipher.doFinal(pText.getBytes());

      String encString = Base64.getEncoder().encodeToString(encBytes);
      System.out.println("Encrypted text: " + encString);

      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] decBytes = cipher.doFinal(Base64.getDecoder().decode(encString));
      String decString = new String(decBytes);

      System.out.println("Decrypted text: " + decString);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
