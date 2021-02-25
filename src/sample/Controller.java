package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML private RadioButton radiobutton1,radiobutton2,radiobutton3;
    @FXML private Button play;
    @FXML private Button r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2;
    private String winner;
    List<Button> buttonList = new ArrayList<>();
    Button[][] buttons = new Button[3][3];
    boolean jugador1 = false;
    int contador = 0;
    boolean jugador2 = false;
    boolean empate = false;
    String turno = "X";



    @FXML public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        buttonList = Arrays.asList(r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2);

        radiobutton1.setToggleGroup(toggleGroup);
        radiobutton1.setSelected(true);

        radiobutton2.setToggleGroup(toggleGroup);

        radiobutton3.setToggleGroup(toggleGroup);

        for (Button button : buttonList) {
            button.setDisable(true);
        }

        buttons[0][0] = r0c0;
        buttons[0][1] = r0c1;
        buttons[0][2] = r0c2;
        buttons[1][0] = r1c0;
        buttons[1][1] = r1c1;
        buttons[1][2] = r1c2;
        buttons[2][0] = r2c0;
        buttons[2][1] = r2c1;
        buttons[2][2] = r2c2;


        play.setOnAction(mouseEvent -> {

            for (Button button : buttonList) {
                button.setDisable(false);
                button.setText("");
            }

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
    }

    private void startPerson() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnMouseClicked(mouseEvent -> {
                    Button b = (Button) mouseEvent.getSource();
                    if (b.getText().equals("")) {
                        b.setText(turno);
                        cambiarJugador();
                        verifyWinner();
                        if (jugador1){
                            System.out.println("Gana X");
                        } else if (jugador2){
                            System.out.println("Gana 0");
                        } else if (empate) {
                            System.out.println("Empate");
                        }
                    }
                });
            }
        }
    }

    public void cambiarJugador() {
        turno = turno.equals("X") ? "0" : "X";
    }

    private void startCompPers() {
    }

    private void startComputer() {
        int turn = 0;

        while (winner == null) {
            if (turn == 0) {

                turn++;
            } else if (turn == 1){

                turn--;
            }
        }
    }

    private void verifyWinner() {
        contador++;
        jugador1 = false;
        jugador2 = false;
        if (buttons[0][0].getText().equals("X") && buttons[0][1].getText().equals("X") && buttons[0][2].getText().equals("X")){
            jugador1 = true;
            contador = 0;
        } else if (buttons[1][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[1][2].getText().equals("X")) {
            jugador1 = true;
            contador = 0;
        } else if (buttons[2][0].getText().equals("X") && buttons[2][1].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            jugador1 = true;
            contador = 0;
        } else if (buttons[0][0].getText().equals("X") && buttons[1][0].getText().equals("X") && buttons[2][0].getText().equals("X")) {
            jugador1 = true;
            contador = 0;
        } else if (buttons[0][1].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][1].getText().equals("X")) {
            jugador1 = true;
            contador = 0;
        } else if (buttons[0][2].getText().equals("X") && buttons[1][2].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            jugador1 = true;
            contador = 0;
        } else if (buttons[0][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            jugador1 = true;
            contador = 0;
        } else if (buttons[0][2].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][0].getText().equals("X")) {
            jugador1 = true;
            contador = 0;
        } else if (buttons[0][0].getText().equals("0") && buttons[0][1].getText().equals("0") && buttons[0][2].getText().equals("0")){
            jugador2 = true;
            contador = 0;
        } else if (buttons[1][0].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[1][2].getText().equals("0")) {
            jugador2 = true;
            contador = 0;
        } else if (buttons[2][0].getText().equals("0") && buttons[2][1].getText().equals("0") && buttons[2][2].getText().equals("0")) {
            jugador2 = true;
            contador = 0;
        } else if (buttons[0][0].getText().equals("0") && buttons[1][0].getText().equals("0") && buttons[2][0].getText().equals("0")) {
            jugador2 = true;
            contador = 0;
        } else if (buttons[0][1].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[2][1].getText().equals("0")) {
            jugador2 = true;
            contador = 0;
        } else if (buttons[0][2].getText().equals("0") && buttons[1][2].getText().equals("0") && buttons[2][2].getText().equals("0")) {
            jugador2 = true;
            contador = 0;
        } else if (buttons[0][0].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[2][2].getText().equals("0")) {
            jugador2 = true;
            contador = 0;
        } else if (buttons[0][2].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[2][0].getText().equals("0")) {
            jugador2 = true;
            contador = 0;
        } else if (!jugador2 && !jugador1 && contador == 9){
            empate = true;
            contador = 0;
        }
    }
}
