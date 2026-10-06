public class PerceptronOR {

    // 1. Abstracción orientada a objetos: Definimos los atributos del perceptrón.
    private double w1; // Peso para la primera entrada
    private double w2; // Peso para la segunda entrada
    private double b;  // Bias o sesgo
    private double velocidadAprendizaje; // 1. Parámetro de velocidad de aprendizaje añadido.

    public PerceptronOR() {
        // 3. Inicializamos los parámetros desde el código fuente, sin pedir datos por consola.
        this.w1 = 0.0;
        this.w2 = 0.0;
        this.b = 0.0;
        this.velocidadAprendizaje = 0.1; // Un valor común y pequeño para que aprenda poco a poco.
    }

    // Método para entrenar al perceptrón
    public void entrenar(int[][] entradas, int[] salidasEsperadas) {
        boolean hayErrores = true; // Bandera para saber si seguimos iterando

        // Repetimos hasta que no haya errores (hasta que converja)
        while (hayErrores) {
            hayErrores = false; // Asumimos éxito inicial en la época

            for (int i = 0; i < entradas.length; i++) {
                int x1 = entradas[i][0];
                int x2 = entradas[i][1];
                int salidaDeseada = salidasEsperadas[i];

                // 5. Cálculos paso a paso sin usar librerías.
                // Calculamos la Suma Ponderada
                double sumaPonderada = (this.w1 * x1) + (this.w2 * x2) + this.b;

                // Función de Activación tipo escalón
                int salidaCalculada = (sumaPonderada > 0) ? 1 : 0;

                // Calculamos el error
                int error = salidaDeseada - salidaCalculada;

                // Si se equivocó, corregimos los pesos
                if (error != 0) {
                    // 1. AQUI ESTÁ LA DIFERENCIA CLAVE: Incorporamos la velocidad de aprendizaje[cite: 3].
                    // Multiplicamos la corrección por la "velocidadAprendizaje"
                    this.w1 = this.w1 + (this.velocidadAprendizaje * error * x1);
                    this.w2 = this.w2 + (this.velocidadAprendizaje * error * x2);
                    this.b = this.b + (this.velocidadAprendizaje * error);
                    
                    hayErrores = true; // Hubo error, tendremos que volver a pasar por todos los patrones
                }
            }
        }
    }

    // Método para mostrar todo lo que pide el profesor
    public void mostrarResultados(int[][] entradas) {
        // 4. Imprimir en la terminal los resultados[cite: 3].
        System.out.println("Resultados tras el entrenamiento del Perceptron (Compuerta OR):");
        
        // 4a. Valores óptimos de los pesos w_1 y w_2[cite: 3].
        System.out.println("a. Los valores optimos de los pesos son: w_1 = " + String.format("%.1f", this.w1) + " y w_2 = " + String.format("%.1f", this.w2));
        
        // 4b. Valor óptimo del bias (b)[cite: 3].
        System.out.println("b. El valor optimo del bias (b) es: " + String.format("%.1f", this.b));
        
        // 4c. Imprimir los cálculos paso a paso para comprobar que convergió correctamente[cite: 3].
        System.out.println("\nc. Calculos -paso a paso- para cada patron[cite: 3]:");
        for (int i = 0; i < entradas.length; i++) {
            int x1 = entradas[i][0];
            int x2 = entradas[i][1];

            double sumaPonderada = (this.w1 * x1) + (this.w2 * x2) + this.b;
            int activacion = (sumaPonderada > 0) ? 1 : 0;

            System.out.println("   Patron [" + x1 + ", " + x2 + "]:");
            System.out.println("      - Suma Ponderada = (" + String.format("%.1f", this.w1) + " * " + x1 + ") + (" + String.format("%.1f", this.w2) + " * " + x2 + ") + (" + String.format("%.1f", this.b) + ") = " + String.format("%.1f", sumaPonderada));
            System.out.println("      - Funcion de Activacion = (Suma Ponderada > 0) -> " + String.format("%.1f", sumaPonderada) + " > 0 ? " + activacion);
        }
    }

    public static void main(String[] args) {
        // 3. Los patrones de entrenamiento se inicializan desde el código fuente[cite: 3].
        // 2. Entradas para la compuerta lógica OR[cite: 3].
        int[][] entradasOR = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        // Resultados esperados para OR (da 1 si al menos una de las entradas es 1)
        int[] salidasOR = {0, 1, 1, 1};

        // Creamos el perceptrón y lo ponemos a entrenar
        PerceptronOR miPerceptron = new PerceptronOR();
        miPerceptron.entrenar(entradasOR, salidasOR);

        // Mostramos los resultados finales
        miPerceptron.mostrarResultados(entradasOR);
    }
}