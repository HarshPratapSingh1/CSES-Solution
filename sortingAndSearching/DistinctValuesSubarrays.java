import java.util.*;
import java.io.*;

public class DistinctValuesSubarrays {
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

        HashMap<Long, Long> map = new HashMap<>();
        ArrayList<long[]> l = new ArrayList<>();

        long cnt = 0;
        int i = 0, j = 0;
        long ans[] = new long[n];

        while (j < n) {
            map.put(arr[j], map.getOrDefault(arr[j], 0l) + 1);
            boolean flag = false;
            while (i < j && map.getOrDefault(arr[j], 0l) > 1) {
                if (!flag) {
                    long dif = j - i;
                    ans[j - 1] = dif * (dif + 1) / 2;
                    l.add(new long[] { i, j - 1 });
                }
                flag = true;
                map.put(arr[i], map.getOrDefault(arr[i], 0l) - 1);
                if (map.get(arr[i]) == 0)
                    map.remove(arr[i]);
                i++;
            }

            j++;
        }
        long dif = j - i;
        ans[j - 1] = dif * (dif + 1) / 2;
        l.add(new long[] { i, j - 1 });
        long sum = 0;
        for (long val : ans)
            sum += val;

        for (i = 1; i < l.size(); i++) {
            long end = l.get(i - 1)[1];
            long start = l.get(i)[0];
            if (end >= start) {
                dif = end - start + 1;
                cnt = dif * (dif + 1) / 2;
                sum -= cnt;
            }
        }
        System.out.println(sum);
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