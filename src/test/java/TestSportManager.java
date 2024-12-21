import Classi.SportManager;

import Classi.Stagione;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSportManager {
    static SportManager sportManager;

    @BeforeClass
    public static void initTest() {
        sportManager = SportManager.getInstance();
    }

    @After
    public void clearTest(){
        Stagione stagione = sportManager.getStagione();
        stagione.getElencoTornei().clear();
        stagione.getElencoSquadre().clear();
    }

    @Test
    public void testNuovoTorneo() {
        sportManager.nuovoTorneo("WorldCup", 1, 1, 150);
        assertNotNull(sportManager.getStagione().getTorneoCorrente());
    }

    @Test
    public void testNuovoRegolamento() {
        sportManager.nuovoTorneo("WorldCup", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);

        assertNotNull(sportManager.getStagione().getTorneoCorrente().getRegolamento());
    }

    @Test
    public void testConfermaTorneo() {
        sportManager.nuovoTorneo("WorldCup", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        assertNull(sportManager.getStagione().getTorneoCorrente());
        assertEquals(1, sportManager.getStagione().getElencoTornei().size());
    }

    @Test
    public void testNuovaSquadra() {
        sportManager.nuovaSquadra(1, "Bari");

        assertNotNull(sportManager.getStagione().getSquadraCorrente());
        System.out.println(sportManager.getStagione().getSquadraCorrente());
    }

    @Test
    public void testNuovoComponente() {
        sportManager.nuovaSquadra(1, "Bari");
        sportManager.nuovoComponente("Mario", "Rossi", 18, "Attaccante", "MRORSS18");

        assertNotNull(sportManager.getStagione().getSquadraCorrente().getElencoComponenti());
        System.out.println(sportManager.getStagione().getSquadraCorrente().getElencoComponenti());
    }

    @Test
    public void confermaSquadra() {
        sportManager.nuovaSquadra(1, "Bari");
        sportManager.nuovoComponente("Mario", "Rossi", 18, "Attaccante", "MRORSS18");
        sportManager.confermaSquadra();

        assertNull(sportManager.getStagione().getSquadraCorrente());
        assertEquals(1, sportManager.getStagione().getElencoSquadre().size());
    }

    @Test
    public void testIscrizioneSquadra() {
        System.out.println(sportManager.iscrizioneSquadra());
    }

    @Test
    public void testSelezionaSport() {
        sportManager.nuovoTorneo("WorldCup2025", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.nuovoTorneo("WorldCup2026", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.nuovoTorneo("WorldCup", 2, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        System.out.println(sportManager.selezionaSport(1));
    }

    @Test
    public void testSelezionaTorneo() {
        sportManager.nuovoTorneo("WorldCup2026", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.selezionaTorneo(1);

        assertNotNull(sportManager.getStagione().getTorneoCorrente());
    }

    @Test
    public void testSelezionaSquadra() {
        sportManager.nuovaSquadra(1, "Bari");
        sportManager.confermaSquadra();

        sportManager.nuovoTorneo("WorldCup2026", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");

        assertNotNull(sportManager.getStagione().getSquadraCorrente());
    }

    @Test
    public void testConfermaIscrizioneSquadra() {
        sportManager.nuovaSquadra(1, "Bari");
        sportManager.confermaSquadra();

        sportManager.nuovoTorneo("WorldCup2026", 1, 1, 150);
        sportManager.nuovoRegolamento(20, 8, 3, 1, 0);
        sportManager.confermaTorneo();

        sportManager.selezionaTorneo(1);
        sportManager.selezionaSquadra("Bari");

        sportManager.confermaIscrizioneSquadra();
        assertNull(sportManager.getStagione().getTorneoCorrente());
        assertEquals(1, sportManager.getStagione().getElencoTornei().get(1).getElencoSquadre().size());
    }
}
