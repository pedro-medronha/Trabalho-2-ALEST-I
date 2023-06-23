package T2;

public class Main {
    public static void main(String[] args) {
        ListaLogradouro listaLogradouro = new ListaLogradouro();
        String caminhoArquivo = "D:\\AlestFinal\\Trabalho-2-ALEST-I\\dataEditado.csv";

        LeituraArquivo leituraArquivo = new LeituraArquivo(listaLogradouro, caminhoArquivo);
        leituraArquivo.realizarLeitura();

        // Access and work with the populated list of logradouros
        // ...
    }
}
