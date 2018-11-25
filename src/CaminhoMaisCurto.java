/**
 * Classe principal do sistema para calculo do caminho mais curto utilizando os
 * algoritmos de Dijkstra e Floyd-Warshall para a disciplina de Grafos
 */

class CaminhoMaisCurto {
    final static int INF = Integer.MAX_VALUE;
    final static int DIMENSAO = 100;

    /**
     * Main do programa
     * 
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        Calculos calculos = new Calculos();
        if (calculos.calcUnico("/home/dionisio/Documentos/UFRN/grafos.txt", false, DIMENSAO, false))
            System.out.println("***FIM ÚNICO***");
        else
            System.out.println("***ERRO ÚNICO***");

        // Executar algoritmos com matriz de dimensão 10 até 100, iterando 100 vezes em
        // cada e salvando em arquivo novo
        if (calculos.calcLoop("/home/dionisio/Documentos/UFRN/grafos2.txt", true, 10, 100, 100, false))
            System.out.println("***FIM LOOP***");
        else
            System.out.println("***ERRO LOOP***");
    }
}