package cses.Graphs;

import java.util.*;
import java.io.*;

class tuple{
	int u;
	int v; 
	long w;
	tuple(int u ,int v,long w){
		this.u = u;
		this.v = v;
		this.w = w;
	}
}
public class cycleFinding {
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
       
       ArrayList<tuple> adj = new ArrayList<>();
       
//       for(int i = 0 ; i <= n ; i++) adj.add(new ArrayList<>());
   
       for(int i = 0 ; i < m ; i++){
           int u = scan.nextInt();
           int v = scan.nextInt();
           long w = scan.nextLong();
           adj.add(new tuple(u,v,w));
//           adj.add(new tuple(v,u,w));
       }
       long dist[] = new long[n + 1];
       int p[] = new int[n+1];
       
       Arrays.fill(dist,0);
       Arrays.fill(p,-1);
       
       int x = -1;
       
       for(int i = 0 ; i < n ; i++) {
    	   x = -1;
    	   for(tuple t : adj) {
    		   if(dist[t.v] > dist[t.u] + t.w) {
 				  dist[t.v] = dist[t.u]+t.w;
 				  p[t.v] = t.u;
 				  x = t.v;
 				   
 			  }
    	   }
       }
       if(x == -1) {
    	   System.out.println("NO");
       }else {
           int y = x;
           
           for (int i = 0; i < n; i++) y = p[y];

           List<Integer> path = new ArrayList<>();
           for (int cur = y; ; cur = p[cur]) {
               path.add(cur);
               if (cur == y && path.size() > 1) break;
           }
           Collections.reverse(path);

           System.out.println("YES");
           for (int u : path) {
               System.out.print(u + " ");
           }
           System.out.println();
       }
   }
}
