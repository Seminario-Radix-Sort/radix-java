package radix;

import java.util.Arrays;

public class RadixSort{
    private Long[] vetor;
    private int tamanho;
    private int base;
    
    public RadixSort(Long[] vetor, int tamanho, int base){
        this.vetor = vetor;
        this.tamanho = tamanho;
        this.base = base;
    }

    public void setVetor(Long[] vetor){
        this.vetor = vetor;
    }

    public void setTamanho(int tamanho){
        this.tamanho = tamanho;
    }

    public void setBase(int base){
        this.base = base;
    }

    public Long[] getVetor(){
        return vetor;
    }

    public int getTamanho(){
        return tamanho;
    }

    public int getBase(){
        return base;
    }

    private Long getMax(){
        Long max = vetor[0];
        for(int i = 1; i < tamanho; i++){
            if(vetor[i] > max){
                max = vetor[i];
            }
        }
        return max;
    }

    private void countingSort(Long exp){
        Long[] output = new Long[tamanho];
        int[] contagem = new int[base];
        Arrays.fill(contagem, 0);

        for(int i = 0; i < tamanho; i++){
            Long digito = (vetor[i] / exp) % base;
            contagem[digito.intValue()]++;
        }

        for(int i = 1; i < base; i++){
            contagem[i] += contagem[i - 1];
        }

        for(int i = tamanho - 1; i >= 0; i--){
            Long digito = (vetor[i] / exp) % base;
            output[contagem[digito.intValue()] - 1] = vetor[i];
            contagem[digito.intValue()]--;
        }

        System.arraycopy(output, 0, vetor, 0, tamanho);
    }

    public void radixSort(){
        Long max = getMax();
        Long exp = 1L;

        while(max / exp > 0){
            countingSort(exp);
            exp *= base;
        }
    }
}