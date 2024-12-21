import Classi.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class TestSquadra {
    static Squadra squadra;

    @BeforeClass
    public static void initTest() {
        Sport sport = new Sport(1, "Calcio");
        squadra = new Squadra(sport, "Italia");
    }

    @Test
    public void testAggiungiComponente() {
        squadra.aggiungiComponente("Mario", "Rossi", 20, "attaccante", "RSSMR17B18J");
        assertNotNull(squadra.getElencoComponenti());

        for(String key : squadra.getElencoComponenti().keySet()) {
            System.out.println(squadra.getElencoComponenti().get(key).toString());
        }
    }

}
