package cses.Graphs;
import java.io.*;
import java.util.*;
public class messageRoute {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        
        ArrayList<ArrayList<Integer>> l = new ArrayList<>();
        for (int i = 0; i <= n; i++) l.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            String[] uv = br.readLine().split(" ");
            int u = Integer.parseInt(uv[0]);
            int v = Integer.parseInt(uv[1]);
            
            l.get(u).add(v);
            l.get(v).add(u);
        }

        boolean[] vis = new boolean[n + 1];
        int[] parent = new int[n + 1];
        
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = true;

        boolean flag = false;
        while (!q.isEmpty()) {
            int val = q.poll();

            if (val == n) {
                flag = true;
                break;
            }

            for (int it : l.get(val)) {
                if (!vis[it]) {
                    parent[it] = val;
                    q.add(it);
                    vis[it] = true;
                }
            }
        }

        if (!flag) {
            System.out.println("IMPOSSIBLE");
        } else {
            ArrayList<Integer> path = new ArrayList<>();
            int i = n;
            while (i != 1) {
                path.add(i);
                i = parent[i];
            }
            path.add(1);
            Collections.reverse(path);
            
            System.out.println(path.size());
            for (int node : path) System.out.print(node + " ");
            System.out.println();
        }
    }
}