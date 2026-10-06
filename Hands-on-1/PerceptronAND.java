public class PerceptronAND {

    // 1. LAS PARTES DEL PERCEPTRON 
    private double w1; 
    private double w2; 
    private double b; 

    public PerceptronAND() {
        // ZONA DE PARAMETROS INICIALES
        this.w1 = 0.0;
        this.w2 = 0.0;
        this.b = 0.0;
    }

    //Metodo donde el perceptron aprende a base de prueba y error
    public void entrenar(int[][] entradas, int[] salidasEsperadas) {
        boolean hayErrores = true; // Por si se equivoca 

        
        while (hayErrores) {
            hayErrores = false; 

            for (int i = 0; i < entradas.length; i++) {
                int x1 = entradas[i][0];
                int x2 = entradas[i][1];
                int salidaReal = salidasEsperadas[i];

                // FORMULA 1: LA SUMA PONDERADA
                // Multiplica entradas por pesos y suma el bias
                double sumaPonderada = (this.w1 * x1) + (this.w2 * x2) + this.b;

                // FORMULA 2: LA FUNCION DE ACTIVACION
                // Si la suma de arriba dio más que cero, responde 1. Si no, responde 0.
                int miPrediccion = (sumaPonderada > 0) ? 1 : 0;

                //(Deseado - Predicción)
                int error = salidaReal - miPrediccion;

                // Si se equivocó (error no es 0), corregimos los pesos
                if (error != 0) {
                    //AJUSTE DE PESOS
                    // NO usar velocidad de aprendizaje, sumamos el error directo
                    this.w1 = this.w1 + (error * x1);
                    this.w2 = this.w2 + (error * x2);
                    this.b = this.b + error;
                    
                    hayErrores = true; 
                }
            }
        }
    }

    
    public void mostrarResultados(int[][] entradas) {
        System.out.println("=== COMPUERTA AND (SIN VELOCIDAD DE APRENDIZAJE) ===");
        System.out.println("a. Pesos finales: w1 = " + this.w1 + " | w2 = " + this.w2);
        System.out.println("b. Bias final: b = " + this.b);
        System.out.println("c. Comprobacion paso a paso:");
        
        for (int i = 0; i < entradas.length; i++) {
            int x1 = entradas[i][0];
            int x2 = entradas[i][1];
            double suma = (this.w1 * x1) + (this.w2 * x2) + this.b;
            int act = (suma > 0) ? 1 : 0;

            System.out.println("   Patron [" + x1 + ", " + x2 + "]:");
            System.out.println("      Suma Ponderada: (" + this.w1 + " * " + x1 + ") + (" + this.w2 + " * " + x2 + ") + (" + this.b + ") = " + suma);
            System.out.println("      Activacion: " + suma + " > 0 ? -> Da como resultado: " + act);
        }
    }

    
    public static void main(String[] args) {
        // ZONA DE DATOS
        int[][] entradas = {
            {0, 0}, {0, 1}, {1, 0}, {1, 1}
        };
        // Resultados para el AND (solo el 1 y 1 da 1)
        int[] salidas = {0, 0, 0, 1};

        PerceptronAND p = new PerceptronAND();
        p.entrenar(entradas, salidas); 
        p.mostrarResultados(entradas);
    }
}