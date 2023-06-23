package T2;

import java.time.format.DateTimeFormatter;

public class ListaSinalizacoes {
    private Sinalizacao inicio;
    private Sinalizacao fim;
    private int quantidade;

    public ListaSinalizacoes() {
        inicio = null;
        fim = null;
        quantidade = 0;
    }

    public boolean estaVazia() {
        return quantidade == 0;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionar(Sinalizacao sinalizacao) {
        if (sinalizacao != null) {
            if (estaVazia()) {
                inicio = sinalizacao;
                fim = sinalizacao;
            } else {
                fim.setProximo(sinalizacao);
                sinalizacao.setAnterior(fim);
                fim = sinalizacao;
            }
            quantidade++;
        }
    }

    public Sinalizacao getInicio() {
        return inicio;
    }

    public Sinalizacao getFim() {
        return fim;
    }

    public Sinalizacao getUltimaSinalizacao() {
        return fim;
    }

    public Sinalizacao obterProximaSinalizacao(Sinalizacao sinalizacaoAtual) {
        if (sinalizacaoAtual != null) {
            return sinalizacaoAtual.getProximo();
        }
        return null;
    }

    public Sinalizacao obterSinalizacaoAnterior(Sinalizacao sinalizacaoAtual) {
        if (sinalizacaoAtual != null) {
            return sinalizacaoAtual.getAnterior();
        }
        return null;
    }

    public int contarSinalizacoesMes(String mesAno) {
        int quantidadeSinalizacoes = 0;
        Sinalizacao sinalizacao = inicio;

        while (sinalizacao != null) {
            String mesAnoSinalizacao = sinalizacao.getDataImplantacao().format(DateTimeFormatter.ofPattern("MM/yyyy"));
            if (mesAno.equals(mesAnoSinalizacao)) {
                quantidadeSinalizacoes++;
            }
            sinalizacao = sinalizacao.getProximo();
        }

        return quantidadeSinalizacoes;
    }
}
