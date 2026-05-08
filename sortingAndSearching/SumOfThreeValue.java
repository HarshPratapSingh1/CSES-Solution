import java.util.*;
import java.io.*;

public class SumOfThreeValue {
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
        long x = scan.nextLong();

        long arr[][] = new long[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = scan.nextLong();
            arr[i][1] = i + 1;
        }

        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

        ArrayList<Long> l = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            long needed = x - arr[i][0];
            int j = i + 1, k = n - 1;

            while (j < k) {
                long sum = arr[j][0] + arr[k][0];
                if (sum == needed) {
                    l.add(arr[i][1]);
                    l.add(arr[j][1]);
                    l.add(arr[k][1]);
                    flag = true;
                    break;
                } else if (sum > needed)
                    k--;
                else
                    j++;
            }
            if (flag)
                break;
        }
        if (!flag)
            System.out.println("IMPOSSIBLE");
        else {
            Collections.sort(l);
            for (long it : l)
                System.out.print(it + " ");
        }
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