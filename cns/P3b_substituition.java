import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class P3b_substituition {
    static String sbs(String txt) {
        Map letter_map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            letter_map.put((char) ('a' + i), (char) ('z' - i));
        }
        StringBuilder res = new StringBuilder();
        txt.chars().forEach(c -> res.append(letter_map.getOrDefault((char) c, (char) c)));
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String txt = sc.nextLine();
        String enc = sbs(txt);
        String dec = sbs(enc);
        System.out.println(enc + "\n" + dec);
        sc.close();
    }
}
