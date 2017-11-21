package org.altervista.growworkinghard.jswmm;

import oms3.annotations.*;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator;

@Description("ODE solver for Manning equation over subcatchments")
@Author(name = "ftt01", contact = "dallatorre.daniele@gmail.com")
@Status(Status.DRAFT)
@License("GPL3.0")

public class DormandPrince54 extends AbstractRunoffSolver{

    @Description("Precipitation data")
    @In
    private double precipitation;

    @Description("Constant depth factor")
    @In
    private double depthFactor;

    @Description("Initial time")
    @In
    private double initialTime;

    @Description("Final time")
    @In
    private double finalTime;

    @Description("Initial value")
    @In
    private double[] initialValue = { 0.0 };

    @Description("Output step size")
    @In
    private double outputStepSize = 0.0;

    @Description("Output values")
    @Out
    private double[] outputValues;


    private FirstOrderIntegrator dp54;

    private FirstOrderDifferentialEquations ode;

    protected DormandPrince54(double precipitation, double depthFactor,
                              double minimumStepSize, double maximumStepSize,
                              double absoluteTolerance, double relativeTolerance) {

        this.precipitation = precipitation;
        this.ode = new RunoffODE(precipitation,depthFactor);
        this.dp54 = new DormandPrince54Integrator(minimumStepSize, maximumStepSize,
                absoluteTolerance, relativeTolerance);
    }

    double[] integrate(double initialTime, double[] inputValues,
                       double finalTime, double[] outputValues){
        dp54.integrate(ode, initialTime, inputValues, finalTime, outputValues);
        return outputValues;
    }
}