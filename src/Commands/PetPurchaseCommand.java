package Commands;

import UI.Pet;
import UI.User;

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

    public void execute(User user)
    {
        user.addPet(pet);
    }
}
