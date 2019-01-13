package org.sec.pattern.decorator;

/**
 * Created by zhou_wei on 2014/10/29.
 */
public class IPhone5 implements IPhone {

    private IPhone iPhone4;

    public IPhone5() {
        iPhone4 = new IPhone4();
    }

    @Override
    public int getLength() {
        return iPhone4.getLength() + 10;
    }
}
