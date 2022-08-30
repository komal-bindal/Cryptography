import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class AutoKeyCipher {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        int n;
        do {
            System.out.println("-----MENU FOR AUTOKEY CIPHER-----");
            System.out.println("1. Encryption");
            System.out.println("2. Decryption");
            System.out.println("3. Exit");
            System.out.println("Waiting for input.....");
            do {
                System.out.println("Please enter the correct no. (1-3)");
                String str = br.readLine();
                if (str.equals("1") || str.equals("2") || str.equals("3")) {
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
                    } while (b == 0);
                    int k = Integer.parseInt(key);
                    String cipherText = encrypt(str, k % 26);
                    System.out.println("EncryptedText ---> " + cipherText);
                    break;
                case 2:

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
                    } while (b == 0);
                    int k1 = Integer.parseInt(key);

                    String decryptedText = decrypt(str, k1 % 26);
                    System.out.println("DecryptedText ----> " + decryptedText);
                    break;
                case 3:
                    break;

                default:
                    break;
            }

        } while (n != 4);
    }

    public static String encrypt(String text, int k) {
        StringBuffer result1 = new StringBuffer();
        if (text.length() == 0) {
            return result1.toString();
        }
        if (Character.isUpperCase(text.charAt(0))) {
            char ch = (char) ((((int) text.charAt(0) - 65) + k) % 26 + 65);
            result1.append(ch);
        } else {
            char ch = (char) ((((int) text.charAt(0) - 97) + k) % 26 + 97);
            result1.append(ch);
        }
        for (int i = 1; i < text.length(); i++) {
            int pos = i;
            do {
                pos--;
            } while (pos >= 0 && text.charAt(pos) == ' ');
            if (Character.isUpperCase(text.charAt(pos))) {
                k = ((int) text.charAt(pos) - 65);
            } else {
                k = ((int) text.charAt(pos) - 97);
            }

            if (text.charAt(i) == ' ') {
                result1.append(text.charAt(i));
            } else if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) ((((int) text.charAt(i) - 65) + k) % 26 + 65);
//                System.out.println(k + " " + ch);
                result1.append(ch);
            } else {
                char ch = (char) ((((int) text.charAt(i) - 97) + k) % 26 + 97);
//                System.out.println(k + " " + ch);
                result1.append(ch);
            }
        }
        return result1.toString();
    }


    public static String decrypt(String text, int k) {
        StringBuffer result = new StringBuffer();
        if (text.length() == 0) {
            return result.toString();
        }
        if (Character.isUpperCase(text.charAt(0))) {
            int num = (((int) text.charAt(0) - 65) - k) % 26;
            char ch = (char) (num < 0 ? 26 + num + 65 : num + 65);
            result.append(ch);
        } else {
            int num = (((int) text.charAt(0) - 97) - k) % 26;
            char ch = (char) (num < 0 ? 26 + num + 97 : num + 97);
            result.append(ch);
        }
        for (int i = 1; i < text.length(); i++) {
            int pos = i;
            do {
                pos--;
            } while (pos >= 0 && result.charAt(pos) == ' ');
            if (Character.isUpperCase(text.charAt(pos))) {
                k = ((int) result.charAt(pos) - 65);
            } else {
                k = ((int) result.charAt(pos) - 97);
            }

            if (text.charAt(i) == ' ') {
                result.append(text.charAt(i));
            } else if (Character.isUpperCase(text.charAt(i))) {
                int num = (((int) text.charAt(i) - 65) - k) % 26;
                char ch = (char) (num < 0 ? 26 + num + 65 : num + 65);
//                System.out.println(k + " " + num + " " + ch);

                result.append(ch);
            } else {
                int num = (((int) text.charAt(i) - 97) - k) % 26;
                char ch = (char) (num < 0 ? 26 + num + 97 : num + 97);
//                System.out.println(k + " " + num + " " + ch);

                result.append(ch);
            }
        }
        return result.toString();
    }


}