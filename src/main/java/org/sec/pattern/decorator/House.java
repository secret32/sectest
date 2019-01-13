package org.sec.pattern.decorator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/5/26.
 */
@Deprecated
public class House {

    public List<Decorator> list = new ArrayList<>();

    public House addDecorator(Decorator decorator) {
        list.add(decorator);
        return this;
    }

    public void showDecorator() {
        for (Decorator decorator : list) {
            System.out.println(decorator.getName());
        }
    }

    public static void main(String[] args) {
        Frame f = new Frame();
        House h1 = new House();
        h1.addDecorator(new Window(f));
        House h2 = new House();
        h2.addDecorator(new Window(f)).addDecorator(new Door(f));
        h1.showDecorator();
        System.out.println("*****************************");
        h2.showDecorator();
    }

}
