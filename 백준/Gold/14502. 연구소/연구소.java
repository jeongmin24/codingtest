import java.io.*;
import java.util.*;
/**
벽 3개를 선택시 
BFS로 바이러스 확산 시뮬레이션을 다시 해야함

현재 코드에서는 이전 BFS 방문 기록이 그대로 남음 + grid 변경이 생기면 그대로 반영됨
-> 다음 조합에서 이미 방문했다고 판단하는 문제 + 기존의 grid가 바뀜 
*/
public class Main {


    static int N;
    static int M;
    static int[][] grid;

    static int safeG;

    static int[][] arr;
    static int cnt;

    static boolean[][] visited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static int[][] copy;

    public static void copyArray() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copy[i][j] = grid[i][j];
            }
        }
    }

    public static void bfs(int sr, int sc) {

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr,sc});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {

            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0||nr>=N||nc<0||nc>=M) continue;
                if(visited[nr][nc]) continue;
                if(copy[nr][nc] != 0) continue;

                copy[nr][nc] = 2;
                visited[nr][nc] = true;
                q.offer(new int[]{nr,nc});
            }

        }

    }

    public static void dfs(int m, int start) {

        if(m==3) {
            // 바이러스 전파하기
            copy = new int[N][M];
            visited = new boolean[N][M];
            copyArray();

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(copy[i][j] == 2) {
                        bfs(i,j); // 2 주변의 0을 2로 바꾸기
                    }
                }
            }

            // copy 배열의 0 개수 카운트
            int sum = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(copy[i][j] == 0) {
                        sum++;
                    }
                }
            }

            safeG = Math.max(sum, safeG);

            return;

        }

        for(int i=start; i<cnt; i++) {


            grid[arr[i][0]][arr[i][1]] = 1;
            dfs(m+1, i+1);
            grid[arr[i][0]][arr[i][1]] = 0;

        }

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(grid[i][j] == 0) {
                    cnt++;
                }
            }
        }

        arr = new int[cnt][2]; // 0:r 1:c

        int idx=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(grid[i][j] == 0) {
                    arr[idx][0] = i;
                    arr[idx][1] = j;
                    idx++;
                }
            }
        }

        safeG = Integer.MIN_VALUE;

        dfs(0,0);

        System.out.println(safeG);
    }

}