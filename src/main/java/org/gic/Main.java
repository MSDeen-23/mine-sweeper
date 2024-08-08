package org.gic;

import org.gic.minesweeper.Minesweeper;
import org.gic.minesweeper.SquareMinesweeperImpl;
import org.gic.minesweeper.MineSweeperUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Minesweeper!");
        String continueInput;
        do {
            int gridSize,numberOfMines;

            while (true){
                try {
                    System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
                    gridSize = MineSweeperUtils.Validator.gridSize(bufferedReader.readLine());
                    break;
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
                    numberOfMines = MineSweeperUtils.Validator.numberOfMines(bufferedReader.readLine(),gridSize);
                    break;
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }

            Minesweeper mineSweeper = new SquareMinesweeperImpl(gridSize,numberOfMines);

            while(!mineSweeper.isGameOver()){
                System.out.println("Here is your mineField");
                mineSweeper.displayMinefield();
                System.out.print("Select a square to reveal (e.g. A1): ");
                String currentGameSquare = bufferedReader.readLine().toUpperCase();
                try {
                    mineSweeper.revealSquare(currentGameSquare);
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(mineSweeper.getStatus().toString());
            System.out.println("The minefield was:");
            mineSweeper.displayMinefield();
            System.out.print("Press any key to continue or press X to exit: ");
            continueInput = bufferedReader.readLine().toUpperCase();

        }while (!continueInput.equals("X"));

    }
}