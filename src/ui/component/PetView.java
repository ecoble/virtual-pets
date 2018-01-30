package ui.component;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;
import model.Pet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by M5sp on 9/27/17.
 */
public class PetView extends VBox
{
    private Pet pet;
    private String imageUrl;
    private int imageSize;

    @FXML
    private ImageView petView;

    @FXML
    private ImageView foodView;

    @FXML
    private ImageView waterView;

    @FXML
    private Canvas canvas;

    @FXML
    private Insets fishPadding;

    @FXML
    private VBox root;

    public PetView(Pet pet, String imageUrl, int imageSize)
    {
        this.pet = pet;
        this.imageUrl = imageUrl;
        this.imageSize = imageSize;

        Component.load("PetView.fxml", this);
    }

    @FXML
    protected void initialize()
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline statTimeline = new Timeline();

        KeyFrame hungerKeyFrame = new KeyFrame(Duration.millis(1), ae -> drawStatMeter(gc, 0, pet.getHungerStat(), Color.GREEN));
        KeyFrame thirstKeyFrame = new KeyFrame(Duration.millis(1), ae -> drawStatMeter(gc, 15, pet.getThirstStat(), Color.BLUE));
        KeyFrame hygieneKeyFrame = new KeyFrame(Duration.millis(1), ae -> drawStatMeter(gc, 30, pet.getHygieneStat(), Color.SADDLEBROWN));

        statTimeline.getKeyFrames().addAll(hungerKeyFrame, thirstKeyFrame, hygieneKeyFrame);
        statTimeline.setCycleCount(Animation.INDEFINITE);
        statTimeline.play();

        petView.setFitWidth(imageSize);
        petView.setImage(new Image(imageUrl));

        if(pet.getSpecies().equals("fish"))
        {
            root.setPadding(new Insets(0, 0, 25, 0));
        }

        pet.onFeed(() -> {
            foodView.setImage(new Image("images/food.png"));

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(5000),
                    ae -> foodView.setImage(null))
            );

            timeline.play();

        });

        pet.onGiveWater(() -> {
            waterView.setImage(new Image("images/water.png"));

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(5000),
                    ae -> waterView.setImage(null))
            );

            timeline.play();

        });


    }

    @FXML
    protected void toggleStats(MouseEvent event)
    {
        if(canvas.isVisible())
        {
            canvas.setVisible(false);
        }
        else if(!canvas.isVisible())
        {
            canvas.setVisible(true);
        }
    }

    private void drawStatMeter(GraphicsContext gc, int y, int stat, Color color)
    {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(0,y, 102, 9);

        gc.clearRect(1,y + 1, 101, 7);
        gc.setFill(color);
        gc.fillRect(1,y + 1, stat, 7);
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
