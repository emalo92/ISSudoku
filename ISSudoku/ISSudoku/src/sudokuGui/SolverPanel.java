package sudokuGui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import solver.SSolver;
import solver.Solver;

public class SolverPanel extends JPanel{
	private static final long serialVersionUID = -5185142006961358400L;
	private int selectedI,selectedJ,width,height;
	private boolean risolto;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	private CellPanel griglia[][];
	private int[][] sudoku;
	private JButton button,buttonN,buttonP;
	private ImageIcon background;
	private Solver solver;
	private LinkedList<int[][]> soluzioni;
	private int indice = 0;
	
	public SolverPanel(int width,int height){
		this.width=width;
		this.height=height;
		background=new ImageIcon(this.getClass().getResource("/immagini/sfondo.jpg"));
		create();
		setListener();
		disposeGameElement();
		this.setFocusable(true);
	}
	private void create(){
		solver=new SSolver();
		selectedI=0; 
		selectedJ=0; 
		sudoku=new int[9][9];
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				sudoku[i][j]=0;
		risolto=false;
		layout=new GridBagLayout();
		gbc=new GridBagConstraints();
		griglia=new CellPanel[9][9];
		button=new JButton("Risolvi");
		soluzioni = new LinkedList<int[][]>();
		buttonN = new JButton ("Next");
		buttonP = new JButton("Prev");
	}
	private void setListener(){
		button.addActionListener(new Listener());
		button.addKeyListener(new Key());
		this.addKeyListener(new Key());
		buttonN.addActionListener(new Listener());
		buttonP.addActionListener(new Listener());
		
	}
	private void disposeGameElement(){		
		this.setLayout(layout);
		setGriglia();
		gbc.gridwidth=3;
		gbc.gridx=3;
		gbc.gridy=9;
		Dimension d=new Dimension(93,25);
		gbc.insets=new Insets(5,3,0,0);
		layout.setConstraints(button, gbc);
		button.setPreferredSize(d);
		this.add(button);
	}
	private void risolviGriglia(){
		try {
			String n;
			n = JOptionPane.showInputDialog("Definisci un numero massimo di soluzioni.");
			solver.setNumSoluzioni(Integer.parseInt(n));
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Il campo input deve contenere solo numeri.");
			return;
		}
		sudoku = solver.risolvi(sudoku);
		for (int i = 0; i<solver.getListaSol().size(); i++) {
			soluzioni.add(solver.getListaSol().get(i));
		}
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(sudoku[i][j]!=0){
					griglia[i][j].setString(Integer.toString(sudoku[i][j]),CellPanel.Tipo.FISSO);
				}
			}
		}
		risolto=true;
		this.remove(button);
		this.validate();
		this.repaint(); ///////BUtton quaaa
		if (soluzioni.size()==1)
			JOptionPane.showMessageDialog(this,"soluzione unica.");
		else if (soluzioni.size()==0)
			JOptionPane.showMessageDialog(this,"nessuna soluzione trovata, verifica correttezza.");
		else {
			if (soluzioni.size()>999)
				JOptionPane.showMessageDialog(this,"Soluzioni superiori a 1000, verranno mostrate solo queste.");
			else JOptionPane.showMessageDialog(this,"Sono state trovate "+soluzioni.size()+" soluzioni.");
			Dimension d=new Dimension(93,25);
			gbc.insets=new Insets(5,3,0,0);
			layout.setConstraints(buttonN, gbc);
			buttonN.setPreferredSize(d);
			this.add(buttonN);
			this.validate();
			this.repaint();
		}
		
	}
	private void setGriglia(){
		griglia=new CellPanel[9][9];
		Dimension d=new Dimension(30,30);
		for(int i=0; i<9;i++){
			for(int j=0; j<9;j++){
				griglia[i][j]=new CellPanel();
				griglia[i][j].setPreferredSize(d);
				griglia[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				if(i==3||i==6){
					if(j==3||j==6)
						gbc.insets=new Insets(4,4,0,0);
					else
						gbc.insets=new Insets(4,2,0,0);
				}
				else{
					if(i==0){
						if(j==3||j==6)
							gbc.insets=new Insets(30,4,0,0);
						else
							gbc.insets=new Insets(30,2,0,0);
						
					}
					else
						if(j==3||j==6)
							gbc.insets=new Insets(2,4,0,0);
						else
							gbc.insets=new Insets(2,2,0,0);
				}
				gbc.gridy=i;
				gbc.gridx=j;
				layout.setConstraints(griglia[i][j], gbc);
				this.add(griglia[i][j]);
			}
		}
		griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
	}
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.drawImage(background.getImage(),0,0,width,height,null,this);
	}
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
					if (e.getSource() == button)
						risolviGriglia();
					if (e.getSource() == buttonN) {
						indice++;
						switchGriglia(indice);
					}
					if (e.getSource() == buttonP) {
						indice--;
						switchGriglia(indice);
				}
		}
	}
	private class Key implements KeyListener{
		public void keyPressed(KeyEvent e) {	
			//if(e.getKeyCode()==KeyEvent.VK_SPACE){
				//risolviGriglia(0);
			//}
			if(!risolto){
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
					if(selectedJ==0)
						selectedJ=8;
					else
						selectedJ--;
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
					if(selectedJ==8)
						selectedJ=0;
					else
						selectedJ++;
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
				}
				if(e.getKeyCode()==KeyEvent.VK_UP){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
					if(selectedI==0)
						selectedI=8;
					else
						selectedI--;
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
					if(selectedI==8)
						selectedI=0;
					else
						selectedI++;
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
				}
				if(e.getKeyCode()==KeyEvent.VK_1||e.getKeyCode()==KeyEvent.VK_NUMPAD1){
					inserisci(selectedI,selectedJ,1);
				}
				if(e.getKeyCode()==KeyEvent.VK_2||e.getKeyCode()==KeyEvent.VK_NUMPAD2){
					inserisci(selectedI,selectedJ,2);
				}
				if(e.getKeyCode()==KeyEvent.VK_3||e.getKeyCode()==KeyEvent.VK_NUMPAD3){
					inserisci(selectedI,selectedJ,3);
				}
				if(e.getKeyCode()==KeyEvent.VK_4||e.getKeyCode()==KeyEvent.VK_NUMPAD4){
					inserisci(selectedI,selectedJ,4);
				}
				if(e.getKeyCode()==KeyEvent.VK_5||e.getKeyCode()==KeyEvent.VK_NUMPAD5){
					inserisci(selectedI,selectedJ,5);
				}
				if(e.getKeyCode()==KeyEvent.VK_6||e.getKeyCode()==KeyEvent.VK_NUMPAD6){
					inserisci(selectedI,selectedJ,6);
				}
				if(e.getKeyCode()==KeyEvent.VK_7||e.getKeyCode()==KeyEvent.VK_NUMPAD7){
					inserisci(selectedI,selectedJ,7);
				}
				if(e.getKeyCode()==KeyEvent.VK_8||e.getKeyCode()==KeyEvent.VK_NUMPAD8){
					inserisci(selectedI,selectedJ,8);
				}
				if(e.getKeyCode()==KeyEvent.VK_9||e.getKeyCode()==KeyEvent.VK_NUMPAD9){
					inserisci(selectedI,selectedJ,9);
				}
				if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
					inserisci(selectedI,selectedJ,0);
				}
			}
		}
		public void keyReleased(KeyEvent e) {
		}
		public void keyTyped(KeyEvent e) {
		}
		private void inserisci(int x,int y,int daInserire){
			if(sudoku[x][y]!=daInserire&&daInserire!=0){
				sudoku[x][y]=daInserire;
				Integer ins=daInserire;
				griglia[x][y].setString(ins.toString(),CellPanel.Tipo.VARIABILE);
				griglia[selectedI][selectedJ].repaint();
				repaint();
			}else{
				sudoku[x][y]=0;
				griglia[x][y].setBG(CellPanel.Selezione.SELEZIONATO);
				griglia[x][y].setString("",CellPanel.Tipo.VARIABILE);
				griglia[selectedI][selectedJ].repaint();
				repaint();
			}
		}
	}
	
	public void switchGriglia(int indice) {
		
		if (indice<0) {
			JOptionPane.showMessageDialog(this,"Non consentito");
			return;
		}
		
		if (indice >= soluzioni.size()) {
			JOptionPane.showMessageDialog(this,"Non consentito, lista terminata");
			return;
		}
		this.removeAll();
		this.validate();
		this.repaint();

		sudoku = soluzioni.get(indice);
		layout=new GridBagLayout();
		gbc=new GridBagConstraints();
		griglia=new CellPanel[9][9];
		this.setLayout(layout);
		setGriglia();
		gbc.gridwidth=3;
		gbc.gridx=3;
		gbc.gridy=9;
		
		Dimension d=new Dimension(96,25);
		gbc.insets=new Insets(0,3,0,0);
		layout.addLayoutComponent(buttonP, gbc);
		buttonP.setPreferredSize(d);
		this.add(buttonP);
		gbc.insets=new Insets(45,3,0,0);
		layout.addLayoutComponent(buttonN, gbc);
		buttonN.setPreferredSize(d);
		this.add(buttonN);
		
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(sudoku[i][j]!=0){
					griglia[i][j].setString(Integer.toString(sudoku[i][j]),CellPanel.Tipo.FISSO);
				}
			}
		}
		this.validate();
		this.repaint();
	}
	
}
