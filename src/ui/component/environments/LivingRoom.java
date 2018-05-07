package ui.component.environments;

import commands.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ui.component.Component;
import ui.component.PetView;
import ui.component.Root;
import ui.component.menus.BuyPets;
import ui.component.menus.Home;
import ui.component.menus.PetNames;
import ui.component.menus.Shop;

import java.io.File;
import java.io.FileWriter;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by M5sp on 10/19/17.
 */
public class LivingRoom extends VBox
{
    private User user;
    private Root root;

    @FXML
    private HBox landPetContainer;

    @FXML
    private StackPane birdContainer;

    @FXML
    private StackPane fishContainer;

    @FXML
    private ImageView foodContainer;

    @FXML
    private ImageView waterContainer;

    @FXML
    private Button quitButton;

    @FXML
    private Button dogButton;

    @FXML
    private Button catButton;

    @FXML
    private Button fishButton;

    @FXML
    private Button birdButton;

    @FXML
    private Button rabbitButton;

    @FXML
    private Button dogFoodButton;

    @FXML
    private Button catFoodButton;

    @FXML
    private Button birdFoodButton;

    @FXML
    private Button fishFoodButton;

    @FXML
    private Button rabbitFoodButton;

    @FXML
    private HBox petNameBox;

    public LivingRoom(User user, Root root)
    {
        this.user = user;
        this.root = root;
        this.user.getPets().addListener((ListChangeListener)(change -> {
            while(change.next())
            {
                for (Object obj : change.getRemoved())
                {
                    Pet pet = (Pet) obj;
                    Pane container = null;
                    switch (pet.getType())
                    {
                        case LAND:
                            container = landPetContainer;
                            break;
                        case BIRD:
                            container = birdContainer;
                            break;
                        case FISH:
                            container = fishContainer;
                            break;
                        default:
                            throw new NotImplementedException();
                    }

                    PetView view = container.getChildren().stream()
                            .filter(n -> n instanceof PetView)
                            .map(n -> (PetView) n)
                            .filter(v -> v.getPet() == pet)
                            .findFirst()
                            .get();

                    container.getChildren().remove(view);
                }

                for (Object obj : change.getAddedSubList())
                {
                    createPets((Pet) obj);
                }
            }
        }));

        Component.load("LivingRoom.fxml",this);
    }

    @FXML
    protected void initialize()
    {
        for(Pet pet : user.getPets())
        {
            createPets(pet);
        }

        dogButton.setText("Buy Dog: $" + Dog.price);
        catButton.setText("Buy Cat: $" + Cat.price);
        fishButton.setText("Buy Fish: $" + Fish.price);
        birdButton.setText("Buy Bird: $" + Bird.price);
        rabbitButton.setText("Buy Rabbit: $" + Rabbit.price);

        dogFoodButton.setText("Dog Food: $" + Dog.foodPrice);
        catFoodButton.setText("Cat Food: $" + Cat.foodPrice);
        birdFoodButton.setText("Bird Food: $" + Bird.foodPrice);
        fishFoodButton.setText("Fish Food: $" + Fish.foodPrice);
        rabbitFoodButton.setText("Rabbit Food: $" + Rabbit.foodPrice);
    }

    private void createPets(Pet pet)
    {
        if(pet instanceof model.Dog)
        {
            landPetContainer.getChildren().add((new PetView(pet, "images/golden-retriever.png", 250)));
        }
        else if(pet instanceof model.Cat)
        {
            landPetContainer.getChildren().add((new PetView(pet, "images/cat_image.png", 150)));
        }
        else if(pet instanceof model.Bird)
        {
            birdContainer.getChildren().add((new PetView(pet, "images/bird.png", 150)));
        }
        else if(pet instanceof model.Fish)
        {
            fishContainer.getChildren().add((new PetView(pet, "images/goldfish.png", 75)));
        }
        else if(pet instanceof model.Rabbit)
        {
            landPetContainer.getChildren().add((new PetView(pet, "images/rabbit.png", 50)));
        }
    }

    //Home menu
    @FXML
    protected void buyMorePets(MouseEvent event)
    {
        root.transitionMenu(new BuyPets(root, user));
        root.changeMessage("You have $" + user.getMoney() + ".");
    }

    @FXML
    protected void showStore(MouseEvent event)
    {
        root.transitionMenu(new Shop(root, user));
        root.changeMessage("You have $" + user.getMoney() + ". \nFood Inventory: \n"
                + "Dog: " + user.getFood(FoodType.DOG) + " "
                + "Cat: " + user.getFood(FoodType.CAT) + " "
                + "Bird: " + user.getFood(FoodType.BIRD) + " "
                + "Fish: " + user.getFood(FoodType.FISH) + " "
                + "Rabbit: " + user.getFood(FoodType.RABBIT) + " ");
    }

