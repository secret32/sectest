package org.sec.core.net.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by secret on 14-7-16.
 */
public class SecSocket {

    public void client() {
        try (Socket socket = new Socket()) {
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 1556);
            socket.connect(address);
            OutputStream out = socket.getOutputStream();
            String msg = String.format("GET / HTTP/1.1\r\n\r\n");
            System.out.println(msg);
            out.write(msg.getBytes());
            InputStream in = socket.getInputStream();
            byte[] buf = new byte[1024];
            int i = in.read(buf, 0, 1024);
            System.out.printf("len:%d;str:%s", i, new String(buf));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void server() {
        try (ServerSocket ss = new ServerSocket()) {
            ss.bind(new InetSocketAddress("127.0.0.1", 1556));
            System.out.println("server is running....");
            while (true) {
                try {
                    Socket s = ss.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    String line = null;
                    System.out.print(s.getInetAddress() + " sent: ");
                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                        out.write("hahahahhahahhahhahhahhahha");
                        out.flush();
                        System.out.println("reply");
                    }
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
