package cses.Graphs;
import java.io.*;
import java.util.*;
public class BuildingRoads {
	public static void main(String[]args)throws IOException {
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

        ArrayList<Integer> joiningCity = new ArrayList<>();
        int[] vis = new int[n + 1];
        Arrays.fill(vis, -1);

        for (int i = 1; i <= n; i++) {
            if (vis[i] == -1) {
                joiningCity.add(i);
                solve(adj, i, vis);
            }
        }

        if (joiningCity.size() > 1) {
            out.println(joiningCity.size() - 1);
            for (int i = 1; i < joiningCity.size(); i++) {
                out.println(joiningCity.get(i - 1) + " " + joiningCity.get(i));
            }
        } else {
            out.println(0);
        }

        out.flush();
        out.close();
    }

    public static void solve(ArrayList<ArrayList<Integer>> adj, int idx, int[] vis) {
        vis[idx] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(idx);

        while (!q.isEmpty()) {
            int val = q.poll();
            for (int it : adj.get(val)) {
                if (vis[it] == -1) {
                    q.offer(it);
                    vis[it] = 1;
                }
            }
        }
    }
}