package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Character implements Comparable<Character>
{
    Random rand = new Random();
    String name;
    private int age, mortalSinsRemaining, deathAge, nodeInterval;
    private int currentActions = 0;
    String story;
    boolean stateOfGrace;
    LinkedList<Action> listOfAllActions, actionBank, stateBank;
    LinkedList<graphNode> crucialPoints;
    private HashMap<String,Integer> virtuesAndVices;
    private HashMap<String,Integer> passions;
    private HashMap<String,Relationship> relationships;
    private HashMap<String, String> subvirtueandviceindex;
    public Character(LinkedList<Action> act, LinkedList<Action> state,  String namen, HashMap<String,Integer> Virtus, HashMap<String,Integer> passion, HashMap<String, String> SubvirtueToVirtue, int nodeInterval)
    {
        this.stateOfGrace = true; //We'll presuppose they're baptised
        this.story = "";
        this.name = namen;
        this.nodeInterval = nodeInterval;
        //new Character(Test.getActionList(), Test.getStateList(), names.getRandomName(), Test.getAllVirtues(), Test.getAllPassions())
        this.virtuesAndVices = (HashMap<String,Integer>) Virtus.clone();
        this.passions = (HashMap<String,Integer>)  passion.clone();
        this.relationships = new HashMap<>();
        crucialPoints = new LinkedList<>();
        setSubvirtueandviceindex(SubvirtueToVirtue);
        System.err.println("Character Stats: " + this.getVirtuesAndVices() + " " + this.getPassions());
        this.age = rand.nextInt(70);
        this.deathAge = rand.nextInt(80) + 40;
        actionBank = (LinkedList<Action>) act.clone();
        stateBank = (LinkedList<Action>) state.clone();
        System.err.println(stateBank.size());
        System.err.println(actionBank.size());
        //Action test = act.copyAllContents(act);
        /*while(test.getSubsequentAction() != null)
        {
            //System.out.println("Does copy all contents work : " + act.copyAllContents(act));
            test = test.getSubsequentAction();
        }*/
        listOfAllActions = new LinkedList<>();
        listOfAllActions.add(actionBank.get(rand.nextInt(actionBank.size())));
    }

    public LinkedList<graphNode> getCrucialPoints() {
        return crucialPoints;
    }

    public void setCrucialPoints(LinkedList<graphNode> crucialPoints) {
        this.crucialPoints = crucialPoints;
    }
    public void addCrucialPoint(graphNode crucialPoint)
    {
        this.crucialPoints.add(crucialPoint);
    }

    public HashMap<String, String> getSubvirtueandviceindex() {
        return subvirtueandviceindex;
    }

    public void setSubvirtueandviceindex(HashMap<String, String> subvirtueandviceindex) {
        this.subvirtueandviceindex = subvirtueandviceindex;
    }

    public int getNodeInterval() {
        return nodeInterval;
    }

    public void setNodeInterval(int nodeInterval) {
        this.nodeInterval = nodeInterval;
    }

    public int getCurrentActions() {
        return currentActions;
    }

    public void setCurrentActions(int currentActions) {
        this.currentActions = currentActions;
    }

    @Override
    public int compareTo(@NotNull Character o)
    {
        Integer thisFitness = this.returnFitness(this.getLengthOfActions());
        return thisFitness.compareTo(o.returnFitness(o.getLengthOfActions()));
    }


    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
    public int getLengthOfActions()
    {
        return listOfAllActions.size();
    }


    public LinkedList<Action> getActionBank() {
        Collections.shuffle(this.actionBank);return actionBank;
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

    public LinkedList<Action> getStateBank() {
        Collections.shuffle(this.stateBank); return stateBank;
    }

    public void setStateBank(LinkedList<Action> stateBank) {
         this.stateBank = stateBank;
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

    public HashMap<String,Relationship>  getRelationships() {
        return relationships;
    }

    public void setRelationships(HashMap<String,Relationship> relationships) {
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
                //System.out.println("Finding Subsequent Action " + nextAction.getId());
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
            //System.out.println("Finding Random Action");
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

        //System.out.println("Random Action: " + nextAction.getId());
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
            if(!c.getName().contentEquals(this.getName()) || c.getAge() != this.getAge())
            {
                relationships.put(c.getName(),new Relationship(c,newPassions));
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
    // I need the action to be cloned, because if it isn't I'll be making modifications to a pre existing action when I say it's true/false
    public void addActionToListofAllActions(Action act)
    {
        Action toAdd = act.returnClone();
        listOfAllActions.add(toAdd);
    }

    public void printStory(BibleLoader bible, NameGenerator generator)
    {
        //System.out.println("Test");
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
                    //System.out.println(toPrint.replace("<1>", generator.getRandomName()).replace("<2>", generator.getRandomName()).replace("<3>", generator.getRandomName()));
                    //System.out.println(tempAct.getPostConditionsAccept().getVirtueEffects());
                }
            }
            catch(Exception e)
            {
                System.err.println(e);
                System.exit(-1);
            }
            if(tempAct.getScriptures() != null)
            {
                //System.out.println(bible.getVerse(tempAct.getScriptures().get(0)));
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
                newValue = newValue + passions.get(currentVirtue) + rand.nextInt(30) - 15;
                passions.replace(currentVirtue,newValue);
            }
        }
    }
    public void calmDown()
    {
        Iterator<String> passionIterate = getPassions().keySet().iterator();
        HashMap<String, Integer> emotions;
        String passion;
        while(passionIterate.hasNext())
        {
            passion = passionIterate.next();
            emotions = getPassions();
            if(getPassions().get(passion) > 0) {
                emotions.replace(passion, getPassions().get(passion) - 5);
            }
            else
            {
                emotions.replace(passion, getPassions().get(passion) + 5);
            }
            setPassions(emotions);
        }
    }
    public void fallingOutOfTheHabit()
    {
        Iterator<String> passionIterate = getVirtuesAndVices().keySet().iterator();
        HashMap<String, Integer> virtues;
        String virtue;
        while(passionIterate.hasNext())
        {
            virtue = passionIterate.next();
            virtues = getVirtuesAndVices();
            if(virtues.get(virtue) > 0) {
                virtues.replace(virtue, virtues.get(virtue) - 1);
            }
            else
            {
                virtues.replace(virtue, virtues.get(virtue) + 1);
            }
            setVirtuesAndVices(virtues);
        }
    }

    public graphNode generateObjectivePoint()
    {
        System.err.println(this.getName());
        Iterator<String> throughNodes = subvirtueandviceindex.keySet().iterator();
        String current, virtueLogName, tableName;
        double toAdd;
        HashMap<String, Double> values = new HashMap<>();
        while(throughNodes.hasNext())
        {
            current = throughNodes.next();
            //System.err.println(current);
            if(current.contains("index") || current.contains("IS_PRECOND") || current.contains("IS_POSTCON") || current.contains("_PERSON") || current.contains("IS_ABOVE") || current.contains("ACTION_ID"))
            {
                //System.err.println("Not valid");
            }
            else if(subvirtueandviceindex.get(current).contains("PASSIONS"))
            {
                toAdd = Math.abs(passions.get(current));//We want anger to show up as well
                //System.err.println(toAdd);
                //System.err.println(values);
                if(values.containsKey("PASSIONS"))
                {
                    values.replace("PASSIONS", values.get("PASSIONS") + toAdd);
                }
                else
                {
                    values.put("PASSIONS", toAdd);
                }
            }
            else if(subvirtueandviceindex.get(current).contains("VIRTUE"))
            {
                toAdd = virtuesAndVices.get(current);
                //System.err.println(virtuesAndVices.get(current));
                virtueLogName = subvirtueandviceindex.get(current).replace("VIRTUE_", "");
                if(current.contains("SUBVICE"))
                {
                    toAdd = toAdd * -1;
                }
                if(values.containsKey(virtueLogName))
                {
                    values.replace(virtueLogName, values.get(virtueLogName) + toAdd);
                }
                else
                {
                    values.put(virtueLogName, toAdd);
                }
            }


        }
        graphNode node = new graphNode(values);
        System.err.println("New Point");
        System.err.println(node);
        return node;
    }

    public int returnFitness(int positionInActions)
    {
        return 0;
    }
    public LinkedList<Action> fullCrossover(LinkedList<Character> savedList, int lifeLength)
    {
        LinkedList<Action> newLife = new LinkedList<>();
        int numberOfPoints = 10;
        int position = 0;
        int crossoverIncrement = lifeLength / numberOfPoints;
        Character newChar = savedList.get(rand.nextInt(savedList.size()));
        for(int i = 0; i < numberOfPoints; i++)
        {
            newLife.addAll(crossover(savedList.get(rand.nextInt(savedList.size())),position, i * crossoverIncrement));
            rand.setSeed(rand.nextLong());
            position = i * crossoverIncrement;
        }

        return newLife;
    }
    public LinkedList<Action> crossover(Character toCrossover,int start, int point)
    {
        LinkedList<Action> newLife = new LinkedList<>();
        LinkedList<Action> lifeOne, lifeTwo;
        lifeOne = toCrossover.getListOfAllActions();
        for(int i = start; i < point; i++)
        {
            newLife.add(lifeOne.get(i));
        }
        return newLife;
    }
}
