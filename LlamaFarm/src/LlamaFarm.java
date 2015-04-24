import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LlamaFarm extends JFrame implements WindowListener
{
	private GamePanel gp;
	private int width = 1000;
	private int height = 1000;
	
	public LlamaFarm()
	{
		super("Llama Farm"); // Creates a window titled "Llama Farm"
		makeGUI();

		addWindowListener(this);
		setSize(width, height);
		setResizable(false);
		setVisible(true);

		int borderWidth = getWidth() - getContentPane().getWidth();
		int borderHeight = getHeight() - getContentPane().getHeight();
		setSize(width + borderWidth, height + borderHeight);
	}
	
	// Called when user runs program
	public static void main(String[] args)
	{
		// Testing
		// testGene();
		
		new LlamaFarm();
	}
	
	// Creates skeleton content for window and the game panel
	public void makeGUI()
	{
		Container c = getContentPane();
		gp = new GamePanel(this, width, height);
		c.add(gp, "Center");
	}
	
	// Testing Functions
	public static void testGene()
	{
		// Gene Testing
		// Create test genes with automatic values
		Gene a = new Gene(20, 0.9, 0.1);
		Gene b = new Gene(20, 0.1, 0.1);
		System.out.println("Parents:");
		System.out.println(a.getStrand1() + " " + a.getStrand2());
		System.out.println(b.getStrand1() + " " + b.getStrand2());
		
		// Create test genes with pre-determined values
		// Gene a = new Gene("1111111111", "0000000000", 0);
		// Gene b = new Gene("1111111111", "0000000000", 0);
		
		// Gene Meiosis Testing
		a.meiosis();
		b.meiosis();
		System.out.println("Parents after meiosis:");
		System.out.println(a.getStrand1() + " " + a.getStrand2());
		System.out.println(b.getStrand1() + " " + b.getStrand2());
		
		// Gene Breeding Testing
		a.changeOffspringCount(7);
		Gene[] offspring = a.combineWith(b);
		System.out.println("Children:");
		for (Gene x : offspring)
		{
			System.out.println(x.getStrand1() + " " + x.getStrand2() + " - " + x.getActivation());
		}
	}
	
	// Every method below here deals with user interaction with window
	public void windowActivated(WindowEvent e)
	{
	    gp.resumeGame();
	}
	
    public void windowDeactivated(WindowEvent e)
    {
  	    // gp.pauseGame();
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