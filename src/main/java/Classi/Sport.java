package Classi;

public class Sport {
    private int codice;
    private String nome;


    // ********************* Costruttore
    public Sport(int codice, String nome) {
        this.codice = codice;
        this.nome = nome;
    }


    @Override
    public boolean equals(Object obj) {
        return this.nome.equals(((Sport) obj).nome) && this.codice == ((Sport) obj).codice;
    }


    @Override
    public String toString() {
        return "Sport {" +
                "codiceSport = " + codice +
                ", nomeSport = '" + nome + '\'' +
                "} \n";
    }
}