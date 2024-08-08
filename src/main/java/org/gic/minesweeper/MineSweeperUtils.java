package org.gic.minesweeper;

public class MineSweeperUtils {

    public final static String INCORRECT_INPUT_ERROR_MESSAGE = "Incorrect input.";

    public static class Validator {
        public static int gridSize(String sizeOfGridInput) throws IllegalArgumentException {
            int gridSize;
            try {
                gridSize = Integer.parseInt(sizeOfGridInput);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
            }
            return gridSize(gridSize);
        }

        public static int gridSize(int gridSize) throws IllegalArgumentException {
            if (gridSize < 2) {
                throw new IllegalArgumentException("Minimum size of the grid is 2.");
            }
            if (gridSize > 10) {
                throw new IllegalArgumentException("Maximum size of grid is 10.");
            }
            return gridSize;
        }

        public static int numberOfMines(String numberOfMinesInput, int gridSize) throws IllegalArgumentException {
            int numberOfMines;
            try {
                numberOfMines = Integer.parseInt(numberOfMinesInput);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
            }
            return numberOfMines(gridSize, numberOfMines);

        }

        public static int numberOfMines(int gridSize, int numberOfMines) {
            if (numberOfMines <= 0) {
                throw new IllegalArgumentException("There must be at least 1 mine.");
            }

            int fieldSquares = gridSize * gridSize;
            int maxNumOfMinesBePlaced = (int) Math.ceil(fieldSquares * 0.35);
            if (numberOfMines > maxNumOfMinesBePlaced) {
                throw new IllegalArgumentException("For the given grid size the maximum number of mines could be placed is " + maxNumOfMinesBePlaced);
            }
            return numberOfMines;
        }

    }
    public static class Parser {
        public static int extractRowIndex(String currentGameSquare) throws IllegalArgumentException {

            char rowSquare = currentGameSquare.charAt(0);
            if (rowSquare >= 'A' && rowSquare <= 'Z') {
                int row = rowSquare - 65;
                return row;
            } else {
                throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
            }

        }

        public static int extractColumnIndex(String currentGameSquare) throws IllegalArgumentException {
            try {
                int colSquare = Integer.parseInt(currentGameSquare.substring(1));
                return colSquare - 1;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
            }

        }
    }
}
