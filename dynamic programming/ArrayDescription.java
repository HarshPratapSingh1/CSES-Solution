import java.util.*;
import java.io.*;

public class ArrayDescription {
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

    static long arr[];
    static int n, m;
    static long memo[][];

    static void solve() throws IOException {
        n = scan.nextInt();
        m = scan.nextInt();

        arr = new long[n];
        memo = new long[n][m + 2];

        for (int i = 0; i < n; i++)
            arr[i] = scan.nextLong();

        if (arr[0] == 0) {
            for (int i = 1; i <= m; i++) {
                memo[0][i] = 1;
            }
        } else {
            memo[0][(int) arr[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++) {
            if (arr[idx] == 0) {
                for (int last = 1; last <= m; last++) {
                    memo[idx][last] = (memo[idx - 1][last - 1] + memo[idx - 1][last] + memo[idx - 1][last + 1]) % MOD;
                }
            } else {
                memo[idx][(int) arr[idx]] = (memo[idx - 1][(int) arr[idx] - 1] + memo[idx - 1][(int) arr[idx]]
                        + memo[idx - 1][(int) arr[idx] + 1]) % MOD;
            }
        }
        long ans = 0;
        for (int i = 0; i <= m; i++) {
            ans = (ans + memo[n - 1][i]) % MOD;
        }
        System.out.println(ans);
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