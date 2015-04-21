
public class Gene
{
	// Representation of double stranded DNA
	private String strand1;
	private String strand2;
	private int strandLength;
	private int offspringCount;
	
	private double mutationRate;
	
	// Constructor for a random gene
	public Gene(int sl, double possibility, double mutation)
	{
		offspringCount = 4;
		
		mutationRate = mutation;
		strandLength = sl;
		
		strand1 = "";
		strand2 = "";
		
		// Randomly generates genes based on a possibility
		for (int i = 0; i < sl; i++)
		{
			if (Math.random() < possibility)
				strand1 += "1";
			else
				strand1 += "0";
			
			if (Math.random() < possibility)
				strand2 += "1";
			else
				strand2 += "0";
		}
	}
	
	// Constructor for a pre-determined gene
	public Gene(String s1, String s2, double mutation)
	{
		offspringCount = 4;
		mutationRate = mutation;
		strandLength = s1.length();
		strand1 = s1;
		strand2 = s2;
	}
	
	// Provides an accessor for strand info
	public String toString()
	{
		return strand1 + strand2;
	}
	
	public String getStrand1()
	{
		return strand1;
	}
	
	public String getStrand2()
	{
		return strand2;
	}
	

	// Mutates and crosses over the gene
	public void Meiosis()
	{
		String newStrand1 = "";
		String newStrand2 = "";
		// Create a different temp newStrand1 because Strings use OOP and are copied by reference
		String newStrand1temp = "";
		
		for (int i = 0; i < strandLength; i++)
		{
			// Mutates strand 1 at a point if necessary
			if (Math.random() < mutationRate)
				newStrand1 += (int)(Math.random() * 2);
			else
				newStrand1 += strand1.charAt(i);
			
			// Mutates strand 2 at a point if necessary
			if (Math.random() < mutationRate)
				newStrand2 += (int)(Math.random() * 2);
			else
				newStrand2 += strand2.charAt(i);
			
			// Builds newStrand1temp
			newStrand1temp += newStrand1.charAt(i);
		}
		
		// Perform crossing over of strands
		int crossoverIndex = (int)(Math.random() * (strandLength + 1));
		newStrand1 = newStrand1.substring(0, crossoverIndex) + newStrand2.substring(crossoverIndex);
		newStrand2 = newStrand2.substring(0, crossoverIndex) + newStrand1temp.substring(crossoverIndex);
		
		strand1 = newStrand1;
		strand2 = newStrand2;
	}
	
	// Combines two genes together
	public Gene[] CombineWith(Gene other)
	{
		// Gets the strands from other gene
		String strand1other = other.toString().substring(0, strandLength);
		String strand2other = other.toString().substring(strandLength);
		
		Gene[] toReturn = new Gene[offspringCount];
		
		for (int i = 0; i < offspringCount; i++)
		{
			String rand1, rand2;

			// Creates a punnett square and selects one of the possibilities
			if (Math.random() < 0.5)
				rand1 = strand1other;
			else
				rand1 = strand2other;

			if (Math.random() < 0.5)
				rand2 = strand1;
			else
				rand2 = strand2;
			
			toReturn[i] = new Gene(rand1, rand2, mutationRate);
		}
		
		return toReturn;
	}
	
	// Gets the percent activation of a gene
	public double getActivation()
	{
		int totalLength = strand1.length() * 2;
		int activeNucleotides = 0;
		
		for (int i = 0; i < strand1.length(); i++)
		{
			if (strand1.charAt(i) == '1')
				activeNucleotides++;
			if (strand2.charAt(i) == '1')
				activeNucleotides++;
		}
		
		return ((double) activeNucleotides) / totalLength;
	}
	
	// Changes the number of offspring to some non-standard value
	public void changeOffspringCount(int x)
	{
		offspringCount = x;
	}
}
