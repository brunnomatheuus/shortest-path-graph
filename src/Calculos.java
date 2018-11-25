/**
 * Classe para executar os algoritmos
 */
class Calculos {
    /**
     * Executa os algoritmos uma vez cada
     * 
     * @param caminho  Caminho do arquivo onde salvar os resultados
     * @param limpar   Limpar arquivo destino ou não
     * @param dimensao Dimensao da matriz desejada
     * @param ver      Visualiza as operacoes
     * @return Retorna true quando consegue executar todas as operacoes e salvar o
     *         arquivo
     */
    public boolean calcUnico(String caminho, Boolean limpar, int dimensao, Boolean ver) {
        // Gera uma matriz de adjacencias randomica na dimensao informada
        Matriz matriz = new Matriz();
        long tinicial0 = System.nanoTime();
        int graph[][] = matriz.geraMatriz(dimensao, ver);
        long tfinal0 = System.nanoTime();

        // Usa Dijkstra para calcular os caminhos mais curtos
        long tinicial1 = System.currentTimeMillis();
        DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        int resultado = d.dijkstraAllPairs(graph, ver);
        long tfinal1 = System.currentTimeMillis();
        if (resultado != 0) {
            matriz.printMatrix(graph);
            return false;
        }

        // Usa Floyd-Warshall para calcular os caminhos mais curtos
        long tinicial2 = System.currentTimeMillis();
        FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
        f.floydWarshall(graph, ver);
        long tfinal2 = System.currentTimeMillis();

        // Acrescenta o resultado no arquivo.
        // Leiaute: dimensão; tempo de geração; tempo Dijkstra; tempo Floyd-Warshall
        Arquivo arquivo = new Arquivo();
        return arquivo.salvar(caminho, limpar, dimensao + ";" + (tfinal0 - tinicial0) + ";" + (tfinal1 - tinicial1)
                + ";" + (tfinal2 - tinicial2) + ";");
    }

    /**
     * Executa os algoritmos diversas vezes
     * 
     * @param caminho     Caminho do arquivo onde salvar os resultados
     * @param limpar      Limpar arquivo destino ou não
     * @param dimensaoMin Dimensao minima da matriz desejada
     * @param dimensaoMax Dimensao maxima da matriz desejada
     * @param qtdLoops    Quantidade de execucoes
     * @param ver         Visualiza as operacoes
     * @return Retorna true quando consegue executar todas as operacoes e salvar o
     *         arquivo
     */
    public boolean calcLoop(String caminho, Boolean limpar, int dimensaoMin, int dimensaoMax, int qtdLoops,
            Boolean ver) {
        if (dimensaoMin <= 0 || dimensaoMax <= 0) {
            System.out.println("Dimensões inválidas");
            return false;
        }
        if (dimensaoMax < dimensaoMin) {
            System.out.println("Dimensões inválidas");
            return false;
        }

        System.out.print("Iniciando loops: ");
        ProgressBar pb = new ProgressBar();
        long total = dimensaoMax - dimensaoMin;
        long startTime = System.currentTimeMillis();
        for (int i = dimensaoMin; i <= dimensaoMax; i++) {
            pb.printProgress(startTime, total, i - dimensaoMin);
            for (int j = 0; j < qtdLoops; j++) {
                if (i > dimensaoMin || j > 0)
                    limpar = false;
                if (!calcUnico(caminho, limpar, i, ver)) {
                    System.out.println("Matriz " + i + "x" + i + " inválida!");
                }
            }
        }
        System.out.println();
        return true;
    }
}