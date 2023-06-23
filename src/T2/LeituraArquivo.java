package T2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class LeituraArquivo {
    private ListaLogradouro listaLogradouro;
    private String caminhoArquivo;

    public LeituraArquivo(ListaLogradouro listaLogradouro, String caminhoArquivo) {
        this.listaLogradouro = listaLogradouro;
        this.caminhoArquivo = caminhoArquivo;
    }

    public void realizarLeitura() {
        int linhasLidas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] colunas = linha.split(";");
                if (colunas.length >= 13) {
                    processarLinha(colunas);
                    linhasLidas++;
                } else {
                    System.out.println("Invalid line format: " + linha);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Linhas lidas: " + linhasLidas);
    }

    private void processarLinha(String[] colunas) {
        if (colunas.length >= 13) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime dateTime = LocalDateTime.parse(colunas[0], formatter);
            int anoDataExtracao = dateTime.getYear();
            int mesDataExtracao = dateTime.getMonthValue();
            int diaDataExtracao = dateTime.getDayOfMonth();
            int horaDataExtracao = dateTime.getHour();
            int minDataExtracao = dateTime.getMinute();

            String descricao = colunas[1];
            String estado = colunas[2];
            String complemento = colunas[3];

            int anoImplantacao = 0;
            int mesImplantacao = 0;
            int diaImplantacao = 0;
            if (!colunas[4].equals("")) {
                String dateValue = colunas[4];
                if (dateValue.contains("-")) {
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                } else {
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                }
                LocalDate date = LocalDate.parse(dateValue, formatter);
                anoImplantacao = date.getYear();
                mesImplantacao = date.getMonthValue();
                diaImplantacao = date.getDayOfMonth();
            }

            String logradouroTipo = colunas[5].split(" ", 2)[0];
            String logradouroNome = colunas[5].split(" ", 2)[1];

            double numInicial;
            if (colunas[6].equals("")) {
                numInicial = 0;
            } else {
                numInicial = Double.parseDouble(colunas[6]);
            }

            double numFinal;
            if (colunas[7].equals("")) {
                numFinal = 0;
            } else {
                numFinal = Double.parseDouble(colunas[7]);
            }

            String defronte = colunas[8];
            String cruzamento = colunas[9];
            String lado = colunas[10];

            String fluxo = "";
            if (colunas.length >= 12) {
                fluxo = colunas[11];
            }

            String localInstalacao = "";
            if (colunas.length >= 13) {
                localInstalacao = colunas[12];
            }

            Logradouro logradouro = new Logradouro(logradouroTipo, logradouroNome);
            listaLogradouro.atualizar(logradouro);

            // Debug print statements
            System.out.println("Processed line: " + Arrays.toString(colunas));
            System.out.println("Logradouro: " + logradouroTipo + " " + logradouroNome);
        }
    }

    public String obterRuaComMaisSinalizacoes() {
        String ruaComMaisSinalizacoes = listaLogradouro.obterLogradouroComMaisSinalizacoes();
        if (ruaComMaisSinalizacoes != null) {
            return ruaComMaisSinalizacoes;
        }
        return "N/A";
    }

    public String obterMesesMaisSinalizacoes(String nomeLogradouro) {
        String mesesMaisSinalizacoes = listaLogradouro.obterMesMaisSinalizacoes(nomeLogradouro);
        if (mesesMaisSinalizacoes != null) {
            return mesesMaisSinalizacoes;
        }
        return "N/A";
    }
}
