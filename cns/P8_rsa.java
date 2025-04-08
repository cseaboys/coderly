import java.util.Scanner;
import java.math.BigInteger;

class P8_rsa {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number to encrypt/decrypt:");
        int msg = sc.nextInt();
        System.out.println("Enter 1st prime number p:");
        int p = sc.nextInt();
        System.out.println("Enter 2nd prime number q:");
        int q = sc.nextInt();

        int n = p * q;
        int z = (p - 1) * (q - 1);
        int e = 2;
        while (gcd(e, z) != 1)
            e++;

        int d = 0;
        while ((1 + d * z) % e != 0)
            d++;
        d = (1 + d * z) / e;

        System.out.println("z = " + z + ", e = " + e + ", d = " + d);
        double c = Math.pow(msg, e) % n;
        System.out.println("Encrypted message: " + c);

        BigInteger N = BigInteger.valueOf(n);
        BigInteger C = BigInteger.valueOf((long) c);
        System.out.println("Decrypted message: " + C.pow(d).mod(N));
    }

    static int gcd(int e, int z) {
        return e == 0 ? z : gcd(z % e, e);
    }
}