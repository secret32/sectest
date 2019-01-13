package org.sec.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SingletonTest {

	@SuppressWarnings("unused")
	private static void reflect() {
		try {
			Singleton s = Singleton.getInstance();
			System.out.println(s);
			// Constructor<Singleton>[] constructors =
			// (Constructor<Singleton>[]) Singleton.class
			// .getDeclaredConstructors();
			// for (Constructor<Singleton> constructor : constructors) {
			// constructor.setAccessible(true);
			// Singleton singleton = constructor.newInstance();
			// System.out.println(singleton);
			// }
			Field field = Singleton.class.getDeclaredField("instance");
			field.setAccessible(true);
			field.set(s, null);
			System.out.println(Singleton.getInstance());
		} catch (IllegalAccessException | IllegalArgumentException
				| NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Single aSingle = Single.SINGLE;
			System.out.println(aSingle);
			Constructor<Single> sConstructor = (Constructor<Single>) Single.class
					.getDeclaredConstructors()[0];
			sConstructor.setAccessible(true);
			Single single = sConstructor.newInstance();
			System.out.println(single);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
