package tela;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AmbienteJogo extends JFrame{
	JPanel fundo;
	ImageIcon bandeirinhas;
	JLabel Fundo;
	
	
	
	
	
	public AmbienteJogo() {
		setTitle("Ambiente do Jogo");
		setVisible(true);
		setResizable(true);
		setSize(800,600);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		bandeirinhas = new ImageIcon(getClass().getResource("/resources/Corrida.jpg"));
		
		
		Fundo = new JLabel();
		Fundo.setBounds(0, 0, 50, 50);
		Fundo.setIcon(bandeirinhas);
		
		
		
		
		
		
		add(Fundo);
		
		
	}



	public static void main(String arg[]) {
		AmbienteJogo mm = new AmbienteJogo();
		//mm.setSize(800,600);
		mm.setVisible(true);
		
	}


}