package cses.Graphs;
import java.io.*;
import java.util.*;

public class roundTrip {
    static int parent[];
    static int last , start;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int val1 = Integer.parseInt(st.nextToken());
            int val2 = Integer.parseInt(st.nextToken());
            adj.get(val1).add(val2);
            adj.get(val2).add(val1);
        }

        parent = new int[n + 1];
        last = -1 ; start = -1;
        boolean vis[] = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                if (dfs(i, -1, vis, adj)) {
                    break;
                }
            }
        }
        if(last == -1) System.out.println("IMPOSSIBLE");
        
        else {
//        	System.out.println(start +" "+last+" "+Arrays.toString(parent));
        	ArrayList<Integer>path = new ArrayList<>();
        	
        	int i = last;
        	path.add(start);
        	while(i != start) {
        		path.add(i);
        		i = parent[i];
        	}
        	path.add(start);
        	Collections.reverse(path);
        	
        	System.out.println(path.size());
        	for(int it:path) System.out.print(it+" ");
        	System.out.println();
        }
        
        
    }

    public static boolean dfs(int node, int par, boolean[] vis, ArrayList<ArrayList<Integer>> adj) {
        vis[node] = true;
        parent[node] = par;

        for (int neighbor : adj.get(node)) {
            if (neighbor == par) continue;

            if (vis[neighbor]) {
            	start = neighbor;
                last = node;
                return true;
            }

            if (dfs(neighbor, node, vis, adj)) {
                return true;
            }
        }

        return false;
    }
}
