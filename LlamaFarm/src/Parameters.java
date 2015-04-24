import java.awt.Color;
import java.io.*;

public class Parameters
{
	// Write experiment notes here to debug
	public static String experiment_notes = "All values started at 0.1, few trees, stylishness affect diminished";
	
	// Window Settings
	public static int window_size = 1000; // Width and height of the game window. Recommend to keep constant
	public static int frames_per_second = -1; // FPS of simulation. Set to negative value to remove frame limiter
	
	// World Instantiation Settings
	// The below 3 should be in constant increasing order
	public static double world_arability = 0.1; // Probability of a spot on the world getting food = arability
	public static double world_obsrate = 0.3; // Probability of a spot on the world being colored alternatively = obsrate - arability
	public static double world_hazrate = 0.31; // Probability of a spot on the world being dangerous = hazrate - obsrate
	
	public static double energy_loss = 10; // Energy lost every turn
	
	// Simulation Settings
	public static int num_tiles = 125; // Number of tiles in world width and height. Should be a factor of window_size
	public static int num_llamas = 1000; // Number of llamas in world. Should be a multiple of 4
	public static int num_generations = 2000; // Maximum number of generations
	public static boolean debug = true; // Debug?
	public static boolean debug_to_cmd = true; // Print stats to Console?
	public static boolean debug_to_csv = true; // Print stats to CSV file?
	
	// Genetics Settings
	public static double mutation_rate = 0.0; // Rate of mutation
	public static int genome_length = 1000; // Length of each gene
	
	// Initial Genome Settings
	public static double hunger_init = 0.1; // Initial hunger probability
	public static double lazy_init = 0.1; // Initial laziness probability
	public static double violent_init = 0.1; // Initial violence probability
	public static double meta_init = 0.1; // Initial metabolism probability
	public static double style_init = 0.1; // Initial style probability
	
	// Color Palettes
	public static Color[] style_colors = // Light pink -> dark purple values representing style
	{
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
	
	public static Color[] tile_colors = // Colors for the tiles
	{
		new Color(0x795548),
		new Color(0x00E676),
		new Color(0xFF3D00),
		new Color(0x424242)
	};
	
	// Llama Behaviour Settings
	public static double energy_init = 1000; // Initial energy
	public static double idle_regain = 2.5; // How much energy is saved while idle
	public static double aggressive_loss = 2.5; // How much energy is lost when aggressive
	public static int search_radius = 5; // How far to search when pathfinding
	public static double food_regain = 2000; // How much energy is regained by eating
	public static double attack_loss = 200; // How much energy is lost when attacked
	// Increase this to increase the chance of a llama being stunned when faced with a more stylish llama
	public static double stylishness_multiplicity = 1;
	
	// Llama Timer Settings
	public static int run_cooldown = 5; // How long to run away from a foe
	public static int stun_timer = 10; // How long to stay stunned
	public static int eating_timer = 5; // How long to spend eating
	
	// Prints all parameters to a file
	public static void print_params(long time)
	{
		PrintWriter out = null;
		try
		{
			out = new PrintWriter(new FileWriter(new File("Experiment_" + time + "_info.txt")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		// Have to do this manually since everything in parameters is static
		out.println("Experiment at time " + time);
		out.println("------------------------------");
		out.println("experiment_notes: " + experiment_notes);
		out.println("window_size: " + window_size);
		out.println("frames_per_second: " + frames_per_second);
		out.println("world_arability: " + world_arability);
		out.println("world_obsrate: " + world_obsrate);
		out.println("world_hazrate: " + world_hazrate);
		out.println("energy_loss: " + energy_loss);
		out.println("num_tiles: " + num_tiles);
		out.println("num_llamas: " + num_llamas);
		out.println("num_generations: " + num_generations);
		out.println("mutation_rate: " + mutation_rate);
		out.println("genome_length: " + genome_length);
		out.println("hunger_init: " + hunger_init);
		out.println("lazy_init: " + lazy_init);
		out.println("violent_init: " + violent_init);
		out.println("meta_init: " + meta_init);
		out.println("style_init: " + style_init);
		out.println("energy_init: " + energy_init);
		out.println("idle_regain: " + idle_regain);
		out.println("aggressive_loss: " + aggressive_loss);
		out.println("search_radius: " + search_radius);
		out.println("food_regain: " + food_regain);
		out.println("attack_loss: " + attack_loss);
		out.println("stylishness_multiplicity: " + stylishness_multiplicity);
		out.println("run_cooldown: " + run_cooldown);
		out.println("stun_timer: " + stun_timer);
		out.println("eating_timer: " + eating_timer);
		out.flush();
		out.close();
	}
}
