import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[][] num;
	static char[][] array;
	static boolean[][] visited;
	
	static int[][] dir = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1} };
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("res/sample_input.txt"));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			num = new int[N][N];
			visited = new boolean[N][N];
			array = new char[N][N];
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					array[i][j] = line.charAt(j);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					int ncnt = 0;
					for(int d=0; d<8; d++) {
						// 1. 지뢰일때만 표시 (.으로 착각함) 
						if(!isWall(i+dir[d][0], j+dir[d][1]) && array[i+dir[d][0]][j+dir[d][1]] == '*') {
							ncnt++;
						}
					}
					num[i][j] = ncnt;
					
				}
			}
			
			int network = 0;
			
			// 2. 숫자가 0일때만 8방향 확장 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(array[i][j]=='.'&& num[i][j]==0 && !visited[i][j]) {
						bfs(i, j);
						network++;
					}
				}
			}
			
			// 3. 숫자가 0이 아닐때는 그 칸만 클릭 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(array[i][j]=='.'&&!visited[i][j]) {
						visited[i][j] = true;
						network++;
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, network);
		}
	}
	
	public static boolean isWall(int r, int c) {
		return (r<0||r>=N||c<0||c>=N);
	}
	
	public static void bfs(int sr, int sc) {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {sr,sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<8; d++) {
				int nr = cur[0] + dir[d][0];
				int nc = cur[1] + dir[d][1];
				
				if(isWall(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				if(array[nr][nc] == '*') continue;
				
				// 0이면 8방향 처리 
				if(num[nr][nc] == 0) {
					visited[nr][nc] = true;
					q.add(new int[] {nr,nc});
				} else {
					// 0이 아니면 그 칸만 방문 
					visited[nr][nc] = true;
				}
			}
		}
		
	}

}
