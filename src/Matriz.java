/**
 * Classe para gerar e imprimir uma matriz de adjacencias randomica
 */
class Matriz {

    private static final int INF = Integer.MAX_VALUE;

    /**
     * Gera uma matriz de adjacencias randomica.
     * 
     * @param dimensao A dimensao da matriz desejada
     * @param print    Booleano para indicar se deseja imprimir o resultado
     * @return A matriz gerada
     */
    public static int[][] geraMatriz(int dimensao, Boolean print) {
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
                        graph[i][j] = (int) ((Math.random() * 10) + 1);
                    }
                }
            }
            // Nao pode haver uma fila vazia (vertice desconexo)
            boolean testaFilaVazia = true;
            for (int j = 0; j < dimensao; j++) {
                if (graph[i][j] != INF && graph[i][j] != 0)
                    testaFilaVazia = false;
            }
            if (testaFilaVazia) {
                int valor = (int) (Math.random() * dimensao);
                if (valor == i) {
                    if (valor - 1 > 0)
                        valor = valor - 1;
                    else {
                        if (valor < dimensao)
                            valor = valor + 1;
                        else
                            System.exit(3);
                    }
                }

                graph[i][valor] = (int) ((Math.random() * 10) + 1);
                graph[valor][i] = graph[i][valor];
            }
        }

        if (print)
            printMatrix(graph);

        return graph;
    }

    /**
     * Compara duas matrizes.
     * 
     * @param matrix1 Uma das matrizes a comparar
     * @param matrix2 Uma das matrizes a comparar
     * @return Retorna true quando as duas sao iguais.
     */
    public static boolean compareMatrix(int matrix1[][], int matrix2[][]) {
        for (int i = 0; i < matrix1[0].length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                if (matrix1[i][j] != matrix2[i][j])
                    return false;
            }
        }
        return true;
    }

    /**
     * Imprime a matrix informada
     * 
     * @param graph    A matriz a ser impressa
     * @param dimensao A dimensao da matriz
     */
    public static void printMatrix(int graph[][]) {
        int dimensao = graph[0].length;
        System.out.println("Matrix:");
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
}