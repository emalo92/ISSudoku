package generatore;

import solver.Solver;
import solver.XSSolver;

public class XSudokuGenerator implements GeneratoreGriglia {

private Solver solver;
	
	public XSudokuGenerator () {
		solver = new XSSolver();
	}

	@Override
	public int[][] creaSchemaCompleto() {
		int[][] schema = new int [9][9];
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
			schema [i][j] = 0;
			}
		}
		solver.setUnaSola(true);
		schema = solver.risolvi(schema);
		solver.setUnaSola(false);
		return schema;
	}
	
}