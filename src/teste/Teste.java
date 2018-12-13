package teste;


import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
//A CLASSE GAME HERDA AS FUNCIONALIDADES DE JFRAME
public class Teste extends JFrame {

	BufferedImage backBuffer;	//ESSE É O NOSSO BUFFER
	int FPS = 30;				//ESSA É A TAXA DE ATUALIZAÇÃO
	int janelaW = 500;			//LARGURA DA TELA
	int janelaH = 500;			//ALTURA DA TELA
	ImageIcon fundo = new ImageIcon(getClass().getResource("/resources/Corrida.jpeg"));
	ImageIcon gato1 =  new ImageIcon(getClass().getResource("/resources/gato1.png"));
	
	//NO NOSSO METODO ATUALIZAR VAMOS CHAMAR OS METODOS
	//QUE SERÃO EXECUTADOS O TEMPO INTEIRO...
	public void atualizar() {

	}

	//NESSE MÉTODO VAMOS IREMOS DESENHAR
	//FORMAS GEOMETRICAS, IMAGENS E TEXTOS NA TELA E ETC...
	public void desenharGraficos() {
		Graphics g = getGraphics();//COM g IREMOS DESENHAR O TUDO QUE ESTÁ NO BUFFER NA TELA
		Graphics bbg = backBuffer.getGraphics();//COM bbg IREMOS DESENHAR NO NOSSO BUFFER
		bbg.drawImage(fundo.getImage(),0,0, this);
		gato1.setImage(gato1.getImage().getScaledInstance(-200, 100, 0));
		bbg.drawImage(gato1.getImage(), 30, 100, this);
		
		
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
		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);//INICIADO O NOSSO BUFFER DE IMAGEM
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
}// FIM DO CÓDIGO FONTE