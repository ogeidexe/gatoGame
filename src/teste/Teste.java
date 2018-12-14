package teste;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import MultPlayer.Cliente;
//A CLASSE GAME HERDA AS FUNCIONALIDADES DE JFRAME
public class Teste extends JFrame implements KeyListener,ActionListener{

	BufferedImage backBuffer;	//ESSE É O NOSSO BUFFER
	int FPS = 30;				//ESSA É A TAXA DE ATUALIZAÇÃO
	int janelaW = 800;			//LARGURA DA TELA
	int janelaH = 600;			//ALTURA DA TELA
	ImageIcon fundo = new ImageIcon(getClass().getResource("/resources/Corrida.jpeg"));
	ImageIcon gato1 =  new ImageIcon(getClass().getResource("/resources/gato1.png"));
	Integer g1,g2,g3,g4;
	Cliente cliente;
	
	//NO NOSSO METODO ATUALIZAR VAMOS CHAMAR OS METODOS
	//QUE SERÃO EXECUTADOS O TEMPO INTEIRO...
	public void setMsgatual(String msg){
		
	}
	public void atualizar()  {
		//if(Integer.parseInt(cliente.getmsgAtual())== KeyEvent.VK_ENTER)
			
			 
	}
	public void setAtualClient(Cliente player) {
		this.cliente = player;
		System.out.println("cliente Instanciado ID:"+cliente.getMYID());
	}
	//NESSE MÉTODO VAMOS IREMOS DESENHAR
	//FORMAS GEOMETRICAS, IMAGENS E TEXTOS NA TELA E ETC...
	public void desenharGraficos() {
		Graphics g = getGraphics();//COM g IREMOS DESENHAR O TUDO QUE ESTÁ NO BUFFER NA TELA
		Graphics bbg = backBuffer.getGraphics();//COM bbg IREMOS DESENHAR NO NOSSO BUFFER
		fundo.setImage(fundo.getImage().getScaledInstance(800, 600, 100));
		bbg.drawImage(fundo.getImage(),0,0, this);
		bbg.drawImage(gato1.getImage(), g1, 100, this);
		
		
		//bbg.drawImage(gato11.cenas[gato11.cena].getImage(), gato11.altura, gato11.largura, this);
		
		//gato11.animarMaisLento();
		//AQUI ESTAMOS DESENHANDO O BUFFER NA TELA,
		//NAS COORDENADAS X:0 e Y:0
		g.drawImage(backBuffer, 0, 0, this);
	}
	//ESSE É O NOSSO MÉTODO INICIALIZAR
	//AQUI VAMOS INICIALIZAR ALGUMAS CONFIGURAÇÃO DO frame E OUTRAS CONFIGURAÇÕES
	public void inicializar() {
		setTitle("Titulo do Jogo!");//SETANDO O TITULO DA JANELA
		setSize(janelaW, janelaH);//DEFINIDO AS DIMENSÕES DA JANELA
		setResizable(false);//TIRANDO A PERMISSÃO DO USUÁRIO REDIMENSIONAR A JANELA
		setDefaultCloseOperation(EXIT_ON_CLOSE);//QUANDO FECHARMOS O frame A APLICAÇÃO PARA DE EXECUTAR
		setLayout(null);//COM ISSO PODEREMOS DEFINIAR COORDENADA E DIMESÕES DE ELMENTOS DE FORMULARIO NA TELA
		setVisible(true);//MUDANDO A VISIBILIDADE DO frame PARA TRUE, ASSIM ELE APARECERÁ
		setFocusable(true);
		Teste.this.getContentPane().setFocusable(true);
		Teste.this.getContentPane().setFocusTraversalKeysEnabled(true);
		this.addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);//INICIADO O NOSSO BUFFER DE IMAGEM
		fundo.setImage(fundo.getImage().getScaledInstance(800, 600, 100));
		gato1.setImage(gato1.getImage().getScaledInstance(100, 100, 10));
		g1 = 10;
		g2 = 10;
		g3 = 10;
		g4 = 10;
		//gato11.cenas[0] = new ImageIcon(getClass().getResource("resources/gato1.png"));
		//sprite gato1 = new sprite(1, x, y)
	}
	//AQUI É O NOSSO MÉTODO RUN()
	//NELE TEMOS O NOSSO GAME LOOP (UM LOOP INFINITO)
	public void run() {
		inicializar();//AQUI CHAMAMOS O METODO INICIALIZAR SOMENTE UMA VEZ, POIS ELE ESTÁ FORA DO NOSSO LOOP
		while (true) {//AQUI É O NOSSO LOOP INFINITO
			atualizar();//CHAMAMOS O METODO ATUALIZAR O TEMPO INTEIRO
			desenharGraficos();//ATUALIZAREMOS O GRÁFICO QUE APARECE NA TELA O TEMPO INTEIRO
				try {
					Thread.sleep(1000/FPS); //PEQUENA PAUSA
				} catch (Exception e) {
					System.out.println("Thread interrompida!");
				}
		}
	}
	//AQUI É O NOSSO MÉTODO PRINCIPAL
	public static void main(String[] args) {
		Teste game = new Teste();//CRIAMOS UM OBJETO A PARTIR DESSA PROPRIA CLASSE
		game.run();//CHAMAMOS O METODO RUN()
	}
	//-----------------------------------------------------------------------------

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		try {
			cliente.enviarMensagem("ESPACE");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}// FIM DO CÓDIGO FONTE