import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class gridPathDescription {
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
    static int dist[][] = {{0,1},{0,-1},{1,0},{-1,0}}; // R L D U
    static int cnt;
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        String s = scan.next();
        boolean vis[][] = new boolean[7][7];
        cnt = 0;
        char arr[] = s.toCharArray();
        solve(s,0,0,vis,0,arr);
        scan.println(String.valueOf(cnt));
    }
    public static void solve(String s, int row, int col, boolean vis[][] , int idx, char arr[]){
        if(row == 6 && col == 0) {
            if(idx == 48) cnt++;
            return;
        }
        if(idx == 48) return;


        if(((row == 0 || vis[row-1][col]) && (row == 6 || vis[row+1][col]) &&
              col > 0 && col < 6 && !vis[row][col-1] && !vis[row][col+1])) return;
        
        if(((col == 0 || vis[row][col-1]) && (col == 6 || vis[row][col+1]) &&
            row > 0 && row < 6 && !vis[row-1][col] && !vis[row+1][col])) return;
        
              
        vis[row][col] = true;
        char c = arr[idx];
        if(c == '?'){
            for(int i = 0 ; i < 4 ; i++){
                int nr = row + dist[i][0];
                int nc = col + dist[i][1];

                if(check(nr,nc,vis)) solve(s,nr,nc,vis,idx+1,arr);
            }

        }else{
            int val = -1;
            if(c == 'R') val = 0;
            else if(c == 'L') val = 1;
            else if(c == 'D') val = 2;
            else if(c == 'U') val = 3;

            int nr = row + dist[val][0];
            int nc = col + dist[val][1];

            if(check(nr, nc, vis)) solve(s, nr, nc, vis, idx+1, arr);
        }
        
        vis[row][col] = false;
    }
    public static boolean check(int i, int j, boolean vis[][]){
        return i >= 0 && i < 7 && j >= 0 && j < 7 && !vis[i][j];
    }
}
