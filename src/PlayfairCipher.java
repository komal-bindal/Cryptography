import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class PlayfairCipher {
    public static void main(String[] args) throws IOException {

        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        int n;
        do {
            System.out.println("-----MENU FOR PLAYFAIR CIPHER-----");
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
            Character[][] keyMatrix = new Character[5][5];
            String cipherText;
            String decryptedText;
            HashMap<Character, int[]> map = new HashMap<>();
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
                        System.out.println("Please enter your key (a-z)");
                        key = br.readLine();
                        for (int i = 0; i < key.length(); i++) {
                            if ((key.charAt(i) >= 'a' && key.charAt(i) <= 'z')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    keyMatrix = generateKey(key);
                    for (int i = 0; i < keyMatrix.length; i++) {
                        for (int j = 0; j < keyMatrix[i].length; j++) {
                            System.out.print(keyMatrix[i][j]);
                        }
                        System.out.println();
                    }
                    map = createHashMap(keyMatrix);
                    cipherText = encryption(str, keyMatrix, map);
                    System.out.println(cipherText);
                    break;
                case 2:
                    b = 1;
                    do {
                        System.out.println("Please enter your encrypted message (A-Z)");
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
                        System.out.println("Please enter your key (a-z)");
                        key = br.readLine();
                        for (int i = 0; i < key.length(); i++) {
                            if ((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
//                    keyMatrix = generateKey(key);
//                    decryptedText = decryption(str, keyMatrix);
//                    System.out.println(decryptedText);
                    break;
                case 3:
                    break;
                default:
                    break;
            }

        } while (n != 4);

    }

    private static HashMap<Character, int[]> createHashMap(Character[][] keyMatrix) {
        HashMap<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < keyMatrix.length; i++) {
            for (int j = 0; j < keyMatrix[i].length; j++) {
                map.put(keyMatrix[i][j], new int[]{i, j});
            }
        }
        return map;
    }

    private static String encryption(String str, Character[][] keyMatrix, HashMap<Character, int[]> map) {
        StringBuilder res = new StringBuilder();
        str = str.toUpperCase();
        StringBuilder plainText = new StringBuilder(str);
        int size = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                size++;
            }
        }
        if (size % 2 == 1) {
            plainText.append("X");
        }
        for (int i = 0; i < plainText.length(); i++) {
            while (plainText.charAt(i) == ' ') {
                res.append(plainText.charAt(i));
                i++;
//                System.out.println(plainText.charAt(i-1) + " append" );
            }
            Character first = plainText.charAt(i);
            i++;
            while (plainText.charAt(i) == ' ') {
                res.append(plainText.charAt(i));
                i++;
//                System.out.println(plainText.charAt(i-1) + " append2" );

            }
            Character second = plainText.charAt(i);
            int[] arr = map.get(first);
            int r1 = arr[0], c1 = arr[1];
            arr = map.get(second);
            int r2 = arr[0], c2 = arr[1];
            if (r1 == r2) {
                res.append(keyMatrix[r1][(c1 + 1) % 5]);
                res.append(keyMatrix[r1][(c2 + 1) % 5]);
            } else if (c1 == c2) {
                res.append(keyMatrix[(r1 + 1) % 5][c1]);
                res.append(keyMatrix[(r2 + 1) % 5][c2]);
            } else {
                res.append(keyMatrix[r1][c2]);
                res.append(keyMatrix[r2][c1]);

            }
//            System.out.println(first + " " + second);

        }
        return res.toString().toUpperCase();
    }

    private static Character[][] generateKey(String key) {
        Character[][] matrix = new Character[5][5];
        int p = 0;
        int r = 0, c = 0;
        key = key.toUpperCase();
        HashSet<Character> s = new HashSet<>();
        String alphabets = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < key.length(); i++) {
            if (s.contains(key.charAt(i)) || key.charAt(i) == 'J') {
                continue;
            } else {
                s.add(key.charAt(i));
                r = r % 5;
                c = c % 5;
                matrix[r][c] = key.charAt(i);
                c++;
            }
            if (c == 5) {
                r++;
            }
        }
        for (int i = 0; i < alphabets.length(); i++) {
            if (s.contains(alphabets.charAt(i))) {
                continue;
            } else {
                s.add(alphabets.charAt(i));
                r = r % 5;
                c = c % 5;
                matrix[r][c] = alphabets.charAt(i);
                c++;
            }
            if (c == 5) {
                r++;
            }
        }
        return matrix;
    }
}
