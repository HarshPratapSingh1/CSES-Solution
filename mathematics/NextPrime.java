import java.util.*;
import java.io.*;

public class NextPrime {
    static FastIO scan;
    static final int MOD = 1_000_000_007;
    static final long LINF = (long) 1e18;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        // System.setOut(new PrintStream("output.txt"));
        scan = new FastIO();
        int t = 1;
        t = scan.nextInt();
        while (t-- > 0) {
            solve();
        }
        scan.close();
    }

    static void solve() throws IOException {
        long n = scan.nextLong();
        for (long i = n + 1; i <= n + 540; i++) {
            if (isPrime(i)) {
                scan.pn(i);
                return;
            }
        }
    }

    public static boolean isPrime(long n) {
        if (n < 2)
            return false;
        if (n == 2 || n == 3)
            return true;
        if (n % 2 == 0)
            return false;

        long m = n - 1;
        long cnt = 0;
        while (m % 2 == 0) {
            m /= 2;
            cnt++;
        }

        int[] bases = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };

        for (int a : bases) {
            if (n <= a)
                break;

            long last = pow(a, m, n);

            if (last == 1 || last == n - 1)
                continue;

            boolean isComposite = true;
            for (int i = 0; i < cnt - 1; i++) {
                last = safeMul(last, last, n);

                if (last == 1)
                    return false;
                if (last == n - 1) {
                    isComposite = false;
                    break;
                }
            }
            if (isComposite)
                return false;
        }
        return true;
    }

    public static long safeMul(long a, long b, long mod) {
        long res = 0;
        a %= mod;
        while (b > 0) {
            if ((b & 1) != 0) {
                res = (res + a) % mod;
            }
            a = (a * 2) % mod;
            b >>= 1;
        }
        return res;
    }

    public static long pow(long a, long b, long mod) {
        if (b == 0)
            return 1;

        long val = pow(a, b / 2, mod) % mod;
        val = safeMul(val, val, mod);

        if (b % 2 != 0)
            val = a * val % mod;

        return val;
    }

    // ---------------------- FAST I/O ----------------------
    static class FastIO {
        private final InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final PrintWriter out = new PrintWriter(System.out);

        private int readByte() throws IOException {
            if (ptr >= len) {
                ptr = 0;
                len = in.read(buffer);
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        public String next() throws IOException {
            int c = readByte();
            while (c >= 0 && c <= 32)
                c = readByte();
            if (c == -1)
                return null;
            StringBuilder sb = new StringBuilder();
            while (c > 32) {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            int c = readByte();
            while (c >= 0 && c <= 32)
                c = readByte();
            if (c == -1)
                return 0;
            int s = 1;
            if (c == '-') {
                s = -1;
                c = readByte();
            }
            int v = 0;
            while (c > 32) {
                v = v * 10 + (c - '0');
                c = readByte();
            }
            return v * s;
        }

        public long nextLong() throws IOException {
            int c = readByte();
            while (c >= 0 && c <= 32)
                c = readByte();
            if (c == -1)
                return 0;
            int s = 1;
            if (c == '-') {
                s = -1;
                c = readByte();
            }
            long v = 0;
            while (c > 32) {
                v = v * 10 + (c - '0');
                c = readByte();
            }
            return v * s;
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public void pn(Object o) {
            out.println(o);
        }

        public void p(Object o) {
            out.print(o);
        }

        public void close() {
            out.close();
        }
    }
}