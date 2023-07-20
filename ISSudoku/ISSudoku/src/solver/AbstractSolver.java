package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;

import util.Casella;

public abstract class AbstractSolver implements Solver {
	
	protected final int MAXSOL = 1000;
	protected Random random;
	protected int numSoluzioni = 0;
	protected boolean stop;
	protected int[][] griglia;
	protected LinkedList<Casella> listaCas;
	protected int rimanenti = 0;
	protected LinkedList<int[][]> listaSol;
	protected boolean unaSola;
	protected boolean dueSole;
	protected boolean isUnica, verifica;
	protected int maxSoluzioni;
	
	public AbstractSolver() {
		random = new Random();
		stop = false;
		griglia = new int [9][9];
		unaSola = false;
		dueSole = false;
		isUnica = false;
		verifica = true;
		maxSoluzioni = MAXSOL;
	}
	
	private void inizializzaLista() { //per distinguere tipo, esporta questa classe fuori dalla astratta
		listaSol = new LinkedList<int[][]>();
		listaCas = new LinkedList<Casella>();
		/*
		//random
		int tmp [][] = new int [9][9];
		Casella v;
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				if (griglia [i][j] == 0) {
					rimanenti++;
				}
				tmp[i][j] = griglia [i][j];
			}
		}
		int indice = 0;
		int a = random.nextInt(9);
		int b = random.nextInt(9);
		v = new Casella (a,b);
		while (indice < rimanenti) {
			if (tmp[v.riga][v.colonna] == 0) {
				listaCas.add(v);
				tmp [v.riga][v.colonna] = -1;
				indice++;
			}
			a = random.nextInt(9);
			b = random.nextInt(9);
			v = new Casella (a, b);
		}
		*/
		
		/*
		//risoluzione per righe
				for (int i = 0; i<9; i++) {
					for (int j = 0; j<9; j++) {
						if (griglia [i][j] == 0) {
							listaCas.add(new Casella(i,j));
							rimanenti++;
						}
					}
				}
			
				
		*/
		//risoluzione per colonne
		
		for (int i = 0; i<9; i++) {
			for (int j = 0; j<9; j++) {
				if (griglia [j][i] == 0) {
					listaCas.add(new Casella(j,i));
					rimanenti++;
				}
			}
		}
		//Collections.shuffle(listaCas);
	}
	
	public LinkedList<int[][]> getListaSol() {
		return this.listaSol;
	}
	
	public int[][] getGriglia() {
		return this.griglia;
	}
	
	public boolean getStop() {
		return this.stop;
	}
	
	public void setNumSoluzioni(int n) {
		this.maxSoluzioni = n;
	}
	
	public LinkedList<Casella> listaCas () {
		return this.listaCas;
	}
	
	public LinkedList<int[][]> risolvi (int daRisolvere[][], int numMaxSoluzioni) {
		maxSoluzioni = numMaxSoluzioni;
		risolvi (daRisolvere);
		return listaSol;
	}
	
	public boolean hasUniqueSolution( int daRisolvere[][] ) {
		verifica = false;
		isUnica = false;
		dueSole = true;
		risolvi( daRisolvere );
		if (numSoluzioni == 1)
			isUnica = true;
		else isUnica = false;
		dueSole = false;
		verifica = true;
		return isUnica;
	}
	
	public int[][] risolvi (int daRisolvere[][]) {
		
		this.griglia = daRisolvere;
		inizializzaLista();
		int tmp;
		if (verifica) {
			for (int i = 0; i<griglia.length;i++) {
				for (int j = 0; j<griglia.length;j++) {
					if (griglia[i][j]!=0) {
						tmp = griglia[i][j];
						griglia[i][j] = 0;
						if (!verificaConsistenza(i,j,tmp)) {
							griglia[i][j] = tmp;
							return daRisolvere;
						}
						griglia[i][j] = tmp;
							
					}
				}
			}
		}
		//if (risolvi(0)) {
			risolvi(0);
			System.out.println("finittooo");
			numSoluzioni = listaSol.size();
			return listaSol.getFirst();
	}
	
	private void risolvi(int k) {
		System.out.println("partito");
		if(unaSola) {
			if (listaSol.size()==1)
				return;
		}
		if(dueSole) {
			if (listaSol.size()==2)
				return;
		}
		if(stop) { //termina immediatamente
			System.out.println("terminato");
			return;
		}
		if (maxSoluzioni == listaSol.size()) {
			return;
		}
		ArrayList<Integer> numeri = new ArrayList<Integer>();
			
		if ( k >= listaCas.size()) {
			System.out.println("totale assegnamenti: " +(k));
			int[][] solProvvisoria = new int[9][9];
			for (int i = 0 ; i<griglia.length; i++) {
				for (int j = 0 ; j<griglia.length; j++) {
					solProvvisoria[i][j] = griglia [i][j];
				}
			}
			listaSol.add(solProvvisoria);
			return; //se vuoi mantenere le soluzioni aggiungi una lista qui 
			//attento alle condizioni.
		}//////metti i valori a random con una lista e shuflle
		Casella cell = listaCas.get(k);
		for (int i = 1; i<= 9; i++) {
			numeri.add(i);
		}
		Collections.shuffle(numeri);
		for (int i = 0; i<numeri.size(); i++) {
			if (verificaConsistenza(cell.getRiga(), cell.getColonna(), (int)numeri.get(i) )) {
				griglia [cell.getRiga()][cell.getColonna()] = (int)numeri.get(i);
				System.out.println("verificato");
				//update (riga ,colonna) nik è stato qui
				//if (risolvi(k+1)) {
					//System.out.println("trovato");
					//return true;
				risolvi(k+1);
			}
		}
		if (stop) {
			return;
		}
		griglia [cell.getRiga()][cell.getColonna()] = 0;
		//update...
		return;
	}
	
	public abstract boolean verificaConsistenza(int r, int c, int n);
	
	protected boolean verificaRiga(int r, int c, int n) {
		if(c<9) {
			if (this.griglia[r][c] == n) {
				return false;
			}
			else {
				return verificaRiga (r, c+1, n);
			}
		}
		return true;
	}
	
	protected boolean verificaColonna(int r, int c, int n) {
		if(r<9) {
			if (this.griglia[r][c] == n) {
				return false;
			}
			else {
				return verificaColonna (r+1, c, n);
			}
		}
		return true;
	}
	
	protected boolean verificaBlocco (int r, int c, int n) {
		r = (r/3)*3;
		c = (c/3)*3;
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<3; j++) {
				if (griglia[r+i][c+j] == n) {
					return false;
				}
			}
		}
		return true;
				
	}
	
	protected boolean verificaDiagonali(int r, int c, int n) {
		if (r==c) {
			for (int j = 0; j<9; j++) {
				if (griglia[j][j] == n )
					return false;
			}
		}
		if (r==8-c) {
			for (int j = 0; j<9; j++) {
				if (griglia[j][8-j] == n )
					return false;
			}
		}
		return true;
	}

}
