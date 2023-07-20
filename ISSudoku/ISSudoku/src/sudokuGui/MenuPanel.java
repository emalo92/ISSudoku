package sudokuGui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = -3662936029551412568L;
	private ImageIcon background;
	private int width, height;
	private Dimension d;
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	private JButton n,x,aTempo,nTempo,xTempo;
	JButton nF,nM,nDif,nDia,xF,xM,xDif,xDia,aNF,aNM,aNDif,aNDia,aXF,aXM,
	aXDif,aXDia,solver,info;
	public MenuPanel(int width, int height){
		this.width=width;
		this.height=height;
		background=new ImageIcon(this.getClass().getResource("/Immagini/Sudoku.png"));
		n=new JButton("Sudoku");
		nF=new JButton("Facile");
		nM=new JButton("Medio");
		nDif=new JButton("Difficile");
		nDia=new JButton("ESTREMO");
		x=new JButton("XSudoku");
		xF=new JButton("Facile");
		xM=new JButton("Medio");
		xDif=new JButton("Difficile");
		xDia=new JButton("ESTREMO");
		aTempo=new JButton("Sudoku a tempo");
		nTempo=new JButton("Sudoku");
		xTempo=new JButton("XSudoku");
		aNF=new JButton("Facile");
		aNM=new JButton("Medio");
		aNDif=new JButton("Difficile");
		aNDia=new JButton("ESTREMO");
		aXF=new JButton("Facile");
		aXM=new JButton("Medio");
		aXDif=new JButton("Difficile");
		aXDia=new JButton("ESTREMO");
		solver=new JButton("Solver");
		info=new JButton("Info");
		createMenuPanel();
		setListener();
	}
	private void createMenuPanel(){
		layout=new GridBagLayout();
		gbc=new GridBagConstraints();
		this.setLayout(layout);
		d=new Dimension(200,30);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.insets=new Insets(100,0,0,0);
		n.setPreferredSize(d);
		layout.setConstraints(n, gbc);
		this.add(n);
		gbc.insets=new Insets(0,0,0,0);
		x.setPreferredSize(d);
		gbc.gridy=1;
		layout.setConstraints(x, gbc);
		this.add(x);
		aTempo.setPreferredSize(d);
		gbc.gridy=2;
		layout.setConstraints(aTempo, gbc);
		this.add(aTempo);
		solver.setPreferredSize(d);
		gbc.gridy=3;
		layout.setConstraints(solver, gbc);
		this.add(solver);
		info.setPreferredSize(d);
		gbc.gridy=4;
		layout.setConstraints(info, gbc);
		this.add(info);
	}
	private void setListener(){
		 n.addActionListener(new Listener());
		 x.addActionListener(new Listener());
		 aTempo.addActionListener(new Listener());
		 nTempo.addActionListener(new Listener());
		 xTempo.addActionListener(new Listener());
	}
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==n){
				removeAll();
				validate();
				gbc.gridy=0;
				gbc.insets=new Insets(100,0,0,0);
				nF.setPreferredSize(d);
				layout.setConstraints(nF, gbc);
				add(nF);
				gbc.insets=new Insets(0,0,0,0);
				gbc.gridy=1;
				nM.setPreferredSize(d);
				layout.setConstraints(nM, gbc);
				add(nM);
				gbc.gridy=2;
				nDif.setPreferredSize(d);
				layout.setConstraints(nDif, gbc);
				add(nDif);
				gbc.gridy=4;
				nDia.setPreferredSize(d);
				layout.setConstraints(nDia, gbc);
				add(nDia);
				validate();
				repaint();
			}
			if(e.getSource()==x){
				removeAll();
				validate();
				gbc.gridy=0;
				gbc.insets=new Insets(100,0,0,0);
				xF.setPreferredSize(d);
				layout.setConstraints(xF, gbc);
				add(xF);
				gbc.insets=new Insets(0,0,0,0);
				gbc.gridy=1;
				xM.setPreferredSize(d);
				layout.setConstraints(xM, gbc);
				add(xM);
				gbc.gridy=2;
				xDif.setPreferredSize(d);
				layout.setConstraints(xDif, gbc);
				add(xDif);
				gbc.gridy=4;
				xDia.setPreferredSize(d);
				layout.setConstraints(xDia, gbc);
				add(xDia);
				validate();
				repaint();
			}
			if(e.getSource()==aTempo){
				removeAll();
				validate();
				gbc.gridy=0;
				gbc.insets=new Insets(100,0,0,0);
				nTempo.setPreferredSize(d);
				layout.setConstraints(nTempo, gbc);
				add(nTempo);
				gbc.insets=new Insets(0,0,0,0);
				gbc.gridy=1;
				xTempo.setPreferredSize(d);
				layout.setConstraints(xTempo, gbc);
				add(xTempo);
				validate();
				repaint();
			}
			if(e.getSource()==nTempo){
				removeAll();
				validate();
				gbc.gridy=0;
				gbc.insets=new Insets(100,0,0,0);
				aNF.setPreferredSize(d);
				layout.setConstraints(aNF, gbc);
				add(aNF);
				gbc.insets=new Insets(0,0,0,0);
				gbc.gridy=1;
				aNM.setPreferredSize(d);
				layout.setConstraints(aNM, gbc);
				add(aNM);
				gbc.gridy=2;
				aNDif.setPreferredSize(d);
				layout.setConstraints(aNDif, gbc);
				add(aNDif);
				gbc.gridy=4;
				aNDia.setPreferredSize(d);
				layout.setConstraints(aNDia, gbc);
				add(aNDia);
				validate();
				repaint();
			}
			if(e.getSource()==xTempo){
				removeAll();
				validate();
				gbc.gridy=0;
				gbc.insets=new Insets(100,0,0,0);
				aXF.setPreferredSize(d);
				layout.setConstraints(aXF, gbc);
				add(aXF);
				gbc.insets=new Insets(0,0,0,0);
				gbc.gridy=1;
				aXM.setPreferredSize(d);
				layout.setConstraints(aXM, gbc);
				add(aXM);
				gbc.gridy=2;
				aXDif.setPreferredSize(d);
				layout.setConstraints(aXDif, gbc);
				add(aXDif);
				gbc.gridy=4;
				aXDia.setPreferredSize(d);
				layout.setConstraints(aXDia, gbc);
				add(aXDia);
				validate();
				repaint();
			}
		}
	}
	public void paintComponent(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.drawImage(background.getImage(),0,0,width,height,null,this);
	}
}
