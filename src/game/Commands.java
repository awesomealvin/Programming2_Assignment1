package game;

/**
 * This class includes all the possible starting commands that the
 * game will scan, and also displays the available commands
 * in the current mode the player is in.
 * @author atang 15909204
 */
public class Commands
{
	public static final String OPEN = "open";
	public static final String PICKUP = "pickup";
	public static final String SEARCH = "search";
	public static final String ESCAPE = "escape";
	public static final String STATS = "stats";
	public static final String YELL = "yell";
	
	public static final String HINT = "hints";
	
	public static final String HEAL = "heal";
	
	public static final String ATTACK = "attack";
	public static final String RUN = "run";
	public static final String HELP = "help";
	
	public static final String SHOP = "shop";
	public static final String BUY = "buy";
	
	private static final String[] hints = {"The \"help\" command shows ALL available commands; they may be useful!",
			"Looks like you'll need climbing equipment to go up that hole!",
			"Completing an action can be pretty risky...",
			"If you're low on health, healing is possible!",
			"Check your inventory, you may have starting equipment!",
			"Not dealing enough damage? There may be a sword lying around somewhere!",
			"Maybe farming is the best option for wealth...",
			"There is a shop that may sell useful items!",
			"Careful! There seems to be a large creature that doesn't want anyone escaping!"};
	private static int hintsIndex;

	
	
	
	Commands()
	{
		hintsIndex = 0;
	}
	
	/**
	 * Displays the next hint whenever called by the player.
	 * @author atang 15909204
	 */
	public void displayHints()
	{
		System.out.println(hints[hintsIndex]);
		++hintsIndex;
		
		hintsIndex = (hintsIndex >= hints.length)?0:hintsIndex;
	}
	
	/**
	 * Displays the availabe commands in
	 * battle mode.
	 * @author atang 15909204
	 */
	public void displayBattleCommands()
	{
		System.out.println("Available Commands:");
		System.out.println("attack|--|Attack your opponent");
		System.out.println("  heal|--|Attempt to heal yourself");
		System.out.println("   run|--|Attempt to run away from the current battle");
		System.out.println("  help|--|Displays current available commands");
	}
	
	/**
	 * Displays the availabe commands in
	 * explore mode.
	 * @author atang 15909204
	 */
	public void displayExploreCommands()
	{
		System.out.println("Available Commands:");
		System.out.println("    open [n]|--|Opens door labeled n");
		System.out.println("     search |--|Search room for items");
		System.out.println("pickup[item]|--|Pickup an item labeled item");
		System.out.println("        heal|--|Attempt to heal yourself");
		System.out.println("       stats|--|Display your current stats and inventory");
		System.out.println("        yell|--|Force a fight with an enemy");
		System.out.println("      escape|--|Attempt to escape the dungeon");
		System.out.println("       hints|--|Show next hint");
		System.out.println("        help|--|Displays current available commands");
	}
	
	/**
	 * Displays the availabe commands in
	 * shop mode.
	 * @author atang 15909204
	 */
	public void displayShopCommands()
	{
		System.out.println("Available Commands:");
		System.out.println("         shop|--|Display available items from the vendor");
		System.out.println("   buy [item]|--|Purchase labeled item from the vendor");
		System.out.println("        stats|--|Display your current stats and inventory");
		System.out.println("         heal|--|Attempt to heal yourself");
		System.out.println("       search|--|Search room for doors and items");
		System.out.println("pickup [item]|--|Pickup an item labeled item");
		System.out.println("     open [n]|--|Opens door labeled n");
		System.out.println("       escape|--|Attempt to escape the dungeon");
		System.out.println("        hints|--|Show next hint");
		System.out.println("         help|--|Displays current available commands");
	}
	
}
