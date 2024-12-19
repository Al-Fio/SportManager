import Classi.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static Enum.TipoModalita.CAMPIONATO;
import static org.junit.Assert.assertNotNull;

public class TestTorneo {
    private static Torneo torneo;

    @BeforeClass
    public static void initTest() {
        Sport sport = new Sport(1, "Calcio");
        Regolamento regolamento = new Regolamento(1, "Calcio5", 50, 7, 3, 0, 1);
        Modalita modalita = new Modalita(1, CAMPIONATO);

        torneo = new Torneo(1, "worldCup", sport, regolamento, modalita, 150);
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
