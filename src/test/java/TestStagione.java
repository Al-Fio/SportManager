import Classi.Modalita;
import Classi.Regolamento;
import Classi.Sport;
import Classi.Stagione;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static Enum.TipoModalita.CAMPIONATO;
import static org.junit.Assert.*;

public class TestStagione {
    private static Stagione stagione;

    @BeforeClass
    public static void initTest() {
        stagione = Stagione.getInstance("2024");
    }

    @Test
    public void testAggiungiTorneo() {
        Sport sport = new Sport(1, "Calcio");
        Regolamento regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        Modalita modalita = new Modalita(1, CAMPIONATO);

        stagione.aggiungiTorneo("WorldCup", sport, regolamento, modalita, 150);

        assertNotNull(stagione.getTorneoCorrente());
    }

    @Test
    public void testConfermaTorneo() {
        Sport sport = new Sport(1, "Calcio");
        Regolamento regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        Modalita modalita = new Modalita(1, CAMPIONATO);

        stagione.aggiungiTorneo("WorldCup", sport, regolamento, modalita, 150);
        stagione.confermaTorneo();

        assertNull(stagione.getTorneoCorrente());
        assertEquals(1, stagione.getElencoTornei().size());
    }

    @Test
    public void testAggiungiSquadra() {
        Sport sport = new Sport(1, "Calcio");
        stagione.aggiungiSquadra(sport, "Catania");

        assertNotNull(stagione.getSquadraCorrente());
    }

    @Test
    public void testAggiungiComponente() {
        Sport sport = new Sport(1, "Calcio");
        stagione.aggiungiSquadra(sport, "Bari");
        stagione.aggiungiComponente("Mario", "Rossi", 18, "Attaccante", "MRORSS18");

        assertNotNull(stagione.getSquadraCorrente().getElencoComponenti());
        System.out.println(stagione.getSquadraCorrente().getElencoComponenti());
    }

    @Test
    public void confermaSquadra() {
        Sport sport = new Sport(1, "Calcio");
        stagione.aggiungiSquadra(sport, "Bari");
        stagione.aggiungiComponente("Mario", "Rossi", 18, "Attaccante", "MRORSS18");
        stagione.confermaSquadra();

        assertNull(stagione.getSquadraCorrente());
        assertEquals(1, stagione.getElencoSquadre().size());
    }

    @Test
    public void testTorneiPerSport() {
        Sport sport = new Sport(1, "Calcio");
        Regolamento regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        Modalita modalita = new Modalita(1, CAMPIONATO);
        stagione.aggiungiTorneo("WorldCup", sport, regolamento, modalita, 150);
        stagione.confermaTorneo();

        sport = new Sport(2, "Calcio5");

        regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        modalita = new Modalita(1, CAMPIONATO);
        stagione.aggiungiTorneo("WorldCup2025", sport, regolamento, modalita, 150);
        stagione.confermaTorneo();

        sport = new Sport(1, "Calcio");

        System.out.println(stagione.torneiPerSport(sport));
    }

    @Test
    public void testSelezionaTorneo() {
        Sport sport = new Sport(1, "Calcio");
        Regolamento regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        Modalita modalita = new Modalita(1, CAMPIONATO);
        stagione.aggiungiTorneo("WorldCup", sport, regolamento, modalita, 150);
        stagione.confermaTorneo();

        stagione.selezionaTorneo(1);

        assertNotNull(stagione.getTorneoCorrente());
    }

    @Test
    public void testSelezionaSquadra() {
        Sport sport = new Sport(1, "Calcio");
        stagione.aggiungiSquadra(sport, "Bari");
        stagione.aggiungiComponente("Mario", "Rossi", 18, "Attaccante", "MRORSS18");
        stagione.confermaSquadra();

        Regolamento regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        Modalita modalita = new Modalita(1, CAMPIONATO);
        stagione.aggiungiTorneo("WorldCup", sport, regolamento, modalita, 150);
        stagione.confermaTorneo();

        stagione.selezionaTorneo(1);

        stagione.selezionaSquadra("Bari");

        assertNotNull(stagione.getSquadraCorrente());
    }

    @Test
    public void testConfermaIscrizioneSquadra() {
        Sport sport = new Sport(1, "Calcio");
        stagione.aggiungiSquadra(sport, "Bari");
        stagione.aggiungiComponente("Mario", "Rossi", 18, "Attaccante", "MRORSS18");
        stagione.confermaSquadra();

        Regolamento regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        Modalita modalita = new Modalita(1, CAMPIONATO);
        stagione.aggiungiTorneo("WorldCup", sport, regolamento, modalita, 150);
        stagione.confermaTorneo();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Bari");
        stagione.confermaIscrizioneSquadra();

        assertNull(stagione.getSquadraCorrente());
        assertNull(stagione.getTorneoCorrente());
        assertEquals(1, stagione.getElencoTornei().get(1).getElencoSquadre().size());
    }
}
