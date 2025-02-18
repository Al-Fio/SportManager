package Classi;

import Eccezioni.WrongPartException;
import Interfacce.Osservabile;
import Interfacce.Osservatore;
import Interfacce.StatoPartita;
import Enum.Esito;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Partita implements Osservabile, Cloneable{
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

    public void notify(int operazione) {
        for( Osservatore osservatore : osservatori)
            osservatore.update(statoPartita, operazione);
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, boolean aSquadre) {
        if (statoPartita instanceof PartitaDaDisputare) {
            statoPartita.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2, aSquadre);
            notify(0);
        }
    }


    // ********************* Caso d'uso UC9 - Inserisci Statistiche di un Giocatore
    public Partecipante selezionaGiocatorePartita(String CF) {
        Partecipante giocatore = null;
        try {
            giocatore = partecipante1.getElencoComponenti().get(CF);

            if(giocatore == null) {
                giocatore = partecipante2.getElencoComponenti().get(CF);
            }

        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
        }

        return giocatore;
    }

    public void inserisciStatisticheGiocatore(Partecipante partecipante, int puntiEffettuati) throws WrongPartException {
        if(statoPartita instanceof PartitaDaDisputare)
            throw new WrongPartException();
        else {
            statoPartita.inserisciStatisticheGiocatore(partecipante, puntiEffettuati);
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

    public Partecipante getPartecipante1() {
        return partecipante1;
    }

    public Partecipante getPartecipante2() {
        return partecipante2;
    }

    public int getPunteggioPartecipante1() throws WrongPartException {
        if(!(statoPartita instanceof PartitaDisputata)) {
            throw new WrongPartException();
        } else {
            statoPartita.getPartecipante1();
        }

        return 0;
    }

    public int getPunteggioPartecipante2() throws WrongPartException {
        if(!(statoPartita instanceof PartitaDisputata)) {
            throw new WrongPartException();
        } else {
            statoPartita.getPartecipante2();
        }

        return 0;
    }

    public Esito getEsitoPartecipante1() throws WrongPartException {
        if(!(statoPartita instanceof PartitaDisputata)) {
            throw new WrongPartException();
        } else {
            statoPartita.getEsitoPartecipante1();
        }

        return null;
    }

    public Esito getEsitoPartecipante2() throws WrongPartException {
        if(!(statoPartita instanceof PartitaDisputata)) {
            throw new WrongPartException();
        } else {
            statoPartita.getEsitoPartecipante2();
        }

        return null;
    }

    public List<StatisticheGiocatore> getStatistiche() throws WrongPartException {
        if(!(statoPartita instanceof PartitaDisputata)) {
            throw new WrongPartException();
        } else {
            statoPartita.getStatistiche();
        }

        return null;
    }

    public StatoPartita getStatoPartita() {
        return statoPartita;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Partita clone = (Partita) super.clone();

        clone.partecipante1 = (Partecipante) partecipante1.clone();
        clone.partecipante2 = (Partecipante) partecipante2.clone();

        clone.setStatoPartita(new PartitaDaDisputare(clone));

        if(osservatori != null) {
            clone.osservatori = new ArrayList<>();
            for(Osservatore osservatore : osservatori) {
                clone.attach(osservatore);
            }
        }

        return clone;
    }


    @Override
    public String toString() {
        return "Partita [" +
                "partecipante1: " + partecipante1.getId() +
                ", partecipante2: " + partecipante2.getId() +
                ", campo: " + campo +
                "data = " + data +
                "]\n\n";
    }
}
