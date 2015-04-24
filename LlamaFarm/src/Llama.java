import java.awt.*;
import java.util.*;

public class Llama
{
	// All possible states a llama can obtain
	// Note that NIRVANA is not among these
	public static Color[] styleColors = {
		new Color(0xFCE4EC),
		new Color(0xF8BBD0),
		new Color(0xF48FB1),
		new Color(0xF06292),
		new Color(0xEC407A),
		new Color(0xE91E63),
		new Color(0xD81B60),
		new Color(0xC2185B),
		new Color(0xAD1457),
		new Color(0x880E4F)
	};
	
	public enum State { IDLE_STATE, FOOD_SEEK, EATING, AGGRESSIVE, ATTACKING, RUNNING_AWAY, RANDOM_WALK, STUNNED }
	
	private GameWorld world;
	private Gene[] genome;
	private double energy;
	
	private double hungerDriveInd, lazinessInd, violenceInd, metabolismInd, styleInd; // This is the order
	
	private State currentState;
	private int cooldown;
	public int xTarget, yTarget;
	private Llama targetLlama;
	
	private int xPos;
	private int yPos;
	
	// Constructor for llama
	public Llama(GameWorld gw, Gene[] g)
	{
		world = gw;
		genome = g;
		currentState = State.IDLE_STATE;
		cooldown = 0;

		hungerDriveInd = g[0].getActivation();
		lazinessInd = g[1].getActivation();
		violenceInd = g[2].getActivation();
		metabolismInd = g[3].getActivation();
		styleInd = g[4].getActivation();
		
		energy = 1000;

		xPos = (int)(Math.random() * world.getNumTiles());
		yPos = (int)(Math.random() * world.getNumTiles());
	}
	
	// Renders Llama onto game panel based on its stylishness
	public void renderLlama(Graphics img, int tileSize)
	{
		if (styleInd >= 1.0)
			img.setColor(styleColors[9]);
		else
			img.setColor(styleColors[(int)(styleInd * styleColors.length)]);
		
		img.fillOval(xPos * world.getTileSize(), yPos * world.getTileSize(), tileSize - 1, tileSize - 1);
		
		if (currentState == State.STUNNED)
		{
			img.setColor(Color.yellow);
			img.fillRect(xPos * world.getTileSize() + 2, yPos * world.getTileSize() + 2, tileSize / 3, tileSize / 3);
		}
		
		if (currentState == State.AGGRESSIVE)
		{
			img.setColor(Color.red);
			img.fillRect(xPos * world.getTileSize() + 2 + tileSize / 3, yPos * world.getTileSize() + 2 + tileSize / 3, tileSize / 3, tileSize / 3);
		}
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
		if (currentState != State.IDLE_STATE)
			energy -= world.getEnergyUse();
		switch (currentState)
		{
			case IDLE_STATE:
				idleAction();
				break;
			case FOOD_SEEK:
				foodSeekAction();
				break;
			case EATING:
				eatAction();
				break;
			case AGGRESSIVE:
				aggressiveAction();
				break;
			case ATTACKING:
				attackingAction();
				break;
			case RUNNING_AWAY:
				runningAwayAction();
				break;
			case RANDOM_WALK:
				randWalkAction();
				break;
			case STUNNED:
				stunAction();
				break;
		}
		
		if (energy <= 0)
			die();
	}
	
	private void idleAction()
	{
		energy += world.getEnergyUse() * .25;
		if (Math.random() > 1 - (1 - lazinessInd) / 4)
		{
			currentState = State.RANDOM_WALK;
		}
	}
	
	private void stunAction()
	{
		energy -= world.getEnergyUse();
		if (cooldown <= 0)
			currentState = State.IDLE_STATE;
		cooldown--;
	}
	
	private void runningAwayAction()
	{
		if (cooldown <= 0)
		{
			currentState = State.IDLE_STATE;
		}
		this.moveAwayFromTarget();
		cooldown--;
	}
	
	private void aggressiveAction()
	{
		energy -= world.getEnergyUse() * .25;
		Tile[][] worldMap = world.getMap();
		if (checkRecAttackTarget(worldMap, xPos, yPos, 5))
		{
			currentState = State.ATTACKING;
		}
		else
		{
			makeRandomMove();
		}
	}
	
