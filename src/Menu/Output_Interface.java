package Menu;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Output_Interface  {

    Double tempForMult;
    String year;
    Double firstTemp;
    //=====================================================================================================================================================================
    public void setLoadTxt(TextField loadTxt) {
        this.loadTxt = loadTxt;
    }

    public void setOptTxt(TextField optTxt) {
        this.optTxt = optTxt;
    }

    public void setNomTxt(TextField nomTxt) {
        this.nomTxt = nomTxt;
    }

    public void setCapTxt(TextField capTxt) {
        this.capTxt = capTxt;
    }

    public void setDodTxt(TextField dodTxt) {
        this.dodTxt = dodTxt;
    }

    public void setBattEffTxt(TextField battEffTxt) {
        this.battEffTxt = battEffTxt;
    }

    public void setPowerTxt(TextField powerTxt) {
        this.powerTxt = powerTxt;
    }

    public void setPv_VoltTxt(TextField pv_VoltTxt) {
        this.pv_VoltTxt = pv_VoltTxt;
    }

    public void setCoefTxt(TextField coefTxt) {
        this.coefTxt = coefTxt;
    }

    public void setWidthTxt(TextField widthTxt) {
        this.widthTxt = widthTxt;
    }

    public void setLengthTxt(TextField lengthTxt) {
        this.lengthTxt = lengthTxt;
    }

    public void setDc_VoltTxt(TextField dc_VoltTxt) {
        this.dc_VoltTxt = dc_VoltTxt;
    }

    public void setAut_DaysTxt(TextField aut_DaysTxt) {
        this.aut_DaysTxt = aut_DaysTxt;
    }

    public void setInv_EffTxt(TextField inv_EffTxt) {
        this.inv_EffTxt = inv_EffTxt;
    }
    //=====================================================================================================================================================================

    //Load
    TextField loadTxt = new TextField();
    TextField optTxt = new TextField();

    //Battery
    TextField nomTxt = new TextField();
    TextField capTxt = new TextField();
    TextField dodTxt = new TextField();
    ComboBox multComb = new ComboBox();
    TextField battEffTxt = new TextField();

    //PV
    TextField powerTxt = new TextField();
    TextField pv_VoltTxt = new TextField();
    TextField coefTxt = new TextField();
    TextField widthTxt = new TextField();
    TextField lengthTxt = new TextField();
    TextField dc_VoltTxt = new TextField();
    TextField aut_DaysTxt = new TextField();
    TextField inv_EffTxt = new TextField();
    String urlString=null;
    public Output_Interface(){

    }

    public void setTempForMult(Double tempForMult) {
        this.tempForMult = tempForMult;
    }

    public void calc(){
System.out.println(firstTemp);

    }
    //=====================================================================================================================================================================
    //Load weather information
    public XYChart.Series getRadiation() {
        return radiation;
    }

    public XYChart.Series getLoad() {
        return load;
    }

    public XYChart.Series getEnergy() {
        return energy;
    }

    public XYChart.Series getTemperature() {
        return temperature;
    }
    //=====================================================================================================================================================================

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final LineChart<String,Number> lineChart =
            new LineChart<String,Number>(xAxis,yAxis);

    XYChart.Series temperature = new XYChart.Series();
    XYChart.Series radiation = new XYChart.Series();
    XYChart.Series load = new XYChart.Series();
    XYChart.Series energy = new XYChart.Series();
    XYChart.Series angle = new XYChart.Series();
    JSONObject object =null;
    JSONArray array =null;
    JSONObject rad = null;
    JSONObject temp=null;
    ArrayList<Double> pOut_per_Module=null ;
    //=====================================================================================================================================================================

    public void generateGraphs_By_Loading_Weather_Data(String latitude, String longitude, String year){
        urlString ="https://power.larc.nasa.gov/cgi-bin/v1/DataAccess.py?request=execute&identifier=SinglePoint" +
                "&parameters=ALLSKY_SFC_SW_DWN,T2M&" +

                "startDate="+year+"&endDate="+year+

                "&userCommunity=SSE&tempAverage=INTERANNUAL&outputList=JSON" +
                "&lat="+latitude+"&lon="+longitude+"&user=anonymous";

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = read.readLine()) != null) {
                result.append(line);
            }
            read.close();
            object = new JSONObject(result.toString());

            array = object.getJSONArray("features");
            for (int i = 0; i < array.length(); i++) {
                rad =array.getJSONObject(i).getJSONObject("properties").getJSONObject("parameter").getJSONObject("ALLSKY_SFC_SW_DWN");
                temp =array.getJSONObject(i).getJSONObject("properties").getJSONObject("parameter").getJSONObject("T2M");
            }
            temperature.setName("Temperature data");
            radiation .setName("Radiation data");

            temperature.getData().add(new XYChart.Data("Jan",temp.getDouble("201801")));
            temperature.getData().add(new XYChart.Data("Feb",temp.getDouble("201802") ));
            temperature.getData().add(new XYChart.Data("Mar", temp.getDouble("201803")));
            temperature.getData().add(new XYChart.Data("Apr", temp.getDouble("201804")));
            temperature.getData().add(new XYChart.Data("May",  temp.getDouble("201805")));
            temperature.getData().add(new XYChart.Data("Jun",  temp.getDouble("201806")));
            temperature.getData().add(new XYChart.Data("Jul", temp.getDouble("201807")));
            temperature.getData().add(new XYChart.Data("Aug",  temp.getDouble("201808")));
            temperature.getData().add(new XYChart.Data("Sep",  temp.getDouble("201809")));
            temperature.getData().add(new XYChart.Data("Oct",  temp.getDouble("201810")));
            temperature.getData().add(new XYChart.Data("Nov",  temp.getDouble("201811")));
            temperature .getData().add(new XYChart.Data("Dec", temp.getDouble("201812")));

            radiation .getData().add(new XYChart.Data("Jan", getRad().getDouble("201801")));
            radiation .getData().add(new XYChart.Data("Feb", getRad().getDouble("201802") ));
            radiation .getData().add(new XYChart.Data("Mar",  getRad().getDouble("201803")));
            radiation .getData().add(new XYChart.Data("Apr",  getRad().getDouble("201804")));
            radiation .getData().add(new XYChart.Data("May",   getRad().getDouble("201805")));
            radiation .getData().add(new XYChart.Data("Jun",   getRad().getDouble("201806")));
            radiation .getData().add(new XYChart.Data("Jul",  getRad().getDouble("201807")));
            radiation .getData().add(new XYChart.Data("Aug",   getRad().getDouble("201808")));
            radiation .getData().add(new XYChart.Data("Sep",   getRad().getDouble("201809")));
            radiation .getData().add(new XYChart.Data("Oct",   getRad().getDouble("201810")));
            radiation .getData().add(new XYChart.Data("Nov",   getRad().getDouble("201811")));
            radiation .getData().add(new XYChart.Data("Dec",  getRad().getDouble("201812")));


            energy.setName("Energy of array");
            //Load
            pOut_per_Module = new ArrayList<Double>();

            power= Double.valueOf(loadTxt.getText());
            opt = Double.valueOf(optTxt.getText());
            load_Power =( power*opt) ;
            bEFF = Double.valueOf(battEffTxt.getText())/100;
            eff = Double.valueOf(inv_EffTxt.getText())/100;
            Pmax = Double.valueOf(powerTxt.getText());
            coef = Double.valueOf(coefTxt .getText());
            DCBusV =Double.valueOf(dc_VoltTxt.getText());
            Vmp = Double.valueOf(pv_VoltTxt .getText());
            mL = Double.valueOf(lengthTxt.getText());
            mW = Double.valueOf(widthTxt.getText());

            P_Maximum = Pmax*(1+coef*(temp.getDouble("201801")-25));
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201801")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201802")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201803")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201804")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201805")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201806")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201807")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201808")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201809")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201810")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201811")-25))/1000);
            pOut_per_Module.add(Pmax*(1+coef*(temp.getDouble("201812")-25))/1000);


            Preq = load_Power/(eff*bEFF);
            Pmodule = P_Maximum*3.5;
            n_PV = (Preq *1000)/(Pmodule);
            p_Series =  DCBusV/Vmp;
            p_Paralle = n_PV/p_Series;
            area_PV = n_PV*mL*mW;

            avgDailyOTxt.setText(String.valueOf(new DecimalFormat("#.##").format(Preq))+"KW");
            mNoTxt.setText(String.valueOf(Math.ceil(n_PV)));
            mSeriesTxt.setText(String.valueOf(Math.ceil(p_Series)));
            mParallelTxt.setText(String.valueOf(Math.ceil(p_Paralle)));

            arrayEffTxt.setText(String.valueOf(  new DecimalFormat("#.##").format(area_PV))+"m^2");

            capT = Double.valueOf(capTxt.getText());
            dd = Double.valueOf(dodTxt.getText())/100;
            Days = Double.valueOf(aut_DaysTxt .getText());
            CTotal = load_Power*1000*tempForMult*Days/(DCBusV*dd);
            nomV = Double.valueOf(nomTxt .getText());
            b_Series= (DCBusV/nomV);
            b_Parallel =  (CTotal/ capT);

            //Output results
            totalCapTxt.setText(String.valueOf(new DecimalFormat("#.##").format(CTotal))+"Ah");
            bNoTxt.setText(String.valueOf(Math.ceil(b_Series*b_Parallel)));
            bSeriesTxt.setText(String.valueOf(Math.ceil(b_Series)));
            bParallelTxt.setText(String.valueOf(  Math.ceil(b_Parallel)));

            energy.getData().add(new XYChart.Data("Jan",  getRad().getDouble("201801")*n_PV*( 1-0.15)*pOut_per_Module.get(0)));
            energy.getData().add(new XYChart.Data("Feb", getRad().getDouble("201802")*n_PV*( 1-0.15)* pOut_per_Module.get(1)));
            energy.getData().add(new XYChart.Data("Mar",  getRad().getDouble("201803")*n_PV*( 1-0.15)* pOut_per_Module.get(2)));
            energy.getData().add(new XYChart.Data("Apr",  getRad().getDouble("201804")*n_PV*( 1-0.15)*pOut_per_Module.get(3)));
            energy.getData().add(new XYChart.Data("May",   getRad().getDouble("201805")*n_PV*( 1-0.15)* pOut_per_Module.get(4)));
            energy.getData().add(new XYChart.Data("Jun",   getRad().getDouble("201806")*n_PV*( 1-0.15)* pOut_per_Module.get(5)));
            energy.getData().add(new XYChart.Data("Jul",  getRad().getDouble("201807")*n_PV*( 1-0.15)* pOut_per_Module.get(6)));
            energy.getData().add(new XYChart.Data("Aug",   getRad().getDouble("201808")*n_PV*( 1-0.15)* pOut_per_Module.get(7)));
            energy.getData().add(new XYChart.Data("Sep",   getRad().getDouble("201809")*n_PV*( 1-0.15)* pOut_per_Module.get(8)));
            energy.getData().add(new XYChart.Data("Oct",   getRad().getDouble("201810")*n_PV*( 1-0.15)* pOut_per_Module.get(9)));
            energy.getData().add(new XYChart.Data("Nov",   getRad().getDouble("201811")*n_PV*( 1-0.15)* pOut_per_Module.get(10)));
            energy.getData().add(new XYChart.Data("Dec",  getRad().getDouble("201812")*n_PV*( 1-0.15)* pOut_per_Module.get(11)));

