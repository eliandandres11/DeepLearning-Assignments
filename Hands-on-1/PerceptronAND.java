public class PerceptronAND {

    // 1. Abstracción orientada a objetos: Definimos los atributos del perceptrón.
    private double w1; // Peso para la primera entrada
    private double w2; // Peso para la segunda entrada
    private double b;  // Bias o sesgo

    public PerceptronAND() {
        // 3. Los parámetros del perceptrón se inicializan desde el código fuente sin capturar datos.
        // Inicializamos los pesos y el bias en 0.
        this.w1 = 0.0;
        this.w2 = 0.0;
        this.b = 0.0;
    }

    // Método para entrenar al perceptrón
    public void entrenar(int[][] entradas, int[] salidasEsperadas) {
        boolean hayErrores = true; // Bandera para saber si el perceptrón ya aprendió

        // El ciclo continúa hasta que el perceptrón clasifique todo correctamente (converja).
        while (hayErrores) {
            hayErrores = false; // Asumimos que no habrá errores en esta vuelta

            for (int i = 0; i < entradas.length; i++) {
                int x1 = entradas[i][0];
                int x2 = entradas[i][1];
                int salidaDeseada = salidasEsperadas[i];

                // Calculamos la Suma Ponderada: (w1 * x1) + (w2 * x2) + b[cite: 1].
                double sumaPonderada = (this.w1 * x1) + (this.w2 * x2) + this.b;

                // Función de Activación (Escalón): Si la suma es mayor a 0, da 1; si no, da 0[cite: 1].
                int salidaCalculada = (sumaPonderada > 0) ? 1 : 0;

                // Calculamos el error (Diferencia entre lo que queremos y lo que nos dio)
                int error = salidaDeseada - salidaCalculada;

                // Si hay un error, ajustamos los pesos y el bias
                if (error != 0) {
                    // 1. Actualización de pesos SIN el parámetro de velocidad de aprendizaje[cite: 1].
                    // La fórmula estándar es w_nuevo = w_viejo + (tasa * error * entrada), pero al quitar la tasa, queda así:
                    this.w1 = this.w1 + (error * x1);
                    this.w2 = this.w2 + (error * x2);
                    this.b = this.b + error;
                    
                    hayErrores = true; // Como hubo error, marcamos que debemos volver a revisar todos los patrones
                }
            }
        }
    }

    // Método para imprimir los resultados requeridos por el profesor
    public void mostrarResultados(int[][] entradas) {
        // 4. Se imprimen en la terminal los resultados finales[cite: 1].
        System.out.println("Resultados tras el entrenamiento del Perceptron (Compuerta AND):");
        
        // 4a. Imprimir valores óptimos de los pesos w_1 y w_2[cite: 1].
        System.out.println("a. Los valores optimos de los pesos son: w_1 = " + this.w1 + " y w_2 = " + this.w2);
        
        // 4b. Imprimir el valor óptimo del bias[cite: 1].
        System.out.println("b. El valor optimo del bias (b) es: " + this.b);
        
        System.out.println("\nc. Calculos -paso a paso- para cada patron[cite: 1]:");
        // 4c. Imprimir los cálculos de Suma Ponderada y Función de Activación por patrón[cite: 1].
        for (int i = 0; i < entradas.length; i++) {
            int x1 = entradas[i][0];
            int x2 = entradas[i][1];

            // 5. Cálculos manuales paso a paso sin usar librerías[cite: 1].
            double sumaPonderada = (this.w1 * x1) + (this.w2 * x2) + this.b;
            int activacion = (sumaPonderada > 0) ? 1 : 0;

            System.out.println("   Patron [" + x1 + ", " + x2 + "]:");
            System.out.println("      - Suma Ponderada = (" + this.w1 + " * " + x1 + ") + (" + this.w2 + " * " + x2 + ") + (" + this.b + ") = " + sumaPonderada);
            System.out.println("      - Funcion de Activacion = (Suma Ponderada > 0) -> " + sumaPonderada + " > 0 ? " + activacion);
        }
    }

    public static void main(String[] args) {
        // 3. Los patrones de entrenamiento para la compuerta AND se inicializan desde el código fuente[cite: 1].
        // 2. El perceptrón aprenderá a reconocer la compuerta lógica AND[cite: 1].
        int[][] entradasAND = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        // Resultados esperados para una compuerta AND (solo da 1 cuando ambos son 1)
        int[] salidasAND = {0, 0, 0, 1};

        // Instanciamos el perceptrón
        PerceptronAND miPerceptron = new PerceptronAND();

        // Entrenamos
        miPerceptron.entrenar(entradasAND, salidasAND);

        // Mostramos la comprobación de que el clasificador convergió correctamente[cite: 1].
        miPerceptron.mostrarResultados(entradasAND);
    }
}