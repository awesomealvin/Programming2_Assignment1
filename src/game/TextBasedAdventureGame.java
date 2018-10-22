package game;

import java.util.Scanner;

import java.util.Random;
import java.text.NumberFormat;
import java.io.File;
import java.io.IOException;

/**
 * This class is the core of the game which
 * includes the main method.
 * @author atang 15909204
 */

public class TextBasedAdventureGame
{
	public static int[][] roomLayout; 
	public static String[] descriptions;
	public static boolean[] dagger;
	public static boolean[] gold;
	public static int[] goldAmount;
	public static NumberFormat healthF = NumberFormat.getPercentInstance();
	public static NumberFormat goldF = NumberFormat.getCurrencyInstance();
	public static Player player = new Player();
	public static Commands command = new Commands();
	public static Shop shop = new Shop();
	public static boolean bossKill = false;
	
	public static Enemy enemy;

	/**
	 * This method checks if an item is true in the roomIndex
	 * 
	 * @param line is the string containing items
	 * 
	 * @param i is the roomIndex value
	 * 
	 * @author atang 15909204
	 *
	 */
	public static void itemCheck(String line, int i)
	{
		dagger[i] = (line.contains("dagger")) ? (true) : (false);
		gold[i] = (line.contains("gold")) ? (true) : (false);
	}

	/**
	 * This method returns the roomIndex value for roomLayout[].
	 * 
	 * @param line is the string containing the connected rooms
	 * 
	 * @author atang 15909204
	 */
	public static int[] numbers(String line)
	{
		String temp[] = line.split(",");
		int[] num = new int[temp.length];

		for (int i = 0; i < temp.length; ++i)
		{
			num[i] = new Integer(temp[i]);
		}

		return num;
	}

	public static void printLine()
	{
		System.out.println("========================================================================");
	}

	/**
	 * This method scans the gamemap.txt file and initializes the roomLayout
	 * array.
	 * 
	 * @author atang 15909204
	 */
	public static void readGameMap() throws IOException
	{
		int numberOfRooms;
		Scanner scanFile = new Scanner(new File("input/gamemap.txt"));

		numberOfRooms = new Integer(scanFile.nextLine());
		roomLayout = new int[numberOfRooms][];
		descriptions = new String[numberOfRooms];
		dagger = new boolean[numberOfRooms];
		gold = new boolean[numberOfRooms];
		goldAmount = new int[numberOfRooms];
		
		int roomIndex;

		while (scanFile.hasNextLine())
		{
			roomIndex = new Integer(scanFile.nextLine()) - 1;
			scanFile.nextLine(); // @description line
			descriptions[roomIndex] = scanFile.nextLine();
			scanFile.nextLine(); // @connect line
			roomLayout[roomIndex] = numbers(scanFile.nextLine());
			scanFile.nextLine(); // @items line
			itemCheck(scanFile.nextLine(), roomIndex);
		}
		scanFile.close();
		System.out.println("Initialising Game Map -- number of rooms: " + numberOfRooms);
		System.out.println("Type and enter \"help\" for a list of available commands.");
	}

	/**
	 * Prints the room description of the player's current room
	 * @param roomNum Room number of the player's current positon.
	 * @author atang 15909204
	 */
	public static void printRoomDescription(int roomNum)
	{
		System.out.println(descriptions[roomNum]);
	}

	/**
	 * Returns a tokenized string array of the user's command
	 * to be used for comparing the commands.
	 * @return token a string array.
	 */
	public static String[] tokenize()
	{
		System.out.print("Command: ");
		Scanner scan = new Scanner(System.in);
		String[] token = scan.nextLine().split(" ");

		return token;
	}

	/**
	 * Generates the player damage while checking
	 * if they have a dagger equipped or not.
	 * @return damage the randomized damage
	 * @author atang 15909204
	 */
	public static int generatePlayerDamage()
	{
		Random rand = new Random();
		int damage = 0;

		if (player.hasDagger())
		{
			damage = rand.nextInt(26) + 50;
			
		} else
		{
			damage = rand.nextInt(51) + 10;
	
		}
		return damage;
	}
	
