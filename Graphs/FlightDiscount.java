package cses.Graphs;
import java.util.*;

import java.io.*;

public class FlightDiscount {
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
    public static void main(String[]args) {
    	int n = scan.nextInt() , m = scan.nextInt();
    	
    	List<List<Pair>> adj1 = new ArrayList<>();
    	List<List<Pair>> adj2 = new ArrayList<>();
    	long edg[][] = new long[m][3];
    	for(int i = 0 ; i <= n ; i++) {
    		adj1.add(new ArrayList<>());
    		adj2.add(new ArrayList<>());
    	}
    	
    	for(int i = 0 ; i < m ; i++) {
    		int u = scan.nextInt();
    		int v = scan.nextInt();
    		long w = scan.nextLong();
    		
    		edg[i][0] = u;
    		edg[i][1] = v;
    		edg[i][2] = w;
    		adj1.get(u).add(new Pair(v,w));
    		adj2.get(v).add(new Pair(u,w));
    	}
    	
    	long dist1[] = dijkstra(n+1, adj1, 1);
    	long dist2[] = dijkstra(n+1, adj2, n);
    	
    	long ans = Long.MAX_VALUE;
//    	System.out.println(Arrays.toString(dist1)+" "+Arrays.toString(dist2));
    	for(long it[]:edg) {
    		int u = (int)it[0] , v = (int)it[1] ;
    		long w = it[2];
    		
    		if(dist1[u] == Long.MAX_VALUE || dist2[v] == Long.MAX_VALUE) continue;
    		
    		long one = dist1[u];
    		long two = dist2[v];
    		ans = Math.min(ans, one+two+(w/2));
    	}
    	scan.println(String.valueOf(ans));

    }	
    public static long[] dijkstra(int V, List<List<Pair>> adj, int src) {
        long[] dist = new long[V];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.dist));
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int u = cur.node;
            long d = cur.dist;

            if (d > dist[u]) continue; 

            for (Pair edge : adj.get(u)) {
                int v = edge.node;
                long w = edge.dist;

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }
        return dist;
    }
    static class Pair {
        int node;
        long dist;
        Pair(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }
}
