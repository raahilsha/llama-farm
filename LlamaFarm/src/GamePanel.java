import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
	private int pWidth;
	private int pHeight;
	private LlamaFarm mainGame;

	private int framesPerSecond = 100;
	private Image dbImg;
	private Graphics dbG;

	private Thread game;
	private boolean isPaused;
	private boolean isRunning;

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

	public void addNotify()
	{
		super.addNotify();
		startGame();
	}

	public void startGame()
	{
		game = new Thread(this);
		game.start();
	}

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

		while (isRunning)
		{
			gameUpdate();
			gameRender();
			paintScreen();

			try
			{
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

	public void gameUpdate()
	{
		if (!isPaused)
		{
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
		Color backgroundColor = Color.WHITE;
		dbG.setColor(backgroundColor);
		dbG.fillRect(0, 0, pWidth, pHeight);
	}

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