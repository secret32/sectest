package org.sec.core.asm;


import org.objectweb.asm.ClassVisitor;

/**
 * Created by Administrator on 2014/5/29.
 */
public class SecClassVisitor extends ClassVisitor {

    public SecClassVisitor(int api) {
        super(api);
    }
}
