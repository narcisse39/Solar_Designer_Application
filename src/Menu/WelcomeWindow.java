package Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

public class WelcomeWindow {

    public WelcomeWindow() {

    }

    private BorderPane rootPane = new BorderPane();

    private TextArea Area = new TextArea();

    HBox boxPane = new HBox();
    private Button nextWindow = new Button();
    private MapWindow map = new MapWindow();
    private Stage secondary;
    private Scene scene ;
    private int idClose=0;

    public Scene Window(){
        //mapWindow();

        //Describe application purpose and operation
        Area.setPrefHeight(177);
        Area.setPrefWidth(650);
        Area.setOpacity(0.5);
        Area.setEditable(false);
        Area.setText("\n\nThe application is used for PV(photovoltaics) sizing to power a RO(Reverse osmosis) desalination unit" +
                "\nThe system depends on batteries to store energy." +
                "\n\nTo use this application without problems, you must have the following information\n" +
                "\t*The location of the desalination unit;\n" +
                "\t*Amount of power required to operate the unit in kilowatts;\n" +
                "\t*Time of operating for RO;\n" +
                "\t*Detailed specification information of the pv modules;\n" +
                "\t*The number of people the Ro unit will provide water for,and the average water consumption for regular person");

        boxPane.setPrefHeight(44);
        boxPane.setPrefWidth(650);

        //Go user map selection interface
        nextWindow.setPrefHeight(30);
        nextWindow.setPrefWidth(300);
        nextWindow.setMnemonicParsing(false);
        nextWindow.setText("NEXT");


        //HBox.setMargin(nextWindow, new Insets(5,0,5,0));
        //  boxPane.getChildren().addAll(nextWindow);
        boxPane.setAlignment(Pos.CENTER);

        //Load text area and button to the container
        rootPane.setCenter(Area);
        rootPane.setBottom(boxPane);

        scene = new Scene(rootPane,640,300);
        return scene;
    }
  /*  private void mapWindow(){

        secondary = new Stage();
        secondary.setTitle("MAP MENU");
        nextWindow.setOnAction(event -> {
            secondary.setScene(map.setScene());
            secondary.setResizable(false);
            secondary.sizeToScene();

            secondary.show();
        });

    }*/


}


