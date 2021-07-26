package com.company;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Conditions
{
    private HashMap<String, Integer> virtueEffects;
    private HashMap<String, String> otherEffects;
    private LinkedList<String> scriptures;


    public Conditions(HashMap<String, Object> allObjects)
    {
        virtueEffects = new HashMap<String, Integer>();
        otherEffects = new HashMap<String,String>();
        scriptures = new LinkedList<String>();
        setVariables(allObjects);
    }

    public HashMap<String, String> getOtherEffects() {
        return otherEffects;
    }

    public void setOtherEffects(HashMap<String, String> otherEffects) {
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
            if(rawInfo.get(key) instanceof Integer)
            {
                virtueEffects.put(key, (int)rawInfo.get(key));
            }
            else if(rawInfo.get(key) instanceof String)
            {
                otherEffects.put(key,(String) rawInfo.get(key));
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
