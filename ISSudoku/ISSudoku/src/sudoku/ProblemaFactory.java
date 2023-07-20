package sudoku;

import generatore.GeneratoreGriglia;
import generatore.SudokuGenerator;
import generatore.XSudokuGenerator;
import solver.SSolver;
import solver.Solver;
import solver.XSSolver;
import util.Tipo;

public class ProblemaFactory {
	
	protected Tipo t;
	
	public ProblemaFactory (Tipo t) {
		this.t = t;
	}

	/*public Livello getLivello() { verifica se conviene fare una cosa del genere
	//	return null;
	//}
	*/

	public Tipo getTipo() {
		if (Tipo.SUDOKU == t)
			return t;
		return null;
	}

	/*public String istruzioni() { stessa verifica di su
		if (Tipo.SUDOKU == t)
			return new Sudoku(t).istruzioni();
		return null;
	}
	*/
	
	public Solver getRisolutore() {
		if (Tipo.SUDOKU == t)
			return new SSolver();
		if (Tipo.XSUDOKU == t)
			return new XSSolver();
		return null;
	}
	
	public GeneratoreGriglia getGenerator() {
		if (Tipo.SUDOKU == t)
			return new SudokuGenerator();
		if (Tipo.XSUDOKU == t)
			return new XSudokuGenerator();
		return null;
	}

}
