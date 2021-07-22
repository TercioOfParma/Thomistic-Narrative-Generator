package com.company;

public abstract class Action
{
    protected Action PreviousAction = null;
    protected Action SubsequentAction = null;
    protected Conditions PreConditions;

    public Conditions getPreConditions() {
        return PreConditions;
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

    protected Conditions PostConditionsAccept;
    protected Conditions PostConditionsReject;


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
