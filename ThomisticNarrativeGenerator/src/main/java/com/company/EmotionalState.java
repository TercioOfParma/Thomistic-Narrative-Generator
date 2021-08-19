package com.company;

import java.util.LinkedList;
import java.util.Random;

public class EmotionalState extends Action{
    public EmotionalState(LinkedList<Action> actionList)
    {
        this.allActions= actionList;
    }
    Random rand = new Random();
    @Override
    public void evaluateChoice(Character C, Character C2, Character C3, Verse scr)
    {
        actoutState(C);
    }
}
