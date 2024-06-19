package arquivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Arquivo {
    private String nomeArquivo;
    
    public Arquivo(String nome) {
        this.nomeArquivo = nome;
    }

    public String getNome() {
        return nomeArquivo;
    }

    public Long[] leituraArquivo(int tamanho){
        Long[] vetor = new Long[tamanho];

        // Lê o arquivo e armazena os valores no vetor
        File arq = new File(this.nomeArquivo);
        
        try (Scanner scanner = new Scanner(arq)) {
            int i = 0;
            // Pula 2 linhas
            scanner.nextLine();
            scanner.nextLine();
            
            // Lê a linha e armazena numeros separados por virgula e espaço no vetor
            while(scanner.hasNextLine()){
                String[] linha = scanner.nextLine().split(",");
                for(String num : linha){
                    if(i == tamanho)
                        break;
                    vetor[i] = Long.valueOf(num.trim());
                    i++;
                }
            }
            scanner.close();
        return vetor;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
        return null;
    }

    public void escritaArquivo(String conteudo) throws IOException{
        // data e hora atual
        Date data = new java.util.Date();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        String dataFormatada = sdf.format(data);
        nomeArquivo = nomeArquivo + "-" + dataFormatada + ".csv";

        // Escreve o vetor no arquivo
        File arq = new File(this.nomeArquivo);
        arq.createNewFile();
        try (PrintWriter fw = new PrintWriter(arq)) {
            fw.write("Data e hora de Execução: " + dataFormatada + "\n");
            fw.write("Tempos em segundos:\n");

            fw.write(conteudo);

            fw.close();
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo");
            System.err.println("Erro: " + e.getMessage());
        }
    }
}

