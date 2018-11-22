package tela;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import interfaces.IControles;
import motor.Controles;;




public class TelaInicio extends JFrame implements IControles {
	JButton btnGato1;
	JButton btnGato2;
	JButton btnGato3;
	JButton btnGato4;
	ImageIcon cat1;
	ImageIcon cat2;
	ImageIcon cat3;
	ImageIcon cat4;
	JLabel lcat1;
	JLabel lcat2;
	JLabel lcat3;
	JLabel lcat4;
	JPanel painel1;
	public TelaInicio() {
		painel1 = new JPanel(null);
		btnGato1 = new JButton("Escolher");
		btnGato1.setBounds(100, 200, 100	, 50);
		btnGato2 = new JButton("Escolher");
		btnGato2.setBounds(250, 200, 100	, 50);
		btnGato3 = new JButton("Escolher");
		btnGato3.setBounds(400, 200, 100, 50);
		btnGato4 = new JButton("Escolher");
		btnGato4.setBounds(550, 200, 100	, 50);
		
		cat1 = new ImageIcon(getClass().getResource("imagens/gato1.png"));
		cat2 = new ImageIcon(getClass().getResource("imagens/gato2.png"));
		cat3 = new ImageIcon(getClass().getResource("imagens/gato3.png"));
		cat4 = new ImageIcon(getClass().getResource("imagens/gato4.png"));
		
		
		
		
		lcat1 = new JLabel();
		lcat1.setBounds(100, 50, 100, 100);
		
		lcat2 = new JLabel();
		lcat2.setBounds(250, 50, 100, 100);
		
		lcat3 = new JLabel();
		lcat3.setBounds(400, 50, 100, 100);
		
		lcat4 = new JLabel();
		lcat4.setBounds(550, 50, 100, 100);
		
		if(cat1!=null && cat2 != null && cat3 != null && cat4 != null) {
			lcat1.setIcon(cat1);
			lcat2.setIcon(cat2);
			lcat3.setIcon(cat3);
			lcat4.setIcon(cat4);
		}else {
			System.out.println("imagen do gato nula");
		}

		Controles c = new Controles();
		add(painel1);
		painel1.add(btnGato1);
		painel1.add(btnGato2);
		painel1.add(btnGato3);
		painel1.add(btnGato4);
		painel1.add(lcat1);
		painel1.add(lcat2);
		painel1.add(lcat3);
		painel1.add(lcat4);
		painel1.addKeyListener(c.obterTeclaPressionada());
	}
	
	
	public static void main(String[] args) {
		TelaInicio  ti = new TelaInicio();
		ti.setResizable(false);
		ti.setSize(800, 600);
		ti.setLocationRelativeTo(null);
		ti.setVisible(true);
		
	}
	
}


