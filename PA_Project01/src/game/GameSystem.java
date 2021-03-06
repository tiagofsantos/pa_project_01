package game;

import dao.UserDAOJSON;
import game.models.ComputerGame;
import game.models.Game;
import game.models.Machine;
import game.models.User;
import graphics.controllers.GameController;
import java.awt.Dimension;
import javafx.scene.layout.Pane;

public class GameSystem {

    public static Game game;
    public static GameController gameController;

    public static Dimension dimensions;
    public static Pane contentPane;

    public static void init() {

        dimensions = new Dimension(600, 600);

        User user = new User("Ruben", "123", "ruben.amendoeira@gmail.com");
        User user2 = new User("Tiago", "123", "tiago.afsantos@hotmail.com");
        UserDAOJSON.getInstance().insert(user);
        UserDAOJSON.getInstance().insert(user2);
        game = new ComputerGame(user, 1);
        game.start();
        
        gameController = new GameController(game);
        
        


    }
    
    public static void refresh(){
        contentPane.getChildren().clear();
        gameController.render(contentPane);
    }

}
