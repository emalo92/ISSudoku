package sudoku;

import util.Livello;
import util.Tipo;

public class Sudoku extends AbstractSudoku {
	
	public Sudoku (Tipo t, Livello l) {
		super(t,l);
	}
	
	public Sudoku (Tipo t) {
		super (t);
	}
	
	public void add(int i, int j, int valore) {
		if (permessi[i][j]) {
			sudoku[i][j]= valore;
		}
	}
	
	public boolean checkSolution () {
		int incorrispondenze = 0;
		for ( int i = 0; i < sudoku.length ; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				if (soluzione[i][j]!=sudoku[i][j]) {
					incorrispondenze++;
				}
			}
		}
		if (incorrispondenze==0) {
			return true;
		}
		return false;
	}
	
	public void setNumSoluzioni (int s) {
		numMaxSoluzioni = s;
	}

	public boolean verificaConsistenza(int r, int c, int n) {
		solver.setGriglia(sudoku);
		return solver.verificaConsistenza(r, c, n);
	}

	public boolean[][] getPermessi() {
		// TODO Auto-generated method stub
		return permessi;
	}


}
