package org.gic.minesweeper;

public interface MineSweeper {
    GamingStatus getStatus();
    void printMineField();

    void revealSquare(String givenSquare);
    boolean isGameOver();

}
