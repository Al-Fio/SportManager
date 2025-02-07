package Classi;

public class Regolamento {
    private int numeroSquadre;
    private int numeroMinimoGiocatori;
    private int punteggioVittoria;
    private int punteggioSconfitta;
    private int punteggioPareggio;


    // ********************* Costruttore
    public Regolamento(int numeroSquadre, int numeroMinimoGiocatori, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        this.numeroSquadre = numeroSquadre;
        this.numeroMinimoGiocatori = numeroMinimoGiocatori;
        this.punteggioVittoria = punteggioVittoria;
        this.punteggioSconfitta = punteggioSconfitta;
        this.punteggioPareggio = punteggioPareggio;
    }


    // ********************* Getter e Setter
    public int getNumeroMinimoGiocatori() {
        return numeroMinimoGiocatori;
    }


    @Override
    public String toString() {
        return "Regolamento{" +
                "numeroSquadre=" + numeroSquadre +
                ", numeroMinimoGiocatori=" + numeroMinimoGiocatori +
                ", punteggioVittoria=" + punteggioVittoria +
                ", punteggioSconfitta=" + punteggioSconfitta +
                ", punteggioPareggio=" + punteggioPareggio +
                '}';
    }
}
