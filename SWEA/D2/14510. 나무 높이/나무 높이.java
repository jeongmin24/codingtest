import java.util.*;
import java.io.*;

public class Solution {
	
	static int N;
	static int[] height;
	static int maxH;

	public static void main(String[] args) throws Exception{

//		BufferedReader br = new BufferedReader(new FileReader("res/나무의키.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int tc = 1; tc <= T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			height = new int[N];
			maxH = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
				if(maxH < height[i]) {
					maxH  = height[i];
				}
			}
			
			int even = 0;
			int odd = 0;
			
//			odd, even 구하기
			for(int h=0; h<N; h++) {
				int diff = maxH - height[h];
				even += diff/2;
				odd += diff%2;
			}
			
//			even > odd + 1일때 even--; odd+2;
			while(even > odd+1) {
				even--;
				odd += 2;
			}
			
//			답 구하기
			int ans = 0;
			if(odd > even) {
				ans = odd * 2 -1;
			} else if(odd < even) {
				ans = even * 2;
			} else if(odd == even) {
				ans = odd + even;
			}
			
			System.out.printf("#%d %d\n", tc, ans);
			
			
		}

	}

}
