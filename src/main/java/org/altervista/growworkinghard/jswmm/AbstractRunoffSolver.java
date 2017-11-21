package org.altervista.growworkinghard.jswmm;

abstract class AbstractRunoffSolver {
    abstract double[] integrate(double initialTime, double[] inputValues,
                                double finalTime, double[] outputValues);
}