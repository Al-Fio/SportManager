package Classi;

public class Sport {
    private int codice;
    private String name;

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sport(int codice, String name) {
        this.codice = codice;
        this.name = name; // manca il nome
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Sport) obj).name) && this.codice == ((Sport) obj).codice;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "codice=" + codice +
                ", name='" + name + '\'' +
                '}';
    }
}