package tela;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import MultPlayer.Cliente;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AmbienteJogo extends JFrame{
	public ArrayList<Cliente> clientes = new ArrayList<>();
	

	public AmbienteJogo(Cliente player) {
		this.clientes.add(player);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AmbienteJogo.class.getResource("/resources/gato1.png")));
		setForeground(SystemColor.windowBorder);
		getContentPane().setBackground(SystemColor.menu);
		setSize(624,663);
		setResizable(false);//N�o deixar redirecionar
		setLocationRelativeTo(null);//Qaundo abrir a janela, vai estar no centro
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setVerticalAlignment(SwingConstants.TOP);
		//lblImagem.setIcon(new ImageIcon(AmbienteJogo.class.getResource("/resources/Corrida.jpeg")));
		
		ImageIcon ii;
		ii =  new ImageIcon(getClass().getResource("/resources/Corrida.jpeg"));
		ii.setImage(ii.getImage().getScaledInstance(900,600	, 800));
		lblImagem.setIcon(ii);
		
		
		getContentPane().add(lblImagem, BorderLayout.NORTH);
		
		JButton btnSair = new JButton("Sair");
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//setAlwaysOnTop(true);//deixar a tela de confirma��o de sair sempre no topo
				setLocationRelativeTo(null);//Quando abrir a janela, vai estar no centro
				if((JOptionPane.showConfirmDialog(null, "Realmente Desesa Sair do Jogo?", "Sair", JOptionPane.YES_NO_OPTION)) 
						== 
						(JOptionPane.YES_OPTION)){
					
					System.exit(0);
				}
				
			}
		}
		);
		
		
		getContentPane().add(btnSair, BorderLayout.SOUTH);
		setBackground(SystemColor.controlHighlight);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Tela do Jogo");
		
		
	}



	public static void main(String arg[]) {
		AmbienteJogo mm = new AmbienteJogo(null);
		mm.setSize(900,600);
		mm.setResizable(false);//N�o deixar redirecionar
		mm.setLocationRelativeTo(null);//Qaundo abrir a janela, vai estar no centro
		mm.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mm.setSize(900,600);
		mm.setVisible(true);		
		
	}
}
