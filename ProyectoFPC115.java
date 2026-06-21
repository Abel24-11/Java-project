import java.util.Scanner;
import java.io.*;
import java.nio.file.*;
import java.util.List;
public class ProyectoFPC115{
    public static Scanner sc = new Scanner(System.in);
     public static void main(String[] args) throws IOException{
       //PROGRAMA CREADO POR LOS ESTUDIANTES
       //Jonatha Esau Ramirez Mercedes RM25034
       // Abel Ernesto Rodriguez Rivera RR24106
       //Diego Rene Castro Ramirez CR24024
       //Nestor Alan Chavarria Pineda CP25020

       //Este programa Lee un archivo csv y lo muestra en pantalla para que
       //posteriormente el usuario escoga la opcion sobre lo que desea realizar

        System.out.println("====Bienvenido=====");
        //ARREGLOS PARA GUARDAR LOS DATOS DEL ARCHIVO
        int numeros[] = new int[44];
        String municipios[] = new String[44];
        double areas[] = new double[44];
        int poblaciones[] = new int[44];
        int impuestos[] = new int[44];

        //LLAMAMOS LOS MODULOS
        LeerArchivo(numeros, municipios, areas, poblaciones,impuestos);
        Menu(numeros, municipios, areas, poblaciones, impuestos);
       
        // Creamos un modulo para permitirle al usuario elegir su operacion y permitirle repetir por si quiere hacer otra
        }
        public static void Menu(int[] numeros,String[] municipios,double[] areas,int[] poblaciones,int[] impuestos)throws IOException{
             int opcion = 0;
             int continuar = 1;
             while (continuar == 1) {
                opcion = 0;
                 while (opcion < 1 || opcion > 8) {                 //Validamos que la opcion 
            System.out.println("\n QUE DESEA HACER?");
        System.out.println("1. Ver densidad");
        System.out.println("2. Actualizar poblacion");
        System.out.println("3. Ver  5 municipios con Mayor Poblacion");
        System.out.println("4.Ver devolucion de impuestos");
        System.out.println("5.Ver 7 municipios con Menor Poblacion");
        System.out.println("6.Ver municipio mas pequeno");
        System.out.println("7.Ordenar Municipios Alfabeticamente");
        System.out.println("8. Ordenar densidad (Quick Sort)");
     
        System.out.println("Seleccione una opcion");
         opcion = sc.nextInt();
        sc.nextLine();
        if (opcion < 1 || opcion > 8) {
            System.out.println("Ingrese una opcion valida");
            
        }
         }
         //MOSTRADOS MENU PARA  QUE EL USUARIO PIDA 
         switch (opcion) {
            case 1:
               Densidad(numeros, municipios, areas, poblaciones, impuestos);
                
                break;
            case 2:
                if (ValidarEmpleado()) {
                    ActualizarPoblacion(municipios, poblaciones);
                    GuardarArchivo(numeros, municipios, areas, poblaciones, impuestos);
                    
                }
                break;
            case 3:
                MayorPoblacion(numeros, municipios, areas, poblaciones);
                break;
            case 4:
                Impuestos(municipios, areas, poblaciones, impuestos);
                break;
            case 5:
                MenorPoblacion(numeros, municipios, areas, poblaciones);
                break;
            case 6:
                MunicipoPeque(numeros, municipios, areas, poblaciones);
                break;
            case 7:
                OrdenarMunicipios(numeros, municipios, areas, poblaciones, impuestos);
                break;
            case 8:
                OrdenarDensidad(numeros, municipios, areas, poblaciones, impuestos);
                break;
           

               
         
            
         } //Volvemos a pregunatar si quiere hacer otra operacion
         System.out.println("\n DESEA RELIZAR OTRA OPERACION?");
         System.out.println("1. SI");
         System.out.println("2. NO");
         continuar = sc.nextInt();
         sc.nextLine();
                
             }
             System.out.println("PROGRAMA FINALIZADO");
       
        }
        
        
     public static void LeerArchivo(int [] numeros, String[] municipios,double[] areas,int[] poblaciones,int[] impuestos)throws IOException{
        // Leer archivo CSV
         List<String> lineas =
          Files.readAllLines(
          Path.of("DatosDePrueba.csv"));
          System.out.println("-------------------------------------------------");
          System.out.printf("%-15s %-20s %-12s %-12s %-12s%n",
        "NUMERO", "MUNICIPIO", "AREA", "POBLACION", "IMPUESTOS");

        // Saltar encabezado
          for (int i = 1;
         i < lineas.size(); i++) {
          String[] campos =
          lineas.get(i).split(";");
             int numero = Integer.parseInt(campos[0]);
              String municipio = campos[1];
             double area = Double.parseDouble(campos[2]);
             int poblacion = Integer.parseInt(campos[3].replace(",", ""));
             int impuesto = Integer.parseInt(campos[4]);
             numeros[i-1]= numero;
             municipios[i-1]= municipio;
             areas[i-1]= area;
             poblaciones[i-1]= poblacion;
             impuestos[i-1] = impuesto;
             System.out.printf(
             "%-8d  %-25s  %-12.2f %-12d %-12d%n",
              numero, municipio, area, poblacion, impuesto);
        
           }
            System.out.println("-----------------------------------------------------");
        }
        public static void ActualizarPoblacion(String[] municipios, int[] poblaciones) {

          

           System.out.print("Ingrese el municipio a actualizar: ");
          String buscar = sc.nextLine().trim();

           boolean encontrado = false;

           for (int i = 0; i < municipios.length; i++) {

          if (municipios[i] != null &&
             municipios[i].equalsIgnoreCase(buscar)) {

            System.out.println("Población actual: " + poblaciones[i]);

            System.out.print("Ingrese la nueva población: ");
            poblaciones[i] = sc.nextInt();

           encontrado = true;
            System.out.println("Población actualizada.");
            
        }else{encontrado = false;}   /////////EDITADO
    }

     if (!encontrado) {
        System.out.println("Municipio no encontrado.");
    }
}
public static void GuardarArchivo(int[] numeros,String[] municipios, double[] areas,int[] poblaciones, int[] impuestos)throws IOException {

     BufferedWriter bw = Files.newBufferedWriter(
        Path.of("DatosDePrueba.csv"));

      bw.write("Numero;Municipio;Area;Poblacion;Impuestos");
     bw.newLine();

     for (int i = 0; i < municipios.length; i++) {

        if (municipios[i] != null) {

            bw.write(numeros[i] + ";" +municipios[i] + ";" +areas[i] + ";" +poblaciones[i] + ";" +impuestos[i]);

            bw.newLine();
        }
    }

    bw.close();

    System.out.println("Datos guardados correctamente.");
    
}
public static boolean ValidarEmpleado() {

    int[] codigos = {1001,1002,1003,1004,1005,
                     1006,1007,1008,1009,1010};

    String[] nombres = {"Juan","Maria","Pedro","Ana","Luis",
                        "Carlos","Sofia","Jose","Marta","Rosa"};

    System.out.print("Ingrese código de empleado: ");
    int codigo = sc.nextInt();
    sc.nextLine();

    System.out.print("Ingrese nombre del empleado: ");
    String nombre = sc.nextLine();

    for (int i = 0; i < codigos.length; i++) {

        if (codigo == codigos[i] &&
            nombre.equalsIgnoreCase(nombres[i])) {

            System.out.println("Acceso autorizado.");
           return true;
        }
    }

    System.out.println("Empleado no autorizado.");
    return false;
}
public static void Densidad(int[] numeros, String[] municipios, double[] areas, int[] poblaciones,int[] impuestos){
    int i = 0;
    double[] densidades = new double[municipios.length];
    System.out.println("-------------------------------------------------------");
    System.out.printf("%-8s %-20s %-12s %-12s %-12s %-15s%n",
        "NUMERO","MUNICIPIO","AREA","POBLACION","IMPUESTOS","DENSIDAD"
    );System.out.println("------------------------------------------------------");

    for(i = 0;i < municipios.length;i ++){
        if (municipios[i] != null) {
            densidades[i] = poblaciones[i]/areas[i];

         System.out.printf("%-8d %-20s %-12.2f %-12d %-12d %-15.2f%n", numeros[i],municipios[i],areas[i],poblaciones[i],impuestos[i],densidades[i]);
        }
    }
    System.out.println("--------------------------------------------------------------");
}
public static void MayorPoblacion(int[] numeros, String[] municipios,double[] areas,int [] poblaciones){
    
    

    // Copiar arreglos
    int[] numerosCopia = new int[numeros.length];
    String[] municipiosCopia = new String[municipios.length];
    double[] areasCopia = new double[areas.length];
    int[] poblacionesCopia = new int[poblaciones.length];

    for (int i = 0; i < numeros.length; i++) {
        numerosCopia[i] = numeros[i];
        municipiosCopia[i] = municipios[i];
        areasCopia[i] = areas[i];
        poblacionesCopia[i] = poblaciones[i];
}
  //Ordenamos poblacion de mayor a menor METODO DE BURBUJA
  for(int i = 0; i < poblacionesCopia.length -1;i ++){
    for ( int j = 0; j < poblacionesCopia.length -i -1; j ++){
        if (poblacionesCopia[j] < poblacionesCopia[j + 1]) {
            //Intercambio de valores
            int temporal = poblacionesCopia[j];
            poblacionesCopia[j] = poblacionesCopia[j +1];
            poblacionesCopia[j+1] = temporal; 
        
           //Intercambio de municipio
           String temu = municipiosCopia[j];
           municipiosCopia[j] = municipiosCopia[j+1];
           municipiosCopia[j+1] = temu;

           //Intercambio de area
           double areaT = areasCopia[j];
           areasCopia[j] = areasCopia[j+1];
           areasCopia[j+1] = areaT;

           //Intercambio de numero
           int numT = numerosCopia[j];
           numerosCopia[j] = numerosCopia[j+1];
           numerosCopia[j+1] = numT;
          } 
    }
 }
 System.out.println("\n ==== 5 MUNICIPIOS MAS POBLADOS ====");
 for(int i =0; i < 5 && municipiosCopia[i] != null;i ++){
    System.out.printf("%d. %-20s %d habitantes%n", i + 1, municipiosCopia[i], poblacionesCopia[i]);
 }

}
public static void MenorPoblacion(int[] numeros, String[] municipios,double[] areas,int [] poblaciones){
    
    

    // Copiar arreglos 
    int[] numerosCopia = new int[numeros.length];
    String[] municipiosCopia = new String[municipios.length];
    double[] areasCopia = new double[areas.length];
    int[] poblacionesCopia = new int[poblaciones.length];

    for (int i = 0; i < numeros.length; i++) {
        numerosCopia[i] = numeros[i];
        municipiosCopia[i] = municipios[i];
        areasCopia[i] = areas[i];
        poblacionesCopia[i] = poblaciones[i];
}
  //Ordenamos poblacion de MENOR A MAYOR A METODO DE BURBUJA
  for(int i = 0; i < poblacionesCopia.length -1;i ++){
    for ( int j = 0; j < poblacionesCopia.length -i -1; j ++){
        if (poblacionesCopia[j] > poblacionesCopia[j + 1]) {
            //Intercambio de valores
            int temporal = poblacionesCopia[j];
            poblacionesCopia[j] = poblacionesCopia[j +1];
            poblacionesCopia[j+1] = temporal; 
        
           //Intercambio de municipio
           String temu = municipiosCopia[j];
           municipiosCopia[j] = municipiosCopia[j+1];
           municipiosCopia[j+1] = temu;


           //Intercambio de area
           double areaT = areasCopia[j];
           areasCopia[j] = areasCopia[j+1];
           areasCopia[j+1] = areaT;

           //Intercambio de numero
           int numT = numerosCopia[j];
           numerosCopia[j] = numerosCopia[j+1];
           numerosCopia[j+1] = numT;
          } 
    }
 }
 System.out.println("\n ==== 7 MUNICIPIOS MENOS POBLADOS ====");
 for(int i =0; i < 7 && municipiosCopia[i] != null;i ++){
    System.out.printf("%d. %-20s %d habitantes%n", i + 1, municipiosCopia[i], poblacionesCopia[i]);
 }


}
public static void MunicipoPeque(int[] numeros,String[] municipios,double[] areas, int[] poblaciones){
    double areaMenor = areas[0];
    String municipioMenor = municipios[0];
    
    for(int i = 1;i < municipios.length; i ++){
        if (areas[i] < areaMenor) {
            areaMenor = areas[i];
            municipioMenor = municipios[i];
            
        }

    }
    System.out.println("\nMUNICIPIO MAS PEQUENO ");
    System.out.println("El municipio mas pequeño es:" +municipioMenor);
    System.out.println("Su area es de:" +areaMenor);
}
public static void OrdenarMunicipios(int[] numeros,String[] municipios,double[] areas,int[] poblaciones,int[] impuestos){
    for(int i = 0;i < municipios.length -1;i ++){
        for(int j = 0; j < municipios.length -i -1;j ++){
            if (municipios[j].compareToIgnoreCase(municipios[j +1])> 0) {
                 
                //Intercambiamos los municipios
                String Tmunicipos = municipios[j];
                municipios[j] = municipios[j+1];
                municipios[j+1] = Tmunicipos;

                //Intercambiamos las areas
                double Tarea = areas[j];
                areas[j] = areas[j+1];
                areas[j+1] = Tarea;

                //Intercambiamos los numeros
                int Tnumero = numeros[j];
                numeros[j] = numeros[j+1];
                numeros[j+1] = Tnumero;

                //Intercambiamos las poblaciones
                int Tpoblacion = poblaciones[j];
                poblaciones[j] = poblaciones[j+1];
                poblaciones[j+1] = Tpoblacion;

                //Intercambiamos impuestos
                int Timpuestos = impuestos[j];
                impuestos[j] = impuestos[j+1];
                impuestos[j+1] = Timpuestos;

                
                
            }
        }
    }
   System.out.println("\n MUNICIPIOS ORDENADOS ALFABETICAMENTE");
   System.out.printf(" %-20s %-12s %-12s %-12s%n","MUNICIPIO","AREA","POBLACION","IMPUESTOS");
   for( int i = 0; i < municipios.length; i ++){
    System.out.printf(" %-20s %-12s %-12s %-12s%n",municipios[i], areas[i],poblaciones[i],impuestos[i]);

   }
}
public static void Impuestos(String[] municipios, double[] areas, int[] poblaciones, int[] impuestos){
    System.out.println("\n====MUNICIPIOS CON MAS DE 100,000 HABITANTES===");
     System.out.printf(" %-25s %-12s %-12s %-12s%n","MUNICIPIO","AREA","POBLACION","DEVOLUCION");
     for(int i =0; i < municipios.length; i ++){
        if (poblaciones[i] > 100000) {
            double devolucion = impuestos[i]*0.10;
             System.out.printf(" %-25s %-12.2f %-12d %-12.2f%n",municipios[i], areas[i],poblaciones[i],devolucion);

            
        }
      
     }
     System.out.println("A ESTOS MUNICIPIOS SE LES DEVOLVERA EL 10% DE LOS IMPUESTOS RECAUDADOS HASTA LA FECHA");
   
}
public static void OrdenarDensidad(int[] numeros, String[] municipios, double[] areas,int[] poblaciones, int[] impuestos){

    int[] densidades = new int[municipios.length];

    // Calcular densidades
    for(int i = 0; i < municipios.length; i++){
        densidades[i] = (int)(poblaciones[i] / areas[i]);
    }

    // Ordenar con Quick Sort
    quickSort(numeros, areas, municipios,
              poblaciones, impuestos,
              densidades,
              0,
              densidades.length - 1);

    // Mostrar resultados
    System.out.println("\n=== DENSIDADES ORDENADAS DE MENOR A MAYOR ===");

    System.out.printf("%-8s %-20s %-12s %-12s %-12s %-12s%n", "NUMERO", "MUNICIPIO", "AREA", "POBLACION", "IMPUESTOS","DENSIDAD");

    for(int i = 0; i < municipios.length; i++){

        System.out.printf("%-8d %-20s %-12.2f %-12d %-12d %-12d%n",
                numeros[i],
                municipios[i],
                areas[i],
                poblaciones[i],
                impuestos[i],
                densidades[i]);
    }
}

