import java.io.*;
import java.util.*;

class State {
    // 위치 (r, c), 점프력(k), 현재까지 소요된 시간(cost)
    int r, c, k, cost;
    public State(int r, int c, int k, int cost) {
        this.r = r;
        this.c = c;
        this.k = k;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = Integer.MAX_VALUE / 2;
    static int N, Q;
    static char[][] grid;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int qi = 0; qi < Q; qi++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dijkstra(r1, c1, r2, c2)).append('\n');
        }

        System.out.print(sb);
    }

    static int dijkstra(int sr, int sc, int tr, int tc) {
        // (r,c) 위치에 점프력 k로 도달하는 데 걸리는 최소 시간
        int[][][] dist = new int[N][N][6];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Arrays.fill(dist[i][j], INF);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparing(State -> State.cost));
        dist[sr][sc][1] = 0;
        pq.offer(new State(sr, sc, 1, 0));

        while (!pq.isEmpty()) {
            State now = pq.poll();
            if (now.cost > dist[now.r][now.c][now.k]) continue;
            
            if (now.r == tr && now.c == tc) {
                return now.cost;
            }

            // 점프
            int k = now.k;
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dx[d] * k;
                int nc = now.c + dy[d] * k;
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                // 천적이 사는 돌
                boolean blocked = false;
                for (int step = 1; step <= k; step++) {
                    int rr = now.r + dx[d] * step;
                    int cc = now.c + dy[d] * step;
                    if (grid[rr][cc] == '#') {
                        blocked = true;
                        break;
                    }
                }
                if (blocked) continue;

                // 미끄러운 돌
                if (grid[nr][nc] != '.') continue;
                if (now.cost + 1 < dist[nr][nc][k]) {
                    dist[nr][nc][k] = now.cost + 1;
                    pq.offer(new State(nr, nc, k, dist[nr][nc][k]));
                }
            }

            // 점프력 증가
            if (k >= 1 && k <= 4) {
                int nk = k + 1;
                int ncst = now.cost + nk * nk;
                if (ncst < dist[now.r][now.c][nk]) {
                    dist[now.r][now.c][nk] = ncst;
                    pq.offer(new State(now.r, now.c, nk, ncst));
                }
            }

            // 점프력 감소
            if (k >= 2) {
                for (int nk = 1; nk < k; nk++) {
                    int ncst = now.cost + 1;
                    if (ncst < dist[now.r][now.c][nk]) {
                        dist[now.r][now.c][nk] = ncst;
                        pq.offer(new State(now.r, now.c, nk, ncst));
                    }
                }
            }
        }
        // 도착 불가능
        return -1;
    }
}
