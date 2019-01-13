package org.sec.pattern.decorator;

/**
 * Created by Administrator on 2014/5/26.
 * This interface had been designed because of my misunderstanding to the decorator design pattern.
 * So it was deprecated, as well as Door, Frame, House and Window.
 */
@Deprecated
public interface Decorator {

    default public String getName() {
        return this.getClass().getName();
    }

    public void decorate();

}
