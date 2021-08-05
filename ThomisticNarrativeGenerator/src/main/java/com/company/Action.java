package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public abstract class Action
{
    protected LinkedList<Action> allActions;
    protected Conditions PreConditions = new Conditions();
    protected Conditions PostConditionsAccept = new Conditions();;
    protected Conditions PostConditionsReject = new Conditions();;
    protected String id;
    private Random rand = new Random();
    private LinkedList<String> scriptures;

    public LinkedList<Action> getAllActions() {
        return allActions;
    }

    public void setAllActions(LinkedList<Action> allActions) {
        this.allActions = allActions;
    }

    public Conditions getPreConditions() {
        return PreConditions;
    }
    public ActualGrace copyAGContents(Action act)
    {
        ActualGrace newGrace = new ActualGrace(act.getAllActions());
        newGrace.setId(act.getId());
        newGrace.setPreConditions(act.getPreConditions());
        newGrace.setPostConditionsReject(act.getPostConditionsReject());
        newGrace.setPostConditionsAccept(act.getPostConditionsAccept());
        newGrace.setScriptures(act.getScriptures());

        return newGrace;
    }
    public Temptation copyTemptContents(Action act)
    {
        Temptation tempt = new Temptation(act.getAllActions());
        tempt.setId(act.getId());
        tempt.setPreConditions(act.getPreConditions());
        tempt.setPostConditionsReject(act.getPostConditionsReject());
        tempt.setPostConditionsAccept(act.getPostConditionsAccept());
        tempt.setScriptures(act.getScriptures());

        return tempt;
    }
    public void printAllActions(Action A)
    {
        Action tempIterate = A;
        System.err.println("All Actions: ");
        Iterator<Action> iterate = A.getAllActions().iterator();
        while(iterate.hasNext())
        {
            tempIterate = iterate.next();
            System.err.println(tempIterate.getId());

        }
    }
    public LinkedList<Action> copyAllContents(Action actionList)
    {
        printAllActions(actionList);
        if(actionList == null)
        {
            return null;
        }
        Action tempList;
        Iterator<Action> iterate = actionList.getAllActions().iterator();
        while(iterate.hasNext())
        {
            tempList = iterate.next();
            if(getAllActions() == null)
            {
                    allActions = new LinkedList<>();
                    allActions.add(tempList);
                }
            else
            {
                    allActions.add(tempList);
            }



        }
        System.err.println("Finished Character");
        return allActions;
    }

    public LinkedList<String> getScriptures() {
        return scriptures;
    }

    public void setScriptures(LinkedList<String> scriptures) {
        this.scriptures = scriptures;
    }

    public String getId()
    {
        return id;
    }
    public String toString()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public void setPreConditions(Conditions preConditions) {
        PreConditions = preConditions;
    }

    public Conditions getPostConditionsAccept() {
        return PostConditionsAccept;
    }

    public void setPostConditionsAccept(Conditions postConditionsAccept) {
        PostConditionsAccept = postConditionsAccept;
    }

    public Conditions getPostConditionsReject() {
        return PostConditionsReject;
    }

    public void setPostConditionsReject(Conditions postConditionsReject) {
        PostConditionsReject = postConditionsReject;
    }
    public Action searchList(String id)
    {
        Iterator<Action> iterate = allActions.iterator();
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
    public int getActionListLength(Action A)
    {
        Iterator<Action> iterate = A.getAllActions().iterator();
        Action toCheck;
        int i = 0;
        while(iterate.hasNext())
        {
            toCheck = iterate.next();
            i = i + 1;
        }

        return i;
    }
    public void doApplicationOfAction(Character C, Character C2, Character C3, boolean isAccept)
    {
        Conditions effects = this.getPostConditionsReject();
        String newStoryAction = C.getStory();
        Iterator<String> virtueEffects = effects.getVirtueEffects().keySet().iterator();
        String nextEffect = "";
        String oldEffect = "";
        String toAddToStory;
        String prefix;
        int newValue = 0;
        if(isAccept && this instanceof Temptation)
        {
            prefix = "POSTCONDITIONS_REJECT_";
        }
        else if(isAccept && this instanceof ActualGrace)
        {
            System.out.println("True");
            System.out.println(this.getId());
            prefix = "POSTCONDITIONS_ACCEPT_";
        }
        else
        {
            prefix = "POSTCONDITIONS_REJECT_";
        }
        System.out.println(prefix + "OUTPUT" + " The output field" + this.getId());
        toAddToStory = (String)this.getPostConditionsAccept().getOtherEffects().get(prefix + "OUTPUT") + "\n";
        while(virtueEffects.hasNext())
        {
            nextEffect = virtueEffects.next();
            oldEffect = nextEffect;

            System.out.println("New Action : " + toAddToStory + " From: " + prefix + "OUTPUT");
            if(nextEffect.contains(prefix))
            {
                System.out.println("Relevant Stat! " + nextEffect);
            }
            if(!nextEffect.contains(prefix))
            {
                System.out.println("Skipping irrelevant stat" + nextEffect);
            }
            else if(nextEffect.contains("SECOND_PERSON"))
            {
                nextEffect = nextEffect.replace(prefix, "");
                nextEffect = nextEffect.replace("_SECOND_PERSON", "");
                if(nextEffect.contentEquals("HOPE") || nextEffect.contentEquals("FAITH") || nextEffect.contentEquals("CHARITY"))
                {
                    nextEffect = "VIRTUE_" + nextEffect;
                }
                System.out.println(nextEffect);
                System.out.println(C.getVirtuesAndVices().get(nextEffect));
                System.out.println(effects.getVirtueEffects().get(oldEffect));
                if(nextEffect.contains("VIRTUE")) {
                    try {
                        newValue = C.getVirtuesAndVices().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                        C.getVirtuesAndVices().replace(nextEffect, newValue);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e + " SECOND_PERSON");
                    }
                }
                else
                {
                    try {
                        newValue = C.getPassions().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                        C.getPassions().replace(nextEffect, newValue);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e+ " SECOND_PERSON");
                    }
                }
            }
            else if(nextEffect.contains("THIRD_PERSON"))
            {
                nextEffect = nextEffect.replace(prefix, "");
                nextEffect = nextEffect.replace("_SECOND_PERSON", "");
                if(nextEffect.contentEquals("HOPE") || nextEffect.contentEquals("FAITH") || nextEffect.contentEquals("CHARITY"))
                {
                    nextEffect = "VIRTUE_" + nextEffect;
                }
                System.out.println(nextEffect);
                System.out.println(C.getVirtuesAndVices().get(nextEffect));
                System.out.println(effects.getVirtueEffects().get(oldEffect));
                if(nextEffect.contains("VIRTUE")) {
                    try {
                        newValue = C.getVirtuesAndVices().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                        C.getVirtuesAndVices().replace(nextEffect, newValue);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e+ " THIRD_PERSON");
                    }
                }
                else
                {
                    try {
                        newValue = C.getPassions().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                        C.getPassions().replace(nextEffect, newValue);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e + " THIRD_PERSON");
                    }
                }
            }
            else
            {
                nextEffect = nextEffect.replace(prefix, "");
                if(nextEffect.contentEquals("HOPE") || nextEffect.contentEquals("FAITH") || nextEffect.contentEquals("CHARITY"))
                {
                    nextEffect = "VIRTUE_" + nextEffect;
                }
                System.out.println(nextEffect);
                System.out.println(C.getVirtuesAndVices().get(nextEffect));
                System.out.println(effects.getVirtueEffects().get(oldEffect));
                if(nextEffect.contains("VIRTUE")) {
                    try {
                        newValue = C.getVirtuesAndVices().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                        C.getVirtuesAndVices().replace(nextEffect, newValue);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e+ " FIRST_PERSON");
                    }
                }
                else
                {
                    try {
                        newValue = C.getPassions().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                        C.getPassions().replace(nextEffect, newValue);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e+ " FIRST_PERSON");
                    }
                }


            }
            System.out.println("Finished Effect!");
        }
        System.out.println("To Add: " + toAddToStory);
        toAddToStory =  toAddToStory.replace("<1>",C.getName());
        System.out.println("To Add: " + toAddToStory);
        toAddToStory =  toAddToStory.replace("<2>", C2.getName());
        System.out.println("To Add: " + toAddToStory);
        toAddToStory =  toAddToStory.replace("<3>", C2.getName());
        System.out.println("To Add: " + toAddToStory);
        C.setStory(C.getStory() + toAddToStory);
        System.out.println("Updated Story : " + C.getStory());
        System.out.println("Done!");

    }





    abstract public void evaluateChoice(Character C, Character C2, Character C3);
}
