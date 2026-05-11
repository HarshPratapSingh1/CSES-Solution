import java.util.*;
import java.io.*;

public class DistinctValuesSubarrays2 {
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
        int k = scan.nextInt();
        long arr[] = new long[n];

        for (int i = 0; i < n; i++)
            arr[i] = scan.nextLong();

        HashMap<Long, Long> map = new HashMap<>();
        ArrayList<int[]> l = new ArrayList<>();

        int i = 0, j = 0;

        boolean fl = false;
        while (j < n) {
            map.put(arr[j], map.getOrDefault(arr[j], 0l) + 1);
            fl = false;
            while (i < j && map.size() > k) {
                if (!fl)
                    l.add(new int[] { i, j - 1 });
                fl = true;
                map.put(arr[i], map.getOrDefault(arr[i], 0l) - 1);
                if (map.get(arr[i]) == 0)
                    map.remove(arr[i]);
                i++;
            }
            j++;
        }
        if (map.size() <= k)
            l.add(new int[] { i, j - 1 });

        long end = -1;
        long ans = 0;
        for (int it[] : l) {
            if (end < it[0]) {
                long dif = it[1] - it[0] + 1;
                ans += (dif * (dif + 1) / 2);
                end = it[1];
            } else {
                long dif = it[1] - it[0] + 1;
                long dif2 = end - it[0] + 1;
                long val = (dif * (dif + 1) / 2) - (dif2 * (dif2 + 1) / 2);
                ans += val;
                end = it[1];
            }
        }
        System.out.println(ans);
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