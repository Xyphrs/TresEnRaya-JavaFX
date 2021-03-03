package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Controller {
    @FXML private RadioButton radiobutton1,radiobutton2,radiobutton3;
    @FXML private Button play;
    @FXML private Button r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2;
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

        setDisabled();

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
            setEnabled();
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
        clearBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnMouseClicked(mouseEvent -> {
                    Button b = (Button) mouseEvent.getSource();
                    if (b.getText().equals("")) {
                        b.setText(turno);
                        cambiarJugador();
                        verifyWinner();
                        if (jugador1){
                            setDisabled();
                        } else if (jugador2) {
                            setDisabled();
                        } else if (empate) {
                            setDisabled();
                        }
                    }
                });
            }
        }
    }

    private void startCompPers() {
        clearBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnMouseClicked(mouseEvent -> {
                    Button b = (Button) mouseEvent.getSource();
                    if (b.getText().equals("")) {
                        b.setText(turno);
                        cambiarJugador();
                        verifyWinner();
                    }

                    Random random = new Random();
                    int nran1, nran2;
                    nran1 = random.nextInt(3);
                    nran2 = random.nextInt(3);


                    for (int k = 0; k < 1; k++){
                        if (buttons[nran1][nran2].getText().equals("")) {
                            buttons[nran1][nran2].setText(turno);
                            cambiarJugador();
                            verifyWinner();
                        } else {
                            k--;
                        }
                    }
                });
            }
        }
    }

    private void startComputer() {
        clearBoard();
        Random random = new Random();
        int nran1, nran2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                nran1 = random.nextInt(3);
                nran2 = random.nextInt(3);
                if (!buttons[nran1][nran2].getText().equals("")){
                    j--;
                } else if (buttons[nran1][nran2].getText().equals("")) {
                    buttons[nran1][nran2].setText(turno);
                    cambiarJugador();
                    verifyWinner();
                    if (jugador1 || jugador2 || empate) {
                        break;
                    }
                }
            }
        }
    }

    private void verifyWinner() {
        contador++;
        if (buttons[0][0].getText().equals("X") && buttons[0][1].getText().equals("X") && buttons[0][2].getText().equals("X")){
            jugador1 = true;
        } else if (buttons[1][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[1][2].getText().equals("X")) {
            jugador1 = true;
        } else if (buttons[2][0].getText().equals("X") && buttons[2][1].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            jugador1 = true;
        } else if (buttons[0][0].getText().equals("X") && buttons[1][0].getText().equals("X") && buttons[2][0].getText().equals("X")) {
            jugador1 = true;
        } else if (buttons[0][1].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][1].getText().equals("X")) {
            jugador1 = true;
        } else if (buttons[0][2].getText().equals("X") && buttons[1][2].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            jugador1 = true;
        } else if (buttons[0][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][2].getText().equals("X")) {
            jugador1 = true;
        } else if (buttons[0][2].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][0].getText().equals("X")) {
            jugador1 = true;
        } else if (buttons[0][0].getText().equals("0") && buttons[0][1].getText().equals("0") && buttons[0][2].getText().equals("0")){
            jugador2 = true;
        } else if (buttons[1][0].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[1][2].getText().equals("0")) {
            jugador2 = true;
        } else if (buttons[2][0].getText().equals("0") && buttons[2][1].getText().equals("0") && buttons[2][2].getText().equals("0")) {
            jugador2 = true;
        } else if (buttons[0][0].getText().equals("0") && buttons[1][0].getText().equals("0") && buttons[2][0].getText().equals("0")) {
            jugador2 = true;
        } else if (buttons[0][1].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[2][1].getText().equals("0")) {
            jugador2 = true;
        } else if (buttons[0][2].getText().equals("0") && buttons[1][2].getText().equals("0") && buttons[2][2].getText().equals("0")) {
            jugador2 = true;
        } else if (buttons[0][0].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[2][2].getText().equals("0")) {
            jugador2 = true;
        } else if (buttons[0][2].getText().equals("0") && buttons[1][1].getText().equals("0") && buttons[2][0].getText().equals("0")) {
            jugador2 = true;
        } else if (!jugador1 && !jugador2 && contador == 9) {
            empate = true;
        }

        if (jugador1) {
            System.out.println("Gana P1");
        } else if (jugador2) {
            System.out.println("Gana P2");
        } else if (empate) {
            System.out.println("Empate");
        }
    }

    public void cambiarJugador() {
        turno = turno.equals("X") ? "0" : "X";
    }

    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                contador = 0;
                jugador1 = false;
                jugador2 = false;
                empate = false;
            }
        }
    }

    private void setDisabled() {
        for (Button button : buttonList) {
            button.setDisable(true);
        }
    }

    private void setEnabled() {
        for (Button button : buttonList) {
            button.setDisable(false);
            button.setText("");
        }
    }
}
