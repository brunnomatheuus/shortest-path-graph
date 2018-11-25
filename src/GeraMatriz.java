/**
 * Classe para gerar uma matriz de adjacencias randomica
 */
class GeraMatriz {

    private final int INF = Integer.MAX_VALUE;

    /**
     * Gera uma matriz de adjacencias randomica.
     * 
     * @param dimensao A dimensao da matriz desejada
     * @param print    Booleano para indicar se deseja imprimir o resultado
     * @return A matriz gerada
     */
    public int[][] geraMatriz(int dimensao, Boolean print) {
        int graph[][] = new int[dimensao][dimensao];
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else if (j <= i) {
                    graph[i][j] = graph[j][i];
                } else {
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

        if (print) {
            System.out.println("Matrix gerada para testes");
            for (int i = 0; i < dimensao; ++i) {
                if (i == 0) {
                    System.out.print("X");
                    for (int j = 0; j < dimensao; j++)
                        System.out.print("\t" + j);
                    System.out.println();
                }
                for (int j = 0; j < dimensao; j++) {
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
        }
        return graph;
    }
}