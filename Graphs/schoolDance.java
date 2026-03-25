import java.util.*;
import java.io.*;

public class schoolDance {
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
    static FastIO scan = new FastIO();

    public static void main(String[]args){
        int n = scan.nextInt();
        int m = scan.nextInt();
        int k = scan.nextInt();     
        Dinic d = new Dinic(n+m+2, 0, n+m+1);

        for(int i = 0 ; i < k ; i++){
            int val1 = scan.nextInt();
            int val2 = scan.nextInt() + n;
            
            d.addEdge(val1, val2, 1);
        }
        for(int i = 1 ; i <= n ; i++) d.addEdge(0, i, 1);
        for(int i = 1 ; i <= m ; i++) d.addEdge(n+i, n+m+1, 1);


        System.out.println(d.flow());
        for(Edge it : d.edges){
            if(it.u > n && it.u < n+m+1 && it.v > 0 && it.v <= n && it.flow == 1) System.out.println(it.v+" "+(it.u-n));
        }
        

    }
}
class Edge {
    int v, u;
    long cap, flow;
    Edge(int v, int u, long cap) {
        this.v = v;
        this.u = u;
        this.cap = cap;
        this.flow = 0;
    }
}
class Dinic {
    static final long INF = Long.MAX_VALUE;
    List<Edge> edges = new ArrayList<>();
    List<List<Integer>> adj;
    int n, s, t;
    int[] level, ptr;
    Queue<Integer> q = new ArrayDeque<>();

    Dinic(int n, int s, int t) {
        this.n = n;
        this.s = s;
        this.t = t;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        level = new int[n];
        ptr = new int[n];
    }

    void addEdge(int v, int u, long cap) {
        edges.add(new Edge(v, u, cap));
        edges.add(new Edge(u, v, 0));
        adj.get(v).add(edges.size() - 2);
        adj.get(u).add(edges.size() - 1);
    }

    boolean bfs() {
        Arrays.fill(level, -1);
        level[s] = 0;
        q.clear();
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int id : adj.get(v)) {
                Edge e = edges.get(id);
                if (e.cap == e.flow || level[e.u] != -1) continue;
                level[e.u] = level[v] + 1;
                q.add(e.u);
            }
        }
        return level[t] != -1;
    }

    long dfs(int v, long pushed) {
        if (pushed == 0) return 0;
        if (v == t) return pushed;
        for (; ptr[v] < adj.get(v).size(); ptr[v]++) {
            int id = adj.get(v).get(ptr[v]);
            Edge e = edges.get(id);
            if (level[v] + 1 != level[e.u] || e.cap == e.flow) continue;
            long tr = dfs(e.u, Math.min(pushed, e.cap - e.flow));
            if (tr == 0) continue;
            e.flow += tr;
            edges.get(id ^ 1).flow -= tr;
            return tr;
        }
        return 0;
    }

    long flow() {
        long f = 0;
        while (true) {
            if (!bfs()) break;
            Arrays.fill(ptr, 0);
            long pushed;
            while ((pushed = dfs(s, INF)) != 0) {
                f += pushed;
            }
        }
        return f;
    }
}
