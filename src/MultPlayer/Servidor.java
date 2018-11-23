package MultPlayer;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor extends Thread{
	private static ArrayList<BufferedWriter>clientes;           
	private static ServerSocket server; 
	private String nome;
	private Socket socket;
	public Servidor(Socket socket)
	  {
	    this.socket = socket;
	  }

	  @Override
	  public void run()
	  {
	    try
	    {
	      //Obtém os streams de entrada e saída
	      DataInputStream in = new DataInputStream(socket.getInputStream());
	      DataOutputStream out = new DataOutputStream(socket.getOutputStream());

	      double cartao = in.readDouble(); 
	      double valor = in.readDouble(); 

	    }
	    catch (IOException ex)
	    {
	      System.err.println("Erro: " + ex.getMessage());
	    }

	  }
	
	     public static void main(String [] args)
	     {
	       try
	       {
	         ServerSocket serverSocket = new ServerSocket(12345); //Cria um server socket para aguardar requisições dos clientes
	         while(true)
	         {
	           System.out.println("Aguardando conexões...");
	           Socket socket = serverSocket.accept(); //Fica aguardando pedidos de conexão
	           System.out.println("Conectou-se...");
	           (new Servidor(socket)).start(); //Inicia a thread que tratará do cliente
	         }
	       }
	       catch (IOException ex)
	       {
	         System.err.println("Erro: " + ex.getMessage());
	       }
	     }
	  }


