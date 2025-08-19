package cses.Graphs;

import java.util.*;
import java.io.*;

public class highScore {
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
	
    public static void main(String[] args) {
        int n = scan.nextInt();
        int m = scan.nextInt();
        
        ArrayList<ArrayList<long[]>> adj = new ArrayList<>();
        
        for(int i = 0 ; i <= n ; i++) adj.add(new ArrayList<>());
        
        for(int i = 0 ; i < m ; i++){
            int u = scan.nextInt();
            int v = scan.nextInt();
            long w = scan.nextLong();
            
            adj.get(u).add(new long[]{v,w});
        }
        
        long dist[] = new long[n+1];
        Arrays.fill(dist,Long.MIN_VALUE);
        dist[1] = 0;
        
        
        for (int i = 0; i < n - 1; i++) {
            for (int u = 1; u <= n; u++) {
                for (long[] edge : adj.get(u)) {
                    int v = (int)edge[0];
                    long w = edge[1];
                    if (dist[u] != Long.MIN_VALUE && dist[v] < dist[u] + w) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
        }
 
        HashSet<Integer> set = new HashSet<>();
        for (int u = 1; u <= n; u++) {
            for (long[] edge : adj.get(u)) {
                int v = (int)edge[0];
                long w = edge[1];
                if (dist[u] != Long.MIN_VALUE && dist[v] < dist[u] + w) {
                    set.add(v); 
                }
            }
        }
        
        Queue<Integer>q = new LinkedList<>();
        Queue<Integer>cq = new LinkedList<>();
        q.add(1);
        boolean vis[] = new boolean[n+1];
        
        while(!q.isEmpty()){
            int u = q.poll();
            if(vis[u]) continue;
            
            vis[u] = true;
            
            if(set.contains(u)){
               cq.add(u);
            }
            for(long it[] : adj.get(u)){
               if(!vis[(int)it[0]])q.add((int)it[0]);
            }
        }
        
        boolean canBeReachedToN = false;
        Arrays.fill(vis,false);
        
        while (!cq.isEmpty()) {
            int u = cq.poll();
        
            if (vis[u]) continue;
        
            vis[u] = true;
        
            if (u == n) {
                canBeReachedToN = true;
                break;
            }
        
            for (long[] it : adj.get(u)) {
                if (!vis[(int) it[0]]) cq.add((int) it[0]);
            }
        }
 
        
        if(!canBeReachedToN) System.out.println(dist[n]);
        else System.out.println(-1);
            
        
    }
}
