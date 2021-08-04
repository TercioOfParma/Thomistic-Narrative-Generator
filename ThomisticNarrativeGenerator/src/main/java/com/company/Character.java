package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Character
{
    Random rand = new Random();
    String name;
    int age, mortalSinsRemaining, deathAge;
    String story;
    boolean stateOfGrace;
    Action listOfAllActions;
    private HashMap<String,Integer> virtuesAndVices;
    private HashMap<String,Integer> passions;
    private Relationship relationships;
    public Character(Action act, String namen, HashMap<String,Integer> Virtus, HashMap<String,Integer> passion)
    {
        this.stateOfGrace = true; //We'll presuppose they're baptised
        this.story = "";
        this.name = namen;
        this.virtuesAndVices = Virtus;
        this.passions = passion;
        this.age = rand.nextInt(70);
        this.deathAge = rand.nextInt(80) + 40;
        listOfAllActions = act;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
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

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDeathAge() {
        return deathAge;
    }

    public void setDeathAge(int deathAge) {
        this.deathAge = deathAge;
    }

    public int getMortalSinsRemaining() {
        return mortalSinsRemaining;
    }

    public void setMortalSinsRemaining(int mortalSinsRemaining) {
        this.mortalSinsRemaining = mortalSinsRemaining;
    }

    public boolean isStateOfGrace() {
        return stateOfGrace;
    }

    public void setStateOfGrace(boolean stateOfGrace) {
        this.stateOfGrace = stateOfGrace;
    }

    public HashMap<String, Integer> getPassions() {
        return passions;
    }

    public Relationship getRelationships() {
        return relationships;
    }

    public void setRelationships(Relationship relationships) {
        this.relationships = relationships;
    }

    public void generateRelationships(LinkedList<Character> characters)
    {
        HashMap<String, Integer> newPassions = new HashMap<>();
        newPassions.put("ANGER",0);
        newPassions.put("DARING",0);
        newPassions.put("HOPE",0);
        newPassions.put("PLEASURE",0);
        newPassions.put("LOVE",0);

        for(Character c : characters)
        {
            if(c.getName() != this.getName())
            {
                relationships.setSubsequentRelationship(new Relationship(this, c, newPassions));
            }
        }
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
                    System.out.println(listOfAllActions.getPostConditionsAccept().getVirtueEffects());
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

    public HashMap<String, Integer> getpassions() {
        return passions;
    }

    public void setPassions(HashMap<String, Integer> passions) {
        this.passions = passions;
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
        Iterator<String> goThrough = passions.keySet().iterator();
        String currentVirtue = "";
        int total = 0;
        while(goThrough.hasNext()) {
            currentVirtue = goThrough.next();
            if(currentVirtue.contentEquals("ANGER") || currentVirtue.contentEquals("DARING") || currentVirtue.contentEquals("PLEASURE")
                    || currentVirtue.contentEquals("HOPE") || currentVirtue.contentEquals("LOVE"))
            {
                total = total + passions.get(currentVirtue);
            }
        }
        return total;
    }
    public void emotionalDrift()
    {
        Iterator<String> goThrough = passions.keySet().iterator();
        String currentVirtue = "";
        int newValue = 0;
        while(goThrough.hasNext()) {
            newValue = 0;
            currentVirtue = goThrough.next();
            if(currentVirtue.contentEquals("ANGER") || currentVirtue.contentEquals("DARING") || currentVirtue.contentEquals("PLEASURE")
                    || currentVirtue.contentEquals("HOPE") || currentVirtue.contentEquals("LOVE"))
            {
                newValue = newValue + passions.get(currentVirtue) + rand.nextInt(7) - 3;
                passions.replace(currentVirtue,newValue);
            }
        }
    }
}
