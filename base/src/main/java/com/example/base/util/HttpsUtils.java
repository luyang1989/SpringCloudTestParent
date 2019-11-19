package com.example.base.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;

public class HttpsUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsUtils.class);

    public static CloseableHttpClient getHttpClient() throws Exception {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    public static String sendPostJson(String url, String json, String charset) {
        // CloseableHttpClient httpClient = HttpClients.createDefault() ;
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = getHttpClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("url = " + url + ", statusCode = " + statusCode);
            } else {
                if (response != null) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, charset);
                    }
                } else {
                    LOGGER.info("url = " + url + ", 返回结果为null");
                }
            }

        } catch (Exception e) {
            LOGGER.error("url = " + url + ", 发生网络异常 " + e);
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
                httpPost = null;
            }
        }
        return result;
    }

}
