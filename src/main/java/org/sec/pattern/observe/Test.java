package org.sec.pattern.observe;

import java.util.UUID;

/**
 * Created by Administrator on 2014/5/26.
 */
public class Test {

    public static void main(String[] args) {
        Magazine magazine = new Magazine();
        Observer a = new AObervser();
        Observer b = new BObervser();
        magazine.addSubscriber(a).addSubscriber(b);
        for (int i = 0; i <= 10; i++) {
            if (i == 8)
                magazine.removeSubscriber(a);
            String str = UUID.randomUUID().toString().substring(0, 8).toLowerCase();
            magazine.send(str);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
