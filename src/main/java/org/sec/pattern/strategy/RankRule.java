package org.sec.pattern.strategy;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Created by zhou_wei on 2014/10/29.
 */
public abstract class RankRule implements Comparator<Object> {

    @Override
    public Comparator<Object> reversed() {
        return null;
    }

    @Override
    public Comparator<Object> thenComparing(Comparator<? super Object> other) {
        return null;
    }

    @Override
    public <U> Comparator<Object> thenComparing(Function<? super Object, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Object> thenComparing(Function<? super Object, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Object> thenComparingInt(ToIntFunction<? super Object> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Object> thenComparingLong(ToLongFunction<? super Object> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Object> thenComparingDouble(ToDoubleFunction<? super Object> keyExtractor) {
        return null;
    }
}
