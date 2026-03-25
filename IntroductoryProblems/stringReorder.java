import java.util.*;
import java.io.*;

public class stringReorder {
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
        String s = scan.next();
        int n = s.length();

        int freq[] = new int[26];
        for (char ch : s.toCharArray())
            freq[ch - 'A']++;

        int maxFreq = 0;
        for (int it : freq)
            maxFreq = Math.max(maxFreq, it);

        if (maxFreq > (s.length() + 1) / 2) {
            System.out.println(-1);
            return;
        }

        char ans[] = new char[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (freq[j] == 0 || (i > 0 && (char) (j + 'A') == ans[i - 1]))
                    continue;

                int maxVal = -1;
                freq[j]--;

                for (int k = 0; k < 26; k++) {
                    if (freq[k] == 0)
                        continue;
                    maxVal = Math.max(maxVal, freq[k]);
                }

                int dif = (n - i) / 2;

                if (dif >= maxVal) {
                    ans[i] = (char) (j + 'A');
                    break;
                } else
                    freq[j]++;
            }
        }
        System.out.println(new String(ans));
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