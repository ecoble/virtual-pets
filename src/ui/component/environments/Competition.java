package ui.component.environments;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Pet;
import model.User;
import ui.component.*;

import java.util.Random;

public class Competition extends VBox
{
    private Root root;
    private Pet pet;
    private User user;
    private BooleanProperty isFrisbeeMoving;
    private PetView view;
    private int count = 0;
    private int numCaught;

    @FXML
    private HBox menu;

    @FXML
    private HBox petContainer;

    @FXML
    private AnchorPane frisbeeBox;

    @FXML
    private AnchorPane box;

    @FXML
    private Button start;

    @FXML
    private Button throwFrisbee;

    @FXML
    private Button goHome;

    @FXML
    private Text userMessage;

    public Competition(Root root, User user, Pet pet)
    {
        this.root = root;
        this.user = user;
        this.pet = pet;

        isFrisbeeMoving = new SimpleBooleanProperty(false);
        Component.load("Competition.fxml", this);
    }

    public boolean getIsFrisbeeMoving()
    {
        return isFrisbeeMoving.get();
    }

    public BooleanProperty isFrisbeeMoving()
    {
        return isFrisbeeMoving;
    }

    public int getNumCaught()
    {
        return numCaught;
    }

    @FXML
    protected void initialize()
    {
        if(pet.getSpecies().equals("fish"))
        {
            userMessage.setText("Your fish, " + pet.getName() + ", is competing, but not for long!");
            losePet();
        }
        else
        {
            userMessage.setText("Click on " + pet.getName() + " to jump and catch the frisbee. Don't click too early or too late! You have 10 tries. ");
        }


        pet.hungerStatProperty().addListener((change ->
        {
            if (pet.getHungerStat() <= 0)
            {
                user.removePet(pet);
                root.transition(new LivingRoom(user, root, pet.getName() + " died from hunger!"));
            }
        }));

        pet.thirstStatProperty().addListener((change -> {
            if(pet.getThirstStat() <= 0)
            {
                user.removePet(pet);
                root.transition(new LivingRoom(user, root, pet.getName() + " died from thirst!"));
            }
        }));

        pet.hygieneStatProperty().addListener((changeStat ->
        {
            if (pet.getHygieneStat() == 0)
            {
                userMessage.setText(pet.getName() + " is very dirty! You should give them a bath!");
            }
        }));

        switch (pet.getSpecies())
        {
            case "dog":
                view = new PetView(pet, "images/golden-retriever.png", 250);
                break;
            case "cat":
                view = new PetView(pet, "images/cat_image.png", 150);
                break;
            case "rabbit":
                view = new PetView(pet, "images/rabbit.png", 50);
                break;
            case "bird":
                view = new PetView(pet, "images/bird.png", 150);
                break;
            case "fish":
                view = new PetView(pet, "images/goldfish.png", 75);
                break;
        }

        petContainer.getChildren().add(view);
        view.setDoNotToggle(true);
    }

    @FXML
    protected void start()
    {
        throwFrisbee();

        menu.lookup("start");

        start.setVisible(false);
        start.setManaged(false);

        throwFrisbee.setVisible(true);
        throwFrisbee.setManaged(true);
    }

