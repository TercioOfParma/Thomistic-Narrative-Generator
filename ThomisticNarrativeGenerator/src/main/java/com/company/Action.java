package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public abstract class Action
{
    protected Action PreviousAction = null;
    protected Action SubsequentAction = null;
    protected Conditions PreConditions = new Conditions();
    protected Conditions PostConditionsAccept = new Conditions();;
    protected Conditions PostConditionsReject = new Conditions();;
    protected String id;
    private Random rand = new Random();
    private LinkedList<String> scriptures;
    public Conditions getPreConditions() {
        return PreConditions;
    }
    public ActualGrace copyAGContents(Action act)
    {
        ActualGrace newGrace = new ActualGrace();
        newGrace.setId(act.getId());
        newGrace.setPreConditions(act.getPreConditions());
        newGrace.setPostConditionsReject(act.getPostConditionsReject());
        newGrace.setPostConditionsAccept(act.getPostConditionsAccept());
        newGrace.setScriptures(act.getScriptures());

        return newGrace;
    }
    public Temptation copyTemptContents(Action act)
    {
        Temptation tempt = new Temptation();
        tempt.setId(act.getId());
        tempt.setPreConditions(act.getPreConditions());
        tempt.setPostConditionsReject(act.getPostConditionsReject());
        tempt.setPostConditionsAccept(act.getPostConditionsAccept());
        tempt.setScriptures(act.getScriptures());

        return tempt;
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
        Action currentPos = this;
        while(currentPos.getPreviousAction() != null)
        {
            currentPos = currentPos.getPreviousAction();
            //System.out.println("Test!" + currentPos.getId() + "Next Action: " + currentPos.getPreviousAction().getId());
        }
        while(!currentPos.getId().contentEquals(id) && currentPos.getSubsequentAction() != null)
        {
            currentPos = currentPos.getSubsequentAction();
        }
        if (currentPos == null)
        {
            return null;
        }
        if(currentPos.getId().contentEquals(id))
        {
            return currentPos;
        }
        return null;
    }
    public int getActionListLength(Action A)
    {
        Action toLoop = A;
        int i = 0;
        while(toLoop.getSubsequentAction() != null)
        {
            toLoop = toLoop.getSubsequentAction();
            i = i + 1;
        }

        return i;
    }
    public void doApplicationOfAction(Character C, Character C2, Character C3, Action subsequentAction, boolean isAccept)
    {
        Conditions effects = this.getPostConditionsReject();
        String newStoryAction = C.getStory();
        Iterator<String> virtueEffects = effects.getVirtueEffects().keySet().iterator();
        String nextEffect = "";
        String oldEffect = "";
        String toAddToStory;
        String prefix;
        int newValue;
        if(isAccept && this instanceof Temptation)
        {
            prefix = "POSTCONDITIONS_REJECT_";
        }
        else if(isAccept && this instanceof ActualGrace)
        {
            System.out.println("True");
            prefix = "POSTCONDITIONS_ACCEPT_";
        }
        else
        {
            prefix = "POSTCONDITIONS_REJECT_";
        }

        while(virtueEffects.hasNext())
        {
            nextEffect = virtueEffects.next();
            oldEffect = nextEffect;
            if(nextEffect.contains("SECOND_PERSON"))
            {
                nextEffect = nextEffect.replace(prefix, "");
                nextEffect = nextEffect.replace("_SECOND_PERSON", "");
                C2.getVirtuesAndVices().replace(nextEffect,(int)C2.getVirtuesAndVices().get(nextEffect) + effects.getVirtueEffects().get(oldEffect) );
            }
            else if(nextEffect.contains("THIRD_PERSON"))
            {
                nextEffect = nextEffect.replace(prefix, "");
                nextEffect = nextEffect.replace("_SECOND_PERSON", "");
                newValue = C3.getVirtuesAndVices().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                C3.getVirtuesAndVices().replace(nextEffect, newValue);
            }
            else
            {
                nextEffect = nextEffect.replace(prefix, "");
                System.out.println(prefix + nextEffect + oldEffect);
                newValue = C.getVirtuesAndVices().get(nextEffect) + effects.getVirtueEffects().get(oldEffect);
                C.getVirtuesAndVices().replace(nextEffect, newValue);
            }
            toAddToStory = (String)this.getPostConditionsAccept().getOtherEffects().get(prefix + "_OUTPUT") + "\n";
            toAddToStory =  toAddToStory.replace("<1>",C.getName());
            toAddToStory =  toAddToStory.replace("<2>", C2.getName());
            toAddToStory =  toAddToStory.replace("<3>", C2.getName());
            C.setStory(newStoryAction + toAddToStory);
        }

    }
    public Action getRandomAction()
    {
        int randomAction;
        Action nextAction = rewindActionList(this);
        randomAction = rand.nextInt(getActionListLength(nextAction));
        nextAction = rewindActionList(nextAction);
        for(int j = 0; j < randomAction; j++)
        {
            nextAction = nextAction.getSubsequentAction();
        }

        return nextAction;
    }
    public Action rewindActionList(Action A)
    {
        Action nextAction = A;
        while(nextAction.getPreviousAction() != null)
        {
            nextAction = nextAction.getPreviousAction();
        }
        return nextAction;
    }
    public Action getNextCharacterAction(Character C)
    {
        Action nextAction = rewindActionList(this);
        boolean isFound = false;
        HashMap<String,Integer> virtues, precons;
        String temp;
        while(nextAction.getSubsequentAction() != null)
        {
            nextAction = nextAction.getSubsequentAction(); //We do want to skip the template
            virtues = C.getVirtuesAndVices();
            isFound = true;
            precons = nextAction.getPreConditions().getVirtueEffects();
            for(String key : virtues.keySet())
            {
                temp = key+ "_ABOVE";
                if(!(precons.get(temp) <= virtues.get(key)))
                {
                    isFound = false;
                    break;
                }
                temp = key+ "_BELOW";
                if(!(precons.get(temp) >= virtues.get(key)))
                {
                    isFound = false;
                    break;
                }
            }
            if(isFound)
            {
                int test = rand.nextInt(5);
                if(test > 2)
                {
                    break;
                }
            }
        }

        if(!isFound)
        {
            nextAction = getRandomAction();
        }
        C.setAge(C.getAge() + 1);
        return nextAction;
    }



    public void setPreviousAction(Action A)
    {
        PreviousAction = A;
    }
    public void setSubsequentAction(Action A)
    {
        SubsequentAction = A;
    }
    public Action getPreviousAction()
    {
        return PreviousAction;
    }
    public Action getSubsequentAction()
    {
        return SubsequentAction;
    }
    abstract public void evaluateChoice(Character C, Character C2, Character C3, Action subsequent);
}
