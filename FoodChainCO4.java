import java.util.*;

public class FoodChainCO4 {

    static final int INF = 99999;

    // Dijkstra
    static void dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = -1;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < V; i++) {
                if (!visited[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }

            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] &&
                        graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("===== DIJKSTRA =====");
        for (int i = 0; i < V; i++)
            System.out.println("Distance to Node " + i + " = " + dist[i]);
    }

    // Bellman Ford
    static void bellmanFord(int[][] edges, int V, int E, int src) {

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {

                int u = edges[j][0];
                int v = edges[j][1];
                int w = edges[j][2];

                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + w < dist[v]) {

                    dist[v] = dist[u] + w;
                }
            }
        }

        System.out.println("\n===== BELLMAN FORD =====");
        for (int i = 0; i < V; i++)
            System.out.println("Distance to Node " + i + " = " + dist[i]);
    }

    // Floyd Warshall
    static void floydWarshall(int[][] graph) {

        int V = graph.length;
        int[][] dist = new int[V][V];

        for (int i = 0; i < V; i++)
            dist[i] = graph[i].clone();

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        System.out.println("\n===== FLOYD WARSHALL =====");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                System.out.print(dist[i][j] + "\t");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] graph = {
                {0,4,0,0,7},
                {4,0,2,0,0},
                {0,2,0,3,0},
                {0,0,3,0,1},
                {7,0,0,1,0}
        };

        dijkstra(graph,0);

        int[][] edges = {
                {0,1,4},
                {1,2,2},
                {2,3,3},
                {3,4,1},
                {0,4,7}
        };

        bellmanFord(edges,5,5,0);

        int[][] fw = {
                {0,4,INF,INF,7},
                {4,0,2,INF,INF},
                {INF,2,0,3,INF},
                {INF,INF,3,0,1},
                {7,INF,INF,1,0}
        };

        floydWarshall(fw);
    }
}