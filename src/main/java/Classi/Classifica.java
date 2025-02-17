package Classi;

import Eccezioni.WrongPartException;
import Interfacce.Osservatore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Enum.Esito;
import Interfacce.StatoPartita;

public class Classifica implements Osservatore, Cloneable {
    List<StatisticheClassifica> listaClassifica;

    Regolamento regolamento;


    // ********************* Costruttore
    public Classifica() {
        this.listaClassifica = new ArrayList<StatisticheClassifica>();
    }


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    private void aggiornaClassifica(Partecipante partecipante, int puntiEffettuati, int puntiSubiti, Esito esito) {
        int incrementoPunteggio;

        Comparator<StatisticheClassifica> comparator = Comparator
                .comparingInt((StatisticheClassifica sG) -> sG.punteggio)
                .thenComparing(sG -> sG.puntiEffettuati)
                .thenComparing(sG -> sG.puntiSubiti).reversed();

        switch (esito) {
            case VITTORIA, VITTORIA_A_TAVOLINO:
                incrementoPunteggio = regolamento.getPunteggioVittoria();
                break;

            case PAREGGIO:
                incrementoPunteggio = regolamento.getPunteggioPareggio();
                break;

            case SCONFITTA, SCONFITTA_A_TAVOLINO:
                incrementoPunteggio = regolamento.getPunteggioSconfitta();
                break;

            default:
                incrementoPunteggio = 0;
                break;
        }

        for(StatisticheClassifica classifica : listaClassifica) {
            if (classifica.getPartecipante().equals(partecipante)) {

                classifica.setPunteggio(incrementoPunteggio);
                classifica.setPuntiEffettuati(puntiEffettuati);
                classifica.setPuntiSubiti(puntiSubiti);

                listaClassifica.sort(comparator);

                return;
            }
        }

        listaClassifica.add(new StatisticheClassifica(partecipante, incrementoPunteggio, puntiEffettuati, puntiSubiti));

        listaClassifica.sort(comparator);
    }

    @Override
    public void update(StatoPartita partita, int operazione) {
        if(operazione == 0) {
            Partecipante partecipante1 = partita.getPartecipante1();
            Partecipante partecipante2 = partita.getPartecipante2();

            Esito esitoPartecipante1 = partita.getEsitoPartecipante1();
            Esito esitoPartecipante2 = partita.getEsitoPartecipante2();
            int punteggioPartecipante1 = partita.getPunteggioPartecipante1();
            int punteggioPartecipante2 = partita.getPunteggioPartecipante2();

            aggiornaClassifica(partecipante1, punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1);
            aggiornaClassifica(partecipante2, punteggioPartecipante2, punteggioPartecipante1, esitoPartecipante2);
        }
    }


    // ********************* Getter e Setter
    public void setRegolamento(Regolamento regolamento) {
        this.regolamento = regolamento;
    }

    public List<StatisticheClassifica> getListaClassifica() {
        return listaClassifica;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        Classifica clone = (Classifica) super.clone();

        if(listaClassifica != null) {
            for(StatisticheClassifica classifica : listaClassifica) {
                clone.listaClassifica.add((StatisticheClassifica) classifica.clone());
            }
        }

        return clone;
    }


    @Override
    public String toString() {
        return "Classifica {\n" + listaClassifica +
                '}';
    }
}