//Parameters


            load.setName("Load data");
            load.getData().add(new XYChart.Data("Jan",pOut_per_Module.get(0)/1000));
            load.getData().add(new XYChart.Data("Feb",pOut_per_Module.get(1)/1000));
            load.getData().add(new XYChart.Data("Mar", pOut_per_Module.get(2)/1000));
            load.getData().add(new XYChart.Data("Apr", pOut_per_Module.get(3)/1000));
            load.getData().add(new XYChart.Data("May", pOut_per_Module.get(4)/1000));
            load.getData().add(new XYChart.Data("Jun",  pOut_per_Module.get(5)/1000));
            load.getData().add(new XYChart.Data("Jul", pOut_per_Module.get(6)/1000));
            load.getData().add(new XYChart.Data("Aug",  pOut_per_Module.get(7)/1000));
            load.getData().add(new XYChart.Data("Sep",  pOut_per_Module.get(8)/1000));
            load.getData().add(new XYChart.Data("Oct",  pOut_per_Module.get(9)/1000));
            load.getData().add(new XYChart.Data("Nov",  pOut_per_Module.get(10)/1000));
            load.getData().add(new XYChart.Data("Dec",  pOut_per_Module.get(11)/1000));


            solarChart.getData().addAll(radiation, temperature);
            powerChart.getData().addAll(load, energy);


            e_Data.add(getRad().getDouble("201801")*n_PV*( 1-0.15)*pOut_per_Module.get(0));
            e_Data.add(getRad().getDouble("201802")*n_PV*( 1-0.15)*pOut_per_Module.get(1));
            e_Data.add(getRad().getDouble("201803")*n_PV*( 1-0.15)*pOut_per_Module.get(2));
            e_Data.add(getRad().getDouble("201804")*n_PV*( 1-0.15)*pOut_per_Module.get(3));
            e_Data.add(getRad().getDouble("201805")*n_PV*( 1-0.15)*pOut_per_Module.get(4));
            e_Data.add(getRad().getDouble("201806")*n_PV*( 1-0.15)*pOut_per_Module.get(5));
            e_Data.add(getRad().getDouble("201807")*n_PV*( 1-0.15)*pOut_per_Module.get(6));
            e_Data.add(getRad().getDouble("201808")*n_PV*( 1-0.15)*pOut_per_Module.get(7));
            e_Data.add(getRad().getDouble("201809")*n_PV*( 1-0.15)*pOut_per_Module.get(8));
            e_Data.add(getRad().getDouble("201810")*n_PV*( 1-0.15)*pOut_per_Module.get(9));
            e_Data.add(getRad().getDouble("201811")*n_PV*( 1-0.15)*pOut_per_Module.get(10));
            e_Data.add(getRad().getDouble("201812")*n_PV*( 1-0.15)*pOut_per_Module.get(11));


            outPut.getChildren().add(new Energy_Table.energy_Table().generateTableValues(pOut_per_Module,e_Data));

            outputResult = "RESULT DATA"+
                    "\n"+"\n"+
                    "Site Location Coordinates"+"\n"+"\n"+
                    "latitude: "+latitude+"\n"+
                    "Longitude: "+longitude+"\n"+"\n"+
                    "================================================"+"\n"+
                    "Parameters Data "+"\n"+"\n"+

                    "Output Data "+"\n"+"\n"+
                    "LoadPower: "+"\t"+"\t"+  load_Power+"KWh"+"\n"+"\n"+
                    "PV Data "+"\t"+"\n"+"\n"+
                    "Maximum Power: "+"\t"+"\t"+twoDeceimal.format(P_Maximum/1000)+"KW"+"\n"+
                    "Power required: "+"\t"+twoDeceimal.format(Preq)+"KWh"+"\n"+
                    "Power of module: "+"\t"+twoDeceimal.format(Pmodule/1000)+"KWh"+"\n"+
                    "Number of PV: "+"\t"+"\t"+new DecimalFormat("##.##").format(n_PV)+"\n"+
                    "PV Series: "+"\t"+"\t"+new DecimalFormat("##.##").format( p_Series)+"\n"+
                    "Strings in  Parallel: "+"\t"+new DecimalFormat("##").format( p_Paralle)+"\n"+
                    "Effective area of array: "+twoDeceimal.format(area_PV) +"m^2"+"\n"+"\n"+

                    "Battery Data "+"\n"+"\n"+
                    "Total capacity: "+"\t"+twoDeceimal.format(CTotal)+"Ah"+"\n"+
                    "Capacity per day: "+"\t"+twoDeceimal.format(CTotal/Days)+"Ah"+"\n"+
                    "Battery in Series: "+"\t"+b_Series +"\n"+
                    "String in Parallel: "+"\t"+new DecimalFormat("##").format(b_Parallel )+"\n"+

                    "================================================"+"\n"+
                    "Temperature Data in Degrees"+"\n"+"\n"+
                    "January "+"\t"+temp.getDouble("201801")+"\n"+
                    "February "+"\t"+temp.getDouble("201802")+"\n"+
                    "March "+"\t"+"\t"+temp.getDouble("201803")+"\n"+
                    "April "+"\t"+"\t"+temp.getDouble("201804")+"\n"+
                    "May "+"\t"+"\t"+temp.getDouble("201805")+"\n"+
                    "June "+"\t"+"\t"+temp.getDouble("201806")+"\n"+
                    "July "+"\t"+"\t"+temp.getDouble("201807")+"\n"+
                    "August "+"\t"+"\t"+temp.getDouble("201808")+"\n"+
                    "September "+"\t"+temp.getDouble("201809")+"\n"+
                    "October "+"\t"+temp.getDouble("201810")+"\n"+
                    "November "+"\t"+temp.getDouble("201811")+"\n"+
                    "December "+"\t"+temp.getDouble("201812")+"\n"+
                    "==============================================="+"\n"+
                    "Radiation Data in KWh/m2/day"+"\n"+"\n"+
                    "January "+"\t"+rad.getDouble("201801")+"\n"+
                    "February "+"\t"+rad.getDouble("201802")+"\n"+
                    "March "+"\t"+"\t"+rad.getDouble("201803")+"\n"+
                    "April "+"\t"+"\t"+rad.getDouble("201804")+"\n"+
                    "May "+"\t"+"\t"+rad.getDouble("201805")+"\n"+
                    "June "+"\t"+"\t"+rad.getDouble("201806")+"\n"+
                    "July "+"\t"+"\t"+rad.getDouble("201807")+"\n"+
                    "August "+"\t"+"\t"+rad.getDouble("201808")+"\n"+
                    "September "+"\t"+rad.getDouble("201809")+"\n"+
                    "October"+"\t"+"\t"+rad.getDouble("201810")+"\n"+
                    "November "+"\t"+rad.getDouble("201811")+"\n"+
                    "December "+"\t"+rad.getDouble("201812")+"\n"+
                    "==============================================="+"\n"+
                    "Power and Energy Data"+"\n"+"\n"+
                    "Month \t "+"Power [KW]\t "+"Energy [KWh]"+"\n"+"\n"+
                    "January "+"\t"+twoDeceimal.format(pOut_per_Module.get(0))+" \t"+twoDeceimal.format(e_Data.get(0))+"\n"+
                    "February "+"\t"+twoDeceimal.format(pOut_per_Module.get(1))+" \t"+twoDeceimal.format(e_Data.get(1))+"\n"+
                    "March "+"\t"+"\t"+twoDeceimal.format(pOut_per_Module.get(2))+" \t"+twoDeceimal.format(e_Data.get(2))+"\n"+
                    "April "+"\t"+"\t"+twoDeceimal.format(pOut_per_Module.get(3))+" \t"+twoDeceimal.format(e_Data.get(3))+"\n"+
                    "May "+"\t"+"\t"+twoDeceimal.format(pOut_per_Module.get(4))+" \t"+twoDeceimal.format(e_Data.get(4))+"\n"+
                    "June "+"\t"+"\t"+twoDeceimal.format(pOut_per_Module.get(5))+" \t"+twoDeceimal.format(e_Data.get(5))+"\n"+
                    "July "+"\t"+"\t"+twoDeceimal.format(pOut_per_Module.get(6))+" \t"+twoDeceimal.format(e_Data.get(6))+"\n"+
                    "August "+"\t"+"\t"+twoDeceimal.format(pOut_per_Module.get(7))+" \t"+twoDeceimal.format(e_Data.get(7))+"\n"+
                    "September "+"\t"+twoDeceimal.format(pOut_per_Module.get(8))+" \t"+twoDeceimal.format(e_Data.get(8))+"\n"+
                    "October"+"\t"+"\t"+twoDeceimal.format(pOut_per_Module.get(9))+" \t"+twoDeceimal.format(e_Data.get(9))+"\n"+
                    "November "+"\t"+twoDeceimal.format(pOut_per_Module.get(10))+" \t"+twoDeceimal.format(e_Data.get(10))+"\n"+
                    "December "+"\t"+twoDeceimal.format(pOut_per_Module.get(11))+" \t"+twoDeceimal.format(e_Data.get(11))+"\n"+
                    "==============================================="+"\n"+"\n";
        }catch (Exception e){

        }

    }

    ArrayList<Double> e_Data = new ArrayList<Double>();
    String outputResult=null;
    String tiltresult=null;

    public JSONObject getRad() {
        return rad;
    }

    public JSONObject getTemp() {
        return temp;
    }
    //=====================================================================================================================================================================
