package com.elias.saladecine.interfaz;

import java.util.Map;
import java.util.Scanner;

import com.elias.saladecine.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static SalaCine sala;
    private static Pelicula[] peliculas = new Pelicula[100];
    
    public static void main(String[] args) {
        inicializarSistema();
        mostrarMenuPrincipal();
    }
    
    private static void inicializarSistema() {
        System.out.println("🎬 BIENVENIDOS AL SISTEMA DE GESTIÓN DE CINEMA 🎬");
        System.out.print("Ingrese el número de filas de la sala: ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese el número de columnas de la sala: ");
        int columnas = scanner.nextInt();
        
        sala = new SalaCine(filas, columnas);
        
        cargarPeliculasDefault();
        
        System.out.println("\n✅ Sistema inicializado correctamente!");
        System.out.println("Sala de " + filas + "x" + columnas + " creada exitosamente.");
        System.out.println("📽️  " + peliculas.length + " películas cargadas en el sistema.\n");
    }
    
    private static void cargarPeliculasDefault() {
        peliculas[0] = new PeliculaAccion("Misión Imposible", 150, 13);
        peliculas[1] = new PeliculaAccion("John Wick 4", 169, 16);
        peliculas[2] = new PeliculaAccion("Top Gun: Maverick", 130, 13);
        
        peliculas[3] = new PeliculaComedia("La Máscara", 120, 7);
        peliculas[4] = new PeliculaComedia("Shrek", 90, 0);
        
        peliculas[5] = new PeliculaDrama("Forrest Gump", 142, 13);
        peliculas[6] = new PeliculaDrama("El Padrino", 175, 16);
        
        peliculas[7] = new PeliculaTerror("El Conjuro", 112);
        peliculas[8] = new PeliculaTerror("Scream", 111);
        
        peliculas[9] = new PeliculaInfantil("Frozen", 102, 0);
    }
    
    private static void mostrarMenuPrincipal() {
        int opcion;
        
        do {
            System.out.println("=".repeat(50));
            System.out.println("🎭 MENU PRINCIPAL - GESTIÓN DE SALA DE CINE 🎭");
            System.out.println("=".repeat(50));
            System.out.println("1. 🎬 Gestionar películas");
            System.out.println("2. 🎫 Vender boleto");
            System.out.println("3. 🔓 Liberar asiento");
            System.out.println("4. 👀 Ver estado de la sala");
            System.out.println("5. 📋 Ver información de película actual");
            System.out.println("6. 👥 Ver detalle de compradores");
            System.out.println("7. 🎪 Reiniciar sala (liberar todos los asientos)");
            System.out.println("0. 🚪 Salir del sistema");
            System.out.println("=".repeat(50));
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    gestionarPeliculas();
                    break;
                case 2:
                    venderBoleto();
                    break;
                case 3:
                    liberarAsiento();
                    break;
                case 4:
                    mostrarButacas(sala);

                    break;
                case 5:
                    mostrarInfoPelicula();
                    break;
                case 6:
                    mostrarButacasDetalle(sala);
                    break;
                case 7:
                    reiniciarSala();
                    break;
                case 0:
                    System.out.println("¡Gracias por usar nuestro sistema de cinema! 🍿");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente de nuevo.");
            }
            
            if (opcion != 0) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
                scanner.nextLine();
            }
            
        } while (opcion != 0);
    }
    
    private static void gestionarPeliculas() {
        System.out.println("\n🎬 CATÁLOGO DE PELÍCULAS DISPONIBLES");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < peliculas.length; i++) {
            Pelicula peli = peliculas[i];
            if (peli != null) {
                String genero = obtenerGenero(peli);
                System.out.printf("%2d. [%s] %s (%d años+)\n",
                    (i + 1), genero, peli.getTitulo(), peli.getEdadMinima());
            } else {
                System.out.printf("%2d. [Sin asignar]\n", (i + 1));
            }
        }
        
        System.out.println("11. 📋 Ver sinopsis de película actual");
        System.out.println("=".repeat(50));
        System.out.print("Seleccione una película (1-11): ");
        
        int opcion = scanner.nextInt();
        
        if (opcion >= 1 && opcion <= 10) {
            Pelicula peliculaSeleccionada = peliculas[opcion - 1];
            cambiarPelicula(peliculaSeleccionada);
            peliculaSeleccionada.mostrarSinopsis();
        } else if (opcion == 11) {
            if (sala.getPeliculaActual() != null) {
                sala.getPeliculaActual().mostrarSinopsis();
            } else {
                System.out.println("❌ No hay película en cartelera actualmente.");
            }
        } else {
            System.out.println("❌ Opción inválida.");
        }
    }
    
    private static String obtenerGenero(Pelicula pelicula) {
        if (pelicula instanceof PeliculaAccion) return "ACCIÓN";
        if (pelicula instanceof PeliculaComedia) return "COMEDIA";
        if (pelicula instanceof PeliculaDrama) return "DRAMA";
        if (pelicula instanceof PeliculaTerror) return "TERROR";
        if (pelicula instanceof PeliculaInfantil) return "INFANTIL";
        return "GENERAL";
    }
    
    private static void venderBoleto() {
        if (sala.getPeliculaActual() == null) {
            System.out.println("❌ No hay película en cartelera. Primero seleccione una película.");
            return;
        }

        System.out.println("\n🎫 VENTA DE BOLETOS");
        mostrarButacas(sala);

        scanner.nextLine(); 
        System.out.print("Ingrese el nombre del comprador: ");
        String nombreComprador = scanner.nextLine();

        System.out.print("Ingrese la letra de la fila (A, B, C...): ");
        char letra = scanner.next().toUpperCase().charAt(0);

        System.out.print("Ingrese el número del asiento: ");
        int numero = scanner.nextInt();

        System.out.print("Ingrese la edad del cliente: ");
        int edad = scanner.nextInt();

        // Crear clave como en SalaCine: ejemplo "A1"
        String clave = letra + String.valueOf(numero);

        boolean exito = sala.venderBoleto(clave, edad, nombreComprador);

        if (exito) {
            System.out.println("🎉 ¡Boleto vendido exitosamente!");
            mostrarButacas(sala);
        } else {
            System.out.println("❌ No se pudo vender el boleto.");
        }
    }
    
    private static void liberarAsiento() {
        System.out.println("\n🔓 LIBERAR ASIENTO");
        mostrarButacas(sala);

        System.out.print("Ingrese la letra de la fila (A, B, C...): ");
        char letra = scanner.next().toUpperCase().charAt(0);

        System.out.print("Ingrese el número del asiento: ");
        int numero = scanner.nextInt();

        String clave = letra + String.valueOf(numero);

        boolean exito = sala.liberarAsiento(clave);

        if (exito) {
            System.out.println("🎉 ¡Asiento liberado exitosamente!");
            mostrarButacas(sala);
        } else {
            System.out.println("❌ Ese asiento no existe o ya estaba libre.");
        }
    }
    
    private static void mostrarInfoPelicula() {
        if (sala.getPeliculaActual() != null) {
            System.out.println("\n📋 INFORMACIÓN DE LA PELÍCULA ACTUAL");
            System.out.println(sala.getPeliculaActual().mostrarSinopsis());
        } else {
            System.out.println("❌ No hay película en cartelera actualmente.");
        }
    }
    
    private static void reiniciarSala() {
        System.out.print("⚠️  ¿Está seguro que desea liberar todos los asientos? (s/n): ");
        scanner.nextLine();
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s") || confirmacion.equalsIgnoreCase("si")) {
            sala.reiniciarSala();          
            System.out.println("✅ Todos los asientos han sido liberados.");
            mostrarButacas(sala);          
        } else {
            System.out.println("❌ Operación cancelada.");
        }
    }
    
    public static void mostrarButacas(SalaCine sala) {
        System.out.println("\n=== ESTADO DE LA SALA ===");
        if (sala.getPeliculaActual() != null) {
            System.out.println("🎬 Película: " + sala.getTitulo());
        }
        System.out.println("📊 Ocupación: " + sala.contarAsientosOcupados() + "/" + sala.getTotalAsientos() + " asientos");
        System.out.println("O = Libre, X = Ocupado\n");

        int filas = sala.getFilas();
        int columnas = sala.getColumnas();

        for (int i = 0; i < filas; i++) {
            char letra = (char) ('A' + i);
            System.out.print(letra + " ");
            for (int j = 1; j <= columnas; j++) {
                String clave = letra + String.valueOf(j);
                Asiento asiento = sala.getAsiento(clave);
                char estado = (asiento != null && asiento.estaOcupado()) ? 'X' : 'O';
                System.out.print(" " + estado);
            }
            System.out.println();
        }
    }
    
    public static void mostrarButacasDetalle(SalaCine sala) {
        if (sala == null) {
            System.out.println("No se ha proporcionado una sala válida.");
            return;
        }

        for (Map.Entry<String, Asiento> entry : sala.getButacas().entrySet()) {
            String clave = entry.getKey();
            Asiento asiento = entry.getValue();
            String estado = asiento.estaOcupado()
                    ? "Ocupado por " + asiento.getNombreComprador()
                    : "Libre";
            System.out.println(clave + ": " + estado);
        }
    }

    public static void cambiarPelicula(Pelicula nuevaPelicula) {
    	sala.cambiarPelicula(nuevaPelicula);
    	System.out.println("Pelicula cambiada a: " + nuevaPelicula.getTitulo());
    }

}
