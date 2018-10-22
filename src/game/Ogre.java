package game;

/**
 * This is the Ogre class which inherits the Enemy class.
 * It sets the attributes for the ogre type enemy.
 * This enemy is the final boss for the game.
 * @author atang 15909204
 */
public class Ogre extends Enemy
{
	public Ogre()
	{
		health = 100;
		gold =  (int)(Math.random() * 1300) + 700;
		name = "Ogre";
		resistance = 0.2;
		damageMin = 1;
		damageMax = 20;
		presence = "The ground rumbles violently.\nAn Ogre says hi!";
		weakDamage = "poked you";
		mediumDamage = "punched you";
		strongDamage = "kicked you";
		boss = true;
	}
	
}
