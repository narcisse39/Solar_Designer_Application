package Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.text.DecimalFormat;


public class MapWindow  {

    private Button GoToUserInputInterface = new Button("NEXT");
    private BorderPane root = new BorderPane();
    private HBox bottomBar= new HBox();
    private Stage stage;
    private Scene scene ;

    Input_Interface user = new Input_Interface();
    private ComboBox<String> mapTypes;
    private HBox mapTypePane = new HBox();

    public String getLatX() {
        return latX;
    }

    public String getLongY() {
        return longY;
    }

    private String latX = new String();
    private String longY = new String();
    private static DecimalFormat twoDecimalPlaces = new DecimalFormat("#.##");


    public MapWindow(){

    }


    public void openMapWindow(){
        // create web engine and view to load the map from google api
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("googlemap.html").toString());

        //Tool bar configuration
        bottomBar.setPrefHeight(30);
        bottomBar.setPrefWidth(200);

        //Go to user input interface
        GoToUserInputInterface.setPrefWidth(300);
        GoToUserInputInterface.setPrefHeight(20);
        GoToUserInputInterface.setOnAction(event -> {
            latX = String.valueOf(webEngine.executeScript("document.getElementById('lat').value"));
            longY  = String.valueOf(webEngine.executeScript("document.getElementById('long').value"));


            if(latX.equals("undefined") && longY.equals("undefined")){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Please select the site location on the map");

                alert.showAndWait();
            }else{


                Double lx = Double.valueOf(twoDecimalPlaces.format(Double.valueOf(latX)));
                Double ly =Double.valueOf(twoDecimalPlaces.format(Double.valueOf(longY)));

                stage.close();
               user.input_Interface(latX,longY);
                System.out.println(latX);
           new Output_Interface().getAngle(latX);

            //    new Output_Interface().generateGraphs_By_Loading_Weather_Data(latX,longY,"2018");
            //   new Output_Interface().windowOpened();

                Alert inputAlert = new Alert(Alert.AlertType.INFORMATION);
                inputAlert.setTitle("Warning Dialog");
                inputAlert.setHeaderText(null);
                inputAlert.setContentText("Please enter the load data, battery data, PV data "+"\n" +
                        "and select the year in the system parameters");
                inputAlert.showAndWait();

            }

        });

        //Load to containers
        HBox.setMargin(GoToUserInputInterface, new Insets(0,15,0,0));
        bottomBar.getChildren().add(GoToUserInputInterface);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);

        //Map type selection
        mapTypes = new ComboBox<>();
        mapTypes.getItems().addAll("HYBRID", "SATELLITE", "TERRAIN");
        mapTypes.setPromptText("Select Map Types");
        mapTypes.setOnAction(event -> {
            try{

                if(mapTypes.getValue().equals("TERRAIN")){

                    webEngine.executeScript(
                            "map.setMapTypeId(google.maps.MapTypeId.TERRAIN);"
                    );
                }
                else if(mapTypes.getValue().equals("HYBRID")){

                    webEngine.executeScript(
                            "map.setMapTypeId(google.maps.MapTypeId.HYBRID);"
                    );
                }
                else if(mapTypes.getValue().equals("SATELLITE")){

                    webEngine.executeScript(
                            "map.setMapTypeId(google.maps.MapTypeId.SATELLITE);"
                    );
                }

                else{
                    System.out.println("Select map type please");
                }
            }catch( Exception exc){
                exc.getMessage();
            }
        });
        //Configure map types container
        mapTypes.setPrefWidth(140);
        mapTypes.setPrefHeight(20);
        mapTypePane.setAlignment(Pos.CENTER);
        mapTypePane.getChildren().add(mapTypes);

        //Load all components to the container
        root.getStyleClass().add("map");
        root.setCenter(webView);
        root.setTop(mapTypePane );
        root.setBottom(bottomBar);
        scene = new Scene(root, 900,705);

        stage = new Stage();
        stage.setTitle("MAP MENU");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

    }


}