	/**
	 * Selects a description for the player's damage type
	 * when they attack the enemy based on their damage value
	 * @param damage The generated damage
	 * @return returns the damage type to a string
	 * @author atang 15909204
	 */
	public static String chooseDamageType(int damage)
	{
		if (player.hasDagger())
		{
			if (damage < (26 * 0.25) + 50)
			{
				return "cut";
			}
			else if (damage < (26 * 0.75) + 50)
			{
				return "slash";
			}
			else
			{
				return "lunge towards";
			}
		}
		else
		{
			if (damage < (51 * 0.25) + 10)
			{
				return "slap";
			}
			else if (damage < (51 * 0.75) + 10)
			{
				return "punch";
			}
			else
			{
				return "kick";
			}
		}
	}

	/**
	 * Prints out the damage type of players' damage value
	 * and the amount of damage they did to the opponent.
	 * @param damage The players' generated damage value.
	 * @author atang 15909204
	 */
	public static void outputPlayerDamage(int damage)
	{
		String attackType = chooseDamageType(damage);
		
		if (player.hasDagger())
		{
			System.out.println("You "+attackType+" your opponent with your sword for " + healthF.format((double) (damage * enemy.getResistance()) / 100)
			+ " of their max health.");
		}
		else
		{
			System.out.println(
					"You "+attackType+" your opponent for " + healthF.format((double) (damage * enemy.getResistance()) / 100) + " of their max health.");
		}		
	}

	/**
	 * Gets the damage from the randomly generated damage
	 * of one of the enemy classes
	 * @return returns the damage generated from the class
	 * to be used as reference for other methods.
	 * @author atang 15909204
	 */
	public static int generateEnemyDamage()
	{
		int damage = enemy.getDamage();

		return damage;
	}

	/**
	 * This method controls the damage done to to the enemy.
	 * @author atang 15909204
	 */
	public static void attackEnemy()
	{
		int damage;
		damage = generatePlayerDamage();
		outputPlayerDamage(damage);
		enemy.loseHealth(damage);
		enemy.displayHealth();
		enterContinue();
	}

	/**
	 * This method controls the damage done
	 * to the player by the enemy.
	 * @author atang 15909204
	 */
	public static void attackPlayer()
	{
		int damage = generateEnemyDamage();
		player.loseHealth(damage);
		player.displayHealth();
	}
	
