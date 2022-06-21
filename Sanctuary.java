/**
 * Sanctuary file
 * Using HashMap to efficiently keep track of how many of each species 
 * are currently located on its grounds in a wildlife sanctuary.
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Sanctuary class
 * Using Hashmap to organize all the animals at the sanctuary,
 * including the maximum of animals and maximum of species that
 * the sanctuary can contain.
 */
public class Sanctuary {
    //Container to store all the animal species in the sanctuary.
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Sanctuary(int maxAnimals, int maxSpecies) constructor
     * Sanctuary constructor includes the maximum number of animals
     * and maximum number of species.
     * @param maxAnimals
     * @param maxSpecies
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if(maxAnimals < 0){
            throw new IllegalArgumentException();
        }
        if(maxSpecies < 0){
            throw new IllegalArgumentException();
        }
        //Initialize
        sanctuary = new HashMap<String, Integer>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * getNum(String species)
     * The number of animals at the sanctuary 
     * that are of the given species.
     * @param species
     * @return number of animals
     */
    public int getNum(String species) {
        if(species == null){
            return 0;
        }
        if(sanctuary.containsKey(species)){
            return sanctuary.get(species);
        }
        else{
            return 0;
        }
    }

    /**
     * getTotal() method
     * The total number of animals at the sanctuary
     * @return return the total number of animals at the sanctuary
     */
    public int getTotal() {
        int total = 0;
        Set<String> keys = sanctuary.keySet();
        //For each key in the sanctuary
        for(String item : keys){
            //Get the value of each key
            int numOfAnimals = sanctuary.get(item);
            //Add them up
            total += numOfAnimals;
        }
        return total;
    }

    /**
     * rescue(String species, int num) method
     * Add as many animals as permitted despite the maximum limit
     * @param species animal species
     * @param num animals of species
     * @return the number of animals could not be rescued
     */
    public int rescue(String species, int num) {
        if(num < 0){
            throw new IllegalArgumentException();
        }
        if(species == null){
            return 0;
        }
        //Get the expected animals
        int expectedAnimals = 0;
        expectedAnimals = getTotal() + num;
        //if expectedAnimals <= maxAnimals
        if(expectedAnimals <= maxAnimals){
            int newNumSpecies = 0;
            //check if the species is in the sanctuary
            if(sanctuary.containsKey(species)){
                //if there is, add new number of animals to the list
                newNumSpecies = getNum(species) + num;
                //put back a new number to the sanctuary
                sanctuary.put(species, newNumSpecies);
            }
            else{
                //if the species is not in the sanctuary, add them.
                sanctuary.put(species, num);
            }
            //return 0 when successfully added everything
            return 0;
        }
        //if the expectedAnimals > maxAnimals
        else{
            //the remaining space that you can add
            int remainSpace = maxAnimals - getTotal();

            int newNumSpecies = 0;
            //if the species is in the sanctuary
            if(sanctuary.containsKey(species)){
                //check if the remaining space < maxAnimals
                if(remainSpace < maxAnimals){
                    //Add new number of animals into the species
                    newNumSpecies = getNum(species) + remainSpace;
                    sanctuary.put(species, newNumSpecies);
                }
                else{
                    //if the remaining space > maxAnimals
                    return expectedAnimals - maxAnimals;
                }
            }
            //if the species is not in the sanctuary
            else{
                if(remainSpace < num){
                    sanctuary.put(species, remainSpace);
                    return num - remainSpace;
                }
                else{
                    return expectedAnimals - maxAnimals;
                }
            }
            //return the number of animal that can't be rescued
            return expectedAnimals - maxAnimals;
        }
    }

    /**
     * release(String species, int num)
     * Remove num animals of species from the sanctuary
     * @param species
     * @param num number animals of species
     */
    public void release(String species, int num) {
        if(num < 0 || num > getNum(species)){
            throw new IllegalArgumentException();
        }
        if(species == null){
            throw new IllegalArgumentException();
        }
        if(sanctuary.containsKey(species)){
            int leftOver = 0;
            leftOver = getNum(species) - num;
            sanctuary.replace(species, leftOver);
            if(leftOver == 0){
                sanctuary.remove(species);
            }
        }
    }
}
