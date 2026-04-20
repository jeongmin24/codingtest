import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[] height;
	static int B;
	
	static int minTopHeight;

	public static void main(String[] args) throws Exception{
		
//		BufferedReader br = new BufferedReader(new FileReader("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int tc = 1; tc <= T; tc ++ ) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			height = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			minTopHeight = Integer.MAX_VALUE;
			subset(0, 0);
			
			System.out.printf("#%d %d\n", tc, minTopHeight-B);
			
		}
		
	}
	
	public static void subset(int m, int sum) {
		
		if(sum>=B) {
			minTopHeight = Math.min(minTopHeight, sum);
		}
		
		if(m==N) {
			if(sum>=B) {
				minTopHeight = Math.min(minTopHeight, sum);
			}
			
			return;
		}
		
		// m번째 height 선택
		subset(m+1, sum+height[m]);
		
		// m번째 height 미선택
		subset(m+1, sum);
		
	}

}
