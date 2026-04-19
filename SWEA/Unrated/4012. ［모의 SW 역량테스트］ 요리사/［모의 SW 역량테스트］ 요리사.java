import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[][] S;
	static int[] sel;
	static int finalMinAbs;
	static boolean[] isSelectA;
	
	static int sumA;
	static int sumB;
	
	public static void comb(int m, int start) {
		
		
		if(m == N/2) {
			int taste = calculate();
			finalMinAbs = Math.min(finalMinAbs, taste);
			return;
		}
		
		for(int i=start; i<N; i++) {
			
			isSelectA[i] = true;
			comb(m+1, i+1);
			isSelectA[i] = false;
			
		}
		
	}
	
	public static int calculate() {
		sumA = 0;
		sumB = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(i==j) continue;
				
				if(isSelectA[i]&&isSelectA[j]) {
					sumA += S[i][j];
				}
				
				if(!isSelectA[i]&&!isSelectA[j]) {
					sumB += S[i][j];
				}
				
			}
		}
		
		return Math.abs(sumA-sumB);
		
		
	}
	
	public static void main(String[] args) throws Exception{
		
//		BufferedReader br = new BufferedReader(new FileReader("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			S = new int[N][N];
			
			
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			isSelectA = new boolean[N];
			finalMinAbs = Integer.MAX_VALUE;
			
			comb(0, 0);
			
			System.out.printf("#%d %d\n",tc, finalMinAbs);
		}

	}

}
