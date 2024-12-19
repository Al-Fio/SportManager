package Classi;
import Enum.TipoModalita;

public class Modalita {
    private int codice;
    private TipoModalita nome;

    public Modalita(int codice, TipoModalita nome) {
        this.codice = codice;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Modalita{" +
                "codice=" + codice +
                ", nome=" + nome +
                '}';
    }
}
