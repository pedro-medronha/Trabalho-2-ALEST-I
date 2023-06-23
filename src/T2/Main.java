package T2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaLogradouro listaLogradouro = new ListaLogradouro();
        String caminhoArquivo = "D:\\AlestFinal\\Trabalho-2-ALEST-I\\dataEditado.csv";

        LeituraArquivo leituraArquivo = new LeituraArquivo(listaLogradouro, caminhoArquivo);
        leituraArquivo.realizarLeitura();
        System.out.println(listaLogradouro.existe(caminhoArquivo));
        System.out.println("Select an option:");
        System.out.println("1. Navegue pelas ruas");
        System.out.println("2. Rua com mais sinalizacao");
        System.out.println("3. Mais meses da rua");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println(listaLogradouro);

                System.out.println("Entre o nome da rua");
                scanner.nextLine();
                String nomeLogradouro = scanner.nextLine();

                if (listaLogradouro.existe(nomeLogradouro)) {
                    Logradouro logradouro = listaLogradouro.obterLogradouro(nomeLogradouro);
                    ListaSinalizacoes listaSinalizacoes = logradouro.getListaSinalizacoes();

                    if (listaSinalizacoes.getQuantidade() == 0) {
                        System.out.println("No signalizations registered.");
                        break;
                    }

                    Sinalizacao sinalizacaoAtual = listaSinalizacoes.getInicio();

                    while (sinalizacaoAtual != null) {
                        System.out.println("rua " + nomeLogradouro);
                        System.out.println("sinalizacoes " + listaSinalizacoes.getQuantidade());
                        System.out.println("sinalizacao " + sinalizacaoAtual);

                        System.out.println("selecione opcao");
                        System.out.println("1. proxima sinalizacao");
                        System.out.println("2. sair");

                        int choice = scanner.nextInt();

                        if (choice == 1) {
                            sinalizacaoAtual = listaSinalizacoes.obterProximaSinalizacao(sinalizacaoAtual);
                        } else if (choice == 2) {
                            break;
                        } else {
                            System.out.println("erro");
                        }
                    }
                } else {
                    System.out.println("rua nao existe");
                }
                break;

            case 2:
                String logradouroComMaisSinalizacoes = listaLogradouro.obterLogradouroComMaisSinalizacoes();
                System.out.println("rua com mais sinalizacao " + logradouroComMaisSinalizacoes);
                break;

            case 3:
                System.out.println("entre nome da rua");
                scanner.nextLine();
                nomeLogradouro = scanner.nextLine();
                String mesesMaisSinalizacoes = leituraArquivo.obterMesesMaisSinalizacoes(nomeLogradouro);
                System.out.println(
                        "mes com mais sinalizacoes " + nomeLogradouro + ": " + mesesMaisSinalizacoes);
                break;

            default:
                System.out.println("opcao invalida");
        }
    }
}
