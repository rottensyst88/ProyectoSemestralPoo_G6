package vista;
import gui.*;
import controlador.*;
import excepciones.*;
import utilidades.*;

import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class UISVP {
    private static UISVP instance = new UISVP();

    public static UISVP getInstance() {
        return instance;
    }

    private Scanner sc = new Scanner(System.in).useDelimiter("\t|\r\n|[\n\r\u2028\u2029\u0085]");

    /*
    ==================================================================
    |A PARTIR DE ESTE PUNTO, SE EJECUTAN LOS METODOS CORRESPONDIENTES|
    ==================================================================
     */
    public void menu() {
        boolean verificador = true;
        int valor;

        do {
            System.out.println("""
                    ╔══════════════════════════════════════════╗
                    ║       ...::: Menú principal :::...       ║
                    ║                                          ║
                    ║ 1) Crear empresa                         ║
                    ║ 2) Contratar tripulante                  ║
                    ║ 3) Crear terminal                        ║
                    ║ 4) Crear cliente                         ║
                    ║ 5) Crear bus                             ║
                    ║ 6) Crear viaje                           ║
                    ║ 7) Vender pasajes                        ║
                    ║ 8) Listar ventas                         ║
                    ║ 9) Listar viajes                         ║
                    ║10) Listar pasajeros de viaje             ║
                    ║11) Listar empresas                       ║
                    ║12) Listar llegadas/salidas de terminal   ║
                    ║13) Listar ventas de empresa              ║
                    ║14) Generar pasajes venta                 ║
                    ║15) Leer datos iniciales                  ║
                    ║16) Guardar datos del sistema             ║
                    ║17) Leer datos del sistema                ║
                    ║18) Salir                                 ║
                    ╚══════════════════════════════════════════╝
                    """);

            try {
                valor = Integer.parseInt(entradaDatos("  ..:: Ingrese número de opción", 1));

                switch (valor) {
                    case 1 -> createEmpresas();
                    case 2 -> contrataTripulante();
                    case 3 -> createTerminal();
                    case 4 -> createCliente();
                    case 5 -> createBus();
                    case 6 -> createViaje();
                    case 7 -> vendePasajes();
                    case 8 -> listVentas();
                    case 9 -> listViajes();
                    case 10 -> listPasajerosViaje();
                    case 11 -> listEmpresas();
                    case 12 -> listLlegadasSalidasTerminal();
                    case 13 -> listVentasEmpresa();
                    case 14 -> generatePasajesVenta();
                    case 15 -> readDatosIniciales();
                    case 16 -> saveDatosSistema();
                    case 17 -> readDatosSistema();
                    case 18 -> verificador = false;
                    default -> System.out.println(":::: Numero ingresado no es valido, reintente ::::");
                }
            } catch (NumberFormatException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (verificador);
        System.out.println("Hasta luego! Saliendo del programa...");
    }

    private void createEmpresas() {
        GUICreaEmpresa dialog = new GUICreaEmpresa();
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);

    }

    private void contrataTripulante() {
        try{
            GUIContrataTripulante.display();
        }catch(SVPException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            UISVP.getInstance().menu();
        }

        /*Nombre tripulante = new Nombre(); IdPersona id; Tratamiento tratamiento;

        System.out.println("...:::: Creando un nuevo Tripulante ...::::\n");
        System.out.println(":::: Dato de la Empresa");
        do {
            rut = Rut.of(entradaDatos("R.U.T", 1));
            if (rut == null) {
                System.out.println(":::: Formato de rut ingresado no es valido ::::");
            } else {
                verif = false;
            }
        } while (verif);

        verif = true;
        System.out.println("\n:::: Datos tripulante");

        do {
            try {
                opcion = Integer.parseInt(entradaDatos("Auxiliar[1] o Conductor[2]", 2));
                if (opcion == 1 || opcion == 2) {
                    verif = false;
                } else {
                    System.out.println(":::: El valor ingresado no es valido! ::::");
                }
            } catch (NumberFormatException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (verif);

        verif = true;

        do {
            try {
                do {
                    id = SelectorRut_Pasaporte(2);
                } while (id == null);

                do {
                    tratamiento = SelectorTratamiento(2);
                } while (tratamiento == null);

                tripulante.setNombre(entradaDatos("Nombres", 2));
                tripulante.setApellidoPaterno(entradaDatos("Apellido Paterno", 2));
                tripulante.setApellidoMaterno(entradaDatos("Apellido Materno", 2));
                String calle = entradaDatos("Calle", 2);
                int numero = Integer.parseInt(entradaDatos("Numero", 2));
                String comuna = entradaDatos("Comuna", 2);

                Direccion direccion = new Direccion(calle, numero, comuna);

                try {
                    if (opcion == 1) {
                        ControladorEmpresas.getInstance().hireAuxiliarForEmpresa(rut, id, tripulante, direccion);
                        System.out.println("...:::: Auxiliar contratado exitosamente ::::....");
                    } else {
                        ControladorEmpresas.getInstance().hireConductorForEmpresa(rut, id, tripulante, direccion);
                        System.out.println("...:::: Conductor contratado exitosamente ::::....");
                    }
                } catch (SVPException e) {
                    imprimirErrores(e);
                } finally {
                    verif = false;
                }

         */
    }

    private void createTerminal() {
        System.out.println("...:::: Creando un nuevo Terminal\n");
        boolean verif = true;

        do {
            try {
                String nombre = entradaDatos("Nombre", 1);
                String calle = entradaDatos("Calle", 1);
                int numero = Integer.parseInt(entradaDatos("Numero", 1));
                String comuna = entradaDatos("Comuna", 1);

                Direccion direccion = new Direccion(calle, numero, comuna);

                if(nombre.isBlank() || calle.isBlank() || comuna.isBlank()){
                    System.out.println(":::: Los datos no pueden estar vacios! ::::");
                }else{
                    try {
                        ControladorEmpresas.getInstance().createTerminal(nombre, direccion);
                        System.out.println("....:::: Terminal guardado exitosamente ::::....");
                    } catch (SVPException e) {
                        imprimirErrores(e);
                    } finally {
                        verif = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (verif);
    }

    private void createCliente() {
        Nombre usuario = new Nombre();
        Tratamiento tratamiento;
        IdPersona id;
        System.out.println("\n...:::: Crear un nuevo Cliente ::::...\n");
        boolean verif = true;

        do{
            try{
                do {
                    id = SelectorRut_Pasaporte(1);
                } while (id == null);
                do {
                    tratamiento = SelectorTratamiento(1);
                } while (tratamiento == null);

                String nombre = entradaDatos("Nombres", 1);
                String apPaterno = entradaDatos("Apellido Paterno", 1);
                String apMaterno = entradaDatos("Apellido Materno", 1);
                String telefono = entradaDatos("Telefono", 1);
                String email = entradaDatos("Email", 1);

                if(nombre.isBlank() || apPaterno.isBlank() || apMaterno.isBlank() || telefono.isBlank() || email.isBlank()){
                    System.out.println(":::: Los datos no pueden estar vacios! ::::");
                }else{
                    usuario.setNombre(nombre);
                    usuario.setApellidoPaterno(apPaterno);
                    usuario.setApellidoMaterno(apMaterno);
                    try {
                        SistemaVentaPasajes.getInstancia().createCliente(id, usuario, telefono, email);
                        System.out.println("\n...:::: Cliente guardado exitosamente ::::...");
                    } catch (SVPException e) {
                        imprimirErrores(e);
                    } finally{
                        verif = false;
                    }
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        }while(verif);
    }

    private void createBus() {
        GUICreaBus.display();
    }

    private void createViaje() {
        IdPersona id_aux;
        IdPersona id_con;
        IdPersona[] datos_id = null;
        String[] datos = new String[2];
        boolean verif = true;
        LocalDate fecha = null;
        LocalTime hora = null;
        int precio = 0, duracion = 0, conductores = 0;
        String patente = null;


        System.out.println("\n...:::: Creación de un nuevo Viaje ::::...\n");
        do {
            try {
                fecha = LocalDate.parse(entradaDatos("Fecha[dd/mm/yyyy]", 1), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                hora = LocalTime.parse(entradaDatos("Hora[hh:mm]", 1), DateTimeFormatter.ofPattern("HH:mm"));
                precio = Integer.parseInt(entradaDatos("Precio", 1));
                duracion = Integer.parseInt(entradaDatos("Duración (minutos)", 1));
                patente = entradaDatos("Patente bus", 1);
                conductores = Integer.parseInt(entradaDatos("Numero de conductores", 1));

                if (conductores > 2) {
                    System.out.println(":::: Error! Los conductores solo pueden ser máximo 2");
                } else if(duracion <= 0){
                    System.out.println(":::: Error! La duración no puede ser menor o igual a 0");
                } else {
                    datos_id = new IdPersona[conductores + 1];
                    verif = false;
                }
            } catch (NumberFormatException | DateTimeParseException | NegativeArraySizeException e) {
                System.out.println(":::: El valor ingresado no es valido ::::");
            }
        } while (verif);

        System.out.println("\n:: Id auxiliar ::");
        verif = true;

        do{
            try{
                do {
                    id_aux = SelectorRut_Pasaporte(1);
                } while (id_aux == null);
                datos_id[0] = id_aux;

                for (int z = 1; z <= conductores; z++) {
                    System.out.println("\n:: Id conductor ::");
                    do {
                        id_con = SelectorRut_Pasaporte(1);
                    } while (id_con == null);
                    datos_id[z] = id_con;
                }

                datos[0] = entradaDatos("Nombre comuna llegada", 1);
                datos[1] = entradaDatos("Nombre comuna salida", 1);

                try {
                    SistemaVentaPasajes.getInstancia().createViaje(fecha, hora, precio, duracion, patente, datos_id, datos);
                    System.out.println("\n...:::: Viaje guardado exitosamente ::::...");
                } catch (SVPException e) {
                    imprimirErrores(e);
                } finally {
                    verif = false;
                }
            } catch(NumberFormatException | StringIndexOutOfBoundsException e){
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while(verif);
    }

    private void vendePasajes() {
        IdPersona id = null;
        TipoDocumento tipo = null;
        boolean verif = true;
        System.out.println("\n....:::: Venta de pasajes ::::....\n");
        String idDocumento = null;
        LocalDate fec = null;
        String origen = null, destino = null;

        System.out.println(":::: Datos de la Venta");
        do {
            try {
                idDocumento = entradaDatos("ID Documento", 2);
                do {
                    int tipoDoc = Integer.parseInt(entradaDatos("Tipo doc. [1]Boleta [2]Factura", 2));
                    if (tipoDoc == 1 || tipoDoc == 2) {
                        if (tipoDoc == 1) {
                            tipo = TipoDocumento.BOLETA;
                        } else {
                            tipo = TipoDocumento.FACTURA;
                        }
                    } else {
                        System.out.println(":::: Error! Valor invalido!");
                    }
                } while (tipo == null);

                fec = LocalDate.parse(entradaDatos("Fecha de viaje[dd/mm/yyyy]", 2), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                origen = entradaDatos("Origen (comuna)", 2);
                origen = origen.substring(0, 1).toUpperCase() + origen.substring(1).toLowerCase();
                destino = entradaDatos("Destino (comuna)", 2);
                destino = destino.substring(0, 1).toUpperCase() + destino.substring(1).toLowerCase();
                verif = false;
            } catch (DateTimeParseException | NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println(":::: El valor ingresado no es valido ::::");
            }
        } while (verif);


        System.out.println("\n:::: Datos del cliente\n");

        do {
            try{
                id = SelectorRut_Pasaporte(2);
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (id == null);

        System.out.println("\n::::Pasajes a vender");
        int cantidadPasajes = 0;
        verif = true;

        do {
            try {
                cantidadPasajes = Integer.parseInt(entradaDatos("Cantidad de pasajes", 2));

                if(cantidadPasajes <= 0){
                    System.out.println(":::: No puede comprar 0 o menos pasajes! ::::");
                } else{
                    try {
                        SistemaVentaPasajes.getInstancia().iniciaVenta(idDocumento, tipo, fec, origen, destino, id, cantidadPasajes);
                    } catch (SVPException e) {
                        imprimirErrores(e);
                        return;
                    }  finally {
                        verif = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (verif);

        String[][] horariosDisponibles = SistemaVentaPasajes.getInstancia().getHorariosDisponibles(fec, origen, destino, cantidadPasajes);
        System.out.println("::::Listado de horarios disponibles");
        int contador = 0;
        System.out.print("    *------------*------------*------------*------------*\n");
        System.out.printf("    | %10s | %10s | %10s | %10s |\n", "BUS", "SALIDA", "VALOR", "ASIENTOS");
        for (String[] horario : horariosDisponibles) {
            System.out.println("    |------------+------------+------------+------------|");
            System.out.printf(" %2s | %10s | %10s | %10s | %10s |\n", contador + 1, horario[0], horario[1], horario[2], horario[3]);
            contador++;
        }
        System.out.println("    *------------*------------*------------*------------*\n");

        int seleccion = 0;
        verif = true;

        do {
            try {
                seleccion = Integer.parseInt(entradaDatos("Seleccione viaje en [1.."+contador+"]",1));
                if (seleccion > contador || seleccion < 1) {
                    System.out.println(":::: Error! Valor fuera de rango!");
                } else {
                    verif = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (verif);

        String[] datosCompra = new String[4];
        for (int y = 0; y < seleccion; y++) {
            if (y == seleccion - 1) {
                datosCompra[0] = horariosDisponibles[y][0];
                datosCompra[1] = horariosDisponibles[y][1];
                datosCompra[2] = horariosDisponibles[y][2];
                datosCompra[3] = horariosDisponibles[y][3];
            }
        }
        int pasajesRedondeado = 1;
        while (Integer.parseInt(datosCompra[3]) >= pasajesRedondeado * 4) {
            pasajesRedondeado++;
        }
        String[] datos_viajeSelect = SistemaVentaPasajes.getInstancia().listAsientosDeViaje(fec, LocalTime.parse(datosCompra[1], DateTimeFormatter.ofPattern("HH:mm")), datosCompra[0]);
        System.out.println("*---*---*---*---*---*");
        int IndiceAsiento = 0;
        for (int y = 0; y < pasajesRedondeado; y++) {
            for (int x = 0; x <= 4; x++) {
                if (x == 2) {
                    System.out.print("|   ");
                } else {
                    if (IndiceAsiento < datos_viajeSelect.length) {
                        if (datos_viajeSelect[IndiceAsiento].equals("Ocupado")) {
                            System.out.print("|  *");
                        } else {
                            System.out.printf("|%3d", IndiceAsiento + 1);
                        }
                    } else {
                        System.out.print("|  *");
                    }
                    IndiceAsiento++;
                }
            }
            System.out.println("|");
        }
        System.out.println("*---*---*---*---*---*");
        String[] split = null;

        verif = true;

        do {
            try {
                String asientos = entradaDatos("Seleccione sus asientos [separe por ;]",1);
                split = asientos.split(";");

                if (split.length != cantidadPasajes) {
                    System.out.println(":::: Error! Cantidad de asientos no coincide con la cantidad de pasajes!");
                } else {
                    for (String s : split) {
                        if (Integer.parseInt(s) > datos_viajeSelect.length || Integer.parseInt(s) < 1) {
                            System.out.println(":::: Error! Asiento ubicado fuera de rango!");
                            verif = true;
                            break;
                        }
                        verif = false;
                    }
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (verif);

        verif = true;
        for (int i = 0; i < cantidadPasajes; i++) {
            IdPersona id_pasajero = null;
            String asiento = split[i];
            System.out.println("\n:::: Datos del pasajero " + (i + 1));

            do {
                try{
                    id_pasajero = SelectorRut_Pasaporte(2);
                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    System.out.println(":::: El valor ingresado no es valido! ::::");
                }
            } while (id_pasajero == null);

            do {
                try {
                    if ((SistemaVentaPasajes.getInstancia().getNombrePasajero(id_pasajero).isEmpty())) {
                        String nombre = entradaDatos("Nombre [Sr/Sra. * * *]", 2);
                        Nombre nombrePasajero = new Nombre();

                        String[] nombres_1 = nombre.split(" ");

                        if (nombres_1[0].equals("Sr.")) {
                            nombrePasajero.setTratamiento(Tratamiento.SR);
                        } else {
                            nombrePasajero.setTratamiento(Tratamiento.SRA);
                        }

                        nombrePasajero.setNombre(nombres_1[1]);
                        nombrePasajero.setApellidoPaterno(nombres_1[2]);
                        nombrePasajero.setApellidoMaterno(nombres_1[3]);

                        String telefono = entradaDatos("Telefono", 2);
                        String nombreContacto = entradaDatos("Nombre de contacto [Sr/Sra. * * *]", 2);

                        Nombre nombreContactoPasajero = new Nombre();
                        String[] nombresContacto = nombreContacto.split(" ");

                        if (nombresContacto[0].equals("Sr.")) {
                            nombreContactoPasajero.setTratamiento(Tratamiento.SR);
                        } else {
                            nombreContactoPasajero.setTratamiento(Tratamiento.SRA);
                        }

                        nombreContactoPasajero.setNombre(nombresContacto[1]);
                        nombreContactoPasajero.setApellidoPaterno(nombresContacto[2]);
                        nombreContactoPasajero.setApellidoMaterno(nombresContacto[3]);
                        String telefonoContacto = entradaDatos("Telefono de contacto", 2);
                        try {
                            SistemaVentaPasajes.getInstancia().createPasajero(id_pasajero, nombrePasajero, telefono, nombreContactoPasajero, telefonoContacto);
                        } catch (SVPException e) {
                            imprimirErrores(e);
                            return;
                        }
                    }
                    try {
                        SistemaVentaPasajes.getInstancia().vendePasaje(idDocumento, tipo, LocalTime.parse(datosCompra[1], DateTimeFormatter.ofPattern("HH:mm")), fec, datosCompra[0], Integer.parseInt(asiento), id_pasajero);
                        System.out.println(":::: Pasaje agregado exitosamente!");
                    } catch (SVPException e) {
                        imprimirErrores(e);
                    } finally {
                        verif = false;
                    }
                } catch (PatternSyntaxException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(":::: Datos no fueron ingresados correctamente! ::::");
                }
            } while (verif);
        }

        verif = true;
        System.out.println(":::: Monto total de la venta : " + SistemaVentaPasajes.getInstancia().getMontoVenta(idDocumento, tipo).get());

        do{
            try{
                pagaVentaPasajes(idDocumento, tipo);

                System.out.println("\n:::: Venta realizada exitosamente!");
                System.out.print(":::: Imprimiendo los pasajes\n\n");
                String[] x = SistemaVentaPasajes.getInstancia().pasajesAlImprimir(idDocumento, tipo);

                for (String s : x) {
                    System.out.println("-------------------- PASAJE ---------------------");
                    System.out.println(s);
                    System.out.println("------------------------------------------------\n\n");
                }
                verif = false;
            } catch(NumberFormatException e) {
                System.out.println(":::: Valor ingresado no es valido! ::::");
            }
        }while(verif);
    }

    private void pagaVentaPasajes(String idDocumento, TipoDocumento tipo) {
        boolean verifPago = false;

        do {
            System.out.println("\n:::: Pago de la venta");
            int opcion = Integer.parseInt(entradaDatos("Efectivo[1] o Tarjeta[2]", 2));

            try {
                switch (opcion) {
                    case 1:
                        SistemaVentaPasajes.getInstancia().pagaVenta(idDocumento, tipo);
                        verifPago = true;
                        break;
                    case 2:
                        String numTarjeta = entradaDatos("Nro. tarjeta", 2);
                        long nTarjeta = Long.parseLong(numTarjeta);
                        SistemaVentaPasajes.getInstancia().pagaVenta(idDocumento, tipo, nTarjeta);
                        verifPago = true;
                        break;
                    default:
                        System.out.println("Error! Opcion no valida!");
                }
            } catch (SVPException e) {
                imprimirErrores(e);
            }

        } while (!verifPago);
    }

    private void listVentas() {
        System.out.println("\n...:::: Listado de ventas ::::...\n");
        String[][] pasajeros_arreglo = SistemaVentaPasajes.getInstancia().listVentas();

        if (pasajeros_arreglo.length == 0) {
            System.out.println(":::: Error! No hay ventas");
            return;
        }

        System.out.println("*----------------*------------*------------------*------------------*--------------------------------*------------------*----------------*");
        System.out.printf("| %14s | %10s | %16s | %16s | %30s | %16s | %14s |\n", "ID DOCUMENT", "TIPO DOCU", "FECHA", "RUT / PASAPORTE", "CLIENTE", "CANT BOLETOS", "TOTAL VENTA");
        for (String[] pasajero : pasajeros_arreglo) {
            System.out.println("*----------------+------------+------------------+------------------+--------------------------------+------------------+----------------*");
            System.out.printf("| %14s |", pasajero[0]);
            System.out.printf(" %10s |", pasajero[1]);
            System.out.printf(" %-16s |", pasajero[2]);
            System.out.printf(" %-16s |", pasajero[3]);
            System.out.printf(" %-30s |", pasajero[4]);
            System.out.printf(" %16s |", pasajero[5]);
            System.out.printf(" %14s |\n", pasajero[6]);
        }
        System.out.println("*----------------*------------*------------------*------------------*--------------------------------*------------------*----------------*\n\n");
    }

    private void listViajes() {
        System.out.println("\n...:::: Listado de viajes ::::...\n");
        String[][] pasajeros_arreglo = SistemaVentaPasajes.getInstancia().listViajes();

        if (pasajeros_arreglo.length == 0) {
            System.out.println(":::: Error! No hay viajes");
            return;
        }

        System.out.println("*------------------*------------------*------------------*------------------*------------------*------------------*------------------*------------------*");
        System.out.printf("| %16s | %16s | %16s | %16s | %16s | %16s | %16s | %16s |\n", "FECHA", "HORA SALE", "HORA LLEGA", "PRECIO", "ASIENTOS DISP.", "PATENTE", "ORIGEN", "DESTINO");
        for (String[] pasajero : pasajeros_arreglo) {
            System.out.println("*------------------+------------------+------------------+------------------+------------------+------------------+------------------+------------------*");
            for (int x = 0; x < 8; x++) {
                System.out.printf("| %16s ", pasajero[x]);
            }
            System.out.println("|");
        }
        System.out.println("*------------------*------------------*------------------*------------------*------------------*------------------*------------------*------------------*\n\n");
    }

    private void listPasajerosViaje() {
        String[][] pasajeros_arreglo = null;
        System.out.println("\n...:::: Listado de pasajeros de un viaje ::::...\n");
        boolean verif = true;

        do{
            try{
                LocalDate fecha = LocalDate.parse(entradaDatos("Fecha de viaje[dd/mm/yyyy]", 2), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalTime hora = LocalTime.parse(entradaDatos("Hora de viaje[hh:mm]", 2), DateTimeFormatter.ofPattern("HH:mm"));
                String patente = entradaDatos("Patente del bus", 2);
                patente = patente.substring(0, 2) + patente.substring(3, 5) + patente.substring(6, 8);

                try {
                    pasajeros_arreglo = SistemaVentaPasajes.getInstancia().listPasajerosViaje(fecha, hora, patente);
                } catch (SVPException e) {
                    imprimirErrores(e);
                    return;
                } finally {
                    verif = false;
                }
            } catch (DateTimeParseException | IndexOutOfBoundsException e) {
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while(verif);


        if (pasajeros_arreglo.length == 0) {
            System.out.println(":::: Error! No hay pasajeros para el viaje seleccionado!");
            return;
        }

        System.out.println("*----------*------------------*--------------------------*--------------------------*----------------------*");
        System.out.printf("| %8s | %16s | %24s | %24s | %20s |\n", "ASIENTO", "RUT/PASS", "PASAJERO", "CONTACTO", "TELEFONO CONTACTO");
        for (String[] pasajero : pasajeros_arreglo) {
            System.out.println("*----------+------------------+--------------------------+--------------------------+----------------------*");
            System.out.printf("| %8s |", pasajero[0]);
            System.out.printf(" %16s |", pasajero[1]);
            System.out.printf(" %-24s |", pasajero[2]);
            System.out.printf(" %-24s |", pasajero[3]);
            System.out.printf(" %-20s |\n", pasajero[4]);
        }
        System.out.println("*----------*------------------*--------------------------*--------------------------*----------------------*\n\n");
    }
    private void listVentasEmpresa() {

        try{
            GUIListaVentasEmpresas.display();
        }catch(SVPException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            UISVP.getInstance().menu();
        }

        /*System.out.println("\n...:::: Listado de ventas de una empresa ::::...\n");
        Rut rut = Rut.of(entradaDatos("R.U.T",2));

        try {
            String[][] listas = ControladorEmpresas.getInstance().listVentasEmpresa(rut);

            if (listas.length == 0) {
                System.out.println(":::: Error! No hay ventas para la empresa seleccionada!");
                return;
            }

            System.out.println("*--------------*--------------*--------------*--------------*");
            System.out.printf("| %12s | %12s | %12s | %12s |\n", "FECHA", "TIPO", "MONTO PAGADO", "TIPO PAGO");
            for (String[] lista : listas) {
                System.out.println("*--------------+--------------+--------------+--------------*");
                System.out.printf("| %12s | %12s | %12s | %12s |\n", lista[0], lista[1], lista[2], lista[3]);
            }
            System.out.println("*--------------*--------------*--------------*--------------*\n\n");
        } catch (SVPException e) {
            imprimirErrores(e);
        }

         */
    }
    private void listEmpresas() {
        System.out.println("\n...:::: Listado de empresas::::...\n");
        String[][] empresas_arreglo = ControladorEmpresas.getInstance().listEmpresas();
        if (empresas_arreglo.length == 0) {
            System.out.println(":::: Error! No existe datos de empresas!");
            return;
        }

        System.out.println("*----------------*----------------------*--------------------------------*----------------------*-----------------*-----------------*");
        System.out.printf("| %14s | %20s | %30s | %20s | %15s | %15s |\n", "RUT EMPRESA", "NOMBRE", "URL", "NRO. TRIPULANTES", "NRO. BUSES", "NRO. VENTAS");

        for (String[] empresa : empresas_arreglo) {
            System.out.println("*----------------+----------------------+--------------------------------+----------------------+-----------------+-----------------*");
            System.out.printf("| %14s |", empresa[0]);
            System.out.printf(" %20s |", empresa[1]);
            System.out.printf(" %30s |", empresa[2]);
            System.out.printf(" %20s |", empresa[3]);
            System.out.printf(" %15s |", empresa[4]);
            System.out.printf(" %15s |\n", empresa[5]);
        }
        System.out.println("*----------------*----------------------*--------------------------------*----------------------*-----------------*-----------------*\n\n");
    }

    private void listLlegadasSalidasTerminal() {
        boolean verif = true;

        System.out.println("\n...:::: Listado de llegadas y salidas de un terminal::::...\n");
        do{
            try{
                String nombre_terminal = entradaDatos("Nombre terminal", 1);
                LocalDate fecha = LocalDate.parse(entradaDatos("Fecha[dd/MM/yyyy]", 1), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                try {
                    String[][] llegadasSalidas = ControladorEmpresas.getInstance().listLlegadasSalidasTerminal(nombre_terminal, fecha);

                    System.out.println("*--------------------------*--------------------------*--------------------------*--------------------------*--------------------------*");
                    System.out.printf("| %24s | %24s | %24s | %24s | %24s |\n", "LLEGADA/SALIDA", "HORA", "PATENTE BUS", "NOMBRE EMPRESA", "NRO. PASAJEROS");

                    for (String[] llegadasSalida : llegadasSalidas) {
                        System.out.println("*--------------------------+--------------------------+--------------------------+--------------------------+--------------------------*");
                        System.out.print("|");
                        for (int x = 0; x < 5; x++) {
                            System.out.printf(" %24s |", llegadasSalida[x]);
                        }
                        System.out.println();
                    }
                    System.out.println("*--------------------------*--------------------------*--------------------------*--------------------------*--------------------------*\n\n");
                } catch (SVPException e) {
                    imprimirErrores(e);
                    return;
                } finally {
                    verif = false;
                }
            } catch(DateTimeParseException e){
                System.out.println(":::: Fecha ingresada no es valida! ::::");
            }
        }while(verif);
    }

    /*
    private void listVentasEmpresa() {
        Rut rut;

        System.out.println("\n...:::: Listado de ventas de una empresa ::::...\n");
        do{
            rut = Rut.of(entradaDatos("R.U.T", 2));
        }while(rut == null);

        try {
            String[][] listas = ControladorEmpresas.getInstance().listVentasEmpresa(rut);

            if (listas.length == 0) {
                System.out.println(":::: Error! No hay ventas para la empresa seleccionada!");
                return;
            }

            System.out.println("*--------------*--------------*--------------*--------------*");
            System.out.printf("| %12s | %12s | %12s | %12s |\n", "FECHA", "TIPO", "MONTO PAGADO", "TIPO PAGO");
            for (String[] lista : listas) {
                System.out.println("*--------------+--------------+--------------+--------------*");
                System.out.printf("| %12s | %12s | %12s | %12s |\n", lista[0], lista[1], lista[2], lista[3]);
            }
            System.out.println("*--------------*--------------*--------------*--------------*\n\n");
        } catch (SVPException e) {
            imprimirErrores(e);
        }
    }*/

    private void generatePasajesVenta() {
        System.out.println("\n...:::: Generar pasajes de venta ::::...\n");
        String idDoc = entradaDatos("ID Documento", 2);
        TipoDocumento tipo = null;

        do {
            try{
                int tipoDoc = Integer.parseInt(entradaDatos("Tipo doc. [1]Boleta [2]Factura", 2));
                if (tipoDoc == 1 || tipoDoc == 2) {
                    if (tipoDoc == 1) {
                        tipo = TipoDocumento.BOLETA;
                    } else {
                        tipo = TipoDocumento.FACTURA;
                    }
                } else {
                    System.out.println(":::: Error! Valor invalido!");
                }
            } catch (NumberFormatException e){
                System.out.println(":::: El valor ingresado no es valido! ::::");
            }
        } while (tipo == null);

        try {
            SistemaVentaPasajes.getInstancia().generatePasajesVenta(idDoc, tipo);
            System.out.println("::Archivo generado exitosamente, busque " + tipo.name() + "_" + idDoc + ".txt::\n\n");
        } catch (SVPException e) {
            imprimirErrores(e);
        }
    }

    private void readDatosIniciales() {
        System.out.println("\n...:::: Lectura de datos iniciales ::::...\n");
        try {
            System.out.println("::Leyendo archivo solicitado, espere::");
            SistemaVentaPasajes.getInstancia().readDatosIniciales();
            System.out.println("::Lectura finalizada exitosamente::\n\n");
        } catch (SVPException e) {
            imprimirErrores(e);
        }
    }

    private void saveDatosSistema() {
        System.out.println("\n...:::: Guardado de datos de sistema ::::...\n");
        try {
            System.out.println("::Grabando datos a archivo, espere::");
            SistemaVentaPasajes.getInstancia().saveDatosSistema();
            System.out.println("::Grabado de datos finalizado exitosamente::\n\n");
        } catch (SVPException e) {
            imprimirErrores(e);
        }
    }

    private void readDatosSistema() {
        System.out.println("\n...:::: Lectura de datos de sistema ::::...\n");
        try {
            System.out.println("::Leyendo datos desde archivo, espere::");
            SistemaVentaPasajes.getInstancia().readDatosSistema();
            System.out.println("::Lectura finalizada exitosamente::\n\n");
        } catch (SVPException e) {
            imprimirErrores(e);
        }
    }

    /*
    ==============================================================================
    |METODOS PRIVADOS, CREADOS PARA OPTIMIZAR EL CODIGO DE LA MEJOR FORMA POSIBLE|
    ==============================================================================
     */
    private IdPersona SelectorRut_Pasaporte(int tamanno) {

        int rut_o_pasaporte = Integer.parseInt(entradaDatos("Rut[1] o Pasaporte[2]", tamanno));

        if (rut_o_pasaporte == 1 || rut_o_pasaporte == 2) {
            if (rut_o_pasaporte == 1) {
                return Rut.of(entradaDatos("R.U.T", tamanno));
            } else {
                String nroPasaporte = entradaDatos("Numero de pasaporte", tamanno);
                String nacionalidad = entradaDatos("Nacionalidad", tamanno);
                return Pasaporte.of(nroPasaporte, nacionalidad);
            }
        } else {
            System.out.println(":::: Error! Valor invalido, reintente.");
            return null;
        }
    }

    private Tratamiento SelectorTratamiento(int tamanno) {
        int sr_o_sra = Integer.parseInt(entradaDatos("Sr.[1] o Sra. [2]", tamanno));
        if (sr_o_sra == 1 || sr_o_sra == 2) {
            if (sr_o_sra == 1) {
                return Tratamiento.SR;
            } else {
                return Tratamiento.SRA;
            }
        } else {
            System.out.println(":::: Error! Valor invalido, reintente.");
            return null;
        }
    }

    private String entradaDatos(String mensaje, int tamanno) {
        if (tamanno == 1) {
            System.out.printf("%20s : ", mensaje);
            return sc.next();
        }
        if (tamanno == 2) {
            System.out.printf("%30s : ", mensaje);
            return sc.next();
        }
        return "";
    }

    private void imprimirErrores(SVPException e) {
        System.out.println("\n:::: " + e.getMessage());
        System.out.println("*** Error encontrado, no se pudo concretar la operación! ***\n");
    }

    private void datosPrueba() {
       ControladorEmpresas.getInstance().createEmpresa(Rut.of("77.777.777-1"), "mibus2", "mibus.pe");
       ControladorEmpresas.getInstance().createEmpresa(Rut.of("77.777.777-7"), "mibus", "mibus.cl");
       ControladorEmpresas.getInstance().createEmpresa(Rut.of("22.727.777-7"), "elpepe", "elpepe.cl");
        Nombre aux_test = new Nombre();
        aux_test.setTratamiento(Tratamiento.SR);
        aux_test.setNombre("Ery Flores");
        aux_test.setApellidoPaterno("Valle");
        aux_test.setApellidoMaterno("Lindo");
        ControladorEmpresas.getInstance().hireAuxiliarForEmpresa(Rut.of("77.777.777-7"), Rut.of("00.000.000-0"),
                aux_test, new Direccion("Santa Rosa", 123, "Coihueco"));
        Nombre con_test = new Nombre();
        con_test.setTratamiento(Tratamiento.SR);
        con_test.setNombre("Ariel Alonso");
        con_test.setApellidoPaterno("Bob");
        con_test.setApellidoMaterno("Bar");
        ControladorEmpresas.getInstance().hireConductorForEmpresa(Rut.of("77.777.777-7"), Rut.of("00.000.000-1"), con_test,
                new Direccion("Santo Domingo", 456, "Talquipen"));
        ControladorEmpresas.getInstance().createTerminal("Santo Milagro", new Direccion("Placido", 1, "Talquipen"));
        ControladorEmpresas.getInstance().createTerminal("Pecado Milenio", new Direccion("Milenio", 2, "Nuble"));
        ControladorEmpresas.getInstance().createBus("AABB12", "VOLVO", "GEN", 35, Rut.of("77.777.777-7"));
        // Crear buses para las empresas que no tienen uno
        ControladorEmpresas.getInstance().createBus("BBCC34", "Mercedes-Benz", "GEN", 50, Rut.of("77.777.777-1")); // Para "mibus2"
        ControladorEmpresas.getInstance().createBus("CCDD56", "Scania", "PREMIUM", 45, Rut.of("22.727.777-7")); // Para "elpepe"

        Nombre cli_test = new Nombre();
        cli_test.setTratamiento(Tratamiento.SR);
        cli_test.setNombre("Juan Perez");
        cli_test.setApellidoPaterno("Boole");
        cli_test.setApellidoMaterno("Baron");
        SistemaVentaPasajes.getInstancia().createCliente(Rut.of("11.111.111-1"), cli_test, "888-888", "juan@aol.mk");
        IdPersona[] trip_test = new IdPersona[2];
        trip_test[0] = Rut.of("00.000.000-0");
        trip_test[1] = Rut.of("00.000.000-1");
        String[] nCom_test = {"Talquipen", "Nuble"};
        SistemaVentaPasajes.getInstancia().createViaje(LocalDate.of(1212, 12, 12), LocalTime.of(12, 12), 5000, 60,
                "AABB12", trip_test, nCom_test);
    }

    private void jesse() {
        System.out.println("""
                \s
                                 : ⠄⠄⠄⠄ ⠄⠄⠄⠄ ⠄⠄⠄⠄
                                 ⠄⠄⡔⠙⠢⡀⠄⠄⠄⢀⠼⠅⠈⢂⠄⠄⠄⠄
                                 ⠄⠄⡌⠄⢰⠉⢙⢗⣲⡖⡋⢐⡺⡄⠈⢆⠄⠄⠄
                                 ⠄⡜⠄⢀⠆⢠⣿⣿⣿⣿⢡⢣⢿⡱⡀⠈⠆⠄⠄
                                 ⠄⠧⠤⠂⠄⣼⢧⢻⣿⣿⣞⢸⣮⠳⣕⢤⡆⠄⠄
                                 ⢺⣿⣿⣶⣦⡇⡌⣰⣍⠚⢿⠄⢩⣧⠉⢷⡇⠄⠄
                                 ⠘⣿⣿⣯⡙⣧⢎⢨⣶⣶⣶⣶⢸⣼⡻⡎⡇⠄⠄
                                 ⠄⠘⣿⣿⣷⡀⠎⡮⡙⠶⠟⣫⣶⠛⠧⠁⠄⠄⠄
                                 ⠄⠄⠘⣿⣿⣿⣦⣤⡀⢿⣿⣿⣿⣄⠄⠄⠄⠄⠄
                                 ⠄⠄⠄⠈⢿⣿⣿⣿⣿⣷⣯⣿⣿⣷⣾⣿⣷⡄⠄
                                 ⠄⠄⠄⠄⠄⢻⠏⣼⣿⣿⣿⣿⡿⣿⣿⣏⢾⠇⠄
                                 ⠄⠄⠄⠄⠄⠈⡼⠿⠿⢿⣿⣦⡝⣿⣿⣿⠷⢀⠄
                                 ⠄⠄⠄⠄⠄⠄⡇⠄⠄⠄⠈⠻⠇⠿⠋⠄⠄⢘⡆
                                 ⠄⠄⠄⠄⠄⠄⠱⣀⠄⠄⠄⣀⢼⡀⠄⢀⣀⡜⠄
                                 ⠄⠄⠄⠄⠄⠄⠄⢸⣉⠉⠉⠄⢀⠈⠉⢏⠁⠄⠄
                                 ⠄⠄⠄⠄⠄⠄⡰⠃⠄⠄⠄⠄⢸⠄⠄⢸⣧⠄⠄
                                 ⠄⠄⠄⠄⠄⣼⣧⠄⠄⠄⠄⠄⣼⠄⠄⡘⣿⡆⠄
                                 ⠄⠄⠄⢀⣼⣿⡙⣷⡄⠄⠄⠄⠃⠄⢠⣿⢸⣿⡀
                                 ⠄⠄⢀⣾⣿⣿⣷⣝⠿⡀⠄⠄⠄⢀⡞⢍⣼⣿⠇
                                 ⠄⠄⣼⣿⣿⣿⣿⣿⣷⣄⠄⠄⠠⡊⠴⠋⠹⡜⠄
                                 ⠄⠄⣿⣿⣿⣿⣿⣿⣿⣿⡆⣤⣾⣿⣿⣧⠹⠄⠄
                                 ⠄⠄⢿⣿⣿⣿⣿⣿⣿⣿⢃⣿⣿⣿⣿⣿⡇⠄⠄
                                 ⠄⠄⠐⡏⠉⠉⠉⠉⠉⠄⢸⠛⠿⣿⣿⡟⠄⠄⠄
                                 ⠄⠄⠄⠹⡖⠒⠒⠒⠒⠊⢹⠒⠤⢤⡜⠁⠄⠄⠄
                                 ⠄⠄⠄⠄⠱⠄⠄⠄⠄⠄⢸
                                \s
                              ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢺⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⠜⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⠿⠿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⣿⣿⣿⣿⣿
                              ⣿⣿⡏⠁⠀⠀⠀⠀⠀⣀⣠⣤⣤⣶⣶⣶⣶⣶⣦⣤⡄⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿
                              ⣿⣿⣷⣄⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⡧⠇⢀⣤⣶⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣾⣮⣭⣿⡻⣽⣒⠀⣤⣜⣭⠐⢐⣒⠢⢰⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣿⣏⣿⣿⣿⣿⣿⣿⡟⣾⣿⠂⢈⢿⣷⣞⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣷⣶⣾⡿⠿⣿⠗⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠻⠋⠉⠑⠀⠀⢘⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⣿⡿⠟⢹⣿⣿⡇⢀⣶⣶⠴⠶⠀⠀⢽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⣿⣿⣿⡿⠀⠀⢸⣿⣿⠀⠀⠣⠀⠀⠀⠀⠀⡟⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⣿⣿⣿⡿⠟⠋⠀⠀⠀⠀⠹⣿⣧⣀⠀⠀⠀⠀⡀⣴⠁⢘⡙⢿⣿⣿⣿⣿⣿⣿⣿⣿
                              ⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⠗⠂⠄⠀⣴⡟⠀⠀⡃⠀⠉⠉⠟⡿⣿⣿⣿⣿
                              ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢷⠾⠛⠂⢹⠀⠀⠀⢡⠀⠀⠀⠀⠀⠙⠛⠿⢿
                             \s
                              Jesse, we need a 7,0
                 \s""");
    }
}