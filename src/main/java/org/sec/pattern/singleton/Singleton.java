package org.sec.pattern.singleton;

final public class Singleton {

	private static Singleton instance = null;

	private Singleton() throws Exception {
		if (instance != null)
			throw new Exception(
					"cannot initialize this class more than one time");
	}

	public synchronized static Singleton getInstance() {
		return instance;
	}

}
