package Interfacce;
import Classi.Partecipante;
import Classi.StatisticheGiocatore;
import Enum.Esito;

import java.util.List;

public interface StatoPartita {
    void inserisciRisultato(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2, boolean aSquadre);

    void inserisciStatisticheGiocatore(Partecipante partecipante, int puntiEffettuati);


    int getPunteggioPartecipante1();

    int getPunteggioPartecipante2();

    Esito getEsitoPartecipante1();

    Esito getEsitoPartecipante2();

    List<StatisticheGiocatore> getStatistiche();

    Partecipante getPartecipante1();

    Partecipante getPartecipante2();
}
