package Programas;
import java.util.Scanner;

public class ProgramaInicial {
    //Definición de la constante
    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        // Declaración de Variables
        int numeroTareas = ingresarNumeroTareas();
        String[] nombresTareas = new String[numeroTareas];
        boolean[] tareasCumplidas = new boolean[numeroTareas];
        String[] empleados = new String[numeroTareas];
        int[] idEmpresarial = new int[numeroTareas];
        float[] horasRealizacion = new float[numeroTareas];
        int opcion;

        registrarTareas(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion);
        
        //Ejecución del bucle do-while
        do {
            menu();
            opcion = entrada.nextInt();

            switch(opcion) {
                case 1:
                    listarTareasCompletadas(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion);
                    break;
                case 2:
                    listarTareasPendientes(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion);
                    break;
                case 3:
                    realizarTarea(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion);
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                    entrada.close();
                    return;
                default:
                    System.out.println("Opción Invalida. Por favor, ingrese una opción del menú.");
            }

        } while(opcion >= 1 && opcion <= 4);
    }
    //Método para ingresar el número de tareas
    public static int ingresarNumeroTareas() {
        System.out.print("Ingrese el número de tareas: ");
        int numeroTareas = entrada.nextInt();

        return numeroTareas;
    }

    //Método para registrar las tareas
    public static void registrarTareas(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, int[] idEmpresarial, float[] horasRealizacion) {
        for(int i=0; i < nombresTareas.length; i++) {
            System.out.println("\nRegistro tarea " + (i+1) + ":");
            idEmpresarial[i] = i+1;
            tareasCumplidas[i] = false;
            System.out.print("Ingrese el nombre: ");
            nombresTareas[i] = entrada.next();
            System.out.print("Ingrese la persona responsable: ");
            empleados[i] = entrada.next();
            System.out.print("Ingrese el tiempo de realizacion (H.M): ");
            horasRealizacion[i] = entrada.nextFloat();
            //entrada.next();
        }
    }
    
    //Método para el menú
    public static void menu() {
        System.out.println("\n=== Control de Cumplimiento de Tareas ===");
        System.out.println("1. Tareas Completadas");
        System.out.println("2. Tareas Pendientes");
        System.out.println("3. Realizar Tarea");
        System.out.println("4. Salir");
        System.out.print("Ingrese una opción: ");
    }

    //Método para enseñar las tareas que han sido completadas
    public static void listarTareasCompletadas(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, int[] idEmpresarial, float[] horasRealizacion) {
        System.out.println("Tareas Completadas:\n");
        for(int i = 0; i < nombresTareas.length; i++) {
            if(tareasCumplidas[i]) {
                System.out.printf("ID: %d - Tarea: %s - Encargado: %s - Horas: %.1f\n", idEmpresarial[i], nombresTareas[i], empleados[i], horasRealizacion[i]);
            }
        }
    }
    
    //Método para enseñar las tareas que están pendientes
    public static void listarTareasPendientes(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, int[] idEmpresarial, float[] horasRealizacion) {
        System.out.println("Tareas Pendientes:\n");
        for(int i = 0; i < nombresTareas.length; i++) {
            if(!tareasCumplidas[i]) {
                System.out.printf("ID: %d - Tarea: %s - Encargado: %s - Horas: %.1f\n", idEmpresarial[i], nombresTareas[i], empleados[i], horasRealizacion[i]);
            }
        }
    }

    //Método para realizar las tareas
    public static void realizarTarea(String[] nombresTareas, boolean[] tareasCumplidas, String[] empleados, int[] idEmpresarial, float[] horasRealizacion) {
        listarTareasPendientes(nombresTareas, tareasCumplidas, empleados, idEmpresarial, horasRealizacion);
        int id;
        do {
            System.out.print("Ingrese el ID de la tarea: ");
            id = entrada.nextInt();
        } while ((id-1) < 0 || (id-1) >= nombresTareas.length);

        tareasCumplidas[id-1] = true;
    }
}
