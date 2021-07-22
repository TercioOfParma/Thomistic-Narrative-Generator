package com.company;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Conditions
{
    private HashMap<String, Integer> virtueEffects;
    private HashMap<String, Boolean> statusEffects;
    private LinkedList<String> scriptures;
    private String id, output;


    public Conditions(HashMap<String, Integer> virtueEffects, HashMap<String, Boolean> statusEffects, String id, String output)
    {
        this.virtueEffects = virtueEffects;
        this.statusEffects = statusEffects;
        this.id = id;
        this.output = output;
    }
    public void addScripture(String verseReference)
    {
        scriptures.add(verseReference);
    }
    public void addVirtueEffect(String key, int value)
    {
        virtueEffects.put(key,value);
    }

    public HashMap<String, Integer> getVirtueEffects()
    {
        return virtueEffects;
    }

    public void setVirtueEffects(HashMap<String, Integer> virtueEffects)
    {
        this.virtueEffects = virtueEffects;
    }

    public HashMap<String, Boolean> getStatusEffects()
    {
        return statusEffects;
    }

    public void setStatusEffects(HashMap<String, Boolean> statusEffects)
    {
        this.statusEffects = statusEffects;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }


 }
