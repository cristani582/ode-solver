import org.apache.commons.math.ode.FirstOrderDifferentialEquations;

class ODESystem implements FirstOrderDifferentialEquations {
    @Override
    public int getDimension() {
        return 2; // Exemplo com duas EDOs
    }

    @Override
    public void computeDerivatives(double t, double[] y, double[] yDot) {
        // Defina as equações aqui. Exemplo simples:

        yDot[0] = y[1];        // dx/dt = y(t)
        yDot[1] = -y[0];       // dy/dt = -x(t)

    }
}
