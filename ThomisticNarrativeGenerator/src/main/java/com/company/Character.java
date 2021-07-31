package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Character
{
    Random rand = new Random();
    Action listOfAllActions;
    private HashMap<String,Integer> virtuesAndVices;
    private HashMap<String,Integer> passionsAndRelationships;
    public Character(Action act)
    {
        listOfAllActions = act;
    }

    public int getLengthOfActions()
    {
        int i = 0;
        Action currentAction = listOfAllActions;
        while(currentAction != null)
        {
            currentAction = currentAction.getSubsequentAction();
            i++;
        }

        return i;
    }

    public Action getListOfAllActions()
    {
        return listOfAllActions;
    }

    public void setListOfAllActions(Action listOfAllActions) {
        this.listOfAllActions = listOfAllActions;
    }
    public void addAction(Action act)
    {
        listOfAllActions.setSubsequentAction(act);
        listOfAllActions.getSubsequentAction().setPreviousAction(listOfAllActions);
        listOfAllActions = act;
    }
    public void printStory(BibleLoader bible, NameGenerator generator)
    {
        System.out.println("Test");
        while(listOfAllActions.getPreviousAction() != null)
        {
            System.out.println("PreviousActions!");
            listOfAllActions = listOfAllActions.getPreviousAction();
        }
        while(listOfAllActions.getSubsequentAction() != null)
        {
            String toPrint = "";
            try {
                Object output = listOfAllActions.getPostConditionsAccept().getOtherEffects().get("POSTCONDITIONS_ACCEPT_OUTPUT");
                if(toPrint instanceof String) {
                    toPrint = (String)output;
                    System.out.println(toPrint.replace("<1>", generator.getRandomName()).replace("<2>", generator.getRandomName()).replace("<3>", generator.getRandomName()));
                }
                    listOfAllActions = listOfAllActions.getSubsequentAction();
            }
            catch(Exception e)
            {
                System.err.println(e);
                System.exit(-1);
            }
            if(listOfAllActions.getScriptures() != null)
            {
                System.out.println(bible.getVerse(listOfAllActions.getScriptures().get(0)));
            }
        }
    }

    public HashMap<String, Integer> getVirtuesAndVices() {
        return virtuesAndVices;
    }

    public void setVirtuesAndVices(HashMap<String, Integer> virtuesAndVices) {
        this.virtuesAndVices = virtuesAndVices;
    }

    public HashMap<String, Integer> getPassionsAndRelationships() {
        return passionsAndRelationships;
    }

    public void setPassionsAndRelationships(HashMap<String, Integer> passionsAndRelationships) {
        this.passionsAndRelationships = passionsAndRelationships;
    }

    public int evaluatePrudence()
    {
        Iterator<String> goThrough = virtuesAndVices.keySet().iterator();
        String currentVirtue = "";
        int total = 0;
        while(goThrough.hasNext())
        {
            currentVirtue = goThrough.next();
            if(currentVirtue.contains("PRUDENCE"))
            {
                if(currentVirtue.contains("SUBVIRTUE"))
                {
                    total = total + virtuesAndVices.get(currentVirtue);
                }
                else if(currentVirtue.contains("SUBVICE"))
                {
                    total = total - virtuesAndVices.get(currentVirtue);
                }
            }
        }
        return total;
    }
    public int evaluatePassions()
    {
        Iterator<String> goThrough = passionsAndRelationships.keySet().iterator();
        String currentVirtue = "";
        int total = 0;
        while(goThrough.hasNext()) {
            currentVirtue = goThrough.next();
            if(currentVirtue.contentEquals("ANGER") || currentVirtue.contentEquals("DARING") || currentVirtue.contentEquals("PLEASURE")
                    || currentVirtue.contentEquals("HOPE") || currentVirtue.contentEquals("LOVE"))
            {
                total = total + passionsAndRelationships.get(currentVirtue);
            }
        }
        return total;
    }
    public void emotionalDrift()
    {
        Iterator<String> goThrough = passionsAndRelationships.keySet().iterator();
        String currentVirtue = "";
        int newValue = 0;
        while(goThrough.hasNext()) {
            newValue = 0;
            currentVirtue = goThrough.next();
            if(currentVirtue.contentEquals("ANGER") || currentVirtue.contentEquals("DARING") || currentVirtue.contentEquals("PLEASURE")
                    || currentVirtue.contentEquals("HOPE") || currentVirtue.contentEquals("LOVE"))
            {
                newValue = newValue + passionsAndRelationships.get(currentVirtue) + rand.nextInt(7) - 3;
                passionsAndRelationships.replace(currentVirtue,newValue);
            }
        }
    }
}
