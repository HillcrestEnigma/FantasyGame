/**
 * A room interface for the different rooms
 * 
 * @version 06-14-2021
 * @author JingKun
 */
public interface Room {
    /**
     * Allows the elf to enter the room and perform actions in it, if it can.
     * If the elf can't, return false
     * 
     * @param elf 
     * @return
     */
    public boolean enter(Elf elf);
    /**
     * Allows the elf to exit the room
     * 
     * @param elf 
     */
    public void exit(Elf elf);
    
    public String getName();
}
