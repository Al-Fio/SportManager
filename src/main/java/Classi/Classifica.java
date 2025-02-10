package Classi;

import Interfacce.Osservatore;

import java.util.ArrayList;
import java.util.List;
import Enum.Esito;

public class Classifica implements Osservatore {
    List<StatisticheClassifica> listaClassifica;

    Regolamento regolamento;


    // ********************* Costruttore
    public Classifica() {
        this.listaClassifica = new ArrayList<StatisticheClassifica>();
    }


    // ********************* Caso d'uso UC8 - Inserisci i risultati di una Partita
    private void aggiornaClassifica(Partecipante partecipante, int puntiEffettuati, int puntiSubiti, Esito esito) {
        int incrementoPunteggio;

        switch (esito) {
            case VITTORIA:
                incrementoPunteggio = regolamento.getPunteggioVittoria();
                break;

            case PAREGGIO:
                incrementoPunteggio = regolamento.getPunteggioPareggio();
                break;

            case SCONFITTA:
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

                return;
            }
        }

        listaClassifica.add(new StatisticheClassifica(partecipante, incrementoPunteggio, puntiEffettuati, puntiSubiti));
    }

    @Override
    public void update(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, Partecipante partecipante1, Partecipante partecipante2) {
        aggiornaClassifica(partecipante1, punteggioPartecipante1, punteggioPartecipante2, esitoPartecipante1);
        aggiornaClassifica(partecipante2, punteggioPartecipante2, punteggioPartecipante1, esitoPartecipante2);
    }


    // ********************* Getter e Setter
    public void setRegolamento(Regolamento regolamento) {
        this.regolamento = regolamento;
    }
}
