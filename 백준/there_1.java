import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {
    static class Node {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        for (int i=0; i<V+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        boolean[] visited = new boolean[V+1];
        int[] dist = new int[V+1];
        for (int i = 0; i < V+1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        for (int i = 0; i < V; i++) {
            int nodeDist = Integer.MAX_VALUE;
            int nodeIndex = 0;
            for (int j = 1; j < V+1; j++) {
                if (!visited[j] && dist[j] < nodeDist) {
                    nodeDist = dist[j];
                    nodeIndex = j;
                }
            }
            visited[nodeIndex] = true;

            for (int j = 0; j < graph.get(nodeIndex).size(); j++) {
                Node adjNode = graph.get(nodeIndex).get(j);
                if (dist[adjNode.to] > dist[nodeIndex] + adjNode.weight) {
                    dist[adjNode.to] = dist[nodeIndex] + adjNode.weight;
                }
            }

        }

        for (int i = 1; i < V+1; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dist[i]);
        }
	}
}
