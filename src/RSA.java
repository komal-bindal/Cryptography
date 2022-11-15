import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class RSA {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        int b = 1;
        int p = 0, q = 0;
        do {
            System.out.println("Please enter the value of p (prime) ");
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                    b = 1;
                } else {
                    b = 0;
                    break;
                }
            }
            int a = Integer.parseInt(str);
            if (isPrime(a)) {
                p = a;
                b = 1;
            } else {
                b = 0;
            }
        } while (b == 0);

        do {
            System.out.println("Please enter the value of q (prime) ");
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                    b = 1;
                } else {
                    b = 0;
                    break;
                }
            }
            int a = Integer.parseInt(str);
            if (isPrime(a)) {
                q = a;
                b = 1;
            } else {
                b = 0;
            }
        } while (b == 0);

        int n = p * q;
        int phi = (p - 1) * (q - 1);
        System.out.println(p + " " + q + " " + n + " " + phi);
        ArrayList<Integer> e = new ArrayList<>();
        for (int i = 1; i < phi; i++) {
            if (gcd(i, phi) == 1) {
                e.add(i);
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < e.size(); i++) {
            int inv = modInverse(e.get(i), phi);
            if (inv != -1) {
                map.put(e.get(i), inv);
            }
        }
        System.out.println(map);
        String text;
        b = 1;
        do {
            System.out.println("Please enter your plain message (a-z)");
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || str.charAt(i) == ' ') {
                    b = 1;
                } else {
                    b = 0;
                    break;
                }
            }
            text =str;
        } while (b == 0);

        System.out.println("Please enter the public key you choose");
        int pub = Integer.parseInt(br.readLine());
        int pri = map.get(pub);
        String cipherText = encryption(text, pub, n);
        System.out.println(cipherText);
        String decryptmsg = encryption(cipherText, pri, n);
        System.out.println(text+ " "+ decryptmsg);
    }

    private static String encryption(String str, int pub, int n) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                cipherText.append(' ');
            } else {
                char a = (char) (Math.pow(str.charAt(i) - 97, pub ) % 26);
                cipherText.append((char) (a + 65));
            }
        }
        return cipherText.toString();
    }

    private static String decryption(String str, int pri, int n) {
        StringBuilder cipherText = new StringBuilder();
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                cipherText.append(' ');
            } else {
                char a = (char) (Math.pow(str.charAt(i) - 97, pri ) % 26);
                cipherText.append((char) (a + 65));
            }
        }
        return cipherText.toString().toLowerCase();
    }
    static int modInverse(int r2, int r1) {
        int m0 = r1;
        int t1 = 0, t2 = 1;
        if (r1 == 1)
            return 0;
        while (r2 > 1) {

            int q = r1 / r2;
            int r = r1 - q * r2;
            int t = t1 - q * t2;
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
            if (r2 == 0) {
                return -1;
            }
        }
        if (t2 < 0)
            t2 += m0;
        return t2;
    }

    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        if (a == b)
            return a;
        if (a > b)
            return gcd(a - b, b);
        return gcd(a, b - a);
    }

    static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        else if (n == 2)
            return true;
        else if (n % 2 == 0)
            return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
