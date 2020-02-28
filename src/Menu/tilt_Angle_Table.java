package Menu;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class tilt_Angle_Table {

    public static class tilt_Data{

        private TableView<tiltAngle> table = new TableView<tiltAngle>();
        private final ObservableList<tiltAngle> data =
                FXCollections.observableArrayList(
                        new tiltAngle("", "", "")
                );

        public TableView tilt_Data(ArrayList<Double> sigmaData,ArrayList<Double> tiltData ){
            table.setEditable(false);
            table.setPrefSize(400,300);
            table.setLayoutX(80);
            table.setLayoutY(600);

            TableColumn month = new TableColumn("Month");
            month.setCellValueFactory(new PropertyValueFactory<tiltAngle,String>("month"));

            TableColumn segma = new TableColumn("Segma");
            segma.setCellValueFactory(
                    new PropertyValueFactory<tiltAngle,String>("segma")
            );

            TableColumn tilt = new TableColumn("Tilt");

            tilt.setCellValueFactory(
                    new PropertyValueFactory<tiltAngle,String>("tilt")
            );
            month.setPrefWidth(130);
            segma.setPrefWidth(130);
            tilt.setPrefWidth(130);
            data.add(new tiltAngle("1",String.valueOf(sigmaData.get(0)),String.valueOf(tiltData.get(0))));
            data.add(new tiltAngle("2",String.valueOf(sigmaData.get(1)),String.valueOf(tiltData.get(1))));
            data.add(new tiltAngle("3",String.valueOf(sigmaData.get(2)),String.valueOf(tiltData.get(2))));
            data.add(new tiltAngle("4",String.valueOf(sigmaData.get(3)),String.valueOf(tiltData.get(3))));
            data.add(new tiltAngle("5",String.valueOf(sigmaData.get(4)),String.valueOf(tiltData.get(4))));
            data.add(new tiltAngle("6",String.valueOf(sigmaData.get(5)),String.valueOf(tiltData.get(5))));
            data.add(new tiltAngle("7",String.valueOf(sigmaData.get(6)),String.valueOf(tiltData.get(6))));
            data.add(new tiltAngle("8",String.valueOf(sigmaData.get(7)),String.valueOf(tiltData.get(7))));
            data.add(new tiltAngle("9",String.valueOf(sigmaData.get(8)),String.valueOf(tiltData.get(8))));
            data.add(new tiltAngle("10",String.valueOf(sigmaData.get(9)),String.valueOf(tiltData.get(9))));
            data.add(new tiltAngle("11",String.valueOf(sigmaData.get(10)),String.valueOf(tiltData.get(10))));
            data.add(new tiltAngle("12",String.valueOf(sigmaData.get(11)),String.valueOf(tiltData.get(11))));

            table.setItems(data);
            table.getColumns().addAll(month, segma, tilt);
            return table;
        }
    }
    public static class tiltAngle{
        public tiltAngle(String month, String segma, String tilt_angle) {

            this.month = new SimpleStringProperty(month);
            this.segma = new SimpleStringProperty(segma);
            this.tilt_angle =  new SimpleStringProperty(tilt_angle);
        }

        public String getMonth() {
            return month.get();
        }

        public void setMonth(String month) {
            this.month.set(month);
        }

        public String getSegma() {
            return segma.get();
        }

        public void setSegma(String segma) {
            this.segma.set(segma);
        }

        public String getTilt_angle() {
            return tilt_angle.get();
        }


        public void setTilt_angle(String tilt_angle) {
            this.tilt_angle.set(tilt_angle);
        }

        private final SimpleStringProperty month;
        private final SimpleStringProperty segma;
        private final SimpleStringProperty tilt_angle;
    }

}
