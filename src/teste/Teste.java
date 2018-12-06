package teste;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class Teste extends JFrame
{
	private static final long serialVersionUID = 1L;
	public Teste()
	{
		addKeyListener( new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
				System.out.println(e.getKeyChar());				
			}
			@Override
			public void keyReleased(KeyEvent e)
			{
			}
			@Override
			public void keyPressed(KeyEvent e) 
			{
			}
		});
		setSize(500, 500);
		setVisible( true );		
	}	
	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new Teste();
			}
		});
	}
}
