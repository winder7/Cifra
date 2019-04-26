package cifra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Winder Rezende
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("Tela.fxml"));
        
        Scene scene = new Scene(root);
        
        Image image = new Image("/cifra/icon.jpg");
        stage.getIcons().add(image);
        
        stage.setTitle("Cifra de César/Vigenere by Winder");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
