import java.util.*;
import java.io.*;

public class flightRoutesCheck {

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
        // First DFS (ordering)
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                getTimmer(adj, n, st, i, vis);
            }
        }
        // System.out.println(st);
        Arrays.fill(vis, false);

        int scc = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (!vis[node]) {
                // System.out.println(node);
                scc++;
                if (scc > 1)
                    break;
                dfsRev(revadj, node, vis);
            }
        }
        // System.out.println(Arrays.toString(vis));
        if (scc == 1)
            System.out.println("YES");
        else {
            int a = -1;
            for (int i = 1; i <= n; i++) {
                if (!vis[i]) {
                    a = i;
                    break;
                }
            }
            int b = -1;
            for (int i = 1; i <= n; i++) {
                if (vis[i]) {
                    b = i;
                    break;
                }
            }
            System.out.println("NO");
            System.out.println(a + " " + b);
        }
    }

    public static void getTimmer(ArrayList<ArrayList<Integer>> adj, int n, Stack<Integer> st, int u, boolean[] vis) {
        vis[u] = true;
        for (int it : adj.get(u)) {
            if (!vis[it]) {
                getTimmer(adj, n, st, it, vis);
            }
        }
        st.add(u);
    }

    static void dfsRev(ArrayList<ArrayList<Integer>> revadj, int u, boolean[] vis) {
        vis[u] = true;
        for (int it : revadj.get(u)) {
            if (!vis[it]) {
                dfsRev(revadj, it, vis);
            }
        }
    }
    // ---------------------- UTILITY METHODS BELOW ----------------------

    static class FastIO {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        void print(String s) {
            System.out.print(s);
        }

        void println(String s) {
            System.out.println(s);
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
