package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class graphNode
{
    private HashMap<String,Double> values = new HashMap<>();
    private final int MAXIMUM = 100000;
    private final int MINIMUM = -100000;
    private final int NORMALISED_MAX = 10;
    private final int NORMALISED_MIN = -10;
    /*

    Normalisation function = x - min / max - min
     */
    //Make it clear I'm making something very uncomputational computational
    public graphNode(HashMap<String, Double> values)
    {
        this.values = (HashMap<String, Double>) values.clone();
    }

    public LinkedList<graphNode> normalise(LinkedList<graphNode> nodes)
    {
        String [] dimensions = {"FAITH", "HOPE", "CHARITY", "PRUDENCE", "JUSTICE", "TEMPERANCE", "FORTITUDE", "PASSIONS"};
        double maximum, minimum, toTest;
        LinkedList<graphNode> nodes2 = new LinkedList<>();
        for(String toResolve : dimensions)
        {
            System.err.println(toResolve);
            maximum = MINIMUM;
            minimum = MAXIMUM;
            for(graphNode toGrab : nodes)
            {
                toTest = toGrab.getValues().get(toResolve);
                if(Double.compare(toTest,maximum) > 0)
                {
                    maximum = toTest;
                }
                if(Double.compare(toTest,minimum) < 0)
                {
                    minimum = toTest;
                }
            }
            if(Double.compare(MAXIMUM,minimum) == 0)
            {
                minimum = 0;
            }
            if(Double.compare(MINIMUM,maximum) == 0)
            {
                maximum = 0;
            }
            for(graphNode toNormalise : nodes)
            {
                toNormalise.getValues().replace(toResolve, normaliseValue(toNormalise.getValues().get(toResolve), minimum, maximum) );
            }
        }
        nodes2.addAll(nodes);
        return nodes2;

    }

    public HashMap<String, Double> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Double> values) {
        this.values = values;
    }

    public double normaliseValue(double toNormalise, double min, double max)
    {
        double normalise =  (NORMALISED_MAX - NORMALISED_MIN) * ((toNormalise - min) / (max - min)) + NORMALISED_MIN;
        System.err.println(normalise + " To normalise : " + toNormalise + " Minimum: " + min + " Maximum: " + max);
        if(normalise == Double.POSITIVE_INFINITY)
        {
            normalise = 1;
        }
        else if (normalise == Double.NEGATIVE_INFINITY)
        {
            normalise = -1;
        }

        return normalise;
    }

    @Override
    public String toString() {
        return "graphNode{" +
                "values=" + values +
                '}';
    }
}
