import java.io.*;
import java.util.*;

public class Main {


	static int ans;
	
	static int N;
	static int M;
	
	static int taxiR, taxiC;
	static int fuel;
	
	static int[][] map;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Person implements Comparable<Person>{
		int pr;
		int pc;
		int er;
		int ec;
		int dist; 
		
		public Person(int pr, int pc, int er, int ec, int dist) {
			this.pr = pr;
			this.pc = pc;
			this.er = er;
			this.ec = ec;
			this.dist = dist;
		}

		@Override
		public int compareTo(Person o) {
			// TODO Auto-generated method stub
			if(this.dist != o.dist) {
				return this.dist-o.dist;
			}
			
			if(this.pr != o.pr) {
				return this.pr - o.pr;
			}
			
			return this.pc - o.pc;
			
		}
	}
	
	static class Node {
		int r;
		int c;
		int dist;
		
		public Node(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	public static int bfs(int sr, int sc, int er, int ec) {
		boolean[][] visited = new boolean[N][N];
		Queue<Node> q = new ArrayDeque<>();
		
		q.add(new Node(sr,sc,0));
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if(cur.r == er && cur.c == ec) {
				return cur.dist;
			}
			
			for(int d=0; d<4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr<0||nr>=N||nc<0||nc>=N) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc]==1) continue;
				
				q.add(new Node(nr,nc,cur.dist+1));
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
	public static void main(String[] args)throws Exception {

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxiR = Integer.parseInt(st.nextToken())-1;
		taxiC = Integer.parseInt(st.nextToken())-1;
		
		
		PriorityQueue<Person> pq = new PriorityQueue<>();
		
		for(int i=0; i<M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int pr = Integer.parseInt(st.nextToken())-1;
			int pc = Integer.parseInt(st.nextToken())-1;
			int er = Integer.parseInt(st.nextToken())-1;
			int ec = Integer.parseInt(st.nextToken())-1;
			
			int dist = bfs(taxiR, taxiC, pr, pc);
			if(dist!=-1) {
				pq.add(new Person(pr, pc, er, ec, dist));
			} else {
				pq.add(new Person(pr, pc, er, ec, Integer.MAX_VALUE));
			}
			
			
		}
		
		boolean flag = true;
		while(!pq.isEmpty()) {
			
			Person cur = pq.poll();
			
			if(cur.dist > fuel) {
				ans = -1;
				flag = false;
				break;
			}
			
			fuel -= cur.dist;
			taxiR = cur.pr;
			taxiC = cur.pc;
			
			int dist = bfs(taxiR, taxiC, cur.er, cur.ec);
			
			// 도착지로 갈수 없는 경우 ? 
			if(dist == -1) {
				ans = -1;
				flag = false;
				break;
			} else if ( dist > fuel) { // 도착지에 가다가 가스가 떨어지는 경우 
				ans = -1;
				flag = false;
				break;
			}
			
			taxiR = cur.er;
			taxiC = cur.ec;
			fuel -= dist;
			fuel += dist*2;
			
			int size = pq.size();
			PriorityQueue<Person> tmp = new PriorityQueue<>();
			
			for(int i=0; i<size; i++) {
				Person p = pq.poll();
				int tdist = bfs(taxiR, taxiC, p.pr, p.pc);
				if(tdist == -1) {
					p.dist = Integer.MAX_VALUE;
				} else {
					p.dist = tdist;
				}
				
				tmp.add(new Person(p.pr, p.pc, p.er, p.ec, p.dist));
			}
			
			pq = tmp;
			
		
			
		}
		
		if(flag) {
			ans = fuel;
		}
		
		System.out.println(ans);
		
		
		
	}
}
