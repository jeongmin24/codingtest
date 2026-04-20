import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[] weight;
	static boolean[] visited;
	static int[] selected;
	
	static int count;

	public static void main(String[] args) throws Exception{
		
//		BufferedReader br = new BufferedReader(new FileReader("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			weight = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			visited = new boolean[N];
			selected = new int[N];
			count = 0;
			
			perm(0);
			
			System.out.printf("#%d %d\n", tc, count);
		}

	}
	
	public static void perm(int m) {
		
		if(m==N) {
			
			// sel -> subset
			subset(0, 0, 0);
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			
			if(!visited[i]) {
				selected[m] = i;
				visited[i] = true;
				perm(m+1);
				visited[i] = false;
			}
		}
		
	}
	
	public static void subset(int m, int leftW, int rightW) {
		
		if(rightW < leftW) return;
		
		if(m==N) {
			count++;
			return;
		}
		
		// m번째 selected 를 왼쪽에 더하는 경우
		subset(m+1, leftW+weight[selected[m]], rightW);
		
		// m번째 selected를 오른쪽에 더하는 경우 
		subset(m+1, leftW, rightW+weight[selected[m]]);
		
	}

}
