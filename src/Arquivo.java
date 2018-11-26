import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Classe para salvar os resultados em um arquivo
 */
class Arquivo {
    final static int INF = Integer.MAX_VALUE;

    /**
     * Acrescenta a string em dados no arquivo desejado
     * 
     * @param arquivo
     * @param limpar
     * @param dados
     * @return
     */
    public Boolean salvar(String arquivo, Boolean limpar, String dados) {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileOutputStream(new File(arquivo), !limpar));
            out.println(dados);
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int[][] loadMatrix(String arquivo) throws FileNotFoundException {
        File file = new File(arquivo);
        String linha[];
        int dimensao = 0;
        int row = 0;
        int matrix[][] = new int[0][0];

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                linha = scanner.nextLine().split(";");
                if (dimensao == 0) {
                    dimensao = linha.length;
                    matrix = new int[dimensao][dimensao];
                }
                for (int i = 0; i < dimensao; i++) {
                    if (linha[i].equals("INF"))
                        matrix[row][i] = INF;
                    else
                        matrix[row][i] = Integer.valueOf(linha[i]);
                }
                row++;
            }
        }
        if (dimensao > 0)
            return matrix;
        return null;
    }

    public Boolean saveMatrix(String arquivo, int matrix[][]) {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileOutputStream(new File(arquivo), false));
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    out.print(matrix[i][j] + ";");
                }
                out.println();
            }
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}