import java.util.Scanner;

class Main {
    final static int INF = Integer.MAX_VALUE;

    // Driver program to test above function
    public static void main(String[] args) {

        // TODO - LER A MATRIZ DO ARQUIVO GERADO E CARREGAR NA VARIÁVEL GRAPH

        // TODO - GERAR BASE DE DADOS ALEATÓRIA E GRAVAR EM ARQUIVO
        int n, m;
        n = 3;
        m = 3;
        int graph[][] = new int [n][m];
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m ; j++){
                if(i == j){
                    graph[i][j] = 0;
                }else if(j <= i){
                    graph[i][j] = graph[j][i];
                }
                else{
                    //Variavel que decide se vai ser INF ou não.
                    int bool = (int)(Math.random()*2);
                    if(bool == 0)
                    {
                        graph[i][j] = INF;    
                    }else if(bool == 1){
                        graph[i][j] = (int)(Math.random()*10 + 1);
                    }                    
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            for(int j = 0; j < m ; j++){
                if(graph[i][j] == INF){
                    System.out.print("INF \t");    
                }else{
                    System.out.print(graph[i][j] + "\t");
                }
            }
            System.out.print("\n");
        }


                // { // DEFINE O GRAFO
                // { 0, 2, INF }, // LINHA 0
                // { 2, 0, 4 }, // LINHA 1
                // { INF, 4, 0 } }; // LINHA 2
                // { 0, 2, INF, INF, 3, INF, INF, INF, INF }, // LINHA 0
                // { 2, 0, 4, 1, INF, INF, INF, INF, INF }, // LINHA 1
                // { INF, 4, 0, INF, INF, INF, INF, INF, 5 }, // LINHA 2
                // { INF, 1, INF, 0, INF, 3, INF, 1, INF }, // LINHA 3
                // { 3, INF, INF, INF, 0, 2, INF, INF, INF }, // LINHA 4
                // { INF, INF, INF, 3, 2, 0, 1, INF, INF }, // LINHA 5
                // { INF, INF, INF, INF, INF, 1, 0, INF, 2 }, // LINHA 6
                // { INF, INF, INF, 1, INF, INF, INF, 0, 3 }, // LINHA 7
                // { INF, INF, 5, INF, INF, INF, 2, 3, 0 } }; // LINHA 8

        // long tinicial1 = System.currentTimeMillis();
        // FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
        // f.floydWarshall(graph);
        // long tfinal1 = System.currentTimeMillis();

        // long tinicial2 = System.currentTimeMillis();
        // DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        // d.dijkstraAllPairs(graph);
        // long tfinal2 = System.currentTimeMillis();
        // System.out.println("Dijkstra acabou. Tempo: " + (tfinal1 - tinicial1) + "ms");
        // System.out.println("Floyd-Warshall acabou. Tempo: " + (tfinal2 - tinicial2 + "ms"));
    }
}