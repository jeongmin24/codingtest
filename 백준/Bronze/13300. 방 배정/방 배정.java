import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int K;
	static int[][] room;
	
	public static void main(String[] args)throws Exception {

		room = new int[2][6];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken())-1;
			
			room[S][Y]++;
		}
		
		int count = 0;
		for(int i=0; i<2; i++) {
			for(int j=0; j<6; j++) {
				int n = room[i][j];
				if(n>0) {
					count += n / K;
					
					if(n%K>0) {
						count++;
					}
				}
			}
		}
		
		System.out.println(count);
	
	}
}
