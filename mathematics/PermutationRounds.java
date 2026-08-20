import java.util.*;
import java.io.*;

class DSU {
    int size[];
    int parent[];
    static final int MOD = 1_000_000_007;

    DSU(int n) {
        size = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
            parent[i] = i;
        }
    }

    boolean union(int u, int v) {
        int pU = find(u);
        int pV = find(v);

        if (pU == pV)
            return false;

        int sU = size[pU];
        int sV = size[pV];

        if (sU > sV) {
            parent[pV] = pU;
            size[pU] = (size[pU] + sV) % MOD;
        } else {
            parent[pU] = pV;
            size[pV] = (size[pV] + sU) % MOD;
        }
        return true;
    }

    int find(int n) {
        if (n == parent[n])
            return n;

        return parent[n] = find(parent[n]);
    }
}

public class PermutationRounds {
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
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = scan.nextInt();

        DSU d = new DSU(n + 1);

        for (int i = 0; i < n; i++) {
            int u = i + 1;
            int v = arr[i];

            if (d.find(u) != d.find(v))
                d.union(u, v);
        }

        // System.out.println(Arrays.toString(d.parent)+" "+Arrays.toString(d.size));
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            int parent = d.find(i);
            if (!map.containsKey(parent))
                map.put(parent, d.size[parent]);
        }
        freq = new long[(int) 1e5 + 1];
        System.out.println(lcm(map));
    }

    static long freq[];

    public static long lcm(HashMap<Integer, Integer> map) {
        long lcm = 1;

        for (Map.Entry<Integer, Integer> mp : map.entrySet()) {
            HashMap<Integer, Integer> mp1 = fact(mp.getValue());
            for (Map.Entry<Integer, Integer> mp2 : mp1.entrySet()) {
                if (mp2.getValue() > freq[mp2.getKey()])
                    freq[mp2.getKey()] = mp2.getValue();
            }
        }

        for (long i = 1; i <= (long) 1e5; i++) {
            lcm = (lcm * (pow(i, freq[(int) i]))) % MOD;
        }

        return lcm;
    }

    public static long pow(long a, long b) {
        if (b == 0)
            return 1;

        long val = pow(a, b / 2);
        val = (val * val) % MOD;

        if (b % 2 != 0)
            val = (val * a) % MOD;

        return val;
    }

    public static HashMap<Integer, Integer> fact(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        while (n % 2 == 0) {
            map.put(2, map.getOrDefault(2, 0) + 1);
            n /= 2;
        }

        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        if (n > 2)
            map.put(n, map.getOrDefault(n, 0) + 1);
        return map;
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