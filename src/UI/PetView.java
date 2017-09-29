package UI;

import javafx.scene.layout.Pane;

/**
 * Created by M5sp on 9/27/17.
 */
public class PetView {
    private Pet pet;
    private String imageUrl;
    private int imageSize;
    private Pane container;

    public PetView(Pet pet, String imageUrl, int imageSize, Pane container)
    {
        this.pet = pet;
        this.imageUrl = imageUrl;
        this.imageSize = imageSize;
        this.container = container;
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

    public Pane getContainer()
    {
        return container;
    }
}
