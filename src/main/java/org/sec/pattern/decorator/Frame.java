package org.sec.pattern.decorator;

/**
 * Created by Administrator on 2014/5/26.
 */
@Deprecated
public class Frame implements Decorator {

    @Override
    public String getName(){
        return "";
    }

    @Override
    public void decorate() {
        System.out.println("We built the frame of this house.");
    }
}
