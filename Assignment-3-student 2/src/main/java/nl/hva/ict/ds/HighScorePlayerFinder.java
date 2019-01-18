package nl.hva.ict.ds;

import nl.hva.ict.ds.util.DoubleHashingMultiValueSymbolTable;
import nl.hva.ict.ds.util.LinearProbingMultiValueSymbolTable;
import nl.hva.ict.ds.util.MultiValueSymbolTable;
import nl.hva.ict.ds.util.QuadraticProbingMultiValueSymbolTable;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Experimental class to investigate the use of different collision resolution strategies.
 * This class acts as a central accesspoint to three different collision resolution
 * strategies in such a way the user of this class doesn't have to worry about the
 * different strategies. This class is used by the JUnit tests.
 *
 * This class should not contain any errors, if you find one or think there is something
 * wrong with this class please contact the author.
 *
 * @author Nico Tromp, n.j.tromp@hva.nl
 */
public class HighScorePlayerFinder implements HighScoreList {
    private MultiValueSymbolTable<String, Player> firstNameFinder;
    private MultiValueSymbolTable<String, Player> lastNameFinder;
    private MultiValueSymbolTable<String, Player> fullNameFinder;

    public HighScorePlayerFinder(int arraySize) {
        firstNameFinder = new LinearProbingMultiValueSymbolTable(arraySize);
        lastNameFinder = new QuadraticProbingMultiValueSymbolTable(arraySize);
        fullNameFinder = new DoubleHashingMultiValueSymbolTable(arraySize);
    }

    @Override
    public void add(Player player) {
        firstNameFinder.put(player.getFirstName(), player);
        lastNameFinder.put(player.getLastName(), player);
        fullNameFinder.put(player.getFirstName() + player.getLastName(), player);
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        List<Player> players = new ArrayList<>();
        if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)) {
            players.addAll(fullNameFinder.get(firstName + lastName));
        } else if (StringUtils.isNotBlank(firstName)) {
            players.addAll(firstNameFinder.get(firstName));
        } else if (StringUtils.isNotBlank(lastName)) {
            players.addAll(lastNameFinder.get(lastName));
        }
        return players;
    }

    @Override
    public List<Player> remove(Player player){
        List<Player> players = new ArrayList<>();
        //players.addAll(firstNameFinder.remove(player.getFirstName() + player.getLastName() + player.getHighScore()));

        return players;
    }

    @Override
    public List<Player> getHighScores(int numberOfHighScores) {
        // No need to implement this method.
        return null;
    }

    @Override
    public String collisions() {
        String firstNameCollisions = "First name collisions: " + firstNameFinder.getCollisions();
        String lastNameCollisions = "Last name collisions: " + lastNameFinder.getCollisions();
        String fullNameCollisions = "Full name collisions: " + fullNameFinder.getCollisions();

        return firstNameCollisions +  " " + lastNameCollisions +  " " +  fullNameCollisions;
    }

}
