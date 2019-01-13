package org.sec.application.json;

/**
 * @author zhou_wei
 * @since v0.1
 */
public enum Status {

    START,
    OBJECT_START,
    OBJECT_END,
    ARRAY_START,
    ARRAY_END,
    KEY_START,
    KEY_END,
    VALUE_START,
    VALUE_END,
    END;

}
