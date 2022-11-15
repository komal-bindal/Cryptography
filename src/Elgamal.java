import java.util.Scanner;

public class Elgamal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of q");
        long q = sc.nextLong();
        System.out.println("Enter the value of alpha");
        long alpha = sc.nextLong();
        System.out.println("Enter the value of m");
        long m = sc.nextLong();
        long k = 1;
        SignAlgo sign = new SignAlgo(q, alpha, m, k);
        Verify v = new Verify(sign.q, sign.alpha, sign.publicKey, sign.m, sign.s1, sign.s2);
        v.verified();
    }
}


class SignAlgo {

    public long q, alpha, publicKey, m, s1, s2, k;

    private long privateKey = 16;

    SignAlgo(long a, long b, long c, long d) {
        q = a;
        alpha = b;
        publicKey = ((long) Math.pow(alpha, privateKey)) % q;
        m = c;
        k = d;
        s1 = createS1(alpha, k);
        s2 = createS2();
    }


    long invK() {
        for (int x = 1; x < q - 1; x++)
            if ((k * x) % (q - 1) == 1)
                return x;
        return 1;
    }

    long createS1(long b, long c) {
        long a = ((long) Math.pow(b, c));
        if (a < (long) Math.pow(2, 36) - 1)
            return a % q;
        else//(a==(long)Math.pow(2, 36)-1)
            return (createS1(b, c / 2) * createS1(b, c - c / 2)) % q;
    }

    long createS2() {
        long a = (invK() * (m - privateKey * s1)) % (q - 1);
        if (a >= 0)
            return a;
        else
            return (a + q - 1);
    }


}


class Verify {

    public long q, alpha, publicKey, m, s1, s2;

    Verify(long a, long b, long c, long d, long e, long f) {
        q = a;
        alpha = b;
        publicKey = c;
        m = d;
        s1 = e;
        s2 = f;
    }


    long v1(long b, long c, long d, long e) {
        long a = (((long) Math.pow(b, c)) * ((long) (Math.pow(d, e))));
        if (a < (long) Math.pow(2, 36) - 1)
            return a % q;
        else//(a==(long)Math.pow(2, 36)-1)
            return (v1(b, c / 2, d, e / 2) * v1(b, c - c / 2, d, e - e / 2)) % q;
    }


    long v2(long b, long c) {
        long a = ((long) Math.pow(b, c));
        if (a < (long) Math.pow(2, 36) - 1)
            return a % q;
        else//(a==(long)Math.pow(2, 36)-1)
            return (v2(b, c / 2) * v2(b, c - c / 2)) % q;
    }

    void verified() {
        System.out.println("Value of s1 and s2 are = "+ s1 + " "+ s2);
        if (v1(publicKey, s1, s1, s2) == v2(alpha, m)) {
            System.out.println("Signature verified using Elgamal.");
            System.out.println("The value of v1 mod p: " + v1(publicKey, s1, s1, s2));
            System.out.println("The value of v2 mod p: " + v2(alpha, m));
        } else {
            System.out.println("Signature missmatch");
            System.out.println("The value of v1 mod p: " + v1(publicKey, s1, s1, s2));
            System.out.println("The value of v2 mod p: " + v2(alpha, m));
        }
    }
}