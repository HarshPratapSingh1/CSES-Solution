import java.util.*;
import java.io.*;

public class SumOfFourValues {
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

        ArrayList<Long> ans = new ArrayList<>();
        Arrays.sort(arr, (a, b) -> Long.compare(a[0], b[0]));

        for (int l = n - 1; l >= 3; l--) {
            long val1 = arr[l][0];
            for (int k = l - 1; k >= 2; k--) {
                long val2 = arr[k][0];
                long needed = x - (val1 + val2);
                int i = 0, j = k - 1;
                while (i < j) {
                    long sum = arr[i][0] + arr[j][0];
                    if (sum == needed) {
                        ans.add(arr[i][1]);
                        ans.add(arr[j][1]);
                        ans.add(arr[k][1]);
                        ans.add(arr[l][1]);
                        break;
                    } else if (sum < needed)
                        i++;
                    else
                        j--;
                }
                if (ans.size() > 0)
                    break;
            }
            if (ans.size() > 0)
                break;
        }
        if (ans.size() == 0)
            System.out.println("IMPOSSIBLE");
        else {
            Collections.sort(ans);
            for (long it : ans)
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