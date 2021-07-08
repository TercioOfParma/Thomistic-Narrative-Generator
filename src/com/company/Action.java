package com.company;

public abstract class Action
{
    protected Action PreviousAction = null;
    protected Action SubsequentAction = null;

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
