import java.util.*;
import java.io.*;

public class PermutationOrder {
    static FastIO scan;
    static final int MOD = 1_000_000_007;
    static final long LINF = (long) 1e18;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("input.txt"));
        // System.setOut(new PrintStream("output.txt"));
        scan = new FastIO();
        int t = 1;
        fact = new long[(int) 1e4 + 1];
        precompute();
        t = scan.nextInt();
        while (t-- > 0) {
            solve();
        }
        scan.close();
    }

    static long fact[];

    public static void precompute() {
        fact[0] = 1;
        fact[1] = 1;

        for (int i = 2; i <= 1e4; i++) {
            fact[i] = (fact[i - 1] * i);
        }
    }

    static void solve() throws IOException {
        int move = scan.nextInt();

        if (move == 1) {
            int n = scan.nextInt();
            long k = scan.nextLong();

            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 1; i <= n; i++)
                set.add(i);

            long curK = 0;
            ArrayList<Integer> l = new ArrayList<>();
            int idx = 0;
            while (!set.isEmpty()) {
                int val = -1;
                for (int it : set) {
                    long dif = set.size() - 1;
                    long temp = curK + fact[(int) dif];

                    if (temp >= k) {
                        val = it;
                        System.out.print(it + " ");
                        break;
                    }
                    curK = temp;
                }
                if (val != -1)
                    set.remove(val);
            }
            System.out.println();
        } else {
            int n = scan.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = scan.nextInt();

            int sL[] = new int[n];
            for (int i = 0; i < n; i++) {
                int cnt = 0;
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] > arr[j])
                        cnt++;
                }
                sL[i] = cnt;
            }

            long ans = 0;

            for (int i = 0; i < n - 1; i++) {
                ans += (fact[n - i - 1] * sL[i]);
            }

            ans++;
            System.out.println(ans);
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