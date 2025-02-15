package Classi;

public class Modalita {
    private int codice;
    private String nome;


    // ********************* Costruttore
    public Modalita(int codice, String nome) {
        this.codice = codice;
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Modalita {" +
                "codice = " + codice +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
