package tela;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AmbienteJogo extends JFrame{
	JPanel painel2;
	JLabel Fundo;
	Icon fundo;
	
	
	public void test(){
		painel2 = new JPanel(null);
		fundo = new ImageIcon(getClass().getResource("/resources/Menu.jpg"));
		Fundo = new JLabel();
		Fundo.setIcon(fundo);
				
	     add(painel2);
	     add(Fundo);
	    
	}
	
	
	public static void main(String[] args) {
		AmbienteJogo  mm = new AmbienteJogo();
		mm.setResizable(false);
		mm.setSize(800, 600);
		mm.setLocationRelativeTo(null);
		mm.setVisible(true);
		mm.setFocusable(true);
		mm.setFocusTraversalKeysEnabled(false);
		
	}
}
