package generatore;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import solver.SSolver;
import solver.Solver;

class SudokuGeneratorTest {

	@Test
	void testCreaSchemaCompleto() {
		GeneratoreGriglia sg = new SudokuGenerator();
		Solver so = new SSolver();
		int sudoku [][] = new int[9][9];
		int soluzione [][] = new int[9][9];
		sudoku = sg.creaSchemaCompleto();
		for (int i = 0;i<soluzione.length; i++) {
			for (int j = 0;j<soluzione.length; j++) {
					System.out.print(sudoku[i][j]);
					System.out.print(",");
					assertEquals("Result", true, so.verificaConsistenza(i, j, sudoku[i][j]));
					
			}
			System.out.println();
		}
	}

}
