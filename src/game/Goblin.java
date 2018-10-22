package game;

/**
 * This is the Goblin class which inherits the Enemy class.
 * It sets the attributes for the goblin type enemy.
 * @author atang 15909204
 */
public class Goblin extends Enemy
{
	public Goblin()
	{
		health = 100;
		gold = (int)(Math.random() * 420) + 250;
		name = "Goblin";
		resistance = 1;
		damageMin = 5;
		damageMax = 15;
		presence = "You hear the sound of footsteps growing louder...\nA goblin rushes towards you!";
		weakDamage = "tickled you";
		mediumDamage = "punched you";
		strongDamage = "kicked you in the groin";
		boss = false;
	}

}
