import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MultiplicativeCipher {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        int n;
        do {
            System.out.println("-----MENU FOR MULTIPLICATIVE CIPHER-----");
            System.out.println("1. Encryption");
            System.out.println("2. Decryption");
            System.out.println("3. Brute Force");
            System.out.println("4. Exit");
            System.out.println("Waiting for input.....");
            do {
                System.out.println("Please enter the correct no. (1-4)");
                String str = br.readLine();
                if (str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4")) {
                    n = Integer.parseInt(str);
                } else {
                    n = -1;
                }
            } while (n == -1);
            String str;
            int b = 1;
            String key;
            switch (n) {
                case 1:
                    b = 1;
                    do {
                        System.out.println("Please enter your plain message (a-z || A-Z)");
                        str = br.readLine();
                        for (int i = 0; i < str.length(); i++) {
                            if (Character.isAlphabetic(str.charAt(i)) || str.charAt(i) == ' ') {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    b = 1;
                    do {
                        System.out.println("Please enter your key");
                        key = br.readLine();
                        for (int i = 0; i < key.length(); i++) {
                            if ((key.charAt(i) >= '0' && key.charAt(i) <= '9')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                        if (b == 1) {
                            int inv = modInverse(Integer.parseInt(key) % 26, 26);
                            if (inv == -1) {
                                System.out.println("Key inverse cannot be found. Please enter valid key");
                                b = 0;
                            }
                        }
                    } while (b == 0);
                    int k = Integer.parseInt(key);
                    String cipherText = encrypt(str, k % 26);
                    System.out.println("EncryptedText ---> " + cipherText);
                    break;
                case 2:
                    b = 1;
                    do {
                        System.out.println("Please enter your plain message (A-Z || a-z) ");
                        str = br.readLine();
                        for (int i = 0; i < str.length(); i++) {
                            if (Character.isAlphabetic(str.charAt(i)) || str.charAt(i) == ' ') {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }

                    } while (b == 0);
                    b = 1;
                    do {
                        System.out.println("Please enter your key");
                        key = br.readLine();
                        for (int i = 0; i < key.length(); i++) {
                            if ((key.charAt(i) >= '0' && key.charAt(i) <= '9')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                        if (b == 1) {
                            int inv = modInverse(Integer.parseInt(key) % 26, 26);
                            if (inv == -1) {
                                System.out.println("Key inverse cannot be found. Please enter valid key");
                                b = 0;
                            }
                        }
                    } while (b == 0);
                    int k1 = Integer.parseInt(key);

                    String decryptedText = decrypt(str, k1 % 26);
                    System.out.println("DecryptedText ----> " + decryptedText);
                    break;
                case 3:
                    String plainText;
                    b = 1;
                    do {
                        System.out.println("Please enter your plain message (a-z || A-Z)");
                        plainText = br.readLine();
                        for (int i = 0; i < plainText.length(); i++) {
                            if (Character.isAlphabetic(plainText.charAt(i)) || plainText.charAt(i) == ' ') {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    String encrypted;
                    b = 1;
                    do {
                        System.out.println("Please enter your encrypted message (A-Z || a-z)");
                        encrypted = br.readLine();
                        for (int i = 0; i < encrypted.length(); i++) {
                            if (Character.isAlphabetic(encrypted.charAt(i)) || encrypted.charAt(i) == ' ') {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    int k2 = bruteForce(plainText, encrypted);
                    if (k2 == -1) {
                        System.out.println("key cannot be found");
                    } else {
                        System.out.println("Key to decrypt ---> " + k2);
                    }
                    break;
                case 4:
                    break;
                default:
                    break;
            }

        } while (n != 4);
    }

    public static String encrypt(String text, int s) {
        StringBuffer result1 = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result1.append(text.charAt(i));
            } else if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) ((((int) text.charAt(i) - 65) * s) % 26 + 65);
                result1.append(ch);
            } else {
                char ch = (char) ((((int) text.charAt(i) - 97) * s) % 26 + 97);
                result1.append(ch);
            }
        }
        return result1.toString();
    }

    static int modInverse(int n, int m) {
        int m0 = m;
        int y = 0, x = 1;
        if (m == 1)
            return 0;
        while (n > 1) {
            // q is quotient
            if (m == 0) {
                return -1;
            }
            int q = n / m;
            int t = m;
            m = n % m;
            n = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0)
            x += m0;
        return x;
    }

    public static String decrypt(String text, int s) {
        StringBuffer result = new StringBuffer();
        int s_inv = modInverse(s, 26);
        if (s_inv == -1) {
            System.out.println("can't find");
            return text;
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result.append(text.charAt(i));
            } else if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) ((((int) text.charAt(i) - 65) * s_inv) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) ((((int) text.charAt(i) - 97) * s_inv) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static int bruteForce(String plainText, String encrypted) {
        for (int k = 0; k < 26; k++) {
            if (modInverse(k, 26) != -1) {
                String decrypted = decrypt(encrypted, k);
                if (decrypted.equals(plainText)) {
                    return k;
                }
            }
        }
        return -1;
    }
}