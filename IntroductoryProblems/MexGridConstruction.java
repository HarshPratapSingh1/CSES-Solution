import java.util.*;
import java.io.*;

public class MexGridConstruction {
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
        int arr[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            arr[0][i] = i;
            arr[i][0] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int nums[] = new int[j + i];

                int idx = 0;
                for (int k = 0; k < j; k++)
                    nums[idx++] = arr[i][k];

                for (int k = 0; k < i; k++)
                    nums[idx++] = arr[k][j];
                Arrays.sort(nums);
                int val = calculateMex(nums);
                arr[i][j] = val;
            }
        }
        for (int it[] : arr) {
            for (int i : it)
                scan.p(i + " ");
            scan.pn(" ");
        }
    }

    public static int calculateMex(int arr[]) {
        int mex = 0;
        for (int idx = 0; idx < arr.length; idx++) {
            if (arr[idx] == mex) {
                mex += 1;
            }
        }

        return mex;
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