package nl.hva.ict.ds;

import nl.hva.ict.ds.util.NameReader;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Some very basic tests that show if you implemented the Symbol Table correctly.
 *
 * If you need to put tests or overwrite existing tests please do that in the class ExtendedPlayerFinderTest.
 *
 * They should not contain any errors, if you find one or think there is something
 * wrong with these tests please contact the author.
 *
 * @author Nico Tromp, n.j.tromp@hva.nl
 */
public class HighScorePlayerFinderTest {
    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    HighScoreList highscores;

    Player nearlyHeadlessNick;
    Player dumbledore;
    Player harry;
    Player james;
    Player lily;
    Player voldemort;

    @Before
    public final void setup() {
        highscores = new HighScorePlayerFinder(7);

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", 95);
        dumbledore = new Player("Albus", "Dumbledore", nearlyHeadlessNick.getHighScore() * 1000);
        harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1000);
        james = new Player("James", "Potter", harry.getHighScore() - 4000);
        lily = new Player("Lily", "Potter", harry.getHighScore() - 4000);
        voldemort = new Player("polygene", "lubricants", harry.getHighScore() - 10);

        highscores.add(nearlyHeadlessNick);
        highscores.add(dumbledore);
        highscores.add(harry);
        highscores.add(james);
        highscores.add(lily);
        highscores.add(voldemort);
    }

    @Test
    public final void albusIsUnique() {
        List<Player> albusses = highscores.findPlayer("Albus", null);

        assertEquals(1, albusses.size());
        assertEquals(dumbledore, albusses.get(0));
    }

    @Test
    public final void thePottersArePresent() {
        List<Player> potters = highscores.findPlayer(null, "Potter");

        assertEquals(3, potters.size());
        assertTrue(potters.contains(harry));
        assertTrue(potters.contains(james));
        assertTrue(potters.contains(lily));
    }

    @Test
    public final void fancyPantsIsPresent() {
        List<Player> headless = highscores.findPlayer("Nicholas", "de Mimsy-Porpington");

        assertEquals(1, headless.size());
        assertEquals(nearlyHeadlessNick, headless.get(0));
    }

    @Test
    public void collisionsShouldHappen() {
        String [] firstNames = new NameReader("/firstnames.txt").getNames();
        String [] lastNames = new NameReader("/lastnames.txt").getNames();

        highscores = new HighScorePlayerFinder(10501); // Please adjust this size!
        for (int i = 0; i < 10000; i++) {
            String firstName = firstNames[randomizer.nextInt(firstNames.length)];
            String lastName = lastNames[randomizer.nextInt(lastNames.length)];
            highscores.add(new Player(firstName, lastName, randomizer.nextInt(1000)));
        }

        System.out.println(highscores.collisions());
    }

}