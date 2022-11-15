import java.util.ArrayList;
import java.util.Scanner;

public class PrimitiveRoots {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number");
        int n = scanner.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> valA = new ArrayList<>();
        int v = phi(n);
        System.out.println(v);
        for (int a = 1; a <= v; a++) {
            for (int i = 1; i <= v; i++) {
                int x = Math.floorMod((int) Math.pow(a, i), n);
                System.out.print("O(" + a + ") = " + x + "    ");
                if (x == 1) {
                    if (i == v) {
                        arr.add(a);
                    }
                    break;
                }
            }
            System.out.println();
        }
        System.out.println(arr);
    }

    static int phi(int n) {
        int result = n;
        for (int p = 2; p * p <= n; ++p) {

            if (n % p == 0) {
                while (n % p == 0)
                    n /= p;
                result -= result / p;
            }
        }

        if (n > 1)
            result -= result / n;
        return result;
    }
}
