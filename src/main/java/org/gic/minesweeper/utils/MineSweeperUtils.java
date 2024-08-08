package org.gic.minesweeper.utils;

public class MineSweeperUtils {

    public final static String INCORRECT_INPUT_ERROR_MESSAGE = "Incorrect input.";

    /**
     * This class validates all the inputs
     */
    public static class Validator {

        /**
         * Validates the given string input is a valid grid size
         * @param sizeOfGridInput String input of the grid size
         * @return int: valid gridSize
         * @throws IllegalArgumentException when the given input is not valid
         */
        public static int gridSize(String sizeOfGridInput) throws IllegalArgumentException {
            int gridSize;
            try {
                gridSize = Integer.parseInt(sizeOfGridInput);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
            }
            return gridSize(gridSize);
        }

        /**
         *  Validates the int grid size the grid size should be greater than 2 and the maximum size is 10
         * @param gridSize
         * @return the validated grid size if it meets the criteria
         * @throws IllegalArgumentException
         */
        public static int gridSize(int gridSize) throws IllegalArgumentException {
            if (gridSize < 2) {
                throw new IllegalArgumentException("Minimum size of the grid is 2.");
            }
            if (gridSize > 10) {
                throw new IllegalArgumentException("Maximum size of grid is 10.");
            }
            return gridSize;
        }

        /**
         * Validates the number of mines to be placed based on the grid size
         * @param numberOfMinesInput  number of mines to be placed
         * @param gridSize the size of the grid to be validated based upon
         * @return valid
         * @throws IllegalArgumentException
         */
        public static int numberOfMines(int gridSize,String numberOfMinesInput) throws IllegalArgumentException {
            int numberOfMines;
            try {
                numberOfMines = Integer.parseInt(numberOfMinesInput);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
            }
            return numberOfMines(gridSize, numberOfMines);

        }

        /**
         * Validates the number of mines to be placed based on the grid size
         * There should be at least one mine and the maximum number of mines could be places is 35% of the grid size
         * @param gridSize the size of the grid to be validated based upon
         * @param numberOfMines number of mines to be placed
         * @return validated the number of mines
         * @throws IllegalArgumentException if number of mines doesn't meets the criteria
         */

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

    /**
     * Class to parse the input
     */
    public static class Parser {

        /**
         * Extract the row index based on the given game square
         * @param currentGameSquare a string input of the game square for example A2,D4
         * @return valid row index eg A2-> 0; D4-> 3
         * @throws IllegalArgumentException if the input is incorrect
         */
        public static int extractRowIndex(String currentGameSquare) throws IllegalArgumentException {
            currentGameSquare = currentGameSquare.toUpperCase();
            char rowSquare = currentGameSquare.charAt(0);
            if (rowSquare >= 'A' && rowSquare <= 'Z') {
                int row = rowSquare - 65;
                return row;
            } else {
                throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
            }

        }

        /**
         * Extract the col square based on the given game square
         * @param currentGameSquare a string input of the game square for example A2,B10
         * @return valid column index eg. A2 -> 2; B10-> 10
         * @throws IllegalArgumentException if the input is incorrect
         */
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
