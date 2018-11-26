import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TesteCorretude {  
      
    
    private static final int INF = Integer.MAX_VALUE;

	@Test
    public void verificaMatrizDijkstra() {    
    	int graph[][] = {
    			{0, 2, INF},
    			{2, 0, 4},
    			{INF, 4, 0}
    	};

        DijkstrasAlgorithm d = new DijkstrasAlgorithm();
        
        String resultadoEsperado = "\t0\t2\t6\t2\t0\t4\t6\t4\t0";
        		
        d.dijkstraAllPairs(graph, true);
        
    	System.out.println(d.getMatriz());
    	assertEquals(resultadoEsperado, d.getMatriz());
    }
	
	@Test
    public void verificaMatrizFloyd() {    
    	int graph[][] = {
    			{0, 2, INF},
    			{2, 0, 4},
    			{INF, 4, 0}
    	};

        FloydWarshallAlgorithm f = new FloydWarshallAlgorithm();
        
        String resultadoEsperado = "\t0\t2\t6\t2\t0\t4\t6\t4\t0";
        		
        f.floydWarshall(graph, true);
        
    	System.out.println(f.getMatriz());
    	assertEquals(resultadoEsperado, f.getMatriz());
    }
}