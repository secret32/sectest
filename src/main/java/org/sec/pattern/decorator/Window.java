package org.sec.pattern.decorator;

/**
 * Created by Administrator on 2014/5/26.
 */
@Deprecated
public class Window implements Decorator {

    private Decorator decorator;

    public Window(Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void decorate() {
        decorator.decorate();
        buildWindow();
    }

    public void buildWindow() {
        System.out.println("we build 7 windows for the house");
    }
}
