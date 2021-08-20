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
    public void evaluateChoice(Character C, Character C2, Character C3, Verse scr)
    {

        int randomChoice = rand.nextInt(100);
        randomChoice = randomChoice + C.getVirtuesAndVices().get("SUBVIRTUE_HUMILITY");
        randomChoice = randomChoice + C.evaluatePrudence();
        randomChoice = randomChoice + C.evaluatePassions();
        int i = 0;
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
        if(C.stateOfGrace)
        {
        	randomChoice = randomChoice + 15;
        }

        if(randomChoice < 60)
        {
            printARelevantState(preconditions, C, C2, C3);
            doSin(C, C2, C3, scr);
        }
        else
        {
            printARelevantState(preconditions, C, C2, C3);
            doResistance(C, C2, C3, scr);
        }
        C.emotionalDrift();
        C.calmDown();
        C.fallingOutOfTheHabit();

    }

    public void doSin(Character C, Character C2, Character C3, Verse scriptural)
    {
        Conditions effects = this.getPostConditionsReject();
        this.setAccepted(false);

        Iterator<String> toDo = effects.getVirtueEffects().keySet().iterator();
        HashMap<String, Integer> newStatus = C.getVirtuesAndVices();
        //System.err.println("MEME: " + newStatus);
        String key, full;
        while(toDo.hasNext())
        {
            full = toDo.next();

            key = full.replace("POSTCONDITIONS_ACCEPT_","");
            //System.err.println("VIRTUES BEFORE THE LOOPING : " + C.getVirtuesAndVices() + " 2: " + C2.getVirtuesAndVices() + " 3: " + C3.getVirtuesAndVices());
            updateACharacter(C, C2, C3, effects, key, full);
        }


        doApplicationOfAction(C,C2,C3,true, scriptural);

        if(PostConditionsReject.getOtherEffects().get("POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS") instanceof String)
        {
            String text = (String) PostConditionsReject.getOtherEffects().get("POSTCONDITIONS_REJECT_CONSEQUENTIAL_ACTIONS");
            if(!text.isEmpty())
            {
                //System.out.println("Subsequent Action");
                C.addActionToListofAllActions(C.searchActionBank(text));
            }
        }
        else
        {
            //System.out.println("Random Action");
            C.addActionToListofAllActions(C.getNextCharacterAction(C));
        }
    }


    public void doResistance(Character C, Character C2, Character C3, Verse scriptural)
    {

        Conditions effects = this.getPostConditionsAccept();

        this.setAccepted(true);
        Iterator<String> toDo = effects.getVirtueEffects().keySet().iterator();
        HashMap<String, Integer> newStatus = C.getVirtuesAndVices();
        //System.err.println("MEME: " + newStatus);
        String key, full;
        while(toDo.hasNext())
        {
            full = toDo.next();
            key = full.replace("POSTCONDITIONS_ACCEPT_","");
            //System.err.println("VIRTUES BEFORE THE LOOPING : " + C.getVirtuesAndVices() + " 2: " + C2.getVirtuesAndVices() + " 3: " + C3.getVirtuesAndVices());
            updateACharacter(C, C2, C3, effects, key, full);
        }

        doApplicationOfAction(C,C2,C3,true, scriptural);
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
