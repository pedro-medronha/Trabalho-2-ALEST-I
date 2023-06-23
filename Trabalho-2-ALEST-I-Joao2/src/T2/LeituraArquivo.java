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
    private int anoDataExtracao;
    private int mesDataExtracao;
    private int diaDataExtracao;
    private int horaDataExtracao;
    private int minDataExtracao;
    private String descricao;
    private String estado;
    private String complemento;
    private int diaImplantacao;
    private int mesImplantacao;
    private int anoImplantacao;
    private String localInstalacao;
    private String fluxo;
    private String lado;
    private String cruzamento;
    private String defronte;
    private double numInicial;
    private double numFinal;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(colunas[0], formatter);
        anoDataExtracao = dateTime.getYear();
        mesDataExtracao = dateTime.getMonthValue();
        diaDataExtracao = dateTime.getDayOfMonth();
        horaDataExtracao = dateTime.getHour();
        minDataExtracao = dateTime.getMinute();

        descricao = colunas[1];
        estado = colunas[2];
        complemento = colunas[3];

        anoImplantacao = 0;
        mesImplantacao = 0;
        diaImplantacao = 0;
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

        if (colunas[6].equals("")) {
            numInicial = 0;
        } else {
            numInicial = Double.parseDouble(colunas[6]);
        }

        if (colunas[7].equals("")) {
            numFinal = 0;
        } else {
            numFinal = Double.parseDouble(colunas[7]);
        }

        defronte = colunas[8];
        cruzamento = colunas[9];
        lado = colunas[10];

        fluxo = "";
        if (colunas.length >= 12) {
            fluxo = colunas[11];
        }

        localInstalacao = "";
        if (colunas.length >= 13) {
            localInstalacao = colunas[12];
        }

        Logradouro logradouro = new Logradouro(logradouroTipo, logradouroNome);
        listaLogradouro.atualizar(logradouro);

        // Debug print statements
        System.out.println("Processed line: " + Arrays.toString(colunas));
        System.out.println("Logradouro: " + logradouroTipo + " " + logradouroNome);
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

    public ListaLogradouro getListaLogradouro() {
        return this.listaLogradouro;
    }

    public void setListaLogradouro(ListaLogradouro listaLogradouro) {
        this.listaLogradouro = listaLogradouro;
    }

    public String getCaminhoArquivo() {
        return this.caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public int getAnoDataExtracao() {
        return this.anoDataExtracao;
    }

    public void setAnoDataExtracao(int anoDataExtracao) {
        this.anoDataExtracao = anoDataExtracao;
    }

    public int getMesDataExtracao() {
        return this.mesDataExtracao;
    }

    public void setMesDataExtracao(int mesDataExtracao) {
        this.mesDataExtracao = mesDataExtracao;
    }

    public int getDiaDataExtracao() {
        return this.diaDataExtracao;
    }

    public void setDiaDataExtracao(int diaDataExtracao) {
        this.diaDataExtracao = diaDataExtracao;
    }

    public int getHoraDataExtracao() {
        return this.horaDataExtracao;
    }

    public void setHoraDataExtracao(int horaDataExtracao) {
        this.horaDataExtracao = horaDataExtracao;
    }

    public int getMinDataExtracao() {
        return this.minDataExtracao;
    }

    public void setMinDataExtracao(int minDataExtracao) {
        this.minDataExtracao = minDataExtracao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getDiaImplantacao() {
        return this.diaImplantacao;
    }

    public void setDiaImplantacao(int diaImplantacao) {
        this.diaImplantacao = diaImplantacao;
    }

    public int getMesImplantacao() {
        return this.mesImplantacao;
    }

    public void setMesImplantacao(int mesImplantacao) {
        this.mesImplantacao = mesImplantacao;
    }

    public int getAnoImplantacao() {
        return this.anoImplantacao;
    }

    public void setAnoImplantacao(int anoImplantacao) {
        this.anoImplantacao = anoImplantacao;
    }

    public String getLocalInstalacao() {
        return this.localInstalacao;
    }

    public void setLocalInstalacao(String localInstalacao) {
        this.localInstalacao = localInstalacao;
    }

    public String getFluxo() {
        return this.fluxo;
    }

    public void setFluxo(String fluxo) {
        this.fluxo = fluxo;
    }

    public String getLado() {
        return this.lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getCruzamento() {
        return this.cruzamento;
    }

    public void setCruzamento(String cruzamento) {
        this.cruzamento = cruzamento;
    }

    public String getDefronte() {
        return this.defronte;
    }

    public void setDefronte(String defronte) {
        this.defronte = defronte;
    }

    public double getNumInicial() {
        return this.numInicial;
    }

    public void setNumInicial(double numInicial) {
        this.numInicial = numInicial;
    }

    public double getNumFinal() {
        return this.numFinal;
    }

    public void setNumFinal(double numFinal) {
        this.numFinal = numFinal;
    }
}