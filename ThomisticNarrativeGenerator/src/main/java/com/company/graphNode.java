package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class graphNode
{
    private double prudence,faith,hope,charity,justice,fortitude,temperance, passions;

    private double MAX_VARIABLE;
    private double MIN_VARIABLE;

    /*

    Normalisation function = x - min / max - min
     */
    //Make it clear I'm making something very uncomputational computational
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
        normalise();
    }

    public void normalise()
    {
        LinkedList<Double> allValues = new LinkedList<>();

        allValues.add(getFortitude());
        allValues.add(getHope());
        allValues.add(getJustice());
        allValues.add(getPrudence());
        allValues.add(getFaith());
        allValues.add(getCharity());
        allValues.add(getTemperance());
        Collections.sort(allValues);
        setMAX_VARIABLE(allValues.getFirst());
        setMIN_VARIABLE(allValues.getLast());
        setPrudence(getPrudence());
        setCharity(getCharity());
        setFaith(getFaith());
        setJustice(getJustice());
        setHope(getHope());
        setTemperance(getTemperance());
        setFortitude(getFortitude());

    }
    public double normaliseValue(double toNormalise)
    {
        return toNormalise - getMIN_VARIABLE() / getMAX_VARIABLE() - getMIN_VARIABLE();
    }
    public double getPrudence() {
        return prudence;
    }

    public void setPrudence(double prudence) {
        this.prudence = prudence;
    }

    public double getFaith() {
        return faith;
    }

    public void setFaith(double faith) {
        this.faith = faith;
    }

    public double getHope() {
        return hope;
    }

    public void setHope(double hope) {
        this.hope = hope;
    }

    public double getCharity() {
        return charity;
    }

    public void setCharity(double charity) {
        this.charity = charity;
    }

    public double getJustice() {
        return justice;
    }

    public void setJustice(double justice) {
        this.justice = justice;
    }

    public double getFortitude() {
        return fortitude;
    }

    public void setFortitude(double fortitude) {
        this.fortitude = fortitude;
    }

    public double getTemperance() {
        return temperance;
    }

    public void setTemperance(double temperance) {
        this.temperance = temperance;
    }

    public double getPassions() {
        return passions;
    }

    public void setPassions(double passions) {
        this.passions = passions;
    }

    public double getMAX_VARIABLE() {
        return MAX_VARIABLE;
    }

    public void setMAX_VARIABLE(double MAX_VARIABLE) {
        this.MAX_VARIABLE = MAX_VARIABLE;
    }

    public double getMIN_VARIABLE() {
        return MIN_VARIABLE;
    }

    public void setMIN_VARIABLE(double MIN_VARIABLE) {
        this.MIN_VARIABLE = MIN_VARIABLE;
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
