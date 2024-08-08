package org.gic.minesweeper;

public interface Minesweeper {
    GamingStatus getStatus();
    void printMineField();

    void revealSquare(String givenSquare);
    boolean isGameOver();

}
