package org.altervista.growworkinghard.jswmm;

import oms3.annotations.*;

public class Runoff {

    @Description("Structured input data")
    @In
    @Out
    SWMMobject inputData;

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

    @Description("Precipitation input")
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
    private double[] outputValues;


    @Initialize
    private void initialize(){
        if ( inputData != null ) {
            //Manage SWMMobject to fill fields of the class
            //minimumStepSize =
            //maximumStepSize =
            //absoluteTolerance =
            //relativeTolerance =
            //precipitation =
            //depthFactor =
            //initialTime =
            //finalTime =
            //outputStepSize =
        }
    }

    AbstractRunoffSolver odeSolver = new DormandPrince54(precipitation, depthFactor,
            minimumStepSize, maximumStepSize, absoluteTolerance, relativeTolerance);

    @Execute
    public void run(){
        //infiltration method(i, e)
        //groundwater method(i)
        odeSolver.integrate(initialTime, outputValues, finalTime, outputValues);
    }

    @Finalize
    private void updateSWMMobject(){
        if ( inputData != null ) {
            //Manage SWMMobject to fill fields of the class
            //minimumStepSize =
            //maximumStepSize =
            //absoluteTolerance =
            //relativeTolerance =
            //precipitation =
            //depthFactor =
            //initialTime =
            //finalTime =
            //outputStepSize =
        }
    }

    /*
    outputValues = initialValue;

    double relativeInitialTime = initialTime;
    double[] inputOutputValue = initialValue;
            if(outputStepSize == 0.0)

    {
        outputStepSize = evaluateDeltaTime(finalTime - initialTime);
        ODEsolver = new AbstractRunoffSolver();
    }

    outputStepSize = adaptStepSize(finalTime-initialTime);

    int i = 1;
            while(relativeInitialTime < finalTime){
        dp54.integrate(ode, relativeInitialTime, inputOutputValue,
                relativeInitialTime+outputStepSize, inputOutputValue);
        outputValues[i] = inputOutputValue[0];
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
    }*/

}
