import java.util.*;
import java.io.*;

public class Towers {
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
        long arr[] = new long[n];

        for (int i = 0; i < n; i++)
            arr[i] = scan.nextLong();

        ArrayList<Long> l = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (l.isEmpty() || l.get(l.size() - 1) <= arr[i])
                l.add(arr[i]);
            else {
                int idx = bs(l, arr[i]);
                if (idx > -1) {
                    l.set(idx, arr[i]);
                }
            }
        }
        scan.pn(l.size());
    }

    public static int bs(ArrayList<Long> li, long val) {
        int l = 0, r = li.size() - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(mid, li, val) == 1) {
                ans = mid;
                r = mid - 1;
            } else
                l = mid + 1;
        }
        return ans;
    }

    public static int check(int mid, ArrayList<Long> l, long val) {
        if (l.get(mid) > val)
            return 1;
        return 0;
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