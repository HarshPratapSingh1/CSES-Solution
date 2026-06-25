import java.util.*;
import java.io.*;

public class rectangleCutting {
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

    static long memo[][];

    static void solve() throws IOException {
        int a = scan.nextInt(), b = scan.nextInt();

        memo = new long[a + 1][b + 1];

        for (int w = 1; w <= a; w++) {
            for (int h = 1; h <= b; h++) {
                if (w == h)
                    memo[w][h] = 0;
                else {
                    long ans = Long.MAX_VALUE;
                    for (int mid = 1; mid < w; mid++) {
                        long val1 = memo[w - mid][h];
                        long val2 = memo[mid][h];

                        if (val1 != Long.MAX_VALUE && val2 != Long.MAX_VALUE) {
                            ans = Math.min(ans, val1 + val2 + 1);
                        }

                    }
                    for (int mid = 1; mid < h; mid++) {
                        long val1 = memo[w][h - mid];
                        long val2 = memo[w][mid];

                        if (val1 != Long.MAX_VALUE && val2 != Long.MAX_VALUE) {
                            ans = Math.min(ans, val1 + val2 + 1);
                        }
                    }

                    memo[w][h] = ans;
                }
            }
        }

        System.out.println(memo[a][b]);
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