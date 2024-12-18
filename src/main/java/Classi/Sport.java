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
}