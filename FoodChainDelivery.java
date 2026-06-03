import java.util.*;

public class FoodChainDelivery {
    static int V = 10;
    static int[][] graph = new int[V][V];
    static String[] nodes = {"Restaurant_A", "Restaurant_B", "Hub_H1",
                              "Hub_H2", "Zone_1", "Zone_2", "Zone_3",
                              "Zone_4", "Zone_5", "Zone_6"};

    // BFS - Find nearest delivery partner
    static void bfs(int src) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        int[] level = new int[V];
        visited[src] = true;
        queue.add(src);
        System.out.println("BFS from " + nodes[src] + ":");
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.println("  Visiting: " + nodes[u] + " (Level " + level[u] + ")");
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !visited[v]) {
                    visited[v] = true;
                    level[v] = level[u] + 1;
                    queue.add(v);
                    if (nodes[v].startsWith("Zone") && level[v] == 1) {
                        System.out.println("  --> Nearest delivery zone: " + nodes[v]);
                    }
                }
            }
        }
    }

    // DFS - Check zone connectivity
    static void dfs(int src, boolean[] visited) {
        visited[src] = true;
        System.out.println("  DFS Visiting: " + nodes[src]);
        for (int v = 0; v < V; v++) {
            if (graph[src][v] != 0 && !visited[v])
                dfs(v, visited);
        }
    }

    // Prim's MST
    static void primMST() {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] inMST = new boolean[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V - 1; count++) {
            int u = -1;
            for (int v = 0; v < V; v++)
                if (!inMST[v] && (u == -1 || key[v] < key[u])) u = v;
            inMST[u] = true;
            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
        }
        System.out.println("Prim's MST Edges (Minimum Cost Delivery Network):");
        int total = 0;
        for (int i = 1; i < V; i++) {
            System.out.println("  " + nodes[parent[i]] + " -- " + nodes[i] + " : " + graph[parent[i]][i] + " km");
            total += graph[parent[i]][i];
        }
        System.out.println("  Total Minimum Network Cost: " + total + " km");
    }

    public static void main(String[] args) {
        // Build adjacency matrix (weighted undirected graph)
        graph[0][2]=4;  graph[2][0]=4;   // RestA   -- Hub_H1
        graph[0][4]=7;  graph[4][0]=7;   // RestA   -- Zone_1
        graph[1][2]=3;  graph[2][1]=3;   // RestB   -- Hub_H1
        graph[1][3]=5;  graph[3][1]=5;   // RestB   -- Hub_H2
        graph[1][5]=6;  graph[5][1]=6;   // RestB   -- Zone_2
        graph[2][3]=2;  graph[3][2]=2;   // Hub_H1  -- Hub_H2
        graph[2][5]=5;  graph[5][2]=5;   // Hub_H1  -- Zone_2
        graph[3][6]=4;  graph[6][3]=4;   // Hub_H2  -- Zone_3
        graph[3][7]=3;  graph[7][3]=3;   // Hub_H2  -- Zone_4
        graph[4][8]=5;  graph[8][4]=5;   // Zone_1  -- Zone_5
        graph[5][9]=4;  graph[9][5]=4;   // Zone_2  -- Zone_6
        graph[6][9]=6;  graph[9][6]=6;   // Zone_3  -- Zone_6
        graph[7][8]=2;  graph[8][7]=2;   // Zone_4  -- Zone_5
        graph[8][9]=3;  graph[9][8]=3;   // Zone_5  -- Zone_6

        System.out.println("===== BFS: NEAREST DELIVERY PARTNER SEARCH =====");
        bfs(0);

        System.out.println();
        System.out.println("===== DFS: DELIVERY ZONE CONNECTIVITY CHECK =====");
        boolean[] visited = new boolean[V];
        dfs(2, visited);
        int reachable = 0;
        for (boolean b : visited) if (b) reachable++;
        System.out.println("  Nodes reachable from Hub_H1: " + reachable + "/" + V);
        System.out.println("  Network Status: " + (reachable == V ? "FULLY CONNECTED" : "DISCONNECTED"));

        System.out.println();
        System.out.println("===== PRIM'S MST: MINIMUM HUB NETWORK =====");
        primMST();

        System.out.println();
        System.out.println("Time Complexity: BFS/DFS = O(V+E), Prim's MST = O(V^2)");
    }
}
