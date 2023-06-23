package T2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Sinalizacao {
    public String descricao;
    public String estado;
    public String complemento;
    public LocalDateTime dataImplantacao;
    public String numeroInicial;
    public String numeroFinal;
    public String defronte;
    public String cruzamentoNome;
    public String lado;
    public String fluxo;
    public String localDeInstalacao;
    private Sinalizacao proximo;
    private Sinalizacao anterior;

    public Sinalizacao(int anoDataExtracao, int mesDataExtracao, int diaDataExtracao, int horaDataExtracao,
            int minDataExtracao, String descricao, String estado, String complemento, int diaImplantacao,
            int mesImplantacao, int anoImplantacao, String localInstalacao, String fluxo, String lado,
            String cruzamento, String defronte, double numInicial, double numFinal) {
        this.descricao = descricao;
        this.estado = estado;
        this.complemento = complemento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataImplantacao = LocalDate
                    .parse(String.format("%02d/%02d/%04d", diaImplantacao, mesImplantacao, anoImplantacao), formatter);
            this.dataImplantacao = dataImplantacao.atTime(horaDataExtracao, minDataExtracao);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido: " + e.getMessage());
        }
        this.numeroInicial = Double.toString(numInicial);
        this.numeroFinal = Double.toString(numFinal);
        this.defronte = defronte;
        this.cruzamentoNome = cruzamento;
        this.lado = lado;
        this.fluxo = fluxo;
        this.localDeInstalacao = localInstalacao;
        this.proximo = null;
    }

    public LocalDateTime getDataImplantacao() {
        return dataImplantacao;
    }

    public Sinalizacao getProximo() {
        return proximo;
    }

    public void setProximo(Sinalizacao proximo) {
        this.proximo = proximo;
    }

    public Sinalizacao getAnterior() {
        return anterior;
    }

    public void setAnterior(Sinalizacao anterior) {
        this.anterior = anterior;
    }
}
