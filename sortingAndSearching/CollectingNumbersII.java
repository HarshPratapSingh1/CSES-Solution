import java.util.*;
import java.io.*;

public class CollectingNumbersII {
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
        int m = scan.nextInt();

        long arr[] = new long[n];
        long location[] = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextLong();
            location[(int) arr[i] - 1] = i;
        }

        long ans = 0;

        for (int i = 0; i < n - 1; i++)
            if (location[i] > location[i + 1])
                ans++;

        while (m-- > 0) {
            int l = scan.nextInt() - 1;
            int r = scan.nextInt() - 1;
            long ele1 = arr[l] - 1;
            long ele2 = arr[r] - 1;
            if (ele1 == ele2) {
                scan.pn(ans + 1);
                continue;
            }
            long iniVal = change(location, (int) ele1, (int) ele2);
            swap(location, (int) ele1, (int) ele2);
            swap(arr, (int) l, (int) r);

            long finalVal = change(location, (int) ele1, (int) ele2);
            long ch = finalVal - iniVal;

            ans += ch;

            scan.pn(ans + 1);

        }
    }

    public static long change(long arr[], int idx, int jdx) {
        long ch = 0;
        int min = Math.min(idx, jdx);
        int max = Math.max(jdx, idx);

        if (min + 1 == max) {
            if (min > 0 && arr[min] < arr[min - 1])
                ch++;
            if (max < arr.length - 1 && arr[max] > arr[max + 1])
                ch++;
            if (arr[min] > arr[max])
                ch++;
        } else {
            if (min > 0 && arr[min] < arr[min - 1])
                ch++;
            if (min < arr.length - 1 && arr[min] > arr[min + 1])
                ch++;
            if (max > 0 && arr[max] < arr[max - 1])
                ch++;
            if (max < arr.length - 1 && arr[max] > arr[max + 1])
                ch++;

        }
        return ch;
    }

    public static void swap(long arr[], int i, int j) {
        arr[i] = arr[i] ^ arr[j] ^ (arr[j] = arr[i]);
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