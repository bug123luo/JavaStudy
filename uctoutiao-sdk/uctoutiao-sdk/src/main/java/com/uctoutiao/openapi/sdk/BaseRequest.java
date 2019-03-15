/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.BaseResponse;
import com.uctoutiao.openapi.sdk.util.HttpClientUtil;

public class BaseRequest {

  protected static final String URL_AUTH = "%s/openiflow/auth/token";

  protected static final String URL_CHANNELS = "%s/openiflow/openapi/%s/channels?access_token=%s&app=%s&dn=%s";

  protected static final String URL_CITIES = "%s/openiflow/openapi/%s/cities?access_token=%s&app=%s&dn=%s";

  protected static final String URL_CHANNEL = "%s/openiflow/openapi/%s/channel/%s?access_token=%s&app=%s&dn=%s";

  protected static final String URL_ARTICLE =
      "%s/openarticle/openapi/%s/article/%s?access_token=%s&app=%s&dn=%s&cid=%s";

  protected static final String URL_ARTICLE_RELATED =
      "%s/openarticle/openapi/%s/article/%s/related?access_token=%s&app=%s&dn=%s&cid=%s";

  protected static final String URL_CLIENT_EVENT = "%s/openlog/openapi/%s/client_event?access_token=%s&app=%s&dn=%s";

  protected String app;

  protected String dn;

  protected String server = "https://open.uczzd.cn";

  protected String version = "v2";

  protected static final ObjectMapper mapper = new ObjectMapper();

  {
    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public BaseRequest() {}

  public BaseRequest(String app, String dn) {
    this.app = app;
    this.dn = dn;
  }

  public BaseRequest(String app, String dn, String server) {
    this.app = app;
    this.dn = dn;
    this.server = server;
  }

  public void setServer(String server) {
    this.server = server;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  protected String appendParams(String url, Map<String, Object> params) {
    if (params != null && params.size() > 0) {
      for (Entry<String, Object> entry : params.entrySet()) {
        url += "&" + entry.getKey() + "=" + entry.getValue().toString();
      }
    }
    return url;
  }

  protected BaseResponse doGetRequet(String url) throws OpenApiException {
    String result;
    try {
      result = HttpClientUtil.get(url);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.HTTP_ERROR, e.getMessage());
    }
    if (StringUtils.isBlank(result)) {
      throw OpenApiException.BLANK_RESPONSE;
    }

    BaseResponse baseResponse = BaseResponse.parseBaseResponse(result);
    if (baseResponse.getStatus() != 0) {
      throw new OpenApiException(baseResponse.getStatus(), baseResponse.getMessage());
    }
    return baseResponse;
  }


  protected BaseResponse doPostRequest(String url, String jsonBody) throws OpenApiException {
    String result;
    try {
      result = HttpClientUtil.post(url, jsonBody);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.HTTP_ERROR, e.getMessage());
    }
    if (StringUtils.isBlank(result)) {
      throw OpenApiException.BLANK_RESPONSE;
    }
    BaseResponse baseResponse = BaseResponse.parseBaseResponse(result);
    if (baseResponse.getStatus() != 0) {
      throw new OpenApiException(baseResponse.getStatus(), baseResponse.getMessage());
    }
    return baseResponse;
  }

}
