import java.io.FileNotFoundException;

/**
 * Classe para executar os algoritmos
 */
class Calculos {
    /**
     * Executa os algoritmos uma vez cada com uma determinada matriz
     * 
     * @param caminho Caminho do arquivo onde salvar os resultados
     * @param tipo    Usar 1 para calcular com Dijkstra e 2 para Floyd
     * @param matriz  Matriz
     * @param ver     Visualiza as operacoes
     * @return Retorna true quando consegue executar todas as operacoes e salvar o
     *         arquivo
     */
    public int[][] calcResultado(String caminho, int tipo, int matriz[][], Boolean ver) {
        Arquivo arq = new Arquivo();
        // Usa Dijkstra para calcular os caminhos mais curtos
        if (tipo == 1) {
            DijkstrasAlgorithm d = new DijkstrasAlgorithm();
            int[][] resultado = d.dijkstraAllPairs(matriz, ver);
            if (resultado == null) {
                return null;
            } else {
                if (caminho != "")
                    arq.saveMatrix(caminho, resultado);
                return resultado;
            }
        }

        // Usa Floyd-Warshall para calcular os caminhos mais curtos
        if (tipo == 2) {
            FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
            int[][] resultado = f.floydWarshall(matriz, ver);
            if (resultado == null) {
                return null;
            } else {
                if (caminho != "")
                    arq.saveMatrix(caminho, resultado);
                return resultado;
            }
        }
        return null;
    }

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
        long tinicial0 = System.nanoTime();
        int graph[][] = Matriz.geraMatriz(dimensao, ver);
        long tfinal0 = System.nanoTime();

        // Usa Dijkstra para calcular os caminhos mais curtos
        DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        long tinicial1 = System.nanoTime();
        int[][] resultado = d.dijkstraAllPairs(graph, ver);
        long tfinal1 = System.nanoTime();
        if (resultado == null) {
            Matriz.printMatrix(graph);
            return false;
        }

        // Usa Floyd-Warshall para calcular os caminhos mais curtos
        FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
        long tinicial2 = System.nanoTime();
        f.floydWarshall(graph, ver);
        long tfinal2 = System.nanoTime();

        // Acrescenta o resultado no arquivo. Com tempos em micro segundos.
        // Leiaute: dimensão; tempo de geração; tempo Dijkstra; tempo Floyd-Warshall
        Arquivo arquivo = new Arquivo();
        return arquivo.salvar(caminho, limpar, dimensao + ";" + (tfinal0 - tinicial0) / 1000 + ";"
                + (tfinal1 - tinicial1) / 1000 + ";" + (tfinal2 - tinicial2) / 1000 + ";");
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
        Arquivo arq = new Arquivo();
        arq.salvar(caminho, true, "Dimensao;Geracao;Dijkstra;Floyd");
        ProgressBar pb = new ProgressBar();
        long total = dimensaoMax - dimensaoMin;
        long startTime = System.currentTimeMillis();
        for (int i = dimensaoMin; i <= dimensaoMax; i++) {
            pb.printProgress(startTime, total, i - dimensaoMin);
            for (int j = 0; j < qtdLoops; j++) {
                if (!calcUnico(caminho, false, i, ver)) {
                    System.out.println("Matriz " + i + "x" + i + " inválida!");
                }
            }
        }
        System.out.println();
        return true;
    }

    /**
     * Realiza o calculo em uma matriz e compara com o resultado esperado.
     * 
     * @param caminhoMatriz   Caminho da matriz a ser testada
     * @param caminhoEsperado Caminho da matriz com o resultado esperado
     * @param caminhoDijkstra Caminho para salvar a matriz calculada usando Dijkstra
     * @param caminhoFloyd    Caminho para salvar a matriz calculada usando Floyd
     * @param qtdTeste        Quantidade de vezes a executar os testes
     * @return Retorna 0 quando os calculos foram executados com sucesso.
     */
    public int testeCalculos(String caminhoMatriz, String caminhoEsperado, String caminhoDijkstra, String caminhoFloyd,
            int qtdTeste) {
        Arquivo arq = new Arquivo();
        int graph[][];
        int retorno = 0;

        // Carrega uma matrix a partir de um arquivo texto e executa os testes
        try {
            graph = arq.loadMatrix(caminhoMatriz);
            int esperado[][] = arq.loadMatrix(caminhoEsperado);
            // Matriz.printMatrix(graph);
            for (int i = 0; i < qtdTeste; i++) {
                int resultado1[][] = calcResultado(caminhoDijkstra, 1, graph, false);
                int resultado2[][] = calcResultado(caminhoFloyd, 2, graph, false);
                if (!Matriz.compareMatrix(resultado1, resultado2))
                    retorno++;
                else {
                    if (!Matriz.compareMatrix(esperado, resultado1) || !Matriz.compareMatrix(esperado, resultado2))
                        retorno++;
                }
            }
            return retorno;
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao carregar a matriz.");
            return retorno;
        }
    }
}