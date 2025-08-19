package cses.Graphs;
import java.io.*;
import java.util.*;


public class shortestroutes2 {
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

    // used floyd warshall algo
	static FastIO scan = new FastIO();
    public static void main(String[] args) {
        
        int n = scan.nextInt();
        int m = scan.nextInt();
        int q = scan.nextInt();

        long matAd[][] = new long[n][n];
        
        
        for(int i = 0 ; i < n ; i++) {
        	Arrays.fill(matAd[i], Long.MAX_VALUE/2);
        	matAd[i][i] = 0;
        }
        
        
        for (int i = 0; i < m; i++) {
            int a = scan.nextInt()-1;
            int b = scan.nextInt()-1;
            long c = scan.nextLong();
            if(c < matAd[a][b]) {
            	matAd[a][b] = c;
            	matAd[b][a] = c;
            }
        }
        
        
        for (int k = 0; k < n; k++) {
            long[] distK = matAd[k];
            for (int i = 0; i < n; i++) {
                long dik = matAd[i][k];
                if (dik == Long.MAX_VALUE/2) continue;
                long[] distI = matAd[i];
                for (int j = 0; j < n; j++) {
                    long dkj = distK[j];
                    if (dkj == Long.MAX_VALUE/2) continue;
                    long newDist = dik + dkj;
                    if (newDist < distI[j]) {
                        distI[j] = newDist;
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(q-->0) {
        	int a = scan.nextInt() - 1;
            int b = scan.nextInt() - 1;
            if (a == b) {
                sb.append(0).append("\n");
            } else {
                long ans = matAd[a][b];
                sb.append(ans == Long.MAX_VALUE/2 ? -1 : ans).append("\n");
            }
        }
        System.out.println(sb.toString());
        
   }
}
