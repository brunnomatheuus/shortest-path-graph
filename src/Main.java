/**
 * Classe principal do sistema para calculo do caminho mais curto utilizando os
 * algoritmos de Dijkstra e Floyd-Warshall para a disciplina de Grafos
 */

class Main {
    final static int INF = Integer.MAX_VALUE;
    final static int DIMENSAO = 9;

    /**
     * Gera uma matriz de adjacências aleatória.
     * 
     * @return A matriz gerada
     */
    private static int[][] geraMatriz() {
        int graph[][] = new int[DIMENSAO][DIMENSAO];
        for (int i = 0; i < DIMENSAO; i++) {
            for (int j = 0; j < DIMENSAO; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else if (j <= i) {
                    graph[i][j] = graph[j][i];
                } else {
                    // Variavel que decide se vai ser INF ou não.
                    int bool = (int) (Math.random() * 2);
                    if (bool == 0) {
                        graph[i][j] = INF;
                    } else if (bool == 1) {
                        int valor = (int) ((Math.random() * 10) + 1);
                        graph[i][j] = valor;
                    }
                }
            }
        }

        System.out.println("Matrix gerada para testes");
        for (int i = 0; i < DIMENSAO; ++i) {
            if (i == 0) {
                System.out.print("X");
                for (int j = 0; j < DIMENSAO; j++)
                    System.out.print("\t" + j);
                System.out.println();
            }
            for (int j = 0; j < DIMENSAO; j++) {
                if (j == 0)
                    System.out.print(i);
                if (graph[i][j] == INF) {
                    System.out.print("\tINF");
                } else {
                    System.out.print("\t" + graph[i][j]);
                }
            }
            System.out.print("\n");
        }
        System.out.println();
        return graph;
    }

    /**
     * Main do programa
     * 
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        int graph[][] = geraMatriz();

        long tinicial1 = System.currentTimeMillis();
        FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
        f.floydWarshall(graph);
        long tfinal1 = System.currentTimeMillis();

        long tinicial2 = System.currentTimeMillis();
        DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        d.dijkstraAllPairs(graph);
        long tfinal2 = System.currentTimeMillis();
        System.out.println("Floyd-Warshall acabou. Tempo: " + (tfinal1 - tinicial1) + "ms");
        System.out.println("Dijkstra acabou. Tempo: " + (tfinal2 - tinicial2 + "ms"));
    }
}