package sudoku;

import solver.Solver;
import util.Livello;
import util.Tipo;

public interface Problema {
	
	public Livello getLivello();
	public Tipo getTipo();
	public String istruzioni();
	public Solver getRisolutore();
	public boolean verificaConsistenza(int r, int c, int v);

}
