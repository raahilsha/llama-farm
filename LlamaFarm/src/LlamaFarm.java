import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LlamaFarm extends JFrame implements WindowListener
{
	private GamePanel gp;
	private int width = 750;
	private int height = 750;
	
	public LlamaFarm()
	{
		super("Llama Farm");
		makeGUI();

		addWindowListener(this);
		setSize(width, height);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new LlamaFarm();
	}
	
	public void makeGUI()
	{
		Container c = getContentPane();
		gp = new GamePanel(this, width, height);
		c.add(gp, "Center");
	}
	
	public void windowActivated(WindowEvent e)
	{
	    gp.resumeGame();
	}
	
    public void windowDeactivated(WindowEvent e)
    {
  	    gp.pauseGame();
    }
    
    public void windowDeiconified(WindowEvent e)
    {
    	gp.resumeGame();
    }
    
    public void windowIconified(WindowEvent e)
    {
    	gp.pauseGame();
    }
    
    public void windowClosing(WindowEvent e)
    {
  	    gp.stopGame();
    }
    
    public void windowClosed(WindowEvent e)
    {
    }
    
    public void windowOpened(WindowEvent e)
    {
    }
}