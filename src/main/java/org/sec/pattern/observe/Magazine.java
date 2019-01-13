package org.sec.pattern.observe;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2014/5/26.
 */
public class Magazine implements Service {

    private CopyOnWriteArrayList<Observer> list;

    public Magazine() {
        list = new CopyOnWriteArrayList<>();
    }

    public Magazine addSubscriber(Observer observer) {
        if (!list.contains(observer))
            list.add(observer);
        return this;
    }

    public Magazine removeSubscriber(Observer observer) {
        list.remove(observer);
        return this;
    }

    @Override
    public void send(String info) {
        for (Observer o : list) {
            o.update(info);
        }
    }

}
