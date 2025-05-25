// Metodo que crea el menu de seleccion de naves y retorna información de la
// nave seleccionada
private static String[] menuSpaceShip(String[] selectedSpaceShip) {

    // Define las naves que se muestran en el menu de seleccion de naves.
    String[] spaceShips = { "Red dwarf", "Discovery", "Millennium falcon" };
    int selection = -1;

    // Se agrega a las opciones del menu la opcion de volver al menu anterior.
    String[] options = new String[(spaceShips.length + 1)];

    for (var i = 0; i < spaceShips.length; i++) {
        options[i] = spaceShips[i];
    }

    options[spaceShips.length] = "Back";

    do {

        // imprime el menu de selección de la nave.
        selection = showMenu(options);

        // Valida si la opcion seleccionada es diferente de 0.
        if (selection != 0) {

            // Guarda la información de la nave seleccionada para posteriormente retornarla.
            selectedSpaceShip = selectedSpaceShip(selection, spaceShips, selectedSpaceShip);
        }
    } while (selection != 0 && selectedSpaceShip[0].isBlank());

    return selectedSpaceShip;
}

/*
 * Metodo que recibe la nave seleccionada y la lista de naves disponible,
 * imprime la informacion de la nave para posteriormente retornarla y le pide al
 * usuario la cantidad de pasajeros a viajar.
 */
private static String[] selectedSpaceShip(int option, String[] spaceShips, String[] selectedSpaceShip) {

    // Variable temporal para confirmar la seleccion de la nave
    var confirmation = "";

    // Datos de la nave
    // Velocidad maxima de cada una de las 3 naves en Km/h
    String[] spaceShipsMaxVel = { "36400", "28000", "42000" };

    // Capacidad maxima de cada una de las 3 naves (numero recomendado de pasajeros)
    String[] spaceShipsMaxCapacity = { "10", "25", "5" };

    // Guarda el numero de pasajeros que viajan como un String para su posterior
    // procesamiento
    var spaceShipsCapacity = "";

    // Variable que guarda temporalmente el numero de pasajeros para realizar las
    // validaciones
    var passengersTemp = 0;

    // Se guarda toda la información de la nave seleccionada en el array a retornar
    String[] tempSelectedSpaceShip = new String[4];
    tempSelectedSpaceShip[0] = spaceShips[option - 1];
    tempSelectedSpaceShip[1] = spaceShipsMaxVel[option - 1];
    tempSelectedSpaceShip[2] = spaceShipsMaxCapacity[option - 1];

    do {

        // Imprime por consola la información de la nave seleccionada
        System.out.printf(
                "%4$sThe selected spaceship is:%6$s %1$s %n- Velocity: %2$,d KM/H%n- Max.Capacity: %3$s Passengers"
                        + "%n%5$sDo you want to select this spaceship(Y/n): %6$s",
                tempSelectedSpaceShip[0], Integer.parseInt(tempSelectedSpaceShip[1]), tempSelectedSpaceShip[2],
                GREEN, BLUE, RESET);

        // Se confirma la seleccion de la nave si es n (no) se devuelve a la seleccion
        // de nave
        confirmation = input.nextLine();

        if (confirmation.matches("[Nn]")) {
            return selectedSpaceShip;
        } else if (confirmation.matches("[^Yy%s]")) {
            System.out.printf("%1$sError: Invalid character %2$s%n", RED, RESET);
        }
    } while (confirmation.matches("[^Yy%s]"));

    System.out.printf(
            "%2$sSpaceship %1$s successfully selected.%3$s %n",
            tempSelectedSpaceShip[0], GREEN, RESET);

    // Se pregunta por el numero de pasajeros a viajar, se valida que sea un numero
    // positivo y en caso de que el numero ingresado supere el maximo permitido por
    // la nave seleccionada se muestra una advertencia.
    while (spaceShipsCapacity.isBlank()) {
        System.out.printf("%1$sPlease enter the number of passengers traveling: %2$s", BLUE, RESET);

        if (input.hasNextInt()) {
            passengersTemp = input.nextInt();

            if (passengersTemp >= 0) {
                if (passengersTemp > Integer.parseInt(tempSelectedSpaceShip[2])) {
                    System.out.printf(
                            "%1$sWarning: Spaceship capacity exceeded."
                                    + "The number of passengers is higher that the recommended limit."
                                    + "%nPlease proceed with caution.%2$s%n%3$sPress enter to continue.%2$s",
                            YELLOW, RESET, BLUE);
                    input.nextLine();
                }
                spaceShipsCapacity = String.valueOf(passengersTemp);
            } else {
                System.out.printf("%1$sError: Please enter a positive number.%2$s%n", RED, RESET);
            }
        } else {
            System.out.printf("%1$sError: Please enter a positive number.%2$s%n", RED, RESET);
        }
        input.nextLine();
    }

    // Se guarda el numero de pasajeros en el array a retornar
    tempSelectedSpaceShip[3] = spaceShipsCapacity;

    selectedSpaceShip = tempSelectedSpaceShip;

    return selectedSpaceShip;

}