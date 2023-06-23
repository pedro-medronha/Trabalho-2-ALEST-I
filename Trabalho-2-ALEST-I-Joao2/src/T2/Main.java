package T2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ListaLogradouro listaLogradouro = new ListaLogradouro();
            String caminhoArquivo = "Trabalho-2-ALEST-I-Joao2\\dataEditado.csv";

            LeituraArquivo leituraArquivo = new LeituraArquivo(listaLogradouro, caminhoArquivo);
            leituraArquivo.realizarLeitura();
            System.out.println(listaLogradouro.existe(caminhoArquivo));
            while (true) {
                System.out.println("Selecione uma opcao:");
                System.out.println("1. Navegar pelas ruas");
                System.out.println("2. Rua/Av/Trav com mais sinalizacoes");
                System.out.println("3. Mes com mais sinalizacoes implantadas");
                System.out.println("4. Sair do programa");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println(listaLogradouro);
                        System.out.println("Insira a Rua/Av/Trav de onde gostaria de partir:");
                        scanner.nextLine();
                        String nomeLogradouro = scanner.nextLine();
                        if (listaLogradouro.existe(nomeLogradouro)) {
                            Logradouro logradouro = listaLogradouro.obterLogradouro(nomeLogradouro);
                            ListaSinalizacoes listaSinalizacoes = logradouro.getListaSinalizacoes();

                            if (listaSinalizacoes.getQuantidade() == 0) {
                                System.out.println("Nenhuma sinalizacao registrada.");
                                break;
                            }

                            Sinalizacao sinalizacaoAtual = listaSinalizacoes.getInicio();

                            while (sinalizacaoAtual != null) {
                                System.out.println("rua " + nomeLogradouro);
                                System.out.println("sinalizacoes " + listaSinalizacoes.getQuantidade());
                                System.out.println("sinalizacao " + sinalizacaoAtual);
                                System.out.println("selecione a opcao");
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
                        System.out.println("Informe a rua/avenida/travessa para consultar:");
                        scanner.nextLine();
                        nomeLogradouro = scanner.nextLine();
                        String mesMaisSinalizacoes = listaLogradouro.obterMesMaisSinalizacoes(nomeLogradouro);
                        if (mesMaisSinalizacoes != null) {
                            System.out.println("Mês com mais sinalizações implantadas em " + nomeLogradouro + ": "
                                    + mesMaisSinalizacoes);
                        } else {
                            System.out.println("Não foram encontradas sinalizações para " + nomeLogradouro);
                        }
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("opcao invalida");
                }
            }
        }
    }
}