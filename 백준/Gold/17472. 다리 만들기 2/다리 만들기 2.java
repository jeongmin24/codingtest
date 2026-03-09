import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int index;
        int key;


        public Node(int index, int key) {
            this.key = key;
            this.index = index;
        }


        @Override
        public int compareTo(Node o) {
            return this.key - o.key;
        }
    }

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static int N;
    static int M;
    static int[][] map;
    static List<List<int[]>> islandList;
    static int[][] dist;
    static boolean[][] visited;
    static int idx;
    static int[] key;
    static int[] parent;
    static boolean[] used; // mst_prim 체크

    static int ans;


    public static boolean isWall(int r, int c) {
        return (r<0||r>=N||c<0||c>=M);
    }

    public static void mst_prim(int r) {
        for(int i=0; i<islandList.size(); i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

//        used[r] = true;
        key[r] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

//        for(int i=0; i<islandList.size(); i++) {
//            pq.add(new Node(i, key[i]));
//        }
        
        pq.add(new Node(r, key[r]));

        int totalWeight = 0;
        int connectedCount = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            // 이미 MST에 포함된 섬이면 패스
            if(used[cur.index]) continue;

            used[cur.index] = true;
            totalWeight += cur.key;
            connectedCount++;

            // cur.index 번 섬에서 갈수 있는 다른 섬 i 탐색
            for(int i=0; i<islandList.size(); i++) {
                // 아직 mst 팀이 아니고 다리 연결이 가능하며 기존 보다 짧은 다리
                if(!used[i] && dist[cur.index][i] != Integer.MAX_VALUE) {
                    if (dist[cur.index][i] < key[i]) {
                        key[i] = dist[cur.index][i];
                        parent[i] = cur.index;
                        pq.add(new Node(i, key[i]));
                    }
                }

            }
        }

        if(connectedCount == islandList.size()) {
            ans = totalWeight;
        } else {
            ans = -1;
        }

    }

    public static void bfs(int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();

        islandList.get(idx).add(new int[]{sr,sc});
        visited[sr][sc] = true;
        map[sr][sc] = idx+1;
        q.offer(new int[]{sr,sc});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for(int d=0; d<4; d++) {
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(isWall(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]==0) continue;

                q.offer(new int[]{nr,nc});
                visited[nr][nc] = true;
                map[nr][nc] = idx+1;
                islandList.get(idx).add(new int[]{nr,nc});
            }
        }
    }

    public static void connect(int r, int c, int d, int i) {
        int cnt = 0;

        int tr = r;
        int tc = c;
        while(true) {

            tr += dr[d];
            tc += dc[d];

            if(isWall(tr,tc)) return;
            if(map[tr][tc]!=0) { // 섬을 만나면 무조건 리턴
                int target = map[tr][tc]-1;
                if(target != i) { // 내 섬이 아니면 dist 처리
                    if(cnt>=2) { // 문제 조건: 거리 2이상
                        dist[i][target] = Math.min(cnt, dist[i][target]);
                    }
                }
                return; // 무조건 리턴
            }

            cnt++;
        }
    }

    public static void fillDist(int i) {

        // i행 모든 지점에서 출발
        for(int x=0; x<islandList.get(i).size(); x++) {
            int[] cur = islandList.get(i).get(x);
            int r = cur[0];
            int c = cur[1];

            for(int d=0; d<4; d++) {
                connect(r,c,d,i); // dist[i][j] 갱신
            }
        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        islandList = new ArrayList<>();
        visited = new boolean[N][M];

        idx=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j]&&map[i][j]==1) {

                    islandList.add(new ArrayList<>());
                    bfs(i, j);
                    // bfs에서 arrayList에 (r,c) 추가
                    idx++;
                }
            }
        }

//        System.out.println(idx);
//        for(int i=0; i<islandList.size(); i++) {
//            System.out.print((i+1)+"번 섬 좌표들:");
//            List<int[]> points = islandList.get(i);
//            for(int[] p: points) {
//                System.out.print(Arrays.toString(p)+" ");
//            }
//            System.out.println();
//        }


        dist = new int[islandList.size()][islandList.size()];

        for(int i=0; i<islandList.size(); i++) {
            for(int j=0; j<islandList.size(); j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<islandList.size(); i++) {

            for(int j=0; j<islandList.size(); j++) {
                if(i==j) {
                    dist[i][j]=0;
                    continue;
                }
                // dist[i][j] 채우기 i에서 출발했을때 j까지 도착하는 min 거리
                // dist[i] 채우기 -> i에서 출발했을때 닿는 모든 거리저장
                fillDist(i);
            }

        }

//        for(int i=0; i<islandList.size(); i++) {
//
//            for(int j=0; j<islandList.size(); j++) {
//                // dist[i][j] 채우기 i에서 출발했을때 j까지 도착하는 min 거리
//                System.out.print(dist[i][j]+" ");
//            }
//            System.out.println();
//        }
//
//        for(int i=0; i<N; i++) {
//            for(int j=0; j<M; j++) {
//                System.out.printf("%d ", map[i][j]);
//            }
//            System.out.println();
//        }

        visited = new boolean[islandList.size()][islandList.size()];
        key = new int[islandList.size()];
        parent = new int[islandList.size()];
        used = new boolean[islandList.size()];
        ans = -1;
        // 0에서 시작
        mst_prim(0);

        System.out.printf("%d", ans);

    }
}