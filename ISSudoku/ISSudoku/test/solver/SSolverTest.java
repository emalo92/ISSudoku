package solver;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import generatore.GeneratoreGriglia;
import generatore.SudokuGenerator;

class SSolverTest {


	@Test
	void testRisolviIntArrayArrayInt() {
		Solver so = new SSolver();
		Scanner sc = new Scanner(System.in);
		int sudoku [][] ={{0,0,0,0,3,0,5,0,0},
				   		 {0,0,0,1,6,0,3,0,7},
				   		 {0,0,0,4,0,0,0,0,8},
				   		 {0,0,0,3,0,0,0,0,0},
				   		 {0,0,2,6,0,0,4,0,0},
				   		 {0,9,0,5,7,0,0,0,0},
				   		 {0,1,0,0,0,0,0,0,0},
				   		 {0,2,0,9,0,0,7,0,0},
				   		 {0,3,6,0,0,5,0,0,0}
						};
		
		
		int soluzione [][] = new int[9][9];
		soluzione = so.risolvi(sudoku); 
		
		for (int i = 0;i<soluzione.length; i++) {
			for (int j = 0;j<soluzione.length; j++) {
				System.out.print(soluzione[i][j]);
				System.out.print(",");
			}
			System.out.println();
		}
		
		assertEquals("Result", true, soluzione == so.getListaSol().getFirst());
		
		
		for (int i = 0; i<so.getListaSol().size(); i++) {
			soluzione = so.getListaSol().get(i);
			for (int k = 0;k<soluzione.length; k++) {
				for (int j = 0;j<soluzione.length; j++) {
					System.out.print(soluzione[k][j]);
					System.out.print(",");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		assertEquals("Result", 1000 , so.getListaSol().size());
		
	}

}
