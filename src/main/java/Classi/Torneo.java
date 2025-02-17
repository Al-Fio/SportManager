package Classi;

import Eccezioni.WrongPartException;

import java.time.LocalDateTime;
import java.util.*;

import Enum.Esito;

public class Torneo implements Cloneable {
    private String nome;
    private float quotaIscrizione;
    private int codice;

    private Sport sport;
    private Regolamento regolamento;
    private Modalita modalita;

    private Map<String, Partecipante> elencoPartecipanti;
    private Map<String, GiocatoreSingolo> elencoGiocatoriSingoliCorrente;
    private Partecipante partecipanteCorrente;

    private Calendario calendario;
    private Calendario calendarioCorrente;

    private Classifica classifica;

    private ClassificaGiocatori classificaGiocatori;


    // ********************* Costruttore
    public Torneo(int codice, String nome, Sport sport, Modalita modalita, float quotaIscrizione) {
        this.codice = codice;
        this.nome = nome;
        this.sport = sport;
        this.regolamento = null;
        this.modalita = modalita;
        this.quotaIscrizione = quotaIscrizione;
        this.calendario = null;

        this.classifica = new Classifica();

        elencoPartecipanti = new HashMap<String, Partecipante>();
    }


    // ********************* Caso d'uso UC1 - Inserisci nuovo Torneo
    public void nuovoRegolamento(int numeroSquadre, int numeroMinimoGiocatori, int punteggioVittoria, int punteggioPareggio, int punteggioSconfitta) {
        regolamento = new Regolamento(numeroSquadre, numeroMinimoGiocatori, punteggioVittoria, punteggioPareggio, punteggioSconfitta);

        classifica.setRegolamento(regolamento);

        if(regolamento.getNumeroMinimoGiocatori() != 1) {
            classificaGiocatori = new ClassificaGiocatori();
        }
    }


    // ********************* Caso d'uso UC3 - Inserisci nuova Squadra/Giocatore Singolo nel Torneo
    public boolean verificaUnicitaPartecipanteTorneo(String cf) {
        boolean verifica = false;

        for (Partecipante partecipante : elencoPartecipanti.values()) {
            if (partecipante.getClass().equals(Squadra.class)) {
                try {
                    verifica = partecipante.verificaPresenzaComponente(cf);
                } catch (WrongPartException e) {
                    throw new RuntimeException(e);
                }
            } else {
                verifica = partecipante.getId().equals(cf);
            }

            if (verifica) return true;
        }

        return false;
    }

    public int numeroMinimoGiocatori() {
        return regolamento.getNumeroMinimoGiocatori();
    }

    public Collection<GiocatoreSingolo> visualizzaGiocatoriSingoli() {
        if (elencoGiocatoriSingoliCorrente == null)
            return null;
        else
            return elencoGiocatoriSingoliCorrente.values();
    }

    public boolean accorpaGiocatoreSingolo(String cf) {
        GiocatoreSingolo giocatore = elencoGiocatoriSingoliCorrente.get(cf);

        if(giocatore == null) {return false;}

        try {
            partecipanteCorrente.aggiungiComponente(cf, giocatore);
            System.out.println(partecipanteCorrente);
        } catch (WrongPartException e) {
            System.err.println(e.getMessage());
            return false;
        }

        elencoGiocatoriSingoliCorrente.remove(cf);
        return true;
    }

    public void confermaIscrizionePartecipante() {
        String id = partecipanteCorrente.getId();

        elencoPartecipanti.put(id, partecipanteCorrente);

        partecipanteCorrente = null;

        /*if(elencoGiocatoriSingoliCorrente != null) {
            for (Partecipante partecipante : elencoPartecipanti.values())
                if ((partecipante.getClass().equals(GiocatoreSingolo.class)) && (!elencoGiocatoriSingoliCorrente.containsKey(partecipante.getId())))
                    elencoPartecipanti.remove(partecipante.getId());

            elencoGiocatoriSingoliCorrente = null;
        }*/


        if (elencoGiocatoriSingoliCorrente != null) {
            Iterator<Partecipante> iterator = elencoPartecipanti.values().iterator();

            while (iterator.hasNext()) {
                Partecipante partecipante = iterator.next();
                if (partecipante.getClass().equals(GiocatoreSingolo.class) &&
                        !elencoGiocatoriSingoliCorrente.containsKey(partecipante.getId())) {
                    iterator.remove();
                }
            }
            elencoGiocatoriSingoliCorrente = null;
        }
    }


    // ********************* Caso d'uso UC5 - Crea il calendario di un Torneo
    public void creaCalendario(){
        calendarioCorrente = new Calendario(codice);
    }

