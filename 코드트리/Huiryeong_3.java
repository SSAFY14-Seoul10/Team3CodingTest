import java.util.*;

public class Huiryeong_3 {
    static final int INF = Integer.MAX_VALUE;
    static int n;
    static char[][] arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = Integer.parseInt(sc.nextLine());
        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLine().toCharArray();
        }

        int q = Integer.parseInt(sc.nextLine());
        for (int t = 0; t < q; t++) {
            int[][][] visited = new int[6][n][n];
            for (int[][] temp : visited) {
                for (int[] row : temp) {
                    Arrays.fill(row, INF);
                }
            }

            int sx = sc.nextInt() - 1;
            int sy = sc.nextInt() - 1;
            int ex = sc.nextInt() - 1;
            int ey = sc.nextInt() - 1;

            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            queue.add(new int[]{0,sx,sy,1});
            visited[1][sx][sy] = 0;

            boolean flag = false;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int time = cur[0];
                int x = cur[1];
                int y = cur[2];
                int jump = cur[3];

                if (x == ex && y == ey) {
                    flag = true;
                    System.out.println(time);
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 1; j <= 5; j++) {
                        int nx = x + dx[i] * j;
                        int ny = y + dy[i] * j;

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
                        if (arr[nx][ny] == 'S') continue;
                        if (arr[nx][ny] == '#') break;

                        int possibleTime = time + 1;
                        if (jump <= j) {
                            for (int k = jump + 1; k <= j; k++) {
                                possibleTime += k * k;
                            }
                        } else {
                            possibleTime += 1;
                        }

                        if (visited[j][nx][ny] > possibleTime) {
                            visited[j][nx][ny] = possibleTime;
                            queue.add(new int[]{possibleTime, nx, ny, j});
                        }
                    }
                }
            }

            if (!flag) {
                System.out.println(-1);
            }

        }

        sc.close();
    }
}
