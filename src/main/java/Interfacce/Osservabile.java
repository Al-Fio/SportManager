package Interfacce;
import Enum.Esito;

public interface Osservabile {
    void attach(Osservatore osservatore);
    void detach(Osservatore osservatore);
    void notifyChangeState(int punteggioPartecipante1, int punteggioPartecipante2, Esito esitoPartecipante1, Esito esitoPartecipante2);
}
