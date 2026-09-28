public class PerceptronAND {
    private double w1;
    private double w2;
    private double bias;

    public PerceptronAND() {
        this.w1 = 0.0;
        this.w2 = 0.0;
        this.bias = 0.0;
    }

    public int activar(double sumaPonderada) {
        if (sumaPonderada >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void entrenar() {
        double[][] entradas = {
            {0.0, 0.0},
            {0.0, 1.0},
            {1.0, 0.0},
            {1.0, 1.0}
        };
        int[] salidasEsperadas = {0, 0, 0, 1};

        boolean convergio = false;
        int epoca = 0;

        while (!convergio) {
            convergio = true;
            epoca++;
            System.out.println("=== ÉPOCA " + epoca + " ===");

            for (int i = 0; i < entradas.length; i++) {
                double x1 = entradas[i][0];
                double x2 = entradas[i][1];
                int esperado = salidasEsperadas[i];

                double sumaPonderada = (this.w1 * x1) + (this.w2 * x2) + this.bias;
                int prediccion = activar(sumaPonderada);
                int error = esperado - prediccion;

                System.out.println(String.format("Patrón: [%.0f, %.0f] | Esperado: %d | Predicción: %d | Suma Ponderada: %.2f | Error: %d", 
                    x1, x2, esperado, prediccion, sumaPonderada, error));

                if (error != 0) {
                    convergio = false;
                    this.w1 += error * x1;
                    this.w2 += error * x2;
                    this.bias += error;
                }
            }
            System.out.println(String.format("Pesos actualizados -> w1: %.2f, w2: %.2f, bias: %.2f\n", this.w1, this.w2, this.bias));
        }

        System.out.println("¡El perceptrón AND convergió con éxito!");
        System.out.println("Valores óptimos obtenidos:");
        System.out.println("w1 = " + this.w1);
        System.out.println("w2 = " + this.w2);
        System.out.println("bias (b) = " + this.bias);
    }

    public static void main(String[] args) {
        PerceptronAND perceptron = new PerceptronAND();
        perceptron.entrenar();
    }
}