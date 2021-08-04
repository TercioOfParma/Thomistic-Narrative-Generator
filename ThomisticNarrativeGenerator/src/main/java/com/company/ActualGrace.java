package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class ActualGrace extends Action
{
    Random rand = new Random();
    public void evaluateChoice(Character C, Character C2, Character C3, Action subsequent)
    {
        int randomChoice = rand.nextInt(100);
        randomChoice = randomChoice + C.getVirtuesAndVices().get("SUBVIRTUE_HUMILITY");
        randomChoice = randomChoice + C.evaluatePrudence();
        randomChoice = randomChoice + C.evaluatePassions();

        HashMap<String, Integer> preconEffects = this.PreConditions.getVirtueEffects();
        for(int current : preconEffects.values())
        {
            randomChoice = randomChoice + (current * 4);
        }


        if(randomChoice < 60)
        {
            concomitantAction(C, C2, C3, subsequent);
        }
        else
        {
            return;
        }
    }
    public void concomitantAction(Character C, Character C2, Character C3, Action subsequentAction)
    {
        doApplicationOfAction(C,C2,C3,subsequentAction,true);
        if(PostConditionsAccept.getOtherEffects().get("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS") instanceof String)
        {
            String text = (String) PostConditionsAccept.getOtherEffects().get("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS");
            if(!text.isEmpty())
            {
                C.addAction(subsequentAction.searchList(text));
            }
        }
        else
        {
            C.addAction(subsequentAction.getNextCharacterAction(C));
        }

    }
}
