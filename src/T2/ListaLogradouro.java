package T2;

public class ListaLogradouro {

    private Logradouro fim;
    private Logradouro inicio;
    private int quantidade;

    public ListaLogradouro() {
        inicio = new Logradouro(null, null, null, null, null);
        fim = new Logradouro(null, null, null, null, null);
        quantidade = 0;
    }

    // public Logradouro obterLogradouro(String nome) {
    //
    // }

    // public boolean existe(String nome) {
    //
    // }

    public void adicionar(Logradouro logradouro) {
        ///
    }

    private void adicionarPrimeiroNodo(Logradouro logradouro) {
        ///
    }

    private void adicionarNoFinal(Logradouro logradouro) {
        ///
    }

    public void atualizar(Logradouro logradouro) {
        ///
    }

    @Override
    public String toString() {
        String ret = "{";
        Logradouro aux = null;
        if (quantidade > 0)
            aux = inicio.getProximo();
        for (int i = 0; i < quantidade; i++) {
            ret = ret + aux.getTipo() + " " + aux.getNome() + System.lineSeparator();
            aux = aux.getProximo();
        }
        ret = ret + "}";
        return ret;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
