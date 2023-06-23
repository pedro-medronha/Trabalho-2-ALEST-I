package T2;

import java.time.LocalDateTime;

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

    public Sinalizacao(String descricao, String estado, String complemento, LocalDateTime dataImplantacao,
            String numeroInicial, String numeroFinal, String defronte, String cruzamentoNome, String lado,
            String fluxo, String localDeInstalacao) {
        this.descricao = descricao;
        this.estado = estado;
        this.complemento = complemento;
        this.dataImplantacao = dataImplantacao;
        this.numeroInicial = numeroInicial;
        this.numeroFinal = numeroFinal;
        this.defronte = defronte;
        this.cruzamentoNome = cruzamentoNome;
        this.lado = lado;
        this.fluxo = fluxo;
        this.localDeInstalacao = localDeInstalacao;
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
}