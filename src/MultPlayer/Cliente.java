package MultPlayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread
{
  private String ip; //O IP do servidor
  private int porta; //A porta de comunicação que será utilizada

  public Cliente(String ip, int porta)
  {
    this.ip = ip;
    this.porta = porta;
  }

  @Override
  public void run()
  {
    try
    {
            
      Socket socket = new Socket(ip, porta); //Conecta-se ao servidor
      //Obtém os streams de entrada e saída
      System.out.println("CONECTOU"+socket.isConnected());
      DataInputStream in = new DataInputStream(socket.getInputStream());
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      int i = 561;
      out.writeInt(i); 
      out.flush(); //Forca o envio
      System.out.println(socket.isClosed());
     // out.writeDouble(valor); 
      out.flush();
 
    }
    catch (Exception ex)
    {
        System.err.println("Erro: " + ex.getMessage());
     
      }
    }
  
  	public static void main(String [] args)
     {
        //Cria o cliente para se conectar ao servidor no IP 127.0.0.1 e porta 12345
        Cliente cliente = new Cliente("192.168.1.5", 17220);

        cliente.start(); //Coloca a thread do cliente para ser executada
     }
  }
   