	private boolean checkRecAttackTarget(Tile[][] worldMap, int xPos2, int yPos2, int i)
	{
		if (i <= 0)
			return false;
		if (outOfBounds(worldMap, xPos2, yPos2))
			return false;
		
		Llama otherAtLoc = world.llamaAtLoc(xPos2, yPos2);
		if (otherAtLoc != null && otherAtLoc != this)
		{
			targetLlama = otherAtLoc;
			xTarget = targetLlama.getX();
			yTarget = targetLlama.getY();
			return true;
		}
		
		ArrayList<int[]> possiblePaths = new ArrayList<int[]>();
		// This looks like bad code, but arrays are just annoying in Java
		int[] newThing = {xPos2 + 1, yPos2};
		int[] newThing2 = {xPos2 - 1, yPos2};
		int[] newThing3 = {xPos2, yPos2 + 1};
		int[] newThing4 = {xPos2, yPos2 - 1};
		possiblePaths.add(newThing);
		possiblePaths.add(newThing2);
		possiblePaths.add(newThing3);
		possiblePaths.add(newThing4);
		
		while(possiblePaths.size() > 0)
		{
			int selection = (int)(Math.random() * possiblePaths.size());
			int[] selectedPath = possiblePaths.remove(selection);
			boolean worked = checkRecAttackTarget(worldMap, selectedPath[0], selectedPath[1], i - 1);
			if (worked)
				return true;
		}
		
		return false;
	}
	
	private void attackingAction()
	{
		energy -= world.getEnergyUse() * .25;
		if (targetLlama == null)
		{
			currentState = State.IDLE_STATE;
			return;
		}
		if (targetLlama.getX() == xPos && targetLlama.getY() == yPos)
		{
			targetLlama.getAttacked(this);
		}
		else
		{
			xTarget = targetLlama.getX();
			yTarget = targetLlama.getY();
			moveTowardsTarget();
		}
	}
	
	private void eatAction()
	{
		if (cooldown <= 0)
		{
			world.getMap()[yPos][xPos].changeTile(Tile.TileTypes.SOIL);
			currentState = State.IDLE_STATE;
			energy += 2000;
		}
		cooldown--;
	}
	
	private void randWalkAction()
	{
		if (checkIfStunned())
			return;
		if (Math.random() < hungerDriveInd)
		{
			currentState = State.FOOD_SEEK;
			return;
		}
		if (Math.random() < violenceInd)
		{
			currentState = State.AGGRESSIVE;
			return;
		}
		
		makeRandomMove();
	}
	
	private void foodSeekAction()
	{
		if (checkIfStunned())
			return;
		Tile[][] worldMap = world.getMap();
		if (worldMap[yPos][xPos].getType() == Tile.TileTypes.FOOD)
		{
			cooldown = 5;
			currentState = State.EATING;
			return;
		}
		if (checkRecFood(worldMap, xPos, yPos, 5))
		{
			moveTowardsTarget();
		}
		else
		{
			makeRandomMove();
		}
	}
	
	private boolean checkRecFood(Tile[][] worldMap, int xPos2, int yPos2, int i)
	{
		if (i <= 0)
			return false;
		if (outOfBounds(worldMap, xPos2, yPos2))
			return false;
		if (worldMap[yPos2][xPos2].getType() == Tile.TileTypes.FOOD)
		{
			xTarget = xPos2;
			yTarget = yPos2;
			return true;
		}
		
		ArrayList<int[]> possiblePaths = new ArrayList<int[]>();
		// This looks like bad code, but arrays are just annoying in Java
		int[] newThing = {xPos2 + 1, yPos2};
		int[] newThing2 = {xPos2 - 1, yPos2};
		int[] newThing3 = {xPos2, yPos2 + 1};
		int[] newThing4 = {xPos2, yPos2 - 1};
		possiblePaths.add(newThing);
		possiblePaths.add(newThing2);
		possiblePaths.add(newThing3);
		possiblePaths.add(newThing4);
		
		while(possiblePaths.size() > 0)
		{
			int selection = (int)(Math.random() * possiblePaths.size());
			int[] selectedPath = possiblePaths.remove(selection);
			boolean worked = checkRecFood(worldMap, selectedPath[0], selectedPath[1], i - 1);
			if (worked)
				return true;
		}
		
		return false;
	}
	
