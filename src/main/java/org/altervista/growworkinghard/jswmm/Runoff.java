package org.altervista.growworkinghard.jswmm;

import oms3.annotations.Description;
import oms3.annotations.In;
import org.altervista.growworkinghard.rungeKutta.RunoffEquationSolver;


abstract class AbstractInfiltration {

}

abstract class AbstractGroundwater {

}


public class Runoff {

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

    Runoff(SWMMobject inputData){

    }

    private

}
