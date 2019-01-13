package org.sec.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by secret on 14-6-27.
 */
public class HttpUtil {

    private final static Logger logger = Logger.getLogger(HttpUtil.class);

    @SuppressWarnings("unused")
	private final static int BUFFER_SIZE = 1024;

    public static enum Protocol {
        HTTP("http"), HTTPS("https");
        private String protocol;

        Protocol(String protocol) {
            this.protocol = protocol;
        }

        public String getProtocol() {
            return this.protocol;
        }
    }

    public static enum RequestMethod {
        GET("GET"),
        POST("POST");
        private String methodName;

        private RequestMethod(String methodName) {
            this.methodName = methodName;
        }

        public String getMethodName() {
            return this.methodName;
        }

    }

    public static String request(String url) throws IOException {
        return request(url, RequestMethod.GET, null);
    }

    public static String request(String url, RequestMethod requestMethod, String params) throws IOException {
        if (StringUtils.startsWithIgnoreCase(url, Protocol.HTTP.getProtocol()))
            return httpRequest(url, requestMethod, params);
        else if (StringUtils.startsWithIgnoreCase(url, Protocol.HTTPS.getProtocol()))
            return httpsRequest(url, requestMethod, params);
        else
            throw new IOException("unsupported protocol");
    }

    private static String httpRequest(String httpUrl, RequestMethod requestMethod, String params) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod(requestMethod.getMethodName());
            if (requestMethod.equals(RequestMethod.GET))
                connection.connect();
            if (params != null) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(params.getBytes("UTF-8"));
                outputStream.close();
            }
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    sb.append(str);
                }
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                connection.disconnect();
            } else {
                logger.error(responseCode + "  " + connection.getResponseMessage());
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return sb.toString();
    }

    private static String httpsRequest(String httpsUrl, RequestMethod requestMethod, String params) {
        StringBuilder sb = new StringBuilder();
        try {
            TrustManager[] tm = {new SecX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(httpsUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(ssf);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod.getMethodName());
            if (requestMethod.equals(RequestMethod.GET))
                connection.connect();
            if (params != null) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(params.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            connection.disconnect();
        } catch (Exception e) {
            logger.error(e);
        }
        return sb.toString();
    }

}
