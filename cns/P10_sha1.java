import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class P10_sha1 {
    public static String getSHA1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not found!", e);
        }
    }

    public static void main(String[] args) {
        String text = "Hello, World!";
        String sha1Hash = getSHA1(text);

        System.out.println("Original Text: " + text);
        System.out.println("SHA-1 Hash: " + sha1Hash);
    }
}