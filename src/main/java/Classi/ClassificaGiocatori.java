package Classi;

import Eccezioni.WrongPartException;
import Interfacce.Osservatore;
import Interfacce.StatoPartita;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassificaGiocatori implements Osservatore, Cloneable {
    Map<String, StatisticheGiocatore> listaStatistiche;

    public ClassificaGiocatori() {
        this.listaStatistiche = new HashMap<String, StatisticheGiocatore>();
    }

    private void aggiornaStatistiche(StatisticheGiocatore statisticheGiocatore) {
        for(StatisticheGiocatore classifica : listaStatistiche.values()) {
            if(classifica.getGiocatore().equals(statisticheGiocatore.getGiocatore())) {
                classifica.setPuntiEffettuati(classifica.getPuntiEffettuati() + statisticheGiocatore.getPuntiEffettuati());
                System.out.println("Classifica giocatori aggiornata: " + classifica.getPuntiEffettuati());
                return;
            }
        }

        listaStatistiche.put(statisticheGiocatore.getGiocatore().getId(), statisticheGiocatore);
        System.out.println("Classifica giocatori creata: ");
    }

    @Override
    public void update(StatoPartita partita, int operazione) {
        if(operazione == 1){
            List<StatisticheGiocatore> statisticheGiocatori = partita.getStatistiche();

            for (StatisticheGiocatore statisticheGiocatore : statisticheGiocatori) {
                aggiornaStatistiche(statisticheGiocatore);
            }
        }
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        ClassificaGiocatori clone = new ClassificaGiocatori();

        if(listaStatistiche != null) {
            for(StatisticheGiocatore statisticheGiocatore : listaStatistiche.values()) {
                clone.aggiornaStatistiche((StatisticheGiocatore) statisticheGiocatore.clone());
            }
        }

        return clone;
    }


    @Override
    public String toString() {
        return "ClassificaGiocatori {" + listaStatistiche +
                '}';
    }
}
