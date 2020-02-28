package Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    private Button nextWindow = new Button();
    private MapWindow map = new MapWindow();
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        mapWindow();
        this.stage = primaryStage;
        WelcomeWindow welcome = new WelcomeWindow();
        welcome.boxPane.getChildren().add(nextWindow);

        stage.setTitle("WELCOME MENU");
        stage.setScene(welcome.Window());
        stage.setResizable(false);
        stage.sizeToScene();

        stage.show();

    }
    private void mapWindow(){
        //Go user map selection interface
        nextWindow.setPrefHeight(30);
        nextWindow.setPrefWidth(300);
        nextWindow.setMnemonicParsing(false);
        nextWindow.setText("NEXT");

        nextWindow.setOnAction(event -> {

            stage.close();
            map.openMapWindow();
        });

    }
    static{
        System.setProperty("java.net.useSystemProxies", "true");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
