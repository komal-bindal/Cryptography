import java.util.Scanner;

public class MillerRabin {
    public static void main(String[] args) {
        System.out.println("Please enter the number n");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println("Base value is 2 by default");
        if(isPrime(n)){
            System.out.println("Prime");
        }else{
            System.out.println("Composite");
        }
    }

    static int power(int x, int y, int p) {
        int res = 1;
        x = x % p;
        while (y > 0) {
            if ((y & 1) == 1)
                res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    static boolean miillerTest(int q, int n, int k) {
        int a = 2;
        int x = power(a, q, n);

        if (x == 1 || x == n - 1)
            return true;
        for (int i = 1; i <= k - 1; i++) {
            x = (x * x) % n;
            if (x == n - 1) {
                return true;
            }
        }
        return false;
    }

    static boolean isPrime(int n) {
        if (n <= 1 || n == 4)
            return false;
        if (n <= 3)
            return true;

        int d = n - 1;

        while (d % 2 == 0)
            d /= 2;
        int k = (int) (Math.log((n - 1) / d) / Math.log(2));
        System.out.println(n + " " + k + " " + d);
        if (!miillerTest(d, n, k))
            return false;

        return true;
    }
}