	/**
	 * Tells the user to press enter to continue,
	 * then scans for input.
	 * This gives it a more turn-based combat
	 * feeling when fighting and enemy.
	 * @author atang 15909204
	 */
	public static void enterContinue()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("--------------------------");
		System.out.println("Press ENTER to continue...");
		System.out.print("--------------------------");
		scan.nextLine();	
	}
	
	/**
	 * Prints a skinny line to format the output
	 * @author atang 15909204
	 */
	public static void printSemiBreak()
	{		
		System.out.println("------------------------------------------------------------------------");
	}
	
	/**
	 * Generates the run chance when player tries
	 * to run.
	 * @return returns true or false, meaning if the
	 * run was successful or not.
	 * @author atang 15909204
	 */
	public static boolean generateRunChance()
	{		
		return (Math.random() < 0.4)?true:false;		
	}
	
	/**
	 * Prints command error
	 * @author atang 15909204
	 */
	public static void displayCommandError()
	{
		System.out.println("Unrecognized Command");
	}
	
	/**
	 * Prints out the enemy type encounter message
	 * when a battle is to initiate
	 * @author atang 15909204
	 */
	public static void displayIncomingEnemy()
	{
		enemy.displayEnemyPresence();
	}
	
	/**
	 * This method is used when the player has successfully
	 * ran away. This will randomly choose an available room
	 * that they player runs to.
	 * @author atang 15909204
	 */
	public static void runToRoom()
	{
		int ran = (int) (Math.random() * roomLayout[player.currentPosition()].length);
		
		int room = roomLayout[player.currentPosition()][ran];
		
		System.out.println("You have successfully ran away to room "+room);
		
		player.changePosition(room);
	}
	
	/**
	 * The battle mode of the game. One of the main features.
	 * This is where battle mode happens
	 * @author atang 15909204
	 */
	public static void battleMode()
	{
		if (player.hasRope() && player.currentPosition() == 0 && !bossKill)
		{
			enemy = new Ogre();
		}
		else
		{
			double chance = Math.random();
			if (chance < 0.60)
			{
				enemy = new Bat();
			}
			else if (chance < 0.90)
			{
				enemy = new Goblin();
			}
			else
			{
				enemy = new Spider();
			}
		}
		
		printLine();
		displayIncomingEnemy();
		enterContinue();
		
		boolean run = false;
		
		while (!player.isDead() && !enemy.isDead())
		{
			printLine();
			String userInput[] = tokenize();

			switch (userInput[0].toLowerCase())
			{
			case Commands.ATTACK:
				attackEnemy();
				break;
			case Commands.HEAL:
				player.useBandage();
				break;
			case Commands.RUN:
				run = generateRunChance();
				if (run)
				{
					runToRoom();
					enterContinue();
					exploreMode();
					return;
				}
				else
				{
					System.out.println("You have failed to run!");
					break;	
				}
			
			case Commands.HELP:
				command.displayBattleCommands();
				continue;
			default:
				displayCommandError();
				continue;
			}
			if (!enemy.isDead())
			{
				attackPlayer();
			}
		}
		
		if (enemy.isDead())
		{
			int goldDrop = enemy.dropGold();
			player.getGold(goldDrop);
			player.addKill();
			
			bossKill = (enemy.bossIsDead())?true:false;
		}
		checkPlayerDeath();	
		exploreMode();
	}
	
	/**
	 * Displays the current items in the room.
	 * @author atang 15909204
	 */
	public static void displayItemsInRoom()
	{
		if (gold[player.currentPosition()] || dagger[player.currentPosition()])
		{
			System.out.println("There are item(s) in current room:");
			
			if (gold[player.currentPosition()])
			{
				System.out.println("- Stack of cash containing: "+goldF.format(goldAmount[player.currentPosition()]));
			}
			
			if (dagger[player.currentPosition()])
			{
				System.out.println("- A very cool sword");
			}			
		}
		else
		{
			System.out.println("You find no items in the current room.");
		}		
	}
	
	/**
	 * This method runs once at the beginning to
	 * set the amount of gold for all rooms
	 * @author atang 15909204.
	 */
	public static void generateGoldPerRoom()
	{
		Random rand = new Random();
		
		for (int i = 0; i < goldAmount.length; ++i)
		{
			goldAmount[i] = rand.nextInt(400) + 250;
		}
	}
	
	/**
	 * This method runs when the players searches
	 * the room.
	 * @author atang 15909204
	 */
	public static void searchRoom()
	{
		System.out.println("You search the room...");
		displayItemsInRoom();
	}
	
	/**
	 * Checks whether the user had input a valid door,
	 * and changes the position.
	 * @param userInput The tokenized string
	 * @return returns true or false so the switch statement
	 * where this method is called can either continue
	 * the loop or break it.
	 */
	public static boolean openDoor(String[] userInput)
	{
		if (userInput.length == 2)
		{
			Scanner scan = new Scanner(userInput[1]);
			if (userInput[1].length() > 1 || !scan.hasNextInt())
			{
				System.out.println("You must enter a valid door number!");
				scan.close();
				return false;
			}
			scan.close();
			
			int roomChoice = new Integer(userInput[1]);;
			for (int i = 0; i < roomLayout[player.currentPosition()].length; ++i)
			{
				if (roomChoice == roomLayout[player.currentPosition()][i])
				{
					System.out.println("You open the door...");
					player.changePosition(roomChoice);
					return true;
				}
			}		
		}
			
		System.out.println("You must enter a valid door number!");	
		return false;
	}
	
	/**
	 * Adds the amount of gold the player picked up
	 * to their total current gold.
	 * @author atang 15909204
	 */
	public static void pickupGold()
	{
		if (gold[player.currentPosition()])
		{
			player.getGold(goldAmount[player.currentPosition()]);
			gold[player.currentPosition()] = false;
		}
	}
	
	/**
	 * Pickups the dagger if available in current room
	 * @author atang 15909204
	 */
	public static void pickupDagger()
	{
		if (dagger[player.currentPosition()])
		{
			player.getDagger();
			dagger[player.currentPosition()] = false;
		}
	}

	/**
	 * Second part of the user command when they type
	 * in "pickup". This checks if the second string
	 * matches the correct item. 
	 * @param userInput User input command
	 * @author atang 15909204
	 */
	public static void pickupItem(String[] userInput)
	{
		if (userInput.length == 2)
		{
			switch (userInput[1].toLowerCase())
			{
			case "cash":
			case "gold":
				pickupGold();
				break;
			case "dagger":
			case "sword":
				pickupDagger();
				break;
				default:
					System.out.println("You picked up a handful of air");
					break;	
			}
		}
	}
	
	/**
	 * This method runs when the player uses the escape
	 * command. It checks whether or not the player
	 * has the rope or not, and also if they are in
	 * the correct room where the rope can be used.
	 * If the player has a rope and in the correct room,
	 * it will display the final player stats and close the
	 * program.
	 * @author atang 15909204
	 */
	public static void escapeGame()
	{
		if (!player.hasRope())
		{
			System.out.println("You don't have the ability to escape right now!");
		}
		else if (player.hasRope() && player.currentPosition() != 0)
		{
			System.out.println("You cannot swing your rope here!");
		}
		else
		{
			System.out.println("You cast your rope up into the opening...");
			System.out.println("You slowly pull yourself up...");
			System.out.println("You are free!");
			printLine();
			System.out.println("Your final stats:");
			player.displayStats();
			player.displayTotalGold();
			System.exit(0);
		}
	}
	
	/**
	 * When player attempts to buy the rope, 
	 * it checks whether they have enough cash.
	 * If yes, then add rope to their inventory.
	 * @author atang 15909204
	 */
	public static void buyRope()
	{
		if (player.currentGold() >= shop.getRopePrice())
		{
			player.getRope();
			shop.boughtRope();
			player.loseGold(shop.getRopePrice());
			
		}
		else
		{
			System.out.println("You do not have enough cash for this item!");
		}		
	}
	
	/**
	 * Checks when the player has enough gold
	 * to buy a bandage, then gives them a bandage
	 * if true.
	 * @author atang 15909204
	 */
	public static void buyBandage()
	{
		if (player.currentGold() >= shop.getBandagePrice())
		{
			player.obtainBandage();
			player.loseGold(shop.getBandagePrice());
		}
		else
		{
			System.out.println("You are too poor for medical attention!");
		}	
	}
	
	/**
	 * This is the second stage of checking the user
	 * input when they use the buy command.
	 * It checks which item they want to buy, then 
	 * runs the class methods for obtaining the items.
	 * @param userInput The user command input.
	 * @author atang 15909204
	 */
	public static void buyItems(String[] userInput)
	{	
		if (userInput.length == 2)
		{
			switch(userInput[1].toLowerCase())
			{
			case "rope":
				buyRope();
				
				break;
			case "heal":
			case "health":
			case "healing":
			case "bandages":
			case "bandage":
				buyBandage();	
				break;
				default:
					break;
			}
		}
		else
		{
			System.out.println("What are you even trying to buy?!");
		}
	}
	
	/**
	 * One of the core methods. This is the explore mode
	 * method which includes the command entry, which is
	 * the main function of the mode.
	 * @author atang 15909204
	 */
	public static void exploreMode()
	{	
		if (player.hasRope() && player.currentPosition() == 0 && !bossKill)
		{
			battleMode();
		}
		
		loop:while (true)
		{
			if (player.currentPosition() == 5)
			{
				shopMode();
			}
			
			printSemiBreak();
			printRoomDescription(player.currentPosition());
			printConnections(player.currentPosition());
			printLine();
			String userInput[] = tokenize();
			
			switch (userInput[0].toLowerCase())
			{
			case Commands.HINT:
				command.displayHints();
				continue;
			case Commands.HELP:
				command.displayExploreCommands();
				continue;
			case Commands.SEARCH:
				searchRoom();
				continue;
			case Commands.STATS:
				player.displayStats();
				continue;
			case Commands.HEAL:
				player.useBandage();
				continue;
			case Commands.OPEN:
				if (!openDoor(userInput))
				{
					continue;
				}	
				break loop;
			case Commands.PICKUP:
				pickupItem(userInput);
				break loop;
			case Commands.YELL:
				battleMode();
				break;
			case Commands.ESCAPE:
				escapeGame();
				break;
				default:
					displayCommandError();
					continue;
			}
		}	
	}
	
	/**
	 * Similar to explore mode, but instead monsters cannot
	 * attack while in this mode, and allows the shop
	 * commands.
	 * @author atang 15909204
	 */
	public static void shopMode()
	{
		shop.vendorChat();
		
		loop:while (true)
		{
			printRoomDescription(player.currentPosition());
			printConnections(player.currentPosition());
			printLine();
			String userInput[] = tokenize();
			
			switch (userInput[0].toLowerCase())
			{
			case Commands.SHOP:
				player.displayCurrentGold();
				shop.printItems();
				break;
			case Commands.BUY:
				buyItems(userInput);
				break;
			case Commands.HINT:
				command.displayHints();
				continue;
			case Commands.HEAL:
				player.useBandage();
				break;
			case Commands.HELP:
				command.displayShopCommands();
				continue;
			case Commands.STATS:
				player.displayStats();
				continue;
			case Commands.SEARCH:
				searchRoom();
				continue;
			case Commands.OPEN:
				if (!openDoor(userInput))
				{
					continue;
				}	
				break loop;
			case Commands.PICKUP:
				pickupItem(userInput);
				break;
			case Commands.ESCAPE:
				escapeGame();
				break;
				default:
					displayCommandError();
					continue;
			}
		}
	}

	/**
	 * Prints the rooms connected to the player's 
	 * current room.
	 * @param roomNum Current player position.
	 * @author atang 15909204
	 */
	public static void printConnections(int roomNum)
	{
		System.out.print("There are door(s) labeled: ");

		for (int i = 0; i < roomLayout[roomNum].length; ++i)
		{
			System.out.print(roomLayout[roomNum][i]);
			if (i < roomLayout[roomNum].length - 1)
			{
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	/**
	 * Generates the chance for battle mode.
	 * @return returns true or false depending if the
	 * chance matches the randomly generated number.
	 * @author atang 15909204
	 */
	public static boolean generateBattleMode()
	{
		Random rand = new Random();

		final int BATTLE_CHANCE = 30;
		int generatedChance = rand.nextInt(101);
		if (generatedChance > BATTLE_CHANCE)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Checks if the player is dead after an enemy attacks.
	 * If they are dead, then their final stats will dispaly.
	 * @author atang 15909204
	 */
	public static void checkPlayerDeath()
	{
		if (player.isDead())
		{
			printLine();
			System.out.println("Game Over! You have apparently died.");
			System.out.println("Your final stats:");
			player.displayStats();
			player.displayTotalGold();
			System.exit(0);
		}
	}
	
	/**
	 * The main method.
	 * @author atang 15909204
	 */
	public static void main(String[] args) throws IOException
	{
		readGameMap();
		generateGoldPerRoom();
		exploreMode();

		while (true)
		{

			if (player.currentPosition() == 5)
			{
				shopMode();
			}
			else
			{
				if (generateBattleMode())
				{
					battleMode();
				}
				else
				{	
					exploreMode();
				}
			}
						
			checkPlayerDeath();		
		}
	}
}
