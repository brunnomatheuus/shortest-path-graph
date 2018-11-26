/**
 * Classe para utilizacao do algoritmo de Dijkstra
 */

class DijkstrasAlgorithm {

    private String matrizResultado = "";

    /**
     * Calcula a distancia entre todos os pares de uma matriz de adjacencias
     * 
     * @param graph A matriz de adjacencias
     * @param print Booleano para indicar se deseja imprimir o resultado
     * @return Retorna diferente de 0 quando o grafo eh invalido
     */
    public int[][] dijkstraAllPairs(int[][] graph, Boolean print) {
        if (print) {
            System.out.println(
                    "Dijkstra: A matrix abaixo exibe o caminho mais curto e as distâncias entre cada par de vértices:");
            System.out.print("X\t");
            for (int i = 0; i < graph[0].length; ++i) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        int resultado[][] = new int[graph[0].length][graph[0].length];
        for (int i = 0; i < graph[0].length; i++) {
            int retorno[] = dijkstra(graph, i, print);
            if (retorno == null)
                return null;
            else
                resultado[i] = retorno;
        }
        if (print)
            System.out.println();

        return resultado;
    }

    /**
     * Executa o algoritmo de Dijkstra
     * 
     * @param matriz    A matriz de adjacencias
     * @param srcVertex Vertice de origem para o calculo do caminho mais curto
     * @param print     Booleano para indicar se deseja imprimir o resultado
     * @return Retorna o vetor com os caminhos mais curtos a partir da origem
     */
    public int[] dijkstra(int[][] matriz, int srcVertex, Boolean print) {
        int nVertices = matriz[0].length;
        int[] shortestDistances = new int[nVertices];
        boolean[] added = new boolean[nVertices];
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        shortestDistances[srcVertex] = 0;

        for (int i = 1; i < nVertices; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex]) {
                    if (shortestDistances[vertexIndex] < shortestDistance) {
                        nearestVertex = vertexIndex;
                        shortestDistance = shortestDistances[vertexIndex];
                    }
                }
            }
            if (nearestVertex == -1)
                return null;

            added[nearestVertex] = true;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = matriz[nearestVertex][vertexIndex];

                if (edgeDistance != Integer.MAX_VALUE && edgeDistance > 0
                        && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
        if (print)
            printLine(srcVertex, shortestDistances);
        return shortestDistances;
    }

    /**
     * Imprime a linha da matriz de caminho mais curto
     * 
     * @param startVertex Vertice de origem
     * @param distances   Array de distancias
     */
    private void printLine(int srcVertex, int[] distances) {
        int nVertices = distances.length;

        System.out.print(srcVertex);
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            System.out.print("\t" + distances[vertexIndex]);
            matrizResultado += "\t" + distances[vertexIndex];
        }
        System.out.println();
    }

    public String getMatriz() {
        return matrizResultado;
    }
}
