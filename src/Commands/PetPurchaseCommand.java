package Commands;

import Model.*;

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
        switch(pet.getSpecies()) {
            case "dog":
                user.addLandPetUnits(Dog.numUnits);
                break;
            case "cat":
                user.addLandPetUnits(Cat.numUnits);
                break;
            case "rabbit":
                user.addLandPetUnits(Rabbit.numUnits);
                break;
            case "bird":
               user.addBirdUnits(Bird.numUnits);
                break;
            case "fish":
                user.addFishUnits(Fish.numUnits);
                break;
        }

        user.addPet(pet);
    }
}
