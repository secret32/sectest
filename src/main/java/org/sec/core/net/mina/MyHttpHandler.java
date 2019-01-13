package org.sec.core.net.mina;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MyHttpHandler extends IoHandlerAdapter {
	
	private Map<Long, IoSession> clients = new HashMap<>();
	private Map<Long, String> messages = new HashMap<>();
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated: " + session.getId());
		clients.put(session.getId(), session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened: " + session.getId());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed: " + session.getId());
		clients.remove(session.getId());
		messages.remove(session.getId());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println(session.getId() + " sessionIdle, status=" + status);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		long sessionId = session.getId();
		System.out.println("messageReceived from " + sessionId + ". message: " + message);
		if (message != null) {
			String newMsg = String.valueOf(message);
			String oldMsg = messages.get(sessionId);
			if (oldMsg == null) {
				messages.put(sessionId, newMsg);
			} else if (StringUtils.isBlank(newMsg)) {
				String[] msgs = oldMsg.split("\r\n");
				for (String msg : msgs) {
					if (msg.matches("GET .+")) {
						String res = msg.split(" ")[1];
						switch (res) {
						case "/favicon.ico":
							session.write(new File("res/icon.png"));
							break;
						case "/":
							session.write("hello");
							break;
						case "/login":
							session.write("login");
							break;
						default:
							break;
						}
					}
				}
				clients.remove(session.getId());
				messages.remove(session.getId());
				session.close(true);
			} else {
				messages.put(sessionId, oldMsg + "\r\n" + newMsg);
			}
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("Sent message[" + message + "] to client " + session.getId());
	}
	
	@Override
    public void inputClosed(IoSession session) throws Exception {
		clients.remove(session.getId());
		messages.remove(session.getId());
        super.inputClosed(session);
    }

}
