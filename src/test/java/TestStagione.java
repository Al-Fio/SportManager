import Classi.Modalita;
import Classi.Regolamento;
import Classi.Sport;
import Classi.Stagione;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestStagione {
    static Stagione stagione;

    @BeforeClass
    public static void initTest() {
        stagione = new Stagione("2024_2025");
    }

    @After
    public void clearTest(){
        stagione.getElencoTornei().clear();
        stagione.getElencoSquadre().clear();
    }

    @Test
    public void testAggiungiTorneo() {
        Sport sport = new Sport(1, "Calcio");
        Modalita modalita = new Modalita(1, "campionato");

        stagione.aggiungiTorneo("WorldCup", sport, modalita, 150);

        assertNotNull(stagione.getTorneoCorrente());
    }

    @Test
    public void testNuovoRegolamento() {
        Sport sport = new Sport(1, "Calcio");
        Modalita modalita = new Modalita(1, "campionato");

        stagione.aggiungiTorneo("WorldCup", sport, modalita, 150);

        stagione.nuovoRegolamento(20, 8, 3, 1, 0);

        assertNotNull(stagione.getTorneoCorrente().getRegolamento());
    }

    @Test
    public void testConfermaTorneo() {
        Sport sport = new Sport(1, "Calcio");
        Modalita modalita = new Modalita(1, "campionato");

        stagione.aggiungiTorneo("WorldCup", sport, modalita, 150);
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
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
        Modalita modalita = new Modalita(1, "campionato");
        stagione.aggiungiTorneo("WorldCup", sport, modalita, 150);
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
        stagione.confermaTorneo();

        sport = new Sport(2, "Calcio5");

        modalita = new Modalita(1, "campionato");
        stagione.aggiungiTorneo("WorldCup2025", sport, modalita, 150);
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
        stagione.confermaTorneo();

        sport = new Sport(1, "Calcio");

        System.out.println(stagione.torneiPerSport(sport));
    }

    @Test
    public void testSelezionaTorneo() {
        Sport sport = new Sport(1, "Calcio");
        Modalita modalita = new Modalita(1, "campionato");
        stagione.aggiungiTorneo("WorldCup", sport, modalita, 150);
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
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

        Modalita modalita = new Modalita(1, "campionato");
        stagione.aggiungiTorneo("WorldCup", sport, modalita, 150);
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
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

        Modalita modalita = new Modalita(1, "campionato");
        stagione.aggiungiTorneo("WorldCup", sport, modalita, 150);
        stagione.nuovoRegolamento(20, 8, 3, 1, 0);
        stagione.confermaTorneo();

        stagione.selezionaTorneo(1);
        stagione.selezionaSquadra("Bari");
        stagione.confermaIscrizioneSquadra();

        assertNull(stagione.getSquadraCorrente());
        assertNull(stagione.getTorneoCorrente());
        assertEquals(1, stagione.getElencoTornei().get(1).getElencoSquadre().size());
    }
}
