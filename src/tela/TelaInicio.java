package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import MultPlayer.Cliente;
import MultPlayer.Servidor;
import interfaces.IControles;
import tela.AmbienteJogo;
import teste.Teste;


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
	
	private Cliente cl;
	private JLabel lcat4;
	private JPanel painel1;
	private JTextField ipServidor = new JTextField();
	private static ArrayList<String> btnUsados = new ArrayList<>();
	private Cliente player;
	
	AmbienteJogo aj;
	
	public TelaInicio() {
		
		setTitle("Lobby");
		painel1 = new JPanel(null);
		btnGato1 = new JButton("Gato 1");
		btnGato1.setBounds(100, 200, 100, 50);
		btnGato2 = new JButton("Gato 2");
		btnGato2.setBounds(250, 200, 100, 50);
		btnGato3 = new JButton("Gato 3");
		btnGato3.setBounds(400, 200, 100, 50);
		btnGato4 = new JButton("Gato 4");
		btnGato4.setBounds(550, 200, 100, 50);
		btnGato5 = new JButton("Servidor");
		btnGato5.setBounds(100, 300, 100, 50);
		btnGato6 = new JButton("Cliente");
		btnGato6.setBounds(250, 300, 100, 50);
		btnReady = new JButton("Start");
		btnReady.setBounds(300, 300, 100, 50);
		

		cat1 = new ImageIcon(getClass().getResource("/resources/gato1.png"));
		cat1.setImage(cat1.getImage().getScaledInstance(100, 100, 100));
		cat2 = new ImageIcon(getClass().getResource("/resources/gato2.png"));
		cat2.setImage(cat2.getImage().getScaledInstance(100, 100, 100));
		cat3 = new ImageIcon(getClass().getResource("/resources/gato3.png"));
		cat3.setImage(cat3.getImage().getScaledInstance(100, 100, 100));
		cat4 = new ImageIcon(getClass().getResource("/resources/gato4.png"));
		cat4.setImage(cat4.getImage().getScaledInstance(100, 100, 100));

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
		btnGato5.setEnabled(false);
		//Botão de escolha do primeiro gato 
		btnGato1.addActionListener(new acaoBtn1());
		btnGato2.addActionListener(new acaoBtn2());
		btnGato3.addActionListener(new acaoBtn3());
		btnGato4.addActionListener(new acaoBtn4());
		
	
	}
	
	public void teste(String str) {
		String[]  a= {"ola","mundo"};
	
		System.out.println("<<"+str+">>");
		if(str.equals("START") && aj == null ) {
			 aj =  new AmbienteJogo(player);
			 aj.main(a);
			 
		}
		personagenDisponivel(str);
	}
	public void personagenDisponivel(String string){
			if(btnGato1.getText().equals(string))
				btnGato1.setEnabled(false);
			if(btnGato2.getText().equals(string))
				btnGato2.setEnabled(false);
			if(btnGato3.getText().equals(string))
				btnGato3.setEnabled(false);
			if(btnGato4.getText().equals(string))
				btnGato4.setEnabled(false);
	}
	
	
	
	public static void main(String[] args) {
		TelaInicio  ti = new TelaInicio();
		ti.setResizable(false);
		ti.setSize(800, 600);
		ti.setLocationRelativeTo(null);
		ti.setVisible(true);
		ti.setFocusable(true);
		ti.setFocusTraversalKeysEnabled(false);
		ti.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public class acaoBtn1 implements ActionListener{
		String[] ola = null;
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				cl.enviarMensagem(btnGato1.getText());
				btnUsados.add(btnGato1.getText());
				Teste t = new Teste();
				t.setAtualClient(cl);
				t.main(ola);
				System.out.println(cl.getMYID());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public class acaoBtn2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				cl.enviarMensagem(btnGato2.getText());
				//btnUsados.add(btnGato1.getText());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public class acaoBtn3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				cl.enviarMensagem(btnGato3.getText());
				//btnUsados.add(btnGato1.getText());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public class acaoBtn4 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				cl.enviarMensagem(btnGato4.getText());
				//btnUsados.add(btnGato1.getText());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public class acaoServidor implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Servidor srv = null;
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
	public class acaoConectar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				cl = new Cliente(ipServidor.getText(), 12345,TelaInicio.this);
				cl.start();
				
			} catch (UnknownHostException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			if(!cl.isInterrupted()) {
				player = cl;
				JOptionPane.showMessageDialog(null, "Conectado", "Informação",JOptionPane.INFORMATION_MESSAGE);
				ipServidor.setEditable(false);
				ipServidor.setEnabled(false);
				
							
			}else {
				JOptionPane.showMessageDialog(null, "Não Conectado \n Verifique o ip "
						+ "\n Verifique se o servidor esta ativo", "Informação",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public class acaoCliente implements ActionListener,IControles{
		@Override
		public void actionPerformed(ActionEvent e) {
			JLabel texto = new JLabel("Digite o IP e pressione Enter ou OK");
			painel1.remove(btnGato5);
			
			ipServidor.setBounds(100, 325, 200, 25);
			ipServidor.addKeyListener(this);
			btnGato6.setBounds(400, 300, 125, 50);
			btnGato6.setText("CONECTAR");
			
			texto.setBounds(100, 300, 300, 25);
			ipServidor.setText(Servidor.getIp());
			painel1.add(ipServidor);
			painel1.add(texto);
			painel1.repaint();
			btnGato6.addActionListener(new acaoConectar());
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_ENTER) {
				try {
					cl = new Cliente(ipServidor.getText(), 12345,TelaInicio.this);
					cl.start();
					
				} catch (UnknownHostException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				if(!cl.isInterrupted()) {
					player = cl;
					JOptionPane.showMessageDialog(null, "Conectado", "Informação",JOptionPane.INFORMATION_MESSAGE);
					ipServidor.setEditable(false);
					ipServidor.setEnabled(false);
					
								
				}else {
					JOptionPane.showMessageDialog(null, "Não Conectado \n Verifique o ip "
							+ "\n Verifique se o servidor esta ativo", "Informação",JOptionPane.ERROR_MESSAGE);
				}
					
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	
	}
}
	
