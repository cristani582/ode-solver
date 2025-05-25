import org.apache.commons.math.ode.*;

public class Main {
    public static void main(String[] args) throws IntegratorException, DerivativeException {
        ODESystem odeSystem = new ODESystem();
        ODESolver odeSolver = new ODESolver(odeSystem, 0.01);
        ODEPlotter odePlotter = new ODEPlotter(odeSolver.getTimePoints(), odeSolver.getYPoints());
        odeSolver.solve(0.0, 10.0, new double[]{0.0, 1.0});

        odePlotter.createChart();
    }
}

