package game;

import java.text.NumberFormat;
import java.util.Random;

/**
 * This class is the base enemy class for the different
 * enemy types which are in subclasses.
 * @author atang 15909204
 */
public class Enemy
{
	protected boolean boss; // Whether mob type is enemy or not
	protected int health; // Amount of health
	protected int gold; // Amount of gold they carry
	protected int damageMax; // Maximum damage they can do
	protected int damageMin;// Minimum damage they can do
	protected double resistance; // How "tanky" the mob is. (The lower, the tankier).
	protected String name; // Name of mob
	protected String presence; // Presence message
	protected static NumberFormat healthF = NumberFormat.getPercentInstance(); // Format health to percentage
	protected String weakDamage; // Damage type when they deal low damage 
	protected String mediumDamage; // ^^ When they deal medium damage
	protected String strongDamage; // ^^ When they deal high damage
	
	/**
	 * Constructor doesn't need anything, as 
	 * variety of mob types have different stats to
	 * each other.
	 * @author atang 15909204
	 */
	Enemy()
	{
	
	}
	

	/**
	 * Checks if the enemy is a boss, and if it's dead or not.
	 * This method is for the final boss battle to escape.
	 * @return true of false, if the boss is dead.
	 * @author atang 15909204
	 */
	public boolean bossIsDead()
	{
		if (health <= 0 && boss)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the enemy is dead
	 * @return a boolean so the game knows when to stop
	 * the battle.
	 * @author atang 15909204
	 */
	public boolean isDead()
	{
		return (health <= 0)?(true):(false);
	}
	
	/**
	 * Used when the enemy takes damage.
	 * @param damageInput This is the damage input
	 * that the enemy takes.
	 * @author atang 15909204
	 */
	public void loseHealth(int damageInput)
	{
		health -=( damageInput * resistance);

		health = (health < 0)?(0):(health);
	}
	
	/**
	 * Prints out the enemy's health when attacked.
	 * Or if they've taken the killing blow.
	 * @author atang 15909204
	 */
	public void displayHealth()
	{
		if (health <= 0)
		{
			System.out.println(name+" has been defeated!");
		}
		else
		{
			System.out.println(name+"'s Current Health: "+healthF.format((double)health/100));
		}
	}
	
	/**
	 * Drops gold that is given to the player
	 * @return returns the amount of gold
	 * @author atang 15909204
	 */
	public int dropGold()
	{
		return gold;
	}
	
	/**
	 * Generates the output damage of the enemy
	 * @return returns the damage value
	 * @author atang 15909204
	 */
	public int getDamage()
	{
		Random rand = new Random();
		
		int damage = rand.nextInt(damageMax - damageMin) + damageMin;
		displayDamageType(damage);
		return damage;
	}
	
	/**
	 * Prints out the presence message of the enemy
	 * @author atang 15909204
	 */
	public void displayEnemyPresence()
	{
		System.out.println(presence);
		System.out.println("Prepare for battle!");
	}

	/**
	 * Gets the "tankiness" of the enemy to lower the 
	 * output damage of the player
	 * @return returns the resistance as a double value.
	 * @author atang 15909204
	 */
	public double getResistance()
	{
		return resistance;
	}
	
	/**
	 * Gets the name of the enemy type.
	 * @return returns the name as a string.
	 * @author atang 15909204
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Displays the damage type that the enemy has given.
	 * This makes the combat less boring with a variety
	 * of different damage types.
	 * @param damage The randomly generated damage
	 * from the getDamage class.
	 * @author atang 15909204.
	 */
	public void displayDamageType(int damage)
	{
		if (damage <= (damageMax * 0.25) + damageMin)
		{
			System.out.println(name+" has "+weakDamage+" for " +healthF.format((double)damage/100)+" of your max health.");
		}
		else if (damage <= (damageMax * 0.75) + damageMin)
		{
			System.out.println(name+" has "+mediumDamage+" for " +healthF.format((double)damage/100)+" of your max health.");
		}
		else
		{
			System.out.println(name+" has "+strongDamage+" for " +healthF.format((double)damage/100)+" of your max health.");
		}
	}

}


