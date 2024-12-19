package Classi;

public class Regolamento {
    private int codice;
    private String nome;
    private int numeroSquadre;
    private int numeroMaxComponentiSquadra;
    private int punteggioVittoria;
    private int punteggioSconfitta;
    private int punteggioPareggio;

    public Regolamento(int codice, String nome, int numeroSquadre, int numeroMaxComponentiSquadra, int punteggioVittoria, int punteggioSconfitta, int punteggioPareggio) {
        this.codice = codice;
        this.nome = nome;
        this.numeroSquadre = numeroSquadre;
        this.numeroMaxComponentiSquadra = numeroMaxComponentiSquadra;
        this.punteggioVittoria = punteggioVittoria;
        this.punteggioSconfitta = punteggioSconfitta;
        this.punteggioPareggio = punteggioPareggio;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Regolamento{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroSquadre=" + numeroSquadre +
                ", numeroMaxComponentiSquadra=" + numeroMaxComponentiSquadra +
                ", punteggioVittoria=" + punteggioVittoria +
                ", punteggioSconfitta=" + punteggioSconfitta +
                ", punteggioPareggio=" + punteggioPareggio +
                '}';
    }
}
