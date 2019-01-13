package org.sec.pattern.observe;

/**
 * Created by Administrator on 2014/5/26.
 */
public class BObervser implements Observer {

    @Override
    public void update(String info) {
        if (info.indexOf("b") != -1)
            System.out.println("BOberser: " + info);
    }
    
}
