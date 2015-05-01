import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
	// Used to track UI changes - doesn't matter for this project
	private static final long serialVersionUID = -886995235406571190L;

	private int framesPerSecond = Parameters.frames_per_second;
	
	// Used for double buffering
	private Image dbImg;
	private Graphics dbG;

	private Thread game;
	private boolean isPaused;
	private boolean isRunning;
	
	// Game Specific Stuff
	private GameWorld world;

	// Instantiates game panel
	public GamePanel()
	{
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(Parameters.window_size, Parameters.window_size));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	// Starts game upon completion of panel creation
	public void addNotify()
	{
		super.addNotify();
		startGame();
	}

	public void startGame()
	{
		world = new GameWorld(Parameters.world_arability,
							  Parameters.world_obsrate,
							  Parameters.world_hazrate,
							  Parameters.energy_loss,
							  Parameters.window_size);
		
		game = new Thread(this);
		game.start();
	}

	// Takes care of current window state
	public void resumeGame()
	{
		isPaused = false;
	}

	public void pauseGame()
	{
		isPaused = true;
	}

	public void stopGame()
	{
		isRunning = false;
	}

	public void run()
	{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		isRunning = true;

		// Main loop of the game panel!
		while (isRunning)
		{
			gameUpdate();
			gameRender();
			paintScreen();

			try
			{
				// This pauses current thread to wait until next frame
				if (framesPerSecond > 0)
					Thread.sleep(1000 / framesPerSecond);
			}
			catch (InterruptedException ex)
			{
				repaint();
			}
			Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		}

		System.exit(0);
	}

	// Runs main simulation of game world
	public void gameUpdate()
	{
		if (!isPaused)
		{
			world.Tick();
		}
	}

	public void gameRender()
	{
		if (dbImg == null)
		{
			dbImg = createImage(Parameters.window_size, Parameters.window_size);
			if (dbImg == null)
			{
				return;
			}
			else
			{
				dbG = dbImg.getGraphics();
			}
		}
		
		// Creates a black background in the back - "wipes screen"
		Color backgroundColor = Color.WHITE;
		dbG.setColor(backgroundColor);
		dbG.fillRect(0, 0, Parameters.window_size, Parameters.window_size);
		// Renders the world
		world.renderWorld(dbG);
	}

	// Actual double buffer code
	public void paintScreen()
	{
		Graphics g;
		try
		{
			g = this.getGraphics();
			if ((g != null) && (dbImg != null))
			{
				g.drawImage(dbImg, 0, 0, null);
			}
			g.dispose();
		}
		catch (Exception e)
		{
		}
	}

	// Everything below here takes care of key and mouse events
	public void keyPressed(KeyEvent e)
	{
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public void mouseDragged(MouseEvent e)
	{
	}
	public void mouseMoved(MouseEvent e)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
}