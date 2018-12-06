package MultPlayer;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JOptionPane;

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
	      int i = in.readInt();
	      if(i==561) {
	    	  JOptionPane.showMessageDialog(null, i);
	      }
	    }
	    catch (IOException ex)
	    {
	      System.err.println("Erro: " + ex.getMessage());
	    }

	  }
	  public static String getIp() {
	        String ipAddress = null;
	        Enumeration<NetworkInterface> net = null;
	        try {
	            net = NetworkInterface.getNetworkInterfaces();
	        } catch (SocketException e) {
	            throw new RuntimeException(e);
	        }

	        while (net.hasMoreElements()) {
	            NetworkInterface element = net.nextElement();
	            Enumeration<InetAddress> addresses = element.getInetAddresses();
	            while (addresses.hasMoreElements()) {
	                InetAddress ip = addresses.nextElement();

	                if (ip.isSiteLocalAddress()) {
	                    ipAddress = ip.getHostAddress();
	                }           
	            }
	        }
	        return ipAddress;
	    }
	  
	    public static void main(String [] args)
	     {
	       try
	       {
	         ServerSocket serverSocket = new ServerSocket(17220); //Cria um server socket para aguardar requisições dos clientes
	         while(true)
	         {
	           System.out.println("Aguardando conexões...");
	           Socket socket = serverSocket.accept(); //Fica aguardando pedidos de conexão
	           System.out.println("Conectou-se...");
	           (new Servidor(socket)).start(); //Inicia a thread que tratara do cliente
	         }
	       }
	       catch (IOException ex)
	       {
	         System.err.println("Erro: " + ex.getMessage());
	       }
	     }
	  }


