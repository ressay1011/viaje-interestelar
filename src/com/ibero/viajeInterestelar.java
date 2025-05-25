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

  // Metodo que crea el menu de seleccion de planeta destino y retorna la
  // información del planeta seleccionado
  private static String[] menuDestinationPlanet(String[] destinationPlanet) {

    // Define los planetas que se muestran en el menu de seleccion de planetas.
    String[] planets = { "Mercury", "Venus", "Mars", "Jupiter", "Saturn",
        "Uranus", "Neptune" };
    int selection;

    // Se agrega a las opciones del menu la opcion de volver al menu anterior.
    String[] options = new String[(planets.length + 1)];

    for (var i = 0; i < planets.length; i++) {
      options[i] = planets[i];
    }

    options[planets.length] = "Back";

    // Se crea y imprime el menu de seleccion de planetas
    selection = showMenu(options);

    // Valida si la opcion seleccionada es diferente de 0
    if (selection != 0) {

      // Guarda la información del planeta seleccionado para su posterior retorno
      destinationPlanet = destinationPlanet(selection, planets);
    }

    return destinationPlanet;
  }

  // Metodo que recibe el planeta seleccionado y la lista de planetas disponibles,
  // muestra por consola la informacion del planeta y la retorna
  private static String[] destinationPlanet(int option, String[] planets) {

    // Guarda la distancia de la tierra a los planetas en millones de kilometros.
    String[] planetDistance = { "91", "41", "225", "778", "1429", "2900", "4300" };

    // Guarda la descripción de los planetas.
    String[] planetDescription = {
        "is the smallest and closest planet to the Sun.%nIt has a rocky surface with extreme temperature changes, ranging from -180°C at night to 430°C during the day.%nIt orbits the Sun in just 88 days.",
        "is the second planet from the Sun and similar in size to Earth.%nIt has a thick, toxic atmosphere primarily made of carbon dioxide, which creates a runaway greenhouse effect, making it the hottest planet in the solar system.%nSurface temperatures can reach up to 470°C.%nVenus has no moons and rotates in the opposite direction to most planets, with a day longer than its year.",
        "is the fourth planet from the Sun, known as the \"Red Planet\" due to its reddish appearance caused by iron oxide on its surface.",
        "is the largest planet in the solar system, known for its massive size, strong magnetic field, and distinctive Great Red Spot, a giant storm.%nIt is a gas giant with no solid surface.",
        "is the second-largest planet in the solar system, famous for its stunning ring system made of ice and rock.%nIt is a gas giant with a predominantly hydrogen and helium atmosphere.",
        "is the seventh planet from the Sun and is known for its distinctive blue-green color, caused by methane in its atmosphere.%nIt is an ice giant with a mostly hydrogen and helium atmosphere, but it also contains water, ammonia, and methane.%nUranus has a unique rotation, spinning on its side with an axial tilt of 98 degrees.%nThis results in extreme seasonal variations.%nIt has 13 rings and 27 known moons.",
        "is the eighth and farthest planet from the Sun.%nIt is an ice giant with a deep blue color due to methane in its atmosphere.%nNeptune has strong winds, the fastest in the solar system, and large storm systems, including the Great Dark Spot.%nIt has 14 known moons, with Triton being the largest, and five rings.%nIts atmosphere is mostly hydrogen, helium, and methane."
    };

    // Se crea un nuevo String con la información a retornar. (Nombre del planeta y
    // distancia desde la tierra)
    String[] destinationPlanetInfo = new String[2];
    destinationPlanetInfo[0] = planets[option - 1];
    destinationPlanetInfo[1] = planetDistance[option - 1];

    // Imprime la información del planeta seleccionado
    System.out.printf(
        "%4$sThe selected planet is:%6$s %1$s located approximately %2$,d million kilometers from Earth %3$s%n%5$sPress enter to continue.%6$s",
        destinationPlanetInfo[0], Integer.parseInt(destinationPlanetInfo[1]),
        planetDescription[option - 1], GREEN, BLUE, RESET);
    input.nextLine(); // Gasta el enter

    return destinationPlanetInfo;
  }
}
