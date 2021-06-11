/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework;

/**
 *
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
