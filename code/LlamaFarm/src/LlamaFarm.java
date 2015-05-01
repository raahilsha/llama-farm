import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LlamaFarm extends JFrame implements WindowListener
{
	// Used to track UI changes - doesn't matter for this project
	private static final long serialVersionUID = -2776671431228020692L;
	
	private GamePanel gp;
	private int width = Parameters.window_size;
	private int height = Parameters.window_size;
	
	// Constructor for the game window
	public LlamaFarm()
	{
		super("Llama Farm"); // Creates a window titled "Llama Farm"
		makeGUI();

		addWindowListener(this);
		setSize(width, height);
		setResizable(false);
		setVisible(true);

		// Adjust to account for the borders
		int borderWidth = getWidth() - getContentPane().getWidth();
		int borderHeight = getHeight() - getContentPane().getHeight();
		setSize(width + borderWidth, height + borderHeight);
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
		gp = new GamePanel();
		c.add(gp, "Center");
	}
	
	// Every method below here deals with user interaction with window
	public void windowActivated(WindowEvent e)
	{
	    gp.resumeGame();
	}
	
    public void windowDeactivated(WindowEvent e)
    {
    	// Commented this out to allow program to run minimized
  	    // gp.pauseGame();
    }
    
    public void windowDeiconified(WindowEvent e)
    {
    	gp.resumeGame();
    }
    
    public void windowIconified(WindowEvent e)
    {
    	// gp.pauseGame();
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