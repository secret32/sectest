package org.sec.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClient4Util {

    private static HttpClient4Util HTTP_CLIENT4_UTIL = null;

    public static HttpClient4Util getInstance() {
        if (HTTP_CLIENT4_UTIL == null) {
            HTTP_CLIENT4_UTIL = new HttpClient4Util();
        }
        return HTTP_CLIENT4_UTIL;
    }

    private static HttpClient httpclient = getHttpClient();

    private static HttpClient getHttpClient() {
        if (httpclient == null) {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(15000)
                    .setSocketTimeout(15000).build();
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(10);
            httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).setConnectionManager(cm).build();
        }
        return httpclient;
    }

    public static String getByJsonParam(String urlstr, String paraJson) {
        String body = null;
        HttpPost httpPost = new HttpPost(urlstr);
        StringEntity requestEntity = new StringEntity(paraJson, ContentType.TEXT_XML);
        httpPost.setEntity(requestEntity);
        try {
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            httpPost.abort();
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return body;
    }

    public static String get(String urlstr) {
        String html = null;
        try {
            html = get(urlstr, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    public static String get(String urlstr, int count) {
        HttpGet httpGet = null;
        String html = null;
        boolean reqAgain = false;
        try {
            count++;
            if (count > 5) {
                throw new Exception();
            }
            URL url = new URL(urlstr);
            URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(),
                    url.getQuery(), null);
            httpGet = new HttpGet(uri);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
                    && httpEntity != null) {
                html = EntityUtils.toString(httpEntity, "UTF-8");
                EntityUtils.consume(httpEntity);
            } else {
                reqAgain = true;
            }
        } catch (Exception e) {
            if (httpGet != null) {
                httpGet.abort();
            }
            e.printStackTrace();
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }

        }
        if (reqAgain) {
            html = get(urlstr, count);
        }
        return html;
    }

    public static String post(String url, Map<String, String> map) {
        List<NameValuePair> nvps = new ArrayList<>();
        for (String key : map.keySet()) {
            NameValuePair nvp = new BasicNameValuePair(key, map.get(key));
            nvps.add(nvp);
        }
        HttpPost httpPost = null;
        String html = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null) {
                html = EntityUtils.toString(httpEntity, "UTF-8");
                EntityUtils.consume(httpEntity);
            }

        } catch (Exception e) {
            if (httpPost != null)
                httpPost.abort();
            e.printStackTrace();
        } finally {
            if (httpPost != null)
                httpPost.releaseConnection();
        }
        return html;
    }

    public static String post(String url, String reqContent) {
        HttpPost httpPost = null;
        String html = null;
        try {
            httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(reqContent);
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null) {
                html = EntityUtils.toString(httpEntity, "UTF-8");
                EntityUtils.consume(httpEntity);
            }
        } catch (Exception e) {
            if (httpPost != null)
                httpPost.abort();
            e.printStackTrace();
        } finally {
            if (httpPost != null)
                httpPost.releaseConnection();
        }
        return html;
    }

    /**
     * 下载文件
     * @param urlStr 下载url
     * @param path 文件的保存路径（文件夹）
     * @return 文件本地路径
     */
    public static String download(String urlStr, String path) {
        HttpGet httpGet = null;
        OutputStream out = null;
        try {
            httpGet = new HttpGet(URI.create(urlStr));
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK
                    && httpEntity != null) {
                String fileName;
                try {
                    // 根据响应头获取文件名
                    // 适配字幕库网站，不知道是否兼容其他网站
                    String tmp = response.getHeaders("Content-Disposition")[0].getValue().split(";")[1];
                    fileName = URLDecoder.decode(tmp.substring(tmp.indexOf('"') + 1, tmp.lastIndexOf('"')), "utf-8");
                } catch (Exception e) {
                    fileName = urlStr.substring(urlStr.lastIndexOf('/'));
                }
                Path filePath = Paths.get(path);
                if (!Files.exists(filePath)) {
                    Files.createDirectory(filePath);
                }
                out = Files.newOutputStream(Paths.get(path, fileName),
                        StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                httpEntity.writeTo(out);
                return path + File.separator + fileName;
            }
        } catch (Exception e) {
            if (httpGet != null) {
                httpGet.abort();
            }
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        return null;
    }


}