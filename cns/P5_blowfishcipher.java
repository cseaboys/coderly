import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class P5_blowfishcipher {
    private static final int ROUNDS = 16, BLOCK_SIZE = 8, S_BOXES_SIZE = 4 * 256;
    private int[] P, S;
    private static final int[] P_INIT = {
            0x243F6A88, 0x85A308D3, 0x13198A2E, 0x03707344, 0xA4093822, 0x299F31D0,
            0x082EFA98, 0xEC4E6C89, 0x452821E6, 0x38D01377, 0xBE5466CF, 0x34E90C6C,
            0xC0AC29B7, 0xC97C50DD, 0x3F84D5B5, 0xB5470917, 0x9216D5D9, 0x8979FB1B
    };
    private static final int[] S_INIT = new int[S_BOXES_SIZE];

    public P5_blowfishcipher(byte[] key) {
        P = Arrays.copyOf(P_INIT, P_INIT.length);
        S = Arrays.copyOf(S_INIT, S_INIT.length);
        for (int i = 0, len = key.length; i < P.length; i++) {
            P[i] ^= ((key[i % len] & 0xFF) << 24) |
                    ((key[(i + 1) % len] & 0xFF) << 16) |
                    ((key[(i + 2) % len] & 0xFF) << 8) |
                    (key[(i + 3) % len] & 0xFF);
        }
    }

    private int feistel(int x) {
        return ((S[(x >>> 24) & 0xFF] + S[256 + ((x >>> 16) & 0xFF)]) ^
                S[512 + ((x >>> 8) & 0xFF)]) + S[768 + (x & 0xFF)];
    }

    public byte[] encryptBlock(byte[] block) {
        int left = ByteBuffer.wrap(block, 0, 4).getInt();
        int right = ByteBuffer.wrap(block, 4, 4).getInt();
        for (int i = 0; i < ROUNDS; i++) {
            left ^= P[i];
            right ^= feistel(left);
            int tmp = left;
            left = right;
            right = tmp;
        }
        int tmp = left;
        left = right;
        right = tmp;
        right ^= P[ROUNDS];
        left ^= P[ROUNDS + 1];
        return ByteBuffer.allocate(BLOCK_SIZE).putInt(left).putInt(right).array();
    }

    public byte[] decryptBlock(byte[] block) {
        int left = ByteBuffer.wrap(block, 0, 4).getInt();
        int right = ByteBuffer.wrap(block, 4, 4).getInt();
        for (int i = ROUNDS + 1; i > 1; i--) {
            left ^= P[i];
            right ^= feistel(left);
            int tmp = left;
            left = right;
            right = tmp;
        }
        int tmp = left;
        left = right;
        right = tmp;
        right ^= P[1];
        left ^= P[0];
        return ByteBuffer.allocate(BLOCK_SIZE).putInt(left).putInt(right).array();
    }

    public static void main(String[] args) {
        String text = "abcdefghi";
        byte[] key = "SecretKey".getBytes(StandardCharsets.UTF_8);
        P5_blowfishcipher bf = new P5_blowfishcipher(key);
        System.out.println("Original: " + text);
        byte[] enc = bf.encryptBlock(text.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encrypted: " + Arrays.toString(enc));
        byte[] dec = bf.decryptBlock(enc);
        System.out.println("Decrypted: " + new String(dec, StandardCharsets.UTF_8));
    }
}
