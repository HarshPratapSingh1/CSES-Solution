package cses.Graphs;
import java.util.*;
import java.io.*;

public class RoadConstruction {
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
    	
    	disjointSetUnion dsu = new disjointSetUnion(n);
    	
    	int compNo = n;
    	int size = 1;
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i = 0 ; i < m ; i++) {
    		int val1 = scan.nextInt();
    		int val2 = scan.nextInt();
    		int parent1 = dsu.findUltimateParent(val1);
    		int parent2 = dsu.findUltimateParent(val2);
    		
    		if(parent1 == parent2) {
    			sb.append(compNo).append(" ").append(size+"\n");
    			continue;
    		}
    		compNo--;
    		int curSize1 = dsu.size[parent1];
    		int curSize2 = dsu.size[parent2];
    		
    		size = Math.max(size, curSize2+curSize1);
    		
    		dsu.unionBySize(val1, val2);
    		sb.append(compNo).append(" ").append(size+"\n");
    	}
    	System.out.println(sb.toString());
    }
}
//Note we use either by rank or by size
class disjointSetUnion{
  int [] rank, parent, size;
  disjointSetUnion(int n){
      rank = new int[n+1];
      parent = new int[n+1];
      
      for(int i = 0 ; i <= n ; i++) parent[i] = i;


      //if we do union by size
      size = new int[n+1];
      for(int i = 0 ; i <= n ; i++) size[i] = 1;

  }
  public int findUltimateParent(int u){
      if(parent[u] == u) return u;
      else return parent[u] = findUltimateParent(parent[u]); // path compression;
  }

  public void unionByRank(int u , int v){
      int ultiOf_u = findUltimateParent(u);
      int ultiOf_v = findUltimateParent(v);

      if(ultiOf_u == ultiOf_v) return;
      if(rank[ultiOf_u] > rank[ultiOf_v]) parent[ultiOf_v] = ultiOf_u;
      else if(rank[ultiOf_u] < rank[ultiOf_v]) parent[ultiOf_u] = ultiOf_v;
      else {
          parent[ultiOf_u] = ultiOf_v;
          rank[ultiOf_v]++;
      }
  }

  public void unionBySize(int u , int v){
      int ultiOf_u = findUltimateParent(u);
      int ultiOf_v = findUltimateParent(v);

      if(ultiOf_u == ultiOf_v) return;
      if(size[ultiOf_u] > size[ultiOf_v]) {
          parent[ultiOf_v] = ultiOf_u;
          size[ultiOf_u] += size[ultiOf_v];
      }
      else {
          parent[ultiOf_u] = ultiOf_v;
          size[ultiOf_v] += size[ultiOf_u];
      }
  }
}
