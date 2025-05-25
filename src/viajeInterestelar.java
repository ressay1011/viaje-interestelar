// Metodo que crea el menu al pasarle un array de opciones y devuelve el valor
// seleccionado una vez verificado
private static int showMenu(String[] options) {
    return showMenu(options, "");
}

private static int showMenu(String[] options, String errorMsg) {
    var optionsTemp = "";
    var nOptions = options.length;
    var i = 0;
    var selectionTemp = 0;
    var selection = 0;
    var error = true;
    var fix = "=";
    fix = fix.repeat(60);

    while (error == true) {
        optionsTemp = "";
        i = 0;
        selectionTemp = 0;

        // Crea el logo
        System.out.printf("%1$s               _____ ____  ___   ____________%n"
                + "              / ___// __ \\/   | / ____/ ____/%n"
                + "              \\__ \\/ /_/ / /| |/ /   / __/%n"
                + "             __/ / ____/ ___ / // /_%n"
                + "%2$s   _____ __%1$s///%2$s__ %1$s//%2$s%1$s|\\//%2$s____  ____%n"
                + "  / __//  _/  |/  / / / / /   /   |/  __/ __ \\/ __ \\%n"
                + "  \\__ \\ / // /|/ / / / / /   / /| | / / / / / / // /%n"
                + " __/ // // /  / / // / // ___ |/ / / // / _, _/%n"
                + "////  //\\///  |//  \\// |_|%n%4$s%3$s%n", RED, GREEN, RESET,
                fix);

        // Crea las opciones asignandole a la ultima el numero 0.
        for (String option : options) {
            if (i != (nOptions - 1)) {
                optionsTemp = optionsTemp
                        .concat(String.format("%1$s%3$s. %2$s%4$s.%n", GREEN, RESET, (i + 1), option));
            } else {
                optionsTemp = optionsTemp
                        .concat(String.format("%1$s%3$s. %2$s%4$s.%n", GREEN, RESET, 0, option));
            }
            i = i + 1;
        }

        // Agrega el mensaje de error al menu en caso de haberlo.
        if (error = true) {
            optionsTemp = optionsTemp.concat("%3$s" + errorMsg + "%2$s");
            error = false;
        }

        optionsTemp = optionsTemp
                .concat("%1$s%5$s%4$s%2$s%nPlease select an option (0 - " + (nOptions - 1) + "):%4$s ");

        System.out.printf(optionsTemp, GREEN, BLUE, RED, RESET, fix);

        // Valida si la opcion seleccionada es valida, si es un numero y si esta en el
        // rango permitido.
        if (input.hasNextInt()) {
            selectionTemp = input.nextInt();
            if (selectionTemp >= 0 & selectionTemp <= (nOptions - 1)) {
                selection = selectionTemp;
            } else {
                error = true;
                errorMsg = "%3$sError: The option " + selectionTemp + " is not valid.%4$s%n";
            }
        } else {
            error = true;
            errorMsg = "%3$s%5$s%nError: Please enter a number from 0 to " + (nOptions - 1) + ".%4$s%n";
        }
        input.nextLine(); // Limpiar buffer
    }
    return selection;
}

// Metodo que crea el menu principal y retorna la opcion seleccionada
private static int mainMenu(String destinationPlanet, String[] selectedSpaceShip, double travelDuration,
        String errorMsg) {

    // Guarda la selección del usuario para posteriormente retornarla.
    int selection;

    // Define las opciones que se muestran en el menu principal.
    String[] options = {
            "Select destination planet",
            "Select spaceship",
            "Start the travel simulation",
            "Exit the program" };

    // Valida si hay un planeta seleccionado para mostrar el nombre del planeta.
    if (!destinationPlanet.isBlank()) {
        options[0] = options[0].concat(String.format("%1$s (%2$s)%3$s", BLUE, destinationPlanet, RESET));
    }

    // Valida si hay una nave seleccionada para mostrar el nombre de la nave y el
    // numero de pasajeros.
    if (!selectedSpaceShip[0].isBlank()) {
        options[1] = options[1]
                .concat(String.format("%1$s (%2$s: %3$,d Passengers)%4$s", BLUE, selectedSpaceShip[0],
                        Integer.parseInt(selectedSpaceShip[3]), RESET));
    }

    // Valida si hay un planeta y una nave seleccionados para mostrar la duracion
    // del viaje en dias.
    if (!selectedSpaceShip[0].isBlank() && !destinationPlanet.isBlank()) {
        options[2] = options[2].concat(String.format("%1$s (%2$,.0f Dias)%3$s", BLUE, travelDuration, RESET));
    }

    // Se crea y imprime el menu
    selection = showMenu(options, errorMsg);

    return selection;
}