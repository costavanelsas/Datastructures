package nl.hva.ict.ds;

import java.util.List;

/**
 * Classes that implement this interface provide ordered lists of high-scores in descending order.
 */
public interface HighScoreList {

    /**
     * Add a new player to the list of high-scores.
     * @param player
     */
    void add(Player player);

    List<Player> remove(Player player);

    /**
     * Return a list with players descending sorted on their high-score. If there are less then numberOfHighScores players
     * a list containing all players is returned. If there are more high-scores then numberOfHighScore only the first
     * numberOfHighScores are returned.
     *
     * @param numberOfHighScores the maximum number of high-scores you want.
     * @return at list of, maximum numberOfHighScores, players with the highest high-scores in descending order.
     */
    List<Player> getHighScores(int numberOfHighScores);

    /**
     * Finds players based on their first and last name. At least one of the parameters must have a value other
     * then null or an empty String, {@see String#isEmpty()} for a definition of an empty String.
     * Players match the search values if their names start with the parameters.
     * @param firstName the firstname of the players must start with or be equal to this value, can be null or empty if
     *                 lastName is not null or empty.
     * @param lastName the lastname of the playersmust start with or be equal to this value, can be null or empty if
     *                 firstName is not null or empty
     * @return a list of players that either match by firstname, lastname or both.
     * @throws IllegalArgumentException if both firstName and lastName or null or empty.
     */
    List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException;

    String collisions();
}
