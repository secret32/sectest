package org.sec.core.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Administrator on 2014/5/29.
 */
public class SecClassWriter {

    private final String NEW_CLASS = "org/sec/asm/SecObject";
    private final String CLASS_NAME = NEW_CLASS.replaceAll("/", ".");

    public SecClassWriter() {
    }

    public void writeSecObject() {
        try {
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, NEW_CLASS, null, "java/lang/Object", null);
            FieldVisitor fieldVisitor = writer.visitField(Opcodes.ACC_PRIVATE, "msg", "Ljava/lang/String", null, null);
            fieldVisitor.visitEnd();
            MethodVisitor methodVisitor = writer.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitEnd();
            writer.visitEnd();
            byte[] bytes = writer.toByteArray();
            writeToFile(bytes);
            ClassLoader loader = new SecClassLoader(bytes);
            Class<?> clazz = loader.loadClass(CLASS_NAME);
            System.out.println(clazz.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
	private String toHex(byte b) {
        String result = Integer.toHexString(b & 0xFF);
        if (result.length() == 1) {
            result = '0' + result;
        }
        return result.toUpperCase();
    }

    private void writeToFile(byte[] bts) {
        try {
            Path path = Paths.get("D:/SecObject.class");
            Files.write(path, bts, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING/*, StandardOpenOption.CREATE_NEW*/);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SecClassWriter writer = new SecClassWriter();
        writer.writeSecObject();
    }

}