	private boolean checkIfStunned()
	{
		Tile[][] worldMap = world.getMap();
		if (checkRecStunned(worldMap, xPos, yPos, 5))
		{
			currentState = State.STUNNED;
			cooldown = 10;
			return true;
		}
		return false;
	}
	
	public void makeRandomMove()
	{
		int newxPos = -1, newyPos = -1;
		
		while (outOfBounds(world.getMap(), newxPos, newyPos))
		{
			newxPos = ((int)(Math.random() * 3)) - 1 + xPos;
			newyPos = ((int)(Math.random() * 3)) - 1 + yPos;
		}
		
		xPos = newxPos;
		yPos = newyPos;
	}
	
	private boolean checkRecStunned(Tile[][] worldMap, int xPos2, int yPos2, int i)
	{
		if (i <= 0)
			return false;
		if (outOfBounds(worldMap, xPos2, yPos2))
			return false;
		
		Llama otherAtLoc = world.llamaAtLoc(xPos2, yPos2);
		if (otherAtLoc != null)
		{
			double style2 = otherAtLoc.getStylishness();
			if (Math.random() < style2 - styleInd)
				return true;
			else
				return false;
		}
		
		ArrayList<int[]> possiblePaths = new ArrayList<int[]>();
		// This looks like bad code, but arrays are just annoying in Java
		int[] newThing = {xPos2 + 1, yPos2};
		int[] newThing2 = {xPos2 - 1, yPos2};
		int[] newThing3 = {xPos2, yPos2 + 1};
		int[] newThing4 = {xPos2, yPos2 - 1};
		possiblePaths.add(newThing);
		possiblePaths.add(newThing2);
		possiblePaths.add(newThing3);
		possiblePaths.add(newThing4);
		
		while(possiblePaths.size() > 0)
		{
			int selection = (int)(Math.random() * possiblePaths.size());
			int[] selectedPath = possiblePaths.remove(selection);
			boolean worked = checkRecStunned(worldMap, selectedPath[0], selectedPath[1], i - 1);
			if (worked)
				return true;
		}
		
		return false;
	}
	
	private boolean outOfBounds(Tile[][] worldMap, int xPos2, int yPos2)
	{
		return xPos2 < 0 || yPos2 < 0 || xPos2 >= worldMap.length || yPos2 >= worldMap.length;
	}
	
	private void moveTowardsTarget()
	{
		if (xPos < xTarget)
			xPos++;
		else if (xPos > xTarget)
			xPos--;
		if (yPos < yTarget)
			yPos++;
		else if (yPos > yTarget)
			yPos--;
	}
	
	private void moveAwayFromTarget()
	{
		if (xPos < xTarget)
			xPos--;
		else if (xPos > xTarget)
			xPos++;
		if (yPos < yTarget)
			yPos--;
		else if (yPos > yTarget)
			yPos++;
		
		if (xPos < 0)
			xPos = 0;
		if (xPos >= world.getNumTiles())
			xPos = world.getNumTiles() - 1;
		if (yPos < 0)
			yPos = 0;
		if (yPos >= world.getNumTiles())
			yPos = world.getNumTiles() - 1;
	}
	
	public void getAttacked(Llama other)
	{
		energy = energy - 200;
		if (Math.random() < this.violenceInd)
		{
			currentState = State.RUNNING_AWAY;
			cooldown = 5;
        }
		else
		{
			currentState = State.ATTACKING;
		}
	}
	
	public void stopAttacking()
	{
		targetLlama = null;
		currentState = State.IDLE_STATE;
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
	
	public double getStylishness()
	{
		return styleInd;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public String parseState()
	{
		switch (currentState)
		{
			case IDLE_STATE:
				return "Idle";
			case FOOD_SEEK:
				return "Seeking Food";
			case EATING:
				return "Eating Food";
			case AGGRESSIVE:
				return "Aggressive";
			case ATTACKING:
				return "Attacking";
			case RUNNING_AWAY:
				return "Running";
			case RANDOM_WALK:
				return "Random Walk";
			case STUNNED:
				return "Stunned";
		}
		return "State not found";
	}
}
