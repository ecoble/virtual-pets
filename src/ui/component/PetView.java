package ui.component;

import model.Pet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by M5sp on 9/27/17.
 */
public class PetView extends ImageView
{
    private Pet pet;
    private String imageUrl;
    private int imageSize;

    public PetView(Pet pet, String imageUrl, int imageSize)
    {
        this.pet = pet;
        this.imageUrl = imageUrl;
        this.imageSize = imageSize;

        this.setPreserveRatio(true);
        this.setFitWidth(imageSize);
        this.setImage(new Image(imageUrl));
    }

    public Pet getPet()
    {
        return pet;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public int getImageSize()
    {
        return imageSize;
    }
}
