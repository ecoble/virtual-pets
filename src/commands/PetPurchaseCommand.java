package commands;

import model.*;

/**
 * Created by M5sp on 9/25/17.
 */
public class PetPurchaseCommand implements PurchaseCommand{

    private Pet pet;
    private int price;

    public PetPurchaseCommand(Pet pet, int price)
    {
        this.pet = pet;
        this.price = price;
    }

    public int getPrice()
    {
        return price;
    }

    public Pet getPet()
    {
        return pet;
    }

    public void execute(User user)
    {
        switch(pet.getType()) {
            case LAND:
                user.addLandPetUnits(pet.getNumUnits());
                break;
            case BIRD:
               user.addBirdUnits(pet.getNumUnits());
                break;
            case FISH:
                user.addFishUnits(pet.getNumUnits());
                break;
        }

        user.addPet(pet);
    }
}
