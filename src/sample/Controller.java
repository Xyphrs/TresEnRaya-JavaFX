package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML private RadioButton radiobutton1;
    @FXML private RadioButton radiobutton2;
    @FXML private RadioButton radiobutton3;
    @FXML private Button play;
    @FXML private Button r0c0;
    @FXML private Button r0c1;
    @FXML private Button r0c2;
    @FXML private Button r1c0;
    @FXML private Button r1c1;
    @FXML private Button r1c2;
    @FXML private Button r2c0;
    @FXML private Button r2c1;
    @FXML private Button r2c2;

    @FXML public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();

        radiobutton1.setToggleGroup(toggleGroup);
        radiobutton1.setSelected(true);

        radiobutton2.setToggleGroup(toggleGroup);

        radiobutton3.setToggleGroup(toggleGroup);

        play.setOnMouseClicked(mouseEvent -> {
            if (radiobutton1.isSelected()) {
                startComputer();
                System.out.println("Start Ordenador vs Ordenador");
            } else if (radiobutton2.isSelected()) {
                startCompPers();
                System.out.println("Start Persona vs Ordenador");
            } else {
                startPerson();
                System.out.println("Start Persona vs Persona");

            }
        });

        r0c0.setOnMouseClicked(mouseEvent -> System.out.println("Row 0 Column 0"));
        r0c1.setOnMouseClicked(mouseEvent -> System.out.println("Row 0 Column 1"));
        r0c2.setOnMouseClicked(mouseEvent -> System.out.println("Row 0 Column 2"));
        r1c0.setOnMouseClicked(mouseEvent -> System.out.println("Row 1 Column 0"));
        r1c1.setOnMouseClicked(mouseEvent -> System.out.println("Row 1 Column 1"));
        r1c2.setOnMouseClicked(mouseEvent -> System.out.println("Row 1 Column 2"));
        r2c0.setOnMouseClicked(mouseEvent -> System.out.println("Row 2 Column 0"));
        r2c1.setOnMouseClicked(mouseEvent -> System.out.println("Row 2 Column 1"));
        r2c2.setOnMouseClicked(mouseEvent -> System.out.println("Row 2 Column 2"));


    }

    private void startPerson() {
    }

    private void startCompPers() {
    }

    private void startComputer() {
    }
}
