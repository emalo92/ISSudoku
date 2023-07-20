package sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.Livello;
import util.Tipo;

class SudokuTest {

	@Test
	void testVerificaConsistenza() {
		Livello l = Livello.MEDIO;
		Tipo t = Tipo.SUDOKU;
		Sudoku s = new Sudoku(t,l);
		System.out.println(s.istruzioni());
		int sudoku [][] = s.getSudoku();
		assertEquals("Result", false, s.verificaConsistenza(1,1,sudoku[1][1]));
	}

}
