package cses.Graphs;
import java.io.*;
import java.util.*;

public class monsters {
	

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


	static int dx[] = {-1 , 1 , 0 , 0}; //up  down  left right
	static int dy[] = {0 , 0 , -1 , 1};
	static int r,c;
	public static void main(String[] args) throws IOException {
        FastIO scan = new FastIO(); 
        int n = scan.nextInt();
        int m = scan.nextInt();
        
        // Read the grid
        int dist[][] = new int[n][m];
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = scan.nextLine();
            grid[i] = line.toCharArray();
        }
        r = 0 ; c = 0;
        for(int i = 0 ; i < n ; i++) {
        	for(int j = 0 ; j < m ; j++) {
        		if(grid[i][j] == '.') dist[i][j] = Integer.MAX_VALUE;
        		else dist[i][j] = -1;
        	}
        }
        
     
        bfsForMonster(getList(grid),n,m,dist,grid);
        		
        
        char pathMaker[][] = new char[n][m];
        int startI = -1, startJ = -1;
        
        outerloop:
        for(int i = 0 ; i < n ; i++) {
        	for(int j = 0 ; j < m ; j++) {
        		if(grid[i][j] == 'A') {
        			if(bfsForA(i,j,n,m,dist,grid,pathMaker)) {
        				startI = i;
        				startJ = j;
        			}
        			break outerloop;
        		}
        	}
        }
        
//        for(int it[]:dist) System.out.println(Arrays.toString(it));
        if(startI == -1 || startJ == -1) System.out.println("NO");
        else {
        	BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        	
        	ArrayList<Character> l = new ArrayList<>();
        	while(r != startI || c != startJ) {
        		char val = pathMaker[r][c];
        		l.add(val);
        		int nextR = val == 'U' ? 1 : (val == 'D') ? -1 : 0;
        		int nextC = val == 'L' ? 1 : (val == 'R') ? -1 : 0;
        		r += nextR;
        		c += nextC;
        	}
//        	Collections.reverse(l);
        	
        	out.write("YES\n");
        	out.write(l.size() + "\n");
        	for (int i = l.size() - 1 ; i >= 0 ; i--) out.write(l.get(i));
        	out.flush();
        	
        }
    }
	
	public static List<int[]> getList(char grid[][]){
		List<int[]> l = new ArrayList<>();
		
		for(int i = 0 ; i < grid.length ; i++) {
			for(int j = 0 ; j < grid[0].length ; j++) {
				if(grid[i][j] == 'M')l.add(new int[] {i,j});
			}
		}
		
		return l;
	}
	
	public static void bfsForMonster( List<int[]> l , int n , int m , int dist[][],char grid[][]) {
		Queue<int[]> q = new LinkedList<>();
		boolean vis[][] = new boolean [n][m];
		
		for(int cur[] : l) {
			q.add(new int[] {cur[0],cur[1],0});
			dist[cur[0]][cur[1]] = 0;
			vis[cur[0]][cur[1]] = true;
		}
		
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int row = cur[0];
			int col = cur[1];
			int dis = cur[2];
			
			if(dist[row][col] > dis) dist[row][col] = dis;
			
			for(int d = 0 ; d < 4 ; d++) {
				int nR = row + dx[d];
				int nC = col + dy[d];
				
				if(nR >= 0 && nC >= 0 && nR < n && nC < m && !vis[nR][nC] && grid[nR][nC] == '.') {
					q.add(new int[] {nR,nC,dis + 1});
					vis[nR][nC] = true;
				}
			}
		}
	}
	
	public static boolean bfsForA(int i , int j , int n , int m , int dist[][] , char grid[][],char pathMaker[][]) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {i,j,0});
		boolean vis[][] = new boolean [n][m];
//		dist[i][j] = 0;

		while(!q.isEmpty()) {
			
			int cur[] = q.poll();
			int row = cur[0];
			int col = cur[1];
			int dis = cur[2];
						
			if(row == 0 || row == n - 1 || col == 0 || col == m - 1) {
				r = row;
				c = col;
				return true;
			}

			for(int d = 0 ; d < 4 ; d++) {
				int nR = row + dx[d];
				int nC = col + dy[d];
				if(nR >= 0 && nC >= 0 && nR < n && nC < m && !vis[nR][nC] && grid[nR][nC] == '.') {
					if(dis +1< dist[nR][nC]) {
						q.add(new int[] {nR,nC,dis+1});
						pathMaker[nR][nC] = d == 0 ? 'U' : (d == 1) ? 'D' : (d == 2) ? 'L' : 'R';
						vis[nR][nC] = true;
					}
				}
			}
		}
		return false;
	}
}
