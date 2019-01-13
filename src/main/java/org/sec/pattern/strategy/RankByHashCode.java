package org.sec.pattern.strategy;

/**
 * Created by zhou_wei on 2014/10/29.
 */
public class RankByHashCode extends RankRule {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof String && o2 instanceof String) {
            return ((String) o1).hashCode() > ((String) o2).hashCode() ? 1 : -1;
        }
        return -2;
    }

    private RankByHashCode(){

    }

    public static RankByHashCode getInstance() {
        return new RankByHashCode();
    }
}
