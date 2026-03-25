import java.util.*;
import java.io.*;

public class CourseSchedule {

    static FastScanner scan;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        System.setOut(new PrintStream("output.txt"));
        scan = new FastScanner();

        int n = scan.nextInt();
        int m = scan.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a = scan.nextInt() - 1;
            int b = scan.nextInt() - 1;

            adj.get(a).add(b);
        }
        if (isCycle(adj, n)) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        Stack<Integer> st = new Stack<>();
        boolean vis[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i])
                dfs(i, adj, st, vis);
        }
        while (!st.isEmpty())
            System.out.print((st.pop() + 1) + " ");
        System.out.println();
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>> adj, int n) {
        int[] indeg = new int[n];

        for (int i = 0; i < n; i++) {
            for (int v : adj.get(i)) {
                indeg[v]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0)
                q.add(i);
        }

        int cnt = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            cnt++;
            for (int v : adj.get(u)) {
                if (--indeg[v] == 0)
                    q.add(v);
            }
        }

        return cnt != n;
    }

    public static void dfs(int u, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st, boolean vis[]) {
        vis[u] = true;
        for (int it : adj.get(u)) {
            if (!vis[it])
                dfs(it, adj, st, vis);
        }

        st.push(u);
    }

    // ---------------------- FAST BYTE INPUT ----------------------

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
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

    // ---------------------- UTILITY METHODS ----------------------

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