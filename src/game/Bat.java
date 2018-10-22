package game;

/**
 * This is the Bat class which inherits the Enemy class.
 * It sets the attributes for the bat type enemy.
 * @author atang 15909204
 */
public class Bat extends Enemy
{
	public Bat()
	{
		health = 100;
		gold = (int)(Math.random() * 210) + 80;
		name = "Bat";
		resistance = 1.5;
		damageMin = 1;
		damageMax = 10;
		presence = "Flappity flap is what you hear...\nA bat flies towards you!";
		weakDamage = "screeched at you";
		mediumDamage = "bit you";
		strongDamage = "slapped you";
		boss = false;
	}
}
