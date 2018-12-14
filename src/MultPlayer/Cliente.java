package MultPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import tela.TelaInicio;
import teste.Teste;

public class Cliente  extends Thread  
{
  private String ip; //O IP do servidor
  private int porta; //A porta de comunicação que será utilizada
  private boolean isConectado = false;
  Socket socket;
  ArrayList<Integer> listaD;
  DataInputStream in;
  DataOutputStream out;
  OutputStream ou;
  private Writer ouw;
  private BufferedWriter bfw;
  private TelaInicio ti;
  private Teste te;
  public String id;
  public String msgAtual;
  
  public Cliente(String ip,Integer porta,TelaInicio ti,Teste te) throws UnknownHostException, IOException {
	  this.porta =  porta;
	  this.ip = ip;
	  this.ti = ti;
	  this.te = te;
	  
  }
 
  public void conectar() throws IOException {
	    socket = new Socket(ip,porta);
		ou = socket.getOutputStream();
		ouw = new OutputStreamWriter(ou);
		bfw = new BufferedWriter(ouw);
		bfw.write( "IamNew"+"\r\n");
		bfw.flush();
  }
  	//Verifica se o cliente esta conectado ao servidor
  	public boolean isConectado() {
  		return this.isConectado;
  	}
  	/***
	 * M�todo usado para enviar mensagem para o server socket
	 * 
	 * @param msg
	 *            do tipo String
	 * @throws IOException
	 *             retorna IO Exception caso d� algum erro.
	 */
	public void enviarMensagem(String msg) throws IOException {
		if (msg.equals("140")) {
			System.exit(0);
		} else {			
			bfw.write(msg+"\r\n");
			bfw.flush();
		}
		bfw.flush();
	}

  	
  	//Escuta Alterações
 
	public void escutar() throws IOException {
		InputStream in = socket.getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "DEFAUT";

		while (!"Sair".equalsIgnoreCase(msg))
			if (msg.substring(0,4).equals("MYID")) {
				setMYID(msg);
			}else {
				if (bfr.ready()) {
					msg = bfr.readLine();
					ti.teste(msg);
					te.setMsgatual(msg);
					setmsgAtual(msg);
					//texto.append("Servidor caiu! \r\n");
				}
			}
	}
	public void setmsgAtual(String msg) {
		this.msgAtual = msg;
	}
	public String getmsgAtual() {
		return msgAtual;
	}
	public void setMYID(String id){
		this.id = id;
	}
	public String getMYID() {
		return this.id;
	}
	@Override
	public void run() {
		try {
			conectar();
			escutar();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
  	
	}
	public static void main(String [] args) throws IOException
     {
        //Cria o cliente para se conectar ao servidor no IP 127.0.0.1 e porta 12345
        //Cliente cliente = new Cliente("127.0.0.1",1234,);
        //cliente.conectar(); //Coloca a thread do cliente para ser executada
        //cliente.escutar();
     }
}