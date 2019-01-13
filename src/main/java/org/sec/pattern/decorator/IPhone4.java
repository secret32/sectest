package org.sec.pattern.decorator;

/**
 * Created by zhou_wei on 2014/10/29.
 */
public class IPhone4 implements IPhone {

    private int length;

    public IPhone4() {
        this.length = 20;
    }

    @Override
    public int getLength() {
        return length;
    }
}
