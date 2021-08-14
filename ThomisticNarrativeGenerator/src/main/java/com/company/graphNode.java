package com.company;

import java.util.HashMap;

public class graphNode
{
    private int prudence,faith,hope,charity,justice,fortitude,temperance, passions;


    public graphNode(HashMap<String, Integer> values)
    {
        setCharity(values.get("CHARITY"));
        setFaith(values.get("FAITH"));
        setHope(values.get("HOPE"));
        setJustice(values.get("JUSTICE"));
        setTemperance(values.get("TEMPERANCE"));
        setFortitude(values.get("FORTITUDE"));
        setPrudence(values.get("PRUDENCE"));
        setPassions(values.get("PASSIONS"));
    }

    public int getPassions() {
        return passions;
    }

    public void setPassions(int passions) {
        this.passions = passions;
    }

    public int getTemperance() {
        return temperance;
    }

    public void setTemperance(int temperance) {
        this.temperance = temperance;
    }

    public int getPrudence() {
        return prudence;
    }

    public void setPrudence(int prudence) {
        this.prudence = prudence;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public int getHope() {
        return hope;
    }

    public void setHope(int hope) {
        this.hope = hope;
    }

    public int getCharity() {
        return charity;
    }

    public void setCharity(int charity) {
        this.charity = charity;
    }

    public int getJustice() {
        return justice;
    }

    public void setJustice(int justice) {
        this.justice = justice;
    }

    public int getFortitude() {
        return fortitude;
    }

    public void setFortitude(int fortitude) {
        this.fortitude = fortitude;
    }

    @Override
    public String toString() {
        return "graphNode{" +
                "prudence=" + prudence +
                ", faith=" + faith +
                ", hope=" + hope +
                ", charity=" + charity +
                ", justice=" + justice +
                ", fortitude=" + fortitude +
                ", temperance=" + temperance +
                ", passions=" + passions +
                '}';
    }
}
