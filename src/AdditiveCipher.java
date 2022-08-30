import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AdditiveCipher {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        int n;
        do {
            System.out.println("-----MENU-----");
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
                    } while (b == 0);
                    int k = Integer.parseInt(key);
                    String cipherText = encryption(str, k % 26);
                    System.out.println("EncryptedText ---> " + cipherText);
                    break;
                case 2:
                    b = 1;
                    do {
                        System.out.println("Please enter your plain message (A-Z)");
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
                    } while (b == 0);
                    int k1 = Integer.parseInt(key);

                    String decryptedText = decryption(str, k1 % 26);
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
                        System.out.println("Please enter your plain message (A-Z)");
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

    public static String encryption(String str, int k) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                cipherText.append(' ');
            } else {
                char a = (char) ((char) (str.charAt(i) - 97 + k) % 26);
                cipherText.append((char) (a + 65));
            }
        }
        return cipherText.toString();
    }

    public static String decryption(String str, int k) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                text.append(' ');
            } else {
                int c = Math.floorMod((str.charAt(i) - 65 - k), 26);
                text.append((char) (c + 97));
            }
        }
        return text.toString();
    }

    public static int bruteForce(String plainText, String encrypted) {
        for (int k = 0; k < 26; k++) {
            String text = decryption(encrypted, k);

            if (text.equals(plainText)) {
                return k;
            }
        }
        return -1;
    }
}