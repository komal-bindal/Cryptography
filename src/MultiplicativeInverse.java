import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MultiplicativeInverse {
    public static void main(String[] args) throws IOException {
        InputStreamReader ins = new InputStreamReader(System.in);
        BufferedReader br  = new BufferedReader(ins);
        System.out.println("Please enter the number");
        int n = Integer.parseInt(br.readLine());
        System.out.println("Please enter m");
        int m = Integer.parseInt(br.readLine());
        int inv = modInverse(n, m);
        System.out.println(inv);
    }


    static int modInverse(int r2, int r1) {
        int m0 = r1;
        int t1 = 0, t2 = 1;
        if (r1 == 1)
            return 0;
        while (r2 > 1) {

            int q = r1 / r2;
            int r = r1 - q*r2;
            int t = t1-q*t2;
            System.out.println("q " + q + " r1 " + r1 + " r2 " + r2 + " r " + r + " t1 " + t1 + " t2 " + t2 + " t " + t);
            r1 = r2;
            r2=r;
            t1=t2;
            t2=t;
            System.out.println("q " + q + " r1 " + r1 + " r2 " + r2 + " r " + r + " t1 " + t1 + " t2 " + t2 + " t " + t);
            if (r2 == 0) {
                return -1;
            }
        }
        if (t2 < 0)
            t2 += m0;
        return t2;
    }


}
