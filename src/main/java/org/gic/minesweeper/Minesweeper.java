package org.gic.minesweeper;
/**
 * Minesweeper interface which is extensible to create and play any type or size of the field
 * such as square grids, rectangular grids, or even line-based minefields.
 */
public interface Minesweeper {

    /**
     * Returns the current status of the game
     *
     * @return  current game status ( PLAYING, WON,  LOST)
     */
    GamingStatus getStatus();

    /**
     * Displays the current state of the minefield based on the gaming status
     */
    void displayMinefield();

    /**
     * Based on the user's input this method reveals the square and updates the game state accordingly.
     *
     * @param givenSquare eg "A1", "B3" etc.
     */
    void revealSquare(String givenSquare);

    /**
     * Checks whether the game has ended.
     *
     * @return true if the game is over (either won or lost), false otherwise.
     */
    boolean isGameOver();
}