 // Método principal recursivo de QuickSort
    public static void quickSort(int[] numeros,double[] areas,String[] municipios,int [] poblaciones,int[] impuestos,int[] densidades, int bajo, int alto) {

        // Condición de parada
        if (bajo < alto) {

            // Coloca el pivote en su lugar correcto
            int indiceParticion = particion(numeros, areas, municipios, poblaciones, impuestos,densidades, bajo, alto);

            // Ordena subarreglo izquierdo
            quickSort( numeros, areas, municipios, poblaciones, impuestos,densidades,bajo, indiceParticion - 1);

            // Ordena subarreglo derecho
            quickSort( numeros, areas, municipios, poblaciones, impuestos,densidades,indiceParticion + 1, alto);
        }
    }
    // Método encargado de elegir el pivote y acomodar menores y mayores
    private static int particion(int[] numeros,double[] areas,String[] municipios,int [] poblaciones,int[] impuestos,int[] densidades, int bajo, int alto) {

        // Elegimos el último elemento como pivote
        int pivote = densidades[alto];

        // Índice del elemento más pequeño
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {

            // Si el elemento actual es menor o igual al pivote
            if (densidades[j] <= pivote) {

                i++;

                // Intercambio de arr[i] y arr[j]
                int temporal = densidades[i];
                densidades[i] = densidades[j];
                densidades[j] = temporal;

                 // Intercambio numeros
                 int tempN = numeros[i];
                  numeros[i] = numeros[j];
                  numeros[j] = tempN;

                  // Intercambio municipios
                  String tempM = municipios[i];
                   municipios[i] = municipios[j];
                   municipios[j] = tempM;

                   // Intercambio areas
                 double tempA = areas[i];
                 areas[i] = areas[j];
                 areas[j] = tempA;

                 // Intercambio poblaciones
                 int tempP = poblaciones[i];
                 poblaciones[i] = poblaciones[j];
                 poblaciones[j] = tempP;

                  // Intercambio impuestos
                  int tempI = impuestos[i];
                 impuestos[i] = impuestos[j];
                  impuestos[j] = tempI;
            }
        }

        // Intercambio del pivote con el elemento en la posición correcta
          int temporal = densidades[i + 1];
           densidades[i + 1] = densidades[alto];
          densidades[alto] = temporal;

           int tempN = numeros[i + 1];
            numeros[i + 1] = numeros[alto];
          numeros[alto] = tempN;

           String tempM = municipios[i + 1];
           municipios[i + 1] = municipios[alto];
           municipios[alto] = tempM;

          double tempA = areas[i + 1];
           areas[i + 1] = areas[alto];
          areas[alto] = tempA;

          int tempP = poblaciones[i + 1];
           poblaciones[i + 1] = poblaciones[alto];
          poblaciones[alto] = tempP;

         int tempI = impuestos[i + 1];
          impuestos[i + 1] = impuestos[alto];
         impuestos[alto] = tempI;

         return i + 1;
    }

}

