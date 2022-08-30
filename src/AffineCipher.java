import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AffineCipher {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        int n;
        do {
            System.out.println("-----MENU FOR AFFINE CIPHER-----");
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
            String key;
            int b = 1;
            switch (n) {
                case 1:
                    b = 1;
                    do {
                        System.out.println("Please enter your plain message (a-z)");
                        str = br.readLine();
                        for (int i = 0; i < str.length(); i++) {
                            if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || str.charAt(i) == ' ') {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    b = 1;
                    do {
                        System.out.println("Please enter your key for multiplicative");
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
                    b = 1;
                    do {
                        System.out.println("Please enter your key for additive");
                        key = br.readLine();
                        for (int i = 0; i < key.length(); i++) {
                            if ((key.charAt(i) >= '0' && key.charAt(i) <= '9')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    int k2 = Integer.parseInt(key);
                    String cipherText = encrypt(str, k1 % 26, k2 % 26);
                    System.out.println("EncryptedText ---> " + cipherText);
                    break;
                case 2:
                    b = 1;
                    do {
                        System.out.println("Please enter your plain message (A-Z) ");
                        str = br.readLine();
                        for (int i = 0; i < str.length(); i++) {
                            if ((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || str.charAt(i) == ' ') {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }

                    } while (b == 0);
                    b = 1;
                    do {
                        System.out.println("Please enter your key for multiplicative");
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
                    k1 = Integer.parseInt(key);
                    b = 1;
                    do {
                        System.out.println("Please enter your key for additive");
                        key = br.readLine();
                        for (int i = 0; i < key.length(); i++) {
                            if ((key.charAt(i) >= '0' && key.charAt(i) <= '9')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    k2 = Integer.parseInt(key);

                    String decryptedText = decrypt(str, k1 % 26, k2 % 26);
                    System.out.println("DecryptedText ----> " + decryptedText);
                    break;
                case 3:
                    String plainText;
                    b = 1;
                    do {
                        System.out.println("Please enter your plain message (a-z)");
                        plainText = br.readLine();
                        for (int i = 0; i < plainText.length(); i++) {
                            if ((plainText.charAt(i) >= 'a' && plainText.charAt(i) <= 'z') || plainText.charAt(i) == ' ') {
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
                        System.out.println("Please enter your encrypted message (A-Z )");
                        encrypted = br.readLine();
                        for (int i = 0; i < encrypted.length(); i++) {
                            if ((encrypted.charAt(i) >= 'A' && encrypted.charAt(i) <= 'Z') || encrypted.charAt(i) == ' ') {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    int[] bruteForceKeys = bruteForce(plainText, encrypted);
                    if (bruteForceKeys[0] == -1 || bruteForceKeys[1] == -1) {
                        System.out.println("key cannot be found");
                    } else {
                        System.out.println("Key to decrypt ---> " + bruteForceKeys[0] + " " + bruteForceKeys[1]);
                    }
                    break;
                case 4:
                    break;
                default:
                    break;
            }

        } while (n != 4);
    }

    public static String encrypt(String text, int k1, int k2) {
        StringBuffer result1 = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result1.append(text.charAt(i));
            } else {
                char ch = (char) ((((((int) text.charAt(i) - 97) * k1) % 26) + k2) % 26 + 65);
                result1.append(ch);
            }
        }
        return result1.toString();
    }

    static int modInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;
        if (m == 1)
            return 0;
        while (a > 1) {
            // q is quotient
            if (m == 0) {
                return -1;
            }
            int q = a / m;
            int t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0)
            x += m0;
        return x;
    }

    public static String decrypt(String text, int k1, int k2) {
        StringBuffer result = new StringBuffer();
        int s_inv = modInverse(k1, 26);
        if (s_inv == -1) {
            System.out.println("Key is incorrect");
            return text;
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result.append(text.charAt(i));
            } else {
                char ch = (char) (((Math.floorMod((((int) text.charAt(i) - 65) - k2), 26) * s_inv) % 26) + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static int[] bruteForce(String plainText, String encrypted) {
        int[] res = new int[]{-1, -1};
        int flag = 0;
        for (int k1 = 0; k1 < 26; k1++) {
            if (modInverse(k1, 26) != -1) {
                for (int k2 = 0; k2 < 26; k2++) {
                    if (plainText.equals(decrypt(encrypted, k1, k2))) {
                        res = new int[]{k1, k2};
                        flag = 1;
                        break;
                    }
                }
            }
            if (flag == 1) break;
            else continue;
        }
        return res;
    }
}