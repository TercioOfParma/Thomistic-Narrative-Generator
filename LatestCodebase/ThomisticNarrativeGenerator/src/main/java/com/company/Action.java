package com.company;

import java.util.LinkedList;

public abstract class Action
{
    protected Action PreviousAction = null;
    protected Action SubsequentAction = null;
    protected Conditions PreConditions;
    protected Conditions PostConditionsAccept;
    protected Conditions PostConditionsReject;
    protected String id;
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
        if(currentPos.getId().contentEquals(id) == true)
        {
            return currentPos;
        }
        return null;
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
    abstract public void evaluateChoice(Character C);
}
