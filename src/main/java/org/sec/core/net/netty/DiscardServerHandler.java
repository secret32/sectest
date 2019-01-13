package org.sec.core.net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class DiscardServerHandler extends SimpleChannelInboundHandler {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ((ByteBuf) msg).release();
		ByteBuf in = (ByteBuf) msg;
		System.out.println(in.toString(CharsetUtil.US_ASCII));
		in.release();
	}

}
