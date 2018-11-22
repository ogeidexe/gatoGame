package motor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import interfaces.IControles;

public class Controles extends JFrame implements KeyListener, IControles{
	public Controles() {
		addkeyListener(this);
	}
	public void obterTecla() {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int obterTeclaPressionada(){
		addkeyListener(this);
		
		return 0;
	}

}
