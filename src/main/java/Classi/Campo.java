package Classi;

import java.util.Objects;

public class Campo {
    private int codice;
    private String tipologia;

    // ********************* Costruttore
    public Campo(int codice, String tipologia) {
        this.codice = codice;
        this.tipologia = tipologia;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Campo campo = (Campo) o;
        return codice == campo.codice && Objects.equals(tipologia, campo.tipologia);
    }


    @Override
    public String toString() {
        return "Campo {" +
                "codice = " + codice +
                ", tipologia = '" + tipologia + '\'' +
                '}';
    }
}
