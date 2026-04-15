import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[] num;
	static int[] op;
	static int[] sel;
	
	static int maxR;
	static int minR;
	
	
	public static void main(String[] args) throws Exception{
		
//		BufferedReader br = new BufferedReader(new FileReader("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			num = new int[N];
			op = new int[4];
			sel = new int[N-1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			maxR = Integer.MIN_VALUE;
			minR = Integer.MAX_VALUE;
			
			comb(0);
			
			System.out.printf("#%d %d\n", tc, maxR-minR);
			
		}
		
		
	}
	
	public static void comb(int m) {
		
		if(m==N-1) {
			
			// 1. 누적값 계산 유의!!
			// 처음 값을 누적값 시작으로 두기 
			int acc = num[0];
			for(int i=0; i<sel.length; i++) {
				acc = op(acc, num[i+1], sel[i]);
			}
			maxR = Math.max(acc, maxR);
			minR = Math.min(acc, minR);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(op[i]>0) {
				sel[m] = i;
				op[i]--;
				comb(m+1);
				op[i]++;
			}
		}
	}
	
	public static int op(int a, int b, int opi) {
		switch (opi) {
		case 0:
			return a+b;
		case 1:
			return a-b;
		case 2:
			return a*b;
		}
		return a/b;
	}

}
