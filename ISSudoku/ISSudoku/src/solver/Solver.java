package solver;

import java.util.LinkedList;

public interface Solver {
	
	public int[][] risolvi ( int[][] daRisolvere);
	public boolean verificaConsistenza (int r, int c, int n);
	public boolean getStop();
	public void update(int r, int c);
	public void setStop(boolean b);
	public void setUnaSola(boolean b);
	public LinkedList<int[][]> getListaSol();
	public boolean hasUniqueSolution(int[][] sudoku);
	public void setGriglia(int[][] sudoku);
	public void setNumSoluzioni(int n);
}
