package T2;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ListaLogradouro {

    private Nodo fim;
    private Nodo inicio;
    private int quantidade;
    private Map<String, Logradouro> logradouros;

    public ListaLogradouro() {
        inicio = new Nodo(null);
        fim = new Nodo(null);
        fim.anterior = inicio;
        inicio.proximo = fim;
        quantidade = 0;
        logradouros = new HashMap<>();
    }

    public class Nodo {
        public Nodo anterior;
        public Nodo proximo;
        public Logradouro logradouro;
        public ListaSinalizacoes listaSinalizacoes;

        public Nodo(Logradouro l) {
            this.logradouro = l;
            this.listaSinalizacoes = new ListaSinalizacoes();
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
            logradouros.put(logradouro.getNome(), logradouro);
        }
    }

    private void adicionarPrimeiroNodo(Logradouro logradouro) {
        Nodo newNodo = new Nodo(logradouro);
        newNodo.listaSinalizacoes = logradouro.getListaSinalizacoes();
        newNodo.anterior = inicio;
        newNodo.proximo = fim;
        inicio.proximo = newNodo;
        fim.anterior = newNodo;
    }

    private void adicionarNoFinal(Logradouro logradouro) {
        Nodo newNodo = new Nodo(logradouro);
        newNodo.listaSinalizacoes = logradouro.getListaSinalizacoes();
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

    public String obterLogradouroComMaisSinalizacoes() {
        if (logradouros.isEmpty()) {
            return null;
        }

        String logradouroComMaisSinalizacoes = null;
        int maxSignalizations = 0;

        for (Logradouro logradouro : logradouros.values()) {
            int signalizations = logradouro.getListaSinalizacoes().getQuantidade();
            if (signalizations > maxSignalizations) {
                maxSignalizations = signalizations;
                logradouroComMaisSinalizacoes = logradouro.getNome();
            }
        }

        return logradouroComMaisSinalizacoes;
    }

    public String obterMesMaisSinalizacoes(String nomeLogradouro) {
        if (nomeLogradouro != null) {
            Logradouro logradouro = obterLogradouro(nomeLogradouro);
            if (logradouro != null) {
                ListaSinalizacoes listaSinalizacoes = logradouro.getListaSinalizacoes();
                if (!listaSinalizacoes.estaVazia()) {
                    HashMap<String, Integer> mesesSinalizacoes = new HashMap<>();
                    Sinalizacao sinalizacao = listaSinalizacoes.getInicio();

                    while (sinalizacao != null) {
                        String mesAno = sinalizacao.getDataImplantacao().format(DateTimeFormatter.ofPattern("MM/yyyy"));
                        mesesSinalizacoes.put(mesAno, mesesSinalizacoes.getOrDefault(mesAno, 0) + 1);
                        sinalizacao = sinalizacao.getProximo();
                    }

                    int maxSinalizacoes = 0;
                    String mesMaisSinalizacoes = "";

                    for (Map.Entry<String, Integer> entry : mesesSinalizacoes.entrySet()) {
                        String mesAno = entry.getKey();
                        int quantidadeSinalizacoes = entry.getValue();

                        if (quantidadeSinalizacoes > maxSinalizacoes) {
                            maxSinalizacoes = quantidadeSinalizacoes;
                            mesMaisSinalizacoes = mesAno;
                        }
                    }

                    return mesMaisSinalizacoes;
                }
            }
        }
        return null;
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
