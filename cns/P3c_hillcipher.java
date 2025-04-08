import java.util.Scanner;

public class P3c_hillcipher {
    static int[][] km = new int[2][2];
    static int[][] ikm = new int[2][2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the 2x2 key matrix (integers):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                km[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter even length plaintext: ");
        String pText = sc.next().toUpperCase();

        if (pText.length() % 2 != 0) {
            pText += "X";
        }

        String cText = encrypt(pText);
        System.out.println("Ciphertext: " + cText);

        if (!calculateInverseKeyMatrix()) {
            System.out.println("Key matrix is not invertible. Exiting");
        } else {
            String decryptedText = decrypt(cText);
            System.out.println("Decrypted text: " + decryptedText);
        }

        sc.close();
    }

    public static String encrypt(String pText) {
        StringBuilder cText = new StringBuilder();

        for (int i = 0; i < pText.length(); i += 2) {
            int[] pair = {
                    pText.charAt(i) - 'A',
                    pText.charAt(i + 1) - 'A'
            };

            int[] result = multiplyMatrix(km, pair);
            int firstChar = ((result[0] % 26) + 26) % 26;
            int secondChar = ((result[1] % 26) + 26) % 26;

            cText.append((char) (firstChar + 'A'));
            cText.append((char) (secondChar + 'A'));
        }

        return cText.toString();
    }

    public static String decrypt(String cText) {
        StringBuilder pText = new StringBuilder();

        for (int i = 0; i < cText.length(); i += 2) {
            int[] pair = {
                    cText.charAt(i) - 'A',
                    cText.charAt(i + 1) - 'A'
            };

            int[] result = multiplyMatrix(ikm, pair);
            int firstChar = ((result[0] % 26) + 26) % 26;
            int secondChar = ((result[1] % 26) + 26) % 26;

            pText.append((char) (firstChar + 'A'));
            pText.append((char) (secondChar + 'A'));
        }

        return pText.toString();
    }

    public static int[] multiplyMatrix(int[][] matrix, int[] pair) {
        int[] result = new int[2];
        result[0] = matrix[0][0] * pair[0] + matrix[0][1] * pair[1];
        result[1] = matrix[1][0] * pair[0] + matrix[1][1] * pair[1];
        return result;
    }

    public static boolean calculateInverseKeyMatrix() {
        int determinant = km[0][0] * km[1][1] - km[0][1] * km[1][0];
        determinant = ((determinant % 26) + 26) % 26;

        int modularInverse = -1;
        for (int i = 0; i < 26; i++) {
            if ((determinant * i) % 26 == 1) {
                modularInverse = i;
                break;
            }
        }

        if (modularInverse == -1)
            return false;

        ikm[0][0] = (km[1][1] * modularInverse) % 26;
        ikm[0][1] = (-km[0][1] * modularInverse) % 26;
        ikm[1][0] = (-km[1][0] * modularInverse) % 26;
        ikm[1][1] = (km[0][0] * modularInverse) % 26;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ikm[i][j] = ((ikm[i][j] % 26) + 26) % 26;
            }
        }

        return true;
    }
}
