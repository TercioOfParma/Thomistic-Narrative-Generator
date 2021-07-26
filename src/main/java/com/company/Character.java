package com.company;

public class Character
{
    Action listOfAllActions;
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
    public void printStory()
    {
        while(listOfAllActions.getPreviousAction() != null)
        {
            listOfAllActions = listOfAllActions.getPreviousAction();
        }
        while(listOfAllActions.getSubsequentAction() != null)
        {
            System.out.println(listOfAllActions.getPostConditionsAccept().getOtherEffects().get("POSTCONDITIONS_ACCEPT_OUTPUT"));
            listOfAllActions = listOfAllActions.getSubsequentAction();
        }
    }
}
