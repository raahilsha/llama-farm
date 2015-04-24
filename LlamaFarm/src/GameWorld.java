import java.awt.*;
import java.util.*;

public class GameWorld
{
	private static int numTiles = 125;
	private static int numLlamas = 100;
	
	private ArrayList<Llama> llamas;
	private boolean isRunningSimulation;
	private Tile[][] worldTiles;
	private Llama[][] llamaLocs;
	private int tileSize;
	
	private double arability;
	private double energyDrain;
	private double obsRate;
	private double hazRate;
	
	private int generation;
	private int ticksThisGen;
	
	// Constructor for GameWorld
	public GameWorld(double a, double o, double h, int d, int pw)
	{
		// Basic  world parameters
		arability = a;
		obsRate = o;
		hazRate = h;
		energyDrain = d;
		
		// Calculated world parameters
		generation = 0;
		tileSize = pw / numTiles;
		llamaLocs = new Llama[numTiles][numTiles];
		
		createInitialGeneration();
		isRunningSimulation = true;
		
		// Loops through all the tiles and assigns them a type
		worldTiles = new Tile[numTiles][numTiles];
		retile();
		printGenerationStats();
	}
	
	// Used to first instantiate the llama list in Generation 1
	private void createInitialGeneration()
	{
		llamas = new ArrayList<Llama>();
		
		for (int i = 0; i < numLlamas; i++)
		{
			Gene hunger = new Gene(100, 0.1, .1);
			Gene lazy = new Gene(100, 0.7, .1);
			Gene violent = new Gene(100, 0.1, .1);
			Gene meta = new Gene(100, 0.1, .1);
			Gene style = new Gene(100, 0.1, .1);
			
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
			generateLlamaLocs();

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
			printGenerationStats();
		}
	}
	
	
	private void generateLlamaLocs()
	{
		for (int r = 0; r < numTiles; r++)
		{
			for (int c = 0; c < numTiles; c++)
			{
				llamaLocs[r][c] = null;
			}
		}
		
		for (int i = 0; i < llamas.size(); i++)
		{
			Llama locAdd = llamas.get(i);
			llamaLocs[locAdd.getY()][locAdd.getX()] = locAdd;
		}
	}

	// Takes the arraylist of llamas and creates a brand new generation
	public void createNewGeneration()
	{
		retile();
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
	}
	
	public void retile()
	{
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
	
	// Adds llamas to the list based on the "representative" genes to balance llama population to even 50
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
	
	// Returns the "representative" values of the genes for the llama population
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
	
	// Allows a llama to kill itself / remove itself from the world
	public void removeLlama(Llama l)
	{
		llamas.remove(l);
	}
	
	// Accessor for the number of tiles
	public int getNumTiles()
	{
		return numTiles;
	}
	
	// Accessor for tile size
	public int getTileSize()
	{
		return tileSize;
	}
	
	public double getEnergyUse()
	{
		return this.energyDrain;
	}
	
	public Tile[][] getMap()
	{
		return worldTiles;
	}

	public Llama llamaAtLoc(int xPos2, int yPos2)
	{
		return llamaLocs[yPos2][xPos2];
	}
	
	public void printGenerationStats()
	{
		double[] repGenes = getGeneRepresentation();
		System.out.println("Generation " + generation + " stats");
		System.out.println("--------------------");
		System.out.println("Hunger Drive: " + repGenes[0]);
		System.out.println("Laziness: " + repGenes[1]);
		System.out.println("Violence: " + repGenes[2]);
		System.out.println("Metabolism: " + repGenes[3]);
		System.out.println("Stylishness: " + repGenes[4]);
	}
}