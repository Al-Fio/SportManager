package Classi;

public class Sport {
    private int codice;
    private String nome;

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public Sport(int codice, String nome) {
        this.codice = codice;
        this.nome = nome; // manca il nome
    }

    @Override
    public boolean equals(Object obj) {
        return this.nome.equals(((Sport) obj).nome) && this.codice == ((Sport) obj).codice;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "codice=" + codice +
                ", name='" + nome + '\'' +
                '}';
    }
}