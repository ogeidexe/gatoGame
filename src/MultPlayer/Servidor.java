package MultPlayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Servidor extends Thread {

	private static ArrayList<BufferedWriter> clientes;
	private static ServerSocket server;
	private String nome;
	private Socket con;
	private InputStream in;
	private OutputStream out;
	private InputStreamReader inr;
	private BufferedReader bfr;
	private ArrayList<String> sinc = new ArrayList<>();
	
	/**
	 * M�todo construtor
	 * 
	 * @param com
	 *            do tipo Socket
	 */
	public Servidor(Socket con) {
		this.con = con;
		try {
			in = con.getInputStream();
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeInt(1);
			inr = new InputStreamReader(in);
			bfr = new BufferedReader(inr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private synchronized void addCliente(BufferedWriter bfw) {
		clientes.add(bfw);
	}
	private synchronized void removeCliente(BufferedWriter bfw) {
		clientes.remove(bfw);
	}
	/**
	 * Obtem o Ip Local da Maquina
	 **/
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
	/**
	 * Método run
	 **/
	
	public void run() {

		try {
				String msg  = null;
				OutputStream ou = this.con.getOutputStream();
				Writer ouw = new OutputStreamWriter(ou);
				BufferedWriter bfw = new BufferedWriter(ouw);
				//chama metodo com modificador synchronized para garantir acesso exclusivo
				this.addCliente(bfw);
				//msg = bfr.readLine();  && msg != null
				while (!"Sair".equalsIgnoreCase(msg)) {
					msg = bfr.readLine();
					System.out.println("++"+msg+"++");
					System.out.println(sinc.toString());
					if(!msg.equals(""))
						sinc.add(msg);
			
					//if(msg.equals("IamNew")) {
						sincronizar(bfw);
						System.out.println("cliente novo");
					//}
						
					//else
						//sendToAll(msg);
//						if(msg.equals("Gato 1")) {
//							System.out.println("Mandando Mesagen de Inicio");
//								
//								//sendToAll("START");
//						}
					sendToAll(msg);
//						
//						//System.out.println(msg);
					
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *
	 * 
	 * 
	 * 
	 */
	public void sincronizar(BufferedWriter bfw){
		BufferedWriter bwS, bwP = null;
		bwS =  bfw;
	
		for (String string : sinc) {
					try {
						bfw.write(string+ "\r\n");
						bfw.flush();
					} catch (IOException e) {
						bwP=bwS;
					}
		}
	}
	/**
	 * M�todo usado para enviar mensagem para todos os clients
	 * 
	 * @param bwSaida
	 *            do tipo BufferedWriter
	 * @param msg
	 *            do tipo String
	 * @throws IOException
	 */
	
	public void sendToAll( String msg) throws IOException {
		BufferedWriter bwS, bwP = null;
		for (BufferedWriter bw : clientes) {
			bwS =  bw;
				try{
				bw.write(msg + "\r\n");
				bw.flush();
				}catch(Exception e){
					bwP=bwS;
				}	
		}
		if(bwP !=null){
			this.removeCliente(bwP);
		}
	}

	/***
	 * Método main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// Cria os objetos necessário para inst�nciar o servidor
			JLabel lblMessage = new JLabel("Porta do Servidor:");
			JTextField txtPorta = new JTextField("12345");
			Object[] texts = { lblMessage, txtPorta };
			JOptionPane.showMessageDialog(null, texts);
			server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
			clientes = new ArrayList<BufferedWriter>();
			JOptionPane.showMessageDialog(null, "Servidor ativo na porta: " + txtPorta.getText());
			while (true) {
				System.out.println("Aguardando conexão...");
				Socket con = server.accept();
				
				System.out.println("Cliente conectado...");
				Thread t = new Servidor(con);
				t.start();
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}// Fim do m�todo main
} // Fim da classe