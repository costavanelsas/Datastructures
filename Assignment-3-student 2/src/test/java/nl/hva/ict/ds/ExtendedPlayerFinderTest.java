package nl.hva.ict.ds;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * If you have any tests that should be overwritten or added please put them to this class.
 */
public class ExtendedPlayerFinderTest extends HighScorePlayerFinderTest {

    Player testPlayer;

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

//    //test to check if he does not find a name that does not exists
//    @Test
//    public final void findNoneExistingName() {
//        List<Player> players = highscores.findPlayer("Costa", "Elsas");
//        assertEquals(0, players.size());
//    }

}
