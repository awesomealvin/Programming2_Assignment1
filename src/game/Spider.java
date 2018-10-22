package game;


/**
 * This is the Spider class which inherits the Enemy class.
 * It sets the attributes for the spider type enemy.
 * @author atang 15909204
 */
public class Spider extends Enemy
{

	public Spider()
	{
		health = 100;
		gold = (int)(Math.random() * 500) + 350;
		name = "Giant Spider";
		resistance = 0.6;
		damageMin = 5;
		damageMax = 25;
		presence = "The ground feels sticky beneath you...\nA Giant Spider drops in front of you!";
		weakDamage = "shot a web at you";
		mediumDamage = "pinched you";
		strongDamage = "stung you";
	}
}
