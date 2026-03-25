// package cses.Graphs;

import java.util.*;
import java.io.*;
public class downloadSpeed {
    // Fast Input and Output
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

        long[][] capacity = new long[n][n];
        
        for(int i = 0 ; i < m ; i++){
            int u = scan.nextInt() - 1;
            int v = scan.nextInt() - 1;
            long cap = scan.nextInt();

            addEdge(u, v, cap, capacity);
        }

        long ans = maxFlow(0, n - 1, n, capacity);
        System.out.println(ans);
    }
    
    public static void addEdge(int u, int v, long cap, long capacity[][]){
        capacity[u][v] += cap;
    }
    public static long maxFlow(int s, int t, int n, long capacity[][]){
        long height[] = new long[n];
        long flow[][] = new long[n][n];
        long excess[] = new long[n];
        int seen[] = new int[n];
        Queue<Integer> excessVertices = new LinkedList<>();

        height[s] = n;
        for(int i = 0; i < n; i++) flow[s][i] = 0L;
        excess[s] = Long.MAX_VALUE;

        for(int i = 0; i < n; i++){
            if(i != s) push(s, i, excess, capacity, flow, excessVertices);
        }


        while(!excessVertices.isEmpty()){
            int u = excessVertices.poll();
            if(u != s && u != t) discharge(u,n,excess,capacity,flow,seen,height,excessVertices);
        }

        long maxFlow = 0;
        for(int i = 0; i < n; i++) maxFlow += flow[i][t];

        return maxFlow;

    }
    public static void push(int u, int v, long excess[], long capacity[][], long flow[][], Queue<Integer> excessVertices){
        long d = Math.min(excess[u], capacity[u][v] - flow[u][v]);
        if(d>0){
            excess[u] -= d;
            excess[v] += d;
            flow[u][v] += d;
            flow[v][u] -= d;

            if(d > 0 && excess[v] == d) excessVertices.add(v);
        }
    }
    public static void discharge(int u, int n, long excess[], long capacity[][], long flow[][], int seen[], long height[], Queue<Integer> excessVertices){
        while(excess[u] > 0){
            if(seen[u] < n){
                int v = seen[u];
                if(capacity[u][v] - flow[u][v] > 0 && height[u] > height[v]) push(u, v, excess, capacity, flow, excessVertices);
                else seen[u]++;
            }else{
                relable(u,n,capacity,flow,height);
                seen[u] = 0;
            }
        }
    }
    public static void relable(int u, int n, long capacity[][], long flow[][], long height[]){
        long d = Long.MAX_VALUE;
        for(int i = 0; i < n; i++){
            if(capacity[u][i] - flow[u][i] > 0) d = Math.min(d, height[i]);
        }
        if(d < Long.MAX_VALUE) height[u] = d+1;
    }
}
