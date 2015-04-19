import java.awt.*;

public class Llama
{
	private GameWorld world;
	private Gene[] genome;
	public int energy;
	
	// All possible states a llama can obtain
	// Note that NIRVANA is not among these
	public enum States { IDLE_STATE, FOOD_SEEK, EATING, AGGRESSIVE, ATTACKING, RUNNING_AWAY, RANDOM_WALK }
	
	// Constructor for llama
	public Llama(GameWorld gw, Gene[] g)
	{
		world = gw;
		genome = g;
	}
	
	// Renders Llama onto game panel
	public void RenderLlama(Graphics img)
	{
		
	}
	
	// Breeds one llama with other
	public Llama BreedWith(Llama other)
	{
		return null;
	}
	
	// Runs the Finite State Machine of Llama 
	public void Action(int targetX, int targetY)
	{
		
	}
	
	// Accessor for genome; used when mating
	public Gene[] getGenome()
	{
		return genome;
	}
}