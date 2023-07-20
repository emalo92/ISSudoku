package sudokuGui;

import javax.swing.*;
import javax.swing.border.Border;

import sudoku.*;
import util.Livello;
import util.Tipo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel{
	private static final long serialVersionUID = -8950381894871756728L;
	private int selectedI,selectedJ,sec, min,width,height;
	private boolean risolto,iniziato,xSudoku;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	private CellPanel griglia[][];
	private Sudoku sudoku;
	private Timer timer;
	private boolean permessi[][];
	private JButton button;
	private JLabel tempo;
	private boolean aTempo;
	private ImageIcon background;
	//private GestoreRecords gestore;
	//private Records.Livello liv;
	//private Records.Tipo tip;
	public GamePanel(Livello livello, Tipo tipo,int width,
			int height,boolean aTempo)
			//GestoreRecords gestore)
			{
		/*liv=Records.Livello.FACILE;
		tip=Records.Tipo.SUDOKU;
		if(livello==Livello.MEDIO)
			liv=Records.Livello.MEDIO;
		if(livello==Livello.DIFFICILE)
			liv=Records.Livello.DIFFICILE;
		if(livello==Livello.DIABOLICO)
			liv=Records.Livello.DIABOLICO;
		if(tipo==TipoSudoku.XSUDOKU)
			tip=Records.Tipo.XSUDOKU;
		this.gestore=gestore;
		*/
		this.aTempo=aTempo;
		this.width=width;
		this.height=height;
		if(tipo==Tipo.XSUDOKU)
			xSudoku=true;
		else
			xSudoku=false;
		background=new ImageIcon(this.getClass().getResource("/immagini/sfondo.jpg"));
		create();
		setListener();
		disposeGameElement(livello,tipo);
		setFocusable(true);
		requestFocus();
	}
	private void create(){
		selectedI=0; 
		selectedJ=0; 
		sec=0; 
		min=0;
		risolto=false; 
		iniziato=false;
		layout=new GridBagLayout();
		gbc=new GridBagConstraints();
		griglia=new CellPanel[9][9];
		button=new JButton("Inizia");
	}
	
	private void setListener(){
		button.addActionListener(new Listener());
		button.addKeyListener(new Key());
		this.addKeyListener(new Key());
		timer=new Timer(1000,new Time());
	}
	
	private void disposeGameElement(Livello livello,Tipo tipo){
		sudoku=new Sudoku(tipo, livello);
		this.setLayout(layout);
		permessi=sudoku.getPermessi();
		setGriglia();
		aggiornaGriglia();
		if(aTempo){
			gbc.gridwidth=3;
			gbc.gridx=3;
			gbc.gridy=9;
			Dimension d=new Dimension(93,25);
			gbc.insets=new Insets(5,3,0,0);
			layout.setConstraints(button, gbc);
			button.setPreferredSize(d);
			this.add(button);
			gbc.gridwidth=3;
			gbc.gridx=7;
			tempo=new JLabel("00:00");
			layout.setConstraints(tempo, gbc);
			this.add(tempo);
		}
		else
			iniziato=true;
	}
	private void aggiornaGriglia(){
		int tmp[][]=sudoku.getSudoku();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(tmp[i][j]!=0){
					griglia[i][j].setString(Integer.toString(tmp[i][j]),CellPanel.Tipo.FISSO);
				}
			}
		}
	}
	private void setGriglia(){
		griglia=new CellPanel[9][9];
		Dimension d=new Dimension(30,30);
		for(int i=0; i<9;i++){
			for(int j=0; j<9;j++){
				if(i!=j&&j!=8-i)
					griglia[i][j]=new CellPanel();
				else{
					if(xSudoku)
						griglia[i][j]=new CellPanel(CellPanel.Selezione.DIAGONALE);
					else
						griglia[i][j]=new CellPanel();
				}
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
							gbc.insets=new Insets(0,4,0,0);
						else
							gbc.insets=new Insets(0,2,0,0); //layout centrato prima cifra
						
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
	private void ascoltaBottone(){
		this.remove(button);
		this.validate();
		this.repaint();
		iniziato=true;
		timer.start();
	}
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ascoltaBottone();
		}
	}
	private JPanel getThis(){return this;}
	private class Key implements KeyListener{
		public void keyPressed(KeyEvent e) {	
			if(e.getKeyCode()==KeyEvent.VK_SPACE){
				ascoltaBottone();
			}
			if(!risolto&&iniziato){
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE){
						if(selectedI!=selectedJ&&selectedJ!=8-selectedI)
							griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						else{
							if(xSudoku)
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.DIAGONALE);
							else
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						}
					}
					if(selectedJ==0)
						selectedJ=8;
					else
						selectedJ--;
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE){
						if(selectedI!=selectedJ&&selectedJ!=8-selectedI)
							griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						else{
							if(xSudoku)
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.DIAGONALE);
							else
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						}
					}
					if(selectedJ==8)
						selectedJ=0;
					else
						selectedJ++;
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
				}
				if(e.getKeyCode()==KeyEvent.VK_UP){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE){
						if(selectedI!=selectedJ&&selectedJ!=8-selectedI)
							griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						else{
							if(xSudoku)
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.DIAGONALE);
							else
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						}
					}
					if(selectedI==0)
						selectedI=8;
					else
						selectedI--;
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE)
						griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.SELEZIONATO);
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					if(griglia[selectedI][selectedJ].getBG()!=CellPanel.Selezione.ERRORE){
						if(selectedI!=selectedJ&&selectedJ!=8-selectedI)
							griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						else{
							if(xSudoku)
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.DIAGONALE);
							else
								griglia[selectedI][selectedJ].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						}
					}
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
			if(permessi[x][y]){
				int[][] tmp=sudoku.getSudoku();
				if(tmp[x][y]!=daInserire&&daInserire!=0){
					if(!sudoku.verificaConsistenza(x, y, daInserire))
						griglia[x][y].setBG(CellPanel.Selezione.ERRORE);
					else
						griglia[x][y].setBG(CellPanel.Selezione.SELEZIONATO);
					sudoku.add(x, y, daInserire);
					Integer ins=daInserire;
					griglia[x][y].setString(ins.toString(),CellPanel.Tipo.VARIABILE);
					repaint();
				}
				else{
					sudoku.add(x, y, 0);
					griglia[x][y].setBG(CellPanel.Selezione.SELEZIONATO);
					griglia[x][y].setString("",CellPanel.Tipo.VARIABILE);
					repaint();
				}
				regolaConflitti();
				if(sudoku.checkSolution()){
					risolto=true;
					timer.stop();
					getThis().remove(button);
					getThis().validate();
					getThis().repaint();
					if (aTempo) {
						JOptionPane.showMessageDialog(getThis(),"Hai risolto il sudoku in "+
								min+" min :"+sec+" sec ");
					}
					else JOptionPane.showMessageDialog(getThis(), "Hai risolto il sudoku");
					/* if(aTempo){
						if(gestore.setRecords(liv, tip, min, sec))
							JOptionPane.showMessageDialog(getThis(), "Nuovo Record!\n" +
									"Hai risolto il sudoku in "+
								min+" min :"+sec+" sec ");
						else
							JOptionPane.showMessageDialog(getThis(),"Hai risolto il sudoku in "+
								min+" min :"+sec+" sec ");
						gestore.salva();
					}
					else*/
				}
			}
		}
		private void regolaConflitti(){
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(griglia[i][j].getBG()==CellPanel.Selezione.ERRORE){
						int x=sudoku.getValore(i, j);
						sudoku.add(i, j, 0);
						if(!sudoku.verificaConsistenza(i, j, x))
							griglia[i][j].setBG(CellPanel.Selezione.ERRORE);
						else{
							if(i==j||j==8-i&&xSudoku)
								griglia[i][j].setBG(CellPanel.Selezione.DIAGONALE);
							else
								griglia[i][j].setBG(CellPanel.Selezione.NON_SELEZIONATO);
						}
						sudoku.add(i, j, x);
						Integer ins=x;
						griglia[i][j].setString(ins.toString(),CellPanel.Tipo.VARIABILE);
						repaint();
					}
				}
			}
		}
	}
	private class Time implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(sec<59)
				sec++;
			else{
				sec=0;
				min++;
			}
			remove(tempo);
			validate();
			gbc.gridwidth=3;
			gbc.gridx=7;
			if(min<10&&sec<10)
				tempo=new JLabel("0"+Integer.toString(min)+":0"+Integer.toString(sec));
			if(min>=10&&sec<10)
				tempo=new JLabel(Integer.toString(min)+":0"+Integer.toString(sec));
			if(min<10&&sec>=10)
				tempo=new JLabel("0"+Integer.toString(min)+":"+Integer.toString(sec));
			if(min>=10&&sec>=10)
				tempo=new JLabel(Integer.toString(min)+":"+Integer.toString(sec));
			layout.setConstraints(tempo, gbc);
			add(tempo);
			validate();
		}
	}	
}