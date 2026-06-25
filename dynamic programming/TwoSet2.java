import java.util.*;
import java.io.*;

public class TwoSet2 {
    static FastIO scan;
    static final int MOD = 1_000_000_007;
    static final long LINF = (long) 1e18;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        // System.setOut(new PrintStream("output.txt"));
        scan = new FastIO();
        int t = 1;
        // t = scan.nextInt();
        while (t-- > 0) {
            solve();
        }
        scan.close();
    }

    static int n;
    static long memo[][];

    static void solve() throws IOException {
        n = scan.nextInt();
        long sumtoGet = n * (n + 1) / 2;
        if (sumtoGet % 2 != 0) {
            System.out.println(0);
            return;
        }

        sumtoGet /= 2;

        memo = new long[n + 1][(int) sumtoGet + 1];

        for (int idx = 0; idx <= n; idx++) {
            for (long sum = 0; sum <= sumtoGet; sum++) {
                if (idx == 0) {
                    if (sum == 0)
                        memo[idx][(int) sum] = 1;
                    else
                        memo[idx][(int) sum] = 0;
                } else {
                    long pick = 0;
                    if (sum - idx >= 0) {
                        pick = memo[idx - 1][(int) sum - idx] % MOD;
                    }
                    long not = memo[idx - 1][(int) sum] % MOD;

                    // save and return
                    memo[idx][(int) sum] = (pick + not) % MOD;
                }
            }
        }

        long val = memo[n - 1][(int) sumtoGet];

        System.out.println(val);
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