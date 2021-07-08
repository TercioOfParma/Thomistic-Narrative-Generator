package com.company;

public class Character
{
    Action listOfAllActions;
    public Character()
    {
        listOfAllActions = new ActualGrace();
        listOfAllActions.setSubsequentAction(new Temptation());
        listOfAllActions.getSubsequentAction().setSubsequentAction(new Temptation());
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
}
