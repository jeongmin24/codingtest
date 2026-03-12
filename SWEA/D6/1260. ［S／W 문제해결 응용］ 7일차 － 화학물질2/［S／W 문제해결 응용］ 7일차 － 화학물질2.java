import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] grid;
	static int[][] matrix;
	static boolean[][] visited;
	static int idx;
	static int[][] dp;
	static int[][] chain;
	
	// 상하좌우 
	static int[] dr = {-1,1,0,0,0};
	static int[] dc = {0,0,-1,1,0};
	
	public static boolean isWall(int r, int c) {
		return (r<0||r>=N||c<0||c>=N);
	}
	
	public static void bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {sr,sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int d=0; d<4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(isWall(nr,nc)) continue;
				if(grid[nr][nc]==0) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.add(new int[] {nr,nc});
			}
		}
		
	}
	
	public static int getRowCol(int r, int c, int rd, int cd) {
		int tr = r;
		int tc = c;
		int count = 0;
		while(true) {
			
			count++;
			tr += dr[rd];
			tc += dc[cd];
			if(isWall(tr,tc) || grid[tr][tc]==0) {
				return count;
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			
			
			matrix = new int[20][2]; // 0:r 1:c
			chain = new int[20][2];
			
			N = Integer.parseInt(br.readLine());
			
			grid = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			idx=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && grid[i][j]!=0) {
						bfs(i,j);
						int r = getRowCol(i, j, 1, 4);
						int c = getRowCol(i, j, 4, 3);
						matrix[idx][0] = r;
						matrix[idx][1] = c;
						idx++;
					}
				}
			}
			
			
//			for(int i=0; i<idx; i++) {
//				for(int j=0; j<2; j++) {
//					System.out.print(matrix[i][j]);
//				}
//				System.out.println();
//			}
			
			
			
			// 행열 정렬
			int start = -1;
			for(int i=0; i<idx; i++) {
				boolean flag = true;
				
				for(int j=0; j<idx; j++) {
					if(matrix[i][0] == matrix[j][1]) {
						flag  = false;
						break;
					}
				}
				
				if(flag) {
					start = i;
					break;
				}
				
			}
			
			chain[0][0] = matrix[start][0];
			chain[0][1] = matrix[start][1];
			
			int current = matrix[start][1];
		
			for(int k=1; k<idx; k++) {
				for(int x=0; x<idx; x++) {
					if(matrix[x][0] == current) {
						current = matrix[x][1];
						chain[k][0] = matrix[x][0];
						chain[k][1] = matrix[x][1];
						break;
					}
				}
			}
			
			
			// dp 
			dp = new int[idx][idx];
			
			for(int i=0; i<idx; i++) {
				for(int j=0; j<idx; j++) {
					dp[i][j]=Integer.MAX_VALUE;
				}
			}
			
			for(int i=0; i<idx; i++) {
				for(int j=0; j<idx; j++) {
					if(i==j) dp[i][j]=0;
				}
			}
			
			for(int len=2; len<=idx; len++) {
				for(int i=0; i<=idx-len; i++) {
					int j = i + len - 1;
					
					for(int k=i; k<j; k++) {
						int cost = dp[i][k] + dp[k+1][j] + chain[i][0] * chain[k][1] * chain[j][1];
						dp[i][j] = Math.min(dp[i][j], cost);
					}
				}
			}
			
			System.out.printf("#%d %d\n", t, dp[0][idx-1]);
		}
		
	}
}
