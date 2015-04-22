import java.awt.*;

public class Tile
{
	private Color tileColor;
	private int tileSize;
	private int xPos;
	private int yPos;
	private TileTypes ttype;
	
	public enum TileTypes { SOIL, FOOD, HAZARD, OBSTACLE }
	
	// Constructor for tile
	public Tile(TileTypes c, int s, int x, int y)
	{
		tileSize = s;
		xPos = x;
		yPos = y;
		
		ChangeTile(c);
	}
	
	// Changes the color of tile
	public void ChangeTile(TileTypes c)
	{
		ttype = c;
		
		switch (ttype)
		{
			case SOIL:
				tileColor = new Color(158, 157, 38);
				break;
			case FOOD:
				tileColor = new Color(56, 142, 60);
				break;
			case HAZARD:
				tileColor = new Color(198, 40, 40);
				break;
			case OBSTACLE:
				tileColor = new Color(55, 71, 79);
				break;
		}
	}
	
	// Renders the tile onto the game panel
	public void RenderTile(Graphics img)
	{
		img.setColor(tileColor);
		img.fillRect(xPos, yPos, tileSize, tileSize);
	}
}
