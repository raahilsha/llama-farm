import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
	private int pWidth;
	private int pHeight;
	private LlamaFarm mainGame;

	private int framesPerSecond = -1;
	
	// Used for double buffering
	private Image dbImg;
	private Graphics dbG;

	private Thread game;
	private boolean isPaused;
	private boolean isRunning;
	
	// Game Specific Stuff
	private GameWorld world;

	// Instantiates game panel
	public GamePanel(LlamaFarm mg, int width, int height)
	{
		mainGame = mg;
		pWidth = width;
		pHeight = height;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(pWidth, pHeight));
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
		// Jungle
		world = new GameWorld(.2, .3, .31, 5, pWidth);
		
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
				if (framesPerSecond > 0)
					Thread.sleep(1000 / framesPerSecond); // This pauses current thread to wait until next frame
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
			dbImg = createImage(pWidth, pHeight);
			if (dbImg == null)
			{
				return;
			}
			else
			{
				dbG = dbImg.getGraphics();
			}
		}
		
		// Renders the game onto background image
		Color backgroundColor = Color.WHITE;
		dbG.setColor(backgroundColor);
		dbG.fillRect(0, 0, pWidth, pHeight);
		// world.renderWorld(dbG);
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