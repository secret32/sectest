package org.sec.core.net.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoServer extends NettyServer {
	
	private EchoServer() {
		super(new SimpleChannelInboundHandler() {
			@Override
			protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
				ctx.write(msg);
				ctx.flush();
			}

		});
	}
	
	public static void main(String[] args) {
		new EchoServer().start(1111);
	}

}
