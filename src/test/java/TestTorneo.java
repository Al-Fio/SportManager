import Classi.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestTorneo {
    private static Torneo torneo;

    @BeforeClass
    public static void initTest() {
        Sport sport = new Sport(1, "Calcio");
        Modalita modalita = new Modalita(1, "campionato");

        torneo = new Torneo(1, "worldCup", sport, modalita, 150);
    }

    @Test
    public void testIscriviSquadra() {
        Sport sport = new Sport(1, "Calcio");
        Squadra squadra = new Squadra(sport, "Bari");
        torneo.iscriviSquadra("Bari", squadra);

        assertNotNull(torneo.getElencoSquadre());
        System.out.println(torneo.getElencoSquadre());
    }
}
