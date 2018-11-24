
// A Java program for Floyd Warshall All Pairs Shortest 
// Path algorithm. 
//import java.util.*;
//import java.lang.*;
//import java.io.*;

class FloydWarshallAlgorithm9x9 {
    final static int INF = Integer.MAX_VALUE;
    // final static int V = 9;

    void floydWarshall(int graph[][]) {
        final int V = graph[0].length;
        int dist[][] = new int[V][V];
        int i, j, k;

        /*
         * Initialize the solution matrix same as input graph matrix. Or we can say the
         * initial values of shortest distances are based on shortest paths considering
         * no intermediate vertex.
         */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

        /*
         * Add all vertices one by one to the set of intermediate vertices. ---> Before
         * start of an iteration, we have shortest distances between all pairs of
         * vertices such that the shortest distances consider only the vertices in set
         * {0, 1, 2, .. k-1} as intermediate vertices. ----> After the end of an
         * iteration, vertex no. k is added to the set of intermediate vertices and the
         * set becomes {0, 1, 2, .. k}
         */
        for (k = 0; k < V; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++) {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && (dist[i][k] + dist[k][j] < dist[i][j]))
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(dist, V);
    }

    void printSolution(int dist[][], int V) {
        System.out.println("A seguinte matrix exibe o caminho mais curto e distâncias entre cada par de vértices:");
        System.out.print("X\t");
        for (int i = 0; i < V; ++i) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < V; ++i) {
            System.out.print(i + "\t");
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF)
                    System.out.print("INF\t");
                else
                    System.out.print(dist[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Driver program to test above function
    public static void main(String[] args) {
        int graph[][] = { // DEFINE O GRAFO
                { 0, 2, INF, INF, 3, INF, INF, INF, INF }, // LINHA 0
                { 2, 0, 4, 1, INF, INF, INF, INF, INF }, // LINHA 1
                { INF, 4, 0, INF, INF, INF, INF, INF, 5 }, // LINHA 2
                { INF, 1, INF, 0, INF, 3, INF, 1, INF }, // LINHA 3
                { 3, INF, INF, INF, 0, 2, INF, INF, INF }, // LINHA 4
                { INF, INF, INF, 3, 2, 0, 1, INF, INF }, // LINHA 5
                { INF, INF, INF, INF, INF, 1, 0, INF, 2 }, // LINHA 6
                { INF, INF, INF, 1, INF, INF, INF, 0, 3 }, // LINHA 7
                { INF, INF, 5, INF, INF, INF, 2, 3, 0 } }; // LINHA 8

        FloydWarshallAlgorithm9x9 a = new FloydWarshallAlgorithm9x9();

        // Print the solution
        a.floydWarshall(graph);
    }
}

// Contributed by Aakash Hasija
