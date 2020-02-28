package Menu;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Energy_Table {

    public static class energy_Table{
        private TableView<EnergyData> table = new TableView<EnergyData>();
        private final ObservableList<EnergyData> data =
                FXCollections.observableArrayList(
                        new EnergyData("", "", "")

                );
        public TableView<EnergyData> generateTableValues(ArrayList<Double> sigmaData, ArrayList<Double> tiltData){
            table.setEditable(false);
            table.setPrefSize(400,300);
            table.setLayoutX(510);
            table.setLayoutY(600);


            TableColumn month = new TableColumn("Month");
            month.setCellValueFactory(new PropertyValueFactory<EnergyData,String>("month"));

            TableColumn power = new TableColumn("Power KW");
            power.setCellValueFactory(
                    new PropertyValueFactory<EnergyData,String>("power")
            );

            TableColumn energy = new TableColumn("Energy KWh");

            energy.setCellValueFactory(
                    new PropertyValueFactory<EnergyData,String>("energy")
            );
            month.setPrefWidth(130);
            power.setPrefWidth(130);
            energy.setPrefWidth(130);

            table.setItems(data);
            data.add(new EnergyData("1",String.valueOf(sigmaData.get(0)),String.valueOf(tiltData.get(0))));
            data.add(new EnergyData("2",String.valueOf(sigmaData.get(1)),String.valueOf(tiltData.get(1))));
            data.add(new EnergyData("3",String.valueOf(sigmaData.get(2)),String.valueOf(tiltData.get(2))));
            data.add(new EnergyData("4",String.valueOf(sigmaData.get(3)),String.valueOf(tiltData.get(3))));
            data.add(new EnergyData("5",String.valueOf(sigmaData.get(4)),String.valueOf(tiltData.get(4))));
            data.add(new EnergyData("6",String.valueOf(sigmaData.get(5)),String.valueOf(tiltData.get(5))));
            data.add(new EnergyData("7",String.valueOf(sigmaData.get(6)),String.valueOf(tiltData.get(6))));
            data.add(new EnergyData("8",String.valueOf(sigmaData.get(7)),String.valueOf(tiltData.get(7))));
            data.add(new EnergyData("9",String.valueOf(sigmaData.get(8)),String.valueOf(tiltData.get(8))));
            data.add(new EnergyData("10",String.valueOf(sigmaData.get(9)),String.valueOf(tiltData.get(9))));
            data.add(new EnergyData("11",String.valueOf(sigmaData.get(10)),String.valueOf(tiltData.get(10))));
            data.add(new EnergyData("12",String.valueOf(sigmaData.get(11)),String.valueOf(tiltData.get(11))));
            table.getColumns().addAll(month, power, energy);

            return table;
        }
    }

    public static class EnergyData {
        public String getMonth() {
            return month.get();
        }



        public void setMonth(String month) {
            this.month.set(month);
        }

        public String getPower() {
            return power.get();
        }



        public void setPower(String power) {
            this.power.set(power);
        }

        public String getEnergy() {
            return energy.get();
        }


        public void setEnergy(String energy) {
            this.energy.set(energy);
        }

        public EnergyData(String month, String power, String energy) {

            this.month =new SimpleStringProperty(month) ;
            this.power =new SimpleStringProperty(power);
            this.energy = new SimpleStringProperty(energy);
        }

        private final SimpleStringProperty month;
        private final SimpleStringProperty power;
        private final SimpleStringProperty energy;
    }
}
