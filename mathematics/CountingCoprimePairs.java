import java.util.*;
import java.io.*;

public class CountingCoprimePairs {
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

    static int freq[];
    static int cnt[];
    static long arr[];
    static long val;

    static void solve() throws IOException {
        int n = scan.nextInt();
        arr = new long[n];
        freq = new int[(int) 1e6 + 1];
        cnt = new int[(int) 1e6 + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextLong();
            freq[(int) arr[i]]++;
        }
        precompute();

        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long it = arr[i];
            ArrayList<Integer> factors = new ArrayList<>(getFact(it));
            val = 0;
            rec(0, 0, factors, 1);

            // System.out.println(val);
            if (it == 1)
                ans += n - val - 1;
            else
                ans += (n - val);
        }
        System.out.println(ans / 2);
    }

    public static void rec(int idx, int ct, ArrayList<Integer> arr, long prod) {
        if (idx == arr.size()) {
            if (ct == 0)
                return;
            if (ct % 2 != 0) {
                val += cnt[(int) prod];
            } else
                val -= cnt[(int) prod];
            return;
        }

        // dont pick
        rec(idx + 1, ct, arr, prod);
        rec(idx + 1, ct + 1, arr, prod * arr.get(idx));
    }

    public static HashSet<Integer> getFact(long n) {
        HashSet<Integer> ans = new HashSet<>();
        // ans.add(1);
        while (n % 2 == 0) {
            ans.add(2);
            n /= 2;
        }

        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                ans.add(i);
                n /= i;
            }
        }
        if (n > 2) {
            ans.add((int) n);
        }
        return ans;
    }

    public static void precompute() {
        int MAX = (int) 1e6;
        for (int i = 1; i <= MAX; i++) {
            for (int j = i; j <= MAX; j += i) {
                cnt[i] += freq[j];
            }
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