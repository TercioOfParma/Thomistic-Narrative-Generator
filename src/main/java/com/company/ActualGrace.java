package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class ActualGrace extends Action
{
    public ActualGrace(LinkedList<Action> actionList)
    {
        this.allActions= actionList;
    }
    Random rand = new Random();
    public void evaluateChoice(Character C, Character C2, Character C3)
    {
        int i = 0;
        int randomChoice = rand.nextInt(100);
        randomChoice = randomChoice + C.getVirtuesAndVices().get("SUBVIRTUE_HUMILITY");
        randomChoice = randomChoice + C.evaluatePrudence();
        randomChoice = randomChoice + C.evaluatePassions();
        LinkedList<String> preconditions = new LinkedList<>();
        LinkedList<String> allPrecons = new LinkedList<>();
        HashMap<String, Integer> preconEffects = this.PreConditions.getVirtueEffects();
        for(String keys : preconEffects.keySet())
        {
            allPrecons.add(keys);
        }
        for(int current : preconEffects.values())
        {
            if(current != 0)
            {
                preconditions.add(allPrecons.get(i));
            }
            randomChoice = randomChoice + (current * 4);
            i++;
        }


        if(randomChoice < 60)
        {
            printARelevantState(preconditions, C, C2, C3);
            concomitantAction(C, C2, C3);
        }
        else
        {
            nothingHappens(C);
        }
        C.emotionalDrift();
    }
    public void concomitantAction(Character C, Character C2, Character C3)
    {
        Conditions effects = this.getPostConditionsAccept();


        Iterator<String> toDo = effects.getVirtueEffects().keySet().iterator();
        HashMap<String, Integer> newStatus = C.getVirtuesAndVices();
        String key, full;
        while(toDo.hasNext())
        {
            full = toDo.next();
            key = full.replace("POSTCONDITIONS_ACCEPT_","");
            updateACharacter(C, C2, C3, effects, key, full);
        }

        doApplicationOfAction(C,C2,C3,true);
        if(PostConditionsAccept.getOtherEffects().get("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS") instanceof String)
        {
            String text = (String) PostConditionsAccept.getOtherEffects().get("POSTCONDITIONS_ACCEPT_CONSEQUENTIAL_ACTIONS");;
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
    public void nothingHappens(Character C)
    {
        C.addActionToListofAllActions(C.getNextCharacterAction(C));
    }
}
