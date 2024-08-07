package org.gic.minesweeper;

public enum GamingStatus {
    WON("Congratulations, you have won the game!"),

    LOST("Oh no, you detonated a mine! Game over."),

    PLAYING("Currently playing");

    private final String name;

    GamingStatus(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
