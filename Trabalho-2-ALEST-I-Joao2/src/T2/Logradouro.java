package T2;

public class Logradouro {

    private Logradouro anterior;
    private Logradouro proximo;
    private String nome;
    private String tipo;
    private ListaSinalizacoes listaSinalizacoes;

    public enum Tipo {
        AVENIDA("AV"),
        RUA("R"),
        TRAVESSA("TRAV"),
        BECO("BC"),
        PRACA("PCA");

        private final String sigla;

        Tipo(String sigla) {
            this.sigla = sigla;
        }

        public String getSigla() {
            return sigla;
        }
    }

    public Logradouro(Logradouro anterior, Logradouro proximo, String nome, ListaSinalizacoes listaSinalizacoes,
            String tipo) {
        this.anterior = anterior;
        this.proximo = proximo;
        this.nome = nome;
        this.listaSinalizacoes = new ListaSinalizacoes();
        this.tipo = tipo;
    }

    public Logradouro(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }

    public Logradouro getAnterior() {
        return this.anterior;
    }

    public Logradouro getProximo() {
        return this.proximo;
    }

    public String getNome() {
        return this.nome;
    }

    public ListaSinalizacoes getListaSinalizacoes() {
        if (listaSinalizacoes == null) {
            listaSinalizacoes = new ListaSinalizacoes();
        }
        return listaSinalizacoes;
    }

    public String getTipo() {
        return this.tipo;
    }
}