package org.sec.utils;

import java.util.Comparator;

/**
 * Created by zhou_wei on 2015/1/19.
 * guava-libraries ComparisonChain
 * https://code.google.com/p/guava-libraries/source/browse/guava/src/com/google/common/collect/ComparisonChain.java?name=v13.0-rc2
 */
public abstract class ComparisonChain {

    public abstract ComparisonChain compare(Object o1, Object o2);
    public abstract ComparisonChain compare(Object o1, Object o2, Comparator<?> comparator);
    public abstract int result();

    public static ComparisonChain start() {
        return ACTIVE;
    }

    private static ComparisonChain ACTIVE = new ComparisonChain() {
        @Override
        public ComparisonChain compare(Object o1, Object o2) {
            int i = 1; // TODO compare o1 with o2
            return cp(i);
        }

        @Override
        public ComparisonChain compare(Object o1, Object o2, Comparator<?> comparator) {
            int i = 1; // TODO compare o1 with o2
            return cp(i);
        }

        @Override
        public int result() {
            return 0;
        }

        private ComparisonChain cp(int result) {
            return result == 0 ? ACTIVE : (result > 0 ? GREATER : LESS);
        }
    };

    private static ComparisonChain GREATER = new InactiveComparisonChain(1);
    private static ComparisonChain LESS = new InactiveComparisonChain(-1);

    private static class InactiveComparisonChain extends ComparisonChain {

        private int result;

        public InactiveComparisonChain(int result) {
            this.result = result;
        }

        @Override
        public ComparisonChain compare(Object o1, Object o2) {
            return this;
        }

        @Override
        public ComparisonChain compare(Object o1, Object o2, Comparator<?> comparator) {
            return this;
        }

        @Override
        public int result() {
            return result;
        }
    }

}
