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
		super("Llama Farm"); // Creates a window titled "Llama Farm"
		makeGUI();

		addWindowListener(this);
		setSize(width, height);
		setResizable(false);
		setVisible(true);
	}
	
	// Called when user runs program
	public static void main(String[] args)
	{
		new LlamaFarm();
	}
	
	// Creates skeleton content for window and the game panel
	public void makeGUI()
	{
		Container c = getContentPane();
		gp = new GamePanel(this, width, height);
		c.add(gp, "Center");
	}
	
	// Every method below here deals with user interaction with window
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