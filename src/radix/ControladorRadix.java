package radix;
import arquivos.Arquivo;

public class ControladorRadix {
    private int[] tamanhos;
    private float somaTempo;
    private String tabelaSaida;

    public ControladorRadix(int[] tamanhos) {
        this.tamanhos = tamanhos;
    }

    public void setTamanhos(int[] tamanhos) {
        this.tamanhos = tamanhos;
    }

    public int[] getTamanhos() {
        return tamanhos;
    }

    public float getSomaTempo() {
        return somaTempo;
    }

    public String getTabelaSaida() {
        return tabelaSaida;
    }

    public void calcularTabelaSaida() throws Exception {
        String[] ordens = { "Aleatorio", "Crescente", "Decrescente" };
        String[] tipos = { "", "-RangeMenor", "-RangeMaior", "-CEP", "-Iguais", "-Extremo" };
        tabelaSaida = "Tamanho/Tipo,Aleatorio,Aleatorio-RangeMenor,Aleatorio-RangeMaior,Aleatorio-CEP,Aleatorio-Iguais,Aleatorio-Extremo,Crescente,Crescente-RangeMenor,Crescente-RangeMaior,Crescente-CEP,Crescente-Iguais,Crescente-Extremo,Decrescente,Decrescente-RangeMenor,Decrescente-RangeMaior,Decrescente-CEP,Decrescente-Iguais,Decrescente-Extremo\n";

        for(int t : tamanhos){
            tabelaSaida += t + ",";
            for(String ordem : ordens){
                String nomeArquivo = "../datasets/";
                String dirOrdem;
                dirOrdem = ordem.toLowerCase() + "s/";

                nomeArquivo += dirOrdem + t + ordem;

                for(String tipo : tipos){
                    nomeArquivo += tipo + ".txt";
                    Arquivo arq = new Arquivo(nomeArquivo);
                    Long[] vetor = arq.leituraArquivo(t);
                    if(vetor == null){
                        throw new Exception("Erro ao ler o arquivo " + nomeArquivo);
                    }
                    RadixSort radix = new RadixSort(vetor, t, 10);
                    float tempo = 0;

                    for(int i = 0; i < 10; i++){
                        // Executa 10 vezes para calcular a média em segundos
                        radix.setVetor(vetor);
                        long tempoInicial = System.nanoTime();
                        radix.radixSort();
                        long tempoFinal = System.nanoTime();
                        tempo = (tempoFinal - tempoInicial) / 1000000000F;
                        somaTempo += tempo;
                    }

                    tabelaSaida += String.format("%.6f", (tempo / 10)) + ",";
                    nomeArquivo = "../datasets/" + dirOrdem + t + ordem;
                }
            }
            tabelaSaida += "\n";
        }
        tabelaSaida += "Tempo Total," + String.format("%.6f", (somaTempo)) + "\n";
        tabelaSaida += "Tempo Médio Total," + String.format("%.6f", (somaTempo / 720)) + "\n";
    }
}
