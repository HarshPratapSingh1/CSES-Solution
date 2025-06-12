package cses.Graphs;

import java.util.*;

public class countingRooms {
    static final int[][] DIRS = {{0,1}, {1,0}, {0,-1}, {-1,0}}; 

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        scan.nextLine();

        char[][] grid = new char[n][];
        for (int i = 0; i < n; i++) {
            grid[i] = scan.nextLine().toCharArray();
        }

        boolean[][] visited = new boolean[n][m];
        int roomCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.' && !visited[i][j]) {
                    bfs(i, j, grid, visited);
                    roomCount++;
                }
            }
        }
        scan.close();
        System.out.println(roomCount);
    }

    static void bfs(int startRow, int startCol, char[][] grid, boolean[][] visited) {
        int n = grid.length, m = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];

            for (int[] d : DIRS) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                    grid[nr][nc] == '.' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
