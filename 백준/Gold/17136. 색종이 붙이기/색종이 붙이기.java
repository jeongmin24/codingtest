

import java.util.*;
import java.io.*;

public class Main {

	static int[][] grid;
	static int minCnt;
	static int[] count;
	static int[][] arr;
	static int index;
	static boolean[][] used;
	
	public static boolean isWall(int r, int c) {
		return (r<0||r>=10||c<0||c>=10);
	}
	
	public static void solve(int idx, int curCnt) {
		
		if(curCnt >= minCnt) return;
		
		if(idx==index) {
			
			minCnt = Math.min(curCnt, minCnt);
			return;
		}
		
		int r = arr[idx][0];
		int c = arr[idx][1];
		
		// 덮은 경우
		if(used[r][c]) {
			
			solve(idx+1, curCnt);
			
		} else { // 안덮은 경우 
			
			for(int i=0; i<5; i++) {
				if(valid(r,c,i) && count[i]>0) {
					count[i]--;
					check(r,c,i,true);
					solve(idx+1, curCnt+1);
					count[i]++;
					check(r,c,i,false);
				}
			}
		}
	}
	
	public static void check(int r, int c, int val, boolean flag) {
		for(int i=r; i<=r+val; i++) {
			for(int j=c; j<=c+val; j++) {
				used[i][j] = flag;
			}
		}
	}
	
	public static boolean valid(int r, int c, int val) {
		
		// val = 0 1 2 3 4
		for(int i=r; i<=r+val; i++) {
			for(int j=c; j<=c+val; j++) {
				
				if(isWall(i,j)) return false;
				if(used[i][j]) return false;
				if(grid[i][j]!=1) return false;
			}
		}
		
		return true;
		
	}
	
	
	public static void main(String[] args)throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		grid = new int[10][10];
		used = new boolean[10][10];
		
		for(int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		index = 0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(grid[i][j]==1) index++;
			}
		}
		
		arr = new int[index][2]; // 0:r 1:c
		
		int tmp=0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(grid[i][j]==1) {
					arr[tmp][0] = i;
					arr[tmp][1] = j;
					tmp++;
				}
			}
		}
		
		count = new int[5];
		for(int i=0; i<5; i++) {
			count[i]=5;
		}
		
		minCnt = Integer.MAX_VALUE;
		
		solve(0,0);
		
		
		System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
		
	}

}
