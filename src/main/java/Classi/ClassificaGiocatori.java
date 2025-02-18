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

    private void aggiornaStatistiche(StatisticheGiocatore statisticaGiocatore) {
        if(listaStatistiche.containsKey(statisticaGiocatore.getGiocatore().getId())) {
            StatisticheGiocatore statistica = listaStatistiche.get(statisticaGiocatore.getGiocatore().getId());
            statistica.setPuntiEffettuati(statistica.getPuntiEffettuati() + statisticaGiocatore.getPuntiEffettuati());
        } else
            listaStatistiche.put(statisticaGiocatore.getGiocatore().getId(), statisticaGiocatore);
    }

    @Override
    public void update(StatoPartita partita, int operazione) {
        if(operazione == 1){
            for (StatisticheGiocatore statisticaGiocatore : partita.getStatistiche()) {
                aggiornaStatistiche(statisticaGiocatore);
            }
        }
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        ClassificaGiocatori clone = new ClassificaGiocatori();

        if(listaStatistiche != null) {
            clone.listaStatistiche = new HashMap<>();
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
