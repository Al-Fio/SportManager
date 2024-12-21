package Classi;

public class Modalita {
    private int codice;
    private String nome;

    public Modalita(int codice, String nome) {
        this.codice = codice;
        this.nome = nome;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Modalita{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                '}';
    }
}
