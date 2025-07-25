package 백준;

import java.io.*;
import java.util.*;

public class Huiryeong_2 {
    public static final int INF = Integer.MAX_VALUE;
    public static List<List<Node>> graph;
    public static int[] dist;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new int[V + 1];
        visited = new boolean[V + 1];
        Arrays.fill(dist, INF);

        int start = Integer.parseInt(br.readLine());
        dist[start] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.idx]) continue;
            visited[cur.idx] = true;

            for (Node next : graph.get(cur.idx)) {
                if (!visited[next.idx]) {
                    int newDist = dist[cur.idx] + next.cost;
                    if (newDist < dist[next.idx]) {
                        dist[next.idx] = newDist;
                        pq.add(new Node(next.idx, newDist));
                    }
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
