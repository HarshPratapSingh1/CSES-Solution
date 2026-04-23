import java.util.*;
import java.io.*;

public class NestedRangesCheck {
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
        long arr[][] = new long[n][3];

        for (int i = 0; i < n; i++) {
            arr[i][0] = scan.nextLong();
            arr[i][1] = scan.nextLong();
            arr[i][2] = i;
        }

        Arrays.sort(arr, (x, y) -> {
            if (x[0] != y[0]) {
                return Long.compare(x[0], y[0]); // Start ascending
            } else {
                return Long.compare(y[1], x[1]); // End descending
            }
        });
        long lastMax = 0;
        int contains[] = new int[n];
        int contained[] = new int[n];

        for (int i = 0; i < n; i++) {
            long idx = arr[i][2];

            if (arr[i][1] <= lastMax)
                contained[(int) idx] = 1;
            else
                contained[(int) idx] = 0;
            lastMax = Math.max(lastMax, arr[i][1]);
        }
        long lastMin = Long.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            long idx = arr[i][2];

            if (arr[i][1] >= lastMin)
                contains[(int) idx] = 1;
            else
                contains[(int) idx] = 0;
            lastMin = Math.min(lastMin, arr[i][1]);
        }
        for (int it : contains)
            scan.p(it + " ");
        scan.pn(" ");
        for (int it : contained)
            scan.p(it + " ");
        scan.pn(" ");
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