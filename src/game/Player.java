package game;

import java.text.NumberFormat;

/**
 * This is the class the sets the player attributes, 
 * as well as methods to change the attributes 
 * during gameplay.
 * @author atang 15909204
 */
public class Player
{
	private int gold; // current gold amount
	private int health; // current health
	private boolean dagger; // if player has dagger or not
	private boolean rope; // if player has rope or not
	private int position; // current player room
	private int killCount; // kill count
	private int totalGoldGained; // total amount of gold gained
	private int bandages; // amount of bandages
	
	private NumberFormat healthF = NumberFormat.getPercentInstance();
	private NumberFormat goldF = NumberFormat.getCurrencyInstance();
	
	Player()
	{
		gold = 0;
		health = 100;
		dagger = false;
		rope = false;
		position = 0;
		killCount = 0;
		totalGoldGained = 0;
		bandages = 1;
	}
	
	/**
	 * Adds gold to the player, as well as totaling amount
	 * of gold.
	 * Also calls the method to display their current gold.
	 * @param goldDrop
	 * @author atang 15909204
	 */
	public void getGold(int goldDrop)
	{
		gold += goldDrop;
		totalGoldGained += goldDrop;
		System.out.println("You have obtained "+goldF.format(goldDrop));
		displayCurrentGold();
	}
	
	/**
	 * Displays the current player's gold
	 * @author atang 15909204
	 */
	public void displayCurrentGold()
	{
		System.out.println("Your current bank: "+goldF.format(gold));
	}
	
	/**
	 * Sets the player's dagger boolean and
	 * prints messages.
	 * @author atang 15909204
	 */
	public void getDagger()
	{
		dagger = true;
		System.out.println("You have obtained a very cool sword!");
		System.out.println("You will now deal bonus damage!");
	}
	
	/**
	 * Checks if player has a dagger or not
	 * @return returns true or false
	 * @author atang 15909204
	 */
	public boolean hasDagger()
	{
		return (dagger)?true:false;
	}
	
	/**
	 * Checks if player has a rope.
	 * @return returns true of false so
	 * the game can decide if they have the
	 * ability to escape.
	 * @author atang 15909204
	 */
	public boolean hasRope()
	{
		return (rope)?true:false;
	}
	
	/**
	 * Sets the player's rope boolean to true
	 * when they pickup a rope.
	 * @author atang 15909204
	 */
	public void getRope()
	{
		rope = true;
	}
	
	/**
	 * Deducts gold from the player when called
	 * @param amount The amount to deduct their gold from
	 * @author atang 15909204
	 */
	public void loseGold(int amount)
	{
		gold -= amount;
		displayCurrentGold();
	}
	
	/**
	 * Method to change the position of the player
	 * @param room The room to change the position to
	 * @return returns the position to the main.
	 * @author atang 15909204
	 */
	public int changePosition(int room)
	{
		position = room - 1;
		return position;
	}
	
	/**
	 * @return Returns the player position
	 * @author atang 15909204
	 */
	public int currentPosition()
	{
		return position;
	}
	
	/**
	 * Checks if the player is dead or not
	 * @return returns true when dead, false when still alive.
	 * @author atang 15909204
	 */
	public boolean isDead()
	{
		return (health <=0)?true:false;
	}
	
	/**
	 * Deducts the health from the player
	 * @param damage The damage to deduct the health from
	 * @author atang 15909204
	 */
	public void loseHealth(int damage)
	{
		health -= damage;
		health = (health < 0)?0:health;
	}
	
	/**
	 * Displays the current health of the player.
	 * @author atang 15909204
	 */
	public void displayHealth()
	{
		System.out.println("Current Player Health: "+healthF.format((double)health/100));
	}
	
	/**
	 * Adds health to the player
	 * @param healAmount The amount to heal the player.
	 * @author atang 15909204
	 */
	public void getHealth(int healAmount)
	{
		health += healAmount;
		System.out.println("You have healed "+healthF.format((double)healAmount/100)+" of your max health!");
		if (health > 100)
		{
			health = 100;
		}
		displayHealth();
	}
	
	 /**
	  * @return returns the player's current gold
	  * @author atang 15909204
	  */
	public int currentGold()
	{
		return gold;
	}
	
	/**
	 * Manages the inventory text for the stats command
	 * @return returns the formatted string of the inventory
	 * @author atang 15909204
	 */
	public String currentItems()
	{
		String hasDagger = "";
		String hasRope = "";
		String hasBandages = "";
		
		hasDagger += (dagger)?("Sword, "):("");
		hasRope += (rope)?("Rope, "):("");
		hasBandages += (hasBandages())?("Bandages x"+bandages+", "):("");
		
		String complete = hasDagger+hasBandages+hasRope;
		
		if (dagger || rope || hasBandages())
		{
			char charArray[] = complete.toCharArray();
			
			charArray[complete.length() - 2] = 0;
			
			String finish = String.valueOf(charArray);
			
			return finish;
		}
		
		return complete;
					
	}
	
	/**
	 * Prints out the current stats
	 * of the player.
	 * @author atang 15909204
	 */
	public void displayStats()
	{
		System.out.println("Room: " + (position + 1));
		System.out.println("Health: "+healthF.format((double)health/100));
		System.out.println("Cash: "+goldF.format(gold));
		System.out.println("Inventory: "+currentItems());
		System.out.println("Kill Count: "+killCount);
	}
	
	/**
	 * Prints the total amount of gold the
	 * player has gained
	 * @author atang 15909204
	 */
	public void displayTotalGold()
	{
		System.out.println("Total Cash Gained: "+goldF.format(totalGoldGained));
	}
	
	/**
	 * Increments the kill count when called
	 * @author atang 15909204
	 */
	public void addKill()
	{
		killCount++;
	}
	
	/**
	 * Adds a bandage to the int value
	 * and displays the message
	 * @author atang 15909204
	 */
	public void obtainBandage()
	{
		System.out.println("You have obtained a bandage!");
		bandages++;
	}
	
	/**
	 * Heals the player when a bandage is 
	 * used, and also displays the messages.
	 * @author atang 15909204
	 */
	public void useBandage()
	{
		if (hasBandages())
		{
			System.out.println("You used a bandage.");
			getHealth(50);
			bandages--;
		}
		else
		{
			System.out.println("You can only heal if you have bandages!");
		}
		
	}
	
	/**
	 * Checks if the player has bandages.
	 * @return returns true if they do, false if not.
	 * @author atang 15909204
	 */
	public boolean hasBandages()
	{
		return (bandages > 0)?true:false;
	}
	
	
}
