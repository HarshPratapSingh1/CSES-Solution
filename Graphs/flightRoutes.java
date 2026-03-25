package cses.Graphs;

import java.io.*;
import java.util.*;


public class flightRoutes {
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
    	int n = scan.nextInt();
    	int m = scan.nextInt();
    	int k = scan.nextInt();
    	
    	ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
    	for(int i = 0 ; i <= n ; i++) adj.add(new ArrayList<>());
    	
    	for(int i = 0 ; i < m ; i++) {
    		int u = scan.nextInt();
    		int v = scan.nextInt();
    		long w = scan.nextLong();
    		
    		adj.get(u).add(new Edge(v,w));
    	}
    	
    	
    	PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    	pq.add(new long[] {1,0,-1});
    	PriorityQueue<Long> minPath = new PriorityQueue<>();
    	
    	while(!pq.isEmpty()) {
    		long p[] = pq.poll();
    		
    		if(p[0] == n && p[1]!= Long.MAX_VALUE) minPath.add(p[1]);
    		for(Edge it : adj.get((int)p[0])) {
    			if(it.v == p[2]) continue;
    			pq.add(new long[] {it.v,p[1]+it.w,p[0]});
    		}
    	}
    	System.out.println(minPath);
    	while (!minPath.isEmpty() && k > 0) {
    	    System.out.print(minPath.poll() + " ");
    	    k--;
    	}

    	
    	System.out.println();
    	
    }
}
class Edge{
	int v;
	long w ;
	Edge(int v,long w){
		this.v = v;
		this.w = w;
	}
}
