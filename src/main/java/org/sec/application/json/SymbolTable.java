package org.sec.application.json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou_wei
 * @since v0.1
 */
public class SymbolTable {

    private List<Object> list;

    private static SymbolTable instance;

    public static SymbolTable getInstance(int capacity) {
        if (instance == null) {
            instance = new SymbolTable(capacity);
        }
        return instance;
    }

    public static SymbolTable getInstance() {
        if (instance == null) {
            instance = new SymbolTable(10);
        }
        return instance;
    }

    private SymbolTable(int capacity) {
        list = new ArrayList<>(capacity);
    }

    public void addSymbol(Object symbol) {
        list.add(symbol);
    }

    public Object getSymbol(int index) {
        return list.get(index);
    }

}