    private void losePet()
    {
        goHome.setVisible(false);

        new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> {
                    if(pet.getSpecies().equals("fish"))
                    {
                        root.transition(new LivingRoom(user, root,pet.getName() + " died due to lack of water."));
                    }
                })
        ).play();

        user.removePet(pet);

    }

    @FXML
    protected void goHome()
    {
        if(getNumCaught() > 7)
        {
            root.transition(new LivingRoom(user, root,"You and " + pet.getName() + " returned home. You won $250! What would you like to do now?" ));
            user.addMoney(250);
        }
        else if (getNumCaught() >= 4 && getNumCaught() <= 7)
        {
            root.transition(new LivingRoom(user, root, "You and " + pet.getName() + " returned home. You won $100! What would you like to do now?"));
            user.addMoney(100);
        }
        else
        {
            root.transition(new LivingRoom(user, root, "You and " + pet.getName() + " returned home. You didn't win any money :( What would you like to do now?"));
        }
    }

    @FXML
    public void throwFrisbee()
    {
        count++;
        frisbeeBox.setVisible(true);
        throwFrisbee.setDisable(true);

        frisbeeBox.setLayoutX(590.0);

        Timeline throwF = new Timeline();
        throwF.getKeyFrames().add(new KeyFrame(Duration.millis(3), ae ->
        {

            frisbeeBox.setLayoutX(frisbeeBox.getLayoutX() - 1);

            if(isColliding())
            {
                throwFrisbee.setDisable(false);
                throwF.stop();
                boolean didCatch = didCatch();
                if(didCatch)
                {
                    switch (pet.getSpecies())
                    {
                        case "dog":
                            view.getPetView().setImage(new Image("images/dogwithfrisbee.png"));
                            view.getPetView().setFitWidth(200);
                            break;
                        case "cat":
                            view.getPetView().setImage(new Image("images/catwithfrisbee.png"));
                            view.getPetView().setFitWidth(200);
                            break;
                        case "rabbit":
                            view.getPetView().setImage(new Image("images/rabbitwithfrisbee.png"));
                            view.getPetView().setFitWidth(110);
                            break;
                        case "bird":
                            view.getPetView().setImage(new Image("images/birdwithfrisbee.png"));
                            view.getPetView().setFitWidth(200);
                            break;
                    }
                    numCaught++;
                }
                else
                {
                    switch (pet.getSpecies())
                    {
                        case "dog":
                            view.getPetView().setImage(new Image("images/dogmissfrisbee.png"));
                            view.getPetView().setFitWidth(175);
                            break;
                        case "cat":
                            view.getPetView().setImage(new Image("images/catmissfrisbee.png"));
                            view.getPetView().setFitWidth(120);
                            break;
                        case "rabbit":
                            view.getPetView().setImage(new Image("images/rabbitmissfrisbee.png"));
                            view.getPetView().setFitWidth(120);
                            break;
                        case "bird":
                            view.getPetView().setImage(new Image("images/birdmissfrisbee.png"));
                            view.getPetView().setFitWidth(200);
                            break;
                    }

                }

                frisbeeBox.setVisible(false);
                if(count == 10)
                {
                    userMessage.setText("The competition is over! " + pet.getName() + " caught " + numCaught + " frisbees!");
                }
            }
            else if(frisbeeBox.getLayoutX() < -60)
            {
                throwFrisbee.setDisable(false);
                throwF.stop();
                if(count == 10)
                {
                    userMessage.setText("The competition is over! " + pet.getName() + " caught " + numCaught + " frisbees!");
                }
            }
        }));

        throwF.setCycleCount(Animation.INDEFINITE);
        throwF.play();

        if(count == 10)
        {
            throwFrisbee.setVisible(false);
            throwFrisbee.setManaged(false);
        }
    }

    @FXML
    protected void jump()
    {
        box.setOnMouseClicked(null);

        Timeline jumpUp = new Timeline(new KeyFrame(Duration.millis(3), ae ->
        {
            box.setLayoutY(box.getLayoutY() - 1);
        }));

        Timeline jumpDown = new Timeline(new KeyFrame(Duration.millis(3), ae ->
        {
                box.setLayoutY(box.getLayoutY() + 1);
        }));

        Timeline reset = new Timeline(new KeyFrame(Duration.millis(1), ae ->
        {
            switch (pet.getSpecies())
            {
                case "dog":
                    view.getPetView().setImage(new Image("images/golden-retriever.png"));
                    break;
                case "cat":
                    view.getPetView().setImage(new Image("images/cat_image.png"));
                    break;
                case "rabbit":
                    view.getPetView().setImage(new Image("images/rabbit.png"));
                    break;
                case "bird":
                    view.getPetView().setImage(new Image("images/bird.png"));
                    break;
                case "fish":
                    view.getPetView().setImage(new Image("images/goldfish.png"));
                    break;
            }
            view.getPetView().setFitWidth(view.getImageSize());
            box.setOnMouseClicked(e -> jump());
        }));

        jumpUp.setCycleCount(200);
        jumpDown.setCycleCount(200);
        SequentialTransition jump = new SequentialTransition(jumpUp, jumpDown, reset);
        jump.play();

    }

    public boolean isColliding()
    {
        return box.getLayoutX() + view.getImageSize() >= frisbeeBox.getLayoutX()
           && box.getLayoutX() <= frisbeeBox.getLayoutX() + frisbeeBox.getMaxWidth()
           && box.getLayoutY() + (box.getLayoutY() - view.getPetView().getImage().getHeight() * view.getRatio()) <= frisbeeBox.getLayoutY() + frisbeeBox.getMaxHeight()
           && box.getLayoutY() + view.getHeight() >= frisbeeBox.getLayoutY();
    }

    public boolean didCatch()
    {
        Random rand = new Random();
        int num = 0;

        if(pet.getSkillPoints() > 100)
        {
            num = rand.nextInt(50) + 50;
        }
        else if(pet.getSkillPoints() >= 50 && pet.getSkillPoints() <= 100)
        {
            num = rand.nextInt(75) + 25;
        }
        else
        {
            num = rand.nextInt(100);
        }

        return num >= 75;
    }
}

