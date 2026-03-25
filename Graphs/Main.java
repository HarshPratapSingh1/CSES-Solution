import java.util.*;
import java.io.*;

public class Main {
    static FastIO io;
    static final int MOD = 1_000_000_007;
    static final long LINF = (long) 1e18;

    public static void main(String[] args) throws Exception {
        io = new FastIO();
        int t = 1;
        // t = io.nextInt();
        while (t-- > 0) {
            solve();
        }
        io.close();
    }

    static void solve() throws IOException {

    }

    // ---------------------- NUMBER THEORY ----------------------

    // TC: O(log(min(a, b)))
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // TC: O(log e)
    static long modExp(long b, long e, long m) {
        long r = 1;
        b %= m;
        while (e > 0) {
            if ((e & 1) == 1)
                r = (r * b) % m;
            b = (b * b) % m;
            e >>= 1;
        }
        return r;
    }

    static int[] spf; // Smallest Prime Factor
    static List<Integer> primes = new ArrayList<>();

    // TC: O(N) | Marks each number by its SPF
    static void sieve(int n) {
        spf = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                primes.add(i);
            }
            for (int p : primes) {
                if (p > spf[i] || (long) i * p > n)
                    break;
                spf[i * p] = p;
            }
        }
    }

    // TC: O(log x) | Fast factorization using SPF
    static List<Integer> getFactorization(int x) {
        List<Integer> factors = new ArrayList<>();
        while (x > 1) {
            factors.add(spf[x]);
            x /= spf[x];
        }
        return factors;
    }

    // ---------------------- COMBINATORICS ----------------------

    static long[] fact, invFact;

    // TC: O(N + log MOD)
    static void precomputeNCR(int n, int m) {
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++)
            fact[i] = (fact[i - 1] * i) % m;
        invFact[n] = modExp(fact[n], m - 2, m);
        for (int i = n - 1; i >= 0; i--)
            invFact[i] = (invFact[i + 1] * (i + 1)) % m;
    }

    // TC: O(1) after precomputation
    static long nCr(int n, int r, int m) {
        if (r < 0 || r > n)
            return 0;
        return (((fact[n] * invFact[r]) % m) * invFact[n - r]) % m;
    }

    // ---------------------- MATRIX EXP ----------------------

    // TC: O(dim^3)
    static long[][] multiply(long[][] a, long[][] b, long m) {
        int n = a.length;
        long[][] c = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % m;
        return c;
    }

    // TC: O(dim^3 * log p)
    static long[][] matrixPower(long[][] a, long p, long m) {
        int n = a.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++)
            res[i][i] = 1;
        while (p > 0) {
            if (p % 2 == 1)
                res = multiply(res, a, m);
            a = multiply(a, a, m);
            p /= 2;
        }
        return res;
    }

    // ---------------------- ARRAY & SEARCH UTILS ----------------------

    // TC: O(N log N) | Safe from O(N^2) hacks
    static void sort(int[] a) {
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            int j = r.nextInt(a.length);
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        Arrays.sort(a);
    }

    // TC: O(N log N) | Compresses large coordinates to 0...M-1
    static int[] compress(int[] a) {
        int n = a.length;
        int[] sorted = a.clone();
        sort(sorted);
        int sz = 0;
        for (int i = 0; i < n; i++)
            if (i == 0 || sorted[i] != sorted[i - 1])
                sorted[sz++] = sorted[i];
        int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = Arrays.binarySearch(sorted, 0, sz, a[i]);
        return res;
    }
    // ---------------------- GEOMETRY UTILS ----------------------

    // TC: O(1) | 0: collinear, 1: CW, 2: CCW
    static int orientation(long x1, long y1, long x2, long y2, long x3, long y3) {
        long val = (y2 - y1) * (x3 - x2) - (x2 - x1) * (y3 - y2);
        if (val == 0)
            return 0;
        return (val > 0) ? 1 : 2;
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

public class TarjanBridge {

    static int n;
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] vis;
    static int[] tin, low;
    static int timer;
    static ArrayList<int[]> bridges;

    static void init(int N) {
        n = N;
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        vis = new boolean[n + 1];
        tin = new int[n + 1];
        low = new int[n + 1];
        timer = 0;
        bridges = new ArrayList<>();
    }

    static void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void dfs(int v, int parent) {
        vis[v] = true;
        tin[v] = low[v] = timer++;

        for (int to : adj.get(v)) {
            if (to == parent) continue;

            if (vis[to]) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v);
                low[v] = Math.min(low[v], low[to]);

                if (low[to] > tin[v]) {
                    bridges.add(new int[]{v, to});
                }
            }
        }
    }

    static void findBridges() {
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) dfs(i, -1);
        }
    }
}