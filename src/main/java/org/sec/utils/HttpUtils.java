package org.sec.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUtils {

    private final static int BUFFER_SIZE = 1024;

    public void urlConn(String urlStr) {
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                is = connection.getInputStream();
                ByteBuilder be = new ByteBuilder();
                byte[] bytes = new byte[BUFFER_SIZE];
                int readSize = -1;
                while ((readSize = is.read(bytes, 0, BUFFER_SIZE)) != -1) {
                    be.append(bytes, readSize);
                }
                System.out.println(be);
            } else {
                System.out.println(responseCode + "  " + connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        HttpUtils util = new HttpUtils();
        util.urlConn("http://www.baidu.com");
    }

    @SuppressWarnings("unused")
	private void visitBaidu() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            // The underlying HTTP connection is still held by the response
            // object
            // to allow the response content to be streamed directly from the
            // network socket.
            // In order to ensure correct deallocation of system resources
            // the user MUST either fully consume the response content or abort
            // request
            // execution by calling CloseableHttpResponse#close().

            try {
                System.out.println(response1.getStatusLine());
                HttpEntity entity1 = response1.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity1);
            } finally {
                response1.close();
            }

            HttpPost httpPost = new HttpPost(
                    "https://passport.baidu.com/v2/?login&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "真田信春"));
            nvps.add(new BasicNameValuePair("password", "********"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
