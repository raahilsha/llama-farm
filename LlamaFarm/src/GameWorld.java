import java.awt.*;
import java.util.*;

public class GameWorld
{
	private static int numTiles = 100;
	
	private ArrayList<Llama> llamas;
	private boolean isRunningSimulation;
	private Tile[][] worldTiles;
	private int tileSize;
	
	private double arability;
	private double energyDrain;
	private double obsRate;
	private double hazRate;
	
	private int generation;
	
	// Constructor for GameWorld
	public GameWorld(double a, double o, double h, int drain, int pw)
	{
		arability = a;
		obsRate = o;
		hazRate = h;
		
		generation = 0;
		energyDrain = drain;
		tileSize = pw / numTiles;
		
		worldTiles = new Tile[numTiles][numTiles];
		for (int r = 0; r < numTiles; r++)
		{
			for (int c = 0; c < numTiles; c++)
			{
				double ra = Math.random();
				if (ra < arability)
				{
					worldTiles[r][c] = new Tile(Tile.TileTypes.FOOD, tileSize, c * tileSize, r * tileSize);
				}
				else if (ra < obsRate)
				{
					worldTiles[r][c] = new Tile(Tile.TileTypes.OBSTACLE, tileSize, c * tileSize, r * tileSize);
				}
				else if (ra < hazRate)
				{
					worldTiles[r][c] = new Tile(Tile.TileTypes.HAZARD, tileSize, c * tileSize, r * tileSize);
				}
				else
				{
					worldTiles[r][c] = new Tile(Tile.TileTypes.SOIL, tileSize, c * tileSize, r * tileSize);
				}
			}
		}
	}
	
	// Runs every frame and decides to create new generation if necessary
	public void Tick()
	{
		if (isRunningSimulation)
		{
			
		}
		else
		{
			createNewGeneration();
			generation++;
			isRunningSimulation = true;
		}
	}
	
	
	// Takes the arraylist of llamas and creates a brand new generation
	public void createNewGeneration()
	{
		
	}
	
	// Renders the tiles and llamas onto the game panel
	public void renderWorld(Graphics img)
	{
		for (int r = 0; r < numTiles; r++)
		{
			for (int c = 0; c < numTiles; c++)
			{
				worldTiles[r][c].renderTile(img);
			}
		}
	}
}