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
	
	// Changes the type of tile
	public void changeTile(TileTypes c)
	{
		ttype = c;
		
		switch (ttype)
		{
			case SOIL:
				tileColor = Parameters.tile_colors[0];
				break;
			case FOOD:
				tileColor = Parameters.tile_colors[1];
				break;
			case HAZARD:
				tileColor = Parameters.tile_colors[2];
				break;
			case OBSTACLE:
				tileColor = Parameters.tile_colors[3];
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
