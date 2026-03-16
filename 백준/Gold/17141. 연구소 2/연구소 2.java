import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r;
        int c;
        int time;

        public Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    static int N;
    static int[][] grid;

    static int M;

    static int cnt;
    static int[][] arr;

    static boolean[][] visited;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int time;
    static int[][] copy;

    static int minTime;

    static boolean flag;

    public static void virusSim() {

        Queue<Node> q = new ArrayDeque<>();
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(grid[i][j] == -1) {
                    // 시작 바이러스 visited 처리
                    visited[i][j] = true;
                    q.offer(new Node(i,j,0));
                }
            }
        }

        // time은 이번 조합의 빈칸 그룹이 감염되는 시간
        time=0;
        while(!q.isEmpty()) {

            Node cur = q.poll();
            time = cur.time;

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (visited[nr][nc]) continue;
                if(copy[nr][nc] == 1 ) continue;

                visited[nr][nc] = true;
                q.offer(new Node(nr, nc, cur.time+1));
            }

        }
    }


    public static void dfs(int m, int start) {

        if(m == M) {

            copy = new int[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    copy[i][j] = grid[i][j];
                }
            }

            flag = true;
            // 바이러스 확산
            virusSim();

            // 빈칸 확인
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    // 벽(1)이 아니고, 시작 바이러스(-1)도 아닌데 방문을 안 했다면 실패입니다.
                    // 즉, 원래 0이었거나 선택 안 된 2인 칸을 모두 체크해야 합니다.
                    if(grid[i][j] != 1 && !visited[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if(!flag) break;
            }

            if(flag) {
                minTime = Math.min(minTime, time);
            }
            return;
        }

        for(int i=start; i<cnt; i++) {
            int r = arr[i][0];
            int c = arr[i][1];

            grid[r][c] = -1;
            dfs(m+1, i+1);
            grid[r][c] = 2;
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(grid[i][j] == 2) {
                    cnt++;
                }
            }
        }

        arr = new int[cnt][2]; // 0:r 1:c
        int idx=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(grid[i][j] == 2) {
                    arr[idx][0] = i;
                    arr[idx][1] = j;
                    idx++;
                }
            }
        }

        minTime = Integer.MAX_VALUE;

        // M개 선택하기
        dfs(0, 0);

        if(minTime != Integer.MAX_VALUE) {
            System.out.println(minTime);
        } else {
            System.out.println(-1);
        }

    }

}