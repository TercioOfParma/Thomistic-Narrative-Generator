package com.company;

import java.util.Random;

public class Temptation extends Action {
    Random rand = new Random();
    public void evaluateChoice(Character C)
    {
        int randomChoice = rand.nextInt(100);
        randomChoice = randomChoice + C.getVirtuesAndVices().get("VIRTUE_TEMPERANCE_SUBVIRTUE_HUMILITY");
        randomChoice = randomChoice + C.evaluatePrudence();
        randomChoice = randomChoice + C.evaluatePassions();


        if(randomChoice < 60)
        {
            doSin(C);
        }
        else
        {
            doResistence(C);
        }

    }
    public void doSin(Character C)
    {

    }
    public void doResistence(Character C)
    {

    }
}
