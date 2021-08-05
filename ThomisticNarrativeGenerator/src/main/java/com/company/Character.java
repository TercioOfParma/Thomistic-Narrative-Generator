package com.company;

import java.util.*;

public class Character
{
    Random rand = new Random();
    String name;
    int age, mortalSinsRemaining, deathAge;
    String story;
    boolean stateOfGrace;
    LinkedList<Action> listOfAllActions, actionBank;
    private HashMap<String,Integer> virtuesAndVices;
    private HashMap<String,Integer> passions;
    private Relationship relationships;
    public Character(LinkedList<Action> act,  String namen, HashMap<String,Integer> Virtus, HashMap<String,Integer> passion)
    {
        this.stateOfGrace = true; //We'll presuppose they're baptised
        this.story = "";
        this.name = namen;
        this.virtuesAndVices = Virtus;
        this.passions = passion;
        this.age = rand.nextInt(70);
        this.deathAge = rand.nextInt(80) + 40;
        actionBank = (LinkedList<Action>) act.clone();
        //Action test = act.copyAllContents(act);
        /*while(test.getSubsequentAction() != null)
        {
            System.out.println("Does copy all contents work : " + act.copyAllContents(act));
            test = test.getSubsequentAction();
        }*/
        listOfAllActions = new LinkedList<>();
        listOfAllActions.add(actionBank.get(rand.nextInt(actionBank.size())));
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
    public int getLengthOfActions()
    {
        Iterator<Action> iterate = listOfAllActions.iterator();
        Action toCheck;
        int i = 0;
        while(iterate.hasNext())
        {
            toCheck = iterate.next();
            i = i + 1;
        }

        return i;
    }

    public LinkedList<Action> getActionBank() {
        return actionBank;
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
    public Action searchActionBank(String id)
    {
        Iterator<Action> iterate = actionBank.iterator();
        Action act;
        while(iterate.hasNext())
        {
            act = iterate.next();
            if(act.getId().contentEquals(id))
            {
                return act;
            }

        }

        return null;
    }
    public Action getNextCharacterAction(Character C)
    {
        Collections.shuffle(actionBank);
        Action nextAction = null;
        Iterator<Action> allAct = actionBank.iterator();
        boolean isFound = false;
        HashMap<String,Integer> virtues, precons;
        String temp;
        int skip = 0;
        while(allAct.hasNext() && !isFound)
        {
            nextAction = allAct.next(); //We do want to skip the template
            virtues = C.getVirtuesAndVices();
            isFound = true;
            precons = nextAction.getPreConditions().getVirtueEffects();
            try
            {
                System.out.println("Finding Subsequent Action " + nextAction.getId());
            }
            catch(Exception e)
            {

            }
            for (String key : virtues.keySet()) {
                temp = key + "_ABOVE";
                try {
                    if (!(precons.get(temp) <= virtues.get(key))) {
                        isFound = false;
                        break;
                    }
                    temp = key + "_BELOW";
                    if (!(precons.get(temp) >= virtues.get(key))) {
                        isFound = false;
                        break;
                    }
                    if(nextAction.getId().contentEquals("TEMPLATE"))
                    {
                        isFound = false;
                        break;
                    }
                }
                catch (Exception e) {

                }

            }
            skip = rand.nextInt(3);
            if(skip <= 1)
            {
                isFound = false;
            }
            if(isFound)
            {
                break;
            }
        }
        if(!isFound)
        {
            System.out.println("Finding Random Action");
            nextAction = getRandomAction();
        }
        C.setAge(C.getAge() + 1);
        return nextAction;
    }
    public Action getRandomAction()
    {
        Collections.shuffle(actionBank);
        int randomAction;
        randomAction = rand.nextInt(getListOfAllActions().size()) + 2;
        Iterator<Action> act = actionBank.iterator();
        Action nextAction = null;
        for(int j = 0; j < randomAction; j++)
        {
            nextAction = act.next();
        }

        System.out.println("Random Action: " + nextAction.getId());
        return nextAction;
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

    public LinkedList<Action> getListOfAllActions() {
        return listOfAllActions;
    }

    public void setListOfAllActions(LinkedList<Action> listOfAllActions) {
        this.listOfAllActions = listOfAllActions;
    }

    public void setActionBank(LinkedList<Action> actionBank) {
        this.actionBank = actionBank;
    }

    public void addActionToActionBank(Action act)
    {
        actionBank.add(act);
    }
    public void addActionToListofAllActions(Action act)
    {
        listOfAllActions.add(act);
    }
    public void printStory(BibleLoader bible, NameGenerator generator)
    {
        System.out.println("Test");
        Iterator<Action> listOfAll = listOfAllActions.iterator();
        Action tempAct;
        while(listOfAll.hasNext())
        {
            tempAct = listOfAll.next();
            String toPrint = "";
            try {
                Object output = tempAct.getPostConditionsAccept().getOtherEffects().get("POSTCONDITIONS_ACCEPT_OUTPUT");
                if(toPrint instanceof String) {
                    toPrint = (String)output;
                    System.out.println(toPrint.replace("<1>", generator.getRandomName()).replace("<2>", generator.getRandomName()).replace("<3>", generator.getRandomName()));
                    System.out.println(tempAct.getPostConditionsAccept().getVirtueEffects());
                }
            }
            catch(Exception e)
            {
                System.err.println(e);
                System.exit(-1);
            }
            if(tempAct.getScriptures() != null)
            {
                System.out.println(bible.getVerse(tempAct.getScriptures().get(0)));
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
