package Classi;

public class Campo {
    private int codice;
    private String tipologia;

    // ********************* Costruttore
    public Campo(int codice, String tipologia) {
        this.codice = codice;
        this.tipologia = tipologia;
    }


    @Override
    public String toString() {
        return "Campo{" +
                "codice=" + codice +
                ", tipologia='" + tipologia + '\'' +
                '}';
    }
}
