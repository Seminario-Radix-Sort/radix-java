import arquivos.Arquivo;
import java.util.Locale;
import radix.ControladorRadix;

public class Main {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(new Locale("en", "US"));
        int[] tamanhos = { 10000, 100000, 500000, 1000000 };
        ControladorRadix controlador = new ControladorRadix(tamanhos);

        System.out.println("Calculando tabela de saída do Radix Sort...");
        try {
            controlador.calcularTabelaSaida();
            Arquivo arqSaida = new Arquivo("../datasets/tempoExecucao-Java");
            arqSaida.escritaArquivo(controlador.getTabelaSaida());
            System.out.println("Tabela de saída do Radix Sort calculada com sucesso!");  
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao calcular a tabela de saída do Radix Sort");
            System.err.println("Erro: " + e.getMessage());
        }      
    }
}
