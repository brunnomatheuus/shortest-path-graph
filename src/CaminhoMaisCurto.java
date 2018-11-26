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
        String caminho = "./results/grafosunico.txt";
        Calculos calculos = new Calculos();
        Arquivo arq = new Arquivo();

        // Executa o teste dos algoritmos 1000 vezes
        int erros = calculos.testeCalculos("./dados/matriz9x9teste.txt", "./dados/matriz9x9testeEsperado.txt", "", "",
                1000);
        System.out.println("Teste executado. Erros encontrados: " + erros);

        // Executa teste único usando os dois algoritmos
        arq.salvar(caminho, true, "Dimensao;Geracao;Dijkstra;Floyd");
        if (calculos.calcUnico(caminho, false, DIMENSAO, false))
            System.out.println("Teste único executado com sucesso.");
        else
            System.out.println("Teste único falhou.");

        // Executar algoritmos com matriz de dimensão 10 até 100, iterando 100 vezes em
        // cada e salvando em arquivo novo
        if (calculos.calcLoop("./results/grafosloop.txt", true, 10, 100, 100, false))
            System.out.println("Teste em loop executado com sucesso.");
        else
            System.out.println("Teste em loop falhou.");
    }
}