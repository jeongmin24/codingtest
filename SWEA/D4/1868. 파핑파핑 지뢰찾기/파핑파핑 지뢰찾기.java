import java.util.*;
import java.io.*;

public class Solution {
	
	static char[][] map;
	static int[][] cnt;
	static int N;
	static boolean[][] visited;
	
	static int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	public static void main(String[] args) throws Exception {
		
//		BufferedReader br = new BufferedReader(new FileReader("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			
			cnt = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int tmp = 0;
					for(int d=0; d<8; d++) {
						if(!isWall(i+dirs[d][0], j+dirs[d][1]) && map[i][j] == '.' && map[i+dirs[d][0]][j+dirs[d][1]]=='*') {
							tmp ++;
						}
					}
					cnt[i][j] = tmp;
				}
			}
			
			visited = new boolean[N][N];
			
			int click = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] == '.' && cnt[i][j] == 0) {
						bfs(i, j);
						click++;
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j] && map[i][j] == '.') {
						click++;
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, click);
			
		}
		
	}
	
	public static void bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {sr,sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			
			int[] cur = q.poll();
			
			for(int d=0; d<8; d++) {
				int nr = cur[0] + dirs[d][0];
				int nc = cur[1] + dirs[d][1];
				
				if(isWall(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				
				if(cnt[nr][nc] == 0) {
					q.add(new int[] {nr,nc});
					visited[nr][nc] = true;
				} else {
					visited[nr][nc] = true;
				}
			}
			
		}
	}
	
	public static boolean isWall(int r, int c) {
		return (r<0 || r>=N || c<0 || c>=N);
	}

}
