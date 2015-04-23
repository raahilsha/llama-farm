import java.awt.*;
import java.util.*;

public class GameWorld
{
	private static int numTiles = 125;
	private static int numLlamas = 100;
	
	private ArrayList<Llama> llamas;
	private boolean isRunningSimulation;
	private Tile[][] worldTiles;
	private int tileSize;
	
	private double arability;
	private double energyDrain;
	private double obsRate;
	private double hazRate;
	
	private int generation;
	private int ticksThisGen;
	
	// Constructor for GameWorld
	public GameWorld(double a, double o, double h, int drain, int pw)
	{
		arability = a;
		obsRate = o;
		hazRate = h;
		
		generation = 0;
		energyDrain = drain;
		tileSize = pw / numTiles;
		
		createInitialGeneration();
		isRunningSimulation = true;
		
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
	
	private void createInitialGeneration()
	{
		llamas = new ArrayList<Llama>();
		
		for (int i = 0; i < numLlamas; i++)
		{
			Gene hunger = new Gene(100, 0.1, .01);
			Gene lazy = new Gene(100, 0.1, .01);
			Gene violent = new Gene(100, 0.1, .01);
			Gene meta = new Gene(100, 0.1, .01);
			Gene style = new Gene(100, 0.1, .01);
			
			Gene[] newGenes = {hunger, lazy, violent, meta, style};
			Llama toAdd = new Llama(this, newGenes);
			
			llamas.add(toAdd);
		}
	}

	// Runs every frame and decides to create new generation if necessary
	public void Tick()
	{
		if (isRunningSimulation)
		{
			ticksThisGen++;

			if (llamas.size() <= numLlamas / 2)
			{
				isRunningSimulation = false;
				return;
			}
			
			for (int i = 0; i < llamas.size(); i++)
			{
				llamas.get(i).action();
			}
		}
		else
		{
			createNewGeneration();
			generation++;
			ticksThisGen = 0;
			isRunningSimulation = true;
		}
	}
	
	
	// Takes the arraylist of llamas and creates a brand new generation
	public void createNewGeneration()
	{
		double[] representation = getGeneRepresentation();
		
		while (llamas.size() < numLlamas / 2)
		{
			addRepresentativeLlama(representation);
		}
		
		for (int i = 0; i < numLlamas / 4; i++)
		{
			Llama llama1 = llamas.remove(0);
			Llama llama2 = llamas.remove(0);
			
			Llama[] children = llama1.breedWith(llama2);
			for (Llama l : children)
			{
				llamas.add(l);
			}
		}
		
		System.out.flush();
	}
	
	private void addRepresentativeLlama(double[] representation)
	{
		Gene hunger = new Gene(100, representation[0], .01);
		Gene lazy = new Gene(100, representation[1], .01);
		Gene violent = new Gene(100, representation[2], .01);
		Gene meta = new Gene(100, representation[3], .01);
		Gene style = new Gene(100, representation[4], .01);
		
		Gene[] newGenes = {hunger, lazy, violent, meta, style};
		Llama toAdd = new Llama(this, newGenes);
		
		llamas.add(toAdd);
	}

	private double[] getGeneRepresentation()
	{
		double[] represent = new double[5];
		
		for (int i = 0; i < llamas.size(); i++)
		{
			for (int j = 0; j < 5; j++)
			{
				represent[j] += llamas.get(i).getGenome()[j].getActivation();
			}
		}
		
		for (int j = 0; j < 5; j++)
		{
			represent[j] /= llamas.size();
		}
		
		return represent;
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
		
		for (int i = 0; i < llamas.size(); i++)
		{
			llamas.get(i).renderLlama(img, tileSize);
		}
	}
	
	public void removeLlama(Llama l)
	{
		llamas.remove(l);
	}
	
	public int getNumTiles()
	{
		return numTiles;
	}
	
	public int getTileSize()
	{
		return tileSize;
	}
}