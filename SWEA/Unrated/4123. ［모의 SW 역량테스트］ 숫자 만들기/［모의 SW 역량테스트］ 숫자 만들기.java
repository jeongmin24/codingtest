import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int[] num;
	static int[] op;
	
	static int max;
	static int min;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st; 
		
		for(int tc = 1; tc<=T ; tc++) {
			
			N = Integer.parseInt(br.readLine());
			num = new int[N];
			op = new int[4]; // 0:+ 1:- 2:* 3:/
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			subset(0, num[0]);
			
			System.out.printf("#%d %d\n", tc, max-min);
			
		}
	}
	
	public static void subset(int m, int acc) {
		
		if(m==N-1) {
			
			max = Math.max(acc, max);
			min = Math.min(acc, min);
			return;
			
		}
		
		
		for(int i=0; i<4; i++) {
			if(op[i]>0) {
				op[i]--;
				subset(m+1, cal(acc, num[m+1], i));
				op[i]++;
			}
		}
	}
	
	public static int cal(int a, int b, int op) {
		if(op==0) {
			return a+b;
		} else if(op==1) {
			return a-b;
		} else if(op==2) {
			return a*b;
		} else {
			return a/b;
		}
	}

}
