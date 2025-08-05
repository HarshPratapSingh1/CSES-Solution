package cses.Graphs;

import java.io.*;
import java.util.*;

public class shortestPath1 {
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

    // SPFA for directed weighted graph - shortest paths from node 1
    public static void main(String[] args) {
        FastIO scan = new FastIO();
        int n = scan.nextInt();
        int m = scan.nextInt();

        ArrayList<ArrayList<long[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            long c = scan.nextLong();
            adj.get(a).add(new long[]{b, c});
        }

        long dist[] = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<long[]> q = new PriorityQueue<>(Comparator.comparingLong(x -> x[1]));
        q.add(new long[] {1,0});

        while (!q.isEmpty()) {
            long pa[] = q.poll();
            long u = pa[0];
            long d = pa[1];
            
            if(d > dist[(int)u]) continue;
            
            for (long it[]:adj.get((int)u)) {
                long v = it[0];
                long w = it[1];

                if (dist[(int)v] > dist[(int)u] + w) {
                    dist[(int)v] = dist[(int)u] + w;
                    q.add(new long[] {v,dist[(int)v]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb);    
        }
}
