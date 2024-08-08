import org.gic.minesweeper.MineSweeperUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MineSweeperUtilsTest {

    @Test
    public void testGridSize_success(){
        assertEquals(4, MineSweeperUtils.Validator.gridSize(4));
        assertEquals(10,MineSweeperUtils.Validator.gridSize(10));
        assertEquals(2,MineSweeperUtils.Validator.gridSize(2));
        assertEquals(3,MineSweeperUtils.Validator.gridSize("3"));
        assertEquals(10,MineSweeperUtils.Validator.gridSize("10"));
    }

    @Test
    public void testGridSize_tooLarge(){
        // Integer input
        assertTrue(assertThrows(IllegalArgumentException.class,
                ()->MineSweeperUtils.Validator.gridSize(100)).getMessage().equals("Maximum size of grid is 10."));
        // String input
        assertTrue(assertThrows(IllegalArgumentException.class,
                ()->MineSweeperUtils.Validator.gridSize("90")).getMessage().equals("Maximum size of grid is 10."));
    }
    @Test
    public void testGridSize_tooSmall(){
        // String input
        assertTrue(assertThrows(IllegalArgumentException.class,
                ()->MineSweeperUtils.Validator.gridSize(100))
                .getMessage().equals("Maximum size of grid is 10."));

        // Integer input
        assertTrue(assertThrows(IllegalArgumentException.class,
                ()->MineSweeperUtils.Validator.gridSize(0))
                .getMessage().equals("Minimum size of the grid is 2."));
    }

    @Test
    public void testGridSize_InvalidStringInput(){
        // String input
        assertTrue(assertThrows(IllegalArgumentException.class,
                ()->MineSweeperUtils.Validator.gridSize("A"))
                .getMessage().equals(MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE));

    }

    @Test
    public void testNumberOfMines_success(){
        assertEquals(3,MineSweeperUtils.Validator.numberOfMines(16,3));
        assertEquals(3,MineSweeperUtils.Validator.numberOfMines(10,"3"));
    }

    @Test
    public void testNumberOfMines_tooLowMines(){
        assertEquals("There must be at least 1 mine.",
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Validator.numberOfMines(16,0)).getMessage());
        assertEquals("There must be at least 1 mine.",
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Validator.numberOfMines(10,"-1")).getMessage());
    }

    @Test
    public void testNumberOfMines_Incorrect(){
        assertEquals(MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE,
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Validator.numberOfMines(16,"A")).getMessage());
    }

    @Test
    public void testNumberOfMines_tooHighNumberOfMines(){
        assertEquals("For the given grid size the maximum number of mines could be placed is 6",
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Validator.numberOfMines(4,25)).getMessage());
        assertEquals("For the given grid size the maximum number of mines could be placed is 4",
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Validator.numberOfMines(3,"9")).getMessage());
    }

    @Test
    public void testExtractRowIndex_success(){
        assertEquals(0,MineSweeperUtils.Parser.extractRowIndex("A1"));
        assertEquals(4,MineSweeperUtils.Parser.extractRowIndex("e1"));
        assertEquals(6,MineSweeperUtils.Parser.extractRowIndex("G1"));

    }

    @Test
    public void testExtractRowIndex_Incorrect(){
        assertEquals(MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE,
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Parser.extractRowIndex("#45")).getMessage());
        assertEquals(MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE,
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Parser.extractRowIndex("45A")).getMessage());

    }


    @Test
    public void testExtractColumnIndex_success(){
        assertEquals(22,MineSweeperUtils.Parser.extractColumnIndex("A23"));
        assertEquals(-1,MineSweeperUtils.Parser.extractColumnIndex("e0"));
        assertEquals(35,MineSweeperUtils.Parser.extractColumnIndex("G36"));

    }

    @Test
    public void testExtractColumnIndex_Incorrect(){
        assertEquals(MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE,
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Parser.extractColumnIndex("AA2")).getMessage());
        assertEquals(MineSweeperUtils.INCORRECT_INPUT_ERROR_MESSAGE,
                assertThrows(IllegalArgumentException.class,
                        ()->MineSweeperUtils.Parser.extractColumnIndex("e24A")).getMessage());

    }
}
