package sudokuGui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CellPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public enum Selezione{SELEZIONATO,NON_SELEZIONATO,ERRORE,DIAGONALE}
	public enum Tipo{FISSO,VARIABILE}
	private Selezione selezione;
	private JLabel l;
	public CellPanel(){
		selezione=Selezione.NON_SELEZIONATO;
		this.setBackground(Color.WHITE);
		l=new JLabel();
		this.add(l);
	}
	public CellPanel(Selezione selezione){
		this.selezione=selezione;
		if(selezione==Selezione.NON_SELEZIONATO)
			this.setBackground(Color.WHITE);
		if(selezione==Selezione.DIAGONALE)
			this.setBackground(Color.GREEN);
		if(selezione==Selezione.SELEZIONATO)
			this.setBackground(Color.LIGHT_GRAY);
		if(selezione==Selezione.ERRORE)
			this.setBackground(Color.RED);
		l=new JLabel();
		this.add(l);
	}
	public CellPanel(Selezione selezione, String s,Tipo tipo){
		this.selezione=selezione;
		if(selezione==Selezione.NON_SELEZIONATO)
			this.setBackground(Color.WHITE);
		if(selezione==Selezione.DIAGONALE)
			this.setBackground(Color.GREEN);
		if(selezione==Selezione.SELEZIONATO)
			this.setBackground(Color.LIGHT_GRAY);
		if(selezione==Selezione.ERRORE)
			this.setBackground(Color.ORANGE);
		l=new JLabel(s);
		if(tipo==Tipo.FISSO){
			l.setFont(new java.awt.Font("Times New Roman", 3, 20));
			l.setForeground(Color.BLACK);
		}
		else
			l.setFont(new java.awt.Font("Times New Roman", 0, 20));
		this.add(l);
	}
	public Selezione getBG(){return selezione;}
	public void setString(String s,Tipo tipo){
		this.remove(l);
		this.validate();
		l=new JLabel(s);
		if(tipo==Tipo.FISSO){
			l.setFont(new java.awt.Font("Times New Roman", 3, 20));
			l.setForeground(Color.BLACK);
		}
		else
			l.setFont(new java.awt.Font("Times New Roman", 0, 20));
		this.add(l);
		this.validate();
	}
	public void setBG(Selezione selezione){
		this.selezione=selezione;
		if(selezione==Selezione.NON_SELEZIONATO)
			this.setBackground(Color.WHITE);
		if(selezione==Selezione.DIAGONALE)
			this.setBackground(Color.GREEN);
		if(selezione==Selezione.SELEZIONATO)
			this.setBackground(Color.LIGHT_GRAY);
		if(selezione==Selezione.ERRORE)
			this.setBackground(Color.ORANGE);
		this.repaint();
	}
}
