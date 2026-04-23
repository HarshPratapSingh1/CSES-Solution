import java.util.*;
import java.io.*;

public class JosephusProblemII {
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

    static void solve() throws IOException {
        int n = scan.nextInt();

        long x = scan.nextLong();

        int lIdx[] = new int[n];
        int rIdx[] = new int[n];

        for (int i = 0; i < n; i++) {
            lIdx[i] = (i + 1) % n;
            rIdx[i] = i - 1;
        }
        rIdx[0] = n - 1;

        // x %= n;

        int i = (int) x;
        int cnt = 0;
        while (true) {
            int prev = rIdx[i];
            int front = lIdx[i];

            System.out.print((i + 1) + " ");
            if (prev == front && front == i)
                break;
            rIdx[front] = prev;
            lIdx[prev] = front;
            cnt++;
            if (cnt == n)
                break;

            long dif = x % (n - cnt);
            while (dif-- >= 0) {
                i = front;
                front = lIdx[i];
            }
        }
        System.out.println();
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