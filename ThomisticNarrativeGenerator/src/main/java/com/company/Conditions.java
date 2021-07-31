package com.company;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Conditions
{
    private HashMap<String, Integer> virtueEffects;
    private HashMap<String, Object> otherEffects;
    private LinkedList<String> scriptures;


    public Conditions()
    {
        virtueEffects = new HashMap<String, Integer>();
        otherEffects = new HashMap<String,Object>();
        scriptures = new LinkedList<String>();
    }

    public HashMap<String, Object> getOtherEffects() {
        return otherEffects;
    }

    public void setOtherEffects(HashMap<String, Object> otherEffects) {
        this.otherEffects = otherEffects;
    }

    public void addScripture(String verseReference)
    {
        scriptures.add(verseReference);
    }
    public void addVirtueEffect(String key, int value)
    {
        virtueEffects.put(key,value);
    }

    public void setVariables(HashMap<String, Object> rawInfo) //This has the entire action, presupposes query
    {
        String key;
        Iterator<String> keys = rawInfo.keySet().iterator();
        while(keys.hasNext())
        {
            key = keys.next();
            System.out.println("Next key " + key);
            if(rawInfo.get(key) instanceof Integer)
            {
                System.out.println("Variable Set " + key);
                virtueEffects.put(key, (int)rawInfo.get(key));
            }
            else
            {
                System.out.println("Other Set " + key);
                otherEffects.put(key, rawInfo.get(key));
            }
        }
    }
    public HashMap<String, Integer> getVirtueEffects()
    {
        return virtueEffects;
    }

    public void setVirtueEffects(HashMap<String, Integer> virtueEffects)
    {
        this.virtueEffects = virtueEffects;
    }

    public LinkedList<String> getScriptures() {
        return scriptures;
    }

    public void setScriptures(LinkedList<String> scriptures) {
        this.scriptures = scriptures;
    }
}
