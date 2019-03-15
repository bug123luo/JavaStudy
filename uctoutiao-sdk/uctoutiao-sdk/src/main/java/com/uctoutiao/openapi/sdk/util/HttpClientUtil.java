/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.util;


import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

  private static CloseableHttpClient httpclient = null;

  static {
    int kMaxTotal = 200;
    int kMaxPerRoute = 20;

    RegistryBuilder<ConnectionSocketFactory> registryBuilder =
        RegistryBuilder.<ConnectionSocketFactory>create();
    registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE).build();

    try {
      KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      TrustStrategy anyTrustStrategy = new TrustStrategy() {
        public boolean isTrusted(X509Certificate[] x509Certificates, String s)
            throws CertificateException {
          return true;
        }
      };
      SSLContext sslContext =
          SSLContexts.custom().useTLS().loadTrustMaterial(trustStore, anyTrustStrategy).build();
      LayeredConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(sslContext,
          SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      registryBuilder.register("https", sslSF);
    } catch (Exception e) {

    }
    Registry<ConnectionSocketFactory> registry = registryBuilder.build();
    PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);

    ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Consts.UTF_8).build();

    SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).setSoKeepAlive(true)
        .setSoReuseAddress(true).setSoTimeout(1000).build();

    cm.setDefaultConnectionConfig(connectionConfig);
    cm.setDefaultSocketConfig(socketConfig);
    cm.setMaxTotal(kMaxTotal);
    cm.setDefaultMaxPerRoute(kMaxPerRoute);

    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000)
        .setConnectionRequestTimeout(1000).setSocketTimeout(1000).build();

    httpclient = HttpClients.custom().setConnectionManager(cm)
        .setDefaultRequestConfig(requestConfig).build();
  }


  public static String post(String url, List<NameValuePair> postParams) throws Exception {
    String result = "";
    HttpPost post = new HttpPost(url);
    post.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
    CloseableHttpResponse response = httpclient.execute(post);
    try {
      if (response.getStatusLine().getStatusCode() == 200) {
        result = EntityUtils.toString(response.getEntity());
      }
    } finally {
      if (response != null) {
        response.close();
      }
    }
    return result;
  }

  public static String post(String url, String body) throws Exception {
    String result = "";
    HttpPost post = new HttpPost(url);
    post.setHeader("Content-type", "application/json");
    post.setEntity(new StringEntity(body, "UTF-8"));
    CloseableHttpResponse response = httpclient.execute(post);
    try {
      if (response.getStatusLine().getStatusCode() == 200) {
        result = EntityUtils.toString(response.getEntity());
      }
    } finally {
      if (response != null) {
        response.close();
      }
    }
    return result;
  }

  public static String get(String url) throws Exception {
    String result = "";
    HttpGet get = new HttpGet(url);
    CloseableHttpResponse response = httpclient.execute(get);
    try {
      if (response.getStatusLine().getStatusCode() == 200) {
        result = EntityUtils.toString(response.getEntity());
      }
    } finally {
      if (response != null) {
        response.close();
      }
    }
    return result;
  }

}
