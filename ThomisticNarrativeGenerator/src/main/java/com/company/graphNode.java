package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class graphNode
{
    private HashMap<String,Double> values = new HashMap<>();
    private final int MAXIMUM = 100000;
    private final int MINIMUM = -100000;
    private final int NORMALISED_MAX = 10;
    private final int NORMALISED_MIN = 0;
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
        if(Double.compare(normalise, Double.POSITIVE_INFINITY) == 0)
        {
            normalise = 1;
        }
        else if(Double.compare(normalise, Double.NEGATIVE_INFINITY) == 0)
        {
            normalise = -1;
        }
        else if(Double.compare(normalise, Double.NaN) == 0)
        {
            normalise = NORMALISED_MIN;
        }

        return normalise;
    }

    public double euclideanDistanceToPoint(graphNode ideal, graphNode toCompare)
    {
        double distance = 0;
        String name;
        Iterator<String> dimensionIterate = toCompare.getValues().keySet().iterator();
        double total = 0;
        while(dimensionIterate.hasNext())
        {
            name = dimensionIterate.next();
            distance = (ideal.getValues().get(name) - toCompare.getValues().get(name));
            distance = distance * distance;
            total = total + distance;
        }
        distance = Math.sqrt(total);
        return distance;
    }
    @Override
    public String toString() {
        return "graphNode{" +
                "values=" + values +
                '}';
    }

    public double scoreList(LinkedList<graphNode> idealList, LinkedList<graphNode> listToJudge)
    {
        double score = 0;
        Iterator<graphNode> idealIterate = idealList.iterator();
        Iterator<graphNode> listJudgeIterate = listToJudge.iterator();
        graphNode ideal = null;
        graphNode toCompare = null;
        while(idealIterate.hasNext() && listJudgeIterate.hasNext())
        {
            ideal = idealIterate.next();
            toCompare = listJudgeIterate.next();
            score = score + euclideanDistanceToPoint(ideal, toCompare);
        }

        System.err.println("Score : " + score + " Ideal : " + ideal + " toCompare" + toCompare);
        return score;
    }
    // This is gonna be some UGLY code
    public LinkedList<graphNode> generateIdealPoints()
    {
        LinkedList<graphNode> ideal = new LinkedList<>();
        HashMap<String, Double> idealValues = new HashMap<>();
        //0
        idealValues.put("FAITH", 0.1);
        idealValues.put("HOPE", 0.1);
        idealValues.put("CHARITY", 0.1);
        idealValues.put("PRUDENCE", 2.0);
        idealValues.put("JUSTICE", 2.0);
        idealValues.put("TEMPERANCE", 2.0);
        idealValues.put("FORTITUDE", 2.0);
        idealValues.put("PASSIONS", 10.0);
        ideal.add(new graphNode(idealValues));

        //30
        idealValues.put("FAITH", 0.5);
        idealValues.put("HOPE", 0.5);
        idealValues.put("CHARITY", 0.5);
        idealValues.put("PRUDENCE", 5.0);
        idealValues.put("JUSTICE", 5.0);
        idealValues.put("TEMPERANCE", 5.0);
        idealValues.put("FORTITUDE", 5.0);
        idealValues.put("PASSIONS", 20.0);
        ideal.add(new graphNode(idealValues));

        //60
        idealValues.replace("FAITH", 1.0);
        idealValues.replace("HOPE", 1.0);
        idealValues.replace("CHARITY", 1.0);
        idealValues.replace("PRUDENCE", 8.0);
        idealValues.replace("JUSTICE", 8.0);
        idealValues.replace("TEMPERANCE", 8.0);
        idealValues.replace("FORTITUDE", 8.0);
        idealValues.replace("PASSIONS", 50.0);
        ideal.add(new graphNode(idealValues));

        //90
        idealValues.replace("FAITH", 1.5);
        idealValues.replace("HOPE", 1.5);
        idealValues.replace("CHARITY", 1.5);
        idealValues.replace("PRUDENCE", 11.0);
        idealValues.replace("JUSTICE", 11.0);
        idealValues.replace("TEMPERANCE", 11.0);
        idealValues.replace("FORTITUDE", 11.0);
        idealValues.replace("PASSIONS", 20.0);
        ideal.add(new graphNode(idealValues));

        //120
        idealValues.replace("FAITH", 1.2);
        idealValues.replace("HOPE", 1.2);
        idealValues.replace("CHARITY", 1.2);
        idealValues.replace("PRUDENCE", 13.0);
        idealValues.replace("JUSTICE", 13.0);
        idealValues.replace("TEMPERANCE", 13.0);
        idealValues.replace("FORTITUDE", 13.0);
        idealValues.replace("PASSIONS", 25.0);
        ideal.add(new graphNode(idealValues));

        //120
        idealValues.replace("FAITH", 1.5);
        idealValues.replace("HOPE", 1.5);
        idealValues.replace("CHARITY", 1.5);
        idealValues.replace("PRUDENCE", 14.0);
        idealValues.replace("JUSTICE", 14.0);
        idealValues.replace("TEMPERANCE", 14.0);
        idealValues.replace("FORTITUDE", 14.0);
        idealValues.replace("PASSIONS", 25.0);
        ideal.add(new graphNode(idealValues));

        //150
        idealValues.replace("FAITH", 2.0);
        idealValues.replace("HOPE", 2.0);
        idealValues.replace("CHARITY", 2.0);
        idealValues.replace("PRUDENCE", 16.0);
        idealValues.replace("JUSTICE", 16.0);
        idealValues.replace("TEMPERANCE", 16.0);
        idealValues.replace("FORTITUDE", 16.0);
        idealValues.replace("PASSIONS", 25.0);
        ideal.add(new graphNode(idealValues));

        //180
        idealValues.replace("FAITH", 3.0);
        idealValues.replace("HOPE", 3.0);
        idealValues.replace("CHARITY", 3.0);
        idealValues.replace("PRUDENCE", 18.0);
        idealValues.replace("JUSTICE", 17.0);
        idealValues.replace("TEMPERANCE", 19.0);
        idealValues.replace("FORTITUDE", 18.0);
        idealValues.replace("PASSIONS", 50.0);
        ideal.add(new graphNode(idealValues));

        //210
        idealValues.replace("FAITH", 4.0);
        idealValues.replace("HOPE", 4.0);
        idealValues.replace("CHARITY", 4.0);
        idealValues.replace("PRUDENCE", 20.0);
        idealValues.replace("JUSTICE", 20.0);
        idealValues.replace("TEMPERANCE", 20.0);
        idealValues.replace("FORTITUDE", 20.0);
        idealValues.replace("PASSIONS", 70.0);
        ideal.add(new graphNode(idealValues));

        //240
        idealValues.replace("FAITH", 4.5);
        idealValues.replace("HOPE", 4.5);
        idealValues.replace("CHARITY", 4.5);
        idealValues.replace("PRUDENCE", 23.0);
        idealValues.replace("JUSTICE", 23.0);
        idealValues.replace("TEMPERANCE", 23.0);
        idealValues.replace("FORTITUDE", 23.0);
        idealValues.replace("PASSIONS", 85.0);
        ideal.add(new graphNode(idealValues));

        //270
        idealValues.replace("FAITH", 5.0);
        idealValues.replace("HOPE", 5.0);
        idealValues.replace("CHARITY", 5.0);
        idealValues.replace("PRUDENCE", 24.0);
        idealValues.replace("JUSTICE", 24.0);
        idealValues.replace("TEMPERANCE", 24.0);
        idealValues.replace("FORTITUDE", 24.0);
        idealValues.replace("PASSIONS", 70.0);
        ideal.add(new graphNode(idealValues));

        //300
        idealValues.replace("FAITH", 5.5);
        idealValues.replace("HOPE", 5.5);
        idealValues.replace("CHARITY", 5.5);
        idealValues.replace("PRUDENCE", 25.0);
        idealValues.replace("JUSTICE", 25.0);
        idealValues.replace("TEMPERANCE", 25.0);
        idealValues.replace("FORTITUDE", 25.0);
        idealValues.replace("PASSIONS", 40.0);
        ideal.add(new graphNode(idealValues));


        ideal = normalise(ideal);
        return ideal;
    }

}
