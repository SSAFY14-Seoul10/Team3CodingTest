import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, Q;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class State implements Comparable<State> {
        int time, r, c, k;

        State(int time, int r, int c, int k) {
            this.time = time;
            this.r = r;
            this.c = c;
            this.k = k;
        }

        public int compareTo(State o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static int dijkstra(int sr, int sc, int er, int ec) {
        if (map[sr][sc] == 'S' || map[sr][sc] == '#') return -1;

        int[][][] dist = new int[N][N][6];
        for (int[][] mat : dist)
            for (int[] row : mat)
                Arrays.fill(row, INF);

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[sr][sc][1] = 0;
        pq.offer(new State(0, sr, sc, 1));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int time = cur.time, r = cur.r, c = cur.c, k = cur.k;

            // 방문한 적 있고 더 빠르게 왔으면 무시
            if (time > dist[r][c][k]) continue;

            if (r == er && c == ec) return time;

            // 1. 점프 (시간 +1)
            for (int d = 0; d < 4; d++) {
                for (int j = 1; j <= 5; j++) {
                    int nr = r + dr[d] * j;
                    int nc = c + dc[d] * j;

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
                    if (map[nr][nc] == 'S') continue;
                    if (map[nr][nc] == '#') break;

                    // 경로 중간에 #가 있는지 확인
                    boolean blocked = false;
                    for (int step = 1; step <= j; step++) {
                        int ir = r + dr[d] * step;
                        int ic = c + dc[d] * step;
                        if (ir < 0 || ir >= N || ic < 0 || ic >= N || map[ir][ic] == '#') {
                            blocked = true;
                            break;
                        }
                    }
                    if (blocked) continue;

                    // 점프력 조정 비용 계산
                    int adjust = 0;
                    if (j > k) {
                        for (int m = k + 1; m <= j; m++) {
                            adjust += m * m; // 점프력 증가는 k^2초
                        }
                    } else if (j < k) {
                        adjust = 1; // 점프력 감소는 1초
                    }

                    int newTime = time + 1 + adjust;

                    if (newTime < dist[nr][nc][j]) {
                        dist[nr][nc][j] = newTime;
                        pq.offer(new State(newTime, nr, nc, j));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            if (r1 == r2 && c1 == c2) {
                sb.append("0\n");
            } else {
                int result = dijkstra(r1, c1, r2, c2);
                sb.append(result).append("\n");
            }
        }

        System.out.print(sb);
    }
}
