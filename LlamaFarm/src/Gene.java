
public class Gene
{
	// Representation of double stranded DNA
	private String strand1;
	private String strand2;
	
	private float mutationRate;
	
	// Constructor for a gene
	public Gene(float possibility, float mutation)
	{
		mutationRate = mutation;
	}

	// Mutates and crosses over the gene
	public Gene Meiosis()
	{
		return null;
	}
	
	// Combines two genes together
	public Gene[] CombineWith(Gene other)
	{
		return null;
	}
}
