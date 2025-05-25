import org.apache.commons.math.ode.FirstOrderDifferentialEquations;

class ODESystemOther implements FirstOrderDifferentialEquations {

    @Override
    public int getDimension() {
        return 2; // Exemplo com duas EDOs
    }

    @Override
    public void computeDerivatives(double t, double[] y, double[] yDot) {
        // Defina as equações aqui. Exemplo simples:

        yDot[0] = y[0] + 2*y[1]; // dx/dt = x + 2y
        yDot[1] = 3*y[0] + 2*y[1]; // dy/dt = 3x + 2y

    }
}
