
import java.io.*;
import java.util.*;

public class Main {
	
	static int K;
	static int[] S;
	static int[] sel;
	
	public static void comb(int m, int start) {
		if(m==6) {
			for(int i=0; i<6; i++) {
				System.out.printf("%d ", sel[i]);
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<K; i++) {
			
			sel[m] = S[i];
			comb(m+1, i+1);
			
		}
	}
	public static void main(String[] args) throws Exception {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			
			K = Integer.parseInt(st.nextToken());
			if(K==0) {
				
				return;
			}
			S = new int[K];
			sel = new int[6];
			for(int k=0; k<K; k++) {
			
				S[k] = Integer.parseInt(st.nextToken());
			}
			
			// S 집합에서 6개 조합 출력하기
			comb(0, 0);
			System.out.println();
		}
		
	}

}
