package cses.Graphs;
import java.util.*;
public class labyrinth {
	static int dir [][] = {{1 , 0} , {-1 , 0} , {0 , -1} , {0 , 1}};
	static int cnt ;
	static char[] moves = {'D', 'U', 'L', 'R'};
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		
		scan.nextLine();
		
		char grid[][] = new char[n][m];
		boolean vis[][] = new boolean[n][m];
		char directions[][] = new char [n][m];
		
		for(int i = 0 ; i < n ; i++) {
			String s = scan.next();
			grid[i] = s.toCharArray();
		}
		
		cnt = 0;
		
		int StartR = -1 , StartCol = -1 , endRow = -1 , endCol = -1;
		
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				if(grid[i][j] == 'A') {
					StartR = i;
					StartCol = j;
				}
				if(grid[i][j] == 'B') {
					endRow = i;
					endCol = j;
				}
			}
		}
		if(StartR == -1 || StartCol == -1 || endCol == -1 | endRow == -1) {System.out.println("NO");}
		else {
			boolean isFound = false;
			int count = 0;
			
			Queue<Pair> q = new LinkedList<>();
			
			q.offer(new Pair(StartR,StartCol,cnt));

			vis[StartR][StartCol] = true;

			while(!q.isEmpty()) {
				
				Pair arr = q.poll();
				int r = arr.r , c = arr.c , cnt = arr.cnt ;
					
				if(r == endRow && c == endCol) {
					count = cnt;
					isFound = true;
					break;
				}
					
				for(int j = 0 ; j < 4 ; j++) {
					int nR = r + dir[j][0];
					int nC = c + dir[j][1];

					if(nR >= 0 && nR < n && nC >=0 && nC < m && !vis[nR][nC] && grid[nR][nC] != '#' ) {
						vis[nR][nC] = true;
						q.offer(new Pair (nR,nC ,cnt + 1));
						directions[nR][nC] = moves[j];
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			if(isFound) {
				int i = endRow , j = endCol;
				while(i != StartR || j != StartCol) {
					if(directions[i][j] == 'U') {sb.append('U'); i++;}
					else if(directions[i][j] == 'R') {sb.append('R'); j--;}
					else if(directions[i][j] == 'L') {sb.append('L'); j++;}
					else if(directions[i][j] == 'D') {sb.append('D'); i--;}
				}
				sb.reverse();
				System.out.println("YES");
				System.out.println(count);
				System.out.println(sb.toString());
				
			}
			else System.out.println("NO");
			
		}
	}
}

class Pair{
	int r ,c ,cnt;
	
	Pair(int r , int c ,int cnt ){
		this.r = r;
		this.c = c;
		this.cnt = cnt;
	}
}