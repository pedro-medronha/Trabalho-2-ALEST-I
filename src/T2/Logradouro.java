package T2;

public class Logradouro {

    private Logradouro anterior;
    private Logradouro proximo;
    private String nome;

    public enum Tipo {
        AVENIDA("AV"),
        RUA("R"),
        TRAVESSA("TRAV");

        private final String sigla;

        Tipo(String sigla) {
            this.sigla = sigla;
        }

        public String getSigla() {
            return sigla;
        }
    }

    public Logradouro(Logradouro anterior, Logradouro proximo, String nome) {
        this.anterior = anterior;
        this.proximo = proximo;
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
}
