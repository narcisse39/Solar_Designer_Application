package Menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class Input_Interface {

    public Input_Interface(){

    }

    //=====================================================================================================================================================================
    //Containers
    BorderPane inputRoot = new BorderPane();
    HBox topTitle = new HBox();
    ScrollPane centerRoot = new ScrollPane();
    AnchorPane insidePane = new AnchorPane();
    VBox SystemPane = new VBox();
    Output_Interface output_interface = new Output_Interface();

    //Label Titles
    Label sys_Para_Title = new Label("System Parameters");
    Label load_Para_Title = new Label("Load Parameters");
    Label batt_Para_Title = new Label("Battery Parameters");
    Label PV_Para_Title = new Label("PV Parameters");

    //Load Parameters
    Label loadLbl = new Label("Load Power");
    TextField loadTxt = new TextField();
    Label optLbl = new Label("Operating Time");
    TextField optTxt = new TextField();

    //Battery Parameters
    Label nomLbl = new Label("Nominal Voltage");
    TextField nomTxt = new TextField();
    Label capLbl = new Label("Capacity");
    TextField capTxt = new TextField();
    Label dodLbl = new Label("Depth of Discharge");
    TextField dodTxt = new TextField();
    Label multLbl = new Label("Temperature Multiplier");
    ComboBox multComb = new ComboBox();
    Label battEffLbl = new Label("Efficiency");
    TextField battEffTxt = new TextField();

    //PV Parameters
    Label powerLbl = new Label("Module Power");
    TextField powerTxt = new TextField();
    Label pv_VoltLbl = new Label("Rated Voltage");
    TextField pv_VoltTxt = new TextField();
    Label coefLbl = new Label("Temperature Coefficient");
    TextField coefTxt = new TextField();
    Label widthLbl = new Label("Module Width");
    TextField widthTxt = new TextField();
    Label lengthLbl = new Label("Module Length");
    TextField lengthTxt = new TextField();
    Label dc_VoltLbl = new Label("DC Bus Voltage");
    TextField dc_VoltTxt = new TextField();
    Label aut_DaysLbl = new Label("Number of Autonomy Days");
    TextField aut_DaysTxt = new TextField();
    Label inv_EffLbl = new Label("Inverter Efficiency");
    TextField inv_EffTxt = new TextField();

    Button nextWindow =  new Button("Next");
    Button backWindow = new Button();
    Region first = new Region();
    Region second = new Region();
    Region third = new Region();

    public String getYear() {
        return year;
    }

    private String year;
    Double tempForMult;

    FileInputStream input;
    Stage inputStage = new Stage();
    public void input_Interface(String lat,String longt){
        //Sys title label
        sys_Para_Title.setPrefWidth(200);
        sys_Para_Title.setPrefHeight(30);


//Load settings
        load_Para_Title.setPrefWidth(150);
        load_Para_Title.setPrefHeight(30);
        load_Para_Title.setLayoutX(10);
        load_Para_Title.setLayoutY(141);

        loadLbl.setPrefWidth(150);
        loadLbl.setPrefHeight(25);
        loadTxt.setPrefWidth(10);
        loadTxt.setPrefHeight(25);
        loadTxt.setPromptText("Kilo-Watt");

        optLbl.setPrefWidth(150);
        optLbl.setPrefHeight(25);
        optLbl.setLayoutX(10);
        optLbl.setLayoutY(10);

        optTxt.setPrefWidth(100);
        optTxt.setPrefHeight(25);
        optTxt.setLayoutX(10);
        optTxt.setLayoutY(27);
        optTxt.setPromptText("Hours");

        first.setPrefWidth(200);
        first.setPrefHeight(30);

//Battery settings
        batt_Para_Title.setPrefWidth(150);
        batt_Para_Title.setPrefHeight(25);
        batt_Para_Title.setLayoutX(10);
        batt_Para_Title.setLayoutY(52);

        nomLbl.setPrefWidth(150);
        nomLbl.setPrefHeight(25);
        nomLbl.setLayoutX(10);
        nomLbl.setLayoutY(52);

        nomTxt.setPrefWidth(100);
        nomTxt.setPrefHeight(25);
        nomTxt.setLayoutX(10);
        nomTxt.setLayoutY(69);
        nomTxt.setPromptText("Volt");

        capLbl.setPrefWidth(150);
        capLbl.setPrefHeight(25);
        capLbl.setLayoutX(10);
        capLbl.setLayoutY(94);

        capTxt.setPrefWidth(150);
        capTxt.setPrefHeight(25);
        capTxt.setLayoutX(10);
        capTxt.setLayoutY(111);
        capTxt.setPromptText("Amp-hour");

        dodLbl.setPrefWidth(150);
        dodLbl.setPrefHeight(25);
        dodLbl.setLayoutX(10);
        dodLbl.setLayoutY(136);

        dodTxt.setPrefWidth(150);
        dodTxt.setPrefHeight(25);
        dodTxt.setLayoutX(10);
        dodTxt.setLayoutY(212);
        dodTxt.setPromptText("%");

        multLbl.setPrefWidth(150);
        multLbl.setPrefHeight(25);
        multLbl.setLayoutX(10);
        multLbl.setLayoutY(561);

        multComb.setPrefWidth(150);
        multComb.setPrefHeight(25);
        multComb.setPromptText("Select multiplier");

        multComb.getItems().add("1.00->26,7C");
        multComb.getItems().add("1.04->21.2C");
        multComb.getItems().add("1.11->15.6C");
        multComb.getItems().add("1.19->10.0C");
        multComb.getItems().add("1.30->4.4C");
        multComb.getItems().add("1.40->-1.1C");
        multComb.getItems().add("1.59->-6.7C");
        multComb.setOnAction(event -> {

            if(multComb.getSelectionModel().getSelectedIndex()==0){

                tempForMult = 1.00;

            }else if(multComb.getSelectionModel().getSelectedIndex()==1){

                tempForMult = 1.04;

            }else if(multComb.getSelectionModel().getSelectedIndex()==2){

                tempForMult = 1.11;

            }else if(multComb.getSelectionModel().getSelectedIndex()==3){

                tempForMult = 1.19;

            }else if(multComb.getSelectionModel().getSelectedIndex()==4){

                tempForMult = 1.30;

            }else if(multComb.getSelectionModel().getSelectedIndex()==5){

                tempForMult = 1.40;

            }else if(multComb.getSelectionModel().getSelectedIndex()==6){

                tempForMult = 1.59;

            }else{
                System.out.println("Select temperature multiplier");
            }

        });

        battEffLbl.setPrefWidth(150);
        battEffLbl.setPrefHeight(25);
        battEffLbl.setLayoutX(10);
        battEffLbl.setLayoutY(136);

        battEffTxt.setPrefWidth(150);
        battEffTxt.setPrefHeight(25);
        battEffTxt.setLayoutX(10);
        battEffTxt.setLayoutY(153);
        battEffTxt.setPromptText("%");

        second.setPrefWidth(200);
        second.setPrefHeight(25);
        second.setLayoutX(10);
        second.setLayoutY(111);

//PV title label
        PV_Para_Title.setPrefWidth(150);
        PV_Para_Title.setPrefHeight(25);
        PV_Para_Title.setLayoutX(10);
        PV_Para_Title.setLayoutY(626);

        powerLbl.setPrefWidth(150);
        powerLbl.setPrefHeight(25);
        powerLbl.setLayoutX(10);
        powerLbl.setLayoutY(220);

        powerTxt.setPrefWidth(150);
        powerTxt.setPrefHeight(25);
        powerTxt.setLayoutX(10);
        powerTxt.setLayoutY(313);
        powerTxt.setPromptText("Watt");

        pv_VoltLbl.setPrefWidth(150);
        pv_VoltLbl.setPrefHeight(25);
        pv_VoltLbl.setLayoutX(10);
        pv_VoltLbl.setLayoutY(220);

        pv_VoltTxt.setPrefWidth(150);
        pv_VoltTxt.setPrefHeight(25);
        pv_VoltTxt.setLayoutX(10);
        pv_VoltTxt.setLayoutY(195);
        pv_VoltTxt.setPromptText("Volt");

        coefLbl.setPrefWidth(150);
        coefLbl.setPrefHeight(25);
        coefLbl.setLayoutX(10);
        coefLbl.setLayoutY(220);

        coefTxt.setPrefWidth(150);
        coefTxt.setPrefHeight(25);
        coefTxt.setLayoutX(10);
        coefTxt.setLayoutY(313);
        coefTxt.setPromptText("Decimal");


        widthLbl.setPrefWidth(150);
        widthLbl.setPrefHeight(25);
        widthLbl.setLayoutX(10);
        widthLbl.setLayoutY(346);

        widthTxt.setPrefWidth(150);
        widthTxt.setPrefHeight(25);
        widthTxt.setLayoutX(10);
        widthTxt.setLayoutY(363);
        widthTxt.setPromptText("Meter");

        lengthLbl.setPrefWidth(150);
        lengthLbl.setPrefHeight(25);
        lengthLbl.setLayoutX(10);
        lengthLbl.setLayoutY(346);

        lengthTxt.setPrefWidth(150);
        lengthTxt.setPrefHeight(25);
        lengthTxt.setLayoutX(10);
        lengthTxt.setLayoutY(363);
        lengthTxt.setPromptText("Meter");

        dc_VoltLbl.setPrefWidth(150);
        dc_VoltLbl.setPrefHeight(25);
        dc_VoltLbl.setLayoutX(10);
        dc_VoltLbl.setLayoutY(388);

        dc_VoltTxt.setPrefWidth(150);
        dc_VoltTxt.setPrefHeight(25);
        dc_VoltTxt.setPromptText("Volt");

        aut_DaysLbl.setPrefWidth(150);
        aut_DaysLbl.setPrefHeight(25);
        aut_DaysLbl.setLayoutX(10);
        aut_DaysLbl.setLayoutY(647);

        aut_DaysTxt.setPrefWidth(150);
        aut_DaysTxt.setPrefHeight(25);
        aut_DaysTxt.setLayoutX(10);
        aut_DaysTxt.setLayoutY(664);
        aut_DaysTxt.setPromptText("Days");

        inv_EffLbl.setPrefWidth(150);
        inv_EffLbl.setPrefHeight(25);
        inv_EffLbl.setLayoutX(10);
        inv_EffLbl.setLayoutY(635);

        inv_EffTxt.setPrefWidth(150);
        inv_EffTxt.setPrefHeight(25);
        inv_EffTxt.setLayoutX(10);
        inv_EffTxt.setLayoutY(676);
        inv_EffTxt.setPromptText("%");

        third.setPrefWidth(150);
        third.setPrefHeight(25);
        third.setLayoutX(10);
        third.setLayoutY(111);

        nextWindow.setPrefWidth(150);
        nextWindow.setPrefHeight(25);
        nextWindow.setMnemonicParsing(false);
        nextWindow.setOnAction(event -> {

           output_interface.setLoadTxt(loadTxt);
           output_interface.setOptTxt(optTxt);
           output_interface.setNomTxt(nomTxt);
           output_interface.setCapTxt(capTxt);
           output_interface.setDodTxt(dodTxt);
           output_interface.setBattEffTxt(battEffTxt);
           output_interface.setTempForMult(tempForMult);
           output_interface.setPowerTxt(powerTxt);
           output_interface.setPv_VoltTxt(pv_VoltTxt);
           output_interface.setCoefTxt(coefTxt);
           output_interface.setWidthTxt(widthTxt);
           output_interface.setLengthTxt(lengthTxt);
           output_interface.setDc_VoltTxt(dc_VoltTxt);
           output_interface.setAut_DaysTxt(aut_DaysTxt);
           output_interface.setInv_EffTxt(inv_EffTxt);
            inputStage.close();
            output_interface.windowOpened(lat, longt);


        });



        backWindow.setPrefWidth(50);
        backWindow.setPrefHeight(25);
        backWindow.setMnemonicParsing(false);
        backWindow.setText("Back");
        backWindow.setOnAction(event -> {
            inputStage.close();
        new MapWindow().openMapWindow();
        });


        topTitle.getChildren().addAll(backWindow,sys_Para_Title);
        SystemPane.setLayoutX(10);
        SystemPane.setLayoutY(14);


        SystemPane.getChildren().addAll(load_Para_Title,loadLbl,loadTxt,optLbl,optTxt,first,batt_Para_Title,
                nomLbl,nomTxt,capLbl,capTxt,dodLbl,dodTxt,multLbl,multComb,battEffLbl,battEffTxt,second,PV_Para_Title,
                powerLbl,powerTxt,pv_VoltLbl,pv_VoltTxt,coefLbl,coefTxt,widthLbl,widthTxt,lengthLbl,lengthTxt,dc_VoltLbl,dc_VoltTxt,
                aut_DaysLbl,aut_DaysTxt,inv_EffLbl,inv_EffTxt,third,nextWindow);


        HBox.setMargin(sys_Para_Title,new Insets(0,0,0,120));
        HBox.setMargin(backWindow,new Insets(3,0,3,3));

        //Load margins
        VBox.setMargin(load_Para_Title,new Insets(0,0,0,180));
        VBox.setMargin(loadLbl,new Insets(5,0,0,180));
        VBox.setMargin(loadTxt,new Insets(5,0,0,180));
        VBox.setMargin(optLbl,new Insets(5,0,0,180));
        VBox.setMargin(optTxt,new Insets(5,0,0,180));
        //Battery margins
        VBox.setMargin(batt_Para_Title,new Insets(5,0,0,180));
        VBox.setMargin(nomLbl,new Insets(5,0,0,180));
        VBox.setMargin(nomTxt,new Insets(5,0,0,180));
        VBox.setMargin(capLbl,new Insets(5,0,0,180));
        VBox.setMargin(capTxt,new Insets(5,0,0,180));
        VBox.setMargin(dodLbl,new Insets(5,0,0,180));
        VBox.setMargin(dodTxt,new Insets(5,0,0,180));
        VBox.setMargin(multLbl,new Insets(5,0,0,180));
        VBox.setMargin(multComb,new Insets(5,0,0,180));
        VBox.setMargin(battEffLbl,new Insets(5,0,0,180));
        VBox.setMargin(battEffTxt,new Insets(5,0,0,180));
        //PV margins
        VBox.setMargin(PV_Para_Title,new Insets(5,0,0,180));
        VBox.setMargin(powerLbl,new Insets(5,0,0,180));
        VBox.setMargin(powerTxt,new Insets(5,0,0,180));
        VBox.setMargin(pv_VoltLbl,new Insets(5,0,0,180));
        VBox.setMargin(pv_VoltTxt,new Insets(5,0,0,180));
        VBox.setMargin(coefLbl,new Insets(5,0,0,180));
        VBox.setMargin(coefTxt,new Insets(5,0,0,180));
        VBox.setMargin(widthLbl,new Insets(5,0,0,180));
        VBox.setMargin(widthTxt,new Insets(5,0,0,180));
        VBox.setMargin(lengthLbl,new Insets(5,0,0,180));
        VBox.setMargin(lengthTxt,new Insets(5,0,0,180));
        VBox.setMargin(inv_EffLbl,new Insets(5,0,0,180));
        VBox.setMargin(inv_EffTxt,new Insets(5,0,0,180));
        VBox.setMargin(dc_VoltLbl,new Insets(5,0,0,180));
        VBox.setMargin(dc_VoltTxt,new Insets(5,0,0,180));
        VBox.setMargin(aut_DaysLbl,new Insets(5,0,0,180));
        VBox.setMargin(aut_DaysTxt,new Insets(5,0,0,180));
        VBox.setMargin(nextWindow,new Insets(0,0,10,180));

        insidePane.getChildren().add(SystemPane);
        insidePane.setLayoutX(30);

        centerRoot.setContent(insidePane);

        inputRoot.setPrefHeight(680);
        inputRoot.setPrefWidth(600);
        inputRoot.setTop(topTitle);
        inputRoot.setCenter(centerRoot);

        inputStage.setTitle("INPUT MENU");
        inputStage.setScene(new Scene(inputRoot, 500,680));
        inputStage.setResizable(false);
        inputStage.show();

    }
    //=====================================================================================================================================================================



}
