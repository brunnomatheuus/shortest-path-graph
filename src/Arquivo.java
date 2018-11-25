import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Classe para salvar os resultados em um arquivo
 */
class Arquivo {
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
}