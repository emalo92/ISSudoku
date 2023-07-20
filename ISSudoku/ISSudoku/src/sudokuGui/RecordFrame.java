package sudokuGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import save.GestoreRecords;
import util.Record;

public class RecordFrame extends JFrame {
	private static final long serialVersionUID = 1410484025034718088L;
	private JList listaModalita;
	public static final String sF="Sudoku:Facile";
	public static final String sM="Sudoku:Medio";
	public static final String sD="Sudoku:Difficile";
	public static final String sDi="Sudoku:Diabolico";
	public static final String xF="XSudoku:Facile";
	public static final String xM="XSudoku:Medio";
	public static final String xD="XSudoku:Difficile";
	public static final String xDi="XSudoku:Diabolico";
	public static final String mod="Scegli modalità";
	public static final String riga="\n";
	private String[] modalita;
	private JPanel p;
	private JScrollPane jsp;
	private JTextPane jtp;
	private GestoreRecords gestore;
	public RecordFrame(GestoreRecords gestore){
		this.gestore=gestore;
		Dimension d=new Dimension(150,200);
		p=new JPanel();
		GridBagLayout gb=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		p.setLayout(gb);
		p.setBackground(Color.CYAN);
		jtp=new JTextPane();
		jsp=new JScrollPane();
		modalita=new String[11];
		modalita[0]=mod;
		modalita[1]=riga;
		modalita[2]=sF;
		modalita[3]=sM;
		modalita[4]=sD;
		modalita[5]=sDi;
		modalita[6]=riga;
		modalita[7]=xF;
		modalita[8]=xM;
		modalita[9]=xD;
		modalita[10]=xDi;
		listaModalita=new JList(modalita);
		// listaModalita.addListSelectionListener(new ListListener()); TODO
		jsp.setViewportView(listaModalita);
		jsp.setPreferredSize(d);
		gbc.gridx=0;
		gb.setConstraints(jsp, gbc);
		p.add(jsp);
		d=new Dimension(230,160);
		jtp.setPreferredSize(d);
		jtp.setBackground(Color.CYAN);
		jtp.setEditable(false);
		jtp.setFont(new java.awt.Font("Times New Roman", 3, 16));
		gbc.gridx=1;
		gb.setConstraints(jtp, gbc);
		p.add(jtp);
		add(p);
		this.setSize(400,300);
		this.setLocation(450, 300);
		this.setResizable(false);
		this.addWindowListener(new Close());
	}
	private JFrame getThis(){return this;}
	private class Close extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			getThis().setVisible(false);
		}
	}
	/* private class ListListener implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {
			if(listaModalita.getSelectedValue().equals(sF)){
				jtp.setText(gestore.getRecordToString(Record.Livello.FACILE, Record.Tipo.SUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(sM)){
				jtp.setText(gestore.getRecordToString(Record.Livello.MEDIO, Record.Tipo.SUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(sD)){
				jtp.setText(gestore.getRecordToString(Record.Livello.DIFFICILE, Record.Tipo.SUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(sDi)){
				jtp.setText(gestore.getRecordToString(Record.Livello.ESTREMO, Record.Tipo.SUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(xF)){
				jtp.setText(gestore.getRecordToString(Record.Livello.FACILE, Record.Tipo.XSUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(xM)){
				jtp.setText(gestore.getRecordToString(Record.Livello.MEDIO, Record.Tipo.XSUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(xD)){
				jtp.setText(gestore.getRecordToString(Record.Livello.DIFFICILE, Record.Tipo.XSUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(xDi)){
				jtp.setText(gestore.getRecordToString(Record.Livello.ESTREMO, Record.Tipo.XSUDOKU));
			}
			if(listaModalita.getSelectedValue().equals(riga)){
				jtp.setText("");
			}
			if(listaModalita.getSelectedValue().equals(mod)){
				jtp.setText("");
			}
		}
	}
	*/
}
