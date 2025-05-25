import org.apache.commons.math.ode.*;

import java.util.ArrayList;
import java.util.List;

class ODESolver {
    private final FirstOrderDifferentialEquations odeSystem;
    private final double stepSize;
    private final List<Double> timePoints;
    private final List<double[]> yPoints;


    public ODESolver(FirstOrderDifferentialEquations odeSystem, double stepSize) {
    /*
        It takes as arguments an ODE system and a double called stepSize,
        which determines how the total time is segmented for the numerical solution.
    */
        this.odeSystem = odeSystem;
        this.stepSize = stepSize;
        this.timePoints = new ArrayList<>();
        this.yPoints = new ArrayList<>();
    }

    public void solve(double t0, double t1, double[] y0) throws IntegratorException, DerivativeException {
        /*
            Solve method takes as argument an initial time t0,
            final time t1 and
            a double's array {x(0),y(0),...},
            which will contain the initial conditions of our ode system.
        */
        FirstOrderIntegrator integrator = new DormandPrince54Integrator(stepSize, stepSize, 1.0e-8, 1.0e-8);
        integrator.setStepHandler(new StepHandler() { // creating a step handler to numerically integrate properly
            @Override
            public boolean requiresDenseOutput() { // not used
                return false;
            }
            @Override
            public void reset() { // not used
            }

            public void handleStep(StepInterpolator interpolator, boolean isLast) {
                double t = interpolator.getCurrentTime();
                double[] y = interpolator.getInterpolatedState();
                timePoints.add(t);
                yPoints.add(y.clone()); // adding a vector's copy to make sure a change will not affect the hole system

                //print plot axis in terminal it's not really needed, but uncomment if wanted.
                //System.out.println("t = " + t + ", y = [" + y[0] + ", " + y[1] + "]");
            }
        });
        integrator.integrate(odeSystem, t0, y0, t1, y0);
    }

    public List<Double> getTimePoints() {
        return timePoints;
    }

    public List<double[]> getYPoints() {
        return yPoints;
    }
}
