package org.altervista.growworkinghard.rungeKutta;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator;


public class RunoffEquationSolver {
    public static void main(String[] args) {
        FirstOrderIntegrator dp54 = new DormandPrince54Integrator(1.0e-8, 100.0,
                1.0e-10, 1.0e-10);
        FirstOrderDifferentialEquations ode = new RunoffODE(100, 0.1);
        double[] y = new double[] { 0.0 }; // initial state

        dp54.integrate(ode, 0.0, y, 16.0, y); // now y contains final state at time t=16.0

        System.out.println(y[0]);
    }
}