import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] grid;
	static int M;
	
	static int[][] arr;
	static int[][] sel;
	static int cnt;
	static int idx;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static boolean[][] visited;
	static int bfsTime;
	
	static int ansT;
	
	static class Node {
		int r;
		int c;
		int time;
		
		public Node(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	public static void bfs() {
		
		Queue<Node> q = new ArrayDeque<>();
		
		for(int m=0; m<M; m++) {
			q.offer(new Node(sel[m][0], sel[m][1], 0));
			visited[sel[m][0]][sel[m][1]] = true;
		}
		
		bfsTime = 0;
		
		/**
		 * 시간은 모든 칸에 동일하게 1씩 더하며 진행하지만 결과값을 갱신할때는 0을 만났을때만 측정 
		 * */
		while(!q.isEmpty()) {
			
			Node cur = q.poll();
			if(grid[cur.r][cur.c]==0) {
				bfsTime = Math.max(bfsTime, cur.time);
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				if(visited[nr][nc]) continue;
				if(grid[nr][nc] == 1) continue;
				
				q.offer(new Node(nr, nc, cur.time+1));
				visited[nr][nc] = true;
			}
			
		}
		
	}
	
	public static void dfs(int m, int start) {
		
		if(m == M) {
			
			visited = new boolean[N][N];
			bfs();
			
			// 빈칸인데 방문되지 않은 경우 
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(grid[i][j]==0 && !visited[i][j]) {
						
						return;
					}
				}
			}
			
			// 가장 빨리 퍼지는 시간 
			ansT = Math.min(ansT, bfsTime);
			
			return;
		}
		
		for(int i=start; i<cnt; i++) {
			
			sel[m][0] = arr[i][0];
			sel[m][1] = arr[i][1];
			
			dfs(m+1, i+1);
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(grid[i][j]==2) cnt++;
			}
		}
		
		arr = new int[cnt][2]; // 0:r 1:c
		
		idx=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(grid[i][j]==2) {
					arr[idx][0] = i;
					arr[idx][1] = j;
					idx++;
				}
			}
		}
		
		sel = new int[M][2];
		ansT = Integer.MAX_VALUE;
		dfs(0,0);
		
		if(ansT == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ansT);
		}
		
		
	}

}
