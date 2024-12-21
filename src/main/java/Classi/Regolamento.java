package Classi;

public class Regolamento {
    private int numeroSquadre;
    private int numeroMaxComponentiSquadra;
    private int punteggioVittoria;
    private int punteggioSconfitta;
    private int punteggioPareggio;

    public Regolamento(int numeroSquadre, int numeroMaxComponentiSquadra, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        this.numeroSquadre = numeroSquadre;
        this.numeroMaxComponentiSquadra = numeroMaxComponentiSquadra;
        this.punteggioVittoria = punteggioVittoria;
        this.punteggioSconfitta = punteggioSconfitta;
        this.punteggioPareggio = punteggioPareggio;
    }

    @Override
    public String toString() {
        return "Regolamento{" +
                "numeroSquadre=" + numeroSquadre +
                ", numeroMaxComponentiSquadra=" + numeroMaxComponentiSquadra +
                ", punteggioVittoria=" + punteggioVittoria +
                ", punteggioSconfitta=" + punteggioSconfitta +
                ", punteggioPareggio=" + punteggioPareggio +
                '}';
    }
}
