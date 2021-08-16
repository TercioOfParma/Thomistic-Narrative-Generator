package com.company;

import java.util.HashMap;

public class Relationship
{
    private HashMap<String, Integer> passions; //This is a hashmap and not discrete variables because people may wish to add other bases for relationships in future
    Character characterA;

    public Relationship(Character A, HashMap<String, Integer> passionsMap)
    {
        characterA = A;
        passions = passionsMap;
    }

    public Character getCharacterA()
    {
        return characterA;
    }

    public void setCharacterA(Character characterA)
    {
        this.characterA = characterA;
    }

    public HashMap<String, Integer> getPassions()
    {
        return passions;
    }

    public void setPassions(HashMap<String, Integer> passions)
    {
        this.passions = passions;
    }
}
