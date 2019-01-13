package org.sec.pattern.strategy;

/**
 * Created by zhou_wei on 2014/10/29.
 */
public class RankByNameLength extends RankRule {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof String && o2 instanceof String) {
            return ((String) o1).length() > ((String) o2).length() ? 1 : -1;
        }
        return -2;
    }

    private RankByNameLength() {

    }

    public static RankByNameLength getInstance() {
        return new RankByNameLength();
    }
}