    @FXML
    protected void showPetNames(MouseEvent event)
    {
        root.transitionMenu(new PetNames(root, user));
        root.changeMessage("Which pet would you like to interact with?");
    }

    @FXML
    protected void quit()
    {
        String fileName = null;


        if(!root.getIsNew())
        {
            fileName = root.getFileName();
            Stage stage = (Stage) quitButton.getScene().getWindow();
            stage.close();
        }
        else
        {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(user.getName());
            File file= fileChooser.showSaveDialog(quitButton.getScene().getWindow());

            if(file == null)
            {
                return;
            }

            fileName = file.getName();

            Stage stage = (Stage) quitButton.getScene().getWindow();
            stage.close();
        }

        try
        {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(Json.to(user));
            fileWriter.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Buy pets menu
    @FXML
    protected void buyDog(MouseEvent event)
    {
        PetView view = new PetView(new Dog(), "images/golden-retriever.png", 250);
        buyPet(view, Dog.price);
    }

    @FXML
    protected void buyCat(MouseEvent event)
    {
        PetView view = new PetView(new Cat(), "images/cat_image.png", 150);
        buyPet(view, Cat.price);

    }

    @FXML
    protected void buyFish(MouseEvent event)
    {
        PetView view = new PetView(new Fish(), "images/goldfish.png", 75);
        buyPet(view, Fish.price);

    }

    @FXML
    protected void buyBird(MouseEvent event)
    {
        PetView view = new PetView(new Bird(), "images/bird.png", 150);
        buyPet(view, Bird.price);
    }

    @FXML
    protected void buyRabbit(MouseEvent event)
    {
        PetView view = new PetView(new Rabbit(), "images/rabbit.png", 50);
        buyPet(view, Rabbit.price);

    }

    @FXML
    protected void cancel(MouseEvent event)
    {
        root.transitionMenu(new Home(root, user));
        root.changeMessage("What would you like to do now?");
    }

    private void buyPet(PetView view, int price) {
        if(!root.checkPrice(price, user))
        {
            return;
        }

        if(!checkSpace(view.getPet()))
        {
            return;
        }

        root.changeMessage("You bought a " + view.getPet().getSpecies() + "! You need to name your " + view.getPet().getSpecies() + "!");

        root.transitionMenu(new Home(root, user));

        String name = "";

        while(true)
        {
            Optional<String> opName = collectInput("Enter Pet Name", "Name your pet!");

            if(!opName.isPresent())
            {
                root.transitionMenu(new Home(root, user));
                root.changeMessage("What would you like to do now?");
                return;
            }

            name = opName.get();

            if(checkNameLength(name))
            {
                root.changeMessage("That name is too long!");
            }
            else if(checkIfSameName(name))
            {
                root.changeMessage("You already have a pet with that name!");
            }
            else
            {
                break;
            }
        }

        view.getPet().setName(name);

        PetPurchaseCommand command = new PetPurchaseCommand(view.getPet(), price);
        user.purchase(command);

        root.changeMessage("What would you like to do now?");
    }

    private Optional<String> collectInput(String message, String header)
    {
        TextInputDialog input = new TextInputDialog();
        input.setTitle("Virtual Pets");
        input.setHeaderText(header);
        input.setContentText(message);
        return input.showAndWait();
    }

    private boolean checkSpace(Pet pet)
    {
        if(!user.hasSpace(pet))
        {
            root.changeMessage("You don't have enough space for that!");
            root.transitionMenu(new Home(root, user));
            root.pauseForMessage("What would you like to do now?");
            return false;
        }

        return true;
    }

    private boolean checkIfSameName(String name)
    {
        return user.getPets().stream().anyMatch(pet -> pet.getName().equals(name));
    }

    private boolean checkNameLength(String name)
    {
        return name.length() > 30;
    }

    //Shop menu
    @FXML
    protected void buyDogFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Dog.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new DogFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought dog food! What would you like to do now?");
    }

    @FXML
    protected void buyCatFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Cat.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new CatFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought cat food! What would you like to do now?");
    }

    @FXML
    protected void buyBirdFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Bird.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new BirdFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought bird food! What would you like to do now?");
    }

    @FXML
    protected void buyFishFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Fish.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new FishFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought fish food! What would you like to do now?");
    }

    @FXML
    protected void buyRabbitFood()
    {
        root.transitionMenu(new Home(root, user));

        if(!root.checkPrice(Rabbit.foodPrice, user))
        {
            return;
        }

        FoodPurchaseCommand command = new RabbitFoodPurchaseCommand();
        user.purchase(command);


        root.changeMessage("You bought rabbit food! What would you like to do now?");
    }
}
