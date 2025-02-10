package Classi;

import Interfacce.Osservabile;
import Interfacce.Osservatore;
import Interfacce.StatoPartita;
import Enum.Esito;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Partita implements Osservabile {
    private LocalDateTime data;
    private Partecipante partecipante1;
    private Partecipante partecipante2;
    private Campo campo;

    private StatoPartita statoPartita;
    private List<Osservatore> osservatori;


    // ********************* Costruttore
    public Partita(Partecipante partecipante1, Partecipante partecipante2) {
        this.partecipante1 = partecipante1;
        this.partecipante2 = partecipante2;
        this.osservatori = new ArrayList<>();

        statoPartita = new PartitaDaDisputare(this);

        data = null;
        campo = null;
    }


    // ********************* Metodi Osservabile
    public void attach(Osservatore osservatore) {
        this.osservatori.add(osservatore);
    }

    public void detach(Osservatore osservatore) {
        this.osservatori.remove(osservatore);
    }

    public void notifyChangeState(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        for( Osservatore osservatore : osservatori)
            osservatore.update(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2, this.partecipante1, this.partecipante2);
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        if (statoPartita instanceof PartitaDaDisputare) {
            statoPartita.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2);
            notifyChangeState(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2);
        }
    }


    // ********************* Getter e Setter
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public void setStatoPartita(StatoPartita statoPartita) {
        this.statoPartita = statoPartita;
    }

    public Campo getCampo() {
        return campo;
    }

    public LocalDateTime getData() {
        return data;
    }


    @Override
    public String toString() {
        return "Partita{" +
                "data=" + data +
                ", partecipante1=" + partecipante1 +
                ", partecipante2=" + partecipante2 +
                ", campo=" + campo +
                ", statoPartita=" + statoPartita +
                '}';
    }
}
