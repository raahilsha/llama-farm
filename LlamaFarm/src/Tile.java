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
		
		changeTile(c);
	}
	
	// Changes the color of tile
	public void changeTile(TileTypes c)
	{
		ttype = c;
		
		switch (ttype)
		{
			case SOIL:
				tileColor = new Color(0x795548);
				break;
			case FOOD:
				tileColor = new Color(0x00E676);
				break;
			case HAZARD:
				tileColor = new Color(0xFF3D00);
				break;
			case OBSTACLE:
				tileColor = new Color(0x424242);
				break;
		}
	}
	
	// Renders the tile onto the game panel
	public void renderTile(Graphics img)
	{
		img.setColor(tileColor);
		img.fillRect(xPos, yPos, tileSize, tileSize);
	}
	
	// Accessor for tile type
	public TileTypes getType()
	{
		return ttype;
	}
}
