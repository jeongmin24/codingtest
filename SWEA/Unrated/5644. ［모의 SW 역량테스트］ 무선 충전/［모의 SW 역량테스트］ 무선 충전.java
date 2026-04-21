import java.util.*;
import java.io.*;

public class Solution {
	
	static int M;
	static int A;
	static int[][] BC;
	static int[][] P;
	static int[][] moves;
	static int maxCharge;
	
	static int[][] dirs = { {0,0}, {0,-1}, {1,0}, {0,1}, {-1,0} }; // x,y 기준 이동X / 상 / 우 / 하 / 좌
	

	public static void main(String[] args) throws Exception{

//		BufferedReader br = new BufferedReader(new FileReader("res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc = 1 ; tc <= T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			BC = new int[A+1][4]; // 0:x 1:y 2:c 3:p
			moves = new int[2][M];
			
			// 1. static은 테스트 케이스마다 초기화 되어야 함 
			// 왠만하면 전역에서 초기화는 하지말자 
			maxCharge = 0;
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					moves[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine());
				BC[i][0] = Integer.parseInt(st.nextToken());
				BC[i][1] = Integer.parseInt(st.nextToken());
				BC[i][2] = Integer.parseInt(st.nextToken());
				BC[i][3] = Integer.parseInt(st.nextToken());
				
			}
			
			BC[A][0] = 0;
			BC[A][1] = 0;
			BC[A][2] = 0;
			BC[A][3] = 0; // A칸의 3(p)는 0 ( 선택하지 않음 )
			
			
			int time = 0;
			
			P = new int[2][2];
			
			// 2. 입력받은 BC는 1번 인덱스 기준의 좌표를 가지고 있음 
			// 초기 위치를 (1,1) (10,10)으로 맞추거나 BC의 X,Y 좌표를 입력받을때 -1을 해야함 
			P[0][0] = 1; // 0:x 1:y
			P[0][1] = 1;
			P[1][0] = 10;
			P[1][1] = 10;
			
			while(true) {
				
				int sum = 0, max = 0;
				// 1. 현재 자리에서 가장 큰 BC 조합 찾기
				// 선택하지 않은 경우 처리 
				for(int i=0; i<=A; i++) {
					for(int j=0; j<=A; j++) {
						
						boolean validA = false, validB = false;
						int sumA = 0;
						int sumB = 0;
						int distA = Math.abs(BC[i][0]-P[0][0]) + Math.abs(BC[i][1] - P[0][1]);
						int distB = Math.abs(BC[j][0]-P[1][0]) + Math.abs(BC[j][1] - P[1][1]);
						if(i==A || distA <= BC[i][2]) validA = true;
						
						if(j==A || distB <= BC[j][2]) validB = true;
						
						if(validA && validB) {
							
							if(i==j && i!=A && j!=A) {
								sumA = BC[i][3] / 2;
								sumB = BC[j][3] / 2;
							}  else {
							
								sumA = BC[i][3];
								sumB = BC[j][3];
								
							}
							
							sum = sumA + sumB;
							max = Math.max(sum, max);
							
						}
						
					}
				}
				
				// 2. maxSum 구하고 maxCharge에 누적
				maxCharge += max;
				
				// 3. time == M 이면 break
				if(time == M) break;
				
				// 4. 이동
				P[0][0] = P[0][0] + dirs[moves[0][time]][0];
				P[0][1] = P[0][1] + dirs[moves[0][time]][1];
				
				P[1][0] = P[1][0] + dirs[moves[1][time]][0];
				P[1][1] = P[1][1] + dirs[moves[1][time]][1];
				
				// 5. time++ 
				time++;
				
			}
			
			System.out.printf("#%d %d\n", tc, maxCharge);
		}
	}

}
