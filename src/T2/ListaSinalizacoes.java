package T2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListaSinalizacoes {

    private int quantidade;
    private Sinalizacao inicio;
    private Sinalizacao fim;

    public ListaSinalizacoes() {
        this.quantidade = 0;
        this.inicio = null;
        this.fim = null;
    }
    // Método de adição com Insertion Sort, baseado na data de implantação
    private void adicionarRecursivo(Sinalizacao novaSinalizacao, Sinalizacao aux) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date auxDate = sdf.parse(aux.dataImplantacao);
            Date auxDateProximo = sdf.parse(aux.getProximo().dataImplantacao);
            Date dataNova = sdf.parse(novaSinalizacao.dataImplantacao);
            // verifica se a data é menor que o inicio, para adicionar no começo
            if (aux.equals(this.inicio) && dataNova.before(auxDate)) {
                novaSinalizacao.setProximo(aux);
                this.inicio = novaSinalizacao;
            }
            // verifica se a data é maior que o final, para adicionar no fim da lista
            else if (aux.equals(this.fim) && dataNova.after(auxDate)) {
                aux.setProximo(novaSinalizacao);
                this.fim = novaSinalizacao;
            }
            // verifica se a data é maior que a da Sinalização atual e menor que a da
            // proxima,
            // para adicionar ao meio delas
            else if (dataNova.after(auxDate) && dataNova.before(auxDateProximo)) {
                novaSinalizacao.setProximo(aux.getProximo());
                aux.setProximo(novaSinalizacao);
            }
            else {
                adicionarRecursivo(novaSinalizacao, aux.getProximo());
            }
        } catch (ParseException e) {
            e.getMessage();
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