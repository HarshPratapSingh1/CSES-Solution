import java.util.*;
import java.io.*;

public class MaximumSubarraySum2 {
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

    static void solve() throws IOException {
        int n = scan.nextInt();
        int a = scan.nextInt();
        int b = scan.nextInt();

        long arr[] = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = scan.nextLong();
        long prefix[] = new long[n + 1];

        for (int i = 1; i <= n; i++)
            prefix[i] = prefix[i - 1] + arr[i - 1];

        // System.out.println(Arrays.toString(prefix));

        TreeSet<Integer> set = new TreeSet<>((i1, i2) -> {
            if (prefix[i1] != prefix[i2]) {
                return Long.compare(prefix[i1], prefix[i2]);
            }
            return Integer.compare(i1, i2);
        });

        long len = b - a + 1, ans = Long.MIN_VALUE;

        int i = 0;
        while (i < len) {
            set.add(i);

            long start = prefix[i + a];
            int idx = set.first();
            // String st[] = s.split(" ");
            long end = prefix[idx];

            ans = Math.max(ans, start - end);
            i++;
        }

        int j = i + a - 1;
        while (j <= n) {
            long start = prefix[j];
            int idx = set.first();

            long end = prefix[idx];

            ans = Math.max(ans, start - end);

            set.add(i);
            set.remove((int) (i - len));
            i++;
            j++;
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