import java.util.*;
import java.io.*;

public class PlanetsAndKingdoms {

    static FastIO scan;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        System.setOut(new PrintStream("output.txt"));
        scan = new FastIO();
        int n = scan.nextInt();
        int m = scan.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> revadj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            revadj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            adj.get(u).add(v);
            revadj.get(v).add(u);
        }
        Stack<Integer> st = new Stack<>();

        boolean[] vis = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                dfs(adj, i, vis, st);
            }
        }

        Arrays.fill(vis, false);
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++)
            res.add(-1);
        int cnt = 0;
        while (!st.isEmpty()) {
            int u = st.pop();
            // System.out.println(u);
            if (!vis[u]) {
                cnt++;
                revDfs(revadj, u, vis, cnt, res);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");
        for (int it : res)
            sb.append(it).append(" ");
        System.out.println(sb.toString());
    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj, int u, boolean[] vis, Stack<Integer> st) {
        if (vis[u])
            return;
        vis[u] = true;
        for (int it : adj.get(u)) {
            if (!vis[it])
                dfs(adj, it, vis, st);
        }
        st.push(u);
    }

    public static void revDfs(ArrayList<ArrayList<Integer>> revadj, int u, boolean[] vis, int cnt,
            ArrayList<Integer> res) {
        if (vis[u])
            return;
        vis[u] = true;
        res.set(u - 1, cnt);
        for (int it : revadj.get(u)) {
            if (!vis[it])
                revDfs(revadj, it, vis, cnt, res);
        }
    }

    // ---------------------- UTILITY METHODS BELOW ----------------------

    static class FastIO {
        private final byte[] buffer = new byte[1 << 16]; // 64KB
        private int ptr = 0, len = 0;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = System.in.read(buffer);
                ptr = 0;
                if (len <= 0)
                    return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }

    static void debug(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }

    static long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i += 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }

    static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++)
            if (isPrime[i])
                for (int j = i * i; j <= n; j += i)
                    isPrime[j] = false;
        return isPrime;
    }

    static long modExp(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    static long factorialMod(int n, int mod) {
        long result = 1;
        for (int i = 2; i <= n; i++)
            result = (result * i) % mod;
        return result;
    }

    static int[] prefixSum(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++)
            prefix[i] = prefix[i - 1] + arr[i];
        return prefix;
    }

    static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }
}
