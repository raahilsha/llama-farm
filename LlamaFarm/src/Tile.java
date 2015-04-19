import java.awt.*;

public class Tile
{
	private Color tileColor;
	private int tileSize;
	private int xPos;
	private int yPos;
	
	// Constructor for tile
	public Tile(Color c, int s, int x, int y)
	{
		tileColor = c;
		tileSize = s;
		xPos = x;
		yPos = y;
	}
	
	// Changes the color of tile
	public void ChangeColor(Color c)
	{
		tileColor = c;
	}
	
	// Renders the tile onto the game panel
	public void RenderTile(Graphics img)
	{
		
	}
}