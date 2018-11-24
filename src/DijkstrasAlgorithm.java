// A Java program for Dijkstra's 
// single source shortest path 
// algorithm. The program is for 
// adjacency matrix representation 
// of the graph. 

class DijkstrasAlgorithm {

    private static final int NO_PARENT = -1;
    final static int INF = Integer.MAX_VALUE;

    public void dijkstraAllPairs(int[][] graph) {
        System.out.println(
                "Dijkstra: A matrix abaixo exibe o caminho mais curto e as distâncias entre cada par de vértices:");
        System.out.print("X\t");
        for (int i = 0; i < graph[0].length; ++i) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < graph[0].length; i++) {
            dijkstra(graph, i);
        }
    }

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    public void dijkstra(int[][] adjacencyMatrix, int startVertex) {
        int nVertices = adjacencyMatrix[0].length;

        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[nVertices];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++) {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance != Integer.MAX_VALUE && edgeDistance > 0
                        && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }

        // printSolution(startVertex, shortestDistances, parents);
        printLine(startVertex, shortestDistances, parents);
    }

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    // private void printSolution(int startVertex, int[] distances, int[] parents) {
    // int nVertices = distances.length;
    // System.out.print("Vertex\t Distance\t\tPath");

    // for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
    // System.out.print("\n" + startVertex + " -> ");
    // System.out.print(vertexIndex + " \t\t ");
    // System.out.print(distances[vertexIndex] + "\t\t");
    // printPath(vertexIndex, parents);
    // }
    // }

    private void printLine(int startVertex, int[] distances, int[] parents) {
        int nVertices = distances.length;
        // System.out.print("Vertex\t Distance\t\tPath");

        System.out.print(startVertex);
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            System.out.print("\t" + distances[vertexIndex]);
        }
        System.out.println();
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    // private void printPath(int currentVertex, int[] parents) {

    // // Base case : Source node has
    // // been processed
    // if (currentVertex == NO_PARENT) {
    // return;
    // }
    // printPath(parents[currentVertex], parents);
    // System.out.print(currentVertex + " ");
    // }

    // // Driver Code
    // public static void main(String[] args) {
    // int graph[][] = { // DEFINE O GRAFO
    // { 0, 2, INF, INF, 3, INF, INF, INF, INF }, // LINHA 0
    // { 2, 0, 4, 1, INF, INF, INF, INF, INF }, // LINHA 1
    // { INF, 4, 0, INF, INF, INF, INF, INF, 5 }, // LINHA 2
    // { INF, 1, INF, 0, INF, 3, INF, 1, INF }, // LINHA 3
    // { 3, INF, INF, INF, 0, 2, INF, INF, INF }, // LINHA 4
    // { INF, INF, INF, 3, 2, 0, 1, INF, INF }, // LINHA 5
    // { INF, INF, INF, INF, INF, 1, 0, INF, 2 }, // LINHA 6
    // { INF, INF, INF, 1, INF, INF, INF, 0, 3 }, // LINHA 7
    // { INF, INF, 5, INF, INF, INF, 2, 3, 0 } }; // LINHA 8

    // DijkstrasAlgorithm d = new DijkstrasAlgorithm();
    // d.dijkstra(graph, 1);
    // }
}

// This code is contributed by Harikrishnan Rajan
