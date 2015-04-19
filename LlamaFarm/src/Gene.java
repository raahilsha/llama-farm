
public class Gene
{
	private String strand1;
	private String strand2;
	
	private float mutationRate;
	
	public Gene(float possibility, float mutation)
	{
		mutationRate = mutation;
	}

	public Gene Meiosis()
	{
		return null;
	}
	
	public Gene[] CrossOver(Gene other)
	{
		return null;
	}
}
