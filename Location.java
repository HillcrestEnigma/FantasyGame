/**
 * This is an interface for locations in the games
 * 
 * @version 06-122-2021
 * @author JingKun
 */
public interface Location {
    /**
     * Method that generates the rooms for the location
     * 
     * @params none
     * @return seed
     */
    public int generateRooms();
    /**
     * Method that checks if the player can enter
     * 
     * @paramas elf
     * @return 
     */
    public boolean canEnter(Elf elf);
}
