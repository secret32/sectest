package org.sec.pattern.observe;

/**
 * Created by Administrator on 2014/5/26.
 */
public class AObervser implements Observer {

    @Override
    public void update(String info) {
        if (info.indexOf("a") != -1)
            System.out.println("AOberser: " + info);
    }

}
