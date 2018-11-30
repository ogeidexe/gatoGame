package tela;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MultPlayer.Cliente;
import MultPlayer.Servidor;
import interfaces.IControles;
//implements IControles
public class TelaInicio extends JFrame  {
	JButton btnGato1;
	JButton btnGato2;
	JButton btnGato3;
	JButton btnGato4;
	JButton btnGato5;
	JButton btnGato6;
	JButton btnReady;
	JButton btnGato8;
	ImageIcon cat1;
	ImageIcon cat2;
	ImageIcon cat3;
	ImageIcon cat4;
	JLabel lcat1;
	JLabel lcat2;
	JLabel lcat3;
	JLabel lcat4;
	JPanel painel1;
	JTextField ipServidor = new JTextField();

	public TelaInicio() {
		painel1 = new JPanel(null);
		
		btnGato1 = new JButton("Escolher");
		btnGato1.setBounds(100, 200, 100, 50);
		btnGato2 = new JButton("Escolher");
		btnGato2.setBounds(250, 200, 100, 50);
		btnGato3 = new JButton("Escolher");
		btnGato3.setBounds(400, 200, 100, 50);
		btnGato4 = new JButton("Escolher");
		btnGato4.setBounds(550, 200, 100, 50);
		btnGato5 = new JButton("Servidor");
		btnGato5.setBounds(100, 300, 100, 50);
		btnGato6 = new JButton("Cliente");
		btnGato6.setBounds(250, 300, 100, 50);
		btnReady = new JButton("Start");
		btnReady.setBounds(300, 300, 100, 50);
		

		cat1 = new ImageIcon(getClass().getResource("/resources/gato1.png"));		
		cat2 = new ImageIcon(getClass().getResource("/resources/gato2.png"));
		cat3 = new ImageIcon(getClass().getResource("/resources/gato3.png"));
		cat4 = new ImageIcon(getClass().getResource("/resources/gato4.png"));

		lcat1 = new JLabel();
		lcat1.setBounds(100, 50, 100, 100);

		lcat2 = new JLabel();
		lcat2.setBounds(250, 50, 100, 100);

		lcat3 = new JLabel();
		lcat3.setBounds(400, 50, 100, 100);

		lcat4 = new JLabel();
		lcat4.setBounds(550, 50, 100, 100);

		if (cat1 != null && cat2 != null && cat3 != null && cat4 != null) {
			lcat1.setIcon(cat1);
			lcat2.setIcon(cat2);
			lcat3.setIcon(cat3);
			lcat4.setIcon(cat4);
		} else {
			System.out.println("imagen do gato nula");
		}
     	add(painel1);
		painel1.add(btnGato1);
		painel1.add(btnGato2);
		painel1.add(btnGato3);
		painel1.add(btnGato4);
		painel1.add(btnGato5);
		painel1.add(btnGato6);
		painel1.add(lcat1);
		painel1.add(lcat2);
		painel1.add(lcat3);
		painel1.add(lcat4);
		//ESSE BOTÃO É DO Cliente
		btnGato6.addActionListener(new acaoCliente());
		//ESSE BOTÃO É DO Servidor
		btnGato5.addActionListener(new acaoServidor());
		
		
	
	}
	
	public static void main(String[] args) {
		TelaInicio  ti = new TelaInicio();
		ti.setResizable(false);
		ti.setSize(800, 600);
		ti.setLocationRelativeTo(null);
		ti.setVisible(true);
		ti.setFocusable(true);
		ti.setFocusTraversalKeysEnabled(false);
		
	}
	
	public class acaoServidor implements ActionListener{
	@Override
		public void actionPerformed(ActionEvent arg0) {
			ServerSocket serverSocket = null;
			try {
				serverSocket = new ServerSocket(12345);
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverSocket.getInetAddress();
			Servidor srv = null;
			try {
				srv = new Servidor(new Socket(serverSocket.getInetAddress(),serverSocket.getLocalPort()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			JLabel texto = new JLabel("Digite o IP nos clientes");
			painel1.remove(btnGato6);
			painel1.remove(btnGato5);
			ipServidor.setBounds(100, 325, 200, 25);
			
			btnGato6.setBounds(400, 300, 100, 50);
			ipServidor.setText(srv.getIp().toString());
			ipServidor.setEditable(false);
			texto.setBounds(100, 300, 300, 25);
			painel1.add(ipServidor);
			painel1.add(btnReady);
			painel1.add(texto);
			painel1.repaint();
			
		}
		
	
	}
	public class acaoCliente implements ActionListener,IControles{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JLabel texto = new JLabel("Digite o IP e pressione Enter");
			painel1.remove(btnGato5);
			
			ipServidor.setBounds(100, 325, 200, 25);
			ipServidor.addKeyListener(this);
			
			btnGato6.setBounds(400, 300, 100, 50);
			btnGato6.setText("OK");
			texto.setBounds(100, 300, 300, 25);
			painel1.add(ipServidor);
			painel1.add(texto);
			painel1.repaint();
			
			
		}
		int i= 0;
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println(e.getKeyChar());	
			if(e.getKeyCode()==e.VK_ENTER) {
				Cliente cl = new Cliente(ipServidor.getText(), 12345);
				System.out.println(ipServidor.getText());
				cl.start();
//				if(cl.getState()) {
//					System.out.println("esta conecção funfa é tem o id ="+ cl.getId());
//					ipServidor.setEditable(false);
//					JOptionPane.showMessageDialog(null, "Conectado a "+ipServidor.getText(), "STATUS", JOptionPane.INFORMATION_MESSAGE);
//				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==e.VK_ENTER) {
			}
		}
	
	}
}
	

