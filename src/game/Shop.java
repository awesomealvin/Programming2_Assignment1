package game;

import java.text.NumberFormat;

/**
 * This class is for the shop features in the game
 * such as for the rope and the bandages.
 * @author atang 15909204
 */

public class Shop
{
	private NumberFormat goldF = NumberFormat.getCurrencyInstance();
	
	private boolean rope;
	private final int ROPE_PRICE = 2500;
	private final int BANDAGE_PRICE = 200;
	
	public Shop()
	{
		rope = true;
	}
	
	/**
	 * Prints the items that the
	 * vendor is currently selling.
	 * @author atang 15909204
	 */
	public void printItems()
	{
		System.out.println("The vendor currently sells:");
		System.out.println("- Bandages for " + goldF.format(BANDAGE_PRICE)+" each");
		if (rope)
		{
			System.out.println("- Rope for " +goldF.format(ROPE_PRICE));
		}
		
	}
	
	/**
	 * This displays the initial message when the player enters
	 * the shop.
	 * @author atang 15909204
	 */
	public void vendorChat()
	{
		//System.out.println("Welcome to the vendor's shop!");
		System.out.println("Don't worry about monsters, it's completely safe here!");
		System.out.println("Enter \"help\" to view shop commands!");
	}
	
	/**
	 * Displays a message when the player buys a rope,
	 * and also sets the rope stock to false, so
	 * the rope cannot be bought again.
	 * @return returns true to set the players' rope.
	 */
	public boolean boughtRope()
	{
		rope = false;
		System.out.println("You have bought a rope!");
		return true;
	}
	/**
	 * @return returns the rope price to deduct
	 * the money from the player
	 * @author atang 15909204
	 */
	public int getRopePrice()
	{
		return ROPE_PRICE;
	}
	
	/**
	 * @return returns the bandage price to deduct
	 * the money from the player
	 * @author atang 15909204
	 */
	public int getBandagePrice()
	{
		return BANDAGE_PRICE;
	}	
	
}
