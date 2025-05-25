package com.ibero;

import java.util.Random;
import java.util.Scanner;

public class viajeInterestelar {
  static final String RED = "\u001B[31m";
  static final String GREEN = "\u001B[32m";
  static final String YELLOW = "\u001B[33m";
  static final String BLUE = "\u001B[34m";
  static final String RESET = "\u001B[0m";

  // Static variables used throughout the project
  static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {

    // Selected planet information
    String[] destinationPlanet = { "" };

    // Selected spaceship information
    String[] selectedSpaceShip = { "" };

    // Travel duration calculated based on the selected spaceship's speed and the
    // distance to the selected planet
    var travelDuration = 0d;

    // Error menssage to display in the menu
    var errorMsg = "";

    var fix = "=";
    fix = fix.repeat(60);

    while (true) {
      int option;

      // Validación de si hay una nave seleccionada y un planeta seleccionado para
      // calcular la duración del viaje
      if (!destinationPlanet[0].isBlank() && !selectedSpaceShip[0].isBlank()) {
        travelDuration = calTravelDuration(destinationPlanet[1], selectedSpaceShip[1]);
      }

      option = mainMenu(destinationPlanet[0], selectedSpaceShip, travelDuration, errorMsg);

      switch (option) {

        // Opcion 0 - Salir del programa
        case 0:
          System.out.printf("%2$s%4$s%3$s%n%1$sExiting the program...%3$s", BLUE, GREEN, RESET, fix);
          System.exit(0);
          break;

        // Opcion 1 - Seleccionar planeta de destino
        case 1:
          destinationPlanet = menuDestinationPlanet(destinationPlanet);
          errorMsg = "";
          break;

        // Opcion 2 - Seleccionar nave
        case 2:
          selectedSpaceShip = menuSpaceShip(selectedSpaceShip);
          errorMsg = "";
          break;

        // Opcion 3 - Iniciar viaje
        case 3:

          // Validación de si hay una nave seleccionada y un planeta seleccionado para
          // poder iniciar el viaje
          if (!destinationPlanet[0].isBlank() && !selectedSpaceShip[0].isBlank()) {
            calTravelProgress(destinationPlanet, selectedSpaceShip, travelDuration);
            errorMsg = "";
          } else if (!destinationPlanet[0].isBlank()) {
            errorMsg = "Error: Please select a spaceship before starting the simulation.%n";
          } else if (!selectedSpaceShip[0].isBlank()) {
            errorMsg = "Error: Please select a destination planet before starting the simulation.%n";
          } else {
            errorMsg = "Error: Please select a destination planet and a spaceship before starting the simulation.%n";
          }
          break;

        // El programa en su curso normal nunca deberia llegar aca
        default:
          System.out.println("Como rayos llegaste aqui?");
          // Con fé XD
          break;
      }
    }
  }
}