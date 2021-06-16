/**
 * This is an interface for locations in the games
 * 
 * @version 06-16-2021
 * @author JingKun
 */
public class Location {
    private Room[][] rooms = new Room[50][50];
    /**
     * Method that generates the rooms for the location
     * 
     * @params none
     * @return seed
     */
    public int generateRooms() {
        int seed = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                
            }
        }
        return seed;
    }
    /**
     * Method that checks if the player can enter
     * 
     * @param elf
     * @return 
     */
    public boolean canEnter(Elf elf) {
    }
}