    public boolean creaPartita(String nomePartecipante1, String nomePartecipante2) {
        Partecipante partecipante1 = elencoPartecipanti.get(nomePartecipante1);
        Partecipante partecipante2 = elencoPartecipanti.get(nomePartecipante2);

        if (partecipante1 == null || partecipante2 == null) {
            return false;
        } else {
            calendarioCorrente.creaPartita(partecipante1, partecipante2);
            return true;
        }
    }

    public void inizializzaPartita(Campo campo, LocalDateTime data) {
        calendarioCorrente.inizializzaPartita(campo, data, this.classifica, this.classificaGiocatori);
    }

    public void confermaCalendario() {
        calendario = calendarioCorrente;

        calendarioCorrente = null;
    }

    public void modificaCalendario() {
        try {
            calendarioCorrente = (Calendario) calendario.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminaPartita(Campo campo, LocalDateTime data) {
        calendarioCorrente.eliminaPartita(campo, data);
    }


    // ********************* Caso d'uso UC6 - Visualizza il calendario di un Torneo
    public Calendario visualizzaCalendario() {
        if (calendario == null) {
            System.out.println("Calendario non trovato");
            return null;
        }

        if(calendario.getElencoPartite() == null) {
            System.out.println("Non è stata inserita nessuna partita nel calendario");
        }

        return calendario;
    }


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    public boolean selezionaPartita(Campo campo, LocalDateTime data) {
        return calendario.selezionaPartita(campo, data);
    }

    public void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2) {
        if(regolamento.getNumeroMinimoGiocatori() == 1)
            calendario.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2, false);
        else
            calendario.inserisciRisultato(punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1, esitoPartecipante2, true);
    }


    // ********************* Caso d'uso UC9 - Inserisci Statistiche di un Giocatore
    public boolean selezionaGiocatorePartita(String CF) {
        try {
            partecipanteCorrente = (Partecipante) calendario.selezionaGiocatorePartita(CF).clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }

        if(partecipanteCorrente == null) {
            return false;
        } else
            return true;

    }

    public void inserisciStatisticheGiocatore(int puntiEffettuati) {
        if (partecipanteCorrente != null)
            calendario.inserisciStatisticheGiocatore(partecipanteCorrente, puntiEffettuati);
        else
            System.out.println("Giocatore non trovato");
    }

    public void confermaInserimentoStatistichePartita() {
        calendario.confermaInserimentoStatistichePartita();
    }


    // ********************* Getter e Setter
    public Map<String, Partecipante> getElencoPartecipanti() {
        return elencoPartecipanti;
    }

    public Regolamento getRegolamento() {
        return regolamento;
    }

    public Sport getSport() {
        return sport;
    }

    public int getCodice() {
        return codice;
    }

    public float getQuotaIscrizione() {
        return quotaIscrizione;
    }

    public void setPartecipanteCorrente(Partecipante partecipanteCorrente) {
        try {
            this.partecipanteCorrente = (Partecipante) partecipanteCorrente.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setElencoGiocatoriSingoliCorrente() {
        elencoGiocatoriSingoliCorrente = null;
        elencoGiocatoriSingoliCorrente = new HashMap<String, GiocatoreSingolo>();

        for(Partecipante partecipante : elencoPartecipanti.values()) {
            if (partecipante.getClass().equals(GiocatoreSingolo.class)) {
                elencoGiocatoriSingoliCorrente.put(partecipante.getId(), (GiocatoreSingolo) partecipante);
            }
        }
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public Calendario getCalendarioCorrente() {
        return calendarioCorrente;
    }

    public Classifica getClassifica() {
        return classifica;
    }

    public ClassificaGiocatori getClassificaGiocatori() {
        return classificaGiocatori;
    }

    public Partecipante getPartecipanteCorrente() {
        return partecipanteCorrente;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Torneo clone = (Torneo) super.clone();
        
        if(elencoPartecipanti != null) {
            for(Partecipante partecipante : elencoPartecipanti.values()) {
                clone.elencoPartecipanti.put(partecipante.getId(), (Partecipante) partecipante.clone());
            }
        }
        if(calendario != null)
            clone.calendario = (Calendario) calendario.clone();
        if(classifica != null)
            clone.classifica = (Classifica) classifica.clone();
        if(classificaGiocatori != null)
            clone.classificaGiocatori = (ClassificaGiocatori) classificaGiocatori.clone();

        return clone;
    }


    @Override
    public String toString() {
        return "Torneo {" +
                "codiceTorneo = '" + codice + '\'' +
                ", nome = " + nome +
                ", quota iscrizione = " + quotaIscrizione +
                ",\nSPORT: " + sport +
                "REGOLAMENTO: " + regolamento +
                ", \nMODALITA': " + modalita +
                ", \nELENCO SQUADRE: \n" + elencoPartecipanti +
                "} \n";
    }
}
