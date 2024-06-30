package Programa;

import java.util.Scanner;

public class Programa_implementacion {
        //Uso de final para indicar que la variable es de tipo constante y no puede ser modificable       
        private static final Scanner entrada = new Scanner(System.in);
        private static final int MAX_TAREAS = 100; // Permite definir el máximo de tareas que el sistema puede manejar

        public static void main(String[] args) {
            // Declaración de Variables
            String[] nombresTareas = new String[MAX_TAREAS];
            boolean[] tareasCumplidas = new boolean[MAX_TAREAS];
            String[] empleados = new String[MAX_TAREAS];
            String[] idEmpresarial = new String[MAX_TAREAS];
            float[] horasRealizacion = new float[MAX_TAREAS];
            int cantidadTareasActual = 0;
            int opcion;
            
            //Ejecución del bucle do-while        
            do {
                menu();
                opcion = entrada.nextInt();
                
                //Estructura selectiva switch
                switch (opcion) {
                    case 1:
                        cantidadTareasActual = registrarTareas(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion, cantidadTareasActual);
                        System.out.println("Cantidad tarea actual: " + cantidadTareasActual);
                        break;
                    case 2:
                        listarTareasCompletadas(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion, cantidadTareasActual);
                        break;
                    case 3:
                        listarTareasPendientes(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion, cantidadTareasActual);
                        break;
                    case 4:
                        realizarTarea(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion, cantidadTareasActual);
                        break;
                    case 5:
                        System.out.println("Saliendo del programa");
                        entrada.close();
                        return;
                    default:
                        System.out.println("Opción Invalida. Por favor, ingrese una opción del menú.");
                }
            } while (opcion >= 1 && opcion <= 5);
        }

        // Método para agregar multiples tareas        
        public static int registrarTareas(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, String[] idEmpresarial, float[] horasRealizacion, int cantidadTareas) {
            if (cantidadTareas >= MAX_TAREAS) {
                System.out.println("No se pueden registrar más tareas, límite alcanzado.");
            } else {
                String respuesta;
                do {
                    registrarTarea(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion, cantidadTareas);
                    cantidadTareas++;

                    if (cantidadTareas >= MAX_TAREAS) {
                        System.out.println("Se ha alcanzado el número máximo de tareas.");
                        break;
                    }

                    System.out.print("¿Desea agregar una nueva tarea [S/N]?: ");
                    respuesta = entrada.next().toUpperCase();
                } while ("S".equals(respuesta));
            }
            return cantidadTareas;
        }
        
        //Método para recoger la información de la nueva tarea      
        public static void registrarTarea(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, String[] idEmpresarial, float[] horasRealizacion, int pos) {
            System.out.println("\nRegistro tarea " + (pos + 1) + ":");
            tareasCumplidas[pos] = false;
            System.out.print("Ingrese el nombre: ");
            nombresTareas[pos] = entrada.next();
            System.out.print("Ingrese la persona responsable: ");
            empleados[pos] = entrada.next();
            System.out.print("Ingrese el id empresarial: ");
            idEmpresarial[pos] = entrada.next();
            System.out.print("Ingrese el tiempo de realizacion (H.M): ");
            horasRealizacion[pos] = entrada.nextFloat();
        }

        //Método para mostrar las opciones del programa
        public static void menu() {
            System.out.println("\n=== Control de Cumplimiento de Tareas ===");
            System.out.println("1. Registrar Tarea");
            System.out.println("2. Tareas Completadas");
            System.out.println("3. Tareas Pendientes");
            System.out.println("4. Realizar Tarea");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
        }

        //Método para enseñar la lista de tareas completadas
        public static void listarTareasCompletadas(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, String[] idEmpresarial, float[] horasRealizacion, int cantidadTareas) {
            System.out.println("Tareas Completadas:\n");
            for (int i = 0; i < cantidadTareas; i++) {
                if (tareasCumplidas[i]) {
                    System.out.printf("ID: %d - Tarea: %s - Encargado: %s - IdEmpresarial: %s - Horas: %.1f\n", (i + 1), nombresTareas[i], idEmpresarial[i], empleados[i], horasRealizacion[i]);
                }
            }
        }

        //Método para enseñar la lista de tareas pendientes 
        public static void listarTareasPendientes(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, String[] idEmpresarial, float[] horasRealizacion, int cantidadTareas) {
            System.out.println("Tareas Pendientes:\n");
            for (int i = 0; i < cantidadTareas; i++) {
                if (!tareasCumplidas[i]) {
                    System.out.printf("ID: %d - Tarea: %s - Encargado: %s - IdEmpresarial: %s - Horas: %.1f\n", (i + 1), nombresTareas[i], idEmpresarial[i], empleados[i], horasRealizacion[i]);
                }
            }
        }
        //Método para realizar la tarea        
        public static void realizarTarea(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, String[] idEmpresarial, float[] horasRealizacion, int cantidadTareas) {
            listarTareasPendientes(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion, cantidadTareas);
            int id;
            do {
                System.out.print("Ingrese el ID de la tarea que sea diferente de 0: ");
                id = entrada.nextInt();
            } while ((id - 1) < 0 || (id - 1) >= cantidadTareas);

            tareasCumplidas[id - 1] = true;
        }
    }
