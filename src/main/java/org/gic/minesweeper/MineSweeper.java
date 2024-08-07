package org.gic.minesweeper;

import java.util.Random;

import static org.gic.minesweeper.MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE;

public class MineSweeper {
    private int[][] mineField;

    private final int MINE_SQUARE = -1;

    private final int NORMAL_SQUARE = Integer.MIN_VALUE;

    private final Random  RANDOM = new Random();


    private GamingStatus status;

    public MineSweeper(int sizeOfGrid, int numberOfMines) {
        int validGridSize = MineSweeperUtils.validateGridSize(sizeOfGrid);
        int validNoOfMines = MineSweeperUtils.validateNumberOfMines(validGridSize,numberOfMines);
        mineField = new int[sizeOfGrid][sizeOfGrid];
        status = GamingStatus.PLAYING;
        initializeMineField();
        installMinesInField(validNoOfMines);
    }


    /*
    returns the minefield
     */
    public int[][] getMineField() {
        return mineField;
    }

    public void printMineField(){
        System.out.print(" ");
        for(int i=0;i<mineField[0].length;i++){
            System.out.print(i+1+" ");
        }
        System.out.println();
        for(int i=0;i<mineField.length;i++){
            System.out.print((char)(i+65));
            for(int j=0;j< mineField[i].length;j++){
                if(status==GamingStatus.PLAYING) {
                    if (mineField[i][j] == NORMAL_SQUARE || mineField[i][j] == MINE_SQUARE) {
                        System.out.print("- ");
                    } else {
                        System.out.print(mineField[i][j] + " ");
                    }
                }
                else{
                    if (mineField[i][j] == MINE_SQUARE) {
                        System.out.print("1 ");
                    } else {
                        System.out.print("0 ");
                    }
                }
            }
            System.out.println();
        }
    }


    private void initializeMineField() {
        for(int i=0;i<mineField.length;i++){
            for(int j=0;j< mineField[i].length;j++){
                mineField[i][j] = NORMAL_SQUARE;
            }
        }
    }

    private void installMinesInField(int numberOfMines) {
        int numberOfMinesPlaced = 0;


        int totalArea = mineField.length * mineField[0].length;

        while(numberOfMinesPlaced<numberOfMines){
            int randomSquare = RANDOM.nextInt(totalArea);
            int row = randomSquare / mineField.length;
            int col = randomSquare % mineField.length;
            if(mineField[row][col]==NORMAL_SQUARE){
                mineField[row][col] = MINE_SQUARE ;
                numberOfMinesPlaced++;
            }
        }
    }

    public boolean isGameOver(){
        if(status ==GamingStatus.LOST){
            return true;
        } else if (status ==GamingStatus.WON) {
            return true;
        }
        else{
            return false;
        }
    }

    public GamingStatus getStatus() {
        return status;
    }

    public void revealSquare(String givenSquare) throws IllegalArgumentException{
        int row = MineSweeperUtils.getRowFromGivenSquare(givenSquare);
        int col = MineSweeperUtils.getColumnFromGivenSquare(givenSquare);
        try {
            if (mineField[row][col] == MINE_SQUARE) {
                status = GamingStatus.LOST;
            }
            else{
                mineField[row][col] = getAdjacentMines(row,col);
                if(isAllSquaresRevealed()){
                    status = GamingStatus.WON;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
        }

    }

    private boolean isAllSquaresRevealed() {
        for(int i=0;i<mineField.length;i++){
            for(int j=0;j<mineField[i].length;j++){
                if(mineField[i][j]==NORMAL_SQUARE){
                    return false;
                }
            }
        }
        return true;
    }

    private int getAdjacentMines(int row, int col){
        int numberOfAdjacentMines = 0;
        for(int i=row-1;i<=row+1;i++){
            if( i<0 || i>= mineField.length){
                continue;
            }
            for(int j=col-1;j<=col+1;j++){
                if(i==row && j==col){
                    continue;
                }
                if(j<0 || j>=mineField[i].length){
                    continue;
                }
                if (mineField[i][j]==MINE_SQUARE){
                    numberOfAdjacentMines++;
                }
            }
        }
        return numberOfAdjacentMines;
    }
    private boolean isMineSquare(String givenSquare) throws IllegalArgumentException{
        int row = MineSweeperUtils.getRowFromGivenSquare(givenSquare);
        int col = MineSweeperUtils.getColumnFromGivenSquare(givenSquare);
        try {
            if (mineField[row][col] == MINE_SQUARE) {
                status = GamingStatus.LOST;
                return true;
            }
            else{
                return false;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalArgumentException(INCORRECT_INPUT_ERROR_MESSAGE);
        }
    }
}