ArrayList<Double> tiltAngle = new ArrayList<Double>();
    ArrayList<Double> tiltSegma = new ArrayList<Double>();
    ArrayList<Double> number = new ArrayList<Double> ();


    public void getAngle(String latitude){
        number.add(0,-20.92);
        number.add(1,-12.95);
        number.add(2,-2.42);
        number.add(3,9.41);
        number.add(4,18.79);
        number.add(5,23.09);
        number.add(6,21.18);
        number.add(7,13.45);
        number.add(8,2.22);
        number.add(9,-9.60);
        number.add(10,-18.91);
        number.add(11,-23.05);
        System.out.println("==========================================================");
        System.out.println("tilt angle");
        System.out.println(Double.valueOf(latitude)+"\n");

        try{

            for(int id =0; id <12; id++){
                Double someFormula= number.get(id);
                tiltSegma.add(id,someFormula);
                tiltAngle.add(id, Double.valueOf(latitude)-someFormula);

            }
            outPut.getChildren().add(new tilt_Angle_Table.tilt_Data().tilt_Data(tiltSegma,tiltAngle));
            tiltresult= "Tilt and Segma and "+"\n"+"\n"+
                    "Month \t "+"Tilt Angle [degrees]\t "+"Segma"+"\n"+"\n"+
                    "January "+"\t"+twoDeceimal.format(tiltAngle.get(0))+"\t"+"\t"+twoDeceimal.format(tiltSegma.get(0))+"\n"+
                    "February "+"\t"+twoDeceimal.format(tiltAngle.get(1))+"\t"+"\t"+twoDeceimal.format(tiltSegma.get(1))+"\n"+
                    "March "+"\t"+"\t"+twoDeceimal.format(tiltAngle.get(2))+"\t"+"\t"+twoDeceimal.format(tiltSegma.get(2))+"\n"+
                    "April "+"\t"+"\t"+twoDeceimal.format(tiltAngle.get(3))+"\t"+" \t "+twoDeceimal.format(tiltSegma.get(3))+"\n"+
                    "May "+"\t"+"\t"+twoDeceimal.format(tiltAngle.get(4))+"\t"+"\t "+twoDeceimal.format(tiltSegma.get(4))+"\n"+
                    "June "+"\t"+"\t"+twoDeceimal.format(tiltAngle.get(5))+"\t"+"\t "+twoDeceimal.format(tiltSegma.get(5))+"\n"+
                    "July "+"\t"+"\t"+twoDeceimal.format(tiltAngle.get(6))+"\t"+"\t "+twoDeceimal.format(tiltSegma.get(6))+"\n"+
                    "August "+"\t"+"\t"+twoDeceimal.format(tiltAngle.get(7))+"\t"+"\t "+twoDeceimal.format(tiltSegma.get(7))+"\n"+
                    "September "+"\t"+twoDeceimal.format(tiltAngle.get(8))+"\t"+"\t "+twoDeceimal.format(tiltSegma.get(8))+"\n"+
                    "October"+"\t"+"\t"+twoDeceimal.format(tiltAngle.get(9))+"\t"+"\t"+twoDeceimal.format(tiltSegma.get(9))+"\n"+
                    "November "+"\t"+twoDeceimal.format(tiltAngle.get(10))+"\t"+"\t"+twoDeceimal.format(tiltSegma.get(10))+"\n"+
                    "December "+"\t"+twoDeceimal.format(tiltAngle.get(11))+"\t"+"\t"+twoDeceimal.format(tiltSegma.get(11))+"\n"+
                    "==============================================="+"\n"+"\n";

        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

    //=====================================================================================================================================================================

    //=====================================================================================================================================================================


    private BorderPane outputRoot = new BorderPane();
    private AnchorPane outPut = new  AnchorPane();

   // private AnchorPane inside_Root = new AnchorPane();
    private HBox bottomRoot = new HBox();

    private Label OutputTitle = new Label("OUTPUT RESULTS");



    private Button pdf_Generate = new Button();
    private Button result_Generate = new Button();

    //defining the axes for solar radiation graph
    final CategoryAxis solarX_Axis = new CategoryAxis(); // Plot against month
    final NumberAxis solarY_Axis = new NumberAxis();
    //defining the axes for solar radiation graph
    final CategoryAxis powerX_Axis = new CategoryAxis(); // Plot against month
    final NumberAxis powerY_Axis = new NumberAxis();

    //creating the line chart with two axis created above
    //creating the line chart with two axis created above
    final LineChart<String, Number> solarChart = new LineChart<>(solarX_Axis, solarY_Axis);
    final LineChart<String, Number> powerChart = new LineChart<>(powerX_Axis, powerY_Axis);

    private Scene scene ;
    private Stage primary;
    private String latitude=null;
    private String longitude=null;

    //=====================================================================================================================================================================
    //PV
    Double Pmax;
    Double Vmp;
    Double coef ;
    Double mL;
    Double mW;
    Double eff;
    Double DCBusV;
    Double Days;

    //Batterry
    Double nomV;
    Double capT;
    Double dd;

    Double bEFF;

    //Load
    Double power;
    Double opt;
    // private String multData;

    //Calculations
    Double load_Power;
    //PV
    Double ambTemp;
    Double Preq;
    Double Pmodule;
    Double P_Maximum;
    Double n_PV;
    Double p_Series;
    Double p_Paralle;
    Double area_PV;

    //Battery
    Double CTotal;
    Double b_Series;
    Double b_Parallel;
    Double n_Batt;
    Double totalAutonomyD;

    //=====================================================================================================================================================================
    //PV modules sizing output information
    private Label pvOTitle = new Label("PV");
    private Label avgDailyO = new Label("Average Daily Output");
    private Label mNo = new Label("Module Count");
    private Label mSeries = new Label("Module in Series");
    private Label mParallel = new Label("Strings in Parallel");
    private Label arrayEff = new Label("Array Effective Area");

    private TextField avgDailyOTxt = new TextField();
    private TextField mNoTxt = new TextField();
    private TextField mSeriesTxt = new TextField();
    private TextField mParallelTxt = new TextField();
    private TextField arrayEffTxt = new TextField();

    //Battery sizing information
    private Label bOTitle = new Label("Batteries");
    private Label totalCap = new Label("Total Capacity");
    private Label CapPerDay = new Label("Capacity/day");
    private Label bNo = new Label("Batteries Count");
    private Label bSeries = new Label("Batteries in Series");
    private Label bParallel = new Label("Strings in Parallel");
    private TextField totalCapTxt = new TextField();
    private TextField CapPerDayTxt = new TextField();
    private TextField bNoTxt = new TextField();
    private TextField bSeriesTxt = new TextField();
    private TextField bParallelTxt = new TextField();
    //Containers for input and output interfaces
    private ScrollPane oututScrollPane = new ScrollPane();


    DecimalFormat twoDeceimal = new DecimalFormat("#.##");


    //=====================================================================================================================================================================

    public void getCoordinates(String lat, String longt){
        latitude= lat;
        longitude= longt;
        System.out.println("Latitude: "+latitude +"\n"+"Longitude: "+longitude);

    }


    //=====================================================================================================================================================================
    public void pdf_Generate(){
        pdf_Generate.setText("Save Result Report");
        pdf_Generate.setPrefHeight(25);
        pdf_Generate.setPrefWidth(150);
        pdf_Generate.setLayoutX(14);
        pdf_Generate.setMnemonicParsing(false);
        Stage stage=null;
        FileChooser chooseDirectory = new FileChooser();

        chooseDirectory.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File",".txt"));
        chooseDirectory.setTitle("Save result");
        chooseDirectory.setInitialFileName("untitled.txt");


        pdf_Generate.setOnAction(event -> {

            File selectedFile = chooseDirectory.showSaveDialog(stage);
            try{
                String path= selectedFile.getCanonicalPath();
                //Create the file
                if (selectedFile.createNewFile()){
                    System.out.println("File is created!");
                }else{
                    System.out.println("File already exists.");
                }
                FileWriter out = new FileWriter(path);
                out.write(outputResult+tiltresult);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("File Created!");
                alert.showAndWait();
                out.close();


            }catch(Exception e){

            }
        });


    }
    public void generate_Result(){
        result_Generate.setText("Generate Results");
        result_Generate.setPrefHeight(25);
        result_Generate.setPrefWidth(150);
        result_Generate.setLayoutX(200);
        result_Generate.setMnemonicParsing(false);
        result_Generate.setOnAction(event -> {

        });
    }
    public void generate_PowerChart(){

        powerChart.setTitle("Power Chart");
        powerChart.setAnimated(false); // disable animations
        powerChart.setLayoutX(440);
        powerChart.setLayoutY(20);
        powerChart.setMaxSize(450,360);
        powerX_Axis.setLabel("Months");
        powerX_Axis.setAnimated(false); // axis animations are removed
        powerY_Axis.setLabel("Energy KWh");
        powerY_Axis.setAnimated(false); // axis animations are removed

    }
    public void generate_SolarGraph(){
        solarChart.setTitle("Solar radiation Chart");
        solarChart.setAnimated(false); // disable animations
        solarChart.setLayoutX(8);
        solarChart.setLayoutY(20);
        solarChart.setMaxSize(450,360);
        solarX_Axis.setLabel("Months");
        solarX_Axis.setAnimated(false); // axis animations are removed
        solarY_Axis.setLabel("Irradiation KWh/m2/day");
        solarY_Axis.setAnimated(false); // axis animations are removed

    }
    //=====================================================================================================================================================================
    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    //=====================================================================================================================================================================


    public void windowOpened(String latitude, String longitude){
        this.latitude= latitude;
        this.longitude= longitude;

        OutputTitle.setLayoutX(420);
        OutputTitle.setLayoutY(8);

        pdf_Generate();
        generate_PowerChart();
        generate_SolarGraph();

        generate_Result();
       // result_Get();
        result_Generate.setOnAction(event -> {
            generateGraphs_By_Loading_Weather_Data(latitude,longitude,"2018");
            getAngle(latitude);

        });



//OUTPUT INTERFACE
        pvOTitle.setLayoutX(20);
        pvOTitle.setLayoutY(400);
        pvOTitle.setPrefHeight(30);
        pvOTitle.setPrefWidth(80);

        avgDailyO.setLayoutX(80);
        avgDailyO.setLayoutY(400);
        avgDailyO.setPrefHeight(30);
        avgDailyO.setPrefWidth(150);

        mNo.setLayoutX(230);
        mNo.setLayoutY(400);
        mNo.setPrefHeight(30);
        mNo.setPrefWidth(200);

        mSeries.setLayoutX(380);
        mSeries.setLayoutY(400);
        mSeries.setPrefHeight(30);
        mSeries.setPrefWidth(150);

        mParallel.setLayoutX(530);
        mParallel.setLayoutY(400);
        mParallel.setPrefHeight(30);
        mParallel.setPrefWidth(150);

        arrayEff.setLayoutX(680);
        arrayEff.setLayoutY(400);
        arrayEff.setPrefHeight(30);
        arrayEff.setPrefWidth(150);

        avgDailyOTxt.setLayoutX(80);
        avgDailyOTxt.setLayoutY(430);
        avgDailyOTxt.setPrefHeight(30);
        avgDailyOTxt.setPrefWidth(120);
        avgDailyOTxt.setEditable(false);

        mNoTxt.setLayoutX(230);
        mNoTxt.setLayoutY(430);
        mNoTxt.setPrefHeight(30);
        mNoTxt.setPrefWidth(50);
        mNoTxt.setEditable(false);

        mSeriesTxt.setLayoutX(380);
        mSeriesTxt.setLayoutY(430);
        mSeriesTxt.setPrefHeight(30);
        mSeriesTxt.setPrefWidth(50);
        mSeriesTxt.setEditable(false);

        mParallelTxt.setLayoutX(530);
        mParallelTxt.setLayoutY(430);
        mParallelTxt.setPrefHeight(30);
        mParallelTxt.setPrefWidth(50);
        mParallelTxt.setEditable(false);

        arrayEffTxt.setLayoutX(680);
        arrayEffTxt.setLayoutY(430);
        arrayEffTxt.setPrefHeight(30);
        arrayEffTxt.setPrefWidth(100);
        arrayEffTxt.setEditable(false);
        //Battery
        bOTitle.setLayoutX(20);
        bOTitle.setLayoutY(500);
        bOTitle.setPrefHeight(30);
        bOTitle.setPrefWidth(80);

        totalCap .setLayoutX(80);
        totalCap .setLayoutY(500);
        totalCap .setPrefHeight(30);
        totalCap .setPrefWidth(150);

        CapPerDay .setLayoutX(230);
        CapPerDay .setLayoutY(500);
        CapPerDay .setPrefHeight(30);
        CapPerDay .setPrefWidth(200);

        bNo.setLayoutX(380);
        bNo.setLayoutY(500);
        bNo.setPrefHeight(30);
        bNo.setPrefWidth(150);

        bSeries.setLayoutX(530);
        bSeries.setLayoutY(500);
        bSeries.setPrefHeight(30);
        bSeries.setPrefWidth(150);

        bParallel.setLayoutX(680);
        bParallel.setLayoutY(500);
        bParallel.setPrefHeight(30);
        bParallel.setPrefWidth(150);

        totalCapTxt.setLayoutX(80);
        totalCapTxt.setLayoutY(530);
        totalCapTxt.setPrefHeight(30);
        totalCapTxt.setPrefWidth(120);
        totalCapTxt.setEditable(false);

        CapPerDayTxt.setLayoutX(230);
        CapPerDayTxt.setLayoutY(530);
        CapPerDayTxt.setPrefHeight(30);
        CapPerDayTxt.setPrefWidth(50);
        CapPerDayTxt.setEditable(false);

        bNoTxt.setLayoutX(380);
        bNoTxt.setLayoutY(530);
        bNoTxt.setPrefHeight(30);
        bNoTxt.setPrefWidth(50);
        bNoTxt.setEditable(false);

        bSeriesTxt.setLayoutX(530);
        bSeriesTxt.setLayoutY(530);
        bSeriesTxt.setPrefHeight(30);
        bSeriesTxt.setPrefWidth(50);
        bSeriesTxt.setEditable(false);

        bParallelTxt.setLayoutX(680);
        bParallelTxt.setLayoutY(530);
        bParallelTxt.setPrefHeight(30);
        bParallelTxt.setPrefWidth(100);
        bParallelTxt.setEditable(false);


        outPut.setPrefWidth(800);
        outPut.setPrefHeight(950);

        outPut.getChildren().addAll(solarChart,powerChart,pvOTitle,mNo,mSeries,
                avgDailyO,mParallel,arrayEff,
                mNoTxt,mSeriesTxt, avgDailyOTxt,mParallelTxt,arrayEffTxt,bOTitle,
                totalCap  ,bNo ,bSeries ,bParallel,
                totalCapTxt ,bNoTxt ,bSeriesTxt ,bParallelTxt);

        oututScrollPane.setContent(outPut);
        oututScrollPane.setLayoutX(15);
        oututScrollPane.setLayoutY(40);
      //  oututScrollPane.setPrefHeight(600);
        oututScrollPane.setPrefWidth(930);


        bottomRoot.getChildren().addAll(result_Generate,pdf_Generate);
        HBox.setMargin(result_Generate, new Insets(5,30,10,10));
        HBox.setMargin(pdf_Generate, new Insets(5,0,10,0));
       // HBox.setMargin(OutputTitle,new Insets(0,0,0,120));
        outPutBanner.getChildren().add(OutputTitle);
        outPutBanner.setAlignment(Pos.CENTER);
        bottomRoot.setAlignment(Pos.CENTER);

        outputRoot.setCenter(oututScrollPane);
        outputRoot.setTop(OutputTitle);
        outputRoot.setBottom(bottomRoot);

        scene = new Scene(outputRoot,950,680);
        primary = new Stage();
        primary.setTitle("USER INPUT MENU");
        primary.setResizable(false);
        // primary.sizeToScene();
        primary.setScene(scene);
        //primary.setMaximized(true);
        primary.show();


    }
    HBox outPutBanner = new HBox();




}
