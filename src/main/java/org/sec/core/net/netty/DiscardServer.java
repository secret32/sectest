package org.sec.core.net.netty;

public class DiscardServer extends NettyServer {
	
	public DiscardServer() {
		super(new DiscardServerHandler());
	}

	public static void main(String[] args) {
		new DiscardServer().start(1111);
	}

}
