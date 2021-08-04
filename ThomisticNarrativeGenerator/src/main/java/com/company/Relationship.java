package com.company;

import java.util.HashMap;

public class Relationship
{
    private HashMap<String, Integer> passions; //This is a hashmap and not discrete variables because people may wish to add other bases for relationships in future
    Character characterA, characterB;
    Relationship previousRelationship, subsequentRelationship; //So it acts as a LinkedList

    public Relationship(Character A, Character B, HashMap<String, Integer> passionsMap)
    {
        characterA = A;
        characterB = B;
        passions = passionsMap;
    }

    public Relationship getPreviousRelationship()
    {
        return previousRelationship;
    }

    public void setPreviousRelationship(Relationship previousRelationship)
    {
        this.previousRelationship = previousRelationship;
    }

    public Relationship getSubsequentRelationship()
    {
        return subsequentRelationship;
    }

    public void setSubsequentRelationship(Relationship subsequentRelationship)
    {
        this.subsequentRelationship = subsequentRelationship;
    }

    public Character getCharacterA()
    {
        return characterA;
    }

    public void setCharacterA(Character characterA)
    {
        this.characterA = characterA;
    }

    public Character getCharacterB()
    {
        return characterB;
    }

    public void setCharacterB(Character characterB)
    {
        this.characterB = characterB;
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
