import java.util.*;
import java.io.*;

public class MountainRange {
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

    static int n;
    static long arr[];
    static long memo[];

    static void solve() throws IOException {
        n = scan.nextInt();
        arr = new long[n];
        for (int i = 0; i < n; i++)
            arr[i] = scan.nextLong();

        rev();
        int nxt[] = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i])
                stack.pop();

            if (stack.isEmpty())
                nxt[i] = n;
            else
                nxt[i] = stack.peek();

            stack.push(i);
        }

        ArrayList<int[]> smallChunks = new ArrayList<>();

        int idx = 0;
        while (idx < n) {
            int l = idx;
            int r = nxt[idx];
            smallChunks.add(new int[] { l, r - 1 });
            idx = r;
        }
        long res = 0;
        System.out.println(Arrays.toString(arr));
        for(int it[]:smallChunks){
            res = Math.max(res, it[1]-it[0]);
        }

        System.out.println(res);        
    }

    public static void rev() {
        for (int i = 0; i < n / 2; i++) {
            long temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - 1 - i] = temp;
        }
    }

    public static int bs(long val, ArrayList<Long> arr) {
        int l = 0, r = arr.size() - 1;
        int ans = arr.size();
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (val > arr.get(mid)) {
                ans = mid;
                r = mid - 1;
            } else
                l = mid + 1;
        }
        return ans;
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