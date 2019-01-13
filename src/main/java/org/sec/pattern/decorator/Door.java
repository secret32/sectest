package org.sec.pattern.decorator;

/**
 * Created by Administrator on 2014/5/26.
 */
@Deprecated
public class Door implements Decorator {

    private Decorator decorator;

    public Door(Decorator decorator){
        this.decorator = decorator;
    }

    @Override
    public void decorate() {
        decorator.decorate();
        System.out.println("we built a door 4 the house.");
    }
}
