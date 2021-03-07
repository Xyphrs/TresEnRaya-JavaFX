package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.*;

public class Controller {
    @FXML private Label winner;
    @FXML private RadioButton radiobutton1,radiobutton2,radiobutton3;
    @FXML private Button play;
    @FXML private Button r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2;
    List<Button> buttonList = new ArrayList<>();
    String winner1 = "Gana jugador 1";
    String winner2 = "Gana jugador 2";
    String empatado = "Empate";

    Button[][] buttons = new Button[3][3];
    boolean jugador1 = false;
    int contador = 0;
    boolean jugador2 = false;
    boolean empate = false;
    String turno = "X";

    // Este metodo es el encargado del menu, inciar variables y rellenar la matriz
    // Esto se ejecuta nada mas abrir la aplicacion
    // El menu es el toggle group, una vez le das al boton de start, mira la opcion que has seleccionado (linea 52 a 64)
    @FXML public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        buttonList = Arrays.asList(r0c0, r0c1, r0c2, r1c0, r1c1, r1c2, r2c0, r2c1, r2c2);

        winner.setText("");

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

    // Este metodo es el encargado de la logica detras de humano vs humano
    // Simplemente espera a que haga una accion el usuario y pone la X donde haya hecho click
    // Cuando ha hecho click cambia el jugador que es lo mismo que decir que cambia la X por 0
    // Si hay un ganador desactiva los botones
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

    // Este metodo es el encargado de la logica detras de ordenador vs humano.
    // Primero resetea y instancia el random, y empieza el bucle para la matriz.
    // De linea 97 a 106 es: El turno del jugador el cual es X y verfica si ha ganado, si hay alguien que ha ganado descativa todos los botones.
    // De linea 109 a 129 es: El turno de la maquina el cual es 0 y verfica si ha ganado, si hay alguien que ha ganado descativa todos los botones.
    // El if del los contadores esta para que: Si haces click encima de una casilla que esta ocupada no ponga la maquina un 0, el verfiyWinner siempre hace contador++.
    // El for de 1 esta para que la maquina solo ponga 1 0 y si la casilla donde tiene que ponerlo esta ocupada con algo hay k--
    // para que el bucle vuelva a repetirse hasta que encuentra una casilla libre.
    private void startCompPers() {
        Random random = new Random();
        clearBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnMouseClicked(mouseEvent -> {
                    Button b = (Button) mouseEvent.getSource();
                    if (b.getText().equals("")) {
                        b.setText(turno);
                        verifyWinner();
                    }
                    cambiarJugador();
                    if (jugador1 || jugador2 || empate) {
                        setDisabled();
                    }

                    int nran1, nran2;

                    if (contador == 1 || contador == 3 || contador == 5 || contador == 7) {
                        for (int k = 0; k < 1; k++) {
                            nran1 = random.nextInt(3);
                            nran2 = random.nextInt(3);
                            if (buttons[nran1][nran2].getText().equals("")) {
                                buttons[nran1][nran2].setText(turno);
                                verifyWinner();
                            } else if (!buttons[nran1][nran2].getText().equals("")) {
                                k--;
                                contador--;
                            }
                        }
                    } else if (contador == 9) {
                        verifyWinner();
                    }
                    cambiarJugador();
                    if (jugador1 || jugador2 || empate) {
                        setDisabled();
                    }

                });
            }
        }
    }

    // Este metodo es el encargado de la logica detras de ordenador vs ordenador
    // Primero resetea y instancia el random, y empieza el bucle para la matriz.
    // Dices dentro del bucle que los random son como los indices de la matriz, si ese campo esta vacio pon tu "equipo" (X o 0), si no vuelve a buscar un numero random.
    // Cuando ha hecho escrito en un boton cambia el jugador que es lo mismo que decir que cambia la X por 0
    // Si hay un ganador desactiva los botones y rompe el bucle
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

    // Este metodo es el encargado de verificar el ganador y de sumar el contador.
    // Tambien hace prints para que el debugging sea mas sencillo.
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
            winner.setText(winner1);
            System.out.println("Gana P1");
        } else if (jugador2) {
            winner.setText(winner2);
            System.out.println("Gana P2");
        } else if (empate) {
            winner.setText(empatado);
            System.out.println("Empate");
        }
    }

    // Este metodo es el encargado de cambiar de turno y darle valor.
    public void cambiarJugador() {
        turno = turno.equals("X") ? "0" : "X";
    }

    // Este metodo limpia el "tablero", y resetea variables importantes como el contador y quien ha ganado (boolean).
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

    //Este metodo desactiva todos los botones
    private void setDisabled() {
        for (Button button : buttonList) {
            button.setDisable(true);
        }
    }

    //Este metodo activa todos los botones
    private void setEnabled() {
        for (Button button : buttonList) {
            button.setDisable(false);
            button.setText("");
        }
    }
}
