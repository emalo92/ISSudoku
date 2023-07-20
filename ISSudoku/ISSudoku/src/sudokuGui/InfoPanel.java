package sudokuGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sudoku.ProblemaFactory;
import sudoku.Sudoku;

public class InfoPanel extends JPanel{
	private static final long serialVersionUID = -2960107714299442166L;
	JButton indietro;
	private ImageIcon background;
	public InfoPanel(int width, int height){
		Dimension d=new Dimension(width,height);
		this.setPreferredSize(d);
		background = new ImageIcon(this.getClass().getResource("/immagini/info.jpg"));
		indietro=new JButton("Indietro");
		createAndDispose();
	}
	private void createAndDispose(){
		LinkedList<JLabel> ll=new LinkedList<JLabel>();
		GridBagLayout layout=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		this.setLayout(layout);
		ll.add(new JLabel("Questo è un generatore risolutore di sudoku."));
		ll.add(new JLabel("Caratteristiche di questo software:"));
		ll.add(new JLabel("1)Genera sudoku normali"));
		ll.add(new JLabel("1.1)Regole:"));
		ll.add(new JLabel("		Inserisci numeri da 1 a 9 per riempire la griglia"));
		ll.add(new JLabel("		Non inserire lo stesso numero nella stessa riga"));
		ll.add(new JLabel("		Non inserire lo stesso numero nella stessa colonna"));
		ll.add(new JLabel("		Non inserire lo stesso numero nella stessa quadrato"));
		ll.add(new JLabel("		Si gioca con freccie e numeri"));
		ll.add(new JLabel("2)Genera sudoku \"X\""));
		ll.add(new JLabel("2.1)Regole:"));
		ll.add(new JLabel("		Stesse regole del sudoku normale"));
		ll.add(new JLabel("		Non inserire lo stesso numero nelle diagonali principali"));
		ll.add(new JLabel("3)Risolvi schemi sudoku normale"));
		ll.add(new JLabel("		Inserisci lo schema di un sudoku normale e genera"));
		ll.add(new JLabel("		la soluzione automaticamente"));
		int y=0;
		gbc.anchor=GridBagConstraints.WEST;
		for(JLabel l:ll){
			l.setFont(new java.awt.Font("Times New Roman", 3, 16));
			gbc.gridy=y;
			layout.setConstraints(l, gbc);
			y++;
			this.add(l);
		}
		gbc.gridy=y;
		gbc.anchor=GridBagConstraints.CENTER;
		layout.setConstraints(indietro, gbc);
		this.add(indietro);
	}
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.drawImage(background.getImage(),0,0,400,400,null,this);
	}
}
