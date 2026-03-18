import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[][] city;

	static int[][] cArr;
	static int chickenArrCnt;
	static int[][] hArr;
	static int homeArrCnt;
	
	static int[][] sel;
	static int minChickenDist;
	
	public static void comb(int m, int start) {
		
		if(m==M) {
			// 모든 arrHome
			// arrHome[0] -> sel 모든 경우 dist 구하고
			// 최소 dist 갱신
			int curCityDist = 0; // 조합 하나의 도시의치킨거리 
			for(int i=0; i<homeArrCnt; i++) {
				int curDist = Integer.MAX_VALUE;
				for(int j=0; j<M; j++) {
					int dist = Math.abs(hArr[i][0]-sel[j][0])+Math.abs(hArr[i][1]-sel[j][1]);
					curDist = Math.min(curDist, dist); //집에서 가장 가까운 치킨집 거리 
				}
				curCityDist += curDist;
			}
			
			minChickenDist = Math.min(curCityDist,minChickenDist);
			return;
		}
		
		for(int i=start; i<chickenArrCnt; i++) {
			
			sel[m][0] = cArr[i][0];
			sel[m][1] = cArr[i][1];
			
			comb(m+1, i+1);
			
		}
		
	}
	
	public static void main(String[] args)throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		city = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		chickenArrCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(city[i][j] == 2) {
					chickenArrCnt++;
				}
			}
		}
		
		cArr = new int[chickenArrCnt][2]; //0:r 1:c
		
		int cIdx = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(city[i][j] == 2) {
					cArr[cIdx][0] = i;
					cArr[cIdx][1] = j;
					cIdx++;
				}
			}
		}
		
		homeArrCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(city[i][j] == 1) {
					homeArrCnt++;
				}
			}
		}
		hArr = new int[homeArrCnt][2];
		
		int hIdx = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(city[i][j] == 1) {
					hArr[hIdx][0] = i;
					hArr[hIdx][1] = j;
					hIdx++;
				}
			}
		}
				
		sel = new int[M][2];
		minChickenDist = Integer.MAX_VALUE;
		comb(0,0);
		
		System.out.println(minChickenDist);
		
		
	}

}
