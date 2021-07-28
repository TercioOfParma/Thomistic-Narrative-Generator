package com.company;

import java.util.Random;

public class ActualGrace extends Action
{
    Random rand = new Random();
    public void evaluateChoice(Character C)
    {
        int randomChoice = rand.nextInt(100);
        randomChoice = randomChoice + C.getVirtuesAndVices().get("VIRTUE_TEMPERANCE_SUBVIRTUE_HUMILITY");
        randomChoice = randomChoice + C.evaluatePrudence();
        randomChoice = randomChoice + C.evaluatePassions();


        if(randomChoice < 60)
        {
            concomitantAction(C);
        }
        else
        {
            return;
        }
    }
    public void concomitantAction(Character C)
    {

    }
}
