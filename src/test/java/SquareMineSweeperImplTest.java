import org.gic.minesweeper.GamingStatus;
import org.gic.minesweeper.SquareMinesweeperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

public class SquareMineSweeperImplTest {

    private SquareMinesweeperImpl squareMinesweeper;

    @BeforeEach
    public void setUp() {
        squareMinesweeper = new SquareMinesweeperImpl(4,3);
    }

    @Test
    public void testObjectCreated_success() {
        assertNotNull(squareMinesweeper);
        assertEquals(GamingStatus.PLAYING,squareMinesweeper.getStatus());
        assertFalse(squareMinesweeper.isGameOver());
        
    }

    @Test
    public void testObjectCreation_failure() {
        assertEquals("For the given grid size the maximum number of mines could be placed is 9",
                assertThrows(IllegalArgumentException.class,
                        ()->new SquareMinesweeperImpl(5,10)).getMessage());

        assertEquals("Minimum size of the grid is 2.",
                assertThrows(IllegalArgumentException.class,
                        ()->new SquareMinesweeperImpl(1,10)).getMessage());

        assertEquals("There must be at least 1 mine.",
                assertThrows(IllegalArgumentException.class,
                        ()->new SquareMinesweeperImpl(5,0)).getMessage());
    }

    @Test
    public void testSuccessFlow() throws NoSuchFieldException, IllegalAccessException {
            int[][] mineField = getMineField();
            int mineSquare = getMineSquare();
            for(int i=0;i<mineField.length;i++){
                for(int j=0;j< mineField[i].length;j++){
                    if(mineField[i][j]!=mineSquare){
                        squareMinesweeper.revealSquare(getGameSquare(i,j));
                    }
                }
            }
            assertTrue(squareMinesweeper.isGameOver());
            assertEquals(GamingStatus.WON,squareMinesweeper.getStatus());
    }

    @Test
    public void testFailureFlow() throws NoSuchFieldException, IllegalAccessException {
        int[][] mineField = getMineField();
        int mineSquare = getMineSquare();
        for(int i=0;i<mineField.length;i++){
            for(int j=0;j< mineField[i].length;j++){
                if(mineField[i][j]==mineSquare){
                    squareMinesweeper.revealSquare(getGameSquare(i,j));
                    break;
                }
            }
        }
        assertTrue(squareMinesweeper.isGameOver());
        assertEquals(GamingStatus.LOST,squareMinesweeper.getStatus());
    }

    @Test
    public void testAdjacentMinesFlow() throws NoSuchFieldException, IllegalAccessException {

        final int US = getUnrevealedSquare();
        final int MS = getMineSquare();
        int[][] mineField = {
                {US,US,US,US},
                {US,MS,US,US},
                {US,MS,MS,US},
                {US,US,US,US}
            };
        // set the minefield with a default template
        setMineField(mineField);

        // reveal a non mine square and get the adjacent value
        squareMinesweeper.revealSquare("A1");
        assertFalse(squareMinesweeper.isGameOver());
        assertEquals(GamingStatus.PLAYING,squareMinesweeper.getStatus());
        mineField = getMineField();
        assertEquals(1,mineField[0][0]);

        // reveal a non mine square and get the adjacent value
        squareMinesweeper.revealSquare("B3");
        assertFalse(squareMinesweeper.isGameOver());
        assertEquals(GamingStatus.PLAYING,squareMinesweeper.getStatus());
        mineField = getMineField();
        assertEquals(3,mineField[1][2]);
        assertEquals(US,mineField[0][1]);

        //reveal a mine square and check the game status
        squareMinesweeper.revealSquare("B2");
        assertTrue(squareMinesweeper.isGameOver());
        assertEquals(GamingStatus.LOST,squareMinesweeper.getStatus());
    }


    private String getGameSquare(int row,int col){
        String value = String.valueOf((char)(row+65));
        value += String.valueOf(col+1);
        return value;

    }

    private int[][] getMineField() throws NoSuchFieldException, IllegalAccessException {
        Field mineFieldField = SquareMinesweeperImpl.class.getDeclaredField("mineField");
        mineFieldField.setAccessible(true);
        int[][] mineField = (int[][]) mineFieldField.get(squareMinesweeper);
        return mineField;
    }

    private void setMineField(int[][] mineField) throws NoSuchFieldException, IllegalAccessException {
        Field mineFieldField = SquareMinesweeperImpl.class.getDeclaredField("mineField");
        mineFieldField.setAccessible(true);
        mineFieldField.set(squareMinesweeper,mineField);
    }

    private int getMineSquare() throws NoSuchFieldException, IllegalAccessException {
        Field mineFieldField = SquareMinesweeperImpl.class.getDeclaredField("MINE_SQUARE");
        mineFieldField.setAccessible(true);
        int mineSquare = (int) mineFieldField.get(squareMinesweeper);
        return  mineSquare;
    }

    private int getUnrevealedSquare() throws NoSuchFieldException, IllegalAccessException {
        Field mineFieldField = SquareMinesweeperImpl.class.getDeclaredField("UNREVEALED_SQUARE");
        mineFieldField.setAccessible(true);
        int unrevealedSquare = (int) mineFieldField.get(squareMinesweeper);
        return  unrevealedSquare;
    }




}
