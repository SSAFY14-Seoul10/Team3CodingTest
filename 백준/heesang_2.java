import java.io.*;
import java.util.*;

class Node {
    int idx;
    int val;

    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}

class Edge {
    int to;
    int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    static int V, E, start;
    static int[] dis;
    static List<List<Edge>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        dis = new int[V + 1];

        Arrays.fill(dis, Integer.MAX_VALUE);
        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node -> Node.val));
        pq.add(new Node(start, 0));
        dis[start] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int idx = now.idx;
            if(dis[idx] < now.val) continue;
            for (Edge edge : graph.get(idx)) {
                int to = edge.to;
                int weight = edge.weight;
                if (dis[idx] + weight < dis[to]) {
                    dis[to] = dis[idx] + weight;
                    pq.add(new Node(to, dis[to]));
                }
            }
        }

        for (int i = 1; i < V+1; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dis[i]);
            }
        }
    }
}
