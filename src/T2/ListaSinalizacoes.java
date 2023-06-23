package T2;

import java.time.LocalDateTime;

public class ListaSinalizacoes {
    private int quantidade;
    private Sinalizacao inicio;
    private Sinalizacao fim;

    public ListaSinalizacoes() {
        this.quantidade = 0;
        this.inicio = null;
        this.fim = null;
    }

    private void adicionarRecursivo(Sinalizacao novaSinalizacao, Sinalizacao aux) {
        LocalDateTime auxDate = aux.getDataImplantacao();
        LocalDateTime dataNova = novaSinalizacao.getDataImplantacao();

        if (dataNova.isBefore(auxDate) || dataNova.isEqual(auxDate)) {
            novaSinalizacao.setProximo(aux);

            if (aux.equals(this.inicio)) {
                this.inicio = novaSinalizacao;
            }
        } else if (aux.equals(this.fim)) {
            aux.setProximo(novaSinalizacao);
            this.fim = novaSinalizacao;
        } else if (dataNova.isAfter(auxDate)) {
            adicionarRecursivo(novaSinalizacao, aux.getProximo());
        }
    }

    public void adicionar(Sinalizacao novaSinalizacao) {
        if (this.estaVazia()) {
            this.inicio = novaSinalizacao;
            this.fim = novaSinalizacao;
        } else {
            adicionarRecursivo(novaSinalizacao, this.inicio);
        }
        this.quantidade++;
    }

    public boolean estaVazia() {
        return (this.quantidade == 0);
    }

    public void esvaziarLista() {
        this.inicio = null;
        this.fim = null;
        this.quantidade = 0;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Sinalizacao getInicio() {
        return this.inicio;
    }

    public Sinalizacao getFim() {
        return this.fim;
    }
}
