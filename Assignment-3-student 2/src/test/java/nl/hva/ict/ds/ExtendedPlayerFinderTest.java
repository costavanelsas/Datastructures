package nl.hva.ict.ds;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * If you have any tests that should be overwritten or added please put them to this class.
 */
public class ExtendedPlayerFinderTest extends HighScorePlayerFinderTest {

    HighScoreList highscores2;
    Player testPlayer;
    Player nearlyHeadlessNick2;
    Player dumbledore2;
    Player harry2;
    Player james2;
    Player lily2;
    Player voldemort2;

    @Before
    public final void setup2() {
        highscores2 = new HighScorePlayerFinder(7);

        nearlyHeadlessNick2 = new Player("Nicholas", "de Mimsy-Porpington", 95);
        dumbledore2 = new Player("Albus", "Dumbledore", nearlyHeadlessNick2.getHighScore() * 1000);
        harry2 = new Player("Harry", "Potter", dumbledore.getHighScore() + 1000);
        james2 = new Player("Nicholas", "de Mimsy-Porpington", harry.getHighScore() - 4000);
        lily2 = new Player("Harry", "Potter", harry.getHighScore() - 4000);
        voldemort2 = new Player("Harry", "lubricants", harry.getHighScore() - 10);

        highscores2.add(nearlyHeadlessNick2);
        highscores2.add(dumbledore2);
        highscores2.add(harry2);
        highscores2.add(james2);
        highscores2.add(lily2);
        highscores2.add(voldemort2);
    }

    //test to check if the remove method works correctly
    @Test
    public final void removePotter() {
        List<Player> potters = highscores.findPlayer(null, "Potter");

        potters.remove(potters.get(1));
        assertEquals(2, potters.size());
    }

    //test to check if the put method works correctly
    @Test
    public final void addPlayer() {

        testPlayer = new Player("player", "testing", 1000);
        highscores.add(testPlayer);

        List<Player> players = highscores.findPlayer("player", "testing");

        assertEquals(1, players.size());
        assertEquals(testPlayer, players.get(0));
    }

    @Test
    public void moreHarrys(){
        List<Player> harrys = highscores2.findPlayer("Harry", null);

        assertEquals(3, harrys.size());
    }

    @Test
    public void moreNicolasses(){
        List<Player> headless = highscores2.findPlayer("Nicholas", "de Mimsy-Porpington");

        assertEquals(2, headless.size());
    }

    @Test
    public final void findNoneExistingFullname() {
        List<Player> list = highscores2.findPlayer("Costa", "Jeroen");
        assertEquals(0, list.size());
    }

    @Test
    public final void findNoneExistingFirstname() {
        List<Player> list = highscores2.findPlayer("Costa", null);
        assertEquals(0, list.size());
    }

    @Test
    public final void findNoneExistingLastname() {
        List<Player> list = highscores2.findPlayer(null, "Jeroen");
        assertEquals(0, list.size());
    }


//    //test to check if he does not find a name that does not exists
//    @Test
//    public final void findNoneExistingName() {
//        List<Player> players = highscores.findPlayer("Costa", "Elsas");
//        assertEquals(0, players.size());
//    }

}
