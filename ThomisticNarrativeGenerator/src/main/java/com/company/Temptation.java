package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Temptation extends Action {
    Random rand = new Random();
    public Temptation(LinkedList<Action> actionList)
    {
        this.allActions= actionList;
    }
    public void evaluateChoice(Character C, Character C2, Character C3)
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
            doSin(C, C2, C3);
        }
        else
        {
            doResistance(C, C2, C3);
        }

    }

    public void doSin(Character C, Character C2, Character C3)
    {
        Conditions effects = this.getPostConditionsReject();

        doApplicationOfAction(C,C2,C3,false);

        if(PostConditionsReject.getOtherEffects().get("POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS") instanceof String)
        {
            String text = (String) PostConditionsReject.getOtherEffects().get("POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS");
            if(!text.isEmpty())
            {
                System.out.println("Subsequent Action");
                C.addActionToListofAllActions(C.searchActionBank(text));
            }
        }
        else
        {
            System.out.println("Random Action");
            C.addActionToListofAllActions(C.getNextCharacterAction(C));
        }
    }
    public void doResistance(Character C, Character C2, Character C3)
    {

        doApplicationOfAction(C,C2,C3,true);
        if(PostConditionsAccept.getOtherEffects().get("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS") instanceof String)
        {
            String text = (String) PostConditionsAccept.getOtherEffects().get("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS");
            if(!text.isEmpty())
            {
                System.out.println("Subsequent Action");
                C.addActionToListofAllActions(C.searchActionBank(text));
            }
        }
        else
        {
            System.out.println("Random Action");
            C.addActionToListofAllActions(C.getNextCharacterAction(C));
        }
    }
}
