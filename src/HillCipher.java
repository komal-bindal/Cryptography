import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HillCipher {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ins);
        int n;
        do {
            System.out.println("-----HILLCIPHER MENU-----");
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
            int b = 1;
            String key;
            String column;
            switch (n) {
                case 1:
                    b = 1;
                    do {
                        System.out.println("Please enter the number of columns");
                        column = br.readLine();
                        for (int i = 0; i < column.length(); i++) {
                            if ((column.charAt(i) >= '0' && column.charAt(i) <= '9')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    int c = Integer.parseInt(column);
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
                        System.out.println("Please enter your key with size " + c * c);
                        key = br.readLine();
                        for (int i = 0; i < key.length(); i++) {
                            if (key.length() != c * c) {
                                b = 0;
                                break;
                            } else if ((key.charAt(i) >= 'a' && key.charAt(i) <= 'z')) {
                                b = 1;
                            } else {
                                b = 0;
                                break;
                            }
                        }
                    } while (b == 0);
                    String cipherText = encryption(str, key, c);
                    System.out.println("EncryptedText ---> " + cipherText);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (n != 3);
    }

    private static String encryption(String str, String key, int c) {
        char[][] plainTextArr = matrixFormation(str, c);
        char[][] keyArr = matrixFormation(key, c);
        char[][] resArr = matrixMultiplication(plainTextArr, keyArr);
        StringBuilder arrStr = new StringBuilder();
        for (int i = 0; i < resArr.length; i++) {
            for (int i1 = 0; i1 < resArr[0].length; i1++) {
                System.out.print(resArr[i][i1]);
                arrStr.append(resArr[i][i1]);
            }
            System.out.println();
        }
        StringBuilder res = new StringBuilder();
        int k =0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' '){
                res.append(' ');
            }else{
                res.append(arrStr.charAt(k));
                k++;
            }
        }
        return res.toString();
    }

    private static char[][] matrixMultiplication(char[][] plainTextArr, char[][] keyArr) {
        int rows = plainTextArr.length;
        int columns = plainTextArr[0].length;
        char[][] res = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int sum = 0;
                for (int k = 0; k < columns; k++) {
                    sum += Math.floorMod(((int) plainTextArr[i][k] - 97) * ((int) keyArr[k][j] - 97), 26);
                }
                res[i][j] = (char) (sum+97);
            }
        }
        return res;
    }

    public static char[][] matrixFormation(String str, int c) {
        int size = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != ' '){
                size++;
            }
        }
        int r = (int) Math.ceil((double) size / (double) c);
        int k = 0;
        char[][] arr = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                while (k < str.length() && str.charAt(k) == ' ') {
                    k++;
                }
                if (k >= str.length()) {
                    arr[i][j] = 'z';
                } else {
                    arr[i][j] = str.charAt(k);
                    k++;
                }
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        return arr;
    }
}
