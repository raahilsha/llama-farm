import java.awt.*;
import java.util.*;

public class GameWorld
{
	private ArrayList<Llama> llamas;
	private boolean isRunningSimulation;
	private Tile[][] worldTiles;
	private int tileSize;
	
	private float arability;
	private float energyDrain;
	
	// Constructor for GameWorld
	public GameWorld()
	{
		
	}
	
	// Runs every frame and decides to create new generation if necessary
	public void Tick()
	{
		if (isRunningSimulation)
		{
			
		}
		else
		{
			CreateNewGeneration(); 
			isRunningSimulation = true;
		}
	}
	
	
	// Takes the arraylist of llamas and creates a brand new generation
	public void CreateNewGeneration()
	{
		
	}
	
	// Renders the tiles and llamas onto the game panel
	public void RenderWorld(Graphics img)
	{
		
	}
}