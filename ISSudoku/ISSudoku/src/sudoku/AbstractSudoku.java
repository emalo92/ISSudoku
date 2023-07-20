package sudoku;

import java.util.Random;

import generatore.GeneratoreGriglia;
import solver.Solver;
import util.Livello;
import util.Tipo;

public abstract class AbstractSudoku implements Problema {

	protected Livello liv;
	protected GeneratoreGriglia generatore;
	protected Solver solver;
	protected int[][] sudoku;
	protected boolean [][] permessi; //permessi di scrittura sulla casella
	protected int[][] soluzione;
	protected int numMaxSoluzioni;
	
	public AbstractSudoku (Tipo t, Livello l) {
		this.liv = l;
		ProblemaFactory factory = new ProblemaFactory(t);
		generatore = factory.getGenerator();
		solver = factory.getRisolutore();
		sudoku = new int [9][9]; 
		soluzione = new int [9][9];
		generaSudoku();
		permessi = setPermessi(); 
		numMaxSoluzioni = 1;
	}
	
	public AbstractSudoku (Tipo t) {
		this.liv = null;
		ProblemaFactory factory = new ProblemaFactory(t);
		generatore = factory.getGenerator();
		solver = factory.getRisolutore();
		sudoku = new int [9][9]; 
		soluzione = new int [9][9];
		permessi = setPermessi();
		numMaxSoluzioni = 1;
	}
	
	private void generaSudoku() {
		int a,b; //indici random
		int daRim = 0;
		do {
			soluzione = generatore.creaSchemaCompleto();
			Random r = new Random();
			daRim = 81 - setDaRimuovere();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = soluzione[i][j];
				}
			}
			boolean flag = false;
			while (daRim>0 && !flag) {
				int tentativi = 1000;
				int tmp;
				a = r.nextInt(9);
				b = r.nextInt(9);
				if (sudoku[a][b] != 0) {
					tmp = sudoku[a][b];
					sudoku[a][b] = 0;
					if (solver.hasUniqueSolution(sudoku)) {
						daRim--;
					}
					else {
						sudoku[a][b] = tmp;
						tentativi--;
					}
				}
				if (tentativi == 0) {
					flag = true;
				}
			}
		}
		while ( !solver.hasUniqueSolution( sudoku) );
				
		//TODO stavo rincoglionendo, verifica correttezza
		
	}
	
	private int setDaRimuovere() {
		if (liv == Livello.FACILE) return 39; 
		if (liv == Livello.MEDIO) return 36; 
		if (liv == Livello.DIFFICILE) return 33; 
		if (liv == Livello.ESTREMO) return 30; 
		return 0;
		
	}

	public boolean[][] setPermessi() {
		boolean tmp[][]=new boolean[9][9];
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++){
				if(sudoku[i][j]!=0)
					tmp[i][j]=false;
				else
					tmp[i][j]=true;
			}
		return tmp;
	}
	
	public int getValore(int i, int j) {
		return this.sudoku[i][j];
	}
	
	public Solver getRisolutore() {
		return this.solver;
	}
	
	public int[][] getSudoku() {
		return this.sudoku;
	}

	public Livello getLivello() {
		return this.liv;
	}

	@Override
	public Tipo getTipo() {
		return Tipo.SUDOKU; //ho tolto variabile tipo
	}

	@Override
	public String istruzioni() {
		String regole = "Il SUDOKU\r\n" + 
				"È un gioco di logica nel quale al giocatore viene proposta una griglia di 9×9 celle, ciascuna\r\n" + 
				"delle quali può contenere un numero da 1 a 9, oppure essere vuota; la griglia è suddivisa in 9\r\n" + 
				"righe orizzontali, 9 colonne verticali e, da bordi in neretto, in 9 \"sottogriglie\", chiamate regioni,\r\n" + 
				"di 3×3 celle contigue. Le griglie proposte al giocatore hanno da 20 a 35 celle contenenti un\r\n" + 
				"numero. Lo scopo del gioco è quello di riempire le caselle bianche con numeri da 1 a 9, in\r\n" + 
				"modo tale che in ogni riga, colonna e regione siano presenti tutte le cifre da 1 a 9 e, pertanto,\r\n" + 
				"senza ripetizioni.\r\n" + 
				"La difficoltà di uno schema dipende dalla quantità di numeri preinseriti";
		return regole;
	}







}
