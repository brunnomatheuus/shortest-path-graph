/**
 * Classe principal do sistema para calculo do caminho mais curto utilizando os
 * algoritmos de Dijkstra e Floyd-Warshall para a disciplina de Grafos
 */

class Main {
    final static int INF = Integer.MAX_VALUE;
    final static int DIMENSAO = 100;

    /**
     * Main do programa
     * 
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        GeraMatriz matriz = new GeraMatriz();
        long tinicial0 = System.currentTimeMillis();
        int graph[][] = matriz.geraMatriz(DIMENSAO, false);
        long tfinal0 = System.currentTimeMillis();

        long tinicial1 = System.currentTimeMillis();
        FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
        f.floydWarshall(graph, false);
        long tfinal1 = System.currentTimeMillis();

        long tinicial2 = System.currentTimeMillis();
        DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        d.dijkstraAllPairs(graph, false);
        long tfinal2 = System.currentTimeMillis();

        // Acrescenta o resultado no arquivo.
        // Leiaute: dimensão; tempo de geração; tempoFloyd-Warshall; tempo Dijkstra
        Arquivo arquivo = new Arquivo();
        arquivo.salvar("/home/dionisio/Documentos/UFRN/grafos.txt", DIMENSAO + ";" + (tfinal0 - tinicial0) + ";"
                + (tfinal1 - tinicial1) + ";" + (tfinal2 - tinicial2) + ";");
    }
}