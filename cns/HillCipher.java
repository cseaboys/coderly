import java.util.Scanner;

public class HillCipher {
    static int[][] km = new int[2][2];
    static int[][] ikm = new int[2][2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read key matrix.
        System.out.println("Enter the 2x2 key matrix (integers):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                km[i][j] = sc.nextInt();
            }
        }

        // Read plaintext.
        System.out.println("Enter even length plaintext: ");
        String pText = sc.next().toUpperCase();

        // Case 3: Check if plaintext length is odd and add 'X'
        if (pText.length() % 2 != 0) {
            pText += "X";
        }

        // Encrypt plaintext.
        String cText = encrypt(pText);
        System.out.println("Ciphertext: " + cText);

        // Calculate the inverse key matrix.
        // Case 2: If not invertible, warn the user.
        if (!calculateInverseKeyMatrix()) {
            System.out.println("Key matrix is not invertible. Exiting");
        } else {
            // Case 1: Matrix is invertible, so perform decryption.
            String decryptedText = decrypt(cText);
            System.out.println("Decrypted text: " + decryptedText);
        }

        sc.close();
    }

    // Encrypts the plaintext using the key matrix.
    public static String encrypt(String pText) {
        StringBuilder cText = new StringBuilder();

        for (int i = 0; i < pText.length(); i += 2) {
            int[] pair = {
                pText.charAt(i) - 'A',
                pText.charAt(i + 1) - 'A'
            };

            int[] result = multiplyMatrix(km, pair);
            // Normalize the result to [0,25]
            int firstChar = ((result[0] % 26) + 26) % 26;
            int secondChar = ((result[1] % 26) + 26) % 26;

            cText.append((char) (firstChar + 'A'));
            cText.append((char) (secondChar + 'A'));
        }

        return cText.toString();
    }

    // Decrypts the ciphertext using the inverse key matrix.
    public static String decrypt(String cText) {
        StringBuilder pText = new StringBuilder();

        for (int i = 0; i < cText.length(); i += 2) {
            int[] pair = {
                cText.charAt(i) - 'A',
                cText.charAt(i + 1) - 'A'
            };

            int[] result = multiplyMatrix(ikm, pair);
            // Normalize the result to [0,25]
            int firstChar = ((result[0] % 26) + 26) % 26;
            int secondChar = ((result[1] % 26) + 26) % 26;

            pText.append((char) (firstChar + 'A'));
            pText.append((char) (secondChar + 'A'));
        }

        return pText.toString();
    }

    // Multiplies a 2x2 matrix with a 2-element vector.
    public static int[] multiplyMatrix(int[][] matrix, int[] pair) {
        int[] result = new int[2];
        result[0] = matrix[0][0] * pair[0] + matrix[0][1] * pair[1];
        result[1] = matrix[1][0] * pair[0] + matrix[1][1] * pair[1];
        return result;
    }

    // Calculates the modular inverse of the key matrix.
    // Returns false if the key matrix is not invertible modulo 26.
    public static boolean calculateInverseKeyMatrix() {
        int determinant = km[0][0] * km[1][1] - km[0][1] * km[1][0];
        // Normalize determinant in the range [0,25]
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

        // Compute the inverse key matrix elements and normalize them.
        ikm[0][0] = (km[1][1] * modularInverse) % 26;
        ikm[0][1] = (-km[0][1] * modularInverse) % 26;
        ikm[1][0] = (-km[1][0] * modularInverse) % 26;
        ikm[1][1] = (km[0][0] * modularInverse) % 26;

        // Ensure each element is in the range [0,25]
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ikm[i][j] = ((ikm[i][j] % 26) + 26) % 26;
            }
        }

        return true;
    }
}
