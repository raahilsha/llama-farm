import java.awt.*;

public class Llama
{
	// All possible states a llama can obtain
	// Note that NIRVANA is not among these
	public enum State { IDLE_STATE, FOOD_SEEK, EATING, AGGRESSIVE, ATTACKING, RUNNING_AWAY, RANDOM_WALK, STUNNED }
	
	private GameWorld world;
	private Gene[] genome;
	private int energy;
	
	private double hungerDriveInd, lazinessInd, violenceInd, metabolismInd, styleInd; // This is the order
	
	private State currentState;
	
	private int xPos;
	private int yPos;
	
	// Constructor for llama
	public Llama(GameWorld gw, Gene[] g)
	{
		world = gw;
		genome = g;
		currentState = State.IDLE_STATE;

		hungerDriveInd = g[0].getActivation();
		lazinessInd = g[1].getActivation();
		violenceInd = g[2].getActivation();
		metabolismInd = g[3].getActivation();
		styleInd = g[4].getActivation();

		xPos = (int)(Math.random() * world.getNumTiles())  * world.getTileSize();
		yPos = (int)(Math.random() * world.getNumTiles()) * world.getTileSize();
	}
	
	// Renders Llama onto game panel based on its stylishness
	public void renderLlama(Graphics img, int tileSize)
	{
		if (styleInd < 0.2)
			img.setColor(new Color(255, 128, 171));
		else if (styleInd < 0.4)
			img.setColor(new Color(234, 128, 252));
		else if (styleInd < 0.6)
			img.setColor(new Color(213, 0, 249));
		else if (styleInd < 0.8)
			img.setColor(new Color(93, 64, 55));
		else if (styleInd < 0.9)
			img.setColor(new Color(55, 71, 79));
		else
			img.setColor(new Color(255, 214, 0));
		
		img.fillOval(xPos, yPos, tileSize - 1, tileSize - 1);
	}
	
	// Breeds one llama with other
	public Llama[] breedWith(Llama other)
	{
		int offspringCount = 4;
		int numGenes = genome.length;
		Gene[] otherGenome = other.getGenome();
		Llama[] offspringLlamas = new Llama[offspringCount];

		Gene[][] finishedChildren = new Gene[numGenes][offspringCount];
		Gene[][] finishedChildrenT = new Gene[offspringCount][numGenes];
		
		// One pass to mate all the genes individually with each other
		for (int i = 0; i < numGenes; i++)
		{
			finishedChildren[i] = genome[i].combineWith(otherGenome[i]);
		}
		
		// Transpose finishedChildren to allow us to extract chains of genes
		for (int r = 0; r < numGenes; r++)
		{
			for (int c = 0; c < offspringCount; c++)
			{
				finishedChildrenT[c][r] = finishedChildren[r][c];
			}
		}
		
		// Place all of the combined genes into new llamas
		for (int i = 0; i < offspringCount; i++)
		{
			offspringLlamas[i] = new Llama(world, finishedChildrenT[i]);
		}
		
		return offspringLlamas;
	}
	
	// Runs the Finite State Machine of Llama 
	public void action()
	{
		switch (currentState)
		{
			case IDLE_STATE:
				break;
			case FOOD_SEEK:
				break;
			case EATING:
				break;
			case AGGRESSIVE:
				break;
			case ATTACKING:
				break;
			case RUNNING_AWAY:
				break;
			case RANDOM_WALK:
				break;
			case STUNNED:
				break;
		}
		
		if (styleInd < Math.random())
		{
			die();
		}
		if (energy <= 0)
			die();
	}
	
	// Kills the current llama
	public void die()
	{
		world.removeLlama(this);
	}
	
	// Accessor for genome; used when mating
	public Gene[] getGenome()
	{
		return genome;
	}
	
	// Accessor for current state
	public State getState()
	{
		return currentState;
	}
}
