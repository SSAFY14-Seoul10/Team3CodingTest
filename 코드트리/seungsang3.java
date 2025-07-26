import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int[] dx = {0,0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            map[i] = str.toCharArray();
        }
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int test_case = 0; test_case < T; test_case++) {
            int[][][] visited = new int[6][n][n];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(visited[i][j], Integer.MAX_VALUE);
                }
            }

            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken())-1;
            int sy = Integer.parseInt(st.nextToken())-1;
            int ex = Integer.parseInt(st.nextToken())-1;
            int ey = Integer.parseInt(st.nextToken())-1;

            PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[2]-b[2]);
            queue.add(new int[]{sx,sy,0,1}); // 원래 time은 안넣고 그냥 일반 queue.size로 for문 돌렸는데, 효율성이 별로 안좋아서 이렇게 변경
            visited[1][sx][sy] = 0;

            boolean check = false;
            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                int x = now[0];
                int y = now[1];
                int time = now[2];
                int jump = now[3];

                if (x == ex && y == ey) {
                    check = true;
                    System.out.println(time);
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j <= 5; j++) {
                        int nx = x + dx[i] * j;
                        int ny = y + dy[i] * j;

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n) break; 
                        if (map[nx][ny] == 'S') continue;
                        if (map[nx][ny] == '#') break; // 솔직히 if문 '#'에서 break로 최적화하는건 희령님꺼 봤습니다.... 그전에는 그냥 전부 continue 돌렸다는..

                        int current = time;

                        if(jump == j){
                            current++; // 단순 이동만
                        } else if(jump < j){
                            for (int k = jump + 1; k <= j; k++) { // 속도 변경
                                current += k * k;
                            }
                            current++;// 이동
                        } else {
                            current+=2; // 이동 + 속도 줄이기 총 2초
                        }

                        if (visited[j][nx][ny] > current) {
                            visited[j][nx][ny] = current;
                            queue.add(new int[]{nx, ny,current ,j});
                        }
                    }
                }
            }

            if(!check){
                System.out.println(-1);
            }
        }
    }


}


