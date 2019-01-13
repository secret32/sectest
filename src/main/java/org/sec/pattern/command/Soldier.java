package org.sec.pattern.command;

/**
 * Created by Administrator on 2014/5/26.
 */
public class Soldier {

    String name;

    public Soldier(String name) {
        this.name = name;
    }

    public void crash() {
        System.out.println(name + " crashed the town!");
    }

    public void rebuild() {
        System.out.println(name + " rebuild the town!");
    }

}
