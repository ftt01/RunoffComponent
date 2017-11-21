package org.altervista.growworkinghard.rungeKutta;

import oms3.annotations.*;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince54Integrator;

@Description("ODE solver for Manning equation over subcatchments")
@Author(name = "ftt01", contact = "dallatorre.daniele@gmail.com")
@Status(Status.DRAFT)
@License("GPL3.0")

public class RunoffEquationSolver {

    @Description("Minimum step for evaluation of the ODE")
    @In
    private double minimumStepSize = 1.0e-8;

    @Description("Maximum step for evaluation of the ODE")
    @In
    private double maximumStepSize = 1.0e+3;

    @Description("Absolute tolerance for evaluation of the ODE")
    @In
    private double absoluteTolerance = 1.0e-10;

    @Description("Relative tolerance for evaluation of the ODE")
    @In
    private double relativeTolerance = 1.0e-10;

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

    public RunoffEquationSolver(double precipitation, double depthFactor) {
        this.precipitation = precipitation;
        this.ode = new RunoffODE(precipitation,depthFactor);
        this.dp54 = new DormandPrince54Integrator(minimumStepSize, maximumStepSize,
                absoluteTolerance, relativeTolerance);
    }

    @Execute
    private double[] integrate(){
            outputValues = initialValue;

            double relativeInitialTime = initialTime;
            double[] inputOutputValue = initialValue;
            if(outputStepSize == 0.0){
                outputStepSize = evaluateDeltaTime(finalTime-initialTime);
            }

            outputStepSize = adaptStepSize(finalTime-initialTime);

            int i = 1;
            while(relativeInitialTime < finalTime){
                dp54.integrate(ode, relativeInitialTime, inputOutputValue,
                        relativeInitialTime+outputStepSize, inputOutputValue);
                outputValues[i] = inputOutputValue[0];
            }

            return outputValues;
    }

    double adaptStepSize(double totalTime) {
        double tmpStepNumber = totalTime/outputStepSize;

        return totalTime/Math.floor(tmpStepNumber);
    }

    double evaluateDeltaTime(double totalTime) {
        if(totalTime>=24*60*60){
            return 60.0*60.0;
        }
        else{
            return 60.0;
        }
    }

}