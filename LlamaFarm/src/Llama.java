import java.awt.*;

public class Llama
{
	private GameWorld world;
	private Gene[] genome;
	public int energy;
	
	public enum States { IDLE_STATE, FOOD_SEEK, EATING, AGGRESSIVE, ATTACKING, RUNNING_AWAY, RANDOM_WALK }
	
	public Llama(GameWorld gw, Gene[] g)
	{
		world = gw;
		genome = g;
	}
	
	public void RenderLlama(Graphics img)
	{
		
	}
	
	public Llama BreedWith(Llama other)
	{
		return null;
	}
	
	public void Action(int targetX, int targetY)
	{
		
	}
	
	public Gene[] getGenome()
	{
		return genome;
	}
}
