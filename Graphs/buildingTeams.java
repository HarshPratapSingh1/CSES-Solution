package cses.Graphs;
import java.io.*;
import java.util.*;
public class buildingTeams {
	 static boolean flag = false;

	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());

	        int n = Integer.parseInt(st.nextToken());
	        int m = Integer.parseInt(st.nextToken());

	        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());

	        for (int i = 0; i < m; i++) {
	            st = new StringTokenizer(br.readLine());
	            int u = Integer.parseInt(st.nextToken());
	            int v = Integer.parseInt(st.nextToken());
	            adj.get(u).add(v);
	            adj.get(v).add(u);
	        }

	        int[] color = new int[n + 1];
	        Arrays.fill(color, 0);

	        for (int i = 1; i <= n; i++) {
	            if (color[i] == 0) {
	                if (!bfs(i, adj, color)) {
	                    flag = true;
	                    break;
	                }
	            }
	        }

	        if (flag) {
	            System.out.println("IMPOSSIBLE");
	        } else {
	            StringBuilder sb = new StringBuilder();
	            for (int i = 1; i <= n; i++) {
	                sb.append(color[i]).append(" ");
	            }
	            System.out.println(sb.toString().trim());
	        }
	    }

	    public static boolean bfs(int start, ArrayList<ArrayList<Integer>> adj, int[] color) {
	        Queue<Integer> q = new LinkedList<>();
	        q.add(start);
	        color[start] = 1;

	        while (!q.isEmpty()) {
	            int node = q.poll();
	            for (int neighbor : adj.get(node)) {
	                if (color[neighbor] == 0) {
	                    color[neighbor] = 3 - color[node];
	                    q.add(neighbor);
	                } else if (color[neighbor] == color[node]) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	}