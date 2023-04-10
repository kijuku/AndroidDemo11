package com.main.myshoppingdatabase;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    protected String name;
    protected float amount;
    protected String unit;
    protected Date date;

    public Note() {
    }

    public Note(String name,  Date date) {
        this.name = name;
        this.amount = 0.0f;
        this.date = date;
        this.unit = " kpl";
    }
    public Note(String name, float amount, Date date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.unit = " kpl";
    }

    public Note(String name, float amount) {
        this.name = name;
        this.amount = amount;
        this.date = new Date();
        this.unit = " kpl";
    }

    public String getName() {
        return this.name.toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void addAmount() {
        this.amount++;
    }

    public void decreaseAmount() {
        this.amount--;
    }

    public Note name(String name) {
        setName(name);
        return this;
    }

    public Note amount(float amount) {
        setAmount(amount);
        return this;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Note{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", date=" + date +
                '}';
    }
}
