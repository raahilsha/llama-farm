import java.awt.Color;


public class Parameters
{
	// Window Settings
	public static int window_size = 1000;
	public static int frames_per_second = -1;
	
	// World Instantiation Settings
	public static double world_arability = 0.001;
	public static double world_obsrate = 0.3;
	public static double world_hazrate = 0.31;
	public static double energy_loss = 10;
	
	// Simulation Settings
	public static int num_tiles = 125;
	public static int num_llamas = 1000;
	public static boolean debug = true;
	public static boolean debug_to_cmd = true;
	
	// Genetics Settings
	public static double mutation_rate = 0.01;
	public static int genome_length = 100;
	
	// Initial Genome Settings
	public static double hunger_init = 0.01;
	public static double lazy_init = 0.0;
	public static double violent_init = 0.0;
	public static double meta_init = 0.5;
	public static double style_init = 0.0;
	
	// Color Palettes
	public static Color[] style_colors = {
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
	
	public static Color[] tile_colors = {
		new Color(0x795548),
		new Color(0x00E676),
		new Color(0xFF3D00),
		new Color(0x424242)
	};
	
	// Llama Behaviour Settings
	public static double energy_init = 1000;
	public static double idle_regain = 2.5;
	public static double aggressive_loss = 2.5;
	public static int search_radius = 5;
	public static double food_regain = 2000;
	public static double attack_loss = 200;
	public static double stylishness_multiplicity = 10;
	
	// Llama Timer Settings
	public static int run_cooldown = 5;
	public static int stun_timer = 10;
	public static int eating_timer = 5;
}
