class Main {
    final static int INF = Integer.MAX_VALUE;
    final static int DIMENSAO = 9;

    // Driver program to test above function
    public static void main(String[] args) {
        int n = DIMENSAO;
        int graph[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else if (j <= i) {
                    graph[i][j] = graph[j][i];
                } else {
                    // Variavel que decide se vai ser INF ou não.
                    int bool = (int) (Math.random() * 4);
                    if (bool == 0) {
                        graph[i][j] = INF;
                    } else if (bool == 1) {
                        graph[i][j] = (int) (Math.random() * 10 + 1);
                    }
                }
            }
        }

        System.out.println("Matrix gerada para testes");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("INF \t");
                } else {
                    System.out.print(graph[i][j] + "\t");
                }
            }
            System.out.print("\n");
        }

        long tinicial1 = System.currentTimeMillis();
        FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
        f.floydWarshall(graph);
        long tfinal1 = System.currentTimeMillis();

        long tinicial2 = System.currentTimeMillis();
        DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        d.dijkstraAllPairs(graph);
        long tfinal2 = System.currentTimeMillis();
        System.out.println("Dijkstra acabou. Tempo: " + (tfinal1 - tinicial1) + "ms");
        System.out.println("Floyd-Warshall acabou. Tempo: " + (tfinal2 - tinicial2 + "ms"));
    }
}