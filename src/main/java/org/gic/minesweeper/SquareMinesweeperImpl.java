package org.gic.minesweeper;

import org.gic.minesweeper.utils.MineSweeperUtils;

import java.util.Random;

import static org.gic.minesweeper.utils.MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE;

public class SquareMinesweeperImpl implements Minesweeper {

    // a 2d representation of the minefield
    private int[][] mineField;

    // all mine squares are marked with -1
    private final int MINE_SQUARE = -1;

    // all non mine squares will is assigned a value with the integer's min value
    private final int UNREVEALED_SQUARE = Integer.MIN_VALUE;

    private final Random  RANDOM = new Random();

    // get the current gaming status (WON, LOST, PLAYING)
    private GamingStatus status;


    /**
     * To instantiate an object of a Sqaure based minefield the size of the grid and number of mines
     * to be placed is crucial
     * @param sizeOfGrid The grid size to create a 2D matrix (square based) eg 4 will create 4*4 matrix
     * @param numberOfMines The number of mines to be placed in the field
     */
    public SquareMinesweeperImpl(int sizeOfGrid, int numberOfMines) {
        // validate the size of the grid and number of mines
        int validGridSize = MineSweeperUtils.Validator.gridSize(sizeOfGrid);
        int validNoOfMines = MineSweeperUtils.Validator.numberOfMines(validGridSize,numberOfMines);

        mineField = new int[sizeOfGrid][sizeOfGrid];
        status = GamingStatus.PLAYING;

        // initialize the minefield with default values
        initializeMineField();

        // place the mines randomly in the initialized minefield
        placeMinesInField(validNoOfMines);
    }


    /**
     * Initialize the minefield with the default value (UNREVEALED SQUARE)
     */
    private void initializeMineField() {
        for(int i=0;i<mineField.length;i++){
            for(int j=0;j< mineField[i].length;j++){
                mineField[i][j] = UNREVEALED_SQUARE;
            }
        }
    }

    /**
     * Place n number of mines at random squares within the minefield
     * @param numberOfMines number of mines to be placed in the field
     */
    private void placeMinesInField(int numberOfMines) {
        int numberOfMinesPlaced = 0;

        int totalSquares = mineField.length * mineField[0].length;

        while(numberOfMinesPlaced<numberOfMines){


            /*
            generate the random number
            divide and get the row and col from the random number
            eg. for 4*4 matrix total square will be 16 and assume random number to place mine generated is 11
            row = 11 / 4 = 2
            col = 11 % 4 = 3
            so the mine will be placed in (2,3) square
             */
            int randomSquare = RANDOM.nextInt(totalSquares);
            int row = randomSquare / mineField.length;
            int col = randomSquare % mineField.length;

            // place the mine only if the square is UNREVEALED that said it should not be a mine square
            // and increment the number of mines if it is successful
            if(mineField[row][col] == UNREVEALED_SQUARE){
                mineField[row][col] = MINE_SQUARE ;
                numberOfMinesPlaced++;
            }
        }
    }


    /**
     * Display the minefield according the current gaming status
     */
    @Override
    public void displayMinefield(){
        // displaying the column index
        System.out.print("  ");
        for(int i=0;i<mineField[0].length;i++){
            System.out.print(i+1+" ");
        }
        System.out.println();

        // displaying the row
        for(int i=0;i<mineField.length;i++){
            // display the row index
            System.out.print((char)(i+65)+" ");

            // display the mine field
            for(int j=0;j< mineField[i].length;j++){
                System.out.print(getCell(i, j));
            }
            System.out.println();
        }
    }

    /**
     * Returns the corresponding square cell based on the game status
     * @param i row of the cell
     * @param j column of the cell
     * @return
     */
    private String getCell(int i, int j) {
        String value;
        // if gaming status is playing don't reveal the minefield and uncover the field with '-' and display only if
        // it has been unrevealed
        if(status==GamingStatus.PLAYING) {
            if (mineField[i][j] == UNREVEALED_SQUARE || mineField[i][j] == MINE_SQUARE) {
                value = "_";
            } else {
                value = String.valueOf(mineField[i][j]);
            }
        }
        // if gaming status is not playing then the game has ended so reveal the minefield
        else{
            if (mineField[i][j] == MINE_SQUARE) {
                value = "X";
            } else {
                value = "0";
            }
        }
        return value +" ";
    }


    /**
     * Get the current gaming status
     * @return WON,LOST, PLAYING
     */
    @Override
    public GamingStatus getStatus() {
        return status;
    }

    @Override
    public void revealSquare(String givenSquare) throws IllegalArgumentException{
        // get row and column from the given square the following methods will also validate the given input
        int row = MineSweeperUtils.Parser.extractRowIndex(givenSquare);
        int col = MineSweeperUtils.Parser.extractColumnIndex(givenSquare);

        try {
            // check if the input given is a mine square
            if (mineField[row][col] == MINE_SQUARE) {
                // set the gaming status to LOST if it is a mine square
                status = GamingStatus.LOST;
            }
            else{
                // if not a mine square then get the adjacent mines from the input
                mineField[row][col] = getAdjacentMines(row,col);

                // check if all squares has been revealed and set the status to won
                if(isAllSquaresRevealed()){
                    status = GamingStatus.WON;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            // if we have array index out of bounds then the user input is in correct
            throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
        }

    }

    /**
     * Returns whether the game has ended
     * @return either TRUE or FALSE
     */
    @Override
    public boolean isGameOver() {
        // if the game status is not in the playing state then the game has come to an end
        return !getStatus().equals(GamingStatus.PLAYING);
    }

    /**
     * Returns a boolean whether all the squares has been revealed
     * @return boolean: either TRUE or FALSE
     */
    private boolean isAllSquaresRevealed() {
        for(int i=0;i<mineField.length;i++){
            for(int j=0;j<mineField[i].length;j++){
                // if any of the square in UNREVEALED then return false
                if(mineField[i][j]== UNREVEALED_SQUARE){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the number of adjacent mines from the given square position
     * an adjacent mine is a mine which falls in the surrounding of the given square
     * eg. given square (2,2)
     * adjacent mine will be calculated if any mine is in the following positions
     * (1,1),(1,2),(1,3)
     * (2,1),  X  ,(2,3)
     * (3,1),(3,2),(3,3)
     * @param row minefield's row Index number
     * @param col minefield's column Index number
     * @return int: number of adjacent mines
     */
    private int getAdjacentMines(int row, int col){
        int numberOfAdjacentMines = 0;
        // from the given row start from the previous row and end it in the next row
        for(int i=row-1;i<=row+1;i++){

            // continue if the row doesn't fall in the minefield's area
            if( i<0 || i>= mineField.length){
                continue;
            }

            // from the given column start from the previous col and end it in the next col
            for(int j=col-1;j<=col+1;j++){
                // continue if we are checking the given input square (not needed though as the game would have ended)
                if(i==row && j==col){
                    continue;
                }
                // continue if the col doesn't fall in the minefield's area
                if(j<0 || j>=mineField[i].length){
                    continue;
                }
                // increment the numberOfAdjacentMine is a mine square
                if (mineField[i][j]==MINE_SQUARE){
                    numberOfAdjacentMines++;
                }
            }
        }
        return numberOfAdjacentMines;
    }
}
