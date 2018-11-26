/**
 * Classe para utilizacao do algoritmo de Floyd-Warshall
 */
class FloydWarshallAlgorithm {
    final static int INF = Integer.MAX_VALUE;
    private String matrizRetorno = "";

    /**
     * Executa o algoritmo de Floyd-Warshall e imprime a matriz de caminho mais
     * curto como resultado
     * 
     * @param graph A matriz de adjacencias
     * @param print Booleano para indicar se deseja imprimir o resultado
     */
    void floydWarshall(int graph[][], Boolean print) {
        final int V = graph[0].length;
        int dist[][] = new int[V][V];
        int i, j, k;

        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && (dist[i][k] + dist[k][j] < dist[i][j]))
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        if (print)
            printSolution(dist, V);
    }

    /**
     * Imprime a matriz de caminho mais curto
     * 
     * @param dist A Matriz de distancias a imprimir
     * @param V    A dimensao da matriz
     */
    void printSolution(int dist[][], int V) {
        System.out.println(
                "Floyd-Warshall: A matrix abaixo exibe o caminho mais curto e as distâncias entre cada par de vértices:");
        System.out.print("X\t");
        for (int i = 0; i < V; ++i) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < V; ++i) {
            System.out.print(i + "\t");
            for (int j = 0; j < V; ++j) {
                if (dist[i][j] == INF) {
                    System.out.print("INF\t");
                    matrizRetorno += "\tINF" ;
                }
                else {
                    System.out.print(dist[i][j] + "\t");
                    matrizRetorno += "\t" + dist[i][j];
                }
            }
            System.out.println();
        }
        System.out.println();
    }

	public String getMatriz() {
		return matrizRetorno;
	}
}