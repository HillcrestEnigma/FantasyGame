/**
 * This class represents the sole character of the game.
 * 
 * @version 06-16-2021
 * @author Jing Sun & Paul Lee
 */
public class Elf extends Entity {
    int health;
    int gold;
    final static int maxGold = 8;

    public Elf(String name) {
        super(name);
	health = 100;
	gold = 0;
    }
}
