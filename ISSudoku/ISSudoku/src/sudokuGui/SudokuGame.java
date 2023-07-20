package sudokuGui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import sudoku.*;
import util.Livello;
import util.Tipo;


public class SudokuGame extends JFrame {
	private static final long serialVersionUID = -1699463406692644999L;
	private GamePanel gamePanel;
	private InfoPanel infoPanel;
	private MenuPanel menuPanel;
	private SolverPanel solverPanel;
	private JMenuBar menu;
	private JMenu file,help,normale,xsudoku,solver,aTempo,nTempo,xTempo;
	private JMenuItem nF,nM,nDif,nDia,xF,xM,xDif,xDia,aNF,aNM,aNDif,aNDia,
	aXF,aXM,aXDif,aXDia,esci,about,record,nuovoSolver;
	private JSeparator s1,s2;
	private boolean mp,gp,sp,ip;
	//private GestoreRecords gestore;
	private RecordFrame recordFrame;
	
	public SudokuGame(){
		this.setLayout(new BorderLayout());
		create();
		disponi();
		setListener();
	}
	public void run(){
		this.setSize(406, 452);
		this.setLocation(550, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}
	private void create(){
		//gestore=new GestoreRecords();
		menuPanel=new MenuPanel(400,400);
		infoPanel=new InfoPanel(400,400);
		//recordFrame=new RecordFrame(gestore);
		mp=true;
		gp=false;
		sp=false;
		ip=false;
		s1=new JSeparator();
		s2=new JSeparator();
		menu=new JMenuBar();
		file=new JMenu("File");
		solver=new JMenu("Solver");
		normale=new JMenu("Sudoku");
		xsudoku=new JMenu("XSudoku");
		aTempo=new JMenu("A Tempo");
		nTempo=new JMenu("Sudoku");
		xTempo=new JMenu("XSudoku");
		help=new JMenu("?");
		nF=new JMenuItem("Facile");
		nM=new JMenuItem("Medio");
		nDif=new JMenuItem("Difficile");
		nDia=new JMenuItem("ESTREMO");
		xF=new JMenuItem("Facile");
		xM=new JMenuItem("Medio");
		xDif=new JMenuItem("Difficile");
		xDia=new JMenuItem("ESTREMO");
		aNF=new JMenuItem("Facile");
		aNM=new JMenuItem("Medio");
		aNDif=new JMenuItem("Difficile");
		aNDia=new JMenuItem("ESTREMO");
		aXF=new JMenuItem("Facile");
		aXM=new JMenuItem("Medio");
		aXDif=new JMenuItem("Difficile");
		aXDia=new JMenuItem("ESTREMO");
		record=new JMenuItem("Record");
		esci=new JMenuItem("Esci");
		nuovoSolver=new JMenuItem("Nuovo");
		about=new JMenuItem("About");
	}
	private void disponi(){
		normale.add(nF);
		normale.add(nM);
		normale.add(nDif);
		normale.add(nDia);
		xsudoku.add(xF);
		xsudoku.add(xM);
		xsudoku.add(xDif);
		xsudoku.add(xDia);
		aTempo.add(nTempo);
		aTempo.add(xTempo);
		nTempo.add(aNF);
		nTempo.add(aNM);
		nTempo.add(aNDif);
		nTempo.add(aNDia);
		xTempo.add(aXF);
		xTempo.add(aXM);
		xTempo.add(aXDif);
		xTempo.add(aXDia);
		file.add(normale);
		file.add(xsudoku);
		file.add(aTempo);
		file.add(s1);
		solver.add(nuovoSolver);
		file.add(solver);
		file.add(s2);
		file.add(esci);
		help.add(record);
		help.add(about);		
		menu.add(file);
		menu.add(help);
		this.add(menu,BorderLayout.NORTH);
		this.add(menuPanel,BorderLayout.CENTER);
	}
	private void setListener(){
		menuPanel.nF.addActionListener(new ButtonListener());
		menuPanel.nM.addActionListener(new ButtonListener());
		menuPanel.nDif.addActionListener(new ButtonListener());
		menuPanel.nDia.addActionListener(new ButtonListener());
		menuPanel.xF.addActionListener(new ButtonListener());
		menuPanel.xM.addActionListener(new ButtonListener());
		menuPanel.xDif.addActionListener(new ButtonListener());
		menuPanel.xDia.addActionListener(new ButtonListener());
		menuPanel.aNF.addActionListener(new ButtonListener());
		menuPanel.aNM.addActionListener(new ButtonListener());
		menuPanel.aNDif.addActionListener(new ButtonListener());
		menuPanel.aNDia.addActionListener(new ButtonListener());
		menuPanel.aXF.addActionListener(new ButtonListener());
		menuPanel.aXM.addActionListener(new ButtonListener());
		menuPanel.aXDif.addActionListener(new ButtonListener());
		menuPanel.aXDia.addActionListener(new ButtonListener());
		menuPanel.info.addActionListener(new ButtonListener());
		menuPanel.solver.addActionListener(new ButtonListener());
		infoPanel.indietro.addActionListener(new ButtonListener());
		nF.addActionListener(new MenuListener());
		nM.addActionListener(new MenuListener());
		nDif.addActionListener(new MenuListener());
		nDia.addActionListener(new MenuListener());
		xF.addActionListener(new MenuListener());
		xM.addActionListener(new MenuListener());
		xDif.addActionListener(new MenuListener());
		xDia.addActionListener(new MenuListener());
		aNF.addActionListener(new MenuListener());
		aNM.addActionListener(new MenuListener());
		aNDif.addActionListener(new MenuListener());
		aNDia.addActionListener(new MenuListener());
		aXF.addActionListener(new MenuListener());
		aXM.addActionListener(new MenuListener());
		aXDif.addActionListener(new MenuListener());
		aXDia.addActionListener(new MenuListener());
		esci.addActionListener(new MenuListener());
		record.addActionListener(new MenuListener());
		about.addActionListener(new MenuListener());
		nuovoSolver.addActionListener(new MenuListener());
		this.addWindowListener(new Close());
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==menuPanel.nF){
				mettiGP(Livello.FACILE,Tipo.SUDOKU,false);
			}
			if(e.getSource()==menuPanel.nM){
				mettiGP(Livello.MEDIO,Tipo.SUDOKU,false);
			}
			if(e.getSource()==menuPanel.nDif){
				mettiGP(Livello.DIFFICILE,Tipo.SUDOKU,false);
			}
			if(e.getSource()==menuPanel.nDia){
				mettiGP(Livello.ESTREMO, Tipo.SUDOKU, false);
			}
			if(e.getSource()==menuPanel.xF){
				mettiGP(Livello.FACILE,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==menuPanel.xM){
				mettiGP(Livello.MEDIO,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==menuPanel.xDif){
				mettiGP(Livello.DIFFICILE,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==menuPanel.xDia){
				mettiGP(Livello.ESTREMO,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==menuPanel.aNF){
				mettiGP(Livello.FACILE,Tipo.SUDOKU,true);
			}
			if(e.getSource()==menuPanel.aNM){
				mettiGP(Livello.MEDIO,Tipo.SUDOKU,true);
			}
			if(e.getSource()==menuPanel.aNDif){
				mettiGP(Livello.DIFFICILE,Tipo.SUDOKU,true);
			}
			if(e.getSource()==menuPanel.aNDia){
				mettiGP(Livello.ESTREMO,Tipo.SUDOKU,true);
			}
			if(e.getSource()==menuPanel.aXF){
				mettiGP(Livello.FACILE,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==menuPanel.aXM){
				mettiGP(Livello.MEDIO,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==menuPanel.aXDif){
				mettiGP(Livello.DIFFICILE,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==menuPanel.aXDia){
				mettiGP(Livello.ESTREMO,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==menuPanel.solver){
				mp=false;
				sp=true;
				solverPanel=new SolverPanel(400,400);
				remove(menuPanel);
				validate();
				add(solverPanel);
				solverPanel.requestFocus();
				validate();
			}
			if(e.getSource()==menuPanel.info){
				mp=false;
				ip=true;
				remove(menuPanel);
				validate();
				add(infoPanel);
				repaint();
				validate();
			}
			if(e.getSource()==infoPanel.indietro){
				ip=false;
				mp=true;
				remove(infoPanel);
				validate();
				add(menuPanel);
				validate();
				repaint();
			}
		}
		private void mettiGP(Livello livello,Tipo tipo,boolean aTempo){
			mp=false;
			gp=true;
			gamePanel=new GamePanel(livello,tipo,400,400,aTempo);//gestore);
			remove(menuPanel);
			validate();
			add(gamePanel);
			gamePanel.requestFocus();
			validate();
		}
	}
	private JFrame getThis(){return this;}
	private class MenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==nF){
				mettiGP(Livello.FACILE,Tipo.SUDOKU,false);
			}
			if(e.getSource()==nM){
				mettiGP(Livello.MEDIO,Tipo.SUDOKU,false);
			}
			if(e.getSource()==nDif){
				mettiGP(Livello.DIFFICILE,Tipo.SUDOKU,false);
			}
			if(e.getSource()==nDia){
				mettiGP(Livello.ESTREMO,Tipo.SUDOKU,false);
			}
			if(e.getSource()==xF){
				mettiGP(Livello.FACILE,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==xM){
				mettiGP(Livello.MEDIO,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==xDif){
				mettiGP(Livello.DIFFICILE,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==xDia){
				mettiGP(Livello.ESTREMO,Tipo.XSUDOKU,false);
			}
			if(e.getSource()==aNF){
				mettiGP(Livello.FACILE,Tipo.SUDOKU,true);
			}
			if(e.getSource()==aNM){
				mettiGP(Livello.MEDIO,Tipo.SUDOKU,true);
			}
			if(e.getSource()==aNDif){
				mettiGP(Livello.DIFFICILE,Tipo.SUDOKU,true);
			}
			if(e.getSource()==aNDia){
				mettiGP(Livello.ESTREMO,Tipo.SUDOKU,true);
			}
			if(e.getSource()==aXF){
				mettiGP(Livello.FACILE,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==aXM){
				mettiGP(Livello.MEDIO,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==aXDif){
				mettiGP(Livello.DIFFICILE,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==aXDia){
				mettiGP(Livello.ESTREMO,Tipo.XSUDOKU,true);
			}
			if(e.getSource()==nuovoSolver){
				if(mp){
					mp=false;
					sp=true;
					solverPanel=new SolverPanel(400,400);
					remove(menuPanel);
					validate();
					add(solverPanel);
					solverPanel.requestFocus();
					validate();
				}
				if(sp){
					remove(solverPanel);
					solverPanel=new SolverPanel(400,400);
					validate();
					add(solverPanel);
					solverPanel.requestFocus();
					validate();
				}
				if(ip){
					ip=false;
					sp=true;
					solverPanel=new SolverPanel(400,400);
					remove(infoPanel);
					validate();
					add(solverPanel);
					solverPanel.requestFocus();
					validate();
				}
				if(gp){
					gp=false;
					sp=true;
					remove(gamePanel);
					solverPanel=new SolverPanel(400,400);
					validate();
					add(solverPanel);
					solverPanel.requestFocus();
					validate();
				}
			}
			if(e.getSource()==esci){
				int option=JOptionPane.showConfirmDialog(getThis(), "Vuoi davvero uscire?","Esci?", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION)
					System.exit(0);
			}
			if(e.getSource()==record){
				//TODO
				JOptionPane.showMessageDialog(getThis(), "Da implemetare");
				//recordFrame.setVisible(true);
			}
			if(e.getSource()==about){
				JOptionPane.showMessageDialog(getThis(), "Autore:\n Longo Emanuele");
			}
		}
		private void mettiGP(Livello livello,Tipo tipo,boolean aTempo){
			if(mp){
				mp=false;
				gp=true;
				gamePanel=new GamePanel(livello,tipo,400,400,aTempo);//gestore);
				remove(menuPanel);
				validate();
				add(gamePanel);
				gamePanel.requestFocus();
				validate();
			}
			if(sp){
				sp=false;
				gp=true;
				gamePanel=new GamePanel(livello,tipo,400,400,aTempo);//gestore);
				remove(solverPanel);
				validate();
				add(gamePanel);
				gamePanel.requestFocus();
				validate();
			}
			if(ip){
				ip=false;
				gp=true;
				gamePanel=new GamePanel(livello,tipo,400,400,aTempo);//gestore);
				remove(infoPanel);
				validate();
				add(gamePanel);
				gamePanel.requestFocus();
				validate();
			}
			if(gp){
				remove(gamePanel);
				gamePanel=new GamePanel(livello,tipo,400,400,aTempo);//gestore);
				validate();
				add(gamePanel);
				gamePanel.requestFocus();
				validate();
			}
		}
	}
	private class Close extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			int option=JOptionPane.showConfirmDialog(getThis(), "Vuoi davvero uscire?","Esci?", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}
}