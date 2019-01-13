package org.sec.pattern.observe;

/**
 * Created by Administrator on 2014/5/26.
 */
public interface Observer {

    default public void update(String info) {
        System.out.println(info);
    }
}
