public class PerceptronOR {

    // 1. LAS PARTES DEL PERCEPTRON 
    private double w1;
    private double w2;
    private double b;
    private double velocidadAprendizaje; 

    public PerceptronOR() {
        // ZONA DE PARAMETROS INICIALES
        this.w1 = 0.0;
        this.w2 = 0.0;
        this.b = 0.0;
        this.velocidadAprendizaje = 0.1; 
    }

    // Metodo donde el perceptron aprende
    public void entrenar(int[][] entradas, int[] salidasEsperadas) {
        boolean hayErrores = true;

        while (hayErrores) {
            hayErrores = false;

            for (int i = 0; i < entradas.length; i++) {
                int x1 = entradas[i][0];
                int x2 = entradas[i][1];
                int salidaReal = salidasEsperadas[i];

                // --- FORMULA 1: LA SUMA PONDERADA ---
                double sumaPonderada = (this.w1 * x1) + (this.w2 * x2) + this.b;

                // --- FORMULA 2: LA FUNCION DE ACTIVACION ---
                //Ahora es "mayor o igual a cero" (>= 0)
                int miPrediccion = (sumaPonderada >= 0) ? 1 : 0;

                int error = salidaReal - miPrediccion;

                if (error != 0) {
                    // --- FORMULA 3: AJUSTE DE PESOS (CON VELOCIDAD) ---
                    this.w1 = this.w1 + (this.velocidadAprendizaje * error * x1);
                    this.w2 = this.w2 + (this.velocidadAprendizaje * error * x2);
                    this.b = this.b + (this.velocidadAprendizaje * error);
                    
                    hayErrores = true;
                }
            }
        }
    }

    // 3. IMPRIMIR RESULTADOS  
    public void mostrarResultados(int[][] entradas) {
        System.out.println("=== COMPUERTA OR (CON VELOCIDAD DE APRENDIZAJE) ===");
        System.out.println("a. Pesos finales: w1 = " + String.format("%.1f", this.w1) + " | w2 = " + String.format("%.1f", this.w2));
        System.out.println("b. Bias final: b = " + String.format("%.1f", this.b));
        System.out.println("c. Comprobacion paso a paso:");
        
        for (int i = 0; i < entradas.length; i++) {
            int x1 = entradas[i][0];
            int x2 = entradas[i][1];
            double suma = (this.w1 * x1) + (this.w2 * x2) + this.b;
            
         
            int act = (suma >= 0) ? 1 : 0;

            System.out.println("   Patron [" + x1 + ", " + x2 + "]:");
            System.out.println("      Suma Ponderada: (" + String.format("%.1f", this.w1) + " * " + x1 + ") + (" + String.format("%.1f", this.w2) + " * " + x2 + ") + (" + String.format("%.1f", this.b) + ") = " + String.format("%.1f", suma));
            System.out.println("      Activacion: " + String.format("%.1f", suma) + " >= 0 ? -> Da como resultado: " + act);
        }
    }

    // 4. EL MAIN
    public static void main(String[] args) {
        // ZONA DE DATOS
        int[][] entradas = {
            {0, 0}, {0, 1}, {1, 0}, {1, 1}
        };
        int[] salidas = {0, 1, 1, 1};

        PerceptronOR p = new PerceptronOR();
        p.entrenar(entradas, salidas);
        p.mostrarResultados(entradas);
    }
}