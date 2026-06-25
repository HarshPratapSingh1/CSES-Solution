import java.util.*;
import java.io.*;

public class LongestCommonSubsequence {
    static FastIO scan;
    static final int MOD = 1_000_000_007;
    static final long LINF = (long) 1e18;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        System.setOut(new PrintStream("output.txt"));
        scan = new FastIO();
        int t = 1;
        // t = scan.nextInt();
        while (t-- > 0) {
            solve();
        }
        scan.close();
    }

    static int n, m;
    static long arr[];
    static long brr[];
    static long memo[][];
    static ArrayList<Long> res;

    static void solve() throws IOException {
        n = scan.nextInt();
        m = scan.nextInt();

        arr = new long[n];
        brr = new long[m];

        for (int i = 0; i < n; i++)
            arr[i] = scan.nextLong();
        for (int i = 0; i < m; i++)
            brr[i] = scan.nextLong();

        memo = new long[n + 1][m + 1];
        for (long it[] : memo)
            Arrays.fill(it, -1);

        res = new ArrayList<>();

        long val = dp(0, 0);
        printer(0, 0);
        System.out.println(val);
        for (long it : res)
            System.out.print(it + " ");
        System.out.println();
    }

    public static void printer(int i, int j) {
        if (i == n || j == m)
            return;

        if (arr[i] == brr[j]) {
            res.add(arr[i]);
            printer(i + 1, j + 1);
        } else if (memo[i][j + 1] >= memo[i + 1][j]) {
            printer(i, j + 1);
        } else
            printer(i + 1, j);
    }

    public static long dp(int i, int j) {
        // pruning

        // base case
        if (i == n || j == m)
            return 0;

        // cache check
        if (memo[i][j] != -1)
            return memo[i][j];

        // compute
        long ans = 0;
        if (arr[i] == brr[j]) {
            ans = dp(i + 1, j + 1) + 1;
        } else {
            ans = Math.max(dp(i + 1, j), dp(i, j + 1));
        }
        // save and return
        return memo[i][j] = ans;
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