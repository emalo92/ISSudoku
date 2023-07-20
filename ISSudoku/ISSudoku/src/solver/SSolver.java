package solver;

public class SSolver extends AbstractSolver {
	
	public SSolver () {
		super();
	}

	@Override
	public void update(int r, int c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStop(boolean b) {
		if (b) {
			super.stop = true;
		}
		else super.stop = false;
	}
	
	public void setUnaSola(boolean a) {
		if (a) 
			unaSola = true;
		else unaSola = false;
	}
	
	public boolean verificaConsistenza(int r, int c, int n) {
		if ( verificaRiga(r,0,n) && verificaColonna(0,c,n) && verificaBlocco (r,c,n) ) {
			return true;
		}
			else return false;
	}

	@Override
	public void setGriglia(int[][] sudoku) {
		for (int i = 0; i<sudoku.length; i++) {
			for (int j = 0; j<sudoku.length; j++) {
				this.griglia[i][j] = sudoku [i][j];
			}
		}
		
	}

}
