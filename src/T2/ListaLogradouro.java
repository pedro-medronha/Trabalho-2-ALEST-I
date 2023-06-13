package T2;

public class ListaLogradouro {

    private Nodo fim;
    private Nodo inicio;
    private int quantidade;

    public ListaLogradouro() {
        inicio = new Nodo(null);
        fim = new Nodo(null);
        fim.anterior = inicio;
        inicio.proximo = fim;
        quantidade = 0;
    }

    public class Nodo {
        public Nodo anterior;
        public Nodo proximo;
        public Logradouro logradouro;
        public ListaSinalizacoes listaSinalizacoes;

        public Nodo(Logradouro l) {
            this.logradouro = l;
            listaSinalizacoes = new ListaSinalizacoes();
        }
    }

    public Logradouro obterLogradouro(String nome) {
        if (nome != null) {
            for (Nodo n = inicio.proximo; n != fim; n = n.proximo) {
                if (n.logradouro.getNome().equals(nome)) {
                    return n.logradouro;
                }
            }
        }
        return null;
    }

    public boolean existe(String nomeLogradouro) {
        if (nomeLogradouro != null) {
            for (Nodo nodo = inicio.proximo; nodo != fim; nodo = nodo.proximo) {
                if (nodo.logradouro.getNome().equals(nomeLogradouro)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void adicionar(Logradouro logradouro) {
        if (logradouro != null && !existe(logradouro.getNome())) {
            if (quantidade == 0) {
                adicionarPrimeiroNodo(logradouro);
            } else {
                adicionarNoFinal(logradouro);
            }
            quantidade++;
        }
    }

    private void adicionarPrimeiroNodo(Logradouro logradouro) {
        Nodo newNodo = new Nodo(logradouro);
        newNodo.anterior = inicio;
        newNodo.proximo = fim;
        inicio.proximo = newNodo;
        fim.anterior = newNodo;
    }

    private void adicionarNoFinal(Logradouro logradouro) {
        Nodo newNodo = new Nodo(logradouro);
        Nodo lastNodo = fim.anterior;
        lastNodo.proximo = newNodo;
        newNodo.anterior = lastNodo;
        newNodo.proximo = fim;
        fim.anterior = newNodo;
    }

    public void atualizar(Logradouro logradouro) {
        if (logradouro != null) {
            for (Nodo nodo = inicio.proximo; nodo != fim; nodo = nodo.proximo) {
                if (nodo.logradouro.getNome().equals(logradouro.getNome())) {
                    nodo.logradouro = logradouro;
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("{");
        Nodo aux = inicio.proximo;
        while (aux != fim) {
            ret.append(aux.logradouro.getTipo())
                    .append(" ")
                    .append(aux.logradouro.getNome())
                    .append(System.lineSeparator());
            aux = aux.proximo;
        }
        ret.append("}");
        return ret.toString();
    }
}
